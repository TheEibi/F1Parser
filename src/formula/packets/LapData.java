/**
 * 
 */
package formula.packets;

import java.io.Serializable;
import java.nio.ByteBuffer;

import formula.constants.LapDataConstants;

/**
 * @author reinh
 *
 */
public class LapData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6361500316293240473L;
	private int lastLapTimeInMS;
	private int currentLapTimeInMS;
	private short sector1TimeInMS;
	private short sector2TimeInMS;
	private float lapDistance;
	private float totalDistance;
	private float safetyCarDelta;
	private short carPosition;
	private short currentLapNum;
	private short pitStatus;
	private short numPitStops;
	private short sector;
	private short currentLapInvalid;
	private short penalties;
	private short warnings;
	private short numUnservedDriveThroughPens;
	private short numUnservedStopGoPens;
	private short gridPosition;
	private short driverStatus;
	private short resultStatus;
	private short pitLaneTimerActive;
	private short pitLaneTimeInLaneInMS;
	private short pitStopTimerInMS;
	private short pitStopShouldServePen;

	public LapData(ByteBuffer argBb) {
		lastLapTimeInMS = argBb.getInt();
		currentLapTimeInMS = argBb.getInt();
		sector1TimeInMS = argBb.getShort();
		sector2TimeInMS = argBb.getShort();
		lapDistance = argBb.getFloat();
		totalDistance = argBb.getFloat();
		safetyCarDelta = argBb.getFloat();
		carPosition = argBb.get();
		currentLapNum = argBb.get();
		pitStatus = argBb.get();
		numPitStops = argBb.get();
		sector = argBb.get();
		currentLapInvalid = argBb.get();
		penalties = argBb.get();
		warnings = argBb.get();
		numUnservedDriveThroughPens = argBb.get();
		numUnservedStopGoPens = argBb.get();
		gridPosition = argBb.get();
		driverStatus = argBb.get();
		resultStatus = argBb.get();
		pitLaneTimerActive = argBb.get();
		pitLaneTimeInLaneInMS = argBb.getShort();
		pitStopTimerInMS = argBb.getShort();
		pitStopShouldServePen = argBb.get();
	}

	public int getLastLapTimeInMS() {
		return lastLapTimeInMS;
	}

	public int getCurrentLapTimeInMS() {
		return currentLapTimeInMS;
	}

	public short getSector1TimeInMS() {
		return sector1TimeInMS;
	}

	public short getSector2TimeInMS() {
		return sector2TimeInMS;
	}

	public float getLapDistance() {
		return lapDistance;
	}

	public float getTotalDistance() {
		return totalDistance;
	}

	public float getSafetyCarDelta() {
		return safetyCarDelta;
	}

	public short getCarPosition() {
		return carPosition;
	}

	public short getCurrentLapNum() {
		return currentLapNum;
	}

	public short getPitStatus() {
		return pitStatus;
	}

	public short getNumPitStops() {
		return numPitStops;
	}

	public short getSector() {
		return sector;
	}

	public short getCurrentLapInvalid() {
		return currentLapInvalid;
	}

	public short getPenalties() {
		return penalties;
	}

	public short getWarnings() {
		return warnings;
	}

	public short getNumUnservedDriveThroughPens() {
		return numUnservedDriveThroughPens;
	}

	public short getNumUnservedStopGoPens() {
		return numUnservedStopGoPens;
	}

	public short getGridPosition() {
		return gridPosition;
	}

	public short getDriverStatus() {
		return driverStatus;
	}

	public short getResultStatus() {
		return resultStatus;
	}

	public short getPitLaneTimerActive() {
		return pitLaneTimerActive;
	}

	public short getPitLaneTimeInLaneInMS() {
		return pitLaneTimeInLaneInMS;
	}

	public short getPitStopTimerInMS() {
		return pitStopTimerInMS;
	}

	public short getPitStopShouldServePen() {
		return pitStopShouldServePen;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append("::");
		sb.append(String.format("%05d", getLastLapTimeInMS()));
		sb.append("::");
		sb.append(String.format("%05d", getCurrentLapTimeInMS()));
		sb.append("::");
		sb.append(Long.toUnsignedString(getSector1TimeInMS()));
		sb.append("::");
		sb.append(getSector2TimeInMS());
		sb.append("::");
		sb.append(String.format("%05f", getLapDistance()));
		sb.append("::");
		sb.append(String.format("%05f", getTotalDistance()));
		sb.append("::");
		sb.append(getSafetyCarDelta());
		sb.append("::");
		sb.append(getCarPosition());
		sb.append("::");
		sb.append(getCurrentLapNum());
		sb.append("::");
		sb.append(LapDataConstants.PIT_STATUS.get(getPitStatus()));
		sb.append("::");
		sb.append(getNumPitStops());
		sb.append("::");
		sb.append(LapDataConstants.SECTOR.get(getSector()));
		sb.append("::");
		sb.append(getCurrentLapInvalid());
		sb.append("::");
		sb.append(getPenalties());
		sb.append("::");
		sb.append(getWarnings());
		sb.append("::");
		sb.append(getNumUnservedDriveThroughPens());
		sb.append("::");
		sb.append(getNumUnservedStopGoPens());
		sb.append("::");
		sb.append(getGridPosition());
		sb.append("::");
		sb.append(LapDataConstants.DRIVER_STATUS.get(getDriverStatus()));
		sb.append("::");
		sb.append(LapDataConstants.RESULT_STATUS.get(getResultStatus()));
		sb.append("::");
		sb.append(LapDataConstants.PIT_LANE_TIMER_ACTIVE.get(getPitLaneTimerActive()));
		sb.append("::");
		sb.append(getPitLaneTimeInLaneInMS());
		sb.append("::");
		sb.append(getPitStopTimerInMS());
		sb.append("::");
		sb.append(getPitStopShouldServePen());
		return sb.toString();
	}

}
