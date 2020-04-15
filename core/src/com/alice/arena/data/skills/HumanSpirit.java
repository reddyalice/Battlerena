package com.alice.arena.data.skills;

import java.util.logging.SocketHandler;

import com.alice.arena.components.AIComponent;
import com.alice.arena.components.CharactherComponent;
import com.alice.arena.components.PositionComponent;
import com.alice.arena.components.VelocityComponent;
import com.alice.arena.data.Skill;
import com.alice.arena.utils.Assets;
import com.alice.arena.utils.TextureHolder;
import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class HumanSpirit extends Skill {

	ShapeRenderer renderer;

	
	public HumanSpirit() {
		super("Human's Spirit", new TextureHolder(Assets.GetTexture("icon_humanSpirit")), null, 
				1, 20, 0, "Human's Stubborness");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void SkillInit(CharactherComponent cc) {
		renderer = new ShapeRenderer();
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
	public void SkillRender(SpriteBatch batch, ShapeRenderer shapeRenderer, CharactherComponent cc, PositionComponent pc, int index) {
		//shapeRenderer.set(ShapeType.Line);
		//shapeRenderer.circle(pc.x + cc.race.width / 2f, pc.y + cc.race.height / 2f, 24, 100);
		

	}

	@Override
	public void ActiveCall(CharactherComponent cc, PositionComponent pc, int index) {
		
		
	}

	@Override
	public void SkillAIUpdate(CharactherComponent cc, AIComponent aic, Engine en, float delta, PositionComponent pc,
			VelocityComponent vc, int index) {
		// TODO Auto-generated method stub
		
	}



}
