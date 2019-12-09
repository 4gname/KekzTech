package blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import itemBlocks.IB_TFFTStorageFieldBlockT6;
import kekztech.KekzCore;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class Block_TFFTStorageFieldBlockT6 extends Block {

    private static Block_TFFTStorageFieldBlockT6 instance = new Block_TFFTStorageFieldBlockT6();

    private Block_TFFTStorageFieldBlockT6() {
        // I am a singleton
        super(Material.iron);
    }

    public static Block_TFFTStorageFieldBlockT6 getInstance() {
        return instance;
    }

    public void registerBlock() {
        final String blockName = "kekztech_tfftstoragefieldblock6_block";
        super.setBlockName(blockName);
        super.setCreativeTab(CreativeTabs.tabMisc);
        super.setBlockTextureName(KekzCore.MODID + ":" + "TFFTStorageFieldBlock6");
        super.setHardness(5.0f);
        super.setResistance(6.0f);
        GameRegistry.registerBlock(getInstance(), IB_TFFTStorageFieldBlockT6.class, blockName);
    }
}
