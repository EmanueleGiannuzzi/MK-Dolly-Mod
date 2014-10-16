package com.djgiannuzz.mkdollymod.renderer;

import java.util.Map;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.djgiannuzz.mkdollymod.LogHelper;
import com.djgiannuzz.mkdollymod.blocks.TEDolly;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import com.mojang.authlib.minecraft.MinecraftProfileTexture.Type;

public class RenderDolly extends TileEntitySpecialRenderer
{
	private static ResourceLocation texture = AbstractClientPlayer.locationStevePng;;

	private ModelPlayer model;
	
	public RenderDolly()
	{
		this.model = new ModelPlayer();
	}
	
	public void setSkinResourceLocation(GameProfile profile)
	{
		if (profile != null)
        {
            Minecraft minecraft = Minecraft.getMinecraft();
            Map map = minecraft.func_152342_ad().func_152788_a(profile);

            if (map.containsKey(Type.SKIN))
            {
                texture = minecraft.func_152342_ad().func_152792_a((MinecraftProfileTexture)map.get(Type.SKIN), Type.SKIN);
            }

        }
		else
			texture = AbstractClientPlayer.locationStevePng;;
	}
	
	@Override
	public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float f) 
	{
		this.RenderAModelAt((TileEntity)tileentity, x, y, z, f);
	}
	
	public void RenderAModelAt(TileEntity tileentity, double par2, double par4, double par6, float par8)
	{
		int metadata = tileentity.getBlockMetadata();
		int rotationAngle = 0;
		
		if(metadata%4 == 0)
		{
			rotationAngle = 270;
		}
		else if(metadata%4 == 1)
		{
			rotationAngle = 0;
		}
		else if(metadata%4 == 2)
		{
			rotationAngle = 90;
		}
		else if(metadata%4 == 3)
		{
			rotationAngle = 180;
		}
		
		if(tileentity instanceof TEDolly)
		{
			setSkinResourceLocation(((TEDolly)tileentity).gameProfile);
			if(((TEDolly)tileentity).gameProfile != null)
				LogHelper.debug("Name: " + ((TEDolly)tileentity).gameProfile.getName());
		}
		
		GL11.glPushMatrix();
			GL11.glTranslated((float)par2 + 0.5F, (float)par4 + 0.7F, (float)par6 + 0.5F);
			GL11.glScalef(0.5F, -0.5F, -0.5F);
			GL11.glRotatef(rotationAngle , 0.0F, 1.0F, 0.0F);
			this.bindTexture(texture);
			model.renderModel();
		GL11.glPopMatrix();
	}

}
