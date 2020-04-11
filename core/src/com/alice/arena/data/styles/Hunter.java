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

public class Hunter extends Style {

	public Hunter() {
		super(1, "Hunter", new TextureHolder(Assets.GetTexture("hunter"), 16, 24), 32, 48, 
				-10, 0, 10, -30,
				-30, 0, 30, 0, 0,
				Registry.SKILLS.ShootArrow);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void StyleInit(CharactherComponent cc) {
		// TODO Auto-generated method stub

	}

	@Override
	public void StyleUpdate(CharactherComponent cc, float deltaTime, PositionComponent pc, VelocityComponent vc) {
		// TODO Auto-generated method stub
		UtilFunctions.StandartStyleAnim(cc, vc, deltaTime);
	}

}
