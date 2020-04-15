package com.alice.arena.data.races;

import com.alice.arena.components.AIComponent;
import com.alice.arena.components.CharactherComponent;
import com.alice.arena.components.PositionComponent;
import com.alice.arena.components.VelocityComponent;
import com.alice.arena.data.Race;
import com.alice.arena.data.Registry;
import com.alice.arena.data.Skill;
import com.alice.arena.utils.Assets;
import com.alice.arena.utils.TextureHolder;
import com.alice.arena.utils.UtilFunctions;

public class Glitch extends Race {

	public Glitch() {
		super(2, "Glitch", new TextureHolder(Assets.GetTexture("robot"), 16,24), 32, 48, 100, 150, 1.2f, 1.1f, 0.9f,
				3f, 2f, 3, -1, Registry.SKILLS.VeryNormal);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void RacialInit(CharactherComponent cc) {
		// TODO Auto-generated method stub

	}

	@Override
	public void RacialUpdate(CharactherComponent cc, float delta, PositionComponent pc, VelocityComponent vc) {
		// TODO Auto-generated method stub
		UtilFunctions.StandartRaceAnim(cc, vc, delta);

	}

	@Override
	public void RacialAIUpdate(CharactherComponent cc, AIComponent aic, float delta, PositionComponent pc, VelocityComponent vc) {
		// TODO Auto-generated method stub
		
	}

}
