package com.alice.arena.data.skills;

import com.alice.arena.components.CharactherComponent;
import com.alice.arena.components.PositionComponent;
import com.alice.arena.components.VelocityComponent;
import com.alice.arena.data.Skill;
import com.alice.arena.utils.Assets;
import com.alice.arena.utils.TextureHolder;
import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class CallAid extends Skill {

	public CallAid() {
		super("Call Aid", new TextureHolder(Assets.GetTexture("icon_callAid")), null, 
				1, 60, 50, "Call for some soldiers.");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void SkillInit(CharactherComponent cc) {
		// TODO Auto-generated method stub

	}

	@Override
	public void SkillUpdate(CharactherComponent cc, Engine en, float delta, PositionComponent pc, VelocityComponent vc,
			int index) {
		// TODO Auto-generated method stub

	}

	@Override
	public void SkillRender(SpriteBatch batch, ShapeRenderer shapeRebderer, CharactherComponent cc,
			PositionComponent pc, int index) {
		// TODO Auto-generated method stub

	}

	@Override
	public void ActiveCall(CharactherComponent cc, PositionComponent pc, int index) {
		// TODO Auto-generated method stub

	}

}
