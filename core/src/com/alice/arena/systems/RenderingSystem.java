package com.alice.arena.systems;

import com.alice.arena.components.CharactherComponent;
import com.alice.arena.components.PositionComponent;
import com.alice.arena.data.Skill;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class RenderingSystem extends IteratingSystem {

	private ComponentMapper<PositionComponent> pm = ComponentMapper.getFor(PositionComponent.class);
	private ComponentMapper<CharactherComponent> cm = ComponentMapper.getFor(CharactherComponent.class);
	private SpriteBatch batch;
	
	
	public RenderingSystem(SpriteBatch batch) {
		super(Family.all(PositionComponent.class, CharactherComponent.class).get());
		this.batch = batch;
	}
	
	@Override
	public void addedToEngine(Engine engine) {
		super.addedToEngine(engine);
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		
		PositionComponent pc = pm.get(entity);
		CharactherComponent cc = cm.get(entity);
		
		
		
		batch.draw(cc.race.racialTexture, pc.x, pc.y, 32,32, 64, 64, 1, 1, cc.rotation, 0,0
				,cc.race.racialTexture.getWidth(), cc.race.racialTexture.getHeight(), false, false);
		batch.draw(cc.style.styleTexture, pc.x, pc.y,32,32, 64, 64, 1, 1, cc.rotation, 0,0
				,cc.style.styleTexture.getWidth(), cc.style.styleTexture.getHeight(), false, false);
		int i = 0;
		for(Skill s : cc.skill) {
			s.SkillRender(batch, cc, pc, i);
			i++;
		}
		
		
		
	}

}
