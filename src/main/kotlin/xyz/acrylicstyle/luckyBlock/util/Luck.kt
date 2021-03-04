package xyz.acrylicstyle.luckyBlock.util

import net.blueberrymc.world.level.block.Block
import net.minecraft.server.level.ServerPlayer

interface Luck {
    fun execute(player: ServerPlayer, block: Block)
}
