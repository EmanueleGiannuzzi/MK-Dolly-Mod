package com.djgiannuzz.mkdollymod.packets;

import io.netty.buffer.ByteBuf;

import java.util.UUID;

import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.djgiannuzz.mkdollymod.LogHelper;
import com.djgiannuzz.mkdollymod.blocks.TEDolly;
import com.mojang.authlib.GameProfile;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class RequestGuiProfilePacket implements IMessage
{
	private int x,y,z;
	private String playerName;

    public RequestGuiProfilePacket() { }

    
    public RequestGuiProfilePacket(int x, int y, int z, String name)
    {
    	this.x = x;
    	this.y = y;
    	this.z = z;
    	this.playerName = name;
    }

    @Override
    public void toBytes(ByteBuf buf) 
    {
    	buf.writeInt(x);
    	buf.writeInt(y);
    	buf.writeInt(z);
    	ByteBufUtils.writeUTF8String(buf, playerName);
    }

    @Override
    public void fromBytes(ByteBuf buf) 
    {
    	this.x = buf.readInt();
    	this.y = buf.readInt();
    	this.z = buf.readInt();
    	this.playerName = ByteBufUtils.readUTF8String(buf);
    }

    public static class Handler implements IMessageHandler<RequestGuiProfilePacket, IMessage> 
    {
        @Override
        public IMessage onMessage(RequestGuiProfilePacket message, MessageContext ctx) 
        {
        	World world = ctx.getServerHandler().playerEntity.worldObj;
        	TileEntity te = world.getTileEntity(message.x, message.y, message.z);
        	
        	if(te != null && te instanceof TEDolly)
        	{
        		TEDolly teDolly = (TEDolly) te;
        		teDolly.gameProfile = MinecraftServer.getServer().func_152358_ax().func_152655_a(message.playerName);  
        	}
        	return null; 
        }
    }
}
