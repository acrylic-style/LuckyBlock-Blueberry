package xyz.acrylicstyle.luckyBlock.util.lucks

import net.blueberrymc.world.level.block.Block
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.entity.item.PrimedTnt
import xyz.acrylicstyle.luckyBlock.util.Luck
import java.util.Random

object InstaExplodeLarge : Luck {
    override fun execute(random: Random, player: ServerPlayer, block: Block) {
        for (i in 1..500) {
            val tnt = PrimedTnt(block.level, block.location.x + 0.5, block.location.y + 0.5, block.location.z + 0.5, player)
            tnt.fuse = random.nextInt(100)
            block.level.addFreshEntity(tnt)
        }
    }
}
