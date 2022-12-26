package net.forsteri.createindustrialchemistry.substances.element;

import net.forsteri.createindustrialchemistry.entry.registers.substances.GasSubstances;
import net.forsteri.createindustrialchemistry.entry.registers.substances.SolidSubstances;
import net.forsteri.createindustrialchemistry.substances.abstracts.ChemicalSubstance;
import net.forsteri.createindustrialchemistry.substances.abstracts.properties.inFluid.ExplodeInFluid;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class Potassium extends ChemicalSubstance implements ExplodeInFluid {
    public Potassium(Properties pProperties, CreativeModeTab... creativeModeTabs) {
        super(pProperties, creativeModeTabs);
    }

    @Override
    public Block[] fluidExplodesIn() {
        return new Block[]{Blocks.WATER};
    }

    @Override
    public ItemLike returnItem() {
        return SolidSubstances.POTASSIUM_HYDROXIDE.get();
    }

    @Override
    public void afterExplode(ItemStack stack, ItemEntity entity){
        entity.level.setBlock(new BlockPos(
                Math.floor(entity.getX()),
                Math.floor(entity.getY()),
                Math.floor(entity.getZ())), GasSubstances.HYDROGEN.BLOCK.get().defaultBlockState(),3);
    }
}
