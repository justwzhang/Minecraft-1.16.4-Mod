package com.CoinBeast.MyMinecraftMod.armor;

import com.CoinBeast.MyMinecraftMod.MyMinecraftMod;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.function.Supplier;

public enum ModArmorMaterial implements IArmorMaterial {

    SUSANOO(MyMinecraftMod.MOD_ID + ":orange_susano", 1000, new int[] {500, 700, 600, 500}, 18,
            SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 30F, null);


    private static final int[] MAX_DAMAGE = new int[]{11, 16, 15, 13};
    private final String name;
    private final int maxDamageFactor; //multiply with MAX_DAMAGE
    private final int[] damageReductionAmount;
    private final int enchantability;
    private final SoundEvent soundEvent;
    private final float TOUGHNESS;
    private final Supplier<Ingredient> repairMaterial;

    ModArmorMaterial(String name, int maxDamageFactor, int[] damageReductionAmount, int enchantability, SoundEvent soundEvent, float TOUGHNESS, Supplier<Ingredient> repairMaterial){
        this.name = name;
        this.maxDamageFactor = maxDamageFactor;
        this.damageReductionAmount = damageReductionAmount;
        this.enchantability = enchantability;
        this.soundEvent = soundEvent;
        this.TOUGHNESS = TOUGHNESS;
        this.repairMaterial = repairMaterial;
    }


    @Override
    public int getDurability(EquipmentSlotType slotIn) {
        return 0;
    }

    @Override
    public int getDamageReductionAmount(EquipmentSlotType slotIn) {
        return this.damageReductionAmount[slotIn.getIndex()];
    }

    @Override
    public int getEnchantability() {
        return this.enchantability;
    }

    @Override
    public SoundEvent getSoundEvent() {
        return this.soundEvent;
    }

    @Override
    public Ingredient getRepairMaterial() {
        return null;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public float getToughness() {
        return this.TOUGHNESS;
    }

    @Override
    public float getKnockbackResistance() {
        return 1000;
    }
}
