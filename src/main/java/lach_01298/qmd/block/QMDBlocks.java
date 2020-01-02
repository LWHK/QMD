package lach_01298.qmd.block;

import lach_01298.qmd.EnumTypes;
import lach_01298.qmd.QMD;
import lach_01298.qmd.QMDInfo;
import lach_01298.qmd.multiblock.accelerator.block.BlockAcceleratorBeam;
import lach_01298.qmd.multiblock.accelerator.block.BlockAcceleratorBeamPort;
import lach_01298.qmd.multiblock.accelerator.block.BlockAcceleratorCasing;
import lach_01298.qmd.multiblock.accelerator.block.BlockAcceleratorCooler1;
import lach_01298.qmd.multiblock.accelerator.block.BlockAcceleratorCooler2;
import lach_01298.qmd.multiblock.accelerator.block.BlockAcceleratorEnergyPort;
import lach_01298.qmd.multiblock.accelerator.block.BlockAcceleratorGlass;
import lach_01298.qmd.multiblock.accelerator.block.BlockAcceleratorInlet;
import lach_01298.qmd.multiblock.accelerator.block.BlockAcceleratorMagnet;
import lach_01298.qmd.multiblock.accelerator.block.BlockAcceleratorOutlet;
import lach_01298.qmd.multiblock.accelerator.block.BlockAcceleratorSource;
import lach_01298.qmd.multiblock.accelerator.block.BlockAcceleratorYoke;
import lach_01298.qmd.multiblock.accelerator.block.BlockRFCavity;
import lach_01298.qmd.multiblock.accelerator.block.BlockLinearAcceleratorController;
import lach_01298.qmd.multiblock.accelerator.block.BlockRingAcceleratorController;
import nc.Global;
import nc.NCInfo;
import nc.block.item.ItemBlockMeta;
import nc.block.item.NCItemBlock;
import nc.block.tile.ITileType;
import nc.util.InfoHelper;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.ForgeRegistries;


public class QMDBlocks
{

	public static Block beamline;
	
	public static Block linearAcceleratorCotroller;
	public static Block ringAcceleratorCotroller;
	public static Block acceleratorBeam;
	public static Block acceleratorCasing;
	public static Block acceleratorGlass;
	public static Block acceleratorInlet;
	public static Block acceleratorOutlet;
	public static Block acceleratorBeamPort;
	public static Block RFCavity;
	public static Block acceleratorMagnet;
	public static Block acceleratorYoke;
	public static Block acceleratorCooler1;
	public static Block acceleratorCooler2;
	public static Block acceleratorSource;
	public static Block acceleratorEnergyPort;
	
	public static Block oreLeacher;

	
	public static void init() 
	{
		beamline = withName(new BlockBeamline(), "beamline");
		
		
		linearAcceleratorCotroller = withName(new BlockLinearAcceleratorController(), "linear_accelerator_controller");
		ringAcceleratorCotroller = withName(new BlockRingAcceleratorController(), "ring_accelerator_controller");
		acceleratorBeam = withName(new BlockAcceleratorBeam(), "accelerator_beam");
		acceleratorCasing = withName(new BlockAcceleratorCasing(), "accelerator_casing");
		acceleratorGlass = withName(new BlockAcceleratorGlass(), "accelerator_glass");
		acceleratorInlet = withName(new BlockAcceleratorInlet(), "accelerator_inlet");
		acceleratorOutlet = withName(new BlockAcceleratorOutlet(), "accelerator_outlet");
		acceleratorBeamPort = withName(new BlockAcceleratorBeamPort(), "accelerator_beam_port");
		RFCavity = withName(new BlockRFCavity(), "accelerator_cavity");
		acceleratorMagnet = withName(new BlockAcceleratorMagnet(), "accelerator_magnet");
		acceleratorYoke = withName(new BlockAcceleratorYoke(), "accelerator_yoke");
		acceleratorCooler1 = withName(new BlockAcceleratorCooler1(), "accelerator_cooler1");
		acceleratorCooler2 = withName(new BlockAcceleratorCooler2(), "accelerator_cooler2");
		acceleratorSource =  withName(new BlockAcceleratorSource(), "accelerator_source");
		acceleratorEnergyPort = withName(new BlockAcceleratorEnergyPort(), "accelerator_energy_port");
	}
	
	public static void register() 
	{
		registerBlock(beamline);
		
		
		registerBlock(linearAcceleratorCotroller);
		registerBlock(ringAcceleratorCotroller);
		registerBlock(acceleratorBeam);
		registerBlock(acceleratorCasing);
		registerBlock(acceleratorGlass);
		registerBlock(acceleratorInlet);
		registerBlock(acceleratorOutlet);
		registerBlock(acceleratorBeamPort);
		registerBlock(RFCavity, new ItemBlockMeta(RFCavity, EnumTypes.RFCavityType.class,TextFormatting.GREEN ,QMDInfo.RFCavityFixedInfo(),TextFormatting.AQUA,QMDInfo.RFCavityInfo()));
		registerBlock(acceleratorMagnet, new ItemBlockMeta(acceleratorMagnet, EnumTypes.MagnetType.class,TextFormatting.GREEN ,QMDInfo.magnetFixedInfo(),TextFormatting.AQUA,QMDInfo.magnetInfo()));
		registerBlock(acceleratorYoke);
		registerBlock(acceleratorCooler1, new ItemBlockMeta(acceleratorCooler1, EnumTypes.CoolerType1.class,TextFormatting.BLUE, QMDInfo.cooler1FixedInfo(),TextFormatting.AQUA,QMDInfo.cooler1Info()));
		registerBlock(acceleratorCooler2, new ItemBlockMeta(acceleratorCooler2, EnumTypes.CoolerType2.class,TextFormatting.BLUE, QMDInfo.cooler2FixedInfo(),TextFormatting.AQUA,QMDInfo.cooler2Info()));
		registerBlock(acceleratorSource);
		registerBlock(acceleratorEnergyPort);
				
				
				
	}

	public static void registerRenders() 
	{
		registerRender(beamline);
		
		
		registerRender(linearAcceleratorCotroller);
		registerRender(ringAcceleratorCotroller);
		registerRender(acceleratorBeam);
		registerRender(acceleratorCasing);
		registerRender(acceleratorGlass);
		registerRender(acceleratorInlet);
		registerRender(acceleratorOutlet);
		registerRender(acceleratorBeamPort);
		
		for (int i=0; i < EnumTypes.RFCavityType.values().length; i++)
		{
			registerRender(RFCavity, i, EnumTypes.RFCavityType.values()[i].getName());
		}
		for (int i=0; i < EnumTypes.MagnetType.values().length; i++)
		{
			registerRender(acceleratorMagnet, i, EnumTypes.MagnetType.values()[i].getName());
		}
		registerRender(acceleratorYoke);
		
		for (int i=0; i < EnumTypes.CoolerType1.values().length; i++)
		{
			registerRender(acceleratorCooler1, i, EnumTypes.CoolerType1.values()[i].getName());
		}
		for (int i=0; i < EnumTypes.CoolerType1.values().length; i++)
		{
			registerRender(acceleratorCooler2, i, EnumTypes.CoolerType2.values()[i].getName());
		}
		registerRender(acceleratorSource);
		registerRender(acceleratorEnergyPort);
	}


	
	
	
	
	
	
	
	
	
	public static Block withName(Block block, String name) {
		return block.setTranslationKey(QMD.MOD_ID + "." + name).setRegistryName(new ResourceLocation(QMD.MOD_ID, name));
	}
	
	public static <T extends Block & ITileType> Block withName(T block) {
		return block.setTranslationKey(QMD.MOD_ID + "." + block.getTileName()).setRegistryName(new ResourceLocation(QMD.MOD_ID, block.getTileName()));
	}
	
	public static String fixedLine(String name) {
		return "tile." + QMD.MOD_ID + "." + name + ".fixd";
	}
	
	public static String infoLine(String name) {
		return "tile." + QMD.MOD_ID + "." + name + ".desc";
	}
	
	public static void registerBlock(Block block, TextFormatting[] fixedColors, String[] fixedTooltip, TextFormatting infoColor, String... tooltip) {
		ForgeRegistries.BLOCKS.register(block);
		ForgeRegistries.ITEMS.register(new NCItemBlock(block, fixedColors, fixedTooltip, infoColor, tooltip).setRegistryName(block.getRegistryName()));
	}
	
	public static void registerBlock(Block block, TextFormatting fixedColor, String[] fixedTooltip, TextFormatting infoColor, String... tooltip) {
		ForgeRegistries.BLOCKS.register(block);
		ForgeRegistries.ITEMS.register(new NCItemBlock(block, fixedColor, fixedTooltip, infoColor, tooltip).setRegistryName(block.getRegistryName()));
	}
	
	public static void registerBlock(Block block, String... tooltip) {
		registerBlock(block, TextFormatting.RED, InfoHelper.EMPTY_ARRAY, TextFormatting.AQUA, tooltip);
	}
	
	public static void registerBlock(Block block, ItemBlock itemBlock) {
		ForgeRegistries.BLOCKS.register(block);
		ForgeRegistries.ITEMS.register(itemBlock.setRegistryName(block.getRegistryName()));
	}
	
	public static void registerRender(Block block) {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation(block.getRegistryName(), "inventory"));
	}
	
	public static void registerRender(Block block, int meta, String type) {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), meta, new ModelResourceLocation(new ResourceLocation(QMD.MOD_ID, block.getRegistryName().getPath()), "type=" + type));
	}



}