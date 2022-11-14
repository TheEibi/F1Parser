/**
 * 
 */
package formula.constants;

import java.util.HashMap;
import java.util.Map;

/**
 * @author reinh
 *
 */
public class SessionConstants {

	public static final Map<Short, String> MARSHALL_ZONE_FLAG = new HashMap<>();
	public static final Map<Short, String> SESSION_TYPE = new HashMap<>();
	public static final Map<Short, String> WEATHER = new HashMap<>();
	public static final Map<Short, String> TRACK_TEMPERATURE_CHANGE = new HashMap<>();
	public static final Map<Short, String> AIR_TEMPERATURE_CHANGE = new HashMap<>();
	public static final Map<Short, String> FORMULA = new HashMap<>();
	public static final Map<Short, String> SLI_PRO_NATIVE_SUPPORT = new HashMap<>();
	public static final Map<Short, String> SAFETY_CAR_STATUS = new HashMap<>();
	public static final Map<Short, String> NETWORK_GAME = new HashMap<>();
	public static final Map<Short, String> FORECAST_ACCURACY = new HashMap<>();
	public static final Map<Short, String> STEERING_ASSIST = new HashMap<>();
	public static final Map<Short, String> BRAKING_ASSIST = new HashMap<>();
	public static final Map<Short, String> GEARBOX_ASSIST = new HashMap<>();
	public static final Map<Short, String> PIT_ASSIST = new HashMap<>();
	public static final Map<Short, String> PIT_RELEASE_ASSIST = new HashMap<>();
	public static final Map<Short, String> ERS_ASSIST = new HashMap<>();
	public static final Map<Short, String> DRS_ASSIST = new HashMap<>();
	public static final Map<Short, String> DYNAMIC_RACING_LINE = new HashMap<>();
	public static final Map<Short, String> DYNAMIC_RACING_LINE_TYPE = new HashMap<>();
	
	
	public static final Map<Short, String> SESSION_LENGTH = new HashMap<>();
	
	
	
	static {
		MARSHALL_ZONE_FLAG.put((short) -1, "INVALID/UNKNOWN");
		MARSHALL_ZONE_FLAG.put((short) 0, "NONE");
		MARSHALL_ZONE_FLAG.put((short) 1, "GREEN");
		MARSHALL_ZONE_FLAG.put((short) 2, "BLUE");
		MARSHALL_ZONE_FLAG.put((short) 3, "YELLOW");
		MARSHALL_ZONE_FLAG.put((short) 4, "RED");

		SESSION_TYPE.put((short) 0, "UNKNOWN");
		SESSION_TYPE.put((short) 1, "P1");
		SESSION_TYPE.put((short) 2, "P2");
		SESSION_TYPE.put((short) 3, "P3");
		SESSION_TYPE.put((short) 4, "SHORT P");
		SESSION_TYPE.put((short) 5, "Q1");
		SESSION_TYPE.put((short) 6, "Q2");
		SESSION_TYPE.put((short) 7, "Q3");
		SESSION_TYPE.put((short) 8, "SHORT Q");
		SESSION_TYPE.put((short) 9, "ONE-SHOT Q");
		SESSION_TYPE.put((short) 10, "R");
		SESSION_TYPE.put((short) 11, "R2");
		SESSION_TYPE.put((short) 12, "R3");
		SESSION_TYPE.put((short) 13, "TIME TRIAL");
		
		WEATHER.put((short) 0, "CLEAR");
		WEATHER.put((short) 1, "LIGHT CLOUD");
		WEATHER.put((short) 2, "OVERCAST");
		WEATHER.put((short) 3, "LIGHT RAIN");
		WEATHER.put((short) 4, "HEAVY RAIN");
		WEATHER.put((short) 5, "STORM");
		
		TRACK_TEMPERATURE_CHANGE.put((short) 0, "UP");
		TRACK_TEMPERATURE_CHANGE.put((short) 1, "DOWN");
		TRACK_TEMPERATURE_CHANGE.put((short) 2, "NO CHANGE");
		
		AIR_TEMPERATURE_CHANGE.put((short) 0, "UP");
		AIR_TEMPERATURE_CHANGE.put((short) 1, "DOWN");
		AIR_TEMPERATURE_CHANGE.put((short) 2, "NO CHANGE");
		
		FORMULA.put((short) 0, "F1 MODERN");
		FORMULA.put((short) 1, "F1 CLASSIC");
		FORMULA.put((short) 2, "F2");
		FORMULA.put((short) 3, "F1 GENERIC");
		FORMULA.put((short) 4, "BETA");
		FORMULA.put((short) 5, "SUPERCARS");
		FORMULA.put((short) 6, "ESPORTS");
		FORMULA.put((short) 7, "F2 2021");
		
		SLI_PRO_NATIVE_SUPPORT.put((short) 0, "INACTIVE");
		SLI_PRO_NATIVE_SUPPORT.put((short) 1, "ACTIVE");
		
		SAFETY_CAR_STATUS.put((short) 0, "NO SAFETY CAR");
		SAFETY_CAR_STATUS.put((short) 1, "FULL");
		SAFETY_CAR_STATUS.put((short) 2, "VIRTUAL");
		SAFETY_CAR_STATUS.put((short) 3, "FORMATION LAP");
		
		NETWORK_GAME.put((short) 0, "OFFLINE");
		NETWORK_GAME.put((short) 1, "ONLINE");
		
		FORECAST_ACCURACY.put((short) 0, "PERFECT");
		FORECAST_ACCURACY.put((short) 1, "APPROXIMATE");
		
		STEERING_ASSIST.put((short) 0, "OFF");
		STEERING_ASSIST.put((short) 1, "ON");
		
		BRAKING_ASSIST.put((short) 0, "OFF");
		BRAKING_ASSIST.put((short) 1, "LOW");
		BRAKING_ASSIST.put((short) 2, "MEDIUM");
		BRAKING_ASSIST.put((short) 3, "HIGH");
		
		GEARBOX_ASSIST.put((short) 1, "MANUAL");
		GEARBOX_ASSIST.put((short) 2, "MANUAL & SUGGERSTED GEAR");
		GEARBOX_ASSIST.put((short) 3, "AUTO");

		PIT_ASSIST.put((short) 0, "OFF");
		PIT_ASSIST.put((short) 1, "ON");
		
		PIT_RELEASE_ASSIST.put((short) 0, "OFF");
		PIT_RELEASE_ASSIST.put((short) 1, "ON");
		
		ERS_ASSIST.put((short) 0, "OFF");
		ERS_ASSIST.put((short) 1, "ON");
		
		DRS_ASSIST.put((short) 0, "OFF");
		DRS_ASSIST.put((short) 1, "ON");
		
		DYNAMIC_RACING_LINE.put((short) 0, "OFF");
		DYNAMIC_RACING_LINE.put((short) 1, "CORNERS ONLY");
		DYNAMIC_RACING_LINE.put((short) 2, "FULL");
		
		DYNAMIC_RACING_LINE_TYPE.put((short) 0, "2D");
		DYNAMIC_RACING_LINE_TYPE.put((short) 1, "3D");
		
		
		SESSION_LENGTH.put((short) 0, "NONE");
		SESSION_LENGTH.put((short) 2, "VERY SHORT");
		SESSION_LENGTH.put((short) 3, "SHORT");
		SESSION_LENGTH.put((short) 4, "MEDIUM");
		SESSION_LENGTH.put((short) 5, "MEDIUM LONG");
		SESSION_LENGTH.put((short) 6, "LONG");
		SESSION_LENGTH.put((short) 7, "FULL");
		
	}
	
	private SessionConstants() {}
}
