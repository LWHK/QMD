package lach_01298.qmd.multiblock.network;

import java.util.ArrayList;
import java.util.List;

import io.netty.buffer.ByteBuf;
import lach_01298.qmd.ByteUtil;
import lach_01298.qmd.particle.ParticleStorageAccelerator;
import lach_01298.qmd.particleChamber.ParticleChamber;
import lach_01298.qmd.particleChamber.tile.TileTargetChamberController;
import nc.multiblock.network.MultiblockUpdatePacket;
import nc.tile.internal.energy.EnergyStorage;
import net.minecraft.util.math.BlockPos;

public class TargetChamberUpdatePacket extends ParticleChamberUpdatePacket
{
	public List<ParticleStorageAccelerator> beams;
	public long particleCount, recipeParticleCount;
	
	public TargetChamberUpdatePacket() 
	{
		super();
		beams = new ArrayList<ParticleStorageAccelerator>();
	}

	public TargetChamberUpdatePacket(BlockPos pos, boolean isAcceleratorOn, int requiredEnergy, double efficiency,
			EnergyStorage energyStorage, List<ParticleStorageAccelerator> beams, long particleCount, long particleRecipeCount)
	{
		super(pos, isAcceleratorOn, requiredEnergy, efficiency, energyStorage);
		this.beams = beams;
		this.particleCount =particleCount;
		this.recipeParticleCount=particleRecipeCount;
	}

	@Override
	public void readMessage(ByteBuf buf)
	{
		super.readMessage(buf);
		
		int size = buf.readInt();
		for (int i = 0; i < size; i++)
		{
			beams.add(ByteUtil.readBufBeam(buf));
		}

		particleCount = buf.readLong();
		recipeParticleCount = buf.readLong();
	}

	@Override
	public void writeMessage(ByteBuf buf)
	{
		super.writeMessage(buf);
		
		buf.writeInt(beams.size());
		for(ParticleStorageAccelerator beam : beams)
		{
			ByteUtil.writeBufBeam(beam, buf);
		}
		buf.writeLong(particleCount);
		buf.writeLong(recipeParticleCount);
	}
	
	public static class Handler extends MultiblockUpdatePacket.Handler<TargetChamberUpdatePacket, ParticleChamber, TileTargetChamberController> {
		
		public Handler() 
		{
			super(TileTargetChamberController.class);
		}
	}
}
