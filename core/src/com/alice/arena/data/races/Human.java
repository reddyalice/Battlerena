package com.alice.arena.data.races;

import com.alice.arena.components.CharactherComponent;
import com.alice.arena.components.PositionComponent;
import com.alice.arena.components.VelocityComponent;
import com.alice.arena.data.Race;
import com.alice.arena.data.Registry;
import com.alice.arena.utils.Assets;
import com.badlogic.ashley.core.Engine;

public class Human extends Race {

	public Human() {
		super(0, "Human", Assets.GetTexture("human"),
				100, 100, 1, 1, 1, 1, 10f, 10f,
				Registry.SKILLS.VeryNormal,
				Registry.SKILLS.HumanSpirit);
	}

	@Override
	public void RacialInit(CharactherComponent cc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void RacialUpdate(CharactherComponent cc, Engine en, float delta, PositionComponent pc, VelocityComponent vc) {
		// TODO Auto-generated method stub
		
	}



}
