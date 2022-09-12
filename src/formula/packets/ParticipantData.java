/**
 * 
 */
package formula.packets;

import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

import formula.constants.NationalityConstants;
import formula.constants.TeamConstants;

/**
 * @author reinh
 *
 */
public class ParticipantData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6361500316293240473L;

	private short aiControlled;
	private short driverId;
	private short networkId;
	private short teamId;
	private short myTeam;
	private short raceNumber;
	private short nationality;
	private byte[] name = new byte[48];
	private short yourTelemetry;

	public ParticipantData(ByteBuffer argBb) {
		aiControlled = argBb.get();
		driverId = argBb.get();
		networkId = argBb.get();
		teamId = argBb.get();
		myTeam = argBb.get();
		raceNumber = argBb.get();
		nationality = argBb.get();
		for (int i = 0; i < 48; i++) {
			name[i] = argBb.get();
		}
		yourTelemetry = argBb.get();
	}

	public short getAiControlled() {
		return aiControlled;
	}

	public short getDriverId() {
		return driverId;
	}

	public short getNetworkId() {
		return networkId;
	}

	public short getTeamId() {
		return teamId;
	}

	public short getMyTeam() {
		return myTeam;
	}

	public short getRaceNumber() {
		return raceNumber;
	}

	public short getNationality() {
		return nationality;
	}

	public byte[] getName() {
		return name;
	}

	public short getYourTelemetry() {
		return yourTelemetry;
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
		sb.append(Byte.toUnsignedInt((byte) getDriverId()));
		sb.append("::");
		sb.append(Byte.toUnsignedInt((byte) getNetworkId()));
		sb.append("::");
		sb.append(TeamConstants.TEAM_ID.get(getTeamId()));
		sb.append("::");
		sb.append(Byte.toUnsignedInt((byte) getMyTeam()));
		sb.append("::");
		sb.append(Byte.toUnsignedInt((byte) getRaceNumber()));
		sb.append("::");
		sb.append(NationalityConstants.NATIONALITY.get(getNationality()));
		sb.append("::");
		sb.append(getNameAsString());
		sb.append("::");
		sb.append(Byte.toUnsignedInt((byte) getYourTelemetry()));
		return sb.toString();
	}

}
