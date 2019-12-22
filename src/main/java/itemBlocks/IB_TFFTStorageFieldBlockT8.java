package itemBlocks;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;

import java.util.List;

public class IB_TFFTStorageFieldBlockT8 extends ItemBlock {

    public IB_TFFTStorageFieldBlockT8(Block block) {
        super(block);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List lines, boolean advancedTooltips) {
        lines.add("This is not a fluid tank");
        lines.add("Capacity Multi-Tank:"+ EnumChatFormatting.GREEN+" 2 048 000 000L for 1 fluid (Total 25 fluid)"+EnumChatFormatting.YELLOW+" 512 EU/t");
        lines.add(EnumChatFormatting.RED+"Single-Tank not used"+EnumChatFormatting.RESET);
    }

}
