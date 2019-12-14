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

public class Block_GDCUnit extends Block {
	
	private static Block_GDCUnit instance = new Block_GDCUnit();
	
	private Block_GDCUnit() {
		// I am a singleton
		super(Material.iron);
	}
	
	public static Block_GDCUnit getInstance() {
		return instance;
	}
	
	public void registerBlock() {
		final String blockName = "kekztech_gdcceramicelectrolyteunit_block";
		super.setBlockName(blockName);
		super.setCreativeTab(CreativeTabs.tabMisc);
		super.setBlockTextureName(KekzCore.MODID + ":" + "GDCCeramicElectrolyteUnit");
		super.setHardness(5.0f);
		super.setResistance(6.0f);
		GameRegistry.registerBlock(getInstance(), blockName);
	}
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean b) {
		list.add("Gadolinium Doped Ceria Electrolyte");
		list.add("Added by: " + EnumChatFormatting.YELLOW+" 4gname");

	}
}
