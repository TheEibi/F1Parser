/**
 * 
 */
package formula.data;

/**
 * @author reinh
 *
 */
public class DriverLapPosition {

	short vehicleId;
	int lapNo;
	int pos;

	public DriverLapPosition(short argVehicleId, int argLapNo, int argPos) {
		vehicleId = argVehicleId;
		lapNo = argLapNo;
		pos = argPos;
	}

	public short getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(short argVehicleId) {
		vehicleId = argVehicleId;
	}

	public int getLapNo() {
		return lapNo;
	}

	public void setLapNo(int argLapNo) {
		lapNo = argLapNo;
	}

	public int getPos() {
		return pos;
	}

	public void setPos(int argPos) {
		pos = argPos;
	}

}
