package xyz.acrylicstyle.luckyBlock.block

import net.minecraft.core.BlockPos
import net.minecraft.util.Mth
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.SoundType
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.IntegerProperty
import net.minecraft.world.level.material.Material
import xyz.acrylicstyle.luckyBlock.LuckyBlockMod
import xyz.acrylicstyle.luckyBlock.item.LuckyBlockItem

class LuckyBlock : Block(Properties.of(Material.HEAVY_METAL).strength(1.0F).sound(SoundType.STONE)) {
    init {
        registerDefaultState(defaultBlockState().setValue(LUCK, LuckyBlockMod.MAX_LUCK))
    }

    companion object {
        @JvmStatic
        val LUCK: IntegerProperty = IntegerProperty.create("luck", 0, LuckyBlockMod.MAX_LUCK * 2)
    }

    override fun setPlacedBy(level: Level, blockPos: BlockPos, blockState: BlockState, livingEntity: LivingEntity?, itemStack: ItemStack) {
        super.setPlacedBy(level, blockPos, blockState, livingEntity, itemStack)
        if (!level.isClientSide) {
            level.setBlock(blockPos, blockState.setValue(LUCK, Mth.clamp((if (itemStack.orCreateTag.contains("luck")) itemStack.orCreateTag.getInt("luck") else 0) + LuckyBlockMod.MAX_LUCK, 0, LuckyBlockMod.MAX_LUCK * 2)), 3)
        }
    }

    override fun getCloneItemStack(blockGetter: BlockGetter?, blockPos: BlockPos?, blockState: BlockState): ItemStack {
        return super.getCloneItemStack(blockGetter, blockPos, blockState).also {
            val luck = blockState.getLuck()
            val tag = it.orCreateTag
            tag.putInt("luck", luck)
            it.tag = tag
            LuckyBlockItem.setDamageValue(it, luck)
        }
    }

    override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block, BlockState>) {
        builder.add(LUCK)
    }
}

fun BlockState.getLuck() = this.getValue(LuckyBlock.LUCK) - LuckyBlockMod.MAX_LUCK
