package com.CoinBeast.MyMinecraftMod.entities;


import com.CoinBeast.MyMinecraftMod.util.RegistryHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class KunaiBaseEntity extends ProjectileItemEntity {
    public KunaiBaseEntity(EntityType<KunaiBaseEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public KunaiBaseEntity(double x, double y, double z, World worldIn) {
        super(RegistryHandler.BASE_KUNAI_PROJECTILE.get(), x, y, z, worldIn);
    }

    public KunaiBaseEntity( LivingEntity livingEntityIn, World worldIn) {
        super(RegistryHandler.BASE_KUNAI_PROJECTILE.get(), livingEntityIn, worldIn);
    }

    @Override
    protected Item getDefaultItem() {
        return RegistryHandler.KUNAI.get();
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    public void onImpact(RayTraceResult result){
        this.isAirBorne = false;
        Entity player = this.func_234616_v_();
        ServerPlayerEntity serverplayerentity = (ServerPlayerEntity)player;
        if(result.getType() == RayTraceResult.Type.ENTITY){
            Entity entity = ((EntityRayTraceResult)result).getEntity();
            int damage = 5;
            entity.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getEntity()),damage);
        }
        if(player != null && result.getType() == RayTraceResult.Type.BLOCK && !((ServerPlayerEntity) player).isCreative()){
            this.world.addEntity(new ItemEntity(this.world, this.getPosX(), this.getPosY(), this.getPosZ(), this.getItem()));
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
