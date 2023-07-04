package formula.packets;

import java.io.Serializable;
import java.nio.ByteBuffer;

import formula.constants.LapDataConstants;
import formula.constants.TyreConstants;

public class FinalClassificationData  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3442070701096590389L;
	private short position;
	private short numLaps;
	private short gridPosition;
	private short points;
	private short numPitStops;
	private short resultStatus;
	private int bestLapTimeinMS;
	private double totalRaceTime;
	private short penaltiesTime;
	private short numPenalties;
	private short numTyreStints;
	private short[] tyreStintsActual = new short[8];
	private short[] tyreStintsVisual = new short[8];
	private short[] tyreStintsEndLaps = new short[8];

	public void initV22(ByteBuffer argBb) {
		position = argBb.get();
		numLaps = argBb.get();
		gridPosition = argBb.get();
		points = argBb.get();
		numPitStops = argBb.get();
		resultStatus = argBb.get();
		bestLapTimeinMS = argBb.getInt();
		totalRaceTime = argBb.getDouble();
		penaltiesTime = argBb.get();
		numPenalties = argBb.get();
		numTyreStints = argBb.get();
		tyreStintsActual[0] = argBb.get();
		tyreStintsActual[1] = argBb.get();
		tyreStintsActual[2] = argBb.get();
		tyreStintsActual[3] = argBb.get();
		tyreStintsActual[4] = argBb.get();
		tyreStintsActual[5] = argBb.get();
		tyreStintsActual[6] = argBb.get();
		tyreStintsActual[7] = argBb.get();

		tyreStintsVisual[0] = argBb.get();
		tyreStintsVisual[1] = argBb.get();
		tyreStintsVisual[2] = argBb.get();
		tyreStintsVisual[3] = argBb.get();
		tyreStintsVisual[4] = argBb.get();
		tyreStintsVisual[5] = argBb.get();
		tyreStintsVisual[6] = argBb.get();
		tyreStintsVisual[7] = argBb.get();

		tyreStintsEndLaps[0] = argBb.get();
		tyreStintsEndLaps[1] = argBb.get();
		tyreStintsEndLaps[2] = argBb.get();
		tyreStintsEndLaps[3] = argBb.get();
		tyreStintsEndLaps[4] = argBb.get();
		tyreStintsEndLaps[5] = argBb.get();
		tyreStintsEndLaps[6] = argBb.get();
		tyreStintsEndLaps[7] = argBb.get();

	}

	public short getPosition() {
		return position;
	}

	public short getNumLaps() {
		return numLaps;
	}

	public short getGridPosition() {
		return gridPosition;
	}

	public short getPoints() {
		return points;
	}

	public short getNumPitStops() {
		return numPitStops;
	}

	public short getResultStatus() {
		return resultStatus;
	}

	public int getBestLapTimeinMS() {
		return bestLapTimeinMS;
	}

	public double getTotalRaceTime() {
		return totalRaceTime;
	}

	public short getPenaltiesTime() {
		return penaltiesTime;
	}

	public short getNumPenalties() {
		return numPenalties;
	}

	public short getNumTyreStints() {
		return numTyreStints;
	}

	public short[] getTyreStintsActual() {
		return tyreStintsActual;
	}

	public short[] getTyreStintsVisual() {
		return tyreStintsVisual;
	}

	public short[] getTyreStintsEndLaps() {
		return tyreStintsEndLaps;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append("::");
		sb.append(Byte.toUnsignedInt((byte) getPosition()));
		sb.append("::");
		sb.append(Byte.toUnsignedInt((byte) getNumLaps()));
		sb.append("::");
		sb.append(Byte.toUnsignedInt((byte) getGridPosition()));
		sb.append("::");
		sb.append(Byte.toUnsignedInt((byte) getNumPitStops()));
		sb.append("::");
		sb.append(LapDataConstants.RESULT_STATUS.get(getResultStatus()));
		sb.append("::");
		sb.append(Long.toUnsignedString(getBestLapTimeinMS()));
		sb.append("::");
		sb.append(getTotalRaceTime());
		sb.append("::");
		sb.append(Byte.toUnsignedInt((byte) getPenaltiesTime()));
		sb.append("::");
		sb.append(Byte.toUnsignedInt((byte) getNumPenalties()));
		sb.append("::");
		sb.append(Byte.toUnsignedInt((byte) getNumTyreStints()));
		sb.append("::");
		for (int i = 0; i < 8; i++) {
			sb.append(TyreConstants.ACTUAL_COMPOUND.get(getTyreStintsActual()[i]));
			sb.append(",");
		}
		sb.append("::");
		for (int i = 0; i < 8; i++) {
			sb.append(TyreConstants.VISUAL_COMPOUND.get(getTyreStintsVisual()[i]));
			sb.append(",");
		}
		sb.append("::");
		for (int i = 0; i < 8; i++) {
			sb.append(Byte.toUnsignedInt((byte)  getTyreStintsEndLaps()[i]));
			sb.append(",");
		}
		return sb.toString();
	}
}
