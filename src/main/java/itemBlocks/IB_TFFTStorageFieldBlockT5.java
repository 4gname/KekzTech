package itemBlocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class IB_TFFTStorageFieldBlockT5 extends ItemBlock {

    public IB_TFFTStorageFieldBlockT5(Block block) {
        super(block);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List lines, boolean advancedTooltips) {
        lines.add("This is not a fluid tank");
        lines.add("Capacity Multi-Tank: 256 000 000L for 1 fluid (Total 25 fluid)");
        lines.add("Capacity Single-Tank: 1 280 000 000L");
        lines.add("Power Draw: 8 EU/t");
    }

}
