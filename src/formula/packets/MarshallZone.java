package formula.packets;

import java.io.Serializable;
import java.nio.ByteBuffer;

import formula.constants.SessionConstants;

public class MarshallZone implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -385268949882169918L;
	private float zoneStart;
	private short zoneFlag;

	public MarshallZone(ByteBuffer argBb) {
		zoneStart = argBb.getFloat();
		zoneFlag = argBb.get();
	}

	public short getZoneFlag() {
		return zoneFlag;
	}

	public float getZoneStart() {
		return zoneStart;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append("::");
		sb.append(getZoneStart());
		sb.append("::");
		sb.append(SessionConstants.MARSHALL_ZONE_FLAG.get(getZoneFlag()));
		return sb.toString();
	}
}