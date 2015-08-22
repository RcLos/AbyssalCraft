/*******************************************************************************
 * AbyssalCraft
 * Copyright (c) 2012 - 2015 Shinoow.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0.txt
 * 
 * Contributors:
 *     Shinoow -  implementation
 ******************************************************************************/
package com.shinoow.abyssalcraft.common.blocks;

import net.minecraft.block.material.*;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenOcean;
import net.minecraftforge.fluids.BlockFluidClassic;

import com.shinoow.abyssalcraft.AbyssalCraft;
import com.shinoow.abyssalcraft.common.util.EntityUtil;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockCLiquid extends BlockFluidClassic {

	public static final MaterialLiquid Cwater = new MaterialLiquid(MapColor.lightBlueColor);

	@SideOnly(Side.CLIENT)
	protected IIcon[] theIcon;

	public BlockCLiquid() {
		super(AbyssalCraft.CFluid, Material.water);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister) {
		theIcon = new IIcon[]{iconRegister.registerIcon("abyssalcraft:cwater_still"), iconRegister.registerIcon("abyssalcraft:cwater_flow")};

		AbyssalCraft.CFluid.setStillIcon(theIcon[0]);
		AbyssalCraft.CFluid.setFlowingIcon(theIcon[1]);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return side != 0 && side != 1 ? theIcon[1] : theIcon[0];
	}

	@Override
	public MapColor getMapColor(int meta){
		return MapColor.lightBlueColor;
	}

	@Override
	public boolean canDisplace(IBlockAccess world, int x, int y, int z) {
		if(world.getBlock(x, y, z).getMaterial().isLiquid() && world.getBlock(x, y, z) != this && world.getBlock(x, y, z) != AbyssalCraft.anticwater)
			return true;
		if(world.getBlock(x, y, z) == Blocks.lava)
			return true;
		else if(world.getBlock(x, y, z) == Blocks.stone || world.getBlock(x, y, z) == Blocks.netherrack || world.getBlock(x, y, z) == Blocks.end_stone || world.getBlock(x, y, z) == AbyssalCraft.Darkstone || world.getBlock(x, y, z) == AbyssalCraft.dreadstone || world.getBlock(x, y, z) == AbyssalCraft.abydreadstone)
			return true;
		else if(world.getBlock(x, y, z) == Blocks.coal_ore || world.getBlock(x, y, z) == Blocks.iron_ore || world.getBlock(x, y, z) == Blocks.gold_ore || world.getBlock(x, y, z) == Blocks.diamond_ore || world.getBlock(x, y, z) == Blocks.quartz_ore)
			return true;
		else if(world.getBlock(x, y, z) == Blocks.emerald_ore || world.getBlock(x, y, z) == AbyssalCraft.abyore || world.getBlock(x, y, z) == AbyssalCraft.Coraliumore || world.getBlock(x, y, z) == AbyssalCraft.CoraliumInfusedStone || world.getBlock(x, y, z) == AbyssalCraft.abydreadore || world.getBlock(x, y, z) == AbyssalCraft.dreadore)
			return true;
		else if(world.getBlock(x, y, z) == Blocks.stonebrick || world.getBlock(x, y, z) == Blocks.nether_brick || world.getBlock(x, y, z) == AbyssalCraft.Darkstone_brick || world.getBlock(x, y, z) == AbyssalCraft.dreadbrick || world.getBlock(x, y, z) == AbyssalCraft.abydreadbrick)
			return true;
		return super.canDisplace(world, x, y, z);
	}

	@Override
	public boolean displaceIfPossible(World world, int x, int y, int z) {

		if(!world.isRemote && !world.provider.isSurfaceWorld() && world.getBlock(x, y, z) == Blocks.water && AbyssalCraft.shouldSpread == false)
			return false;
		if(!world.isRemote && world.provider.isSurfaceWorld() && AbyssalCraft.shouldSpread == true && world.getBlock(x, y, z).getMaterial().isLiquid() && world.getBlock(x, y, z) != this && world.getBlock(x, y, z) != AbyssalCraft.anticwater)
			world.setBlock(x, y, z, this);
		if(!world.isRemote && !world.provider.isSurfaceWorld() && world.getBlock(x, y, z).getMaterial().isLiquid() && world.getBlock(x, y, z) != this && world.getBlock(x, y, z) != AbyssalCraft.anticwater)
			world.setBlock(x, y, z, this);
		if(!world.isRemote && world.provider.isSurfaceWorld() && AbyssalCraft.destroyOcean == false && world.getBiomeGenForCoords(x, z) instanceof BiomeGenOcean && world.getBlock(x, y, z) == this)
			world.setBlock(x, y, z, Blocks.cobblestone);
		if(!world.isRemote && world.provider.isSurfaceWorld() && AbyssalCraft.destroyOcean == true && world.getBiomeGenForCoords(x, z) instanceof BiomeGenOcean && world.getBlock(x, y, z) == this)
			world.setBlock(x, y, z, this);
		if(!world.isRemote && world.provider.isSurfaceWorld() && AbyssalCraft.shouldSpread == true && world.getBlock(x, y, z) == Blocks.water)
			world.setBlock(x, y, z, this);
		if(!world.isRemote && world.provider.isSurfaceWorld() && AbyssalCraft.shouldSpread == true  && AbyssalCraft.breakLogic == true && world.getBlock(x, y+1, z) == Blocks.water)
			world.setBlock(x, y+1, z, this);
		if(!world.isRemote && !world.provider.isSurfaceWorld() && world.getBlock(x, y, z) == Blocks.water)
			world.setBlock(x, y, z, this);
		if(!world.isRemote && !world.provider.isSurfaceWorld() && AbyssalCraft.breakLogic == true && world.getBlock(x, y+1, z) == Blocks.water)
			world.setBlock(x, y+1, z, this);
		if(!world.isRemote && world.getBlock(x, y, z) == Blocks.lava)
			world.setBlock(x, y, z, this);
		else if(!world.isRemote && world.getBlock(x, y, z) == Blocks.stone || !world.isRemote && world.getBlock(x, y, z) == Blocks.netherrack || !world.isRemote && world.getBlock(x, y, z) == Blocks.end_stone || !world.isRemote && world.getBlock(x, y, z) == AbyssalCraft.Darkstone || !world.isRemote && world.getBlock(x, y, z) == AbyssalCraft.dreadstone || !world.isRemote && world.getBlock(x, y, z) == AbyssalCraft.abydreadstone)
			world.setBlock(x, y, z, AbyssalCraft.abystone);
		else if(!world.isRemote && world.getBlock(x, y, z) == Blocks.coal_ore || !world.isRemote && world.getBlock(x, y, z) == Blocks.iron_ore || !world.isRemote && world.getBlock(x, y, z) == Blocks.gold_ore || !world.isRemote && world.getBlock(x, y, z) == Blocks.diamond_ore || !world.isRemote && world.getBlock(x, y, z) == Blocks.quartz_ore)
			world.setBlock(x, y, z, AbyssalCraft.AbyCorOre);
		else if(!world.isRemote && world.getBlock(x, y, z) == Blocks.emerald_ore || !world.isRemote && world.getBlock(x, y, z) == AbyssalCraft.abyore || !world.isRemote && world.getBlock(x, y, z) == AbyssalCraft.Coraliumore || !world.isRemote && world.getBlock(x, y, z) == AbyssalCraft.CoraliumInfusedStone || !world.isRemote && world.getBlock(x, y, z) == AbyssalCraft.abydreadore || !world.isRemote && world.getBlock(x, y, z) == AbyssalCraft.dreadore)
			world.setBlock(x, y, z, AbyssalCraft.AbyLCorOre);
		else if(!world.isRemote && world.getBlock(x, y, z) == Blocks.stonebrick || !world.isRemote && world.getBlock(x, y, z) == Blocks.nether_brick || !world.isRemote && world.getBlock(x, y, z) == AbyssalCraft.Darkstone_brick || !world.isRemote && world.getBlock(x, y, z) == AbyssalCraft.dreadbrick || !world.isRemote && world.getBlock(x, y, z) == AbyssalCraft.abydreadbrick)
			world.setBlock(x, y, z, AbyssalCraft.abybrick);
		return super.displaceIfPossible(world, x, y, z);
	}

	@Override
	public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity par5Entity) {
		super.onEntityCollidedWithBlock(par1World, par2, par3, par4, par5Entity);

		if(par5Entity instanceof EntityLivingBase && !EntityUtil.isEntityCoralium((EntityLivingBase)par5Entity) && ((EntityLivingBase)par5Entity).getActivePotionEffect(AbyssalCraft.Cplague) == null)
			((EntityLivingBase)par5Entity).addPotionEffect(new PotionEffect(AbyssalCraft.Cplague.id, 200));
	}
}