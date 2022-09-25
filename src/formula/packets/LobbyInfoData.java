package formula.packets;

import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

import formula.constants.NationalityConstants;
import formula.constants.TeamConstants;

public class LobbyInfoData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6932708260505276366L;
	private short aiControlled;
	private short teamId;
	private short nationality;
	private byte[] name = new byte[48];
	private short carNumber;
	private short readyStatus;

	public LobbyInfoData(ByteBuffer argBb) {
		aiControlled = argBb.get();
		teamId = argBb.get();
		nationality = argBb.get();

		for (int i = 0; i < 48; i++) {
			name[i] = argBb.get();
		}

		carNumber = argBb.get();
		readyStatus = argBb.get();
	}

	public short getAiControlled() {
		return aiControlled;
	}

	public short getTeamId() {
		return teamId;
	}

	public short getNationality() {
		return nationality;
	}

	public byte[] getName() {
		return name;
	}

	public short getCarNumber() {
		return carNumber;
	}

	public short getReadyStatus() {
		return readyStatus;
	}

	public String getNameAsString() {
		return new String(getName(), StandardCharsets.UTF_8).trim();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append("::");
		sb.append(Byte.toUnsignedInt((byte) getAiControlled()));
		sb.append("::");
		sb.append(TeamConstants.TEAM_ID.get(getTeamId()));
		sb.append("::");
		sb.append(NationalityConstants.NATIONALITY.get(getNationality()));
		sb.append("::");
		sb.append(getNameAsString());
		sb.append("::");
		sb.append(getCarNumber());
		sb.append("::");
		sb.append(getReadyStatus());
		return sb.toString();
	}

}
