package com.CoinBeast.MyMinecraftMod.events;

import com.CoinBeast.MyMinecraftMod.MyMinecraftMod;
import com.CoinBeast.MyMinecraftMod.container.NetheriteCoreContainer;
import com.CoinBeast.MyMinecraftMod.entities.HokageKunaiEntity;
import com.CoinBeast.MyMinecraftMod.gui.NetheriteCoreGui;
import com.CoinBeast.MyMinecraftMod.items.HokageKunai;
import com.CoinBeast.MyMinecraftMod.util.RegistryHandler;
import net.minecraft.client.GameSettings;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = MyMinecraftMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ModClientEvents {

    public static GameSettings gs = Minecraft.getInstance().gameSettings;
    public static boolean clicked = false;
    public static int temp = 0;


    @SubscribeEvent
    public static void clientSetup(EntityEvent event){
        if(temp == 0) {
            ScreenManager.registerFactory(RegistryHandler.NETHERITE_CONTAINER.get(), NetheriteCoreGui::new);
            temp++;
        }
    }

    @SubscribeEvent
    public static void clickEvent(InputEvent.MouseInputEvent event){
        if(gs.keyBindPickBlock.isPressed()){
            clicked = true;
        }else
            clicked = false;
    }

    @SubscribeEvent
    public static void kunaiTeleport(EntityEvent event) throws InterruptedException {
        if(event.getEntity() instanceof HokageKunaiEntity) {
            HokageKunaiEntity kunai = (HokageKunaiEntity) event.getEntity();
            PlayerEntity player = (PlayerEntity) kunai.getLivingEntity();
            if (player != null) {
                if (clicked) {
                    player.setPositionAndUpdate(kunai.getPosX(), kunai.getPosY(), kunai.getPosZ());
                    kunai.remove();
                }
            }
        }
    }
}
