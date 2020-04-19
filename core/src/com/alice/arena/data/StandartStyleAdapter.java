package com.alice.arena.data;

import com.alice.arena.components.AIComponent;
import com.alice.arena.components.CharactherComponent;
import com.alice.arena.components.PositionComponent;
import com.alice.arena.components.VelocityComponent;
import com.alice.arena.utils.TextureHolder;

public class StandartStyleAdapter extends Style {

	public StandartStyleAdapter(int id, String name, TextureHolder texture, int width, int height, int healthPercentMul,
			int energyPercentMul, int speedPercentMul, int strengthPercentMul, int armorPercentMul,
			int visibilityPercentMul, int visionPercentMul, int healthRegenPercentMul, int energyRegenPercentMul,
			Skill... skills) {
		super(id, name, texture, width, height, healthPercentMul, energyPercentMul, speedPercentMul, strengthPercentMul,
				armorPercentMul, visibilityPercentMul, visionPercentMul, healthRegenPercentMul, energyRegenPercentMul,
				skills);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void StyleInit(CharactherComponent cc) {
		// TODO Auto-generated method stub

	}

	@Override
	public void StyleUpdate(CharactherComponent cc, float deltaTime, PositionComponent pc, VelocityComponent vc) {
		// TODO Auto-generated method stub

	}

	@Override
	public void StyleAIUpdate(CharactherComponent cc, AIComponent aic, float deltaTime, PositionComponent pc,
			VelocityComponent vc) {
		// TODO Auto-generated method stub

	}

}
