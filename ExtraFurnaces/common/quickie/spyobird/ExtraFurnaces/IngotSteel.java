package quickie.spyobird.ExtraFurnaces;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class IngotSteel extends Item
{
    public IngotSteel(int id)
    {
        super(id);
        this.setUnlocalizedName("IngotSteel");
        this.setCreativeTab(CreativeTabs.tabMaterials);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister) 
    {
        itemIcon = iconRegister.registerIcon("extrafurnaces" + ":" + "IngotSteel");
    }       
}
