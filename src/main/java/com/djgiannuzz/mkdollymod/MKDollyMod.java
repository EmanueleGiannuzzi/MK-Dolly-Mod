package com.djgiannuzz.mkdollymod;

import com.djgiannuzz.mkdollymod.blocks.BlockDolly;
import com.djgiannuzz.mkdollymod.blocks.TEDolly;
import com.djgiannuzz.mkdollymod.gui.GuiHandler;
import com.djgiannuzz.mkdollymod.packets.PacketHandler;
import com.djgiannuzz.mkdollymod.proxy.IProxy;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = MKDollyMod.MODID, name = MKDollyMod.NAME, version = MKDollyMod.VERSION)
public class MKDollyMod 
{
	public static final String MODID = "mkdollymod";
    public static final String NAME = "MK Dolly Mod";
    public static final String VERSION = "1.0";
    
    @Mod.Instance(MODID)
	public static MKDollyMod instance;
    
    @SidedProxy(clientSide = "com.djgiannuzz.mkdollymod.proxy.ClientProxy", serverSide = "com.djgiannuzz.mkdollymod.proxy.ServerProxy")
    public static IProxy proxy;
    
	public static boolean debugMode = true;
    
    public static final BlockDolly blockDolly = new BlockDolly();
    	
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
    	PacketHandler.init();
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    	proxy.init();
    	
    	GameRegistry.registerBlock(blockDolly, "blocKDolly");
        GameRegistry.registerTileEntity(TEDolly.class, MODID+":" + "teDolly");
        
        NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
    	
    }
}
