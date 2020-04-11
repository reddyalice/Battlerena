package com.alice.arena.data.skills;

import com.alice.arena.components.CharactherComponent;
import com.alice.arena.components.PositionComponent;
import com.alice.arena.components.VelocityComponent;
import com.alice.arena.data.Skill;
import com.alice.arena.utils.Assets;
import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class HumanSpirit extends Skill {

	

	
	public HumanSpirit() {
		super("Human's Spirit", null, 1, 20, "Human's Stubborness");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void SkillInit(CharactherComponent cc) {
	}

	@Override
	public void SkillUpdate(CharactherComponent cc, Engine en, float delta, PositionComponent pc,VelocityComponent vc,int index) {
		
		if(cc.progress[index] == 0) {
			if(cc.energy <= cc.maxEnergy * 10f / 100f)
			{
				cc.energy +=  cc.maxEnergy * 30f / 100f;
				cc.progress[index] += delta;
			}
			
		}else if(cc.progress[index] > 0 && cc.progress[index]  < cooldown ) {
			cc.progress[index] += delta;
		}else {
			cc.progress[index] = 0;
		}
		
		
	}

	@Override
	public void SkillRender(SpriteBatch batch, CharactherComponent cc, PositionComponent pc, int index) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ActiveCall(CharactherComponent cc, PositionComponent pc, int index) {
		
		
	}



}
