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
public class PacketCarStatus implements Serializable, IF1Packet {

	private static final long serialVersionUID = -1080685132493822981L;

	private PacketHeader packetHeader;
	private List<CarStatusData> carStatusDataList;

	public PacketCarStatus(PacketHeader argPacketHeader, ByteBuffer argBb) {
		packetHeader = argPacketHeader;
		carStatusDataList = new ArrayList<>();

		for (int i = 0; i < 22; i++) {
			carStatusDataList.add(new CarStatusData(argBb));
		}

	}

	public PacketHeader getPacketHeader() {
		return packetHeader;
	}

	public List<CarStatusData> getCarStatusDataList() {
		return carStatusDataList;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getPacketHeader());
		sb.append("::");
		sb.append(getClass().getSimpleName());
		sb.append("::");
		for (CarStatusData carStatusData : carStatusDataList) {
			sb.append(carStatusData);
		}

		return sb.toString();

	}

}
