package com.alice.arena.data.styles;

import com.alice.arena.components.CharactherComponent;
import com.alice.arena.components.PositionComponent;
import com.alice.arena.components.VelocityComponent;
import com.alice.arena.data.Registry;
import com.alice.arena.data.Skill;
import com.alice.arena.data.Style;
import com.alice.arena.utils.Assets;
import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.graphics.Texture;

public class Warrior extends Style {

	public Warrior() {
		super(0, "Warrior", Assets.GetTexture("warrior"), 5, 0, -10, 5, 5, 5, 10, -5,
				Registry.SKILLS.WarriorSword,
				Registry.SKILLS.WarriorSheild);
	}

	@Override
	public void StyleInit(CharactherComponent cc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void StyleUpdate(CharactherComponent cc, float deltaTime, PositionComponent pc, VelocityComponent vc) {
		// TODO Auto-generated method stub
		
	}


	

}
