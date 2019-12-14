
package items.dust;

        import java.util.List;

        import cpw.mods.fml.common.registry.GameRegistry;
        import kekztech.KekzCore;
        import net.minecraft.creativetab.CreativeTabs;
        import net.minecraft.entity.player.EntityPlayer;
        import net.minecraft.item.Item;
        import net.minecraft.item.ItemStack;
        import net.minecraft.util.EnumChatFormatting;

public class dustY2O3 extends Item {
    {
    }

    private static final dustY2O3 instance = new dustY2O3();

    private dustY2O3() {
        // I am a singleton
    }

    public static dustY2O3 getInstance() {
        return instance;
    }

    public void registerItem() {
        super.setHasSubtypes(false);
        final String unlocalizedName = "dustY2O3";
        super.setUnlocalizedName(unlocalizedName);
        super.setCreativeTab(CreativeTabs.tabMisc);
        super.setMaxStackSize(64);
        super.setTextureName(KekzCore.MODID + ":" + "dustY2O3");
        GameRegistry.registerItem(getInstance(), unlocalizedName);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean b) {
        list.add("Yttrium Oxide");
        list.add("Added by: " +EnumChatFormatting.YELLOW+" 4gname");

    }
}
