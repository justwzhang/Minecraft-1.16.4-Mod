package com.CoinBeast.MyMinecraftMod;

import com.CoinBeast.MyMinecraftMod.util.RegistryHandler;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.Supplier;
import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("myminecraftmod")
public class MyMinecraftMod {
    // Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "myminecraftmod";

    public MyMinecraftMod() {
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        // Register the doClientStuff method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        RegistryHandler.init();
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {

    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        registerEntityModels(event.getMinecraftSupplier());
    }

    private void registerEntityModels(Supplier<Minecraft> minecraft){
        ItemRenderer renderer = minecraft.get().getItemRenderer();
        RenderingRegistry.registerEntityRenderingHandler(RegistryHandler.HOKAGE_KUNAI_PROJECTILE.get(), (renderManager) -> new SpriteRenderer<>(renderManager, renderer));
        RenderingRegistry.registerEntityRenderingHandler(RegistryHandler.BASE_KUNAI_PROJECTILE.get(), (renderManager) -> new SpriteRenderer<>(renderManager, renderer));
        RenderingRegistry.registerEntityRenderingHandler(RegistryHandler.AMATERASU_ENTITY.get(), (renderManager) -> new SpriteRenderer<>(renderManager, renderer));
        RenderingRegistry.registerEntityRenderingHandler(RegistryHandler.SHADOW_PARALYSIS_ENTITY.get(), (renderManager) -> new SpriteRenderer<>(renderManager, renderer));
    }

    public static final ItemGroup TAB = new ItemGroup("modTab"){
        @Override
        public ItemStack createIcon(){
            return new ItemStack(RegistryHandler.HOKAGEKUNAI.get());
        }
    };
}
