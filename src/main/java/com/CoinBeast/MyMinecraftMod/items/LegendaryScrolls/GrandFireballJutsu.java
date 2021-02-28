package com.CoinBeast.MyMinecraftMod.items.LegendaryScrolls;

import com.CoinBeast.MyMinecraftMod.MyMinecraftMod;
import com.CoinBeast.MyMinecraftMod.util.KeyboardUtil;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class GrandFireballJutsu extends LegendaryScrollBase {

    public GrandFireballJutsu() {
        super(new Item.Properties().group(MyMinecraftMod.TAB));
    }

    @Override
    public void useItem(World worldIn, PlayerEntity playerIn, Hand handIn) {
        super.useItem(worldIn, playerIn, handIn);
        FireballEntity fireball = new FireballEntity(worldIn, playerIn, 1,1,1);
        Vector3d look = playerIn.getLookVec();
        fireball.setPosition(playerIn.getPosX()+look.x * 1.5, playerIn.getPosY()+look.y * 2.5, playerIn.getPosZ()+look.z * 1.5);
        fireball.accelerationX = look.x/16;
        fireball.accelerationY = look.y/16;
        fireball.accelerationZ = look.z/16;
        fireball.explosionPower = 30;
        worldIn.addEntity(fireball);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new StringTextComponent("Fire ball jutsu's older brother"));
        if(!KeyboardUtil.isHoldingShift())
            tooltip.add(new StringTextComponent("\u00A7c" + "Hold Shift For More Info"));
        else {
            tooltip.add(new StringTextComponent("\u00A7b" + "Effects"));
            tooltip.add(new StringTextComponent( "Launches a projectile that has a medium sized explosion"));
        }
    }
}
