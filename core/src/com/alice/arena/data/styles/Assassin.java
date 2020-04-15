package com.alice.arena.data.styles;

import com.alice.arena.components.AIComponent;
import com.alice.arena.components.CharactherComponent;
import com.alice.arena.components.PositionComponent;
import com.alice.arena.components.VelocityComponent;
import com.alice.arena.data.Registry;
import com.alice.arena.data.Skill;
import com.alice.arena.data.Style;
import com.alice.arena.utils.Assets;
import com.alice.arena.utils.TextureHolder;

public class Assassin extends Style {

	public Assassin() {
		super(5, "Assassin", new TextureHolder(Assets.GetTexture("assassin"), 16, 24), 32, 48, 
				0, 10, 20, 0,
				0, -40, 40, 0, 0,
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

	}

	@Override
	public void StyleAIUpdate(CharactherComponent cc, AIComponent aic, float deltaTime, PositionComponent pc,
			VelocityComponent vc) {
		// TODO Auto-generated method stub

	}

}
