package com.CoinBeast.MyMinecraftMod.items.CommonScrolls;

import com.CoinBeast.MyMinecraftMod.items.BasicScroll;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class CommonScrollBase extends BasicScroll {
    public int cooldown = 15;

    public CommonScrollBase(Properties properties) {
        super(properties);
    }

    @Override
    public void useItem(World worldIn, PlayerEntity playerIn, Hand handIn) {
        super.useItem(worldIn, playerIn, handIn);
    }
    public int getCooldown(){return this.cooldown;}
}
