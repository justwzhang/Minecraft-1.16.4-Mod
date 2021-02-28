package com.CoinBeast.MyMinecraftMod.items;

import com.CoinBeast.MyMinecraftMod.MyMinecraftMod;
import com.CoinBeast.MyMinecraftMod.container.NetheriteCoreContainer;
import com.CoinBeast.MyMinecraftMod.util.KeyboardUtil;
import net.minecraft.client.GameSettings;
import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.SimpleNamedContainerProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class NetheriteCoreTool extends Item {

    public static GameSettings gs = Minecraft.getInstance().gameSettings;

    public NetheriteCoreTool() {
        super(new Item.Properties().group(MyMinecraftMod.TAB).maxStackSize(1));
    }

    public int getInventorySize(ItemStack stack){
        return 5;
    }

    public IItemHandler getInventory(ItemStack stack){
        ItemStackHandler stackHandler = new ItemStackHandler(getInventorySize(stack));
        stackHandler.deserializeNBT(stack.getOrCreateTag().getCompound("Inventory"));
        return stackHandler;
    }

    public void saveInventory(ItemStack stack, IItemHandler itemHandler){
        if(itemHandler instanceof  ItemStackHandler){
            stack.getOrCreateTag().put("Inventory", ((ItemStackHandler) itemHandler).serializeNBT());
        }
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, @Nonnull PlayerEntity playerIn, @Nonnull Hand handIn) {
        if(!worldIn.isRemote){
            if(gs.keyBindSneak.isKeyDown()){
                playerIn.openContainer(new SimpleNamedContainerProvider((id, playerInventory, player) -> new NetheriteCoreContainer(id, playerInventory),
                        new TranslationTextComponent("Ninja Tool")
                ));
            }else{
                ItemStack item = playerIn.getHeldItemMainhand();
                if(item.getItem() instanceof NetheriteCoreTool){
                    IItemHandler itemHandler = getInventory(item);
                    ItemStack itemStack = itemHandler.getStackInSlot(1);

                    if (itemStack.getItem() instanceof BasicScroll) {
                        ((BasicScroll) itemStack.getItem()).useItem(worldIn, playerIn, handIn);
                        playerIn.getCooldownTracker().setCooldown(this, ((BasicScroll) itemStack.getItem()).getCooldown());
                        if(!playerIn.isCreative())
                            itemStack.shrink(1);
                        this.saveInventory(playerIn.getHeldItemMainhand(), itemHandler);
                    }

                }
            }
        }
        return new ActionResult<>(ActionResultType.SUCCESS, playerIn.getHeldItem(handIn));
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new StringTextComponent("A tool to use jutsu scrolls"));
        if(!KeyboardUtil.isHoldingShift())
            tooltip.add(new StringTextComponent("\u00A7c" + "Hold Shift For More Info"));
        else {
            tooltip.add(new StringTextComponent("\u00A7b" + "Effects"));
            tooltip.add(new StringTextComponent( "Shift and click to open inventory"));
            tooltip.add(new StringTextComponent( "Click to use scroll in the top center slot"));
        }
    }
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, CompoundNBT nbt) {
        return null;
    }
}
