package quickie.spyobird.HarderHard;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.FoodStats;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class FoodStatsMod extends FoodStats
{
    /** The player's food level. */
    private int foodLevel = 12;

    /** The player's food saturation. */
    private float foodSaturationLevel = 2.5F;
    
    /** The player's food exhaustion. */
    private float foodExhaustionLevel;

    /** The player's food timer value. */
    private int foodTimer;
    private int prevFoodLevel = 20;

    /**
     * Args: int foodLevel, float foodSaturationModifier
     */
    public void addStats(int par1, float par2)
    {
        this.foodLevel = Math.min(par1 + this.foodLevel, 20);
        this.foodSaturationLevel = Math.min(this.foodSaturationLevel + (float)par1 * par2 * 1.0F, (float)this.foodLevel);
    }

    /**
     * Eat some food.
     */
    public void addStats(ItemFood par1ItemFood)
    {
        this.addStats(par1ItemFood.getHealAmount(), par1ItemFood.getSaturationModifier());
    }

    /**
     * Handles the food game logic.
     */
    public void onUpdate(EntityPlayer par1EntityPlayer)
    {
        int i = par1EntityPlayer.worldObj.difficultySetting;
        this.prevFoodLevel = this.foodLevel;
        
        if (this.foodExhaustionLevel > 1.0F)
        {
            this.foodExhaustionLevel -= 6.4F;
            
            if (this.foodSaturationLevel > 0.0F)
            {
                this.foodSaturationLevel = Math.max(this.foodSaturationLevel - 2.0F, 0.0F);
                System.out.println(foodSaturationLevel);
            }
            else
            {
                this.foodLevel = Math.max(this.foodLevel - 1, 0);
            }
        }

        if (par1EntityPlayer.worldObj.getGameRules().getGameRuleBooleanValue("naturalRegeneration") && this.foodLevel >= 19 && par1EntityPlayer.shouldHeal())
        {
            ++this.foodTimer;

            if (this.foodTimer >= 320)
            {
                par1EntityPlayer.heal(2.0F);
                this.addExhaustion(12.0F);
                this.foodTimer = 0;
            }
        }
        else if (this.foodLevel <= 0)
        {
            ++this.foodTimer;

            if (this.foodTimer >= 40)
            {
                if (par1EntityPlayer.func_110143_aJ() > 10.0F || i >= 3 || par1EntityPlayer.func_110143_aJ() > 2.0F && i >= 2)
                {
                    par1EntityPlayer.attackEntityFrom(DamageSource.starve, 5.0F);
                }

                this.foodTimer = 0;
            }
        }
        else
        {
            this.foodTimer = 0;
        }
    }

    /**
     * Reads food stats from an NBT object.
     */
    public void readNBT(NBTTagCompound par1NBTTagCompound)
    {
        if (par1NBTTagCompound.hasKey("foodLevel"))
        {
            this.foodLevel = par1NBTTagCompound.getInteger("foodLevel");
            this.foodTimer = par1NBTTagCompound.getInteger("foodTickTimer");
            this.foodSaturationLevel = par1NBTTagCompound.getFloat("foodSaturationLevel");
            this.foodExhaustionLevel = par1NBTTagCompound.getFloat("foodExhaustionLevel");
        }
    }

    /**
     * Writes food stats to an NBT object.
     */
    public void writeNBT(NBTTagCompound par1NBTTagCompound)
    {
        par1NBTTagCompound.setInteger("foodLevel", this.foodLevel);
        par1NBTTagCompound.setInteger("foodTickTimer", this.foodTimer);
        par1NBTTagCompound.setFloat("foodSaturationLevel", this.foodSaturationLevel);
        par1NBTTagCompound.setFloat("foodExhaustionLevel", this.foodExhaustionLevel);
    }

    /**
     * Get the player's food level.
     */
    public int getFoodLevel()
    {
        return this.foodLevel;
    }

    @SideOnly(Side.CLIENT)
    public int getPrevFoodLevel()
    {
        return this.prevFoodLevel;
    }

    /**
     * If foodLevel is not max.
     */
    public boolean needFood()
    {
        return this.foodLevel < 20;
    }   

    /**
     * adds input to foodExhaustionLevel to a max of 40
     */
    public void addExhaustion(float par1)
    {
        this.foodExhaustionLevel = Math.min(this.foodExhaustionLevel + par1, 20.0F);
    }

    /**
     * Get the player's food saturation level.
     */
    public float getSaturationLevel()
    {
        return this.foodSaturationLevel;
    }

    @SideOnly(Side.CLIENT)
    public void setFoodLevel(int par1)
    {
        this.foodLevel = par1;
    }

    @SideOnly(Side.CLIENT)
    public void setFoodSaturationLevel(float par1)
    {
        this.foodSaturationLevel = par1;
    }
}