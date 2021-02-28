package com.CoinBeast.MyMinecraftMod.items;

import com.CoinBeast.MyMinecraftMod.MyMinecraftMod;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class ItemBase extends Item {

    public ItemBase() {
        super(new Item.Properties().group(MyMinecraftMod.TAB));
    }
}
