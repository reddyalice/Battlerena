package com.alice.arena.data.skills;

import com.alice.arena.components.CharactherComponent;
import com.alice.arena.components.PositionComponent;
import com.alice.arena.data.Skill;
import com.alice.arena.utils.Assets;
import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class WarriorShield extends Skill{

	public WarriorShield() {
		super("Warrior's Shield", Assets.GetTexture("warriorshield"), 1, 0, "Warrior's Trusted Shield");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void SkillInit(CharactherComponent cc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void SkillUpdate(CharactherComponent cc, Engine en, float delta, PositionComponent pc, int index) {

		
		
	}

	@Override
	public void SkillRender(SpriteBatch batch, CharactherComponent cc, PositionComponent pc, int index) {
		
		batch.draw(texture, pc.x  + cc.lookDir.x * 40f, pc.y + cc.lookDir.y * 40f, 32,32, 64, 64, 1, 1, cc.rotation, 0,0
				,texture.getWidth(), texture.getHeight(), false, false);
		
	}



}
