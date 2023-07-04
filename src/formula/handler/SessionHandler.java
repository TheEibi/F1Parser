/**
 * 
 */
package formula.handler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import formula.F1DataHelper;
import formula.F1Frame;
import formula.constants.SessionConstants;
import formula.packets.PacketSession;

/**
 * @author reinh
 *
 */
public class SessionHandler {
	private static final Logger log = LogManager.getLogger(SessionHandler.class);
	private static final Logger log_weather = LogManager.getLogger("WEATHER_LOGGER");

	public static void handleSession(PacketSession argPacketSession) {
		F1DataHelper.setTrackId(argPacketSession.getTrackId());
		F1DataHelper.setTrackLength(argPacketSession.getTrackLength());
		F1Frame.drawSafetyCarStatus(argPacketSession);

		boolean logWeather = false;
		if (logWeather) {
			log_weather.debug(SessionConstants.WEATHER.get(argPacketSession.getWeather()));
			log_weather.debug(argPacketSession.getTrackTemperature());
			log_weather.debug(argPacketSession.getAirTemperature());

			for (int i = 0; i < argPacketSession.getNumWeatherForecastSamples(); i++) {
				log_weather.debug(argPacketSession.getWeatherForecastSampleList().get(i));
			}

			log_weather.debug("");
			log_weather.debug("");
			log_weather.debug("");
		}

		log.debug(argPacketSession);
	}

	private SessionHandler() {
	}

}
