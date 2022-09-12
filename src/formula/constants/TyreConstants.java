package formula.constants;

import java.util.HashMap;
import java.util.Map;

public class TyreConstants {

	public static final Map<Short, String> ACTUAL_COMPOUND = new HashMap<>();
	public static final Map<Short, String> VISUAL_COMPOUND = new HashMap<>();

	static {
		ACTUAL_COMPOUND.put((short) 7, "INTERMEDIATE");	//F1 Modern
		ACTUAL_COMPOUND.put((short) 8, "WET");			//F1 Modern
		ACTUAL_COMPOUND.put((short) 9, "DRY");			//F1 Classic
		ACTUAL_COMPOUND.put((short) 10, "WET");			//F1 Classic
		ACTUAL_COMPOUND.put((short) 11, "SUPER SOFT");	//F2
		ACTUAL_COMPOUND.put((short) 12, "SOFT");		//F2
		ACTUAL_COMPOUND.put((short) 13, "MEDIUM");		//F2
		ACTUAL_COMPOUND.put((short) 14, "HARD");		//F2
		ACTUAL_COMPOUND.put((short) 15, "WET");			//F2
		ACTUAL_COMPOUND.put((short) 16, "C5");			//F1 Modern
		ACTUAL_COMPOUND.put((short) 17, "C4");			//F1 Modern
		ACTUAL_COMPOUND.put((short) 18, "C3");			//F1 Modern
		ACTUAL_COMPOUND.put((short) 19, "C2");			//F1 Modern
		ACTUAL_COMPOUND.put((short) 20, "C1");			//F1 Modern

		VISUAL_COMPOUND.put((short) 7, "INTERMEDIATE");	//F1 Modern/Classic
		VISUAL_COMPOUND.put((short) 8, "WET");			//F1 Modern/Classic
		VISUAL_COMPOUND.put((short) 15, "WET");			//F2
		VISUAL_COMPOUND.put((short) 16, "SOFT");		//F1 Modern/Classic
		VISUAL_COMPOUND.put((short) 17, "MEDIUM");		//F1 Modern/Classic
		VISUAL_COMPOUND.put((short) 18, "HARD");		//F1 Modern/Classic
		VISUAL_COMPOUND.put((short) 19, "SUPER SOFT");	//F2
		VISUAL_COMPOUND.put((short) 20, "SOFT");		//F2
		VISUAL_COMPOUND.put((short) 21, "MEDIUM");		//F2
		VISUAL_COMPOUND.put((short) 22, "HARD");		//F2
	}
}
