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
public class InfringementConstants {

	public static final Map<Short, String> TYPE = new HashMap<>();

	static {
		TYPE.put((short) 0, "BLOCKING BY SLOW DRIVING");
		TYPE.put((short) 1, "BLOCKING BY WRONG WAY DRIVING");
		TYPE.put((short) 2, "REVERSING OFF THE START LINE");
		TYPE.put((short) 3, "BIG COLLISION");
		TYPE.put((short) 4, "SMALL COLLISION");
		TYPE.put((short) 5, "COLLISION FAILED TO HAND BACK POSITION SINGLE");
		TYPE.put((short) 6, "COLLISION FAILED TO HAND BACK POSITION MULTIPLE");
		TYPE.put((short) 7, "CORNER CUTTING GAINED TIME");
		TYPE.put((short) 8, "CORNER CUTTING OVERTAKE SINGLE");
		TYPE.put((short) 9, "CORNER CUTTING OVERTAKE MULTIPLE");
		TYPE.put((short) 10, "CROSSED PIT EXIT LANE");
		TYPE.put((short) 11, "IGNORING BLUE FLAGS");
		TYPE.put((short) 12, "IGNORING YELLOW FLAGS");
		TYPE.put((short) 13, "IGNORING DRIVE THROUGH");
		TYPE.put((short) 14, "TOO MANY DRIVE THROUGHS");
		TYPE.put((short) 15, "DRIVE THROUGH REMINDER SERVE WITHIN N LAPS");
		TYPE.put((short) 16, "DRIVE THROUGH REMINDER SERVE THIS LAP");
		TYPE.put((short) 17, "PIT LANE SPEEDING");
		TYPE.put((short) 18, "PARKED FOR TOO LONG");
		TYPE.put((short) 19, "IGNORING TYRE REGULATIONS");
		TYPE.put((short) 20, "TOO MANY PENALTIES");
		TYPE.put((short) 21, "MULTIPLE WARNINGS");
		TYPE.put((short) 22, "APPROACHING DISQUALIFICATION");
		TYPE.put((short) 23, "TYRE REGULATIONS SELECT SINGLE");
		TYPE.put((short) 24, "TYRE REGULATIONS SELECT MULTIPLE");
		TYPE.put((short) 25, "LAP INVALIDATED CORNER CUTTING");
		TYPE.put((short) 26, "LAP INVALIDATED RUNNING WIDE");
		TYPE.put((short) 27, "CORNER CUTTING RAN WIDE GAINED TIME MINOR");
		TYPE.put((short) 28, "CORNER CUTTING RAN WIDE GAINED TIME SIGNIFICANT");
		TYPE.put((short) 29, "CORNER CUTTING RAN WIDE GAINED TIME EXTREME");
		TYPE.put((short) 30, "LAP INVALIDATED WALL RIDING");
		TYPE.put((short) 31, "LAP INVALIDATED FLASHBACK USED");
		TYPE.put((short) 32, "LAP INVALIDATED RESET TO TRACK");
		TYPE.put((short) 33, "BLOCKING THE PITLANE");
		TYPE.put((short) 34, "JUMP START");
		TYPE.put((short) 35, "SAFETY CAR TO CAR COLLISION");
		TYPE.put((short) 36, "SAFETY CAR ILLEGAL OVERTAKE");
		TYPE.put((short) 37, "SAFETY CAR EXCEEDING ALLOWED PACE");
		TYPE.put((short) 38, "VIRTUAL SAFETY CAR EXCEEDING ALLOWED PACE");
		TYPE.put((short) 39, "FORMATION LAP BELOW ALLOWED SPEED");
		TYPE.put((short) 40, "FORMATION LAP PARKING");
		TYPE.put((short) 41, "RETIRED MECHANICAL FAILURE");
		TYPE.put((short) 42, "RETIRED TERMINALLY DAMAGED");
		TYPE.put((short) 43, "SAFETY CAR FALLING TOO FAR BACK");
		TYPE.put((short) 44, "BLACK FLAG TIMER");
		TYPE.put((short) 45, "UNSERVED STOP GO PENALTY");
		TYPE.put((short) 46, "UNSERVED DRIVE THROUGH PENALTY");
		TYPE.put((short) 47, "ENGINE COMPONENT CHANGE");
		TYPE.put((short) 48, "GEARBOX CHANGE");
		TYPE.put((short) 49, "PARC FERMÉ CHANGE");
		TYPE.put((short) 50, "LEAGUE GRID PENALTY");
		TYPE.put((short) 51, "RETRY PENALTY");
		TYPE.put((short) 52, "ILLEGAL TIME GAIN");
		TYPE.put((short) 53, "MANDATORY PITSTOP");
		TYPE.put((short) 54, "ATTRIBUTE ASSIGNED");
	}
}
