/**
 * 
 */
package formula.packets;

import java.io.Serializable;
import java.nio.ByteBuffer;

/**
 * @author reinh
 *
 */
public class CarMotionData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4481395980560126825L;
	private float worldPosX;
	private float worldPosY;
	private float worldPosZ;
	private float worldVelocityX;
	private float worldVelocityY;
	private float worldVelocityZ;
	private short worldForwardDirX;
	private short worldForwardDirY;
	private short worldForwardDirZ;
	private short worldRightDirX;
	private short worldRightDirY;
	private short worldRightDirZ;
	private float gForceLateral;
	private float gForceLongitudinal;
	private float gForceVertical;
	private float yaw;
	private float pitch;
	private float roll;

	public void initV22(ByteBuffer argBb) {
		worldPosX = argBb.getFloat();
		worldPosY = argBb.getFloat();
		worldPosZ = argBb.getFloat();
		worldVelocityX = argBb.getFloat();
		worldVelocityY = argBb.getFloat();
		worldVelocityZ = argBb.getFloat();
		worldForwardDirX = argBb.getShort();
		worldForwardDirY = argBb.getShort();
		worldForwardDirZ = argBb.getShort();
		worldRightDirX = argBb.getShort();
		worldRightDirY = argBb.getShort();
		worldRightDirZ = argBb.getShort();
		gForceLateral = argBb.getFloat();
		gForceLongitudinal = argBb.getFloat();
		gForceVertical = argBb.getFloat();
		yaw = argBb.getFloat();
		pitch = argBb.getFloat();
		roll = argBb.getFloat();
	}

	public float getWorldPosX() {
		return worldPosX;
	}

	public float getWorldPosY() {
		return worldPosY;
	}

	public float getWorldPosZ() {
		return worldPosZ;
	}

	public float getWorldVelocityX() {
		return worldVelocityX;
	}

	public float getWorldVelocityY() {
		return worldVelocityY;
	}

	public float getWorldVelocityZ() {
		return worldVelocityZ;
	}

	public short getWorldForwardDirX() {
		return worldForwardDirX;
	}

	public short getWorldForwardDirY() {
		return worldForwardDirY;
	}

	public short getWorldForwardDirZ() {
		return worldForwardDirZ;
	}

	public short getWorldRightDirX() {
		return worldRightDirX;
	}

	public short getWorldRightDirY() {
		return worldRightDirY;
	}

	public short getWorldRightDirZ() {
		return worldRightDirZ;
	}

	public float getgForceLateral() {
		return gForceLateral;
	}

	public float getgForceLongitudinal() {
		return gForceLongitudinal;
	}

	public float getgForceVertical() {
		return gForceVertical;
	}

	public float getYaw() {
		return yaw;
	}

	public float getPitch() {
		return pitch;
	}

	public float getRoll() {
		return roll;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append("::");
		sb.append(getWorldPosX());
		sb.append("::");
		sb.append(getWorldPosY());
		sb.append("::");
		sb.append(getWorldPosZ());
		sb.append("::");
		sb.append(getWorldVelocityX());
		sb.append("::");
		sb.append(getWorldVelocityY());
		sb.append("::");
		sb.append(getWorldVelocityZ());
		sb.append("::");
		sb.append(getWorldForwardDirX());
		sb.append("::");
		sb.append(getWorldForwardDirY());
		sb.append("::");
		sb.append(getWorldForwardDirZ());
		sb.append("::");
		sb.append(getWorldRightDirX());
		sb.append("::");
		sb.append(getWorldRightDirY());
		sb.append("::");
		sb.append(getWorldRightDirZ());
		sb.append("::");
		sb.append(getgForceLateral());
		sb.append("::");
		sb.append(getgForceLongitudinal());
		sb.append("::");
		sb.append(getgForceVertical());
		sb.append("::");
		sb.append(getYaw());
		sb.append("::");
		sb.append(getPitch());
		sb.append("::");
		sb.append(getRoll());

		return sb.toString();
	}

}
