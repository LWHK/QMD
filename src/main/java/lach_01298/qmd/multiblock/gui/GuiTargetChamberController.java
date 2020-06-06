package lach_01298.qmd.multiblock.gui;

import java.util.ArrayList;
import java.util.List;

import lach_01298.qmd.QMD;
import lach_01298.qmd.Units;
import lach_01298.qmd.gui.GuiParticle;
import lach_01298.qmd.particleChamber.ParticleChamber;
import lach_01298.qmd.particleChamber.TargetChamberLogic;
import nc.multiblock.gui.GuiLogicMultiblockController;
import nc.multiblock.network.ClearAllMaterialPacket;
import nc.network.PacketHandler;
import nc.util.Lang;
import nc.util.NCUtil;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;

public class GuiTargetChamberController extends GuiLogicMultiblockController<ParticleChamber, TargetChamberLogic>
{

	protected final ResourceLocation gui_texture;

	

	public GuiTargetChamberController(ParticleChamber multiblock, BlockPos controllerPos, Container container)
	{
		super(multiblock, controllerPos, container);
		gui_texture = new ResourceLocation(QMD.MOD_ID + ":textures/gui/target_chamber_controller.png");
		xSize = 176;
		ySize = 180;
	}

	@Override
	protected ResourceLocation getGuiTexture()
	{
		return gui_texture;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) 
	{
		
		int offset = 8;
		int fontColor = multiblock.isChamberOn ? -1 : 15641088;
		
		String efficiency = Lang.localise("gui.qmd.container.target_chamber.efficiency",String.format("%.2f", multiblock.efficiency*100));
		fontRenderer.drawString(efficiency,offset, 73, fontColor);
		
		
		
		
		if (!NCUtil.isModifierKeyDown())  
		{
			
		}
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
	{
		
		GlStateManager.color(1F, 1F, 1F, 1F);
		mc.getTextureManager().bindTexture(getGuiTexture());
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);

		int power = (int)Math.round((double)multiblock.energyStorage.getEnergyStored()/(double)multiblock.energyStorage.getMaxEnergyStored()*74);

		drawTexturedModalRect(guiLeft + 161, guiTop + 79-power, 176, 0, 6, power);
		
		//input
		if(multiblock.beams.get(0).getParticleStack() != null)
		{
			drawTexturedModalRect(guiLeft + 29, guiTop + 35, 182, 12, 16, 6);
		}
		
		
		
		//top output
		if(multiblock.beams.get(1).getParticleStack() != null)
		{
			drawTexturedModalRect(guiLeft + 63, guiTop + 14, 182, 18, 16, 16);
		}
		
		//middle output
		if(multiblock.beams.get(2).getParticleStack() != null)
		{
			drawTexturedModalRect(guiLeft + 109, guiTop + 36, 182, 50, 16, 4);
		}
		
		//bottom output
		if(multiblock.beams.get(3).getParticleStack() != null)
		{
			drawTexturedModalRect(guiLeft + 63, guiTop + 46, 182, 34, 16, 16);
		}
		
		
		
		//draw progress bar
		int progress = (int)Math.round((double)logic.particleWorkDone/(double)logic.recipeParticleWork*21);
		
		
		drawTexturedModalRect(guiLeft+65, guiTop+32, 182, 0, progress, 12);
	}
	
	@Override
	public void renderTooltips(int mouseX, int mouseY) 
	{
		if (NCUtil.isModifierKeyDown()) drawTooltip(clearAllInfo(), mouseX, mouseY, 153, 81, 18, 18);
		
		
		drawTooltip(energyInfo(), mouseX, mouseY, 160, 4, 8, 76);
		
		
	}
	

	
	
	public List<String> energyInfo() 
	{
		List<String> info = new ArrayList<String>();
		info.add(TextFormatting.YELLOW + Lang.localise("gui.qmd.container.energy_stored",Units.getSIFormat(multiblock.energyStorage.getEnergyStored(), "RF"),Units.getSIFormat(multiblock.energyStorage.getMaxEnergyStored(),"RF")));
		info.add(TextFormatting.RED + Lang.localise("gui.qmd.container.required_energy",Units.getSIFormat(multiblock.requiredEnergy, "RF/t")));
		return info;
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) 
	{
		super.drawScreen(mouseX, mouseY, partialTicks);
		GuiParticle guiParticle = new GuiParticle(this);
		guiParticle.drawParticleStack(multiblock.beams.get(0).getParticleStack(), guiLeft+12, guiTop+30);
		guiParticle.drawParticleStack(multiblock.beams.get(1).getParticleStack(), guiLeft+80, guiTop+7);
		guiParticle.drawParticleStack(multiblock.beams.get(2).getParticleStack(), guiLeft+126, guiTop+30);
		guiParticle.drawParticleStack(multiblock.beams.get(3).getParticleStack(), guiLeft+80, guiTop+53);
		
		guiParticle.drawToolTipBoxwithLuminosity(multiblock.beams.get(0).getParticleStack(), guiLeft+12, guiTop+30, mouseX, mouseY);
		guiParticle.drawToolTipBoxwithLuminosity(multiblock.beams.get(1).getParticleStack(), guiLeft+80, guiTop+7, mouseX, mouseY);
		guiParticle.drawToolTipBoxwithLuminosity(multiblock.beams.get(2).getParticleStack(), guiLeft+126, guiTop+30, mouseX, mouseY);
		guiParticle.drawToolTipBoxwithLuminosity(multiblock.beams.get(3).getParticleStack(), guiLeft+80, guiTop+53, mouseX, mouseY);
		
		
		
		renderTooltips(mouseX, mouseY);
	}
	
	
	@Override
	public void initGui() 
	{
		super.initGui();
		
	}
	
	@Override
	protected void actionPerformed(GuiButton guiButton)
	{
		if (multiblock.WORLD.isRemote)
		{
			if (guiButton.id == 0 && NCUtil.isModifierKeyDown())
			{
				PacketHandler.instance.sendToServer(new ClearAllMaterialPacket(controllerPos));
			}
		}
	}

}