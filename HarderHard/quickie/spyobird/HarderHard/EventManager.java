package quickie.spyobird.HarderHard;

import java.lang.reflect.Field;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.FoodStats;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;

public class EventManager
{
        @ForgeSubscribe
        public void updateFood(EntityJoinWorldEvent event)
        {
            if(event.entity instanceof EntityPlayer)
                setFoodStats((EntityPlayer) event.entity, new FoodStatsMod());
        }

        public static void setFoodStats(EntityPlayer player, FoodStats stats)
        {
            Field[] fields = EntityPlayer.class.getDeclaredFields();

            for (Field f : fields)
            {
                if (f.getType() == FoodStats.class)
                {
                    f.setAccessible(true);
                    try
                    {
                        f.set(player, stats);
                    }
                    catch (Exception e)
                    {
                        System.out.println(e);
                    }
                }
            }
        }
}