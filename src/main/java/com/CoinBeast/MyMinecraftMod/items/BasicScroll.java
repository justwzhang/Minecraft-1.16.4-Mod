package com.CoinBeast.MyMinecraftMod.items;

import net.minecraft.client.GameSettings;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class BasicScroll extends Item {
    public int cooldown = 15;

    public GameSettings gs = Minecraft.getInstance().gameSettings;

    public BasicScroll(Properties properties) {
        super(properties);
    }


    public void useItem(World worldIn, PlayerEntity playerIn, Hand handIn){

    }
    public int getCooldown(){return this.cooldown;}

}
