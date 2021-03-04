package xyz.acrylicstyle.luckyBlock.item

import net.minecraft.ChatFormatting
import net.minecraft.network.chat.Component
import net.minecraft.network.chat.TextComponent
import net.minecraft.world.item.BlockItem
import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.TooltipFlag
import net.minecraft.world.level.Level
import xyz.acrylicstyle.luckyBlock.LuckyBlockMod
import xyz.acrylicstyle.luckyBlock.block.LuckyBlock

class LuckyBlockItem(luckyBlock: LuckyBlock) : BlockItem(luckyBlock, Properties().stacksTo(64).tab(CreativeModeTab.TAB_BUILDING_BLOCKS).durability(LuckyBlockMod.MAX_LUCK)) {
    companion object {
        fun setDamageValue(item: ItemStack, luck: Int) {
            if (luck == 0) {
                item.damageValue = 0
                return
            }
            if (luck < 0) {
                item.damageValue = LuckyBlockMod.MAX_LUCK
                return
            }
            // luck > 0
            item.damageValue = (LuckyBlockMod.MAX_LUCK - luck.coerceAtMost(LuckyBlockMod.MAX_LUCK)).coerceAtLeast(1)
        }
    }

    override fun appendHoverText(itemStack: ItemStack, level: Level?, list: MutableList<Component>, tooltipFlag: TooltipFlag) {
        val luck = itemStack.tag?.getInt("luck") ?: 0
        val l = TextComponent("Luck: ").withStyle(ChatFormatting.YELLOW)
        val color: ChatFormatting = if (luck < 0) {
            ChatFormatting.RED
        } else if (luck > 0) {
            ChatFormatting.GREEN
        } else {
            ChatFormatting.GOLD
        }
        val luckString = if (luck > 0) "+$luck" else "$luck"
        list.add(l.append(TextComponent(luckString).withStyle(color)))
    }

    override fun getName(itemStack: ItemStack?): Component {
        return TextComponent("Lucky Block")
    }
}