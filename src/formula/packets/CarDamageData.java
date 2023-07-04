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
public class CarDamageData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6605383906858727349L;
	private float[] tyresWear = new float[4];
	private short[] tyresDamage = new short[4];
	private short[] brakesDamage = new short[4];
	private short frontLeftWingDamage;
	private short frontRightWingDamage;
	private short rearWingDamage;
	private short floorDamage;
	private short diffuserDamage;
	private short sidepodDamage;
	private short drsFault;
	private short ersFault;
	private short gearBoxDamage;
	private short engineDamage;
	private short engineMGUHWear;
	private short engineESWear;
	private short engineCEWear;
	private short engineICEWear;
	private short engineMGUKWear;
	private short engineTCWear;
	private short engineBlown;
	private short engineSeized;
	
	public void initV22(ByteBuffer argBb) {
		for (int i = 0; i < 4; i++) {
			tyresWear[i] = argBb.getFloat();
		}
		for (int i = 0; i < 4; i++) {
			tyresDamage[i] = argBb.get();
		}
		for (int i = 0; i < 4; i++) {
			brakesDamage[i] = argBb.get();
		}
		frontLeftWingDamage = argBb.get();
		frontRightWingDamage = argBb.get();
		rearWingDamage = argBb.get();
		floorDamage = argBb.get();
		diffuserDamage = argBb.get();
		sidepodDamage = argBb.get();
		drsFault = argBb.get();
		ersFault = argBb.get();
		gearBoxDamage = argBb.get();
		engineDamage = argBb.get();
		engineMGUHWear = argBb.get();
		engineESWear = argBb.get();
		engineCEWear = argBb.get();
		engineICEWear = argBb.get();
		engineMGUKWear = argBb.get();
		engineTCWear = argBb.get();
		engineBlown = argBb.get();
		engineSeized = argBb.get();		
	}

	public float[] getTyresWear() {
		return tyresWear;
	}

	public short[] getTyresDamage() {
		return tyresDamage;
	}

	public short[] getBrakesDamage() {
		return brakesDamage;
	}

	public short getFrontLeftWingDamage() {
		return frontLeftWingDamage;
	}

	public short getFrontRightWingDamage() {
		return frontRightWingDamage;
	}

	public short getRearWingDamage() {
		return rearWingDamage;
	}

	public short getFloorDamage() {
		return floorDamage;
	}

	public short getDiffuserDamage() {
		return diffuserDamage;
	}

	public short getSidepodDamage() {
		return sidepodDamage;
	}

	public short getDrsFault() {
		return drsFault;
	}

	public short getErsFault() {
		return ersFault;
	}

	public short getGearBoxDamage() {
		return gearBoxDamage;
	}

	public short getEngineDamage() {
		return engineDamage;
	}

	public short getEngineMGUHWear() {
		return engineMGUHWear;
	}

	public short getEngineESWear() {
		return engineESWear;
	}

	public short getEngineCEWear() {
		return engineCEWear;
	}

	public short getEngineICEWear() {
		return engineICEWear;
	}

	public short getEngineMGUKWear() {
		return engineMGUKWear;
	}

	public short getEngineTCWear() {
		return engineTCWear;
	}

	public short getEngineBlown() {
		return engineBlown;
	}

	public short getEngineSeized() {
		return engineSeized;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append("::");
		for (int i = 0; i < 4; i++) {
			sb.append(getTyresWear()[i]);
			sb.append(",");
		}
		sb.append("::");
		for (int i = 0; i < 4; i++) {
			sb.append(Byte.toUnsignedInt((byte) getTyresDamage()[i]));
			sb.append(",");
		}
		sb.append("::");
		for (int i = 0; i < 4; i++) {
			sb.append(Byte.toUnsignedInt((byte) getBrakesDamage()[i]));
			sb.append(",");
		}
		sb.append("::");
		sb.append(Byte.toUnsignedInt((byte) getFrontLeftWingDamage()));
		sb.append("::");
		sb.append(Byte.toUnsignedInt((byte) getFrontRightWingDamage()));
		sb.append("::");
		sb.append(Byte.toUnsignedInt((byte) getRearWingDamage()));
		sb.append("::");
		sb.append(Byte.toUnsignedInt((byte) getFloorDamage()));
		sb.append("::");
		sb.append(Byte.toUnsignedInt((byte) getDiffuserDamage()));
		sb.append("::");
		sb.append(Byte.toUnsignedInt((byte) getSidepodDamage()));
		sb.append("::");
		sb.append(Byte.toUnsignedInt((byte) getDrsFault()));
		sb.append("::");
		sb.append(Byte.toUnsignedInt((byte) getErsFault()));
		sb.append("::");
		sb.append(Byte.toUnsignedInt((byte) getGearBoxDamage()));
		sb.append("::");
		sb.append(Byte.toUnsignedInt((byte) getEngineDamage()));
		sb.append("::");
		sb.append(Byte.toUnsignedInt((byte) getEngineMGUHWear()));
		sb.append("::");
		sb.append(Byte.toUnsignedInt((byte) getEngineESWear()));
		sb.append("::");
		sb.append(Byte.toUnsignedInt((byte) getEngineCEWear()));
		sb.append("::");
		sb.append(Byte.toUnsignedInt((byte) getEngineICEWear()));
		sb.append("::");
		sb.append(Byte.toUnsignedInt((byte) getEngineMGUKWear()));
		sb.append("::");
		sb.append(Byte.toUnsignedInt((byte) getEngineTCWear()));
		sb.append("::");
		sb.append(Byte.toUnsignedInt((byte) getEngineBlown()));
		sb.append("::");
		sb.append(Byte.toUnsignedInt((byte) getEngineSeized()));
		return sb.toString();
	}

}
