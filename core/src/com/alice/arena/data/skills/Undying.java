package com.alice.arena.data.skills;

import com.alice.arena.components.AIComponent;
import com.alice.arena.components.CharactherComponent;
import com.alice.arena.components.PositionComponent;
import com.alice.arena.components.VelocityComponent;
import com.alice.arena.data.Skill;
import com.alice.arena.utils.Assets;
import com.alice.arena.utils.TextureHolder;
import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Undying extends Skill {

	public Undying() {
		super("Undying", new TextureHolder(Assets.GetTexture("icon_undying")), null, 
				1, 0, 0, "You will reborn as skeleton once you are dead.");
		
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
	public void SkillAIUpdate(CharactherComponent cc, AIComponent aic, Engine en, float delta, PositionComponent pc,
			VelocityComponent vc, int index) {
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
	
	@Override
	public boolean SkillDeadCall(CharactherComponent cc) {
		// TODO Auto-generated method stub
		return super.SkillDeadCall(cc);
	}

}
