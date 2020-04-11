package com.alice.arena.systems;

import com.alice.arena.components.CharactherComponent;
import com.alice.arena.components.PositionComponent;
import com.alice.arena.data.Skill;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

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
		
		Color c = new Color(Color.WHITE);
		if(cc.visibility < 1f)
		c.a *= cc.visibility;
		
		if(cc.lookDir.x < 0 )
			cc.flip = true;
		
		if(cc.lookDir.x > 0 )
			cc.flip = false;
		
		

		batch.setColor(c);
		cc.race.texture.Draw(batch, pc.x, pc.y, cc.race.width, cc.race.height, cc.raceAnimationStep, cc.flip, false,  0);
		cc.style.texture.Draw(batch, pc.x, pc.y, cc.style.width, cc.style.height, cc.styleAnimationStep, cc.flip, false, 0);

		int i = 0;
		for(Skill s : cc.skill) {
			s.SkillRender(batch, cc, pc, i);
			i++;
		}

		
	}

}
