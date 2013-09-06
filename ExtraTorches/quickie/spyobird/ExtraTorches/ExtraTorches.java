package quickie.spyobird.ExtraTorches;

import net.minecraft.block.Block;
import net.minecraft.block.BlockGlowStone;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = "ExtraTorches", name = "Extra Torches", version = "1.0.0")
@NetworkMod(clientSideRequired = true, serverSideRequired = false)

public class ExtraTorches
{
    @Instance("ExtraTorches")
    public static ExtraTorches instance;
    
    public static Block GlowstoneTorch;
    
    @EventHandler
    public void load(FMLInitializationEvent event)
    {
        GlowstoneTorch = new BlockGlowstoneTorch(2048);
        
        GameRegistry.registerBlock(GlowstoneTorch, "GlowstoneTorch");
    }
    
    @EventHandler
    public static void load(FMLPostInitializationEvent event)
    {
        LanguageRegistry.addName(GlowstoneTorch, "Glowstone Torch");
    }
}
