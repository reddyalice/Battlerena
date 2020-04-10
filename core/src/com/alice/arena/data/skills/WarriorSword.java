package com.alice.arena.data.skills;

import com.alice.arena.components.CharactherComponent;
import com.alice.arena.components.PositionComponent;
import com.alice.arena.components.VelocityComponent;
import com.alice.arena.data.Skill;
import com.alice.arena.utils.Assets;
import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class WarriorSword extends Skill {

	public WarriorSword() {
		super("Warrior's Sword", Assets.GetTexture("warriorsword"), 1, 1, "A Warrior's Trustworthy Sword");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void SkillInit(CharactherComponent cc) {
		// TODO Auto-generated method stub

	}

	@Override
	public void SkillUpdate(CharactherComponent cc, Engine en, float delta, PositionComponent pc, VelocityComponent vc, int index) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void SkillRender(SpriteBatch batch, CharactherComponent cc, PositionComponent pc, int index) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ActiveCall(CharactherComponent cc, int index) {
		// TODO Auto-generated method stub
		
	}





}
