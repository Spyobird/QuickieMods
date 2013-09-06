package quickie.spyobird.ExtraFurnaces;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = "ExtraFurnaces", name = "Extra Furnaces", version = "1.0.0")
@NetworkMod(clientSideRequired = true, serverSideRequired = false, channels = "ExtraFurnaces", packetHandler = EFPacketHandler.class)

public class ExtraFurnaces
{
    @Instance("ExtraFurnaces")
    public static ExtraFurnaces instance;

    private GuiHandler guihandler = new GuiHandler();
    
    public static Block BrickFurnaceIdle;
    public static Block BrickFurnaceActive;
    
    @EventHandler
    public void load(FMLInitializationEvent event)
    {
        BrickFurnaceIdle = new BlockFurnaceBrick(3000, false).setHardness(5.0F).setResistance(10.0F).setUnlocalizedName("BrickFurnaceIdle").setCreativeTab(CreativeTabs.tabDecorations);
        BrickFurnaceActive = new BlockFurnaceBrick(3001, true).setHardness(5.0F).setResistance(10.0F).setUnlocalizedName("BrickFurnaceActive");
        
        GameRegistry.registerBlock(BrickFurnaceIdle, "BrickFurnaceIdle");
        GameRegistry.registerBlock(BrickFurnaceActive, "BrickFurnaceActive");
        
        GameRegistry.registerTileEntity(TileEntityBrickFurnace.class, "tileentitybrickfurnace");
        NetworkRegistry.instance().registerGuiHandler(this, guihandler);
    }
    
    @EventHandler
    public static void load(FMLPostInitializationEvent event)
    {
        LanguageRegistry.addName(BrickFurnaceIdle, "Bloomery");
        LanguageRegistry.addName(BrickFurnaceActive, "Bloomery");
    }
}
