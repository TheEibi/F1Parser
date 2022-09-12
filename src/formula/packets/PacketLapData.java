/**
 * 
 */
package formula.packets;

import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * @author reinh
 *
 */
public class PacketLapData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3553355621990177829L;
	private PacketHeader header;
	private List<LapData> lapDataList;
	private short timeTrialPBCarIdx;
	private short timeTrialRivalCarIdx;

	public PacketLapData(PacketHeader argHeader, ByteBuffer argBb) {
		header = argHeader;
		lapDataList = new ArrayList<>();

		for (int i = 0; i < 22; i++) {
			lapDataList.add(new LapData(argBb));
		}
		timeTrialPBCarIdx = argBb.get();
		timeTrialRivalCarIdx = argBb.get();
	}

	public PacketHeader getHeader() {
		return header;
	}

	public List<LapData> getLapDataList() {
		return lapDataList;
	}

	public short getTimeTrialPBCarIdx() {
		return timeTrialPBCarIdx;
	}

	public short getTimeTrialRivalCarIdx() {
		return timeTrialRivalCarIdx;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getHeader());
		sb.append("::");
		sb.append(getClass().getSimpleName());
		sb.append("::");
		for (LapData lapData : lapDataList) {
			sb.append(lapData);
		}
		sb.append("::");
		sb.append(Byte.toUnsignedInt((byte) getTimeTrialPBCarIdx()));
		sb.append("::");
		sb.append(Byte.toUnsignedInt((byte) getTimeTrialRivalCarIdx()));
		return sb.toString();

	}

}
