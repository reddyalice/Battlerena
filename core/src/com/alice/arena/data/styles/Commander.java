package com.alice.arena.data.styles;

import com.alice.arena.components.CharactherComponent;
import com.alice.arena.components.PositionComponent;
import com.alice.arena.components.VelocityComponent;
import com.alice.arena.data.Registry;
import com.alice.arena.data.Skill;
import com.alice.arena.data.Style;
import com.alice.arena.utils.Assets;
import com.alice.arena.utils.TextureHolder;
import com.alice.arena.utils.UtilFunctions;

public class Commander extends Style {

	public Commander() {
		super(3, "Commander", new TextureHolder(Assets.GetTexture("commander"), 16, 24), 32, 48, 
				0, 0, 0, 0,
				30, 50, 10, 20, 10,
				Registry.SKILLS.ShootArrow,
				Registry.SKILLS.WarriorSword);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void StyleInit(CharactherComponent cc) {

	}

	@Override
	public void StyleUpdate(CharactherComponent cc, float deltaTime, PositionComponent pc, VelocityComponent vc) {
		// TODO Auto-generated method stub
		UtilFunctions.StandartStyleAnim(cc, vc, deltaTime);
	}

}
