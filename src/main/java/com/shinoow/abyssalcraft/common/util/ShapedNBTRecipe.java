/*******************************************************************************
 * AbyssalCraft
 * Copyright (c) 2012 - 2018 Shinoow.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0.txt
 *
 * Contributors:
 *     Shinoow -  implementation
 ******************************************************************************/
package com.shinoow.abyssalcraft.common.util;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;

public class ShapedNBTRecipe extends ShapedRecipes {

	public ShapedNBTRecipe(String name, int width, int height, NonNullList<Ingredient> p_i1917_3_, ItemStack output) {
		super(name, width, height, p_i1917_3_, output);
	}

	@Override
	public boolean matches(InventoryCrafting inv, World worldIn)
	{
		for (int i = 0; i <= 3 - recipeWidth; ++i)
			for (int j = 0; j <= 3 - recipeHeight; ++j)
			{
				if (checkMatch(inv, i, j, true))
					return true;

				if (checkMatch(inv, i, j, false))
					return true;
			}

		return false;
	}

	/**
	 * Checks if the region of a crafting inventory is match for the recipe.
	 */
	private boolean checkMatch(InventoryCrafting inventory, int width, int height, boolean p_77573_4_)
	{
		for (int i = 0; i < 3; ++i)
			for (int j = 0; j < 3; ++j)
			{
				int k = i - width;
				int l = j - height;
				Ingredient ingredient = Ingredient.EMPTY;

				if (k >= 0 && l >= 0 && k < recipeWidth && l < recipeHeight)
					if (p_77573_4_)
						ingredient = recipeItems.get(recipeWidth - k - 1 + l * recipeWidth);
					else
						ingredient = recipeItems.get(k + l * recipeWidth);

				ItemStack itemstack1 = inventory.getStackInRowAndColumn(i, j);

				for(ItemStack itemstack : ingredient.getMatchingStacks())
					if (itemstack1 != null || itemstack != null)
					{
						if (itemstack1 == null && itemstack != null || itemstack1 != null && itemstack == null)
							return false;

						if (itemstack.getItem() != itemstack1.getItem())
							return false;

						if (itemstack.getMetadata() != 32767 && itemstack.getMetadata() != itemstack1.getMetadata())
							return false;

						if (!ItemStack.areItemStackTagsEqual(itemstack, itemstack1))
							return false;
					}
			}

		return true;
	}
}
