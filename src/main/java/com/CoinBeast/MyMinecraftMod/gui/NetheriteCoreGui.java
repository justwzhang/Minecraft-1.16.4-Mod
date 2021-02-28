package com.CoinBeast.MyMinecraftMod.gui;

import com.CoinBeast.MyMinecraftMod.MyMinecraftMod;
import com.CoinBeast.MyMinecraftMod.container.NetheriteCoreContainer;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;

@OnlyIn(Dist.CLIENT)
public class NetheriteCoreGui extends ContainerScreen<NetheriteCoreContainer> {

    private static final ResourceLocation BACKGROUND = new ResourceLocation(MyMinecraftMod.MOD_ID, "textures/gui/netherite_core_gui.png");
    private final PlayerInventory inventory;

    public NetheriteCoreGui(NetheriteCoreContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
        this.inventory = inv;
        this.guiLeft = 0;
        this.guiTop = 0;
        this.xSize = 175;
        this.ySize = 165;
    }

    @Override
    public void render(@Nonnull MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.renderHoveredTooltip(matrixStack, mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(@Nonnull MatrixStack matrixStack, int x, int y) {
        super.drawGuiContainerForegroundLayer(matrixStack, x, y);
        this.font.drawString(matrixStack, this.title.getString(), 8.0f, 6.0f, 4210752);
        this.font.drawString(matrixStack, this.playerInventory.getDisplayName().getString(),8.0f, 72.0f, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(@Nonnull MatrixStack matrixStack, float partialTicks, int mouseX, int mouseY) {
        if(minecraft == null) return;
        GlStateManager.color4f(1.0f, 1.0f, 1.0f, 1.0f);
        this.minecraft.getTextureManager().bindTexture(BACKGROUND);
        int x = (this.width - this.xSize)/2;
        int y = (this.height - this.ySize)/2;
        this.blit(matrixStack, x, y,0,0, this.xSize, this.ySize);
    }
}
