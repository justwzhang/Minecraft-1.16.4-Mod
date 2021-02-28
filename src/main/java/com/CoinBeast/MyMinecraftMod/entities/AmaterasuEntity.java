package com.CoinBeast.MyMinecraftMod.entities;

import com.CoinBeast.MyMinecraftMod.items.LegendaryScrolls.Amaterasu;
import com.CoinBeast.MyMinecraftMod.util.RegistryHandler;
import net.minecraft.block.AbstractFireBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class AmaterasuEntity extends ProjectileItemEntity {

    World world;
    LivingEntity player;
    public AmaterasuEntity(EntityType<AmaterasuEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public AmaterasuEntity(double x, double y, double z, World worldIn) {
        super(RegistryHandler.AMATERASU_ENTITY.get(), x, y, z, worldIn);
    }

    public AmaterasuEntity(LivingEntity livingEntityIn, World worldIn) {
        super(RegistryHandler.AMATERASU_ENTITY.get(), livingEntityIn, worldIn);
        this.world = worldIn;
        this.player = livingEntityIn;
    }

    @Override
    protected Item getDefaultItem() {
        return null;
    }

    @Override
    protected void onImpact(RayTraceResult result) {
        if(result.getType() == RayTraceResult.Type.ENTITY){
            Entity mob = ((EntityRayTraceResult)result).getEntity();
            mob.setFire(10000);
        }
        if(result.getType() == RayTraceResult.Type.BLOCK){
            BlockPos pos = ((BlockRayTraceResult)result).getPos();
            BlockPos pos2 = pos.offset(((BlockRayTraceResult)result).getFace());
            world.setBlockState(pos2, AbstractFireBlock.getFireForPlacement(world, pos2));
        }
        if(!world.isRemote) {
            this.remove();
        }
    }
}
