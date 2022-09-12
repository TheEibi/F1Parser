package formula.packets;

import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class PacketLobbyInfo  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4262555177542962268L;
	private PacketHeader packetHeader;
	private short numPlayers;
	private List<LobbyInfoData> lobbyInfoDataList;

	public PacketLobbyInfo(PacketHeader argPacketHeader, ByteBuffer argBb) {
		packetHeader = argPacketHeader;
		numPlayers = argBb.get();
		lobbyInfoDataList = new ArrayList<>();
		for (int i = 0; i < 22; i++) {
			lobbyInfoDataList.add(new LobbyInfoData(argBb));
		}
	}

	public PacketHeader getPacketHeader() {
		return packetHeader;
	}

	public short getNumPlayers() {
		return numPlayers;
	}

	public List<LobbyInfoData> getLobbyInfoDataList() {
		return lobbyInfoDataList;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getPacketHeader());
		sb.append("::");
		sb.append(getClass().getSimpleName());
		sb.append("::");
		sb.append(Byte.toUnsignedInt((byte) getNumPlayers()));
		for (LobbyInfoData lobbyInfoData : lobbyInfoDataList) {
			sb.append("::");
			sb.append(lobbyInfoData);
		}
		return sb.toString();
	}
}
