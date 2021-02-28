package com.CoinBeast.MyMinecraftMod.items;

import com.CoinBeast.MyMinecraftMod.MyMinecraftMod;
import com.CoinBeast.MyMinecraftMod.entities.HokageKunaiEntity;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.sun.javafx.geom.Vec3d;
import net.java.games.input.Event;
import net.minecraft.block.BlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IEntityReader;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import java.io.IOException;
import java.util.List;

public class HokageKunai extends Item {
    private final Multimap<Attribute, AttributeModifier> kunaiAttributes;
    private boolean isInFlight = false;

    public HokageKunai() {
        super(new Item.Properties().group(MyMinecraftMod.TAB).maxDamage(4));
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Tool modifier", 10.0D, AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(ATTACK_SPEED_MODIFIER, "Tool modifier", (double)-1.9F, AttributeModifier.Operation.ADDITION));
        this.kunaiAttributes = builder.build();
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        if (!worldIn.isRemote) {
            HokageKunaiEntity kunai = new HokageKunaiEntity(playerIn, worldIn);
            AxisAlignedBB scan = new AxisAlignedBB(playerIn.getPosX()-20, playerIn.getPosY()-20,playerIn.getPosZ()-20,playerIn.getPosX()+20, playerIn.getPosY()+20,playerIn.getPosZ()+20);
            Vector3d look = playerIn.getLookVec();
            kunai.setItem(itemstack);
            kunai.shoot(look.x, look.y, look.z, 2F, 0F);
            kunai.isAirBorne = true;
            worldIn.addEntity(kunai);
            PlayerInteractEvent.RightClickEmpty event = new PlayerInteractEvent.RightClickEmpty(playerIn, handIn);
        }
        playerIn.getCooldownTracker().setCooldown(this, 25);
        playerIn.setActiveHand(handIn);
        return ActionResult.resultSuccess(itemstack);
    }

    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlotType equipmentSlot) {
        return equipmentSlot == EquipmentSlotType.MAINHAND ? this.kunaiAttributes : super.getAttributeModifiers(equipmentSlot);
    }

    public int getItemEnchantability() {
        return 0;
    }
}
