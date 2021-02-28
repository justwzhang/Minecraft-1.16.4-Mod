package com.CoinBeast.MyMinecraftMod.items.RareScrolls;

import com.CoinBeast.MyMinecraftMod.MyMinecraftMod;
import com.CoinBeast.MyMinecraftMod.entities.ShadowParalysisEntity;
import com.CoinBeast.MyMinecraftMod.util.KeyboardUtil;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class ShadowParalysisJutsu extends RareScrollBase{
    public ShadowParalysisJutsu() {
        super(new Item.Properties().group(MyMinecraftMod.TAB));
    }

    @Override
    public void useItem(World worldIn, PlayerEntity playerIn, Hand handIn) {
        super.useItem(worldIn, playerIn, handIn);
        ShadowParalysisEntity entity = new ShadowParalysisEntity(playerIn,worldIn);
        Vector3d look = playerIn.getLookVec();
        entity.shoot(look.getX(), look.getY(), look.getZ(), 1000, 0F);
        worldIn.addEntity(entity);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new StringTextComponent("Freeze your enemies in their tracks"));
        if(!KeyboardUtil.isHoldingShift())
            tooltip.add(new StringTextComponent("\u00A7c" + "Hold Shift For More Info"));
        else {
            tooltip.add(new StringTextComponent("\u00A7b" + "Effects"));
            tooltip.add(new StringTextComponent( "Applies slowness 1000 on the entity you are pointing at"));
        }
    }
}
