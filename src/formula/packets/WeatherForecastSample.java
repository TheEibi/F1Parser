package formula.packets;

import java.io.Serializable;
import java.nio.ByteBuffer;

import formula.constants.SessionConstants;

public class WeatherForecastSample  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6709654564067592191L;
	private short sessionType;
	private short timeOffset;
	private short weather;
	private short trackTemperature;
	private short trackTemperatureChange;
	private short airTemperature;
	private short airTemperatureChange;
	private short rainPercentage;

	public void initV22(ByteBuffer argBb) {
		sessionType = argBb.get();
		timeOffset = argBb.get();
		weather = argBb.get();
		trackTemperature = argBb.get();
		trackTemperatureChange = argBb.get();
		airTemperature = argBb.get();
		airTemperatureChange = argBb.get();
		rainPercentage = argBb.get();
	}

	public short getSessionType() {
		return sessionType;
	}

	public short getTimeOffset() {
		return timeOffset;
	}

	public short getWeather() {
		return weather;
	}

	public short getTrackTemperature() {
		return trackTemperature;
	}

	public short getTrackTemperatureChange() {
		return trackTemperatureChange;
	}

	public short getAirTemperature() {
		return airTemperature;
	}

	public short getAirTemperatureChange() {
		return airTemperatureChange;
	}

	public short getRainPercentage() {
		return rainPercentage;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append("::");
		sb.append(SessionConstants.SESSION_TYPE.get(getSessionType()));
		sb.append("::");
		sb.append(getTimeOffset());
		sb.append("::");
		sb.append(SessionConstants.WEATHER.get(getWeather()));
		sb.append("::");
		sb.append(getTrackTemperature());
		sb.append("::");
		sb.append(SessionConstants.TRACK_TEMPERATURE_CHANGE.get(getTrackTemperatureChange()));
		sb.append("::");
		sb.append(getAirTemperature());
		sb.append("::");
		sb.append(SessionConstants.AIR_TEMPERATURE_CHANGE.get(getAirTemperatureChange()));
		sb.append("::");
		sb.append(getRainPercentage());
		return sb.toString();
	}
}