package com.CoinBeast.MyMinecraftMod.items.Susanoo;

import com.CoinBeast.MyMinecraftMod.MyMinecraftMod;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import java.util.List;

public class OrangeSoul extends SusanooSoulBase{
    private static Iterable<ItemStack> originalArmor;
    //private final Iterable<ItemStack> SUSANOO;

    public OrangeSoul() {
        super(new Item.Properties().group(MyMinecraftMod.TAB));

    }

    @Override
    public void useItem(World worldIn, PlayerEntity playerIn, Hand handIn) {
        super.useItem(worldIn, playerIn, handIn);
        originalArmor = playerIn.getArmorInventoryList();
    }
}
