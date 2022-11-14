/**
 * 
 */
package formula;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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

	private static final Logger log = LogManager.getLogger(F1Parser.class);

	private static F1UdpListener listener;

	public static F1UdpListener getF1UdpListener() {
		return listener;
	}

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

	public static void main(String[] args) throws InterruptedException {

		System.setProperty("log4j.configurationFile", "config/log4j2.xml");

		log.info("=============== START ===============");
		listener = new F1UdpListener();
		new F1Frame();

		
//		Thread.sleep(10000);
		
//		listener.reRunFile("c:/tmp/monza.ser");
//		F1Parser.setWriteFile(true);

		listener.start();
		
	}
}
