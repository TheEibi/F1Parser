/**
 * 
 */
package formula.packets;

import java.io.Serializable;
import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import formula.constants.GameModeConstants;
import formula.constants.RulesetConstants;
import formula.constants.SessionConstants;
import formula.constants.TrackConstants;

/**
 * @author reinh
 *
 */
public class PacketSession implements Serializable, IF1Packet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -313846997956123217L;
	private PacketHeader packetHeader;
	private short weather;
	private short trackTemperature;
	private short airTemperature;
	private short totalLaps;
	private short trackLength;
	private short sessionType;
	private short trackId;
	private short formula;
	private short sessionTimeLeft;
	private short sessionDuration;
	private short pitSpeedLimit;
	private short gamePaused;
	private short isSpectating;
	private short spectatorCardIndex;
	private short sliProNativeSupport;
	private short numMarshalZones;
	private List<MarshallZone> marshallZoneList;
	private short safetyCarStatus;
	private short networkGame;
	private short numWeatherForecastSamples;
	private List<WeatherForecastSample> weatherForecastSampleList;
	private short forecastAccuracy;
	private short aiDifficulty;
	private int seasonLinkIdentifier;
	private int weekendLinkIdentifier;
	private int sessionLinkIdentifier;
	private short pitStopWindowIdealLap;
	private short pitStopWindowLatestLap;
	private short pitStopRejoinPosition;
	private short steeringAssist;
	private short brakingAssist;
	private short gearboxAssist;
	private short pitAssist;
	private short pitReleaseAssist;
	private short ersAssist;
	private short drsAssist;
	private short dynamicRacingLine;
	private short dynamicRacingLineType;
	private short gameMode;
	private short ruleSet;
	private int timeOfDay;
	private short sessionLength;

	public PacketSession(PacketHeader argPacketHeader, ByteBuffer argBb) {
		packetHeader = argPacketHeader;
		weather = argBb.get();
		trackTemperature = argBb.get();
		airTemperature = argBb.get();
		totalLaps = argBb.get();
		trackLength = argBb.getShort();
		sessionType = argBb.get();
		trackId = argBb.get();
		formula = argBb.get();
		sessionTimeLeft = argBb.getShort();
		sessionDuration = argBb.getShort();
		pitSpeedLimit = argBb.get();
		gamePaused = argBb.get();
		isSpectating = argBb.get();
		spectatorCardIndex = argBb.get();
		sliProNativeSupport = argBb.get();
		numMarshalZones = argBb.get();
		marshallZoneList = new ArrayList<>();
		for (short i = 21; i > 0; i--) {
			marshallZoneList.add(new MarshallZone(argBb));
		}
		safetyCarStatus = argBb.get();
		networkGame = argBb.get();
		numWeatherForecastSamples = argBb.get();
		weatherForecastSampleList = new ArrayList<>();
		for (short i = 56; i > 0; i--) {
			weatherForecastSampleList.add(new WeatherForecastSample(argBb));
		}
		forecastAccuracy = argBb.get();
		aiDifficulty = argBb.get();
		seasonLinkIdentifier = argBb.getInt();
		weekendLinkIdentifier = argBb.getInt();
		sessionLinkIdentifier = argBb.getInt();
		pitStopWindowIdealLap = argBb.get();
		pitStopWindowLatestLap = argBb.get();
		pitStopRejoinPosition = argBb.get();
		steeringAssist = argBb.get();
		brakingAssist = argBb.get();
		gearboxAssist = argBb.get();
		pitAssist = argBb.get();
		pitReleaseAssist = argBb.get();
		ersAssist = argBb.get();
		drsAssist = argBb.get();
		dynamicRacingLine = argBb.get();
		dynamicRacingLineType = argBb.get();
		gameMode = argBb.get();
		ruleSet = argBb.get();
		timeOfDay = argBb.getInt();
		sessionLength = argBb.get();
	}

	public PacketHeader getPacketHeader() {
		return packetHeader;
	}

	public short getWeather() {
		return weather;
	}

	public short getTrackTemperature() {
		return trackTemperature;
	}

	public short getAirTemperature() {
		return airTemperature;
	}

	public short getTotalLaps() {
		return totalLaps;
	}

	public short getTrackLength() {
		return trackLength;
	}

	public short getSessionType() {
		return sessionType;
	}

	public short getTrackId() {
		return trackId;
	}

	public short getFormula() {
		return formula;
	}

	public short getSessionTimeLeft() {
		return sessionTimeLeft;
	}

	public short getSessionDuration() {
		return sessionDuration;
	}

	public short getPitSpeedLimit() {
		return pitSpeedLimit;
	}

	public short getGamePaused() {
		return gamePaused;
	}

	public short getIsSpectating() {
		return isSpectating;
	}

	public short getSpectatorCardIndex() {
		return spectatorCardIndex;
	}

	public short getSliProNativeSupport() {
		return sliProNativeSupport;
	}

	public short getNumMarshalZones() {
		return numMarshalZones;
	}

	public List<MarshallZone> getMarshallZoneList() {
		return marshallZoneList;
	}

	public short getSafetyCarStatus() {
		return safetyCarStatus;
	}

	public short getNetworkGame() {
		return networkGame;
	}

	public short getNumWeatherForecastSamples() {
		return numWeatherForecastSamples;
	}

	public List<WeatherForecastSample> getWeatherForecastSampleList() {
		return weatherForecastSampleList;
	}

	public short getForecastAccuracy() {
		return forecastAccuracy;
	}

	public short getAiDifficulty() {
		return aiDifficulty;
	}

	public int getSeasonLinkIdentifier() {
		return seasonLinkIdentifier;
	}

	public int getWeekendLinkIdentifier() {
		return weekendLinkIdentifier;
	}

	public int getSessionLinkIdentifier() {
		return sessionLinkIdentifier;
	}

	public short getPitStopWindowIdealLap() {
		return pitStopWindowIdealLap;
	}

	public short getPitStopWindowLatestLap() {
		return pitStopWindowLatestLap;
	}

	public short getPitStopRejoinPosition() {
		return pitStopRejoinPosition;
	}

	public short getSteeringAssist() {
		return steeringAssist;
	}

	public short getBrakingAssist() {
		return brakingAssist;
	}

	public short getGearboxAssist() {
		return gearboxAssist;
	}

	public short getPitAssist() {
		return pitAssist;
	}

	public short getPitReleaseAssist() {
		return pitReleaseAssist;
	}

	public short getErsAssist() {
		return ersAssist;
	}

	public short getDrsAssist() {
		return drsAssist;
	}

	public short getDynamicRacingLine() {
		return dynamicRacingLine;
	}

	public short getDynamicRacingLineType() {
		return dynamicRacingLineType;
	}

	public short getGameMode() {
		return gameMode;
	}

	public short getRuleSet() {
		return ruleSet;
	}

	public int getTimeOfDay() {
		return timeOfDay;
	}

	public short getSessionLength() {
		return sessionLength;
	}

	public String getTimeOfDayCalculated() {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

		Calendar c = new GregorianCalendar();
		c.clear();
		c.add(Calendar.MINUTE, getTimeOfDay());

		return sdf.format(c.getTime());
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getPacketHeader());
		sb.append("::");
		sb.append(getClass().getSimpleName());
		sb.append("::");
		sb.append(SessionConstants.WEATHER.get(getWeather()));
		sb.append("::");
		sb.append(getTrackTemperature());
		sb.append("::");
		sb.append(getAirTemperature());
		sb.append("::");
		sb.append(getTotalLaps());
		sb.append("::");
		sb.append(getTrackLength());
		sb.append("::");
		sb.append(SessionConstants.SESSION_TYPE.get(getSessionType()));
		sb.append("::");
		sb.append(TrackConstants.TRACK.get(getTrackId()));
		sb.append("::");
		sb.append(SessionConstants.FORMULA.get(getFormula()));
		sb.append("::");
		sb.append(getSessionTimeLeft());
		sb.append("::");
		sb.append(getSessionDuration());
		sb.append("::");
		sb.append(getPitSpeedLimit());
		sb.append("::");
		sb.append(getGamePaused());
		sb.append("::");
		sb.append(getIsSpectating());
		sb.append("::");
		sb.append(Byte.toUnsignedInt((byte) getSpectatorCardIndex()));
		sb.append("::");
		sb.append(SessionConstants.SLI_PRO_NATIVE_SUPPORT.get(getSliProNativeSupport()));
		sb.append("::");
		sb.append(getNumMarshalZones());
		sb.append("::");
		for (int i = 0; i < getNumMarshalZones(); i++) {
			sb.append(marshallZoneList.get(i));
			sb.append("::");
		}
		sb.append("::");
		sb.append(SessionConstants.SAFETY_CAR_STATUS.get(getSafetyCarStatus()));
		sb.append("::");
		sb.append(SessionConstants.NETWORK_GAME.get(getNetworkGame()));
		sb.append("::");
		sb.append(getNumWeatherForecastSamples());
		sb.append("::");
		for (int i = 0; i < getNumWeatherForecastSamples(); i++) {
			sb.append(weatherForecastSampleList.get(i));
			sb.append("::");
		}

		sb.append(SessionConstants.FORECAST_ACCURACY.get(getForecastAccuracy()));
		sb.append("::");
		sb.append(getAiDifficulty());
		sb.append("::");
		sb.append(getSeasonLinkIdentifier());
		sb.append("::");
		sb.append(getWeekendLinkIdentifier());
		sb.append("::");
		sb.append(getSessionLinkIdentifier());
		sb.append("::");
		sb.append(getPitStopWindowIdealLap());
		sb.append("::");
		sb.append(getPitStopWindowLatestLap());
		sb.append("::");
		sb.append(getPitStopRejoinPosition());
		sb.append("::");
		sb.append(SessionConstants.STEERING_ASSIST.get(getSteeringAssist()));
		sb.append("::");
		sb.append(SessionConstants.BRAKING_ASSIST.get(getBrakingAssist()));
		sb.append("::");
		sb.append(SessionConstants.GEARBOX_ASSIST.get(getGearboxAssist()));
		sb.append("::");
		sb.append(SessionConstants.PIT_ASSIST.get(getPitAssist()));
		sb.append("::");
		sb.append(SessionConstants.PIT_RELEASE_ASSIST.get(getPitReleaseAssist()));
		sb.append("::");
		sb.append(SessionConstants.ERS_ASSIST.get(getErsAssist()));
		sb.append("::");
		sb.append(SessionConstants.DRS_ASSIST.get(getDrsAssist()));
		sb.append("::");
		sb.append(SessionConstants.DYNAMIC_RACING_LINE.get(getDynamicRacingLine()));
		sb.append("::");
		sb.append(SessionConstants.DYNAMIC_RACING_LINE_TYPE.get(getDynamicRacingLineType()));
		sb.append("::");
		sb.append(GameModeConstants.GAME_MODE.get(getGameMode()));
		sb.append("::");
		sb.append(RulesetConstants.RULE_SET.get(getRuleSet()));
		sb.append("::");
		sb.append(getTimeOfDayCalculated());
		sb.append("::");
		sb.append(SessionConstants.SESSION_LENGTH.get(getSessionLength()));
		return sb.toString();
	}

}
