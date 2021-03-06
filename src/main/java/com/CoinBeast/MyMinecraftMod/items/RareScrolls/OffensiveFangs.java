package com.CoinBeast.MyMinecraftMod.items.RareScrolls;

import com.CoinBeast.MyMinecraftMod.MyMinecraftMod;
import com.CoinBeast.MyMinecraftMod.util.KeyboardUtil;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.EvokerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.EvokerFangsEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class OffensiveFangs extends RareScrollBase{

    public OffensiveFangs() {
        super(new Item.Properties().group(MyMinecraftMod.TAB));
    }

    @Override
    public void useItem(World worldIn, PlayerEntity playerIn, Hand handIn) {
        super.useItem(worldIn, playerIn, handIn);
        assert Minecraft.getInstance().objectMouseOver != null;
        Vector3d look = Minecraft.getInstance().objectMouseOver.getHitVec();
        double d0 = Math.min(look.getY(), playerIn.getPosY());
        double d1 = Math.max(look.getY(), playerIn.getPosY()) + 1.0D;
        float f = (float)MathHelper.atan2(look.getZ() - playerIn.getPosZ(), look.getX() - playerIn.getPosX());
        for(int l = 0; l < 16; ++l) {
            double d2 = 1.25D * (double)(l + 1);
            int j = 1 * l;
            this.spawnFangs(playerIn, playerIn.getPosX() + (double)MathHelper.cos(f) * d2, playerIn.getPosZ() + (double)MathHelper.sin(f) * d2, d0, d1, f, j);
        }
    }

    private void spawnFangs(PlayerEntity player, double p_190876_1_, double p_190876_3_, double p_190876_5_, double p_190876_7_, float p_190876_9_, int p_190876_10_) {
        BlockPos blockpos = new BlockPos(p_190876_1_, p_190876_7_, p_190876_3_);
        boolean flag = false;
        double d0 = 0.0D;

        do {
            BlockPos blockpos1 = blockpos.down();
            BlockState blockstate =player.world.getBlockState(blockpos1);
            if (blockstate.isSolidSide(player.world, blockpos1, Direction.UP)) {
                if (!player.world.isAirBlock(blockpos)) {
                    BlockState blockstate1 = player.world.getBlockState(blockpos);
                    VoxelShape voxelshape = blockstate1.getCollisionShape(player.world, blockpos);
                    if (!voxelshape.isEmpty()) {
                        d0 = voxelshape.getEnd(Direction.Axis.Y);
                    }
                }

                flag = true;
                break;
            }

            blockpos = blockpos.down();
        } while(blockpos.getY() >= MathHelper.floor(p_190876_5_) - 1);

        if (flag) {
            player.world.addEntity(new EvokerFangsEntity(player.world, p_190876_1_, (double)blockpos.getY() + d0, p_190876_3_, p_190876_9_, p_190876_10_, player));
        }

    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new StringTextComponent("Attack your enemies with rising fangs"));
        if(!KeyboardUtil.isHoldingShift())
            tooltip.add(new StringTextComponent("\u00A7c" + "Hold Shift For More Info"));
        else {
            tooltip.add(new StringTextComponent("\u00A7b" + "Effects"));
            tooltip.add(new StringTextComponent( "Summons fangs from the ground that travels in the direction of where you are looking"));
        }
    }
}
