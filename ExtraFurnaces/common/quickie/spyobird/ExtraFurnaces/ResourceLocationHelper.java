package quickie.spyobird.ExtraFurnaces;

import net.minecraft.util.ResourceLocation;

public class ResourceLocationHelper
{
    public static ResourceLocation getResourceLocation(String modid, String path)
    {
        return new ResourceLocation(modid, path);
    }
    
    public static ResourceLocation getResourceLocation(String path)
    {
        return getResourceLocation("extrafurnaces", path);
    }
}
