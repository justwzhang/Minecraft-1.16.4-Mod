package com.CoinBeast.MyMinecraftMod.entities;

import com.CoinBeast.MyMinecraftMod.MyMinecraftMod;
import com.CoinBeast.MyMinecraftMod.util.RegistryHandler;
import net.java.games.input.Mouse;
import net.minecraft.client.GameSettings;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.network.NetworkHooks;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class HokageKunaiEntity extends ProjectileItemEntity {
    private LivingEntity entity;

    public HokageKunaiEntity(EntityType<HokageKunaiEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public HokageKunaiEntity(double x, double y, double z, World worldIn) {
        super(RegistryHandler.HOKAGE_KUNAI_PROJECTILE.get(), x, y, z, worldIn);
    }

    public HokageKunaiEntity(LivingEntity livingEntityIn, World worldIn) {
        super(RegistryHandler.HOKAGE_KUNAI_PROJECTILE.get(), livingEntityIn, worldIn);
        entity = livingEntityIn;
    }
    public LivingEntity getLivingEntity(){return entity;}
    @Override
    protected Item getDefaultItem() {
        return RegistryHandler.HOKAGEKUNAI.get();
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    protected void onImpact(RayTraceResult result){
        this.isAirBorne = false;
        Entity player = this.func_234616_v_();
        ServerPlayerEntity serverplayerentity = (ServerPlayerEntity)player;
        net.minecraftforge.event.entity.living.EnderTeleportEvent event = new net.minecraftforge.event.entity.living.EnderTeleportEvent(serverplayerentity, this.getPosX(), this.getPosY(), this.getPosZ(), 5.0F);
        if(result.getType() == RayTraceResult.Type.ENTITY){
            Entity entity = ((EntityRayTraceResult)result).getEntity();
            int damage = 10;
            entity.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getEntity()),damage);
        }
        if(player != null && result.getType() == RayTraceResult.Type.BLOCK){
            player.setPositionAndUpdate(event.getTargetX(), event.getTargetY(), event.getTargetZ());
        }
        if(!world.isRemote){
            this.remove();
            isAirBorne = false;
        }
    }

    @Override
    public Entity getEntity() {
        return null;
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {

    }

    @Override
    public CompoundNBT serializeNBT() {
        return null;
    }

    @Override
    public boolean shouldRiderSit() {
        return false;
    }

    @Override
    public ItemStack getPickedResult(RayTraceResult target) {
        return null;
    }

    @Override
    public boolean canRiderInteract() {
        return false;
    }

    @Override
    public boolean canBeRiddenInWater(Entity rider) {
        return false;
    }

    @Override
    public EntityClassification getClassification(boolean forSpawnCount) {
        return null;
    }
}
