package blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import itemBlocks.IB_TFFTMultiHatch2;
import kekztech.KekzCore;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import tileentities.TE_TFFTMultiHatch2;

public class Block_TFFTMultiHatch2 extends BlockContainer {

	private static Block_TFFTMultiHatch2 instance = new Block_TFFTMultiHatch2();

	private Block_TFFTMultiHatch2() {
		super(Material.iron);
	}
	
	public static Block_TFFTMultiHatch2 getInstance() {
		return instance;
	}
	
	public void registerBlock() {
		final String blockName = "kekztech_singlehatch_block";
		super.setBlockName(blockName);
		super.setCreativeTab(CreativeTabs.tabMisc);
		super.setBlockTextureName(KekzCore.MODID + ":" + "SingleHatch");
		super.setHardness(5.0f);
		super.setResistance(6.0f);
		GameRegistry.registerBlock(getInstance(), IB_TFFTMultiHatch2.class, blockName);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int p_149915_2_) {
		return new TE_TFFTMultiHatch2();
	}
	
}
