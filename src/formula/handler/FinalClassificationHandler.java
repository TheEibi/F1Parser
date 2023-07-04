/**
 * 
 */
package formula.handler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import formula.packets.PacketFinalClassification;

/**
 * @author reinh
 *
 */
public class FinalClassificationHandler {

	private static final Logger log = LogManager.getLogger(FinalClassificationHandler.class);

	public static void handleFinalClassification(PacketFinalClassification argPacketFinalClassification) {

		log.debug(argPacketFinalClassification);
	}

	private FinalClassificationHandler() {
	}

}
