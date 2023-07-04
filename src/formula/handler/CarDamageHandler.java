/**
 * 
 */
package formula.handler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import formula.F1DataHelper;
import formula.packets.PacketCarDamage;

/**
 * @author reinh
 *
 */
public class CarDamageHandler {

	private static final Logger log = LogManager.getLogger(CarDamageHandler	.class);

	public static void handleCarDamage(PacketCarDamage argCarDamage) {

		F1DataHelper.updateTyreWear(argCarDamage);
		F1DataHelper.updateWarnings();

		log.debug(argCarDamage);
	}

	private CarDamageHandler() {
	}

}
