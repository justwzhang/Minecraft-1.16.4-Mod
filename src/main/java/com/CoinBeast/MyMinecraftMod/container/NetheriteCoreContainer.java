package com.CoinBeast.MyMinecraftMod.container;


import com.CoinBeast.MyMinecraftMod.items.BasicScroll;
import com.CoinBeast.MyMinecraftMod.items.NetheriteCoreTool;
import com.CoinBeast.MyMinecraftMod.items.Susanoo.SusanooSoulBase;
import com.CoinBeast.MyMinecraftMod.util.RegistryHandler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.ClickType;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class NetheriteCoreContainer extends Container{

    private final ItemStack item;
    private final IItemHandler itemHandler;
    private int blocked = -1;

    public NetheriteCoreContainer(int windowID, PlayerInventory playerInventory, PacketBuffer data){
        this(windowID, playerInventory);
    }

    public NetheriteCoreContainer(int windowID, PlayerInventory playerInventory) {
        super(RegistryHandler.NETHERITE_CONTAINER.get(), windowID);
        this.item = getHeldItem(playerInventory.player);
        this.itemHandler = ((NetheriteCoreTool)this.item.getItem()).getInventory(this.item);

        //main Inventory
        this.addSlot(new SlotItemHandler(this.itemHandler, 0,17, 17)); //soul item
        this.addSlot(new SlotItemHandler(this.itemHandler, 1,79, 17)); //main scroll
        this.addSlot(new SlotItemHandler(this.itemHandler, 2,56, 51)); //scroll storage
        this.addSlot(new SlotItemHandler(this.itemHandler, 3,79, 58)); //scroll storage
        this.addSlot(new SlotItemHandler(this.itemHandler, 4,102, 51)); //scroll storage

        //player Inventory
        int startPlayerInvX = 8;
        int startPlayerInvY = 84;
        for(int row = 0; row<3; row++){
            for(int column = 0; column <9; column++){
                this.addSlot(new Slot(playerInventory, 9 + (row * 9) + column, startPlayerInvX+(column * 18),startPlayerInvY +(row * 18)));
            }
        }

        //hotbar
        int hotbarX = 8;
        int hotbarY = 142;
        for(int column =0; column<9; column++){
            Slot slot = addSlot(new Slot(playerInventory, column, hotbarX + (column * 18), hotbarY){
                @Override
                public boolean canTakeStack(PlayerEntity playerIn){return slotNumber != blocked;}
            });

            if(column == playerInventory.currentItem && ItemStack.areItemStacksEqual(playerInventory.getCurrentItem(), this.item)){
                blocked = slot.slotNumber;
            }
        }
    }

    private static ItemStack getHeldItem(PlayerEntity playerIn){
        if(playerIn.getHeldItemMainhand().getItem() instanceof NetheriteCoreTool){
            return playerIn.getHeldItemMainhand();
        }
        if(playerIn.getHeldItemOffhand().getItem() instanceof NetheriteCoreTool){
            return playerIn.getHeldItemOffhand();
        }
        return ItemStack.EMPTY;
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return true;
    }

    @Override
    public void onContainerClosed(PlayerEntity playerIn){
        super.onContainerClosed(playerIn);
        ((NetheriteCoreTool)this.item.getItem()).saveInventory(this.item, this.itemHandler);
    }

    @Override
    public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
        Slot slot = this.getSlot(index);
        if (!slot.canTakeStack(playerIn)) {
            return slot.getStack();
        }
        if (index == blocked || !slot.getHasStack()) {
            return ItemStack.EMPTY;
        }
        if(slot.getStack().getItem() instanceof SusanooSoulBase || slot.getStack().getItem() instanceof  BasicScroll) {
            ItemStack stack = slot.getStack();
            ItemStack newStack = stack.copy();
            int containerSlots = itemHandler.getSlots();
            if (index < containerSlots) {
                if (!this.mergeItemStack(stack, containerSlots, this.inventorySlots.size(), true)) {
                    return ItemStack.EMPTY;
                }
                slot.onSlotChanged();
            } else if (!this.mergeItemStack(stack, 0, 0, false) && stack.getItem() instanceof SusanooSoulBase) {
                return ItemStack.EMPTY;
            }else if(!this.mergeItemStack(stack, 1, containerSlots, false) && stack.getItem() instanceof BasicScroll){
                return ItemStack.EMPTY;
            }
            if (stack.isEmpty()) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }
            return slot.onTake(playerIn, newStack);
        }else{
            return ItemStack.EMPTY;
        }

    }

    @Override
    public ItemStack slotClick(int slotId, int dragType, ClickType clickTypeIn, PlayerEntity player) {
        if (slotId < 0 || slotId > inventorySlots.size()) {
            return super.slotClick(slotId, dragType, clickTypeIn, player);
        }
        Slot slot = inventorySlots.get(slotId);
        if (!canTake(slotId, slot, dragType, player, clickTypeIn)) {
            return slot.getStack();
        }
        return super.slotClick(slotId, dragType, clickTypeIn, player);
    }

    private boolean canTake(int slotId, Slot slot, int button, PlayerEntity player, ClickType clickType) {
        // Hotbar swapping via number keys
        if (clickType == ClickType.SWAP) {
            int hotbarId = itemHandler.getSlots() + 27 + button;
            // Block swapping with container
            if (blocked == hotbarId) {
                return false;
            }

            Slot hotbarSlot = getSlot(hotbarId);
            if (slotId <= itemHandler.getSlots() - 1) {
                return (!isTool(slot.getStack()) && !isTool(hotbarSlot.getStack())) ^
                        (!isScroll(slot.getStack()) && !isScroll(hotbarSlot.getStack()))^
                                (!isSoul(slot.getStack()) && !isSoul(hotbarSlot.getStack()));
            }
        }
        if (slotId == blocked || slotId <= itemHandler.getSlots() - 1 && isTool(player.inventory.getItemStack())) {
            return false;
        }
        if (slotId == 0 && isSoul(slot.getStack()) && (isSoul(player.inventory.getItemStack()) || player.inventory.getItemStack().getItem() == Items.AIR)){
            return true;
        }
        if ((0 < slotId && slotId < 5) && isScroll(slot.getStack())&& (isScroll(player.inventory.getItemStack()) || player.inventory.getItemStack().getItem() == Items.AIR)) {
            return true;
        }
        if (slotId == 0 && !isSoul(player.inventory.getItemStack())) {
            return false;
        }
        if ((0 < slotId && slotId < 5) && !isScroll(player.inventory.getItemStack())) {
            return false;
        }

        return true;
    }

    private static boolean isTool(ItemStack stack) {
        return stack.getItem() instanceof NetheriteCoreTool;
    }

    private static boolean isScroll(ItemStack stack){
        return stack.getItem() instanceof BasicScroll;
    }

    private static boolean isSoul(ItemStack stack){
        return stack.getItem() instanceof SusanooSoulBase;
    }
}
