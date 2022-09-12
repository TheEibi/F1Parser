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
public class PenaltyConstants {

	public static final Map<Short, String> TYPE = new HashMap<>();
	

	static {
		TYPE.put((short) 0, "DRIVE THROUGH");
		TYPE.put((short) 1, "STOP GO");
		TYPE.put((short) 2, "GRID PENALTY");
		TYPE.put((short) 3, "PENALTY REMINDER");
		TYPE.put((short) 4, "TIME PENALTY");
		TYPE.put((short) 5, "WARNING");
		TYPE.put((short) 6, "DISQUALIFIED");
		TYPE.put((short) 7, "REMOVED FROM FORMATION LAP");
		TYPE.put((short) 8, "PARKED TOO LONG TIMER");
		TYPE.put((short) 9, "TYRE REGULATIONS");
		TYPE.put((short) 10, "THIS LAP INVALIDATED");
		TYPE.put((short) 11, "THIS AND NEX LAP INVALIDATED");
		TYPE.put((short) 12, "THIS LAP INVALIDATED WITHOUT REASON");
		TYPE.put((short) 13, "THIS AND NEXT LAP INVALIDATED WITHOUT REASON");
		TYPE.put((short) 14, "THIS AND PREVIOUS LAP INVALIDATED");
		TYPE.put((short) 15, "THIS AND PREVIOUS LAP INVALIDATED WITHOUT REASON");
		TYPE.put((short) 16, "RETIRED");
		TYPE.put((short) 17, "BLACK FLAG TIMER");		
	}
}
