package com.djgiannuzz.mkdollymod.gui;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import com.djgiannuzz.mkdollymod.LogHelper;
import com.djgiannuzz.mkdollymod.MKDollyMod;
import com.djgiannuzz.mkdollymod.blocks.TEDolly;
import com.djgiannuzz.mkdollymod.packets.PacketHandler;
import com.djgiannuzz.mkdollymod.packets.RequestGuiProfilePacket;

public class GuiPlayerName extends GuiScreen
{
	private int x,y,z;
	private static ResourceLocation tex = new ResourceLocation("textures/gui/container/generic_54.png");

	private static int xSize = 176;
	private static int ySize = 166;
	
	private TEDolly te;
	private GuiTextField textBox;
	
	public GuiPlayerName(int x, int y, int z, World world, String username)
	{
		this.x = x;
		this.y = y;
		this.z = z;
		this.allowUserInput = true;
		
		TileEntity tile = world.getTileEntity(x, y, z);
		if(tile instanceof TEDolly)
		{
			te = (TEDolly)tile;
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
    public void initGui()
    {
        this.buttonList.clear();
        Keyboard.enableRepeatEvents(true);
        this.textBox = new GuiTextField(this.fontRendererObj, ((this.width - xSize) / 2) + 23, ((this.height - ySize) / 2) + 40, 120, this.fontRendererObj.FONT_HEIGHT + 4);
        this.textBox.setMaxStringLength(20);
        this.textBox.setEnableBackgroundDrawing(true);
        this.textBox.setTextColor(16777215);
        this.textBox.setCanLoseFocus(false);
        this.textBox.setFocused(true);
    }
	
	@Override
	protected void keyTyped(char ch, int charNo)
    {
		super.keyTyped(ch, charNo);
		if(charNo == 28)
		{
			PacketHandler.network.sendToServer(new RequestGuiProfilePacket(x, y, z, textBox.getText()));
			this.mc.displayGuiScreen((GuiScreen)null);
            this.mc.setIngameFocus();
		}
		else
			this.textBox.textboxKeyTyped(ch, charNo);
    }
	
	@Override
    public void drawScreen(int x, int y, float var3)
    {
        GL11.glColor4f(0F, 1F, 0F, 1F);
		this.mc.renderEngine.bindTexture(tex);

        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l + 30, 0, 0, this.xSize, 5);
        
        this.drawTexturedModalRect(k, l + 35, 0, 125, this.xSize, 14);
        this.drawTexturedModalRect(k, l + 49, 0, 125, this.xSize, 11	);
    	
        this.drawTexturedModalRect(k, l + 60, 0, 218, this.xSize, 5);
        
        this.textBox.drawTextBox();


    	super.drawScreen(x, y, var3);
    	
    }
}
