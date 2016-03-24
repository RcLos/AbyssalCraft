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
package com.shinoow.abyssalcraft.common.items.armor;

import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.lwjgl.opengl.GL11;

import com.shinoow.abyssalcraft.AbyssalCraft;

//@InterfaceList(value = { @Interface(iface = "thaumcraft.api.items.IVisDiscountGear", modid = "Thaumcraft"),
//		@Interface(iface = "thaumcraft.api.items.IRevealer", modid = "Thaumcraft")})
public class ItemDepthsArmor extends ItemArmor /* implements IVisDiscountGear, IRevealer */ {
	public ItemDepthsArmor(ArmorMaterial par2EnumArmorMaterial, int par3, EntityEquipmentSlot par4, String name){
		super(par2EnumArmorMaterial, par3, par4);
		//		GameRegistry.registerItem(this, name);
		setUnlocalizedName(name);
		setCreativeTab(AbyssalCraft.tabCombat);
	}

	@Override
	public String getItemStackDisplayName(ItemStack par1ItemStack) {

		return TextFormatting.DARK_RED + super.getItemStackDisplayName(par1ItemStack);
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String layer)
	{
		if(stack.getItem() == AbyssalCraft.Depthshelmet || stack.getItem() == AbyssalCraft.Depthsplate || stack.getItem() == AbyssalCraft.Depthsboots)
			return "abyssalcraft:textures/armor/depths_1.png";

		if(stack.getItem() == AbyssalCraft.Depthslegs)
			return "abyssalcraft:textures/armor/depths_2.png";
		else return null;
	}

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack itemstack) {
		if (itemstack.getItem() == AbyssalCraft.Depthshelmet)
		{
			player.addPotionEffect(new PotionEffect(MobEffects.waterBreathing, 20, 0));
			player.addPotionEffect(new PotionEffect(MobEffects.nightVision, 260, 0));
			player.addPotionEffect(new PotionEffect(MobEffects.saturation, 20, 0));
			if(player.getActivePotionEffect(AbyssalCraft.Cplague) !=null)
				player.removePotionEffect(AbyssalCraft.Cplague);
		}
		if (itemstack.getItem() == AbyssalCraft.Depthsplate)
			player.addPotionEffect(new PotionEffect(MobEffects.resistance, 20, 0));
		if (itemstack.getItem() == AbyssalCraft.Depthslegs)
			player.addPotionEffect(new PotionEffect(MobEffects.jump, 20, 1));
		if (itemstack.getItem() == AbyssalCraft.Depthsboots)
			player.addPotionEffect(new PotionEffect(MobEffects.moveSpeed, 20, 0));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void renderHelmetOverlay(ItemStack stack, EntityPlayer player, ScaledResolution resolution, float partialTicks){
		final ResourceLocation coraliumBlur = new ResourceLocation("abyssalcraft:textures/misc/coraliumblur.png");


		if(Minecraft.getMinecraft().gameSettings.thirdPersonView == 0 && stack != null && stack.getItem() == AbyssalCraft.Depthshelmet) {
			GL11.glPushAttrib(GL11.GL_ALL_ATTRIB_BITS);

			Tessellator t = Tessellator.getInstance();

			ScaledResolution scale = new ScaledResolution(Minecraft.getMinecraft());
			int width = scale.getScaledWidth();
			int height = scale.getScaledHeight();

			GL11.glDisable(GL11.GL_DEPTH_TEST);
			GL11.glDepthMask(false);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GL11.glDisable(GL11.GL_ALPHA_TEST);
			Minecraft.getMinecraft().renderEngine.bindTexture(coraliumBlur);

			VertexBuffer wr = t.getBuffer();

			wr.begin(7, DefaultVertexFormats.POSITION_TEX);
			wr.pos(0.0D, height, 90.0D).tex(0.0D, 1.0D).endVertex();;
			wr.pos(width, height, 90.0D).tex(1.0D, 1.0D).endVertex();;
			wr.pos(width, 0.0D, 90.0D).tex(1.0D, 0.0D).endVertex();;
			wr.pos(0.0D, 0.0D, 90.0D).tex(0.0D, 0.0D).endVertex();;
			t.draw();

			GL11.glPopAttrib();
		}
	}

	//	@Override
	//	@Method(modid = "Thaumcraft")
	//	public int getVisDiscount(ItemStack stack, EntityPlayer player,
	//			Aspect aspect) {
	//		return stack.getItem() == AbyssalCraft.Depthshelmet ? 5 : stack.getItem() == AbyssalCraft.Depthsplate ? 2 :
	//			stack.getItem() == AbyssalCraft.Depthslegs ? 2 : stack.getItem() == AbyssalCraft.Depthsboots ? 1 : 0;
	//	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void addInformation(ItemStack is, EntityPlayer player, List l, boolean B){
		if(Loader.isModLoaded("Thaumcraft")){
			if(is.getItem() == AbyssalCraft.Depthshelmet)
				l.add("\u00A75"+I18n.translateToLocal("tc.visdiscount")+": 5%");
			if(is.getItem() == AbyssalCraft.Depthsplate)
				l.add("\u00A75"+I18n.translateToLocal("tc.visdiscount")+": 2%");
			if(is.getItem() == AbyssalCraft.Depthslegs)
				l.add("\u00A75"+I18n.translateToLocal("tc.visdiscount")+": 2%");
			if(is.getItem() == AbyssalCraft.Depthsboots)
				l.add("\u00A75"+I18n.translateToLocal("tc.visdiscount")+": 1%");
		}
	}

	//	@Override
	//	@Method(modid = "Thaumcraft")
	//	public boolean showNodes(ItemStack itemstack, EntityLivingBase player) {
	//
	//		return itemstack.getItem() == AbyssalCraft.Depthshelmet;
	//	}
}
