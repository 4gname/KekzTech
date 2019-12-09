package kekztech;

import blocks.Block_ControlRod;
import blocks.Block_GDCUnit;
import blocks.Block_ReactorChamber_OFF;
import blocks.Block_ReactorChamber_ON;
import blocks.Block_TFFTCasing;
import blocks.Block_TFFTMultiHatch;
import blocks.Block_TFFTStorageFieldBlockT1;
import blocks.Block_TFFTStorageFieldBlockT2;
import blocks.Block_TFFTStorageFieldBlockT3;
import blocks.Block_TFFTStorageFieldBlockT4;
import blocks.Block_TFFTStorageFieldBlockT5;
import blocks.Block_YSZUnit;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
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
import items.ErrorItem;
import items.MetaItem_CraftingComponent;
import items.MetaItem_ReactorComponent;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import tileentities.GTMTE_FluidMultiStorage;
import tileentities.GTMTE_ModularNuclearReactor;
import tileentities.GTMTE_SOFuelCellMK1;
import tileentities.GTMTE_SOFuelCellMK2;
import tileentities.TE_TFFTMultiHatch;
import util.Util;

/**
 * My GT-Meta-IDs are: 13101 - 13500
 * 
 * @author kekzdealer
 *
 */
@Mod(modid = KekzCore.MODID, name = KekzCore.NAME, version = KekzCore.VERSION, 
		dependencies =
			  "required-after:IC2; "
			+ "required-after:gregtech;"
			+ "after:bartworks"
		)
public class KekzCore {
	
	public static final String NAME = "KekzTech";
	public static final String MODID = "kekztech";
	public static final String VERSION = "0.2.2";
	
	@Mod.Instance("kekztech")
	public static KekzCore instance;

	private GTMTE_SOFuelCellMK1 sofc1;
	private GTMTE_SOFuelCellMK2 sofc2;
	@SuppressWarnings("unused")
	private GTMTE_ModularNuclearReactor mdr;
	private GTMTE_FluidMultiStorage fms;
	
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		// Items
		ErrorItem.getInstance().registerItem();
		MetaItem_ReactorComponent.getInstance().registerItem();
		MetaItem_CraftingComponent.getInstance().registerItem();
		Items.registerOreDictNames();
		// Blocks
		Block_YSZUnit.getInstance().registerBlock();
		Block_GDCUnit.getInstance().registerBlock();
		Block_TFFTCasing.getInstance().registerBlock();
		Block_TFFTStorageFieldBlockT1.getInstance().registerBlock();
		Block_TFFTStorageFieldBlockT2.getInstance().registerBlock();
		Block_TFFTStorageFieldBlockT3.getInstance().registerBlock();
		Block_TFFTStorageFieldBlockT4.getInstance().registerBlock();
		Block_TFFTStorageFieldBlockT5.getInstance().registerBlock();
		Block_TFFTMultiHatch.getInstance().registerBlock();
		Block_ReactorChamber_OFF.getInstance().registerBlock();
		Block_ReactorChamber_ON.getInstance().registerBlock();
		Block_ControlRod.getInstance().registerBlock();
		// Register TileEntities
		GameRegistry.registerTileEntity(TE_TFFTMultiHatch.class, "kekztech_tfftmultihatch_tile");
	}
	
	@Mod.EventHandler
	public void init(FMLInitializationEvent event	) {
		// Multiblock controllers
		sofc1 = new GTMTE_SOFuelCellMK1(13101, "multimachine.fuelcellmk1", "Solid-Oxide Fuel Cell Mk I");
		sofc2 = new GTMTE_SOFuelCellMK2(13102, "multimachine.fuelcellmk2", "Solid-Oxide Fuel Cell Mk II");
		mdr = new GTMTE_ModularNuclearReactor(13103, "multimachine.nuclearreactor", "Nuclear Reactor");
		fms = new GTMTE_FluidMultiStorage(13104, "multimachine.tf_fluidtank", "T.F.F.T");
	}
	
	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		System.out.println("Registering KekzTech recipes...");
		
		final MetaItem_CraftingComponent craftingItem = MetaItem_CraftingComponent.getInstance();
		final MetaItem_ReactorComponent reactorItem = MetaItem_ReactorComponent.getInstance();

		// Multiblock Controllers
		final Object[] mk1_recipe = {
				"CCC", "PHP", "FBL",
				'C', OrePrefixes.circuit.get(Materials.Advanced),
				'P', ItemList.Electric_Pump_HV.get(1L),
				'H', ItemList.Hull_HV.get(1L),
				'F', GT_OreDictUnificator.get(OrePrefixes.pipeSmall, Materials.StainlessSteel, 1),
				'B', GT_OreDictUnificator.get(OrePrefixes.cableGt02, Materials.Gold, 1),
				'L', GT_OreDictUnificator.get(OrePrefixes.pipeLarge, Materials.StainlessSteel, 1)
		};		
		GT_ModHandler.addCraftingRecipe(sofc1.getStackForm(1), mk1_recipe);
		final Object[] mk2_recipe = {
				"CCC", "PHP", "FBL",
				'C', OrePrefixes.circuit.get(Materials.Master),
				'P', ItemList.Electric_Pump_IV.get(1L),
				'H', ItemList.Hull_IV.get(1L),
				'F', GT_OreDictUnificator.get(OrePrefixes.pipeSmall, Materials.Ultimate, 1),
				'B', Util.getStackofAmountFromOreDict("wireGt04SuperconductorEV", 1),
				'L', GT_OreDictUnificator.get(OrePrefixes.pipeMedium, Materials.Ultimate, 1)
		};
		GT_ModHandler.addCraftingRecipe(sofc2.getStackForm(1), mk2_recipe);
		final Object[] tfft_recipe = {
				"HFH", "PVP", "CFC",
				'H', OrePrefixes.pipeMedium.get(Materials.StainlessSteel),
				'F', ItemList.Field_Generator_MV.get(1L),
				'P', ItemList.Electric_Pump_HV.get(1L),
				'V', OrePrefixes.rotor.get(Materials.VibrantAlloy),
				'C', OrePrefixes.circuit.get(Materials.Data)
		};
		GT_ModHandler.addCraftingRecipe(fms.getStackForm(1), tfft_recipe);
		
		// Ceramic Electrolyte Units
		final ItemStack[] yszUnit = {
				GT_Utility.getIntegratedCircuit(6),
				craftingItem.getStackOfAmountFromDamage(Items.YSZCeramicPlate.getMetaID(), 4),
				GT_OreDictUnificator.get(OrePrefixes.frameGt, Materials.Yttrium, 1),
				GT_OreDictUnificator.get(OrePrefixes.rotor, Materials.StainlessSteel, 1),
				ItemList.Electric_Motor_HV.get(1L),
		};
		GT_Values.RA.addAssemblerRecipe(
				yszUnit, 
				Materials.Hydrogen.getGas(4000), 
				new ItemStack(Block_YSZUnit.getInstance(), 1), 
				1200, 480);
		final ItemStack[] gdcUnit = {
				GT_Utility.getIntegratedCircuit(6),
				craftingItem.getStackOfAmountFromDamage(Items.GDCCeramicPlate.getMetaID(), 8),
				GT_OreDictUnificator.get(OrePrefixes.frameGt, Materials.Gadolinium, new ItemStack(ErrorItem.getInstance(), 1), 1),
				GT_OreDictUnificator.get(OrePrefixes.rotor, Materials.Desh, new ItemStack(ErrorItem.getInstance(), 1), 1),
				ItemList.Electric_Motor_IV.get(1L),
		};
		GT_Values.RA.addAssemblerRecipe(
				gdcUnit, 
				Materials.Hydrogen.getGas(16000), 
				new ItemStack(Block_GDCUnit.getInstance(), 1), 
				2400, 1920);
		
		// T.F.F.T Structure blocks
		final ItemStack[] tfftcasing = {
			GT_Utility.getIntegratedCircuit(6),
			GT_OreDictUnificator.get(OrePrefixes.plate, Materials.DarkSteel, 3),
			GT_OreDictUnificator.get(OrePrefixes.plate, Materials.EnderPearl, 3),
			GT_OreDictUnificator.get(OrePrefixes.frameGt, Materials.StainlessSteel, 1),
		};
		GT_Values.RA.addAssemblerRecipe(
				tfftcasing, 
				FluidRegistry.getFluidStack("molten.polytetrafluoroethylene", 144),
				new ItemStack(Block_TFFTCasing.getInstance(), 1), 
				200, 256);
		final ItemStack[] tfftstoragefield1 = {
			GT_Utility.getIntegratedCircuit(6),
			GT_OreDictUnificator.get(OrePrefixes.circuit, Materials.Advanced, 1),
			GT_OreDictUnificator.get(OrePrefixes.plate, Materials.PulsatingIron, 1),
			GT_OreDictUnificator.get(OrePrefixes.pipeLarge, Materials.Steel, 1),
			ItemList.Electric_Pump_LV.get(1L)
		};
		GT_Values.RA.addAssemblerRecipe(
				tfftstoragefield1, 
				FluidRegistry.getFluidStack("molten.glass", 144),
				new ItemStack(Block_TFFTStorageFieldBlockT1.getInstance(), 1), 
				200, 256);
		final ItemStack[] tfftstoragefield2 = {
				GT_Utility.getIntegratedCircuit(6),
				GT_OreDictUnificator.get(OrePrefixes.circuit, Materials.Advanced, 2),
				GT_OreDictUnificator.get(OrePrefixes.plate, Materials.PulsatingIron, 4),
				GT_OreDictUnificator.get(OrePrefixes.pipeMedium, Materials.StainlessSteel, 1),
				ItemList.Electric_Pump_MV.get(1L)
			};
		GT_Values.RA.addAssemblerRecipe(
				tfftstoragefield2, 
				FluidRegistry.getFluidStack("molten.plastic", 576),
				new ItemStack(Block_TFFTStorageFieldBlockT2.getInstance(), 1), 
				200, 480);
		final ItemStack[] tfftstoragefield3 = {
				GT_Utility.getIntegratedCircuit(6),
				GT_OreDictUnificator.get(OrePrefixes.circuit, Materials.Data, 4),
				GT_OreDictUnificator.get(OrePrefixes.plate, Materials.VibrantAlloy, 2),
				GT_OreDictUnificator.get(OrePrefixes.pipeMedium, Materials.Titanium, 1),
				ItemList.Field_Generator_MV.get(1L),
				ItemList.Electric_Pump_HV.get(2L)
			};
		GT_Values.RA.addAssemblerRecipe(
				tfftstoragefield3, 
				FluidRegistry.getFluidStack("molten.epoxid", 576),
				new ItemStack(Block_TFFTStorageFieldBlockT3.getInstance(), 1), 
				300, 1920);
		final ItemStack[] tfftstoragefield4 = {
				GT_Utility.getIntegratedCircuit(6),
				GT_OreDictUnificator.get(OrePrefixes.circuit, Materials.Elite, 4),
				GT_OreDictUnificator.get(OrePrefixes.plateTriple, Materials.NiobiumTitanium, 1),
				GT_OreDictUnificator.get(OrePrefixes.pipeHuge, Materials.TungstenSteel, 1),
				ItemList.Field_Generator_HV.get(1L),
				ItemList.Electric_Pump_EV.get(1L)
			};
		GT_Values.RA.addAssemblerRecipe(
				tfftstoragefield4,
				FluidRegistry.getFluidStack("molten.epoxid", 1152),
				new ItemStack(Block_TFFTStorageFieldBlockT5.getInstance(), 1),
				400, 4098);

        final ItemStack[] tfftstoragefield5 = {
                GT_Utility.getIntegratedCircuit(6),
                GT_OreDictUnificator.get(OrePrefixes.circuit, Materials.Elite, 4),
                GT_OreDictUnificator.get(OrePrefixes.plate, Materials.NiobiumTitanium, 1),
                GT_OreDictUnificator.get(OrePrefixes.pipeMedium, Materials.TungstenSteel, 1),
                ItemList.Field_Generator_HV.get(1L),
                ItemList.Electric_Pump_EV.get(1L)
        };
        GT_Values.RA.addAssemblerRecipe(
                tfftstoragefield5,
                FluidRegistry.getFluidStack("molten.epoxid", 1152),
                new ItemStack(Block_TFFTStorageFieldBlockT5.getInstance(), 1),
                400, 4098);


		final Object[] multi_hatch = {
				"PRP", "UFU", "PRP",
				'P', GT_OreDictUnificator.get(OrePrefixes.pipeTiny, Materials.NiobiumTitanium, 1),
				'R', GT_OreDictUnificator.get(OrePrefixes.rotor, Materials.StainlessSteel, 1),
				'U', ItemList.Electric_Pump_IV.get(1L),
				'F', ItemList.Field_Generator_HV.get(1L)
		};		
		GT_ModHandler.addCraftingRecipe(new ItemStack(Block_TFFTMultiHatch.getInstance()), multi_hatch);
		
		// Reactor structure blocks
		final ItemStack[] controlrod = {
				GT_Utility.getIntegratedCircuit(6),
				GT_OreDictUnificator.get(OrePrefixes.pipeHuge, Materials.Lead, 1),
				GT_OreDictUnificator.get(OrePrefixes.pipeMedium, Materials.Steel, 4),
				GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Carbon, 64)
			};
		GT_Values.RA.addAssemblerRecipe(
				controlrod, 
				null,
				new ItemStack(Block_ControlRod.getInstance(), 1), 
				800, 480);
		final ItemStack[] reactorchamber = {
				GT_Utility.getIntegratedCircuit(6),
				GT_OreDictUnificator.get(OrePrefixes.pipeHuge, Materials.Lead, 1),
				GT_OreDictUnificator.get(OrePrefixes.pipeTiny, Materials.Lead, 9),
				GT_OreDictUnificator.get(OrePrefixes.ring, Materials.TungstenSteel, 18),
				GT_OreDictUnificator.get(OrePrefixes.plateDense, Materials.Steel, 2),
			};
		GT_Values.RA.addAssemblerRecipe(
				reactorchamber, 
				FluidRegistry.getFluidStack("wet.concrete", 144),
				new ItemStack(Block_ReactorChamber_OFF.getInstance(), 1), 
				1600, 480);
		
		// Ceramic plates
		GT_Values.RA.addAlloySmelterRecipe(
				craftingItem.getStackOfAmountFromDamage(Items.YSZCeramicDust.getMetaID(), Loader.isModLoaded("bartworks") ? 3 : 10), 
				ItemList.Shape_Mold_Plate.get(1),
				craftingItem.getStackOfAmountFromDamage(Items.YSZCeramicPlate.getMetaID(), 1), 
				400, 480);
		GT_Values.RA.addFormingPressRecipe(
				craftingItem.getStackOfAmountFromDamage(Items.GDCCeramicDust.getMetaID(), 10), 
				ItemList.Shape_Mold_Plate.get(1),
				craftingItem.getStackOfAmountFromDamage(Items.GDCCeramicPlate.getMetaID(), 1), 
				800, 480);
		
		// Dusts
		GT_Values.RA.addMixerRecipe(Materials.Boron.getDust(1),	Materials.Arsenic.getDust(1), GT_Utility.getIntegratedCircuit(6), null, 
				null, null, craftingItem.getStackOfAmountFromDamage(Items.BoronArsenideDust.getMetaID(), 2), 
				100, 1920);
		GT_Values.RA.addChemicalRecipe(
				Materials.Ammonia.getCells(2),
				Materials.CarbonDioxide.getCells(1),
				null,
				null,
				craftingItem.getStackOfAmountFromDamage(Items.AmineCarbamiteDust.getMetaID(), 1),
				Util.getStackofAmountFromOreDict("cellEmpty", 3), 
				400, 30);
		GT_Values.RA.addChemicalRecipe(
				craftingItem.getStackOfAmountFromDamage(Items.AmineCarbamiteDust.getMetaID(), 1),
				Materials.Diamond.getDust(16),
				Materials.CarbonDioxide.getGas(1000),
				null,
				craftingItem.getStackOfAmountFromDamage(Items.IsotopicallyPureDiamondDust.getMetaID(), 1),
				null, 1200, 480);
		if (!Loader.isModLoaded("bartworks")) {
			GT_Values.RA.addChemicalRecipe(
					Materials.Yttrium.getDust(1), GT_Utility.getIntegratedCircuit(6), Materials.Oxygen.getGas(3000),
					null, craftingItem.getStackOfAmountFromDamage(Items.YttriaDust.getMetaID(), 1), null,
					400, 30);
			GT_Values.RA.addChemicalRecipe(
					Util.getStackofAmountFromOreDict("dustZirconium", 1), GT_Utility.getIntegratedCircuit(6), Materials.Oxygen.getGas(2000),
					null, craftingItem.getStackOfAmountFromDamage(Items.ZirconiaDust.getMetaID(), 1), null,
					400, 30);
		}
		GT_Values.RA.addChemicalRecipe(
				Materials.Cerium.getDust(2), GT_Utility.getIntegratedCircuit(6), Materials.Oxygen.getGas(3000),
				null, craftingItem.getStackOfAmountFromDamage(Items.CeriaDust.getMetaID(), 2), null, 
				400, 30);
		GT_Values.RA.addMixerRecipe(
				Items.YttriaDust.getOreDictedItemStack(1),
				Items.ZirconiaDust.getOreDictedItemStack(5),
				GT_Utility.getIntegratedCircuit(6), null, null, null,
				craftingItem.getStackOfAmountFromDamage(Items.YSZCeramicDust.getMetaID(), 6), 
				400, 96);
		GT_Values.RA.addMixerRecipe(
				GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Gadolinium, new ItemStack(ErrorItem.getInstance(), 1), 1),
				craftingItem.getStackOfAmountFromDamage(Items.CeriaDust.getMetaID(), 9),
				GT_Utility.getIntegratedCircuit(6), null, null, null,
				craftingItem.getStackOfAmountFromDamage(Items.GDCCeramicDust.getMetaID(), 10), 
				400, 1920);
		
		// Crystals
		GT_Values.RA.addAutoclaveRecipe(
				craftingItem.getStackOfAmountFromDamage(Items.IsotopicallyPureDiamondDust.getMetaID(), 4), 
				Materials.CarbonDioxide.getGas(16000), 
				craftingItem.getStackOfAmountFromDamage(Items.IsotopicallyPureDiamondCrystal.getMetaID(), 1), 10000, 2400, 7680);
		GT_Values.RA.addAutoclaveRecipe(
				craftingItem.getStackOfAmountFromDamage(Items.IsotopicallyPureDiamondDust.getMetaID(), 4), 
				Materials.CarbonDioxide.getGas(16000), 
				craftingItem.getStackOfAmountFromDamage(Items.IsotopicallyPureDiamondCrystal.getMetaID(), 1), 10000, 2400, 1920);
		
		// Heat Pipes
		GT_Values.RA.addLatheRecipe(
				GT_OreDictUnificator.get(OrePrefixes.stick, Materials.AnnealedCopper, 1),  
				craftingItem.getStackFromDamage(Items.CopperHeatPipe.getMetaID()),
				null, 120, 120);
		GT_Values.RA.addLatheRecipe(
				GT_OreDictUnificator.get(OrePrefixes.stick, Materials.Silver, 1),
				craftingItem.getStackFromDamage(Items.SilverHeatPipe.getMetaID()),
				null, 120, 480);
		GT_Values.RA.addLatheRecipe(
				craftingItem.getStackOfAmountFromDamage(Items.BoronArsenideCrystal.getMetaID(), 4),  
				craftingItem.getStackFromDamage(Items.BoronArsenideHeatPipe.getMetaID()),
				null, 1200, 1920);
		GT_Values.RA.addLatheRecipe(
				craftingItem.getStackOfAmountFromDamage(Items.IsotopicallyPureDiamondCrystal.getMetaID(), 4),  
				craftingItem.getStackFromDamage(Items.DiamondHeatPipe.getMetaID()),
				null, 1200, 7680);
		
		// Heat Vents
		final ItemStack[] t1HeatVent = {
				craftingItem.getStackOfAmountFromDamage(Items.CopperHeatPipe.getMetaID(), 2),
				ItemList.Electric_Motor_MV.get(1L),
				GT_OreDictUnificator.get(OrePrefixes.rotor, Materials.Steel, new ItemStack(ErrorItem.getInstance(), 1), 1),
				GT_OreDictUnificator.get(OrePrefixes.plateDouble, Materials.Steel, new ItemStack(ErrorItem.getInstance(), 1), 2),
				GT_OreDictUnificator.get(OrePrefixes.screw, Materials.Steel, new ItemStack(ErrorItem.getInstance(), 1), 8),
				GT_OreDictUnificator.get(OrePrefixes.circuit, Materials.Good, 1),
				GT_Utility.getIntegratedCircuit(6)
		};
		GT_Values.RA.addAssemblerRecipe(
				t1HeatVent, 
				FluidRegistry.getFluidStack("molten.copper", 144),
				reactorItem.getStackFromDamage(Items.T1HeatVent.getMetaID()),
				200, 120);
		final ItemStack[] t2HeatVent = {
				craftingItem.getStackOfAmountFromDamage(Items.SilverHeatPipe.getMetaID(), 2),
				ItemList.Electric_Motor_HV.get(1L),
				GT_OreDictUnificator.get(OrePrefixes.rotor, Materials.Aluminium, new ItemStack(ErrorItem.getInstance(), 1), 1),
				GT_OreDictUnificator.get(OrePrefixes.plateDouble, Materials.Aluminium, new ItemStack(ErrorItem.getInstance(), 1), 2),
				GT_OreDictUnificator.get(OrePrefixes.screw, Materials.Aluminium, new ItemStack(ErrorItem.getInstance(), 1), 8),
				GT_OreDictUnificator.get(OrePrefixes.circuit, Materials.Advanced, 1),
				GT_Utility.getIntegratedCircuit(6)
		};
		GT_Values.RA.addAssemblerRecipe(
				t2HeatVent, 
				FluidRegistry.getFluidStack("molten.silver", 144),
				reactorItem.getStackFromDamage(Items.T2HeatVent.getMetaID()),
				400, 480);
		final ItemStack[] t3HeatVent = {
				craftingItem.getStackOfAmountFromDamage(Items.BoronArsenideHeatPipe.getMetaID(), 2),
				ItemList.Electric_Motor_IV.get(1L),
				GT_OreDictUnificator.get(OrePrefixes.rotor, Materials.TungstenSteel, new ItemStack(ErrorItem.getInstance(), 1), 1),
				GT_OreDictUnificator.get(OrePrefixes.plateDouble, Materials.TungstenSteel, new ItemStack(ErrorItem.getInstance(), 1), 2),
				GT_OreDictUnificator.get(OrePrefixes.screw, Materials.Tungsten, new ItemStack(ErrorItem.getInstance(), 1), 8),
				GT_OreDictUnificator.get(OrePrefixes.circuit, Materials.Data, 1),
				GT_Utility.getIntegratedCircuit(6)
		};
		GT_Values.RA.addAssemblerRecipe(
				t3HeatVent, 
				FluidRegistry.getFluidStack("molten.gallium", 576),
				reactorItem.getStackFromDamage(Items.T3HeatVent.getMetaID()),
				800, 7680);
		
		System.out.println("...done");
	}
	
}
