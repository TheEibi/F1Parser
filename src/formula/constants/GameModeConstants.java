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
public class GameModeConstants {

	public static final Map<Short, String> GAME_MODE = new HashMap<>();
	

	static {
		GAME_MODE.put((short) 0, "EVENT MODE");
		GAME_MODE.put((short) 3, "GRAND PRIX");
		GAME_MODE.put((short) 5, "TIME TRIAL");
		GAME_MODE.put((short) 6, "SPLITSCREEN");
		GAME_MODE.put((short) 7, "ONLINE CUSTOM");
		GAME_MODE.put((short) 8, "ONLINE LEAGUE");
		GAME_MODE.put((short) 11, "CAREER INVITATIONAL");
		GAME_MODE.put((short) 12, "CHAMPIONSHIP INVITATIONAL");
		GAME_MODE.put((short) 13, "CHAMPIONSHIP");
		GAME_MODE.put((short) 14, "ONLINE CHAMPIONSHIP");
		GAME_MODE.put((short) 15, "ONLINE WEEKLY EVENT");
		GAME_MODE.put((short) 19, "CARRER \'22");
		GAME_MODE.put((short) 20, "CARRER \'22 ONLINE");
		GAME_MODE.put((short) 127, "BENCHMARK");
	}
}
