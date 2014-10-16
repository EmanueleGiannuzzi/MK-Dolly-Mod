package com.djgiannuzz.mkdollymod.packets;

import com.djgiannuzz.mkdollymod.MKDollyMod;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;

public class PacketHandler 
{
	public static SimpleNetworkWrapper network;   
	
	public static void init()
	{
		network = NetworkRegistry.INSTANCE.newSimpleChannel(MKDollyMod.MODID);
    	registerPackets();
	}
	
	public static void registerPackets()
	{
		network.registerMessage(RequestGuiProfilePacket.Handler.class, RequestGuiProfilePacket.class, 0, Side.SERVER);
	}
}
