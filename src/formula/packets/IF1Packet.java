/**
 * 
 */
package formula.packets;

import java.nio.ByteBuffer;

/**
 * @author reinh
 *
 */
public interface IF1Packet {

	PacketHeader getPacketHeader();

	void initV22(PacketHeader argPacketHeader, ByteBuffer argBb);
	void initV23(PacketHeader argPacketHeader, ByteBuffer argBb);
	

}
