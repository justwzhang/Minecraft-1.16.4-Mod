package com.CoinBeast.MyMinecraftMod.items.LegendaryScrolls;

import com.CoinBeast.MyMinecraftMod.MyMinecraftMod;
import com.CoinBeast.MyMinecraftMod.entities.ShadowCloneEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class ShadowCloneJutsu extends LegendaryScrollBase{
    public ShadowCloneJutsu() {
        super(new Item.Properties().group(MyMinecraftMod.TAB));
    }

    @Override
    public void useItem(World worldIn, PlayerEntity playerIn, Hand handIn) {
        super.useItem(worldIn, playerIn, handIn);
        ShadowCloneEntity shadowClone = new ShadowCloneEntity(worldIn, playerIn);
        worldIn.addEntity(shadowClone);
    }
}
