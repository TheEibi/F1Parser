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
public class PacketHeader implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4037041671036447096L;
	private short packetFormat;
	private short gameYear;
	private short gameMajorVersion;
	private short gameMinorVersion;
	private short packetVersion;
	private short packetId;
	private long sessionUID;
	private float sessionTime;
	private int frameIdentifier;
	private int overallFrameIdentifier;
	private short playerCarIndex;
	private short secondaryPlayerCarIndex;
	
	public PacketHeader(short argPacketFormat) {
		packetFormat = argPacketFormat;
	}

	public void initV22(ByteBuffer argBb) {
		packetFormat = argBb.getShort();
		gameMajorVersion = argBb.get();
		gameMinorVersion = argBb.get();
		packetVersion = argBb.get();
		packetId = argBb.get();
		sessionUID = argBb.getLong();
		sessionTime = argBb.getFloat();
		frameIdentifier = argBb.getInt();
		playerCarIndex = argBb.get();
		secondaryPlayerCarIndex = argBb.get();
		
		if (gameMajorVersion == 0) {
			packetId  = -1;
		}
	}
	
	public void initV23(ByteBuffer argBb) {
		packetFormat = argBb.getShort();
		gameYear = argBb.get();
		gameMajorVersion = argBb.get();
		gameMinorVersion = argBb.get();
		packetVersion = argBb.get();
		packetId = argBb.get();
		sessionUID = argBb.getLong();
		sessionTime = argBb.getFloat();
		frameIdentifier = argBb.getInt();
		overallFrameIdentifier = argBb.getInt();
		playerCarIndex = argBb.get();
		secondaryPlayerCarIndex = argBb.get();
		
		if (gameMajorVersion == 0) {
			packetId  = -1;
		}
	}

	public short getPacketFormat() {
		return packetFormat;
	}

	public short getGameYear() {
		return gameYear;
	}
	
	public short getGameMajorVersion() {
		return gameMajorVersion;
	}

	public short getGameMinorVersion() {
		return gameMinorVersion;
	}

	public short getPacketVersion() {
		return packetVersion;
	}

	public short getPacketId() {
		return packetId;
	}

	public long getSessionUID() {

		return sessionUID;
	}

	public float getSessionTime() {
		return sessionTime;
	}

	public int getFrameIdentifier() {
		return frameIdentifier;
	}

	public int getOverallFrameIdentifier() {
		return overallFrameIdentifier;
	}
	
	public short getPlayerCarIndex() {
		return playerCarIndex;
	}

	public short getSecondaryPlayerCarIndex() {
		return secondaryPlayerCarIndex;
	}

	
	

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append("::");
		sb.append(getPacketFormat());
		sb.append("::");
		sb.append(getGameYear());
		sb.append("::");
		sb.append(getGameMajorVersion());
		sb.append(".");
		sb.append(String.format("%02d", getGameMinorVersion()));
		sb.append("::");
		sb.append(getPacketVersion());
		sb.append("::");
		sb.append(getPacketId());
		sb.append("::");
		sb.append(Long.toUnsignedString(getSessionUID()));
		sb.append("::");
		sb.append(String.format("%05f", getSessionTime()));
		sb.append("::");
		sb.append(getFrameIdentifier());
		sb.append("::");
		sb.append(getOverallFrameIdentifier());
		sb.append("::");
		sb.append(Byte.toUnsignedInt((byte) getPlayerCarIndex()));
		sb.append("::");
		sb.append(Byte.toUnsignedInt((byte) getSecondaryPlayerCarIndex()));
		return sb.toString();
	}

}
