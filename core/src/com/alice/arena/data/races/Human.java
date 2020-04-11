package com.alice.arena.data.races;

import com.alice.arena.components.CharactherComponent;
import com.alice.arena.components.PositionComponent;
import com.alice.arena.components.VelocityComponent;
import com.alice.arena.data.Race;
import com.alice.arena.data.Registry;
import com.alice.arena.data.Style;
import com.alice.arena.utils.Assets;
import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Human extends Race {

	public Human() {
		super(0, "Human", Assets.GetTexture("human/body"), 
				new Texture[0],
				new Texture[0],
				100, 100, 1, 1, 1, 1, 10f, 10f,
				Registry.SKILLS.VeryNormal,
				Registry.SKILLS.HumanSpirit);
	}

	@Override
	public void RacialInit(CharactherComponent cc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void RacialUpdate(CharactherComponent cc, float delta, PositionComponent pc, VelocityComponent vc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void RacialDraw(SpriteBatch batch, CharactherComponent cc, float delta, PositionComponent pc) {
		batch.draw(cc.race.body, pc.x, pc.y, 32,32, 64, 64, 1, 1, 0, 0,0
				,cc.race.body.getWidth(), cc.race.body.getHeight(), cc.flip, false);
		batch.draw(cc.style.styleTexture, pc.x, pc.y,32,32, 64, 64, 1, 1,0, 0,0
				,cc.style.styleTexture.getWidth(), cc.style.styleTexture.getHeight(), cc.flip, false);
	}





}
