package com.shinoow.abyssalcraft.common.items;

import java.util.List;
import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.world.World;

import com.google.common.collect.Sets;

public class ItemAbyssalniteCAxe extends ItemTool
{
	private static Set<Block> blocksEffectiveAgainst = Sets.newHashSet(new Block[] {Blocks.planks, Blocks.bookshelf, Blocks.log, Blocks.chest, Blocks.pumpkin, Blocks.lit_pumpkin});

	public ItemAbyssalniteCAxe(ToolMaterial enumToolMaterial)
	{
		super(3, enumToolMaterial, blocksEffectiveAgainst);
		this.setHarvestLevel("axe", 8);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void addInformation(ItemStack is, EntityPlayer player, List l, boolean B)
	{
		l.add("This axe can transmutate");
		l.add("planks leaves, logs and saplings");
	}

	/**
	 * Returns the strength of the stack against a given block. 1.0F base, (Quality+1)*2 if correct blocktype, 1.5F if
	 * sword
	 */
	public boolean onItemUse(ItemStack is, EntityPlayer player, World w, int x, int y, int z, int l, float f, float f1, float f3){
		if(w.getBlock(x, y, z) == Blocks.planks){
			w.setBlockMetadataWithNotify(x, y, z, w.getBlockMetadata(x, y, z)+1, 0);
			is.damageItem(50, player);
		}else if(w.getBlock(x, y, z) == Blocks.leaves){
			w.setBlockMetadataWithNotify(x, y, z, w.getBlockMetadata(x, y, z)+1, 0);
			is.damageItem(50, player);
		}else if(w.getBlock(x, y, z) == Blocks.log){
			w.setBlockMetadataWithNotify(x, y, z, w.getBlockMetadata(x, y, z)+1, 0);
			is.damageItem(50, player);
		}else if(w.getBlock(x, y, z) == Blocks.sapling){
			w.setBlockMetadataWithNotify(x, y, z, w.getBlockMetadata(x, y, z)+1, 0);
			is.damageItem(50, player);
		}else if(w.getBlock(x, y, z) == Blocks.leaves2){
			w.setBlockMetadataWithNotify(x, y, z, w.getBlockMetadata(x, y, z)+1, 0);
			is.damageItem(50, player);
		}else if(w.getBlock(x, y, z) == Blocks.log2){
			w.setBlockMetadataWithNotify(x, y, z, w.getBlockMetadata(x, y, z)+1, 0);
			is.damageItem(50, player);
		}
		return false;
	}

	public float func_150893_a(ItemStack par1ItemStack, Block par2Block)
	{
		if (par2Block != null && par2Block.getMaterial() == Material.wood)
		{
			return efficiencyOnProperMaterial;
		}
		else
		{
			return super.func_150893_a(par1ItemStack, par2Block);
		}
	}

}