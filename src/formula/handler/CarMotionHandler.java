/**
 * 
 */
package formula.handler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import formula.packets.PacketCarMotion;

/**
 * @author reinh
 *
 */
public class CarMotionHandler {
	private static final Logger log = LogManager.getLogger(CarMotionHandler	.class);

	public static void handleCarMotion(PacketCarMotion argCarMotion) {
//		System.out.println(argCarMotion.toString());
		log.debug(argCarMotion);
	}

	private CarMotionHandler() {
	}

}
