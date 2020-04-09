package com.alice.arena.data.skills;

import com.alice.arena.components.CharactherComponent;
import com.alice.arena.data.Skill;
import com.alice.arena.utils.Assets;
import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class HumanSpirit extends Skill {

	public HumanSpirit() {
		super("Human's Spirit", Assets.GetTexture("humanspirit"), 1, 0, "Human's Stubborness");
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
