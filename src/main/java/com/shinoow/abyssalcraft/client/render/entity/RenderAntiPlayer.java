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
package com.shinoow.abyssalcraft.client.render.entity;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.shinoow.abyssalcraft.common.entity.anti.EntityAntiPlayer;

@SideOnly(Side.CLIENT)
public class RenderAntiPlayer extends RenderBiped<EntityAntiPlayer>
{
	private static final ResourceLocation playerTexture = new ResourceLocation("abyssalcraft:textures/model/anti/steve.png");

	public RenderAntiPlayer(RenderManager manager)
	{
		super(manager, new ModelBiped(), 0.5F);
		this.addLayer(new LayerHeldItem(this));
		this.addLayer(new LayerBipedArmor(this)
		{
			@Override
			protected void initArmor()
			{
				modelLeggings = new ModelBiped(0.5F);
				modelArmor = new ModelBiped(1.0F);
			}
		});
	}

	@Override
	public void doRender(EntityAntiPlayer par1EntityEntityAntiPlayer, double par2, double par4, double par6, float par8, float par9)
	{
		super.doRender(par1EntityEntityAntiPlayer, par2, par4, par6, par8, par9);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityAntiPlayer par1Entity)
	{
		return playerTexture;
	}
}