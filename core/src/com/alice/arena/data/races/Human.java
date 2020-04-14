package com.alice.arena.data.races;

import com.alice.arena.components.CharactherComponent;
import com.alice.arena.components.PositionComponent;
import com.alice.arena.components.VelocityComponent;
import com.alice.arena.data.Race;
import com.alice.arena.data.Registry;
import com.alice.arena.data.Style;
import com.alice.arena.utils.Assets;
import com.alice.arena.utils.TextureHolder;
import com.alice.arena.utils.UtilFunctions;
import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Human extends Race {

	public Human() {
		super(0, "Human", new TextureHolder(Assets.GetTexture("human"), 16, 24), 32, 48,
				100, 100, 1, 1, 1, 
				1f, 1f,  5f, 10f,
				Registry.SKILLS.VeryNormal,
				Registry.SKILLS.HumanSpirit);
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
	public void RacialAIUpdate(CharactherComponent cc, float delta, PositionComponent pc, VelocityComponent vc) {
		// TODO Auto-generated method stub
		
	}






}
