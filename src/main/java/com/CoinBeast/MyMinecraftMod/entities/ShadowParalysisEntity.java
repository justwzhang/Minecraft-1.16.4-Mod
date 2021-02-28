package com.CoinBeast.MyMinecraftMod.entities;

import com.CoinBeast.MyMinecraftMod.util.RegistryHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class ShadowParalysisEntity extends ProjectileItemEntity {

    World world;
    LivingEntity player;
    public ShadowParalysisEntity(EntityType<ShadowParalysisEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public ShadowParalysisEntity(double x, double y, double z, World worldIn) {
        super(RegistryHandler.SHADOW_PARALYSIS_ENTITY.get(), x, y, z, worldIn);
    }

    public ShadowParalysisEntity(LivingEntity livingEntityIn, World worldIn) {
        super(RegistryHandler.SHADOW_PARALYSIS_ENTITY.get(), livingEntityIn, worldIn);
        this.world = worldIn;
        this.player = livingEntityIn;
    }

    @Override
    protected void onImpact(RayTraceResult result) {
        if(result.getType() == RayTraceResult.Type.ENTITY){
            if(((EntityRayTraceResult) result).getEntity()  instanceof LivingEntity){
                LivingEntity entity = (LivingEntity) ((EntityRayTraceResult) result).getEntity();
                entity.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 400, 1000));
            }

        }else{
            player.getHeldItemMainhand();
        }
        if(!world.isRemote)
            this.remove();
    }

    @Override
    protected Item getDefaultItem() {
        return null;
    }
}
