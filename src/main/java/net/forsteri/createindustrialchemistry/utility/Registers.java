package net.forsteri.createindustrialchemistry.utility;

import net.forsteri.createindustrialchemistry.entry.registers.Equipments;
import net.forsteri.createindustrialchemistry.substances.abstracts.ChemicalSubstance;
import net.forsteri.createindustrialchemistry.substances.abstracts.FluidBlock;
import net.forsteri.createindustrialchemistry.substances.abstracts.fluidBlockTypes.AcidicFluidBlock;
import net.forsteri.createindustrialchemistry.substances.abstracts.fluidBlockTypes.HotFluidBlock;
import net.forsteri.createindustrialchemistry.substances.abstracts.fluidBlockTypes.PoisonousFluidBlock;
import net.forsteri.createindustrialchemistry.substances.abstracts.generals.GeneralFlowingFluid;
import net.forsteri.createindustrialchemistry.substances.abstracts.generals.GeneralRisingGas;
import net.forsteri.createindustrialchemistry.substances.equipment.MetalTank;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Function;

import static net.forsteri.createindustrialchemistry.entry.CreativeModeTabs.*;
import static net.forsteri.createindustrialchemistry.entry.registers.DeferredRegisters.*;

public class Registers {
    public static class Fluids {
        public RegistryObject<FlowingFluid> SOURCE;
        public RegistryObject<FlowingFluid> FLOWING;

        public RegistryObject<LiquidBlock> BLOCK;
        public RegistryObject<Item> TANK;
        public ForgeFlowingFluid.Properties PROPERTIES;
        public Fluids(String name, boolean rises, int color, Function<Fluids, FluidBlock> fluidBlockGen, FunctionInterface.TankItemGenFunction tankItemGen, int distance, CreativeModeTab... creativeModeTabs) {
            this.PROPERTIES = new ForgeFlowingFluid.Properties(
                    () -> this.SOURCE.get(), () -> this.FLOWING.get(),
                    FluidAttributes.builder(WATER_STILL_RL, WATER_FLOWING_RL)
                            .density(10)
                            .luminosity(0)
                            .viscosity(0)
                            .sound(SoundEvents.BUCKET_FILL)
                            .color(color)
            )
                    .slopeFindDistance(7/distance)
                    .levelDecreasePerBlock(7/distance)
                    .block(() -> this.BLOCK.get())
                    .bucket(() -> Items.BUCKET);
            if(!rises) {
                this.SOURCE = FLUIDS.register(name, () -> new GeneralFlowingFluid.Source(this.PROPERTIES, () -> this.TANK.get()));
                this.FLOWING = FLUIDS.register(name + "_flowing", () -> new GeneralFlowingFluid.Flowing(this.PROPERTIES, () -> this.TANK.get()));
            }else {
                this.SOURCE = FLUIDS.register(name, () -> new GeneralRisingGas.Source(this.PROPERTIES, () -> this.TANK.get()));
                this.FLOWING = FLUIDS.register(name + "_flowing", () -> new GeneralRisingGas.Flowing(this.PROPERTIES, () -> this.TANK.get()));
            }
            this.BLOCK = BLOCKS.register(name,
                    () -> fluidBlockGen.apply(this));


            this.TANK = ITEMS.register(name+"_tank",
                    () -> tankItemGen.apply(this, color, creativeModeTabs));
        }

        public Fluids(String name, boolean rises, int color, Function<Fluids, FluidBlock> function, int distance, CreativeModeTab... creativeModeTabs){
            this(name, rises, color, function, TankItemGens::normal, distance, creativeModeTabs);
        }

        public Fluids(String name, boolean rises, int color, CreativeModeTab... creativeModeTabs) {
            this(name, rises, color, FluidBlockGens::normal, 2, creativeModeTabs);
        }

        public Fluids(String name, boolean rises, int color, Function<Fluids, FluidBlock> function, CreativeModeTab... creativeModeTabs) {
            this(name, rises, color, function, 2, creativeModeTabs);
        }

        public Fluids(String name, boolean rises, int color, int distance, CreativeModeTab... creativeModeTabs) {
            this(name, rises, color, FluidBlockGens::normal, distance, creativeModeTabs);
        }

        public static class FluidBlockGens{
            public static FluidBlock normal(Fluids fluidsInstance){
                return new FluidBlock(() -> fluidsInstance.SOURCE.get(), BlockBehaviour.Properties.of(Material.LAVA)
                        .noOcclusion()
                        .strength(100f)
                        .noDrops());
            }

            public static FluidBlock hot(Fluids fluidsInstance){
                return new HotFluidBlock(() -> fluidsInstance.SOURCE.get(), BlockBehaviour.Properties.of(Material.LAVA)
                        .noOcclusion()
                        .strength(100f)
                        .noDrops());
            }

            public static Function<Fluids, FluidBlock> acidic(float pH){
                return (Fluids fluids) -> new AcidicFluidBlock(() -> fluids.SOURCE.get(), BlockBehaviour.Properties.of(Material.LAVA)
                        .noOcclusion()
                        .strength(100f)
                        .noDrops(), pH);
            }

            public static Function<Fluids, FluidBlock> basic(float pH){
                return acidic(pH);
            }

            public static FluidBlock poisonous(Fluids fluidsInstance){
                return new PoisonousFluidBlock(() -> fluidsInstance.SOURCE.get(), BlockBehaviour.Properties.of(Material.LAVA)
                        .noOcclusion()
                        .strength(100f)
                        .noDrops());
            }
        }

        public static class TankItemGens{
            public static Item normal(Fluids fluidsInstance, Integer color, CreativeModeTab... creativeModeTabs){
                return new MetalTank(
                        fluidsInstance.SOURCE,
                        new Item.Properties()
                                .stacksTo(1)
                        ,color, creativeModeTabs);
            }

            public static FunctionInterface.TankItemGenFunction fuel(int fuelTime){
                return (Fluids fluids, Integer color, CreativeModeTab... creativeModeTabs) -> (new MetalTank(
                        fluids.SOURCE,
                        new Item.Properties()
                                .stacksTo(1)
                                .craftRemainder(Equipments.EMPTY_METAL_TANK.get()
                                ), color, fuelTime, creativeModeTabs));
            }
        }

    }

    public static class Compounds {
        public static RegistryObject<Item> createCompound(String name){
            return ITEMS.register(name,
                    () -> new ChemicalSubstance(new Item.Properties(), COMPOUND_SUBSTANCE_TAB));
        }

        public static RegistryObject<Item> createElement(String name){
            return ITEMS.register(name,
                    () -> new ChemicalSubstance(new Item.Properties(), ELEMENTARY_SUBSTANCE_TAB));
        }

        public static RegistryObject<Item> createMixture(String name){
            return ITEMS.register(name,
                    () -> new ChemicalSubstance(new Item.Properties(), MIXTURE_TAB));
        }
    }
}
