package com.CoinBeast.MyMinecraftMod.entities;

import com.CoinBeast.MyMinecraftMod.util.RegistryHandler;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.AbstractSkeletonEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ShadowCloneEntity extends TameableEntity {

    private PlayerEntity player;

    public ShadowCloneEntity(World worldIn, @Nonnull PlayerEntity playerIn){
        this(RegistryHandler.SHADOW_CLONE.get(), worldIn);
        this.player = playerIn;
    }

    public ShadowCloneEntity(EntityType<? extends TameableEntity> type, World worldIn) {
        super(type, worldIn);
        this.setTamed(true);
        this.setOwnerId(player.getUniqueID());
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.addGoal(2, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new FollowOwnerGoal(this, 1.0D, 10.0F, 2.0F, false));
        this.goalSelector.addGoal(4, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(1, new OwnerHurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new OwnerHurtTargetGoal(this));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, MonsterEntity.class, false));
    }

    public static AttributeModifierMap.MutableAttribute func_234233_eS_() {
        return MobEntity.func_233666_p_().createMutableAttribute(Attributes.MOVEMENT_SPEED, (double)1.0F).createMutableAttribute(Attributes.MAX_HEALTH, 2.0D).createMutableAttribute(Attributes.ATTACK_DAMAGE, 6.0D);
    }

    @Override
    public void setTamed(boolean tamed){
        super.setTamed(true);
        func_234233_eS_();
        this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(2.0D);
        this.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(6.0D);
    }

    @Nullable
    @Override
    public AgeableEntity func_241840_a(ServerWorld p_241840_1_, AgeableEntity p_241840_2_) {
        return null;
    }
}
