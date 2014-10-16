package com.djgiannuzz.mkdollymod.renderer;

import net.minecraft.client.model.ModelBiped;

public class ModelPlayer extends ModelBiped
{
	public void renderModel()
	{
		float f5 = 0.0625F;
		bipedHead.render(f5);
		bipedBody.render(f5);
		bipedRightArm.render(f5);
		bipedLeftArm.render(f5);
		bipedRightLeg.render(f5);
		bipedLeftLeg.render(f5);
	}
}
