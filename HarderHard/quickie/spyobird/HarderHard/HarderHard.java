package quickie.spyobird.HarderHard;

import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;

@Mod(modid = "HarderHard", name = "Harder Hard", version = "1.0.0")
@NetworkMod(clientSideRequired = true, serverSideRequired = false)

public class HarderHard
{
    @Instance("HarderHard")
    public static HarderHard instance;
    
    EventManager eventmanager = new EventManager();
    
    @EventHandler
    public void load(FMLInitializationEvent event)
    {
         MinecraftForge.EVENT_BUS.register(eventmanager);   
    }
}
