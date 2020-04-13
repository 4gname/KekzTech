package tileentities;

import blocks.Block_YSZUnit;
import gregtech.GT_Mod;
import gregtech.api.GregTech_API;
import gregtech.api.enums.Materials;
import gregtech.api.enums.Textures;
import gregtech.api.gui.GT_GUIContainer_MultiMachine;
import gregtech.api.interfaces.ITexture;
import gregtech.api.interfaces.metatileentity.IMetaTileEntity;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.items.GT_MetaGenerated_Tool;
import gregtech.api.metatileentity.implementations.GT_MetaTileEntity_MultiBlockBase;
import gregtech.api.objects.GT_RenderedTexture;
import gregtech.api.util.GT_ModHandler;
import gregtech.api.util.GT_Recipe;
import gregtech.api.util.GT_Recipe.GT_Recipe_Map;
import gregtech.api.util.GT_Utility;
import kekztech.MultiBlockTooltipBuilder;
import net.minecraft.block.Block;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidStack;
import org.lwjgl.input.Keyboard;
import util.Vector3i;
import util.Vector3ic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class GTMTE_KekzGenT1_Steam extends GT_MetaTileEntity_MultiBlockBase {


	private final Block CASING = GregTech_API.sBlockCasings4;
	private final int CASING_META = 1;
	private final int CASING_TEXTURE_ID = 49;

	private final int LUBRICANT_PER_TICK = 10;
	private final int EU_PER_TICK = 1680;
	private final int DIST_AND_STEAM_PER_TICK = 900;

	protected int fuelConsumption = 0;

	protected int baseEff = 0;
	protected int optFlow = 0;
	protected int storedFluid = 0;
	protected int counter = 0;

	public GTMTE_KekzGenT1_Steam(int aID, String aName, String aNameRegional) {
		super(aID, aName, aNameRegional);

	}

	public GTMTE_KekzGenT1_Steam(String aName) {
		super(aName);
		
	}
	
	@Override
	public IMetaTileEntity newMetaEntity(IGregTechTileEntity var1) {
		return new GTMTE_KekzGenT1_Steam(super.mName);
	}

	@Override
	public String[] getDescription() {
		final MultiBlockTooltipBuilder b = new MultiBlockTooltipBuilder();
		b.addInfo("Oxidizes gas fuels to generate electricity without polluting the environment")
				.addInfo("Consumes fuel with up to 97% efficiency each second")
				.addInfo("Steam production requires the SOFC to heat up completely first")
				.addInfo("Outputs " + EU_PER_TICK + "EU/t and " + DIST_AND_STEAM_PER_TICK + "L/t distilled water")
				.addInfo("Additionally requires " + LUBRICANT_PER_TICK + "L/t Lubricant gas")
				.addSeparator()
				.beginStructureBlock(3, 3, 5)
				.addController("Front Center")
				.addDynamoHatch("Back Center")
				.addOtherStructurePart("YSZ Ceramic Unit", "3x, Center 1x1x3")
				.addOtherStructurePart("Reinforced Glass", "6x, touching the electrolyte units on the horizontal sides")
				.addCasingInfo("Clean Stainless Steel Casing", 12)
				.addMaintenanceHatch("Instead of any casing")
				.addIOHatches("Instead of any casing")
				.signAndFinalize(": "+EnumChatFormatting.YELLOW+"4gname");
		if(!Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
			return b.getInformation();
		} else {
			return b.getStructureInformation();
		}
	}
	
	@Override
	public ITexture[] getTexture(final IGregTechTileEntity aBaseMetaTileEntity, final byte aSide, final byte aFacing,
			final byte aColorIndex, final boolean aActive, final boolean aRedstone) {
		return aSide == aFacing
				? new ITexture[]{Textures.BlockIcons.CASING_BLOCKS[CASING_TEXTURE_ID],
						new GT_RenderedTexture(aActive ? 
								Textures.BlockIcons.OVERLAY_FRONT_HEAT_EXCHANGER_ACTIVE 
								: Textures.BlockIcons.OVERLAY_FRONT_HEAT_EXCHANGER)}
				: new ITexture[]{Textures.BlockIcons.CASING_BLOCKS[CASING_TEXTURE_ID]};
	}
	
	public Object getClientGUI(int aID, InventoryPlayer aPlayerInventory, IGregTechTileEntity aBaseMetaTileEntity) {
		return new GT_GUIContainer_MultiMachine(aPlayerInventory, aBaseMetaTileEntity, this.getLocalName(),
				"MultiblockDisplay.png");
	}
	
	@Override
	public boolean isCorrectMachinePart(ItemStack stack) {
		return true;
	}


	@Override
	public boolean checkRecipe(ItemStack aStack) {
		ArrayList<FluidStack> tFluids = getStoredFluids();
		if (tFluids.size() > 0) {
			if (baseEff == 0 || optFlow == 0 || counter >= 512 || this.getBaseMetaTileEntity().hasWorkJustBeenEnabled()
					|| this.getBaseMetaTileEntity().hasInventoryBeenModified()) {
				counter = 0;
				baseEff = 1;
				optFlow = DIST_AND_STEAM_PER_TICK;
				if(optFlow<=0 || baseEff<=0){
					stopMachine();//in case the turbine got removed
					return false;
				}
			} else {
				counter++;
			}
		}
		int newPower = fluidIntoPower(tFluids, optFlow);  // How much the turbine should be producing with this flow
		int difference = newPower - this.mEUt; // difference between current output and new output
		int maxChangeAllowed = Math.max(10, GT_Utility.safeInt((long)Math.abs(difference)/100));
		if (Math.abs(difference) > maxChangeAllowed) { // If this difference is too big, use the maximum allowed change
			int change = maxChangeAllowed * (difference > 0 ? 1 : -1); // Make the change positive or negative.
			this.mEUt += change; // Apply the change
		} else
			this.mEUt = newPower;

		if(!super.depleteInput(Materials.Lubricant.getFluid(LUBRICANT_PER_TICK))) {
			super.mEUt = 0;
			super.mEfficiency = 0;
			return false;
		}
		if (this.mEUt <= 0) {
			//stopMachine();
			this.mEUt=0;
			this.mEfficiency=0;
			return false;
		} else {
			this.mMaxProgresstime = 1;
			this.mEfficiencyIncrease = 10;
			return true;
		}
	}

	int fluidIntoPower(ArrayList<FluidStack> aFluids, int aOptFlow) {
		int tEU = 0;
		int flow = 0;
		storedFluid=0;
		flow = DIST_AND_STEAM_PER_TICK;
		if(flow<=0)return 0;
		if (depleteInput(GT_ModHandler.getSteam(DIST_AND_STEAM_PER_TICK))) {
			addOutput(GT_ModHandler.getDistilledWater(DIST_AND_STEAM_PER_TICK));
		} else {
			stopMachine();
		}
		tEU = EU_PER_TICK;
		return tEU;
	}
	
	public Vector3ic rotateOffsetVector(Vector3ic forgeDirection, int x, int y, int z) {
		final Vector3i offset = new Vector3i();
		
		// either direction on z-axis
		if(forgeDirection.x() == 0 && forgeDirection.z() == -1) {
			offset.x = x;
			offset.y = y;
			offset.z = z;
		}
		if(forgeDirection.x() == 0 && forgeDirection.z() == 1) {
			offset.x = -x;
			offset.y = y;
			offset.z = -z;
		}
		// either direction on x-axis
		if(forgeDirection.x() == -1 && forgeDirection.z() == 0) {
			offset.x = z;
			offset.y = y;
			offset.z = -x;
		}
		if(forgeDirection.x() == 1 && forgeDirection.z() == 0) {
			offset.x = -z;
			offset.y = y;
			offset.z = x;
		}
		// either direction on y-axis
		if(forgeDirection.y() == -1) {
			offset.x = x;
			offset.y = z;
			offset.z = y;
		}
		
		return offset;
	}
	
	@Override
	public boolean checkMachine(IGregTechTileEntity thisController, ItemStack guiSlotItem) {		
		// Figure out the vector for the direction the back face of the controller is facing
		final Vector3ic forgeDirection = new Vector3i(
				ForgeDirection.getOrientation(thisController.getBackFacing()).offsetX,
				ForgeDirection.getOrientation(thisController.getBackFacing()).offsetY,
				ForgeDirection.getOrientation(thisController.getBackFacing()).offsetZ
				);
		int minCasingAmount = 12; 
		boolean formationChecklist = true; // if this is still true at the end, machine is good to go :)
		
		// Front slice			
		for(int X = -1; X <= 1; X++) {
			for(int Y = -1; Y <= 1; Y++) {
				if(X == 0 && Y == 0) {
					continue; // is controller
				}
				
				// Get next TE
				final Vector3ic offset = rotateOffsetVector(forgeDirection, X, Y, 0);
				IGregTechTileEntity currentTE = 
						thisController.getIGregTechTileEntityOffset(offset.x(), offset.y(), offset.z());
				
				// Tries to add TE as either of those kinds of hatches.
				// The number is the texture index number for the texture that needs to be painted over the hatch texture (TAE for GT++)
				if (   !super.addMaintenanceToMachineList(currentTE, CASING_TEXTURE_ID) 
					&& !super.addInputToMachineList(currentTE, CASING_TEXTURE_ID)
					&& !super.addOutputToMachineList(currentTE, CASING_TEXTURE_ID)) {
					
					// If it's not a hatch, is it the right casing for this machine? Check block and block meta.
					if ((thisController.getBlockOffset(offset.x(), offset.y(), offset.z()) == CASING) 
							&& (thisController.getMetaIDOffset(offset.x(), offset.y(), offset.z()) == CASING_META)) {
						// Seems to be valid casing. Decrement counter.
						minCasingAmount--;
					} else {
						formationChecklist = false;
					}
				}
			}
		}
		
		// Middle three slices
		for(int X = -1; X <= 1; X++) {
			for(int Y = -1; Y <= 1; Y++) {
				for(int Z = -1; Z >= -3; Z--) {
					final Vector3ic offset = rotateOffsetVector(forgeDirection, X, Y, Z);
					if(X == 0 && Y == 0) {
						if(!thisController.getBlockOffset(offset.x(), offset.y(), offset.z()).getUnlocalizedName()
								.equals(Block_YSZUnit.getInstance().getUnlocalizedName())) {
							formationChecklist = false;
						}
						continue;
					}
					if(Y == 0 && (X == -1 || X == 1)) {
						if(!thisController.getBlockOffset(offset.x(), offset.y(), offset.z()).getUnlocalizedName()
								.equals("blockAlloyGlass")) {
							formationChecklist = false;
						}
						continue;
					}
					// Get next TE
					IGregTechTileEntity currentTE = 
							thisController.getIGregTechTileEntityOffset(offset.x(), offset.y(), offset.z());// x, y ,z
					
					// Tries to add TE as either of those kinds of hatches.
					// The number is the texture index number for the texture that needs to be painted over the hatch texture (TAE for GT++)
					if (   !super.addMaintenanceToMachineList(currentTE, CASING_TEXTURE_ID) 
						&& !super.addInputToMachineList(currentTE, CASING_TEXTURE_ID)
						&& !super.addOutputToMachineList(currentTE, CASING_TEXTURE_ID)) {
						
						// If it's not a hatch, is it the right casing for this machine? Check block and block meta.
						if ((thisController.getBlockOffset(offset.x(), offset.y(), offset.z()) == CASING) 
								&& (thisController.getMetaIDOffset(offset.x(), offset.y(), offset.z()) == CASING_META)) {
							// Seems to be valid casing. Decrement counter.
							minCasingAmount--;
						} else {
							formationChecklist = false;
						}
					}
				}
			}
		}
		
		// Back slice
		for(int X = -1; X <= 1; X++) {
			for(int Y = -1; Y <= 1; Y++) {
				// Get next TE
				final Vector3ic offset = rotateOffsetVector(forgeDirection, X, Y, -4);
				IGregTechTileEntity currentTE = 
						thisController.getIGregTechTileEntityOffset(offset.x(), offset.y(), offset.z());// x, y ,z
				
				// Tries to add TE as either of those kinds of hatches.
				// The number is the texture index number for the texture that needs to be painted over the hatch texture (TAE for GT++)
				if (   !super.addMaintenanceToMachineList(currentTE, CASING_TEXTURE_ID) 
					&& !super.addInputToMachineList(currentTE, CASING_TEXTURE_ID)
					&& !super.addOutputToMachineList(currentTE, CASING_TEXTURE_ID)
					&& !super.addDynamoToMachineList(currentTE, CASING_TEXTURE_ID)) {
					
					// If it's not a hatch, is it the right casing for this machine? Check block and block meta.
					if ((thisController.getBlockOffset(offset.x(), offset.y(), offset.z()) == CASING) 
							&& (thisController.getMetaIDOffset(offset.x(), offset.y(), offset.z()) == CASING_META)) {
						// Seems to be valid casing. Decrement counter.
						minCasingAmount--;
					} else {
						formationChecklist = false;
					}
				}
			}
		}		
		
		if(minCasingAmount > 0) {
			formationChecklist = false;
		}
		
		if(this.mDynamoHatches.size() != 1) {
			System.out.println("Exactly one dynamo hatch is required!");
			formationChecklist = false;
		}
		if(this.mInputHatches.size() < 2) {
			System.out.println("At least two input hatches are required!");
			formationChecklist = false;
		}
		
		if(this.mMaintenanceHatches.size() < 1) {
			System.out.println("You need a maintenance hatch to do maintenance.");
		}
		
		return formationChecklist;
	}


	public String[] getInfoData() {

		return new String[]{
/* 1 line */	"Solid-Oxide Generator MK I" + EnumChatFormatting.RESET,
/* 2 line */	StatCollector.translateToLocal("GT5U.engine.output") + ": "
						+ EnumChatFormatting.GREEN + this.mEUt * this.mEfficiency / 10000
						+ EnumChatFormatting.RESET + " EU/t",
/* 3 line */	StatCollector.translateToLocal("GT5U.engine.efficiency") + ": "
						+ EnumChatFormatting.YELLOW + (float)this.mEfficiency / 100.0F
						+ EnumChatFormatting.YELLOW + " %",
/* 4 line */	"Input Steam: " + (((float)this.mEfficiency / 100.0F == 100)
						? EnumChatFormatting.GREEN + "" +DIST_AND_STEAM_PER_TICK + EnumChatFormatting.RESET + " L/t"
						: EnumChatFormatting.GREEN + "0" + EnumChatFormatting.RESET + " L/t"),
/* 5 line */	"Maintenance: " + ((super.getRepairStatus() == super.getIdealStatus())
						? EnumChatFormatting.GREEN + "No Problems" + EnumChatFormatting.RESET
						: EnumChatFormatting.RED + "Has Problems" + EnumChatFormatting.RESET),
/* 6 line */	"Fuel supply: " + ((this.mEUt * this.mEfficiency / 10000 >= 1)
						? EnumChatFormatting.RED + "" + fuelConsumption + EnumChatFormatting.RESET + " L/t"
						: EnumChatFormatting.RED + "0" + EnumChatFormatting.RESET + " L/t"),
/* 7 line */	"Oxygen supply: " + ((this.mEUt * this.mEfficiency / 10000 >= 1)
						? EnumChatFormatting.RED + "" + LUBRICANT_PER_TICK + EnumChatFormatting.RESET + " L/t"
						: EnumChatFormatting.RED + "0" + EnumChatFormatting.RESET + " L/t")
		};
	}
	
	@Override
	public int getMaxEfficiency(ItemStack stack) {
		return 10000;
	}

	@Override
	public int getPollutionPerTick(ItemStack stack) {
		return 0;
	}

	@Override
	public int getDamageToComponent(ItemStack stack) {
		return 0;
	}

	@Override
	public boolean explodesOnComponentBreak(ItemStack stack) {
		return false;
	}
	
}
