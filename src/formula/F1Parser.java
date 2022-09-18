/**
 * 
 */
package formula;

import java.io.IOException;

/**
 * @author reinh
 *
 *         uint8 = short get() uint16 = short getShort() uint32 = int getInt()
 *         uint64 = long getLong() float = float getFloat()
 * 
 */
public class F1Parser {

	private static boolean writeOnce = true;

	private static boolean writeFile = false;

	public static boolean isWriteOnce() {
		return writeOnce;
	}

	public static void setWriteOnce(boolean argWriteOnce) {
		writeOnce = argWriteOnce;
	}

	public static void setWriteFile(boolean argWriteFile) {
		writeFile = argWriteFile;
	}

	public static boolean isWriteFile() {
		return writeFile;
	}

	public static void main(String[] args) throws IOException, ClassNotFoundException {
//		F1UdpListener listener = new F1UdpListener();
//		F1Frame frame = new F1Frame();

		F1DataHelper.readSingleSerFile("c:/tmp/-2372758205150449044.ser");
//		F1Parser.setWriteFile(true);

//		listener.start();
	}
}
