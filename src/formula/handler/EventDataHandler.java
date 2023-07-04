/**
 * 
 */
package formula.handler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import formula.F1DataHelper;
import formula.F1Frame;
import formula.packets.PacketEventData;

/**
 * @author reinh
 *
 */
public class EventDataHandler {

	private static final Logger log = LogManager.getLogger(EventDataHandler.class);
	private static final Logger log_penalty = LogManager.getLogger("PENALTY_LOGGER");
	
	public static void handleEventData(PacketEventData argPacketEventData) {
		if ("SSTA".equals(argPacketEventData.getEventStringCodeAsString())) {
			F1DataHelper.setVehicleTrackWarnings(
					new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 });
			F1DataHelper.setVehicleCurrentTyre(
					new short[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 });
			F1DataHelper.updateWarnings((short) 0, 0);
			F1DataHelper.updateWarnings((short) 0, 1);
			F1DataHelper.updateWarnings((short) 0, 2);
			F1DataHelper.resetDriverLapPosition();
			F1DataHelper.resetDriverDistance();
			F1DataHelper.resetPitStops();
		}
		if ("SEND".equals(argPacketEventData.getEventStringCodeAsString())) {
			F1DataHelper.setUseSelf(true);
			F1Frame.setChangeSelfEnabled(false);
			F1DataHelper.printLeadingLaps();
			F1DataHelper.printTotalDrivenDistance();
			F1DataHelper.printFrontWingChanges();
		}
		if ("PENA".equals(argPacketEventData.getEventStringCodeAsString())) {
			String name = F1DataHelper.getNameForIdx(argPacketEventData.getEventDataDetails().getVehicleIdx());
			String nameOther = F1DataHelper
					.getNameForIdx(argPacketEventData.getEventDataDetails().getOtherVehicleIdx());
			log.debug("{} {} {}", name, nameOther, argPacketEventData);
			log_penalty.info("{} {} {}", name, nameOther, argPacketEventData);

			if (5 == argPacketEventData.getEventDataDetails().getPenaltyType()
					&& (27 == argPacketEventData.getEventDataDetails().getInfringementType()
							|| 7 == argPacketEventData.getEventDataDetails().getInfringementType())) {

				short idx = argPacketEventData.getEventDataDetails().getVehicleIdx();
				F1DataHelper.increaseWarningByVehicleIdx(idx);

				if (F1DataHelper.getVehicleTrackWarnings()[idx] == 3) {
					F1DataHelper.resetWarningByVehicleIdx(idx);
				}

				F1DataHelper.updateWarnings(F1DataHelper.getFrontVehicleIdx(), 0);
				F1DataHelper.updateWarnings(F1DataHelper.getMyVehicleIdx(), 1);
				F1DataHelper.updateWarnings(F1DataHelper.getBehindVehicleIdx(), 2);
			}
		}
		log.debug(argPacketEventData);
	}
	
	private EventDataHandler() {
	}
}
