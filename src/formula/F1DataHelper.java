/**
 * 
 */
package formula;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import formula.constants.SessionConstants;
import formula.constants.TrackConstants;
import formula.constants.TyreConstants;
import formula.data.DriverLapPosition;
import formula.packets.IF1Packet;
import formula.packets.LapData;
import formula.packets.PacketCarDamage;
import formula.packets.PacketCarStatus;
import formula.packets.PacketFinalClassification;
import formula.packets.PacketLapData;
import formula.packets.PacketParticipants;
import formula.packets.PacketSession;

/**
 * @author reinh
 *
 */
public class F1DataHelper {

	private static final String DECIMAL_FORMAT = "%.01f";

	private static long curSessionUUID;

	private static short frontVehicleIdx = 0;
	private static short myVehicleIdx = 0;
	private static short behindVehicleIdx = 0;
	private static boolean useSelf = true;
	private static int[] vehicleTrackWarnings = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0 };

	private static short[] vehicleCurrentTyre = new short[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0 };
	private static PacketParticipants participants;

	private static Map<Short, List<DriverLapPosition>> dlpMap = new HashMap<>();

	private static List<Float> driverDistanceTotal = new ArrayList<>(
			Arrays.asList(0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f));

	private static int[] vehicleFrontwingChange = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0 };

	private static int[] vehiclePitStopTime = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0 };

	private static short trackId = -1;
	private static short trackLength = -1;

	private static FileOutputStream fos = null;
	private static ObjectOutputStream oos = null;

	private static String session = "";
	private static String track = "";

	private static final Logger log = LogManager.getLogger(F1DataHelper.class);

	public static int[] getVehicleFrontwingChange() {
		return vehicleFrontwingChange;
	}

	public static void setVehicleFrontwingChange(int[] argVehicleFrontwingChange) {
		vehicleFrontwingChange = argVehicleFrontwingChange;
	}

	public static int[] getVehicleTrackWarnings() {
		return vehicleTrackWarnings;
	}

	public static void setVehicleTrackWarnings(int[] argVehicleTrackWarnings) {
		vehicleTrackWarnings = argVehicleTrackWarnings;
	}

	public static short[] getVehicleCurrentTyre() {
		return vehicleCurrentTyre;
	}

	public static void setVehicleCurrentTyre(short[] argVehicleCurrentTyre) {
		vehicleCurrentTyre = argVehicleCurrentTyre;
	}

	public static short getFrontVehicleIdx() {
		return frontVehicleIdx;
	}

	public static void setFrontVehicleIdx(short argFrontVehicleIdx) {
		frontVehicleIdx = argFrontVehicleIdx;
	}

	public static short getMyVehicleIdx() {
		return myVehicleIdx;
	}

	public static void setMyVehicleIdx(short argMyVehicleIdx) {
		myVehicleIdx = argMyVehicleIdx;
	}

	public static short getBehindVehicleIdx() {
		return behindVehicleIdx;
	}

	public static void setBehindVehicleIdx(short argBehindVehicleIdx) {
		behindVehicleIdx = argBehindVehicleIdx;
	}

	public static boolean isUseSelf() {
		return useSelf;
	}

	public static void setUseSelf(boolean argUseSelf) {
		useSelf = argUseSelf;
	}

	public static String getNameForIdx(short argVehicleIdx) {
		if (participants == null || argVehicleIdx < 0 || argVehicleIdx > participants.getParticipantDataList().size()) {
			return "";
		}
		return participants.getParticipantDataList().get(argVehicleIdx).getNameAsString();
	}

	public static boolean isPublicTelemetryForIdx(short argVehicleIdx) {
		if (participants == null) {
			return false;
		}
		return participants.getParticipantDataList().get(argVehicleIdx).getYourTelemetry() == 0;
	}

	public static void setParticipants(PacketParticipants argParticipants) {
		participants = argParticipants;
	}

	public static PacketParticipants getParticipants() {
		return participants;
	}

	public static FileOutputStream getFos() {
		return fos;
	}

	public static ObjectOutputStream getOos() {
		return oos;
	}

	public static short getVehicleIdxDriverInFront(short argMyPos, PacketLapData argPacketLapData) {

		if (argMyPos > 1) {
			for (short i = 0; i < 22; i++) {
				if (argPacketLapData.getLapDataList().get(i).getCarPosition() == argMyPos - 1) {
					return i;
				}
			}
		}
		return 0;
	}

	public static short getVehicleIdxDriverBehind(short argMyPos, PacketLapData argPacketLapData) {
		if (argMyPos < 20) {
			for (short i = 0; i < 22; i++) {
				if (argPacketLapData.getLapDataList().get(i).getCarPosition() == argMyPos + 1) {
					return i;
				}
			}
		}
		return 20;
	}

	public static void writeSingleSerFile(IF1Packet argF1Packet, long argSessionUUID) throws IOException {
		if (argF1Packet instanceof PacketSession) {
			session = SessionConstants.SESSION_TYPE.get(((PacketSession) argF1Packet).getSessionType());
			track = TrackConstants.TRACK.get(((PacketSession) argF1Packet).getTrackId());
		}

		if (argF1Packet instanceof PacketFinalClassification) {
			argSessionUUID *= -1;
		}

		if (curSessionUUID != argSessionUUID && fos != null) {
			oos.flush();
			fos.flush();
			oos.close();
			fos.close();
			oos = null;
			fos = null;

			renamefile(curSessionUUID, track, session);

			curSessionUUID = argSessionUUID;
		}
		if (fos == null)
			fos = new FileOutputStream(
					System.getProperty("data.writeToFile.location") + File.separator + argSessionUUID + ".ser");
		if (oos == null)
			oos = new ObjectOutputStream(fos);

		oos.writeUnshared(argF1Packet);
		oos.flush();
	}

	protected static void renamefile(long argSessionUUID, String argTrackName, String argSessionName) {
		Path source = Paths
				.get(System.getProperty("data.writeToFile.location") + File.separator + argSessionUUID + ".ser");
		try {
			Files.move(source,
					source.resolveSibling(argTrackName + "_" + argSessionName + "_" + argSessionUUID + ".ser"));
		} catch (IOException e) {
			log.error("Error renaming file", e);
		}
	}

	public static void increaseWarningByVehicleIdx(short vehicleIdx) {
		vehicleTrackWarnings[vehicleIdx]++;
	}

	public static void resetWarningByVehicleIdx(short vehicleIdx) {
		vehicleTrackWarnings[vehicleIdx] = 0;
	}

	public static void readSingleSerFile(String argFileName) {
		File f = new File(argFileName);

		Object obj;

		boolean read = true;
		try (FileInputStream fis = new FileInputStream(f); ObjectInputStream ois = new ObjectInputStream(fis)) {

			while (read) {
				obj = ois.readUnshared();
				if (obj instanceof IF1Packet) {
					F1UdpListener.handlePacket((IF1Packet) obj);
				}
			}
		} catch (IOException | ClassNotFoundException e) {
			read = false;
			log.info("jetzt is aus");
		}

	}

	public static void updateTyreWear(PacketCarDamage argCarDamage) {
		F1DataHelper.updateTyreWear(F1DataHelper.getFrontVehicleIdx(), argCarDamage, 0);
		F1DataHelper.updateTyreWear(F1DataHelper.getMyVehicleIdx(), argCarDamage, 1);
		F1DataHelper.updateTyreWear(F1DataHelper.getBehindVehicleIdx(), argCarDamage, 2);

	}

	public static void updateTyreWear(short argVehicleIdx, PacketCarDamage argPacket, int argType) {
		if (argVehicleIdx < 0) {
			return;
		}
		float[] tyreWear = argPacket.getCarDamageDataList().get(argVehicleIdx).getTyresWear();

		StringBuilder sb = new StringBuilder();
		if (isPublicTelemetryForIdx(argVehicleIdx) && argVehicleIdx != getMyVehicleIdx()) {
			sb.append("<html><strike>");
		}

		sb.append(String.format(DECIMAL_FORMAT, (tyreWear[2])));
		sb.append(" ");
		sb.append(String.format(DECIMAL_FORMAT, (tyreWear[3])));
		sb.append(" ");
		sb.append(String.format(DECIMAL_FORMAT, (tyreWear[0])));
		sb.append(" ");
		sb.append(String.format(DECIMAL_FORMAT, (tyreWear[1])));
		if (isPublicTelemetryForIdx(argVehicleIdx) && argVehicleIdx != getMyVehicleIdx()) {
			sb.append("</strike></html>");
		}

		if (argType == 0) {
			F1Frame.setTyreFrontText(sb.toString());
		}
		if (argType == 1) {
			F1Frame.setTyreSelfText(sb.toString());
		}
		if (argType == 2) {
			F1Frame.setTyreBehindText(sb.toString());
		}

	}

	public static void updateCurrentTyre() {
		F1DataHelper.updateCurrentTyre(F1DataHelper.getFrontVehicleIdx(), 0);
		F1DataHelper.updateCurrentTyre(F1DataHelper.getMyVehicleIdx(), 1);
		F1DataHelper.updateCurrentTyre(F1DataHelper.getBehindVehicleIdx(), 2);
	}

	public static void updateCurrentTyre(short argVehicleIdx, int argType) {
		if (argVehicleIdx < 0) {
			return;
		}
		short currentTyre = getVehicleCurrentTyre()[argVehicleIdx];

		String currentCompoundName = TyreConstants.VISUAL_COMPOUND.get(currentTyre);

		Color tyreColor = null;

		if (currentCompoundName == null) {
			currentCompoundName = "WET";
		}

		switch (currentCompoundName) {
		case "INTERMEDIATE":
			tyreColor = Color.GREEN;
			break;
		case "WET":
			tyreColor = Color.BLUE;
			break;
		case "SOFT":
			tyreColor = Color.RED;
			break;
		case "MEDIUM":
			tyreColor = Color.YELLOW;
			break;
		case "HARD":
			tyreColor = Color.WHITE;
			break;
		case "SUPER SOFT":
			tyreColor = Color.PINK;
			break;
		default:
			break;

		}

		if (argType == 0) {
			F1Frame.setTyreFrontColor(tyreColor);
		}
		if (argType == 1) {
			F1Frame.setTyreSelfColor(tyreColor);
		}
		if (argType == 2) {
			F1Frame.setTyreBehindColor(tyreColor);
		}

	}

	public static void updateERSStatus(PacketCarStatus argCarStatus) {
		F1DataHelper.updateERSStatus(F1DataHelper.getFrontVehicleIdx(), argCarStatus, 0);
		F1DataHelper.updateERSStatus(F1DataHelper.getMyVehicleIdx(), argCarStatus, 1);
		F1DataHelper.updateERSStatus(F1DataHelper.getBehindVehicleIdx(), argCarStatus, 2);
	}

	public static void updateERSStatus(short argVehicleIdx, PacketCarStatus argCarStatus, int argType) {
		if (argVehicleIdx < 0) {
			return;
		}

		String ersText = String.format(DECIMAL_FORMAT,
				argCarStatus.getCarStatusDataList().get(argVehicleIdx).getErsStoreEnergy() / 40000, 0);
		Color ersColor = argCarStatus.getCarStatusDataList().get(argVehicleIdx).getErsDeployMode() == 3 ? Color.BLUE
				: Color.WHITE;

		if (argType == 0) {
			F1Frame.setERSFront(ersText, ersColor);
		}
		if (argType == 1) {
			F1Frame.setERSSelf(ersText, ersColor);
		}
		if (argType == 2) {
			F1Frame.setERSBehind(ersText, ersColor);
		}
	}

	public static void updateWarnings() {
		F1DataHelper.updateWarnings(F1DataHelper.getFrontVehicleIdx(), 0);
		F1DataHelper.updateWarnings(F1DataHelper.getMyVehicleIdx(), 1);
		F1DataHelper.updateWarnings(F1DataHelper.getBehindVehicleIdx(), 2);
	}

	public static void updateWarnings(short argVehicleIdx, int argType) {
		if (argVehicleIdx < 0) {
			return;
		}
		String text = "  " + vehicleTrackWarnings[argVehicleIdx] + "  ";

		Color bgColor = Color.GREEN;
		if (vehicleTrackWarnings[argVehicleIdx] == 1) {
			bgColor = Color.YELLOW;
		}
		if (vehicleTrackWarnings[argVehicleIdx] == 2) {
			bgColor = Color.RED;
		}

		if (argType == 0) {
			F1Frame.setWarningFrontText(text);
			F1Frame.setWarningFrontColor(bgColor);
		}
		if (argType == 1) {
			F1Frame.setWarningSelfText(text);
			F1Frame.setWarningSelfColor(bgColor);
		}
		if (argType == 2) {
			F1Frame.setWarningBehindText(text);
			F1Frame.setWarningBehindColor(bgColor);
		}
	}

	public static void createDriverLapPosition(PacketLapData argPacketLapData) {
		short idx = 0;
		for (LapData lapData : argPacketLapData.getLapDataList()) {
			if (lapData.getCurrentLapNum() > getLastLapNumberPerDriver(idx)) {
				addNewLap(new DriverLapPosition(idx, lapData.getCurrentLapNum(), lapData.getCarPosition()));
			}
			float driverCurrTotal = driverDistanceTotal.get(idx);

			if (driverCurrTotal <= lapData.getTotalDistance() && lapData.getResultStatus() != 3
					&& getParticipants().getParticipantDataList().get(idx).getAiControlled() == 0) {
				driverDistanceTotal.set(idx, lapData.getTotalDistance());
			}

			idx++;
		}
	}

	public static void addNewLap(DriverLapPosition argDriverLapPosition) {
		List<DriverLapPosition> dlpList = dlpMap.getOrDefault(argDriverLapPosition.getVehicleId(), new ArrayList<>());
		dlpList.add(argDriverLapPosition);
		dlpMap.put(Short.valueOf(argDriverLapPosition.getVehicleId()), dlpList);
	}

	public static int getLastLapNumberPerDriver(short argVehicleIdx) {
		List<DriverLapPosition> dlpList = dlpMap.get(argVehicleIdx);
		if (dlpList == null) {
			return 0;
		}
		return dlpList.get(dlpList.size() - 1).getLapNo();
	}

	public static void resetDriverLapPosition() {
		dlpMap = new HashMap<>();
	}

	public static void resetDriverDistance() {
		driverDistanceTotal = new ArrayList<>(
				Arrays.asList(0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f));
	}

	public static void resetPitStops() {
		vehicleFrontwingChange = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		vehiclePitStopTime = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
	}

	public static void printLeadingLaps() {
		log.info("Leading Laps");
		for (List<DriverLapPosition> dlpList : dlpMap.values()) {
			short vehicleIdx = 0;
			int leadcount = 0;
			for (DriverLapPosition driverLapPosition : dlpList) {
				vehicleIdx = driverLapPosition.getVehicleId();
				if (driverLapPosition.getPos() == 1) {
					leadcount += 1;
				}
			}
			if (leadcount > 0) {
				log.info("{}: {}", F1DataHelper.getNameForIdx(vehicleIdx), leadcount);
			}
		}
	}

	public static void printTotalDrivenDistance() {
		log.info("Distance Driven");
		for (short i = 0; i < driverDistanceTotal.size(); i++) {
			if (log.isInfoEnabled()) {
				log.info("{}: {}m", F1DataHelper.getNameForIdx(i),
						NumberFormat.getInstance(Locale.GERMAN).format(driverDistanceTotal.get(i)));
			}
		}
	}

	public static void printFrontWingChanges() {
		log.info("Frontwing Changes");
		for (short i = 0; i < vehicleFrontwingChange.length; i++) {
			if (log.isInfoEnabled()) {
				log.info("{}: {}", F1DataHelper.getNameForIdx(i), vehicleFrontwingChange[i]);
			}
		}
	}

	public static void setTrackId(short argTrackId) {
		trackId = argTrackId;
	}

	public static short getTrackId() {
		return trackId;
	}

	public static void setTrackLength(short argTrackLength) {
		trackLength = argTrackLength;
	}

	public static short getTrackLength() {
		return trackLength;
	}

	static {
		Properties props = System.getProperties();

		try (InputStream in = new FileInputStream(props.getProperty("players.properties.path"))) {
			props.load(in);
			props.putAll(System.getProperties());
			System.setProperties(props);
		} catch (IOException ex) {
			log.error(ex);
		}

	}

	private F1DataHelper() {

	}

	public static void checkPitstop(PacketLapData argPacketLapData) {
		short idx = 0;
		for (LapData lapData : argPacketLapData.getLapDataList()) {
			String name = F1DataHelper.getNameForIdx(idx);
//			if ("Verstappen".equalsIgnoreCase(name)) {
//				System.out.println(name + " " + "pitTimerActive=" + lapData.getPitLaneTimerActive());
//				System.out.println(name + " " + "pitStopTime=" + lapData.getPitStopTimerInMS());
				if (lapData.getPitLaneTimerActive() == 0 && vehiclePitStopTime[idx] > 0) {
					System.out.println("pitstop fertig");
					if (vehiclePitStopTime[idx] > 5000) {
						System.out.println("flügel hamma a tauscht");
						vehicleFrontwingChange[idx]++;
					}

					vehiclePitStopTime[idx] = 0;
				}
				if (lapData.getPitLaneTimerActive() == 1) {
					vehiclePitStopTime[idx] = lapData.getPitStopTimerInMS();
				}
//			}
			idx++;
		}

	}

}
