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
public class PacketCarDamage implements Serializable, IF1Packet {

	private static final long serialVersionUID = -1080685132493822981L;

	private PacketHeader packetHeader;
	private List<CarDamageData> carDamageDataList;

	public PacketCarDamage(PacketHeader argPacketHeader, ByteBuffer argBb) {
		packetHeader = argPacketHeader;
		carDamageDataList = new ArrayList<>();

		for (int i = 0; i < 22; i++) {
			carDamageDataList.add(new CarDamageData(argBb));
		}

	}

	public PacketHeader getPacketHeader() {
		return packetHeader;
	}

	public List<CarDamageData> getCarDamageDataList() {
		return carDamageDataList;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getPacketHeader());
		sb.append("::");
		sb.append(getClass().getSimpleName());
		sb.append("::");
		for (CarDamageData carDamageData : carDamageDataList) {
			sb.append(carDamageData);
		}

		return sb.toString();

	}

}
