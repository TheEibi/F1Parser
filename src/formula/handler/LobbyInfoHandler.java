/**
 * 
 */
package formula.handler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import formula.packets.PacketLobbyInfo;

/**
 * @author reinh
 *
 */
public class LobbyInfoHandler {

	private static final Logger log = LogManager.getLogger(LobbyInfoHandler.class);

	public static void handleLobbyInfo(PacketLobbyInfo argPacketLobbyInfo) {
		log.debug(argPacketLobbyInfo);
	}

	private LobbyInfoHandler() {
	}
}
