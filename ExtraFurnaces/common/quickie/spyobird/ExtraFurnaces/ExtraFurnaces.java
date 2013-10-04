package quickie.spyobird.ExtraFurnaces;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.EnumHelper;
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
    
    public static Item IngotSteel;
    public static Item SwordSteel;
    
    public final EnumToolMaterial Steel = EnumHelper.addToolMaterial("Steel", 2, 850, 6.5F, 3.0F, 7);
    
    @EventHandler
    public void load(FMLInitializationEvent event)
    {
        BrickFurnaceIdle = new BlockFurnaceBrick(3000, false).setHardness(5.0F).setResistance(10.0F).setUnlocalizedName("BrickFurnaceIdle").setCreativeTab(CreativeTabs.tabDecorations);
        BrickFurnaceActive = new BlockFurnaceBrick(3001, true).setHardness(5.0F).setResistance(10.0F).setUnlocalizedName("BrickFurnaceActive");
        
        GameRegistry.registerBlock(BrickFurnaceIdle, "BrickFurnaceIdle");
        GameRegistry.registerBlock(BrickFurnaceActive, "BrickFurnaceActive");
        
        //Bloomery
        GameRegistry.addRecipe(new ItemStack(ExtraFurnaces.BrickFurnaceIdle, 1), new Object[]
        {
        "###",
        "# #",
        "###",
        '#', Block.brick
        });
        GameRegistry.addRecipe(new ItemStack(ExtraFurnaces.BrickFurnaceIdle, 1), new Object[]
        {
        "YYY",
        "YXY",
        "###",
        '#', Block.brick, 'X', Block.furnaceIdle, 'Y', Item.brick
        });
 
        IngotSteel = new IngotSteel(6004);
        SwordSteel = new ItemModSword(6005, this.Steel).setUnlocalizedName("SwordSteel");
        
        GameRegistry.registerItem(IngotSteel, "IngotSteel");    
        GameRegistry.registerItem(SwordSteel, "SwordSteel");    
        
        //Steel Sword
        GameRegistry.addRecipe(new ItemStack(ExtraFurnaces.SwordSteel, 1), new Object[]
        {
        "X",
        "X",    
        "Y",
        'X', ExtraFurnaces.IngotSteel, 'Y', Item.stick
        });        
           
        GameRegistry.registerTileEntity(TileEntityBrickFurnace.class, "tileentitybrickfurnace");
        NetworkRegistry.instance().registerGuiHandler(this, guihandler);
    }
    
    @EventHandler
    public static void load(FMLPostInitializationEvent event)
    {
        LanguageRegistry.addName(BrickFurnaceIdle, "Bloomery");
        LanguageRegistry.addName(BrickFurnaceActive, "Bloomery");
        
        LanguageRegistry.addName(IngotSteel, "Steel Ingot");
        LanguageRegistry.addName(SwordSteel, "Steel Sword");
    }
}