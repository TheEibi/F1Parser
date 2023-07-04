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

	public void initV22(PacketHeader argPacketHeader, ByteBuffer argBb) {
		packetHeader = argPacketHeader;
		carStatusDataList = new ArrayList<>();

		for (int i = 0; i < 22; i++) {
			CarStatusData csd = new CarStatusData();
			csd.initV22(argBb);
			carStatusDataList.add(csd);
		}
	}
	
	@Override
	public void initV23(PacketHeader argPacketHeader, ByteBuffer argBb) {
		packetHeader = argPacketHeader;
		carStatusDataList = new ArrayList<>();

		for (int i = 0; i < 22; i++) {
			CarStatusData csd = new CarStatusData();
			csd.initV23(argBb);
			carStatusDataList.add(csd);
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
