package com.alice.arena.data;

import com.alice.arena.components.AIComponent;
import com.alice.arena.components.CharactherComponent;
import com.alice.arena.components.PositionComponent;
import com.alice.arena.components.VelocityComponent;
import com.alice.arena.utils.TextureHolder;
import com.alice.arena.utils.UtilFunctions;

public class StandartRaceAdapter extends Race {

	public StandartRaceAdapter(int id, String name, TextureHolder texture, int width, int height, float baseHealth,
			float baseEnergy, float baseSpeed, float baseStrength, float baseArmor, float baseVisibility,
			float baseVision, float baseHealthRegen, float baseEnergyRegen, Skill... skills) {
		super(id, name, texture, width, height, baseHealth, baseEnergy, baseSpeed, baseStrength, baseArmor,
				baseVisibility, baseVision, baseHealthRegen, baseEnergyRegen, skills);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void RacialInit(CharactherComponent cc) {
		// TODO Auto-generated method stub

	}

	@Override
	public void RacialUpdate(CharactherComponent cc, float delta, PositionComponent pc, VelocityComponent vc) {
		UtilFunctions.StandartRaceAnim(cc, vc, delta);

	}

	@Override
	public void RacialAIUpdate(CharactherComponent cc, AIComponent aic, float delta, PositionComponent pc,
			VelocityComponent vc) {
		// TODO Auto-generated method stub

	}

}
