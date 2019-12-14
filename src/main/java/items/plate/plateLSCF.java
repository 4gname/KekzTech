package items.plate;


import java.util.List;

import cpw.mods.fml.common.registry.GameRegistry;
import kekztech.KekzCore;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;


public class plateLSCF extends Item {
    {
    }

    private static final plateLSCF instance = new plateLSCF();

    private plateLSCF() {
        // I am a singleton
    }

    public static plateLSCF getInstance() {
        return instance;
    }

    public void registerItem() {
        super.setHasSubtypes(false);
        final String unlocalizedName = "plateLSCF";
        super.setUnlocalizedName(unlocalizedName);
        super.setCreativeTab(CreativeTabs.tabMisc);
        super.setMaxStackSize(64);
        super.setTextureName(KekzCore.MODID + ":" + "plateLSCF");
        GameRegistry.registerItem(getInstance(), unlocalizedName);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean b) {
        list.add("Lanthanum Strontium Cobalt Ferrite");
        list.add("Added by: " + EnumChatFormatting.YELLOW+" 4gname");

    }
}