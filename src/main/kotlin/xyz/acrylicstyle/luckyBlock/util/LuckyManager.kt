package xyz.acrylicstyle.luckyBlock.util

import net.blueberrymc.world.level.block.Block
import net.minecraft.server.level.ServerPlayer
import net.minecraft.util.Mth
import xyz.acrylicstyle.luckyBlock.LuckyBlockMod
import xyz.acrylicstyle.luckyBlock.util.lucks.InstaExplodeLarge
import java.util.*
import kotlin.math.abs

object LuckyManager {
    private val lucks = mutableMapOf<Int, Luck>(
        -300 to InstaExplodeLarge
    )
    private val random = Random()

    private fun getRandomLuck(baseLuck: Int) = baseLuck + random.nextInt(200) - 100

    private fun getNearestLuck(luckValue: Int): Luck {
        val normalizedLuck = Mth.clamp(luckValue, -100 + -LuckyBlockMod.MAX_LUCK, 100 + LuckyBlockMod.MAX_LUCK)
        var i = Integer.MAX_VALUE
        var luck: Luck? = null
        lucks.forEach { (key, value) ->
            if (i > abs(normalizedLuck - key)) {
                i = abs(normalizedLuck - key)
                luck = value
            }
        }
        return luck ?: InstaExplodeLarge
    }

    fun execute(baseLuck: Int, player: ServerPlayer, block: Block) {
        getNearestLuck(getRandomLuck(baseLuck)).execute(player, block)
    }
}