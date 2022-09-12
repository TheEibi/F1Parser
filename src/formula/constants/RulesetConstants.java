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
public class RulesetConstants {

	public static final Map<Short, String> RULE_SET = new HashMap<>();
	

	static {
		RULE_SET.put((short) 0, "PRACTICE & QUALIFYING");
		RULE_SET.put((short) 1, "RACE");
		RULE_SET.put((short) 2, "TIME TRIAL");
		RULE_SET.put((short) 4, "TIME ATTACK");
		RULE_SET.put((short) 6, "CHECKPOINT CHALLENGE");
		RULE_SET.put((short) 8, "AUTOCROSS");
		RULE_SET.put((short) 9, "DRIFT");
		RULE_SET.put((short) 10, "AVERAGE SPEED ZONE");
		RULE_SET.put((short) 11, "RIVAL DUEL");
	}
}
