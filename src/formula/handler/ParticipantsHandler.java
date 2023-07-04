/**
 * 
 */
package formula.handler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import formula.F1DataHelper;
import formula.F1Frame;
import formula.packets.PacketParticipants;

/**
 * @author reinh
 *
 */
public class ParticipantsHandler {

	private static final Logger log = LogManager.getLogger(ParticipantsHandler.class);

	public static void handleParticipants(PacketParticipants argPacketParticipants) {

		if (F1DataHelper.isUseSelf()) {
			F1DataHelper.setMyVehicleIdx(argPacketParticipants.getPacketHeader().getPlayerCarIndex());
		}
		F1DataHelper.setParticipants(argPacketParticipants);
		F1Frame.setChangeSelfEnabled(true);

		log.debug(argPacketParticipants);
	}

	public ParticipantsHandler() {
	}
}
