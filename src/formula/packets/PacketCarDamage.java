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

	public void initV22(PacketHeader argPacketHeader, ByteBuffer argBb) {
		packetHeader = argPacketHeader;
		carDamageDataList = new ArrayList<>();

		for (int i = 0; i < 22; i++) {
			CarDamageData cdd = new CarDamageData();
			cdd.initV22(argBb);
			carDamageDataList.add(cdd);
		}

	}
	
	@Override
	public void initV23(PacketHeader argPacketHeader, ByteBuffer argBb) {
		initV22(argPacketHeader, argBb);
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
