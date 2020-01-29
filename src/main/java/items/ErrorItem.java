package items;

import java.util.List;

import cpw.mods.fml.common.registry.GameRegistry;
import kekztech.KekzCore;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ErrorItem extends Item {
	
	private static final ErrorItem instance = new ErrorItem();
	
	private ErrorItem() {
		// I am a singleton
	}
	
	public static ErrorItem getInstance() {
		return instance;
	}
	
	public void registerItem() {
		super.setHasSubtypes(false);
		final String unlocalizedName = "kekztech_error_item";
		super.setUnlocalizedName(unlocalizedName);
		super.setCreativeTab(CreativeTabs.tabMisc);
		super.setMaxStackSize(64);
		super.setTextureName(KekzCore.MODID + ":" + "Error");
		GameRegistry.registerItem(getInstance(), unlocalizedName);
	}
	
	@SuppressWarnings({"unchecked", "rawtypes"})
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean b) {
		list.add("Placeholder item in case something went wrong");
		list.add("If this item shows, you may report it to:");
		list.add("https://github.com/4gname/KekzTech");
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack item, World world, EntityPlayer player) {
		player.swingItem();
		return item;
	}
	
}
