package com.djgiannuzz.mkdollymod.blocks;

import com.djgiannuzz.mkdollymod.MKDollyMod;
import com.djgiannuzz.mkdollymod.gui.GuiHandler;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockDolly extends BlockContainer
{

	public BlockDolly() 
	{
		super(Material.cloth);
		this.setBlockName("Dolly");
		setBlockTextureName("wool_colored_white");
		this.setBlockBounds(4F/16F, 0F, 4F/16F, 12F/16F, 1F, 12F/16F);
	}
	
	@Override
	public boolean shouldSideBeRendered(IBlockAccess iblockaccess, int i, int j, int k, int l)
	{
	   return false;
	}
	
	@Override
	public int getRenderType()
	{
		return -1;
	}
	
	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}
	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}
	@Override
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack)
    {
	    int l = MathHelper.floor_double((double)(par5EntityLivingBase.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
	    ++l;
	    l %= 4;
	
	    if (l == 0)
	    {
	    	par1World.setBlockMetadataWithNotify(par2, par3, par4, 2, 2); //2
	    }
	    else if (l == 1)
	    {
	    	par1World.setBlockMetadataWithNotify(par2, par3, par4, 3, 2); //3
	    }
	    else if (l == 2)
	    {
	    	par1World.setBlockMetadataWithNotify(par2, par3, par4, 0, 2); //0
	    }
	    else if (l == 3)
	    {
	    	par1World.setBlockMetadataWithNotify(par2, par3, par4, 1, 2); //1
	    }
    }

	@Override
	public TileEntity createNewTileEntity(World world, int p_149915_2_) 
	{
		return new TEDolly();
	}
	
	@Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer playerEnt, int par6, float par7, float par8, float par9)
    {
		playerEnt.openGui(MKDollyMod.instance, GuiHandler.GUI_DOLLY, world, x, y, z);
		return true;
    }
}
