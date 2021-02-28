package com.CoinBeast.MyMinecraftMod.items;

import com.CoinBeast.MyMinecraftMod.MyMinecraftMod;
import com.CoinBeast.MyMinecraftMod.entities.HokageKunaiEntity;
import com.CoinBeast.MyMinecraftMod.entities.KunaiBaseEntity;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

import java.util.List;

public class KunaiBase extends Item {
    private final Multimap<Attribute, AttributeModifier> kunaiAttributes;

    public KunaiBase() {
        super(new Item.Properties().group(MyMinecraftMod.TAB).maxStackSize(16));
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Tool modifier", 4.0D, AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(ATTACK_SPEED_MODIFIER, "Tool modifier", (double)-2.5F, AttributeModifier.Operation.ADDITION));
        this.kunaiAttributes = builder.build();
    }

    public boolean canPlayerBreakBlockWhileHolding(){
        return false;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        if (!worldIn.isRemote) {
            KunaiBaseEntity kunai = new KunaiBaseEntity(playerIn, worldIn);
            Vector3d look = playerIn.getLookVec();
            kunai.setItem(itemstack);
            kunai.shoot(look.x, look.y, look.z, 2F, 0F);
            kunai.isAirBorne = true;
            worldIn.addEntity(kunai);
        }
        playerIn.getCooldownTracker().setCooldown(this, 5);
        playerIn.setActiveHand(handIn);
        if (!playerIn.abilities.isCreativeMode) {
            itemstack.shrink(1);
        }
        return ActionResult.resultSuccess(itemstack);
    }

    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlotType equipmentSlot) {
        return equipmentSlot == EquipmentSlotType.MAINHAND ? this.kunaiAttributes : super.getAttributeModifiers(equipmentSlot);
    }

    public int getItemEnchantability() {
        return 0;
    }
}
