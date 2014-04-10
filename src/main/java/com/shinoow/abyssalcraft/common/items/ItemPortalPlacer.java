package com.shinoow.abyssalcraft.common.items;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import com.shinoow.abyssalcraft.AbyssalCraft;
import com.shinoow.abyssalcraft.common.ItemGeneralAC;

import cpw.mods.fml.client.FMLClientHandler;

public class ItemPortalPlacer extends ItemGeneralAC
{
	public ItemPortalPlacer()
	{
		super();
		this.maxStackSize = 1;
		this.setCreativeTab(AbyssalCraft.tabTools);
	}

	public boolean isFull3D()
	{
		return true;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void addInformation(ItemStack par1ItemStack, EntityPlayer entityplayer, List list, boolean is){
		list.add("Right-click on the ground to");
		list.add("create a portal. Infinite uses.");
	}

	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
	{
		if(!par3World.isRemote)
		{
			int direction = MathHelper.floor_double((double)(par2EntityPlayer.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

			if(direction == 1 || direction == 3)
			{
				for(int y = 1; y < 5; y++)
				{
					for (int z = -1; z < 2; z++)
					{
						if(par3World.getBlockMetadata(par4, par5 + y, par6 + z) != 0)
						{
							FMLClientHandler.instance().getClient().ingameGUI.getChatGUI().printChatMessage(new ChatComponentText("No room for a portal."));
									return false;
						}
					}
				}

				par3World.setBlock(par4, par5 + 1, par6, AbyssalCraft.Darkstone);
				par3World.setBlock(par4, par5 + 1, par6 + 1, AbyssalCraft.Darkstone);
				par3World.setBlock(par4, par5 + 1, par6 + 2, AbyssalCraft.Darkstone);
				par3World.setBlock(par4, par5 + 1, par6 - 1, AbyssalCraft.Darkstone);

				par3World.setBlock(par4, par5 + 2, par6 - 1, AbyssalCraft.Darkstone);
				par3World.setBlock(par4, par5 + 3, par6 - 1, AbyssalCraft.Darkstone);
				par3World.setBlock(par4, par5 + 4, par6 - 1, AbyssalCraft.Darkstone);
				par3World.setBlock(par4, par5 + 5, par6 - 1, AbyssalCraft.Darkstone);

				par3World.setBlock(par4, par5 + 2, par6 + 2, AbyssalCraft.Darkstone);
				par3World.setBlock(par4, par5 + 3, par6 + 2, AbyssalCraft.Darkstone);
				par3World.setBlock(par4, par5 + 4, par6 + 2, AbyssalCraft.Darkstone);
				par3World.setBlock(par4, par5 + 5, par6 + 2, AbyssalCraft.Darkstone);

				par3World.setBlock(par4, par5 + 5, par6, AbyssalCraft.Darkstone);
				par3World.setBlock(par4, par5 + 5, par6 + 1, AbyssalCraft.Darkstone);

				par3World.setBlock(par4, par5 + 2, par6 + 1, AbyssalCraft.Coraliumfire);
			}
			else
			{
				for(int y = 1; y < 5; y++)
				{
					for (int x = -1; x < 2; x++)
					{
						if(par3World.getBlockMetadata(par4 + x, par5 + y, par6) != 0)
						{
							FMLClientHandler.instance().getClient().ingameGUI.getChatGUI().printChatMessage(new ChatComponentText("No room for a portal."));
									return false;
						}
					}
				}

				par3World.setBlock(par4, par5 + 1, par6, AbyssalCraft.Darkstone);
				par3World.setBlock(par4 + 1, par5 + 1, par6, AbyssalCraft.Darkstone);
				par3World.setBlock(par4 + 2, par5 + 1, par6, AbyssalCraft.Darkstone);
				par3World.setBlock(par4 - 1, par5 + 1, par6, AbyssalCraft.Darkstone);

				par3World.setBlock(par4 - 1, par5 + 2, par6, AbyssalCraft.Darkstone);
				par3World.setBlock(par4 - 1, par5 + 3, par6, AbyssalCraft.Darkstone);
				par3World.setBlock(par4 - 1, par5 + 4, par6, AbyssalCraft.Darkstone);
				par3World.setBlock(par4 - 1, par5 + 5, par6, AbyssalCraft.Darkstone);

				par3World.setBlock(par4 + 2, par5 + 2, par6, AbyssalCraft.Darkstone);
				par3World.setBlock(par4 + 2, par5 + 3, par6, AbyssalCraft.Darkstone);
				par3World.setBlock(par4 + 2, par5 + 4, par6, AbyssalCraft.Darkstone);
				par3World.setBlock(par4 + 2, par5 + 5, par6, AbyssalCraft.Darkstone);

				par3World.setBlock(par4, par5 + 5, par6, AbyssalCraft.Darkstone);
				par3World.setBlock(par4 + 1, par5 + 5, par6, AbyssalCraft.Darkstone);

				par3World.setBlock(par4 + 1, par5 + 2, par6, AbyssalCraft.Coraliumfire);
			}
			return true;
		}
		else
			return false;
	}


	/**  When portal block generation has been changed to match the new design, replace the methods
         	above with these (keep the Coralium Fire)

         				   par3World.setBlock(par4, par5 + 1, par6 - 2, AbyssalCraft.Darkstone);
                           par3World.setBlock(par4, par5 + 2, par6 - 2, AbyssalCraft.Darkstone);
                           par3World.setBlock(par4, par5 + 3, par6 - 2, AbyssalCraft.Darkstone);
                           par3World.setBlock(par4, par5 + 4, par6 - 2, AbyssalCraft.Darkstone);
                           par3World.setBlock(par4, par5 + 5, par6 - 2, AbyssalCraft.Darkstone);

                           par3World.setBlock(par4, par5 + 2, par6 + 2, AbyssalCraft.Darkstone);
                           par3World.setBlock(par4, par5 + 3, par6 + 2, AbyssalCraft.Darkstone);
                           par3World.setBlock(par4, par5 + 4, par6 + 2, AbyssalCraft.Darkstone);
                           par3World.setBlock(par4, par5 + 5, par6 + 2, AbyssalCraft.Darkstone);

                           par3World.setBlock(par4, par5 + 5, par6, AbyssalCraft.Darkstone);
                           par3World.setBlock(par4, par5 + 5, par6 + 1, AbyssalCraft.Darkstone);
                           par3World.setBlock(par4, par5 + 5, par6 - 1, AbyssalCraft.Darkstone);

						   par3World.setBlock(par4 - 2, par5 + 1, par6, AbyssalCraft.Darkstone);
                           par3World.setBlock(par4 - 2, par5 + 2, par6, AbyssalCraft.Darkstone);
                           par3World.setBlock(par4 - 2, par5 + 3, par6, AbyssalCraft.Darkstone);
                           par3World.setBlock(par4 - 2, par5 + 4, par6, AbyssalCraft.Darkstone);
                           par3World.setBlock(par4 - 2, par5 + 5, par6, AbyssalCraft.Darkstone);

                           par3World.setBlock(par4 + 2, par5 + 2, par6, AbyssalCraft.Darkstone);
                           par3World.setBlock(par4 + 2, par5 + 3, par6, AbyssalCraft.Darkstone);
                           par3World.setBlock(par4 + 2, par5 + 4, par6, AbyssalCraft.Darkstone);
                           par3World.setBlock(par4 + 2, par5 + 5, par6, AbyssalCraft.Darkstone);

                           par3World.setBlock(par4, par5 + 5, par6, AbyssalCraft.Darkstone);
                           par3World.setBlock(par4 + 1, par5 + 5, par6, AbyssalCraft.Darkstone);
                           par3World.setBlock(par4 - 1, par5 + 5, par6, AbyssalCraft.Darkstone);
	 */
}