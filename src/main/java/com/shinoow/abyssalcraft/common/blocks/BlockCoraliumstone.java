/*******************************************************************************
 * AbyssalCraft
 * Copyright (c) 2012 - 2016 Shinoow.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0.txt
 * 
 * Contributors:
 *     Shinoow -  implementation
 ******************************************************************************/
package com.shinoow.abyssalcraft.common.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

import com.shinoow.abyssalcraft.AbyssalCraft;

public class BlockCoraliumstone extends Block {

	public BlockCoraliumstone() {
		super(Material.rock);
		setCreativeTab(AbyssalCraft.tabBlock);
		setTickRandomly(true);
	}

	@Override
	public void updateTick(World par1World, BlockPos pos, IBlockState state, Random par5Random) {
		if (!par1World.isRemote)
			for (int l = 0; l < 1; ++l) {
				int i1 = pos.getX() + par5Random.nextInt(3) - 1;
				int j1 = pos.getY() + par5Random.nextInt(5) - 3;
				int k1 = pos.getZ() + par5Random.nextInt(3) - 1;

				if (par1World.getBlockState(new BlockPos(i1, j1, k1)) == AbyssalCraft.Cwater)
					par1World.setBlockState(new BlockPos(i1, j1, k1), AbyssalCraft.cstone.getDefaultState());
			}
	}
}