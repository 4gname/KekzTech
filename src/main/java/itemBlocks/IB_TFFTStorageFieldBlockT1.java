package itemBlocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;

public class IB_TFFTStorageFieldBlockT1 extends ItemBlock {

	public IB_TFFTStorageFieldBlockT1(Block block) {
		super(block);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List lines, boolean advancedTooltips) {
		lines.add("This is not a fluid tank");
		lines.add("Capacity Multi-Tank:"+ EnumChatFormatting.GREEN+ " 16 000 000L for 1 fluid (Total 25 fluid)" + EnumChatFormatting.YELLOW+ " 0.5 EU/t");
		lines.add("Capacity Single-Tank:" + EnumChatFormatting.GREEN+ " 80 000 000L" + EnumChatFormatting.YELLOW+ " 1 EU/t");
	}
	
}
