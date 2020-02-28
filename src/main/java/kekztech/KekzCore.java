package kekztech;

import blocks.*;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.ProgressManager;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import gregtech.api.enums.GT_Values;
import gregtech.api.enums.ItemList;
import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.util.GT_ModHandler;
import gregtech.api.util.GT_OreDictUnificator;
import gregtech.api.util.GT_Utility;
import items.dust.*;
import items.ceramic.*;
import items.plate.*;
import items.ErrorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.oredict.OreDictionary;
import tileentities.*;
import util.Util;


/**
 * GT-Meta-IDs are: 13101 - 13500
 */

@Mod(modid = KekzCore.MODID, name = KekzCore.NAME, version = KekzCore.VERSION, 
		dependencies =
			  "required-after:IC2;"
			+ "required-after:gregtech;"
			+ "after:bartworks;"
		)
public class KekzCore {
	
	public static final String NAME = "GW++ KekzTech";
	public static final String MODID = "kekztech";
	public static final String VERSION = "0.3";
	
	@Mod.Instance("kekztech")
	public static KekzCore instance;

	// Generators
	private GTMTE_SOFuelCellMK1 sofc1;
	private GTMTE_SOFuelCellMK3 sofc2;
	private GTMTE_SOFuelCellMK2 sofc3;

	// Tank
	private GTMTE_FluidMultiStorage fms;
    private GTMTE_FluidMultiStorage2 fms2;

	@Mod.EventHandler //Register
	public void preInit(FMLPreInitializationEvent event) {
	// Items
			ErrorItem.getInstance().registerItem();
			dustGDC.getInstance().registerItem();
			dustYSZ.getInstance().registerItem();
			dustLSCF.getInstance().registerItem();
			dustZr.getInstance().registerItem();
			dustGd.getInstance().registerItem();
			dustCe.getInstance().registerItem();
			dustLa.getInstance().registerItem();
			dustSr.getInstance().registerItem();
			dustY.getInstance().registerItem();
			dustY2O3.getInstance().registerItem();
			ceramicGDC.getInstance().registerItem();
			ceramicYSZ.getInstance().registerItem();
			ceramicLSCF.getInstance().registerItem();
			plateYSZ.getInstance().registerItem();
			plateGDC.getInstance().registerItem();
			plateLSCF.getInstance().registerItem();


	// Blocks
			Block_YSZUnit.getInstance().registerBlock();
			Block_GDCUnit.getInstance().registerBlock();
			Block_LSCFUnit.getInstance().registerBlock();
			Block_TFFTStorageFieldBlockT1.getInstance().registerBlock();
			Block_TFFTStorageFieldBlockT2.getInstance().registerBlock();
			Block_TFFTStorageFieldBlockT3.getInstance().registerBlock();
			Block_TFFTStorageFieldBlockT4.getInstance().registerBlock();
			Block_TFFTStorageFieldBlockT5.getInstance().registerBlock();
			Block_TFFTStorageFieldBlockT6.getInstance().registerBlock();
			Block_TFFTStorageFieldBlockT7.getInstance().registerBlock();
			Block_TFFTStorageFieldBlockT8.getInstance().registerBlock();
			Block_TFFTMultiHatch.getInstance().registerBlock();
			Block_TFFTMultiHatch2.getInstance().registerBlock();
	// Register TileEntities
			GameRegistry.registerTileEntity(TE_TFFTMultiHatch.class, "kekztech_multihatch_tile");
			GameRegistry.registerTileEntity(TE_TFFTMultiHatch2.class, "kekztech_singlehatch_tile");
	// OreDict
		//Dust
			OreDictionary.registerOre("dustCubicZirconia", dustYSZ.getInstance());
			OreDictionary.registerOre("dustGDC", dustGDC.getInstance());
			OreDictionary.registerOre("dustLSCF", dustLSCF.getInstance());
			OreDictionary.registerOre("dustZirconium", dustZr.getInstance());
			OreDictionary.registerOre("dustGadolinium", dustGd.getInstance());
			OreDictionary.registerOre("dustCerium", dustCe.getInstance());
			OreDictionary.registerOre("dustLanthanum", dustLa.getInstance());
			OreDictionary.registerOre("dustStrontium", dustSr.getInstance());
			OreDictionary.registerOre("dustYttrium", dustY.getInstance());
			OreDictionary.registerOre("dustYttriumOxide", dustY2O3.getInstance());
		//Ceramic
			OreDictionary.registerOre("ceramicYSZ", ceramicYSZ.getInstance());
			OreDictionary.registerOre("ceramicGDC", ceramicGDC.getInstance());
			OreDictionary.registerOre("ceramicLSCF", ceramicLSCF.getInstance());
		//Plate
			OreDictionary.registerOre("plateYSZ", plateYSZ.getInstance());
			OreDictionary.registerOre("plateGDC", plateGDC.getInstance());
			OreDictionary.registerOre("plateLSCF", plateLSCF.getInstance());}

	@Mod.EventHandler //Register GT
	public void load(FMLInitializationEvent event){
		Block_TFFTStorageFieldBlockT1.run();
		Block_TFFTStorageFieldBlockT2.run();
		Block_TFFTStorageFieldBlockT3.run();
		Block_TFFTStorageFieldBlockT4.run();
		Block_TFFTStorageFieldBlockT5.run();
		Block_TFFTStorageFieldBlockT6.run();
		Block_TFFTStorageFieldBlockT7.run();
		Block_TFFTStorageFieldBlockT8.run();
	}

	@Mod.EventHandler //Register GT
	public void init(FMLInitializationEvent event) {
		// Multiblock controllers
		sofc1 = new GTMTE_SOFuelCellMK1(13101, "multimachine.fuelcellmk1", "Solid-Oxide Generator Mk I");
		sofc2 = new GTMTE_SOFuelCellMK3(13102, "multimachine.fuelcellmk2", "Solid-Oxide Generator Mk II");
		sofc3 = new GTMTE_SOFuelCellMK2(13103, "multimachine.fuelcellmk3", "Solid-Oxide Generator Mk III");
		fms = new GTMTE_FluidMultiStorage(13104, "multimachine.tf_fluidtank", "Multi-Tank");
        fms2 = new GTMTE_FluidMultiStorage2 (13105, "multimachine.tf_fluidtank1", "Single-Tank");
}

	@Mod.EventHandler //Register Recipes
	public void postInit(FMLPostInitializationEvent event) {
		System.out.println("Registering KekzTech recipes...");

// Multiblock Controllers
	//SOFuelCellMK1
		final Object[] mk1_recipe = {
				"CCC", "PHP", "FBL",
				'C', OrePrefixes.circuit.get(Materials.Data),
				'P', ItemList.Electric_Pump_HV.get(1L),
				'H', ItemList.Hull_HV.get(1L),
				'F', GT_OreDictUnificator.get(OrePrefixes.pipeSmall, Materials.Titanium, 1),
				'B', GT_OreDictUnificator.get(OrePrefixes.cableGt02, Materials.Gold, 1),
				'L', GT_OreDictUnificator.get(OrePrefixes.pipeLarge, Materials.Titanium, 1)};
		GT_ModHandler.addCraftingRecipe(sofc1.getStackForm(1), mk1_recipe);
	//SOFuelCellMK2
		final Object[] mk2_recipe = {
				"CCC", "PHP", "FBL",
				'C', OrePrefixes.circuit.get(Materials.Elite),
				'P', ItemList.Electric_Pump_EV.get(1L),
				'H', ItemList.Hull_EV.get(1L),
				'F', GT_OreDictUnificator.get(OrePrefixes.pipeSmall, Materials.TungstenSteel, 1),
				'B', Util.getStackofAmountFromOreDict("wireGt04SuperconductorEV", 1),
				'L', GT_OreDictUnificator.get(OrePrefixes.pipeMedium, Materials.TungstenSteel, 1)};
		GT_ModHandler.addCraftingRecipe(sofc2.getStackForm(1), mk2_recipe);
	//SOFuelCellMK3
		final Object[] mk3_recipe = {
				"CCC", "PHP", "FBL",
				'C', OrePrefixes.circuit.get(Materials.Master),
				'P', ItemList.Electric_Pump_IV.get(1L),
				'H', ItemList.Hull_IV.get(1L),
				'F', GT_OreDictUnificator.get(OrePrefixes.pipeSmall, Materials.Ultimate, 1),
				'B', Util.getStackofAmountFromOreDict("wireGt04SuperconductorIV", 1),
				'L', GT_OreDictUnificator.get(OrePrefixes.pipeMedium, Materials.Ultimate, 1)};
		GT_ModHandler.addCraftingRecipe(sofc3.getStackForm(1), mk3_recipe);

	//MultiTank
		final Object[] tfft_recipe = {
				"HFH", "PVP", "CFC",
				'H', OrePrefixes.pipeMedium.get(Materials.Titanium),
				'F', ItemList.Field_Generator_MV.get(1L),
				'P', ItemList.Electric_Pump_HV.get(1L),
				'V', ItemList.Hull_HV.get(1L),
				'C', OrePrefixes.circuit.get(Materials.Data)};
		GT_ModHandler.addCraftingRecipe(fms.getStackForm(1), tfft_recipe);
	//SingleTank
		final Object[] singletank_recipe = {
				"HFH", "PVP", "CFC",
				'H', OrePrefixes.pipeMedium.get(Materials.StainlessSteel),
				'F', ItemList.Field_Generator_LV.get(1L),
				'P', ItemList.Electric_Pump_MV.get(1L),
				'V', ItemList.Hull_MV.get(1L),
				'C', OrePrefixes.circuit.get(Materials.Data)};
		GT_ModHandler.addCraftingRecipe(fms2.getStackForm(1), singletank_recipe);


// Ceramic Electrolyte Units
	//YSZ Unit
		final ItemStack[] yszUnit = {
				GT_Utility.getIntegratedCircuit(6),
				Util.getStackofAmountFromOreDict("plateYSZ", 6),
				GT_OreDictUnificator.get(OrePrefixes.frameGt, Materials.StainlessSteel, 1),
				GT_OreDictUnificator.get(OrePrefixes.rotor, Materials.StainlessSteel, 1),
				ItemList.Electric_Motor_EV.get(1L)};
		GT_Values.RA.addAssemblerRecipe(
				yszUnit,
				Materials.Hydrogen.getGas(4000),
				new ItemStack(Block_YSZUnit.getInstance(), 1),
				20*60, 480);

	//GDC Unit
		final ItemStack[] gdcUnit = {
				GT_Utility.getIntegratedCircuit(6),
				Util.getStackofAmountFromOreDict("plateGDC", 6),
				GT_OreDictUnificator.get(OrePrefixes.frameGt, Materials.Titanium,1),
				GT_OreDictUnificator.get(OrePrefixes.rotor, Materials.Titanium,1),
				ItemList.Electric_Motor_IV.get(1L)};
		GT_Values.RA.addAssemblerRecipe(
				gdcUnit,
				Materials.Hydrogen.getGas(8000),
				new ItemStack(Block_GDCUnit.getInstance(), 1),
				20*60, 1920);
	//LSCF Unit
		final ItemStack[] lscfUnit = {
				GT_Utility.getIntegratedCircuit(6),
				Util.getStackofAmountFromOreDict("plateLSCF", 6),
				GT_OreDictUnificator.get(OrePrefixes.frameGt, Materials.TungstenSteel, 1),
				GT_OreDictUnificator.get(OrePrefixes.rotor, Materials.TungstenSteel, 1),
				ItemList.Electric_Motor_LuV.get(1L)};
		GT_Values.RA.addAssemblerRecipe(
				lscfUnit, Materials.Hydrogen.getGas(16000),
				new ItemStack(Block_LSCFUnit.getInstance(), 1),
				20*60, 7680);


// - - - - T.F.F.T Structure blocks - - - - / / /

// - - - Tank Fields - - - //

	//Field Tier 1
		final ItemStack[] tfftstoragefield1 = {
				GT_Utility.getIntegratedCircuit(6),
				ItemList.Electric_Pump_MV.get(1L),
				GT_OreDictUnificator.get(OrePrefixes.plate, Materials.StainlessSteel, 4)};
		GT_Values.RA.addAssemblerRecipe(
				tfftstoragefield1, FluidRegistry.getFluidStack("molten.epoxid", 36),
				new ItemStack(Block_TFFTStorageFieldBlockT1.getInstance(), 1),
				200, 480);
//Field Tier 2
		final ItemStack[] tfftstoragefield2 = {
				GT_Utility.getIntegratedCircuit(6),
				ItemList.Field_Generator_LV.get(1L),
				ItemList.Electric_Pump_HV.get(1L),
				GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Titanium, 4)};
		GT_Values.RA.addAssemblerRecipe(
				tfftstoragefield2, FluidRegistry.getFluidStack("molten.epoxid", 144),
				new ItemStack(Block_TFFTStorageFieldBlockT2.getInstance(), 1),
				200, 1920);
//Field Tier 3
		final ItemStack[] tfftstoragefield3 = {
				GT_Utility.getIntegratedCircuit(6),
				ItemList.Field_Generator_MV.get(1L),
				ItemList.Electric_Pump_EV.get(1L),
				GT_OreDictUnificator.get(OrePrefixes.plate, Materials.TungstenSteel, 4)};
		GT_Values.RA.addAssemblerRecipe(
				tfftstoragefield3, FluidRegistry.getFluidStack("molten.epoxid", 144),
				new ItemStack(Block_TFFTStorageFieldBlockT3.getInstance(), 1),
				200, 7680);
//Field Tier 4
		final ItemStack[] tfftstoragefield4 = {
				GT_Utility.getIntegratedCircuit(6),
				ItemList.Field_Generator_HV.get(1L),
				ItemList.Electric_Pump_IV.get(1L),
				GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Chrome, 4)};
		GT_Values.RA.addAssemblerRecipe(
				tfftstoragefield4, FluidRegistry.getFluidStack("molten.epoxid", 144),
				new ItemStack(Block_TFFTStorageFieldBlockT4.getInstance(), 1),
				200, 30720);
//Field Tier 5
        final ItemStack[] tfftstoragefield5 = {
				GT_Utility.getIntegratedCircuit(6),
				ItemList.Field_Generator_EV.get(1L),
				ItemList.Electric_Pump_LuV.get(1L),
				GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Iridium, 4)};
        GT_Values.RA.addAssemblerRecipe(
                tfftstoragefield5,  FluidRegistry.getFluidStack("molten.epoxid", 144),
                new ItemStack(Block_TFFTStorageFieldBlockT5.getInstance(), 1),
                200, 122880);

//Field Tier 6
		final ItemStack[] tfftstoragefield6 = {
				GT_Utility.getIntegratedCircuit(6),
				ItemList.Field_Generator_IV.get(1L),
				ItemList.Electric_Pump_ZPM.get(1L),
				GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Osmium, 4)};
		GT_Values.RA.addAssemblerRecipe(
				tfftstoragefield6, FluidRegistry.getFluidStack("molten.epoxid", 144),
				new ItemStack(Block_TFFTStorageFieldBlockT6.getInstance(), 1),
				200, 491520);
//Field Tier 7
		final ItemStack[] tfftstoragefield7 = {
				GT_Utility.getIntegratedCircuit(6),
				ItemList.Field_Generator_LuV.get(1L),
				ItemList.Electric_Pump_UV.get(1L),
				GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Tritanium, 4)};
		GT_Values.RA.addAssemblerRecipe(
				tfftstoragefield7, FluidRegistry.getFluidStack("molten.epoxid", 144),
				new ItemStack(Block_TFFTStorageFieldBlockT7.getInstance(), 1),
				200, 1966080);
//Field Tier 8
		final ItemStack[] tfftstoragefield8 = {
				GT_Utility.getIntegratedCircuit(6),
				ItemList.Field_Generator_ZPM.get(1L),
				ItemList.Electric_Pump_UHV.get(1L),
				GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Neutronium, 4)};
		GT_Values.RA.addAssemblerRecipe(
				tfftstoragefield8, FluidRegistry.getFluidStack("molten.epoxid", 144),
				new ItemStack(Block_TFFTStorageFieldBlockT8.getInstance(), 1),
				200, 7864320);

// - - - Multi Hatch - - - //

		final Object[] multi_hatch = {
				"PRP", "UFU", "PRP",
				'P', GT_OreDictUnificator.get(OrePrefixes.pipeHuge, Materials.Titanium, 1),
				'R', ItemList.Hatch_Output_EV.get(1L),
				'U', ItemList.Hatch_Input_EV.get(1L),
				'F', ItemList.Electric_Pump_HV.get(1L)};
		GT_ModHandler.addCraftingRecipe(new ItemStack(Block_TFFTMultiHatch.getInstance()), multi_hatch);

// - - - Single Hatch - - - //

		final Object[] single_hatch = {
				"PRP", "UFU", "PRP",
				'P', GT_OreDictUnificator.get(OrePrefixes.pipeHuge, Materials.StainlessSteel, 1),
				'R', ItemList.Hatch_Output_HV.get(1L),
				'U', ItemList.Hatch_Input_HV.get(1L),
				'F', ItemList.Electric_Pump_MV.get(1L)};
		GT_ModHandler.addCraftingRecipe(new ItemStack(Block_TFFTMultiHatch2.getInstance()), single_hatch);

//  -  -  -  -  Dusts  -  -  -  -  //
		// Rare Earth Processing - Strontium and Gadolinium
		GT_Values.RA.addSifterRecipe(GT_OreDictUnificator.get(OrePrefixes.dustSmall, Materials.RareEarth, 1),
				new ItemStack[] {
						new ItemStack(dustSr.getInstance(), 1),
						new ItemStack(dustGd.getInstance(), 1),
						null, null, null, null, null, null, null },
				new int[] { 200, 200, 200, 200, 200, 200, 200, 200, 200 }, 20 * 30, 500);
		// Y2O3
		if (!Loader.isModLoaded("bartworks")) {
			GT_Values.RA.addChemicalRecipe(
					Materials.Yttrium.getDust(2), GT_Utility.getIntegratedCircuit(6), Materials.Oxygen.getGas(3000),
					null, new ItemStack(dustY2O3.getInstance(), 1), null,
					400, 30);
		GT_Values.RA.addChemicalRecipe(
				Util.getStackofAmountFromOreDict("dustY", 2), GT_Utility.getIntegratedCircuit(6), Materials.Oxygen.getGas(3000),
				null, new ItemStack(dustY2O3.getInstance(), 1), null,
				400, 30);}
		// YSZ
		GT_Values.RA.addMixerRecipe(
				Util.getStackofAmountFromOreDict("dustZirconium", 1),
				Util.getStackofAmountFromOreDict("dustYttriumOxide", 1),
				null,null,null,
				GT_Utility.getIntegratedCircuit(2),null, null,
				new ItemStack(dustYSZ.getInstance(), 2),
				400, 96);
		// GDC
		GT_Values.RA.addMixerRecipe(
				Util.getStackofAmountFromOreDict("dustCerium", 9),
				Util.getStackofAmountFromOreDict("dustGadolinium", 1),
				null,null,null,
				GT_Utility.getIntegratedCircuit(2),
				null, null,
				new ItemStack(dustGDC.getInstance(), 10),
				400, 96);
		// LSCF
		GT_Values.RA.addMixerRecipe(
				Util.getStackofAmountFromOreDict("dustLanthanum", 15),
				Util.getStackofAmountFromOreDict("dustStrontium", 13),
				GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Iron, 8),
				GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Cobalt, 2),
				null,
				GT_Utility.getIntegratedCircuit(2),null, null,
				new ItemStack(dustLSCF.getInstance(), 38),
				400, 96);

//  -  -  -  -  Ceramics  -  -  -  -  //
		// YSZ
		GT_Values.RA.addBlastRecipe(
				Util.getStackofAmountFromOreDict("dustCubicZirconia", 16), GT_Utility.getIntegratedCircuit(1),
				null, null,
				new ItemStack(ceramicYSZ.getInstance(), 1),null,
				20*300, 480, 3200);

		// GDC
		GT_Values.RA.addBlastRecipe(
				Util.getStackofAmountFromOreDict("dustGDC", 16), GT_Utility.getIntegratedCircuit(1),
				null, null,
				new ItemStack(ceramicGDC.getInstance(), 1),null,
				20*300, 1920, 4200);

		// LSCF
		GT_Values.RA.addBlastRecipe(
				Util.getStackofAmountFromOreDict("dustLSCF", 16), GT_Utility.getIntegratedCircuit(1),
				null, null,
				new ItemStack(ceramicLSCF.getInstance(), 1),null,
				20*300, 7680, 4200);

//  -  -  -  -  Plates  -  -  -  -  //
		// YSZ
		GT_Values.RA.addCutterRecipe(Util.getStackofAmountFromOreDict("ceramicYSZ", 1),
				new ItemStack(plateYSZ.getInstance(), 4),null,
				20*40, 480, true);
		// GDC
		GT_Values.RA.addCutterRecipe(Util.getStackofAmountFromOreDict("ceramicGDC", 1),
				new ItemStack(plateGDC.getInstance(), 4),null,
				20*40, 1920, true);
		// LSCF
		GT_Values.RA.addCutterRecipe(Util.getStackofAmountFromOreDict("ceramicLSCF", 1),
				new ItemStack(plateLSCF.getInstance(), 4),null,
				20*40, 7680, true);

		System.out.println("...done");
	}

}
