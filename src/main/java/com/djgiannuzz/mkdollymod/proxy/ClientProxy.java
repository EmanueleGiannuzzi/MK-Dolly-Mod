package com.djgiannuzz.mkdollymod.proxy;

import com.djgiannuzz.mkdollymod.blocks.TEDolly;
import com.djgiannuzz.mkdollymod.renderer.RenderDolly;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;


public class ClientProxy extends CommonProxy
{
	public void init()
	{
		ClientRegistry.bindTileEntitySpecialRenderer(TEDolly.class, new RenderDolly());
	}
}
