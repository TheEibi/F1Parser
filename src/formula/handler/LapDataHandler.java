/**
 * 
 */
package formula.handler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import formula.F1DataHelper;
import formula.F1Frame;
import formula.packets.PacketLapData;

/**
 * @author reinh
 *
 */
public class LapDataHandler {
	private static final Logger log = LogManager.getLogger(LapDataHandler.class);

	public static void handleLapData(PacketLapData argPacketLapData) {

		short pos = 1;
		if (F1DataHelper.getMyVehicleIdx() > -1) {
			pos = argPacketLapData.getLapDataList().get(F1DataHelper.getMyVehicleIdx()).getCarPosition();
		}
		F1DataHelper.setFrontVehicleIdx(F1DataHelper.getVehicleIdxDriverInFront(pos, argPacketLapData));
		F1DataHelper.setBehindVehicleIdx(F1DataHelper.getVehicleIdxDriverBehind(pos, argPacketLapData));

		F1DataHelper.updateWarnings(F1DataHelper.getFrontVehicleIdx(), 0);
		F1DataHelper.updateWarnings(F1DataHelper.getMyVehicleIdx(), 1);
		F1DataHelper.updateWarnings(F1DataHelper.getBehindVehicleIdx(), 2);

		F1DataHelper.createDriverLapPosition(argPacketLapData);

		F1Frame.drawTrackPosition(argPacketLapData);

		F1DataHelper.checkPitstop(argPacketLapData);

		log.debug(argPacketLapData);
	}

	private LapDataHandler() {
	}

}
