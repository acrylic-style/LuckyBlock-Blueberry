@file:JvmName("LuckyBlockMod")
package xyz.acrylicstyle.luckyBlock

import net.blueberrymc.common.bml.BlueberryMod
import net.blueberrymc.common.bml.event.event
import net.blueberrymc.common.event.block.PlayerBlockBreakEvent
import net.blueberrymc.registry.BlueberryRegistries
import xyz.acrylicstyle.luckyBlock.block.LuckyBlock
import xyz.acrylicstyle.luckyBlock.block.getLuck
import xyz.acrylicstyle.luckyBlock.item.LuckyBlockItem
import xyz.acrylicstyle.luckyBlock.util.LuckyManager

class LuckyBlockMod : BlueberryMod() {
    companion object {
        const val MAX_LUCK = 200
    }

    init {
        event<PlayerBlockBreakEvent> {
            it.block.blockState?.let { state ->
                if (state.block !is LuckyBlock) return@event
                if (!it.block.level.isClientSide && !it.player.isCreative) {
                    it.isDropItems = false
                    LuckyManager.execute(state.getLuck(), it.player, it.block)
                }
            }
        }
    }

    override fun onPreInit() {
        val luckyBlock = LuckyBlock()
        val luckyBlockItem = LuckyBlockItem(luckyBlock)
        BlueberryRegistries.BLOCK.register("lucky_block", luckyBlock)
        BlueberryRegistries.ITEM.register("lucky_block", luckyBlockItem)
    }
}
