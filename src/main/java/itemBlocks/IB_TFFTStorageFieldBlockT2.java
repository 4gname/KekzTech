package itemBlocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;

public class IB_TFFTStorageFieldBlockT2 extends ItemBlock {

	public IB_TFFTStorageFieldBlockT2(Block block) {
		super(block);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List lines, boolean advancedTooltips) {
		lines.add("This is not a fluid tank");
		lines.add("Capacity Multi-Tank:" + EnumChatFormatting.GREEN+" 32 000 000L for 1 fluid (Total 25 fluid)" + EnumChatFormatting.YELLOW+ " 1 EU/t");
		lines.add("Capacity Single-Tank:" + EnumChatFormatting.GREEN+" 160 000 000L" + EnumChatFormatting.YELLOW+ " 2 EU/t");
	}
	
}
