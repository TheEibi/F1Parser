/**
 * 
 */
package formula.packets;

import java.io.Serializable;
import java.nio.ByteBuffer;

import formula.constants.TyreConstants;

/**
 * @author reinh
 *
 */
public class CarStatusData implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7330973709467211323L;
	private short tractionControl;
	private short antiLockBrakes;
	private short fuelMix;
	private short frontBrakeBias;
	private short pitLimitStatus;
	private float fuelInTank;
	private float fuelCapacity;
	private float fuelRemainingLaps;
	private short maxRPM;
	private short idleRPM;
	private short maxGears;
	private short drsAllowed;
	private short drsActivationDistance;
	private short actualTyreCompound;
	private short visualTyreCompound;
	private short tyresAgeLaps;
	private short vehicleFiaFlags;
	private float enginePowerICE;
	private float enginePowerMGUK;
	private float ersStoreEnergy;
	private short ersDeployMode;
	private float ersHarvestedThisLapMGUK;
	private float ersHarvestedThisLapMGUH;
	private float ersDeployedThisLap;
	private short networkPaused;

	public void initV22(ByteBuffer argBb) {
		tractionControl = argBb.get();
		antiLockBrakes = argBb.get();
		fuelMix = argBb.get();
		frontBrakeBias = argBb.get();
		pitLimitStatus = argBb.get();
		fuelInTank = argBb.getFloat();
		fuelCapacity = argBb.getFloat();
		fuelRemainingLaps = argBb.getFloat();
		maxRPM = argBb.getShort();
		idleRPM = argBb.getShort();
		maxGears = argBb.get();
		drsAllowed = argBb.get();
		drsActivationDistance = argBb.getShort();
		actualTyreCompound = argBb.get();
		visualTyreCompound = argBb.get();
		tyresAgeLaps = argBb.get();
		vehicleFiaFlags = argBb.get();
		ersStoreEnergy = argBb.getFloat();
		ersDeployMode = argBb.get();
		ersHarvestedThisLapMGUK = argBb.getFloat();
		ersHarvestedThisLapMGUH = argBb.getFloat();
		ersDeployedThisLap = argBb.getFloat();
		networkPaused = argBb.get();
	}
	
	public void initV23(ByteBuffer argBb) {
		tractionControl = argBb.get();
		antiLockBrakes = argBb.get();
		fuelMix = argBb.get();
		frontBrakeBias = argBb.get();
		pitLimitStatus = argBb.get();
		fuelInTank = argBb.getFloat();
		fuelCapacity = argBb.getFloat();
		fuelRemainingLaps = argBb.getFloat();
		maxRPM = argBb.getShort();
		idleRPM = argBb.getShort();
		maxGears = argBb.get();
		drsAllowed = argBb.get();
		drsActivationDistance = argBb.getShort();
		actualTyreCompound = argBb.get();
		visualTyreCompound = argBb.get();
		tyresAgeLaps = argBb.get();
		vehicleFiaFlags = argBb.get();
		enginePowerICE = argBb.getFloat();
		enginePowerMGUK = argBb.getFloat();
		ersStoreEnergy = argBb.getFloat();
		ersDeployMode = argBb.get();
		ersHarvestedThisLapMGUK = argBb.getFloat();
		ersHarvestedThisLapMGUH = argBb.getFloat();
		ersDeployedThisLap = argBb.getFloat();
		networkPaused = argBb.get();
	}
	

	public short getTractionControl() {
		return tractionControl;
	}

	public short getAntiLockBrakes() {
		return antiLockBrakes;
	}

	public short getFuelMix() {
		return fuelMix;
	}

	public short getFrontBrakeBias() {
		return frontBrakeBias;
	}

	public short getPitLimitStatus() {
		return pitLimitStatus;
	}

	public float getFuelInTank() {
		return fuelInTank;
	}

	public float getFuelCapacity() {
		return fuelCapacity;
	}

	public float getFuelRemainingLaps() {
		return fuelRemainingLaps;
	}

	public short getMaxRPM() {
		return maxRPM;
	}

	public short getIdleRPM() {
		return idleRPM;
	}

	public short getMaxGears() {
		return maxGears;
	}

	public short getDrsAllowed() {
		return drsAllowed;
	}

	public short getDrsActivationDistance() {
		return drsActivationDistance;
	}

	public short getActualTyreCompound() {
		return actualTyreCompound;
	}

	public short getVisualTyreCompound() {
		return visualTyreCompound;
	}

	public short getTyresAgeLaps() {
		return tyresAgeLaps;
	}

	public short getVehicleFiaFlags() {
		return vehicleFiaFlags;
	}

	public float getErsStoreEnergy() {
		return ersStoreEnergy;
	}

	public short getErsDeployMode() {
		return ersDeployMode;
	}

	public float getErsHarvestedThisLapMGUK() {
		return ersHarvestedThisLapMGUK;
	}

	public float getErsHarvestedThisLapMGUH() {
		return ersHarvestedThisLapMGUH;
	}

	public float getErsDeployedThisLap() {
		return ersDeployedThisLap;
	}

	public short getNetworkPaused() {
		return networkPaused;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append("::");
		sb.append(Byte.toUnsignedInt((byte) getTractionControl()));
		sb.append("::");
		sb.append(Byte.toUnsignedInt((byte) getAntiLockBrakes()));
		sb.append("::");
		sb.append(Byte.toUnsignedInt((byte) getFuelMix()));
		sb.append("::");
		sb.append(Byte.toUnsignedInt((byte) getFrontBrakeBias()));
		sb.append("::");
		sb.append(Byte.toUnsignedInt((byte) getPitLimitStatus()));
		sb.append("::");
		sb.append(getFuelInTank());
		sb.append("::");
		sb.append(getFuelCapacity());
		sb.append("::");
		sb.append(getFuelRemainingLaps());
		sb.append("::");
		sb.append(Byte.toUnsignedInt((byte) getMaxRPM()));
		sb.append("::");
		sb.append(Byte.toUnsignedInt((byte) getIdleRPM()));
		sb.append("::");
		sb.append(Byte.toUnsignedInt((byte) getDrsAllowed()));
		sb.append("::");
		sb.append(Byte.toUnsignedInt((byte) getDrsActivationDistance()));
		sb.append("::");
		sb.append(TyreConstants.ACTUAL_COMPOUND.get(getActualTyreCompound()));
		sb.append("::");
		sb.append(TyreConstants.VISUAL_COMPOUND.get(getVisualTyreCompound()));
		sb.append("::");
		sb.append(Byte.toUnsignedInt((byte) getTyresAgeLaps()));
		sb.append("::");
		sb.append(getVehicleFiaFlags());
		sb.append("::");
		sb.append(getErsStoreEnergy());
		sb.append("::");
		sb.append(Byte.toUnsignedInt((byte) getErsDeployMode()));
		sb.append("::");
		sb.append(getErsHarvestedThisLapMGUK());
		sb.append("::");
		sb.append(getErsHarvestedThisLapMGUH());
		sb.append("::");
		sb.append(getErsDeployedThisLap());
		sb.append("::");
		sb.append(Byte.toUnsignedInt((byte) getNetworkPaused()));
		return sb.toString();
	}

}
