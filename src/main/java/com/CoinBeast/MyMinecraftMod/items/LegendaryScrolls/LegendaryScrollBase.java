package com.CoinBeast.MyMinecraftMod.items.LegendaryScrolls;

import com.CoinBeast.MyMinecraftMod.items.BasicScroll;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class LegendaryScrollBase extends BasicScroll {
    public int cooldown = 30;

    public LegendaryScrollBase(Properties properties) {
        super(properties);
    }

    @Override
    public void useItem(World worldIn, PlayerEntity playerIn, Hand handIn) {
        super.useItem(worldIn, playerIn, handIn);
    }

    public int getCooldown(){return this.cooldown;}
}
