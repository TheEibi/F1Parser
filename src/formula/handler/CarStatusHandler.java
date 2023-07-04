/**
 * 
 */
package formula.handler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import formula.F1DataHelper;
import formula.packets.CarStatusData;
import formula.packets.PacketCarStatus;

/**
 * @author reinh
 *
 */
public class CarStatusHandler {

	private static final Logger log = LogManager.getLogger(CarStatusHandler.class);
	
	public static void handleCarStatus(PacketCarStatus argCarStatus) {

		int i = 0;
		short[] vehicleCurrentTyre = F1DataHelper.getVehicleCurrentTyre();
		boolean update = false;
		for (CarStatusData carStatusData : argCarStatus.getCarStatusDataList()) {
			if (vehicleCurrentTyre[i] != carStatusData.getVisualTyreCompound()) {
				vehicleCurrentTyre[i] = carStatusData.getVisualTyreCompound();
				update = true;
			}
			i++;
		}

		if (update) {
			F1DataHelper.setVehicleCurrentTyre(vehicleCurrentTyre);
			F1DataHelper.updateCurrentTyre();
		}

		F1DataHelper.updateERSStatus(argCarStatus);

		log.debug(argCarStatus);
	}

	private CarStatusHandler() {
	}

}
