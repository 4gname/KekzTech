package blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import kekztech.KekzCore;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;

import java.util.List;

public class Block_YSZUnit extends Block {
	
	private static Block_YSZUnit instance = new Block_YSZUnit();
	
	private Block_YSZUnit() {
		// I am a singleton
		super(Material.iron);
	}
	
	public static Block_YSZUnit getInstance() {
		return instance;
	}
	
	public void registerBlock() {
		final String blockName = "kekztech_yszceramicelectrolyteunit_block";
		super.setBlockName(blockName);
		super.setCreativeTab(CreativeTabs.tabMisc);
		super.setBlockTextureName(KekzCore.MODID + ":" + "YSZCeramicElectrolyteUnit");
		super.setHardness(5.0f);
		super.setResistance(6.0f);
		GameRegistry.registerBlock(getInstance(), blockName);
	}

	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean b) {
		list.add("Yttria-Stabilized Zirconia Electrolyte");
		list.add("Added by: " + EnumChatFormatting.YELLOW+" 4gname");

	}
}
