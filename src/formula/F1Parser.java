/**
 * 
 */
package formula;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;

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
		Configurator.initialize(null, "config/log4j2.xml");

		log.info("=============== START ===============");
		Properties props = System.getProperties();

		try (InputStream in = new FileInputStream("config/system.properties")) {
			props.load(in);
			props.putAll(System.getProperties());
			System.setProperties(props);
		} catch (IOException ex) {
			log.error(ex);
		}

		listener = new F1UdpListener();
		new F1Frame();

		if (Boolean.parseBoolean((String) props.getOrDefault("data.reRunFile.enabled", "false"))) {
			log.info("Rerun file={}", props.get("data.reRunFile.location"));
			Thread.sleep(Integer.parseInt((String) props.get("data.reRunFile.wait")));
			listener.reRunFile((String) props.get("data.reRunFile.location"));
		} else {
			F1Parser.setWriteFile(
					Boolean.parseBoolean((String) props.getOrDefault("data.writeToFile.enabled", "false")));

			listener.start();
		}

	}
}
