package items.dust;

import java.util.List;

import cpw.mods.fml.common.registry.GameRegistry;
import kekztech.KekzCore;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;

public class dustZr extends Item {
    {
    }

    private static final dustZr instance = new dustZr();

    private dustZr() {
        // I am a singleton
    }

    public static dustZr getInstance() {
        return instance;
    }

    public void registerItem() {
        super.setHasSubtypes(false);
        final String unlocalizedName = "dustZr";
        super.setUnlocalizedName(unlocalizedName);
        super.setCreativeTab(CreativeTabs.tabMisc);
        super.setMaxStackSize(64);
        super.setTextureName(KekzCore.MODID + ":" + "dustZr");
        GameRegistry.registerItem(getInstance(), unlocalizedName);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean b) {
        list.add("Zr");
        list.add("Added by: " +EnumChatFormatting.YELLOW+" 4gname");

    }
}