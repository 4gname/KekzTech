package kekztech;

import cpw.mods.fml.common.Loader;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import util.Util;

import java.util.Arrays;

public enum Items {

    // Ceramics
    dustYSZ(0,1),
    dustGDC(1,1),
    dustLSCF(2,1),
    plateYSZ(9,1),
    plateGDC(9,1),
    plateLSCF(10,1),
    /*YttriaDust(11,1),
    ZirconiaDust(12,1),
    CeriaDust(13,1),
    YSZCeramicPlate(14,1),
    GDCCeramicPlate(15,1),*/

    // Error Item
    Error(0,0);

   /* static {
        GadoliniumDopedCeriaOxide.setOreDictName("dustGDC");
        YttriaStabilizedZirconia.setOreDictName("dustYSZ");
    }
*/
    private final int metaID;
    private final int identifier;

    private Items(int metaID, int identifier) {
        this.metaID = metaID;
        this.identifier = identifier;
    }

    public int getMetaID() {
        return metaID;
    }

  /*  String OreDictName;

        private void registerOreDict(){
            OreDictionary.registerOre(getOreDictName(),getNonOreDictedItemStack(1));
        }

        public static void registerOreDictNames(){
            Arrays.stream(Items.values()).filter(e -> e.getOreDictName() != null).forEach(Items::registerOreDict);
        }

	public ItemStack getNonOreDictedItemStack(int amount){
		return 	identifier == 0 ? 	new ItemStack(PlateCeramics.getInstance(),amount,this.getMetaID()) :
									new ItemStack(PlateCeramics.getInstance(),amount,this.getMetaID());
	}

	public ItemStack getOreDictedItemStack(int amount){
		return 	this.getOreDictName() != null ? 		Util.getStackofAmountFromOreDict(this.getOreDictName(),amount) :
				identifier == 0 ? 	new ItemStack(PlateCeramics.getInstance(),amount,this.getMetaID()) :
									new ItemStack(PlateCeramics.getInstance(),amount,this.getMetaID());
	}

    public String getOreDictName() {
        return OreDictName;
    }

    public void setOreDictName(String oreDictName) {
        OreDictName = oreDictName;
    }*/
}
