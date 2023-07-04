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
public class PacketCarMotion implements Serializable, IF1Packet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2044798212303894353L;
	private PacketHeader packetHeader;
	private List<CarMotionData> carMotionDataList;
	private float[] suspensionPosition = new float[4];
	private float[] suspensionVelocity = new float[4];
	private float[] suspensionAcceleration = new float[4];
	private float[] wheelSpeed = new float[4];
	private float[] wheelSlip = new float[4];
	private float localVelocityX;
	private float localVelocityY;
	private float localVelocityZ;
	private float angularVelocityX;
	private float angularVelocityY;
	private float angularVelocityZ;
	private float angularAccelerationX;
	private float angularAccelerationY;
	private float angularAccelerationZ;
	private float frontWheelsAngle;

	public void initV22(PacketHeader argPacketHeader, ByteBuffer argBb) {
		packetHeader = argPacketHeader;

		carMotionDataList = new ArrayList<>();
		for (int i = 0; i < 22; i++) {
			CarMotionData cmd = new CarMotionData();
			cmd.initV22(argBb);
			carMotionDataList.add(cmd);
		}
		for (int i = 0; i < 4; i++) {
			suspensionPosition[i] = argBb.getFloat();
		}
		for (int i = 0; i < 4; i++) {
			suspensionVelocity[i] = argBb.getFloat();
		}
		for (int i = 0; i < 4; i++) {
			suspensionAcceleration[i] = argBb.getFloat();
		}
		for (int i = 0; i < 4; i++) {
			wheelSpeed[i] = argBb.getFloat();
		}
		for (int i = 0; i < 4; i++) {
			wheelSlip[i] = argBb.getFloat();
		}

		localVelocityX = argBb.getFloat();
		localVelocityY = argBb.getFloat();
		localVelocityZ = argBb.getFloat();
		angularVelocityX = argBb.getFloat();
		angularVelocityY = argBb.getFloat();
		angularVelocityZ = argBb.getFloat();
		angularAccelerationX = argBb.getFloat();
		angularAccelerationY = argBb.getFloat();
		angularAccelerationZ = argBb.getFloat();
		frontWheelsAngle = argBb.getFloat();
	}

	@Override
	public void initV23(PacketHeader argPacketHeader, ByteBuffer argBb) {
		packetHeader = argPacketHeader;

		carMotionDataList = new ArrayList<>();
		for (int i = 0; i < 22; i++) {
			CarMotionData cmd = new CarMotionData();
			cmd.initV22(argBb);
			carMotionDataList.add(cmd);
		}
	}

	public PacketHeader getPacketHeader() {
		return packetHeader;
	}

	public List<CarMotionData> getCarMotionDataList() {
		return carMotionDataList;
	}

	public float[] getSuspensionPosition() {
		return suspensionPosition;
	}

	public float[] getSuspensionVelocity() {
		return suspensionVelocity;
	}

	public float[] getSuspensionAcceleration() {
		return suspensionAcceleration;
	}

	public float[] getWheelSpeed() {
		return wheelSpeed;
	}

	public float[] getWheelSlip() {
		return wheelSlip;
	}

	public float getLocalVelocityX() {
		return localVelocityX;
	}

	public float getLocalVelocityY() {
		return localVelocityY;
	}

	public float getLocalVelocityZ() {
		return localVelocityZ;
	}

	public float getAngularVelocityX() {
		return angularVelocityX;
	}

	public float getAngularVelocityY() {
		return angularVelocityY;
	}

	public float getAngularVelocityZ() {
		return angularVelocityZ;
	}

	public float getAngularAccelerationX() {
		return angularAccelerationX;
	}

	public float getAngularAccelerationY() {
		return angularAccelerationY;
	}

	public float getAngularAccelerationZ() {
		return angularAccelerationZ;
	}

	public float getFrontWheelsAngle() {
		return frontWheelsAngle;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getPacketHeader());
		sb.append("::");
		for (CarMotionData carMotionData : getCarMotionDataList()) {
			sb.append(carMotionData);
		}
		sb.append("::");
		for (int i = 0; i < 4; i++) {
			sb.append(getSuspensionPosition()[i]);
			sb.append(",");
		}
		sb.append("::");
		for (int i = 0; i < 4; i++) {
			sb.append(getSuspensionVelocity()[i]);
			sb.append(",");
		}
		sb.append("::");
		for (int i = 0; i < 4; i++) {
			sb.append(getSuspensionAcceleration()[i]);
			sb.append(",");
		}
		sb.append("::");
		for (int i = 0; i < 4; i++) {
			sb.append(getWheelSpeed()[i]);
			sb.append(",");
		}
		sb.append("::");
		for (int i = 0; i < 4; i++) {
			sb.append(getWheelSlip()[i]);
			sb.append(",");
		}
		sb.append("::");
		sb.append(getLocalVelocityX());
		sb.append("::");
		sb.append(getLocalVelocityY());
		sb.append("::");
		sb.append(getLocalVelocityZ());
		sb.append("::");
		sb.append(getAngularVelocityX());
		sb.append("::");
		sb.append(getAngularVelocityY());
		sb.append("::");
		sb.append(getAngularVelocityZ());
		sb.append("::");
		sb.append(getAngularAccelerationX());
		sb.append("::");
		sb.append(getAngularAccelerationY());
		sb.append("::");
		sb.append(getAngularAccelerationZ());
		sb.append("::");
		sb.append(getFrontWheelsAngle());

		return sb.toString();
	}

}
