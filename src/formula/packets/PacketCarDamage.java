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
public class PacketCarDamage implements Serializable {

	private static final long serialVersionUID = -1080685132493822981L;

	private PacketHeader header;
	private List<CarDamageData> carDamageDataList;

	public PacketCarDamage(PacketHeader argHeader, ByteBuffer argBb) {
		header = argHeader;
		carDamageDataList = new ArrayList<>();

		for (int i = 0; i < 22; i++) {
			carDamageDataList.add(new CarDamageData(argBb));
		}

	}

	public PacketHeader getHeader() {
		return header;
	}

	public List<CarDamageData> getCarDamageDataList() {
		return carDamageDataList;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getHeader());
		sb.append("::");
		sb.append(getClass().getSimpleName());
		sb.append("::");
		for (CarDamageData carDamageData : carDamageDataList) {
			sb.append(carDamageData);
		}

		return sb.toString();

	}

}
