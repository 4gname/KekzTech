package items.ceramic;

import java.util.List;

import cpw.mods.fml.common.registry.GameRegistry;
import kekztech.KekzCore;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;

public class ceramicLSCF extends Item {
    {
    }

    private static final ceramicLSCF instance = new ceramicLSCF();

    private ceramicLSCF() {
        // I am a singleton
    }

    public static ceramicLSCF getInstance() {
        return instance;
    }

    public void registerItem() {
        super.setHasSubtypes(false);
        final String unlocalizedName = "ceramicLSCF";
        super.setUnlocalizedName(unlocalizedName);
        super.setCreativeTab(CreativeTabs.tabMisc);
        super.setMaxStackSize(4);
        super.setTextureName(KekzCore.MODID + ":" + "ceramicLSCF");
        GameRegistry.registerItem(getInstance(), unlocalizedName);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean b) {
        list.add("Lanthanum Strontium Cobalt Ferrite Ceramic");
        list.add("Added by: " +EnumChatFormatting.YELLOW+" 4gname");

    }
}