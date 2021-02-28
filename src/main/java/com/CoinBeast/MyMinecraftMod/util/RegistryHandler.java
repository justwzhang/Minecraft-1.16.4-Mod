package com.CoinBeast.MyMinecraftMod.util;

import com.CoinBeast.MyMinecraftMod.MyMinecraftMod;
import com.CoinBeast.MyMinecraftMod.container.NetheriteCoreContainer;
import com.CoinBeast.MyMinecraftMod.entities.*;
import com.CoinBeast.MyMinecraftMod.items.CommonScrolls.BasicHealingJutsu;
import com.CoinBeast.MyMinecraftMod.items.CommonScrolls.BodyFlickerJutsu;
import com.CoinBeast.MyMinecraftMod.items.CommonScrolls.IronSkinJutsu;
import com.CoinBeast.MyMinecraftMod.items.ForbiddenScrolls.EighthGate;
import com.CoinBeast.MyMinecraftMod.items.ForbiddenScrolls.MitoticRegeneration;
import com.CoinBeast.MyMinecraftMod.items.ForbiddenScrolls.RasenShuriken;
import com.CoinBeast.MyMinecraftMod.items.HokageKunai;
import com.CoinBeast.MyMinecraftMod.items.ItemBase;
import com.CoinBeast.MyMinecraftMod.items.KunaiBase;
import com.CoinBeast.MyMinecraftMod.items.LegendaryScrolls.Amaterasu;
import com.CoinBeast.MyMinecraftMod.items.LegendaryScrolls.GrandFireballJutsu;
import com.CoinBeast.MyMinecraftMod.items.LegendaryScrolls.ShadowCloneJutsu;
import com.CoinBeast.MyMinecraftMod.items.NetheriteCoreTool;
import com.CoinBeast.MyMinecraftMod.items.RareScrolls.DefensiveFangs;
import com.CoinBeast.MyMinecraftMod.items.RareScrolls.FireballJutsu;
import com.CoinBeast.MyMinecraftMod.items.RareScrolls.OffensiveFangs;
import com.CoinBeast.MyMinecraftMod.items.RareScrolls.ShadowParalysisJutsu;
import com.CoinBeast.MyMinecraftMod.items.Susanoo.OrangeSoul;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class RegistryHandler {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MyMinecraftMod.MOD_ID);
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, MyMinecraftMod.MOD_ID);
    public static final DeferredRegister<ContainerType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, MyMinecraftMod.MOD_ID);

    public static void init(){
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ENTITIES.register((FMLJavaModLoadingContext.get().getModEventBus()));
        CONTAINERS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    /* Items */
    public static final RegistryObject<HokageKunai> HOKAGEKUNAI = ITEMS.register("hokage_kunai", HokageKunai::new);
    public static final RegistryObject<KunaiBase> KUNAI = ITEMS.register("kunai", KunaiBase::new);
    public static final RegistryObject<NetheriteCoreTool> NETHERITE_CORE_TOOL = ITEMS.register("netherite_core_tool", NetheriteCoreTool::new);
    public static final RegistryObject<ItemBase> NETHERITE_CORE = ITEMS.register("netherite_core", ItemBase::new);
    public static final RegistryObject<OrangeSoul> ORANGE_SOUL = ITEMS.register("orange_soul", OrangeSoul::new);

    /* Jutsus */
    public static final RegistryObject<FireballJutsu> FIREBALL_JUTSU = ITEMS.register("fireball_jutsu", FireballJutsu::new);
    public static final RegistryObject<GrandFireballJutsu> GRAND_FIREBALL_JUTSU = ITEMS.register("grand_fireball_jutsu", GrandFireballJutsu::new);
    public static final RegistryObject<EighthGate> EIGHTH_GATE = ITEMS.register("eigth_gate", EighthGate::new);
    public static final RegistryObject<BodyFlickerJutsu>BODY_FLICKER_JUTSU = ITEMS.register("body_flicker", BodyFlickerJutsu::new);
    public static final RegistryObject<MitoticRegeneration> MITOTIC_REGENERATION = ITEMS.register("mitotic_regeneration", MitoticRegeneration::new);
    public static final RegistryObject<Amaterasu> AMATERASU = ITEMS.register("amaterasu", Amaterasu::new);
    public static final RegistryObject<ShadowParalysisJutsu> SHADOW_PARALYSIS_JUTSU = ITEMS.register("shadow_paralysis_jutsu", ShadowParalysisJutsu::new);
    public static final RegistryObject<BasicHealingJutsu> BASIC_HEALING = ITEMS.register("basic_healing_jutsu", BasicHealingJutsu::new);
    public static final RegistryObject<IronSkinJutsu> IRON_SKIN = ITEMS.register("iron_skin_jutsu", IronSkinJutsu::new);
    public static final RegistryObject<OffensiveFangs> OFFENSIVE_FANGS = ITEMS.register("offensive_fangs", OffensiveFangs::new);
    public static final RegistryObject<RasenShuriken> RASENSHURIKEN = ITEMS.register("rasenshuriken", RasenShuriken::new);
    public static final RegistryObject<DefensiveFangs> DEFENSIVE_FANGS = ITEMS.register("defensive_fangs", DefensiveFangs::new);
    public static final RegistryObject<ShadowCloneJutsu> SHADOW_CLONE_JUTSU = ITEMS.register("shadow_clone_jutsu", ShadowCloneJutsu::new);

    /* Entities */
    public static final RegistryObject<EntityType<HokageKunaiEntity>> HOKAGE_KUNAI_PROJECTILE = ENTITIES.register("hokage_kunai_projectile",
            () -> EntityType.Builder.<HokageKunaiEntity>create(HokageKunaiEntity::new, EntityClassification.MISC).size(0.25F, 0.25F).build(""));
    public static final RegistryObject<EntityType<KunaiBaseEntity>> BASE_KUNAI_PROJECTILE = ENTITIES.register("kunai_projectile",
            () -> EntityType.Builder.<KunaiBaseEntity>create(KunaiBaseEntity::new, EntityClassification.MISC).size(.25F, 0.25F).build(""));
    public static final RegistryObject<EntityType<AmaterasuEntity>> AMATERASU_ENTITY = ENTITIES.register("amaterasu_entity",
            () -> EntityType.Builder.<AmaterasuEntity>create(AmaterasuEntity::new, EntityClassification.MISC).size(0F, 0F).build(""));
    public static final RegistryObject<EntityType<ShadowParalysisEntity>> SHADOW_PARALYSIS_ENTITY = ENTITIES.register("shadoow_paralysis_entity",
            () -> EntityType.Builder.<ShadowParalysisEntity>create(ShadowParalysisEntity::new, EntityClassification.MISC).size(0F, 0F).build(""));
    public static final RegistryObject<EntityType<ShadowCloneEntity>> SHADOW_CLONE = ENTITIES.register("shadow_clone",
            () -> EntityType.Builder.<ShadowCloneEntity>create(ShadowCloneEntity::new, EntityClassification.CREATURE).build(""));

    /* Containers */
    public static final RegistryObject<ContainerType<NetheriteCoreContainer>> NETHERITE_CONTAINER = CONTAINERS
            .register("netherite_container", () -> IForgeContainerType.create(NetheriteCoreContainer::new));

}
