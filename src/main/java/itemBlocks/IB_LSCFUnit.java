package itemBlocks;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;

import java.util.List;

public class IB_LSCFUnit extends ItemBlock {

	public IB_LSCFUnit(Block block) {
		super(block);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List lines, boolean advancedTooltips) {
		lines.add("Lanthanum Strontium Cobalt Ferrite Electrolyte");
		lines.add("Added by: " + EnumChatFormatting.YELLOW+" 4gname");
	}
	
}
