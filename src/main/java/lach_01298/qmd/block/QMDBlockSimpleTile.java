package lach_01298.qmd.block;

import lach_01298.qmd.enums.BlockTypes.SimpleTileType;
import nc.block.tile.BlockTile;
import nc.block.tile.ITileType;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class QMDBlockSimpleTile extends BlockTile implements ITileType
{

	private final SimpleTileType type;

	public QMDBlockSimpleTile(SimpleTileType type)
	{
		super(Material.IRON);
		this.type = type;
		setCreativeTab(type.getCreativeTab());
	}

	@Override
	public String getTileName()
	{
		return type.getName();
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta)
	{
		return type.getTile();
	}

	public static class Transparent extends QMDBlockSimpleTile
	{

		protected final boolean smartRender;

		public Transparent(SimpleTileType type, boolean smartRender)
		{
			super(type);
			setHardness(1.5F);
			setResistance(10F);
			this.smartRender = smartRender;
		}

		@Override
		@SideOnly(Side.CLIENT)
		public BlockRenderLayer getRenderLayer()
		{
			return BlockRenderLayer.CUTOUT;
		}

		@Override
		public boolean isFullCube(IBlockState state)
		{
			return false;
		}

		@Override
		public boolean isOpaqueCube(IBlockState state)
		{
			return false;
		}

		@Override
		@SideOnly(Side.CLIENT)
		public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess world, BlockPos pos, EnumFacing side)
		{
			if (!smartRender)
			{
				return true;
			}

			IBlockState otherState = world.getBlockState(pos.offset(side));
			Block block = otherState.getBlock();

			if (blockState != otherState)
			{
				return true;
			}

			return block == this ? false : super.shouldSideBeRendered(blockState, world, pos, side);
		}
	}
}
