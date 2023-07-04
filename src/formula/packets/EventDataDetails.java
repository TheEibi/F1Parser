/**
 * 
 */
package formula.packets;

import java.io.Serializable;
import java.nio.ByteBuffer;

import formula.constants.InfringementConstants;
import formula.constants.PenaltyConstants;

/**
 * @author reinh
 *
 */
public class EventDataDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1117396336290563092L;
	private transient ByteBuffer bb;
	private String eventType;

	// Common
	private short vehicleIdx;
	private short otherVehicleIdx;

	// FTLP
	private float lapTime;

	// PENA
	private short penaltyType;
	private short infringementType;
	private short time;
	private short lapNum;
	private short placesGained;

	// SPTP
	private float speed;
	private short isOverallFastestInSession;
	private short isDriverFastestInSession;
	private short fastestVehicleIdxInSession;
	private float fastestSpeedInSession;

	// LGOT
	private short numLights;

	// FLBK
	private int flashbackFrameIdentifier;
	private float flashbackSessionTime;

	// BUTN
	private int buttonStatus;
	
	// OVTK
	private short overtakingVehicleIdx;
	private short beingOvertakenVehicleIdx;

	public void initV22(ByteBuffer argBb, String argEventType) {
		bb = argBb;
		eventType = argEventType;
	}

	public ByteBuffer getBb() {
		return bb;
	}

	public String getEventType() {
		return eventType;
	}

	public short getVehicleIdx() {
		return vehicleIdx;
	}

	public short getOtherVehicleIdx() {
		return otherVehicleIdx;
	}

	public float getLapTime() {
		return lapTime;
	}

	public short getPenaltyType() {
		return penaltyType;
	}

	public short getInfringementType() {
		return infringementType;
	}

	public short getTime() {
		return time;
	}

	public short getLapNum() {
		return lapNum;
	}

	public short getPlacesGained() {
		return placesGained;
	}

	public float getSpeed() {
		return speed;
	}

	public short getIsOverallFastestInSession() {
		return isOverallFastestInSession;
	}

	public short getIsDriverFastestInSession() {
		return isDriverFastestInSession;
	}

	public short getFastesVehicleIdxInSession() {
		return fastestVehicleIdxInSession;
	}

	public float getFastestSpeedInSession() {
		return fastestSpeedInSession;
	}

	public short getNumLights() {
		return numLights;
	}

	public int getFlashbackFrameIdentifier() {
		return flashbackFrameIdentifier;
	}

	public float getFlashbackSessionTime() {
		return flashbackSessionTime;
	}

	public int getButtonStatus() {
		return buttonStatus;
	}

	public void parseSSTA() {
	}

	public void parseSEND() {
	}

	public void parseFTLP() {
		vehicleIdx = bb.get();
		lapTime = bb.getFloat();
	}

	public void parseRTMT() {
		vehicleIdx = bb.get();
	}

	public void parseDRSE() {
	}

	public void parseDRSD() {
	}

	public void parseTMPT() {
		vehicleIdx = bb.get();
	}

	public void parseCHQF() {
	}

	public void parseRCWN() {
		vehicleIdx = bb.get();
	}

	public void parsePENA() {
		penaltyType = bb.get();
		infringementType = bb.get();
		vehicleIdx = bb.get();
		otherVehicleIdx = bb.get();
		time = bb.get();
		lapNum = bb.get();
		placesGained = bb.get();
	}

	public void parseSPTP() {
		vehicleIdx = bb.get();
		speed = bb.getFloat();
		isOverallFastestInSession = bb.get();
		isDriverFastestInSession = bb.get();
		fastestVehicleIdxInSession = bb.get();
		fastestSpeedInSession = bb.getFloat();
	}

	public void parseSTLG() {
		numLights = bb.get();
	}

	public void parseLGOT() {
		numLights = bb.get();
	}

	public void parseDTSV() {
		vehicleIdx = bb.get();
	}

	public void parseSGSV() {
		vehicleIdx = bb.get();
	}

	public void parseFLBK() {
		flashbackFrameIdentifier = bb.getInt();
		flashbackSessionTime = bb.getFloat();
	}

	public void parseBUTN() {
		buttonStatus = bb.getInt();
	}

	public void parseOVTK23() {
		overtakingVehicleIdx = bb.get();
		beingOvertakenVehicleIdx = bb.get();
	}
	
	public void parseRDFL23() {
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append("::");
		switch (eventType) {
		case "SSTA":
			// Sent when the session starts
			break;
		case "SEND":
			// Sent when the session ends
			break;
		case "FTLP":
			// When a driver achieves the fastest lap
			sb.append(Byte.toUnsignedInt((byte) vehicleIdx));
			sb.append("::");
			sb.append(lapTime);
			break;
		case "RTMT":
			// When a driver retires
			sb.append(Byte.toUnsignedInt((byte) vehicleIdx));
			break;
		case "DRSE":
			// Race control have enabled DRS
			break;
		case "DRSD":
			// Race control have disabled DRS
			break;
		case "TMPT":
			// Your team mate has entered the pits
			sb.append(Byte.toUnsignedInt((byte) vehicleIdx));
			break;
		case "CHQF":
			// The chequered flag has been waved
			break;
		case "RCWN":
			// The race winner is announced
			sb.append(Byte.toUnsignedInt((byte) vehicleIdx));
			break;
		case "PENA":
			// A penalty has been issued
			sb.append(PenaltyConstants.TYPE.get(penaltyType));
			sb.append("::");
			sb.append(InfringementConstants.TYPE.get(infringementType));
			sb.append("::");
			sb.append(Byte.toUnsignedInt((byte) vehicleIdx));
			sb.append("::");
			sb.append(Byte.toUnsignedInt((byte) otherVehicleIdx));
			sb.append("::");
			sb.append(Byte.toUnsignedInt((byte) time));
			sb.append("::");
			sb.append(Byte.toUnsignedInt((byte) lapNum));
			sb.append("::");
			sb.append(Byte.toUnsignedInt((byte) placesGained));
			break;
		case "SPTP":
			// Speed trap has been triggered by fastest speed
			sb.append(Byte.toUnsignedInt((byte) vehicleIdx));
			sb.append("::");
			sb.append(speed);
			sb.append("::");
			sb.append(Byte.toUnsignedInt((byte) isOverallFastestInSession));
			sb.append("::");
			sb.append(Byte.toUnsignedInt((byte) isDriverFastestInSession));
			sb.append("::");
			sb.append(Byte.toUnsignedInt((byte) fastestVehicleIdxInSession));
			sb.append("::");
			sb.append(fastestSpeedInSession);
			break;
		case "STLG":
			// Start lights – number shown
			sb.append(Byte.toUnsignedInt((byte) numLights));
			break;
		case "LGOT":
			// Lights out
			sb.append(Byte.toUnsignedInt((byte) numLights));
			break;
		case "DTSV":
			// Drive through penalty served
			sb.append(Byte.toUnsignedInt((byte) vehicleIdx));
			break;
		case "SGSV":
			// Stop go penalty served
			sb.append(Byte.toUnsignedInt((byte) vehicleIdx));
			break;
		case "FLBK":
			// Flashback activated
			sb.append(flashbackFrameIdentifier);
			sb.append("::");
			sb.append(flashbackSessionTime);
			break;
		case "BUTN":
			// Button status changed
			sb.append(buttonStatus);
			break;
		case "RDFL":
			// Red flag shown
			break;
		case "OVTK":
			// Overtake occured
			sb.append(overtakingVehicleIdx);
			sb.append("::");
			sb.append(beingOvertakenVehicleIdx);
			break;
		default:
			break;
		}

		return sb.toString();
	}
}
