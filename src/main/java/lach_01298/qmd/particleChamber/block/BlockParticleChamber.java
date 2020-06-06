package lach_01298.qmd.particleChamber.block;

import lach_01298.qmd.particleChamber.tile.TileParticleChamber;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockParticleChamber extends BlockParticleChamberPart 
{


	
	public BlockParticleChamber()
	{
		super();
	}

	

	

	@Override
	public TileEntity createNewTileEntity(World world, int metadata)
	{
		return new TileParticleChamber();
	}

	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY,
			float hitZ, int meta, EntityLivingBase placer)
	{
		return getDefaultState();
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand,
			EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		if (hand != EnumHand.MAIN_HAND || player == null)
			return false;

		if (player.getHeldItemMainhand().isEmpty() && world.getTileEntity(pos) instanceof TileParticleChamber)
		{
			TileParticleChamber target = (TileParticleChamber) world.getTileEntity(pos);
			

			if (!world.isRemote)

			return true;
		}
		return super.onBlockActivated(world, pos, state, player, hand, facing, hitX, hitY, hitZ);
	}
	



	
	

	
}
