package blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import gregtech.api.GregTech_API;
import itemBlocks.IB_TFFTStorageFieldBlockT8;
import kekztech.KekzCore;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.world.World;

public class Block_TFFTStorageFieldBlockT8 extends Block {

    private static Block_TFFTStorageFieldBlockT8 instance = new Block_TFFTStorageFieldBlockT8();

    private Block_TFFTStorageFieldBlockT8() {
        // I am a singleton
        super(Material.iron);
    }

    public static Block_TFFTStorageFieldBlockT8 getInstance() {
        return instance;
    }

    public void registerBlock() {
        final String blockName = "kekztech_tfftstoragefieldblock8_block";
        super.setBlockName(blockName);
        super.setCreativeTab(CreativeTabs.tabMisc);
        super.setBlockTextureName(KekzCore.MODID + ":" + "TFFTStorageFieldBlock8");
        super.setHardness(5.0f);
        super.setResistance(6.0f);
        GameRegistry.registerBlock(getInstance(), IB_TFFTStorageFieldBlockT8.class, blockName);
    }
    public static void run() {
        GregTech_API.registerMachineBlock(getInstance(),  -1);
    }

    @Override
    public void breakBlock(World aWorld, int aX, int aY, int aZ, Block aBlock, int aMeta) {
        if (GregTech_API.isMachineBlock(this,  aWorld.getBlockMetadata(aX, aY, aZ))) {
            GregTech_API.causeMachineUpdate(aWorld, aX, aY, aZ);
        }
    }

    @Override
    public void onBlockAdded(World aWorld, int aX, int aY, int aZ) {
        if (GregTech_API.isMachineBlock(this, aWorld.getBlockMetadata(aX, aY, aZ))) {
            GregTech_API.causeMachineUpdate(aWorld, aX, aY, aZ);
        }
    }
}
