/**
 * 
 */
package formula.packets;

import java.io.Serializable;
import java.nio.ByteBuffer;

/**
 * @author reinh
 *
 */
public class PacketEventData implements Serializable, IF1Packet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2786103010736921123L;
	private PacketHeader packetHeader;
	private char[] eventStringCode = new char[4];
	private EventDataDetails eventDataDetails;

	public void initV22(PacketHeader argPacketHeader, ByteBuffer argBb) {
		packetHeader = argPacketHeader;
		eventStringCode[0] = (char) argBb.get();
		eventStringCode[1] = (char) argBb.get();
		eventStringCode[2] = (char) argBb.get();
		eventStringCode[3] = (char) argBb.get();
		
		eventDataDetails = new EventDataDetails();
		eventDataDetails.initV22(argBb, getEventStringCodeAsString());

		switch (getEventStringCodeAsString()) {
		case "SSTA":
			// Sent when the session starts
			eventDataDetails.parseSSTA();
			break;
		case "SEND":
			// Sent when the session ends
			eventDataDetails.parseSEND();
			break;
		case "FTLP":
			// When a driver achieves the fastest lap
			eventDataDetails.parseFTLP();
			break;
		case "RTMT":
			// When a driver retires
			eventDataDetails.parseRTMT();
			break;
		case "DRSE":
			// Race control have enabled DRS
			eventDataDetails.parseDRSE();
			break;
		case "DRSD":
			// Race control have disabled DRS
			eventDataDetails.parseDRSD();
			break;
		case "TMPT":
			// Your team mate has entered the pits
			eventDataDetails.parseTMPT();
			break;
		case "CHQF":
			// The chequered flag has been waved
			eventDataDetails.parseCHQF();
			break;
		case "RCWN":
			// The race winner is announced
			eventDataDetails.parseRCWN();
			break;
		case "PENA":
			// A penalty has been issued
			eventDataDetails.parsePENA();
			break;
		case "SPTP":
			// Speed trap has been triggered by fastest speed
			eventDataDetails.parseSPTP();
			break;
		case "STLG":
			// Start lights – number shown
			eventDataDetails.parseSTLG();
			break;
		case "LGOT":
			// Lights out
			eventDataDetails.parseLGOT();
			break;
		case "DTSV":
			// Drive through penalty served
			eventDataDetails.parseDTSV();
			break;
		case "SGSV":
			// Stop go penalty served
			eventDataDetails.parseSGSV();
			break;
		case "FLBK":
			// Flashback activated
			eventDataDetails.parseFLBK();
			break;
		case "BUTN":
			// Button status changed
			eventDataDetails.parseBUTN();
			break;
		default:
			break;
		}
	}
	
	@Override
	public void initV23(PacketHeader argPacketHeader, ByteBuffer argBb) {
		packetHeader = argPacketHeader;
		eventStringCode[0] = (char) argBb.get();
		eventStringCode[1] = (char) argBb.get();
		eventStringCode[2] = (char) argBb.get();
		eventStringCode[3] = (char) argBb.get();
		
		eventDataDetails = new EventDataDetails();
		eventDataDetails.initV22(argBb, getEventStringCodeAsString());

		switch (getEventStringCodeAsString()) {
		case "SSTA":
			// Sent when the session starts
			eventDataDetails.parseSSTA();
			break;
		case "SEND":
			// Sent when the session ends
			eventDataDetails.parseSEND();
			break;
		case "FTLP":
			// When a driver achieves the fastest lap
			eventDataDetails.parseFTLP();
			break;
		case "RTMT":
			// When a driver retires
			eventDataDetails.parseRTMT();
			break;
		case "DRSE":
			// Race control have enabled DRS
			eventDataDetails.parseDRSE();
			break;
		case "DRSD":
			// Race control have disabled DRS
			eventDataDetails.parseDRSD();
			break;
		case "TMPT":
			// Your team mate has entered the pits
			eventDataDetails.parseTMPT();
			break;
		case "CHQF":
			// The chequered flag has been waved
			eventDataDetails.parseCHQF();
			break;
		case "RCWN":
			// The race winner is announced
			eventDataDetails.parseRCWN();
			break;
		case "PENA":
			// A penalty has been issued
			eventDataDetails.parsePENA();
			break;
		case "SPTP":
			// Speed trap has been triggered by fastest speed
			eventDataDetails.parseSPTP();
			break;
		case "STLG":
			// Start lights – number shown
			eventDataDetails.parseSTLG();
			break;
		case "LGOT":
			// Lights out
			eventDataDetails.parseLGOT();
			break;
		case "DTSV":
			// Drive through penalty served
			eventDataDetails.parseDTSV();
			break;
		case "SGSV":
			// Stop go penalty served
			eventDataDetails.parseSGSV();
			break;
		case "FLBK":
			// Flashback activated
			eventDataDetails.parseFLBK();
			break;
		case "BUTN":
			// Button status changed
			eventDataDetails.parseBUTN();
			break;
		case "RDFL":
			// Red flag shown
			eventDataDetails.parseRDFL23();
			break;
		case "OVTK":
			// Button status changed
			eventDataDetails.parseOVTK23();
			break;
		default:
			break;
		}
	}

	public PacketHeader getPacketHeader() {
		return packetHeader;
	}

	public char[] getEventStringCode() {
		return eventStringCode;
	}

	public String getEventStringCodeAsString() {
		return String.valueOf(eventStringCode);
	}

	public EventDataDetails getEventDataDetails() {
		return eventDataDetails;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getPacketHeader());
		sb.append("::");
		sb.append(getClass().getSimpleName());
		sb.append("::");
		sb.append(getEventStringCode());
		sb.append("::");
		sb.append(getEventDataDetails());
		return sb.toString();
	}

}
