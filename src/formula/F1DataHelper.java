/**
 * 
 */
package formula;

import java.awt.Color;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import formula.packets.PacketCarDamage;
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
	private static PacketParticipants participants;

	private static FileOutputStream fos = null;
	private static ObjectOutputStream oos = null;

	public static int[] getVehicleTrackWarnings() {
		return vehicleTrackWarnings;
	}

	public static void setVehicleTrackWarnings(int[] argVehicleTrackWarnings) {
		vehicleTrackWarnings = argVehicleTrackWarnings;
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
		return participants.getParticipantDataList().get(argVehicleIdx).getNameAsString();

	}

	public static boolean isPublicTelemetryForIdx(short argVehicleIdx) {
		return participants.getParticipantDataList().get(argVehicleIdx).getYourTelemetry() == 1;
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

	public static void writeSingleSerFile(Object argObj, long argSessionUUID) throws IOException {
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

		oos.writeUnshared(argObj);
		oos.flush();
	}

	public static void increaseWarningByVehicleIdx(short vehicleIdx) {
		vehicleTrackWarnings[vehicleIdx]++;
	}

	public static void resetWarningByVehicleIdx(short vehicleIdx) {
		vehicleTrackWarnings[vehicleIdx] = 0;
	}

	public static void readSingleSerFile() throws IOException, ClassNotFoundException {
		File f = new File("c:/tmp/-7963470744936800331.ser");
		FileInputStream fis = new FileInputStream(f);
		ObjectInputStream ois = new ObjectInputStream(fis);

		Object obj;

		boolean read = true;

		try (ois) {
			while (read) {
				obj = ois.readUnshared();
				if (obj != null)
					System.out.println(obj.toString());

			}
		} catch (EOFException e) {
			read = false;
			System.out.println("jetzt is aus");
		}
	}

	public static void updateTyres(short argVehicleIdx, PacketCarDamage argPacket, int argType) {
		if (argVehicleIdx < 0) {
			return;
		}
		float[] tyreWear = argPacket.getCarDamageDataList().get(argVehicleIdx).getTyresWear();
		StringBuilder sb = new StringBuilder();
		if (isPublicTelemetryForIdx(argVehicleIdx)) {
			sb.append("<html><strike>");
		}
		sb.append(String.format(DECIMAL_FORMAT, (tyreWear[2])));
		sb.append(" ");
		sb.append(String.format(DECIMAL_FORMAT, (tyreWear[3])));
		sb.append(" ");
		sb.append(String.format(DECIMAL_FORMAT, (tyreWear[0])));
		sb.append(" ");
		sb.append(String.format(DECIMAL_FORMAT, (tyreWear[1])));
		if (isPublicTelemetryForIdx(argVehicleIdx)) {
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

}
