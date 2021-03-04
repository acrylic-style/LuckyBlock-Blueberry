package xyz.acrylicstyle.luckyBlock.util

import net.blueberrymc.world.level.block.Block
import net.minecraft.server.level.ServerPlayer
import java.util.Random

interface Luck {
    fun execute(random: Random, player: ServerPlayer, block: Block)
}
