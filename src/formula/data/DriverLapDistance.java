/**
 * 
 */
package formula.data;

import java.awt.Color;

/**
 * @author reinh
 *
 */
public class DriverLapDistance {

	private short vehicleIdx;
	private short raceNumber;
	private Color teamColor;
	private float lapDistance;

	public void setRaceNumber(short argRaceNumber) {
		raceNumber = argRaceNumber;
	}

	public short getRaceNumber() {
		return raceNumber;
	}

	public short getVehicleIdx() {
		return vehicleIdx;
	}

	public void setVehicleIdx(short argVehicleIdx) {
		vehicleIdx = argVehicleIdx;
	}

	public void setTeamColor(short teamId) {

		switch (teamId) {
		case 0: // MERCEDES
			teamColor = new Color(108, 211, 191);
			break;
		case 1: // FERRARI
			teamColor = new Color(249, 21, 54);
			break;
		case 2: // RED BULL RACING
			teamColor = new Color(54, 113, 198);
			break;
		case 3: // WILLIAMS
			teamColor = new Color(55, 190, 221);
			break;
		case 4: // ASTON MARTIN
			teamColor = new Color(53, 140, 117);
			break;
		case 5: // ALPINE
			teamColor = new Color(34, 147, 209);
			break;
		case 6: // ALPHA TAURI
			teamColor = new Color(94, 143, 170);
			break;
		case 7: // HAAS
			teamColor = new Color(182, 186, 189);
			break;
		case 8: // McLAREN"
			teamColor = new Color(245, 128, 32);
			break;
		case 9: // ALFA ROMEO
			teamColor = new Color(201, 45, 75);
			break;

		default:
			teamColor = Color.BLACK;
		}
	}

	public Color getTeamColor() {
		return teamColor;
	}

	public void setLapDistance(float argLapDistance) {
		lapDistance = argLapDistance;
	}

	public float getLapDistance() {

		return lapDistance;
	}

}
