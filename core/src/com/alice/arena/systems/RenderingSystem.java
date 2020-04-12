package com.alice.arena.systems;

import com.alice.arena.components.CharactherComponent;
import com.alice.arena.components.PositionComponent;
import com.alice.arena.data.Skill;
import com.alice.arena.screens.PlayScreen;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class RenderingSystem extends IteratingSystem {

	private ComponentMapper<PositionComponent> pm = ComponentMapper.getFor(PositionComponent.class);
	private ComponentMapper<CharactherComponent> cm = ComponentMapper.getFor(CharactherComponent.class);
	private SpriteBatch batch;
	private ShapeRenderer shapeRenderer;
	
	public RenderingSystem(SpriteBatch batch, ShapeRenderer shapeRenderer) {
		super(Family.all(PositionComponent.class, CharactherComponent.class).get());
		this.batch = batch;
		this.shapeRenderer = shapeRenderer;
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
		float vis = (cc.visibility +  PlayScreen.playerChar.vision) / 2f;
		
		if(vis < 1f && vis >= 0.5f)
			c.a *= vis;
		
		if(cc.lookDir.x < 0 )
			cc.flip = true;
		
		if(cc.lookDir.x > 0 )
			cc.flip = false;
		
		

		batch.setColor(c);
		cc.race.texture.Draw(batch, pc.x, pc.y, cc.race.width, cc.race.height, cc.raceAnimationStep, cc.flip, false,  0);
		cc.style.texture.Draw(batch, pc.x, pc.y, cc.style.width, cc.style.height, cc.styleAnimationStep, cc.flip, false, 0);

		int i = 0;
		for(Skill s : cc.skill) {
			s.SkillRender(batch, shapeRenderer ,cc, pc, i);
			i++;
		}

		
	}

}
