package blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import itemBlocks.IB_TFFTStorageFieldBlockT2;
import kekztech.KekzCore;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class Block_TFFTStorageFieldBlockT2 extends Block {
	
	private static Block_TFFTStorageFieldBlockT2 instance = new Block_TFFTStorageFieldBlockT2();
	
	private Block_TFFTStorageFieldBlockT2() {
		// I am a singleton
		super(Material.iron);
	}
	
	public static Block_TFFTStorageFieldBlockT2 getInstance() {
		return instance;
	}
	
	public void registerBlock() {
		final String blockName = "kekztech_tfftstoragefieldblock2_block";
		super.setBlockName(blockName);
		super.setCreativeTab(CreativeTabs.tabMisc);
		super.setBlockTextureName(KekzCore.MODID + ":" + "TFFTStorageFieldBlock2");
		super.setHardness(5.0f);
		super.setResistance(6.0f);
		GameRegistry.registerBlock(getInstance(), IB_TFFTStorageFieldBlockT2.class, blockName);
	} 
}
