/**
 * 
 */
package formula;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import formula.constants.TyreConstants;
import formula.packets.IF1Packet;
import formula.packets.PacketCarDamage;
import formula.packets.PacketCarStatus;
import formula.packets.PacketFinalClassification;
import formula.packets.PacketLapData;
import formula.packets.PacketParticipants;

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

	private static FileOutputStream fos = null;
	private static ObjectOutputStream oos = null;

	private static final Logger log = LogManager.getLogger(F1DataHelper.class);

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
		if (participants == null) {
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
			curSessionUUID = argSessionUUID;
		}
		if (fos == null)
			fos = new FileOutputStream("c:/tmp/" + argSessionUUID + ".ser");
		if (oos == null)
			oos = new ObjectOutputStream(fos);

		oos.writeUnshared(argF1Packet);
		oos.flush();
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

	private F1DataHelper() {
	}

}
