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

public class Necromancer extends Style {

	public Necromancer() {
		super(4, "Necromancer", new TextureHolder(Assets.GetTexture("necromancer"), 16, 24), 32, 48, 
				20, 20, -30, -20,
				20, 50, -20, -20, 10,
				Registry.SKILLS.RaiseDead);
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
