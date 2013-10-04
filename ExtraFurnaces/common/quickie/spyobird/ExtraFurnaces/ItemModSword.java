package quickie.spyobird.ExtraFurnaces;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemSword;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemModSword extends ItemSword
{

    public ItemModSword(int id, EnumToolMaterial Material)
    {
        super(id, Material);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister) 
    {
        itemIcon = iconRegister.registerIcon("extrafurnaces" + ":" + this.getUnlocalizedName().substring(5));
    } 
}
