package com.alice.arena.data.skills;

import com.alice.arena.components.CharactherComponent;
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
	public void SkillUpdate(CharactherComponent cc, Engine en, float delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void SkillRender(SpriteBatch batch, CharactherComponent cc) {
		// TODO Auto-generated method stub
		
	}

}
