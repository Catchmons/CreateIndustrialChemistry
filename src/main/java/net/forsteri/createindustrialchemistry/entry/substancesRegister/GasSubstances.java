package net.forsteri.createindustrialchemistry.entry.substancesRegister;

import net.forsteri.createindustrialchemistry.entry.creativeModeTabs.CompoundSubstanceTab;
import net.forsteri.createindustrialchemistry.entry.creativeModeTabs.ElementarySubstanceTab;
import net.forsteri.createindustrialchemistry.entry.creativeModeTabs.FluidTab;
import net.forsteri.createindustrialchemistry.substances.abstracts.fluidBlockTypes.PoisonousFluidBlock;
import net.forsteri.createindustrialchemistry.substances.abstracts.generals.GeneralRisingGas;
import net.forsteri.createindustrialchemistry.substances.compound.CarbonDioxide;
import net.forsteri.createindustrialchemistry.substances.compound.CarbonMonoxide;
import net.forsteri.createindustrialchemistry.substances.compound.Steam;
import net.forsteri.createindustrialchemistry.substances.element.Chlorine;
import net.forsteri.createindustrialchemistry.substances.element.Oxygen;
import net.forsteri.createindustrialchemistry.substances.equipment.MetalTank;
import net.forsteri.createindustrialchemistry.substances.element.Hydrogen;
import net.forsteri.createindustrialchemistry.substances.abstracts.FluidBlock;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.RegistryObject;

import static net.forsteri.createindustrialchemistry.entry.substancesRegister.DeferredRegisters.*;

@SuppressWarnings({"Convert2MethodRef", "FunctionalExpressionCanBeFolded"})
public class GasSubstances {
    public static final RegistryObject<FlowingFluid> HYDROGEN_SOURCE
            = FLUIDS.register("hydrogen", () -> new Hydrogen.Source(GasSubstances.HYDROGEN_PROPERTIES));

    public static final RegistryObject<FlowingFluid> HYDROGEN_FLOWING
            = FLUIDS.register("hydrogen_flowing", () -> new Hydrogen.Flowing(GasSubstances.HYDROGEN_PROPERTIES));

    public static final ForgeFlowingFluid.Properties HYDROGEN_PROPERTIES = new ForgeFlowingFluid.Properties(
            () -> GasSubstances.HYDROGEN_SOURCE.get(), () -> GasSubstances.HYDROGEN_FLOWING.get(),
            FluidAttributes.builder(WATER_STILL_RL, WATER_FLOWING_RL)
                    .density(10)
                    .luminosity(0)
                    .viscosity(0)
                    .sound(SoundEvents.BUCKET_FILL)
                    .color(0xFFB3DFFF)
                    .gaseous()
    )
                    .slopeFindDistance(5)
                    .levelDecreasePerBlock(2)
                    .block(() -> GasSubstances.HYDROGEN_BLOCK.get())
                    .bucket(() -> Items.BUCKET)
                    ;

    public static final RegistryObject<FluidBlock> HYDROGEN_BLOCK = BLOCKS.register("hydrogen",
            () -> new FluidBlock(() -> GasSubstances.HYDROGEN_SOURCE.get(), BlockBehaviour.Properties.of(Material.WATER)
                    .noCollission().strength(100f).noDrops()));

    public static final RegistryObject<Item> HYDROGEN_TANK = ITEMS.register("hydrogen_tank",
            () -> new MetalTank(
                    GasSubstances.HYDROGEN_SOURCE,
                    new Item.Properties()
                            .stacksTo(1),
                    false, ElementarySubstanceTab.ELEMENTARY_SUBSTANCE_TAB, FluidTab.FLUID_TAB
            ));

    public static final RegistryObject<FlowingFluid> CARBON_DIOXIDE_SOURCE
            = FLUIDS.register("carbon_dioxide", () -> new CarbonDioxide.Source(GasSubstances.CARBON_DIOXIDE_PROPERTIES));

    public static final RegistryObject<FlowingFluid> CARBON_DIOXIDE_FLOWING
            = FLUIDS.register("carbon_dioxide_flowing", () -> new CarbonDioxide.Flowing(GasSubstances.CARBON_DIOXIDE_PROPERTIES));

    public static final ForgeFlowingFluid.Properties CARBON_DIOXIDE_PROPERTIES = new ForgeFlowingFluid.Properties(
            () -> GasSubstances.CARBON_DIOXIDE_SOURCE.get(), () -> GasSubstances.CARBON_DIOXIDE_FLOWING.get(),
            FluidAttributes.builder(WATER_STILL_RL, WATER_FLOWING_RL)
                    .density(10)
                    .luminosity(0)
                    .viscosity(0)
                    .sound(SoundEvents.BUCKET_FILL)
                    .color(0xFFFFFFFF)
                    .gaseous()
    )
            .slopeFindDistance(5)
            .levelDecreasePerBlock(2)
            .block(() -> GasSubstances.CARBON_DIOXIDE_BLOCK.get())
            .bucket(() -> Items.BUCKET)
            ;

    public static final RegistryObject<FluidBlock> CARBON_DIOXIDE_BLOCK = BLOCKS.register("carbon_dioxide",
            () -> new FluidBlock(() -> GasSubstances.CARBON_DIOXIDE_SOURCE.get(), BlockBehaviour.Properties.of(Material.WATER)
                    .noCollission().strength(100f).noDrops()));

    public static final RegistryObject<Item> CARBON_DIOXIDE_TANK = ITEMS.register("carbon_dioxide_tank",
            () -> new MetalTank(
                    GasSubstances.CARBON_DIOXIDE_SOURCE,
                    new Item.Properties()
                            .stacksTo(1),
                    0xFFFFFFFF, CompoundSubstanceTab.COMPOUND_SUBSTANCE_TAB, FluidTab.FLUID_TAB
            ));

    public static final RegistryObject<FlowingFluid> WATER_VAPOR_SOURCE
            = FLUIDS.register("water_vapor", () -> new Steam.Source(GasSubstances.WATER_VAPOR_PROPERTIES));

    public static final RegistryObject<FlowingFluid> WATER_VAPOR_FLOWING
            = FLUIDS.register("water_vapor_flowing", () -> new Steam.Flowing(GasSubstances.WATER_VAPOR_PROPERTIES));

    public static final ForgeFlowingFluid.Properties WATER_VAPOR_PROPERTIES = new ForgeFlowingFluid.Properties(
            () -> GasSubstances.WATER_VAPOR_SOURCE.get(), () -> GasSubstances.WATER_VAPOR_FLOWING.get(),
            FluidAttributes.builder(WATER_STILL_RL, WATER_FLOWING_RL)
                    .density(10)
                    .luminosity(0)
                    .viscosity(0)
                    .sound(SoundEvents.BUCKET_FILL)
                    .color(0xFFFFFFFF)
                    .gaseous()
    )
            .slopeFindDistance(5)
            .levelDecreasePerBlock(2)
            .block(() -> GasSubstances.WATER_VAPOR_BLOCK.get())
            .bucket(() -> Items.BUCKET)
            ;

    public static final RegistryObject<FluidBlock> WATER_VAPOR_BLOCK = BLOCKS.register("water_vapor",
            () -> new FluidBlock(() -> GasSubstances.WATER_VAPOR_SOURCE.get(), BlockBehaviour.Properties.of(Material.WATER)
                    .noCollission().strength(100f).noDrops()));

    public static final RegistryObject<Item> WATER_VAPOR_TANK = ITEMS.register("water_vapor_tank",
            () -> new MetalTank(
                    GasSubstances.WATER_VAPOR_SOURCE,
                    new Item.Properties()
                            .stacksTo(1),
                    0x8843D5EE ,CompoundSubstanceTab.COMPOUND_SUBSTANCE_TAB, FluidTab.FLUID_TAB
            ));

    public static final RegistryObject<FlowingFluid> CARBON_MONOXIDE_SOURCE
            = FLUIDS.register("carbon_monoxide", () -> new CarbonMonoxide.Source(GasSubstances.CARBON_MONOXIDE_PROPERTIES));

    public static final RegistryObject<FlowingFluid> CARBON_MONOXIDE_FLOWING
            = FLUIDS.register("carbon_monoxide_flowing", () -> new CarbonMonoxide.Flowing(GasSubstances.CARBON_MONOXIDE_PROPERTIES));

    public static final ForgeFlowingFluid.Properties CARBON_MONOXIDE_PROPERTIES = new ForgeFlowingFluid.Properties(
            () -> GasSubstances.CARBON_MONOXIDE_SOURCE.get(), () -> GasSubstances.CARBON_MONOXIDE_FLOWING.get(),
            FluidAttributes.builder(WATER_STILL_RL, WATER_FLOWING_RL)
                    .density(10)
                    .luminosity(0)
                    .viscosity(0)
                    .sound(SoundEvents.BUCKET_FILL)
                    .color(0xFFFFFFFF)
                    .gaseous()
    )
            .slopeFindDistance(5)
            .levelDecreasePerBlock(2)
            .block(() -> GasSubstances.CARBON_MONOXIDE_BLOCK.get())
            .bucket(() -> Items.BUCKET)
            ;

    public static final RegistryObject<FluidBlock> CARBON_MONOXIDE_BLOCK = BLOCKS.register("carbon_monoxide",
            () -> new PoisonousFluidBlock(() -> GasSubstances.CARBON_MONOXIDE_SOURCE.get(), BlockBehaviour.Properties.of(Material.WATER)
                    .noCollission().strength(100f).noDrops(), 5));

    public static final RegistryObject<Item> CARBON_MONOXIDE_TANK = ITEMS.register("carbon_monoxide_tank",
            () -> new MetalTank(
                    GasSubstances.CARBON_MONOXIDE_SOURCE,
                    new Item.Properties()
                            .stacksTo(1),
                    0xFFFFFFFF,CompoundSubstanceTab.COMPOUND_SUBSTANCE_TAB, FluidTab.FLUID_TAB
            ));

    public static final RegistryObject<FlowingFluid> OXYGEN_SOURCE
            = FLUIDS.register("oxygen", () -> new Oxygen.Source(GasSubstances.OXYGEN_PROPERTIES));

    public static final RegistryObject<FlowingFluid> OXYGEN_FLOWING
            = FLUIDS.register("oxygen_flowing", () -> new Oxygen.Flowing(GasSubstances.OXYGEN_PROPERTIES));

    public static final ForgeFlowingFluid.Properties OXYGEN_PROPERTIES = new ForgeFlowingFluid.Properties(
            () -> GasSubstances.OXYGEN_SOURCE.get(), () -> GasSubstances.OXYGEN_FLOWING.get(),
            FluidAttributes.builder(WATER_STILL_RL, WATER_FLOWING_RL)
                    .density(10)
                    .luminosity(0)
                    .viscosity(0)
                    .sound(SoundEvents.BUCKET_FILL)
                    .color(0xFFFFFFFF)
                    .gaseous()
    )
            .slopeFindDistance(5)
            .levelDecreasePerBlock(2)
            .block(() -> GasSubstances.OXYGEN_BLOCK.get())
            .bucket(() -> Items.BUCKET)
            ;

    public static final RegistryObject<FluidBlock> OXYGEN_BLOCK = BLOCKS.register("oxygen",
            () -> new FluidBlock(() -> GasSubstances.OXYGEN_SOURCE.get(), BlockBehaviour.Properties.of(Material.WATER)
                    .noCollission().strength(100f).noDrops()));

    public static final RegistryObject<Item> OXYGEN_TANK = ITEMS.register("oxygen_tank",
            () -> new MetalTank(
                    GasSubstances.OXYGEN_SOURCE,
                    new Item.Properties()
                            .stacksTo(1),
                    false, ElementarySubstanceTab.ELEMENTARY_SUBSTANCE_TAB, FluidTab.FLUID_TAB
            ));

    public static final RegistryObject<FlowingFluid> CHLORINE_SOURCE
            = FLUIDS.register("chlorine", () -> new Chlorine.Source(GasSubstances.CHLORINE_PROPERTIES));

    public static final RegistryObject<FlowingFluid> CHLORINE_FLOWING
            = FLUIDS.register("chlorine_flowing", () -> new Chlorine.Flowing(GasSubstances.CHLORINE_PROPERTIES));

    public static final ForgeFlowingFluid.Properties CHLORINE_PROPERTIES = new ForgeFlowingFluid.Properties(
            () -> GasSubstances.CHLORINE_SOURCE.get(), () -> GasSubstances.CHLORINE_FLOWING.get(),
            FluidAttributes.builder(WATER_STILL_RL, WATER_FLOWING_RL)
                    .density(10)
                    .luminosity(0)
                    .viscosity(0)
                    .sound(SoundEvents.BUCKET_FILL)
                    .color(0xFFFDFDA0)
                    .gaseous()
    )
            .slopeFindDistance(5)
            .levelDecreasePerBlock(2)
            .block(() -> GasSubstances.CHLORINE_BLOCK.get())
            .bucket(() -> Items.BUCKET)
            ;

    public static final RegistryObject<FluidBlock> CHLORINE_BLOCK = BLOCKS.register("chlorine",
            () -> new PoisonousFluidBlock(() -> GasSubstances.CHLORINE_SOURCE.get(), BlockBehaviour.Properties.of(Material.LAVA)
                    .noCollission().strength(100f).noDrops(), 6));

    public static final RegistryObject<Item> CHLORINE_TANK = ITEMS.register("chlorine_tank",
            () -> new MetalTank(
                    GasSubstances.CHLORINE_SOURCE,
                    new Item.Properties()
                            .stacksTo(1),
                    false ,ElementarySubstanceTab.ELEMENTARY_SUBSTANCE_TAB, FluidTab.FLUID_TAB
            ));

    public static final RegistryObject<FlowingFluid> HYDROGEN_IODIDE_SOURCE
            = FLUIDS.register("hydrogen_iodide",
            () -> new GeneralRisingGas.Source(GasSubstances.HYDROGEN_IODIDE_PROPERTIES, GasSubstances.HYDROGEN_IODIDE_TANK));

    public static final RegistryObject<FlowingFluid> HYDROGEN_IODIDE_FLOWING
            = FLUIDS.register("hydrogen_iodide_flowing",
            () -> new GeneralRisingGas.Flowing(GasSubstances.HYDROGEN_PROPERTIES, GasSubstances.HYDROGEN_IODIDE_TANK));

    public static final ForgeFlowingFluid.Properties HYDROGEN_IODIDE_PROPERTIES = new ForgeFlowingFluid.Properties(
            () -> GasSubstances.HYDROGEN_IODIDE_SOURCE.get(), () -> GasSubstances.HYDROGEN_IODIDE_FLOWING.get(),
            FluidAttributes.builder(WATER_STILL_RL, WATER_FLOWING_RL)
                    .density(10)
                    .luminosity(0)
                    .viscosity(0)
                    .sound(SoundEvents.BUCKET_FILL)
                    .color(0xFFFFFFFF)
                    .gaseous()
    )
            .slopeFindDistance(5)
            .levelDecreasePerBlock(2)
            .block(() -> GasSubstances.HYDROGEN_IODIDE_BLOCK.get())
            .bucket(() -> Items.BUCKET);

    public static final RegistryObject<FluidBlock> HYDROGEN_IODIDE_BLOCK = BLOCKS.register("hydrogen_iodide",
            () -> new FluidBlock(() -> GasSubstances.HYDROGEN_IODIDE_SOURCE.get(), BlockBehaviour.Properties.of(Material.WATER)
                    .noCollission().strength(100f).noDrops()));

    public static final RegistryObject<Item> HYDROGEN_IODIDE_TANK = ITEMS.register("hydrogen_iodide_tank",
            () -> new MetalTank(
                    GasSubstances.HYDROGEN_IODIDE_SOURCE,
                    new Item.Properties()
                            .stacksTo(1),
                    0xFFFFFFFF, CompoundSubstanceTab.COMPOUND_SUBSTANCE_TAB, FluidTab.FLUID_TAB
            ));

    public static void register(){}
}
