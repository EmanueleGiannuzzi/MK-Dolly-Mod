package com.djgiannuzz.mkdollymod.blocks;

import java.util.UUID;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

import com.mojang.authlib.GameProfile;

public class TEDolly extends TileEntity
{
	public GameProfile gameProfile;
	
	@Override
    public void writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);
        writeToNBTForDescriptionPacket(nbt);
    }
	
	private void writeToNBTForDescriptionPacket(NBTTagCompound nbt)
    {
		writeGameProfileToNBT(nbt, gameProfile);
    }
	
	private void writeGameProfileToNBT(NBTTagCompound nbt, GameProfile gp)
	{
		if(gameProfile != null)
		{
			nbt.setString("playerName", gameProfile.getName());
			nbt.setLong("MSB", gameProfile.getId().getMostSignificantBits());
			nbt.setLong("LSB", gameProfile.getId().getLeastSignificantBits());
		}
	}
	
    @Override
	public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);
        readGameProfileFromNBT(nbt);
    }
    
    private void readGameProfileFromNBT(NBTTagCompound nbt)
    {
    	if(nbt.hasKey("MSB") && nbt.hasKey("LSB") && nbt.hasKey("playerName"))
    		gameProfile = new GameProfile(new UUID(nbt.getLong("MSB"), nbt.getLong("LSB")), nbt.getString("playerName"));
    }
    
    @Override
	public Packet getDescriptionPacket()
	{
		NBTTagCompound nbt = new NBTTagCompound();
		writeToNBT(nbt);
		return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 1, nbt);
	}
    
	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt)
	{
		readFromNBT(pkt.func_148857_g());
	}
}
