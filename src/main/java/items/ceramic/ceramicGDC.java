package items.ceramic;

import java.util.List;

import cpw.mods.fml.common.registry.GameRegistry;
import kekztech.KekzCore;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;

public class ceramicGDC extends Item {
    {
    }

    private static final ceramicGDC instance = new ceramicGDC();

    private ceramicGDC() {
        // I am a singleton
    }

    public static ceramicGDC getInstance() {
        return instance;
    }

    public void registerItem() {
        super.setHasSubtypes(false);
        final String unlocalizedName = "ceramicGDC";
        super.setUnlocalizedName(unlocalizedName);
        super.setCreativeTab(CreativeTabs.tabMisc);
        super.setMaxStackSize(4);
        super.setTextureName(KekzCore.MODID + ":" + "ceramicGDC");
        GameRegistry.registerItem(getInstance(), unlocalizedName);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean b) {
        list.add("Gadolinium Doped Ceria Ceramic");
        list.add("Added by: " +EnumChatFormatting.YELLOW+" 4gname");

    }
}