package com.CoinBeast.MyMinecraftMod.items.ForbiddenScrolls;

import com.CoinBeast.MyMinecraftMod.MyMinecraftMod;
import com.CoinBeast.MyMinecraftMod.util.KeyboardUtil;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.potion.Potion;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class EighthGate extends ForbiddenScrollBase{

    public EighthGate() {
        super(new Item.Properties().group(MyMinecraftMod.TAB));
    }

    @Override
    public void useItem(World worldIn, PlayerEntity playerIn, Hand handIn) {
        super.useItem(worldIn, playerIn, handIn);
        playerIn.addPotionEffect(new EffectInstance(Effects.STRENGTH, 6000, 1000));
        playerIn.addPotionEffect(new EffectInstance(Effects.SPEED, 6000, 2));
        playerIn.addPotionEffect(new EffectInstance(Effects.WITHER, 6000, 3));
        playerIn.addPotionEffect(new EffectInstance(Effects.JUMP_BOOST,6000 , 2));
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new StringTextComponent("You will receive great power and speed at the eventual cost of your life"));
        if(!KeyboardUtil.isHoldingShift())
            tooltip.add(new StringTextComponent("\u00A7c" + "Hold Shift For More Info"));
        else {
            tooltip.add(new StringTextComponent("\u00A7b" + "Effects"));
            tooltip.add(new StringTextComponent( "Strength 1000"));
            tooltip.add(new StringTextComponent( "Speed 3"));
            tooltip.add(new StringTextComponent( "Wither 4"));
            tooltip.add(new StringTextComponent( "Jump Boost 3"));
        }
    }
}
