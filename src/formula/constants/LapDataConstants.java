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
public class LapDataConstants {
	
	public static final Map<Short, String> PIT_STATUS= new HashMap<>();
	public static final Map<Short, String> SECTOR = new HashMap<>();
	public static final Map<Short, String> CURRENT_LAP_INVALID = new HashMap<>();
	public static final Map<Short, String> DRIVER_STATUS = new HashMap<>();
	public static final Map<Short, String> RESULT_STATUS = new HashMap<>();
	public static final Map<Short, String> PIT_LANE_TIMER_ACTIVE = new HashMap<>();
	
	static {
		PIT_STATUS.put((short) 0, "NONE");
		PIT_STATUS.put((short) 1, "PITTING");
		PIT_STATUS.put((short) 2, "IN PIT AREA");
	
		SECTOR.put((short) 0, "SECTOR 1");
		SECTOR.put((short) 1, "SECTOR 2");
		SECTOR.put((short) 2, "SECTOR 3");
		
		CURRENT_LAP_INVALID.put((short) 0, "VALID");
		CURRENT_LAP_INVALID.put((short) 1, "INVALID");

		DRIVER_STATUS.put((short) 0, "IN GARAGE");
		DRIVER_STATUS.put((short) 1, "FLYING LAP");
		DRIVER_STATUS.put((short) 2, "IN LAP");
		DRIVER_STATUS.put((short) 3, "OUT LAP");
		DRIVER_STATUS.put((short) 4, "ON TRACK");

		RESULT_STATUS.put((short) 0, "INVALID");
		RESULT_STATUS.put((short) 1, "INACTIVE");
		RESULT_STATUS.put((short) 2, "ACTIVE");
		RESULT_STATUS.put((short) 3, "FINISHED");
		RESULT_STATUS.put((short) 4, "DIDNOTFINISH");
		RESULT_STATUS.put((short) 5, "DISQUALIFIED");
		RESULT_STATUS.put((short) 6, "NOT CLASSIFIED");
		RESULT_STATUS.put((short) 7, "RETIRED");
		
		PIT_LANE_TIMER_ACTIVE.put((short) 0, "INACTIVE");
		PIT_LANE_TIMER_ACTIVE.put((short) 1, "ACTIVE");
	}
}
