package quickie.spyobird.ExtraFurnaces;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

public class EFPacketHandler implements IPacketHandler
{
    @Override
    public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player)
    {
        if (packet.channel.equals("ExtraFurnaces"))
        {
            handlePacket(packet);
        }
    }
    
    public void handlePacket(Packet250CustomPayload packet)
    {
        DataInputStream inputStream = new DataInputStream(new ByteArrayInputStream(packet.data));
        
        int rand1;
        int rand2;
        
        try
        {
            rand1 = inputStream.readInt();
            rand2 = inputStream.readInt();
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return;
        }
        
        System.out.println(rand1 + "" + rand2);
    }
}
