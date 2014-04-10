package com.shinoow.abyssalcraft.common.blocks;

import net.minecraft.block.material.Material;

import com.shinoow.abyssalcraft.AbyssalCraft;
import com.shinoow.abyssalcraft.common.BlockGeneralAC;

public class AbyOre extends BlockGeneralAC
{
	public AbyOre()
	{
		super(Material.rock);
		this.setCreativeTab(AbyssalCraft.tabBlock);
		this.setHarvestLevel("pickaxe", 2);
    }
}