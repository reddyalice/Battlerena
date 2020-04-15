package com.alice.arena.systems;

import com.alice.arena.components.CharactherComponent;
import com.alice.arena.components.PositionComponent;
import com.alice.arena.data.Skill;
import com.alice.arena.screens.PlayScreen;
import com.alice.arena.utils.Assets;
import com.alice.arena.utils.TextureHolder;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class CharactherRenderingSystem extends IteratingSystem {

	private SpriteBatch batch;
	private ShapeRenderer shapeRenderer;
	private TextureHolder circle;
	
	
	public CharactherRenderingSystem(SpriteBatch batch, ShapeRenderer shapeRenderer) {
		super(Family.all(PositionComponent.class, CharactherComponent.class).get());
		this.batch = batch;
		this.shapeRenderer = shapeRenderer;
		circle = new TextureHolder(Assets.GetTexture("circle"));
	}
	
	@Override
	public void addedToEngine(Engine engine) {
		super.addedToEngine(engine);
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		
		PositionComponent pc = entity.getComponent(PositionComponent.class);
		CharactherComponent cc = entity.getComponent(CharactherComponent.class);
		
		Color c = new Color(Color.WHITE);
		float vis = (cc.visibility +  PlayScreen.playerChar.vision) / 2f;
		if(vis < 1f && vis >= 0.5f)
			c.a *= vis;
		
		
		
		

		batch.setColor(c);
		cc.race.texture.Draw(batch, pc.x, pc.y, cc.race.width, cc.race.height, cc.raceAnimationStep, cc.flip, false,  0);
		cc.style.texture.Draw(batch, pc.x, pc.y, cc.style.width, cc.style.height, cc.styleAnimationStep, cc.flip, false, 0);
		
		
	
		
		
		
		shapeRenderer.set(ShapeType.Filled);
		shapeRenderer.setColor(Color.BLACK);
		
		
		shapeRenderer.rectLine( pc.x, pc.y + cc.race.height + 13.75f,  pc.x + cc.race.width, pc.y + cc.race.height + 13.75f, 7.5f);
		
		shapeRenderer.setColor(Color.GREEN);
		shapeRenderer.rectLine( pc.x, pc.y + cc.race.height + 15f,  pc.x + cc.race.width * (cc.health / cc.maxHealth), pc.y + cc.race.height + 15f, 5f);
		
		shapeRenderer.setColor(Color.CYAN);
		shapeRenderer.rectLine( pc.x, pc.y + cc.race.height + 11.25f,  pc.x + cc.race.width * (cc.energy / cc.maxEnergy), pc.y + cc.race.height + 11.25f, 2.5f);
		
		shapeRenderer.setColor(0, 75f/255f, 0, 1f);
		for(int i = 1; i < cc.maxHealth / 20f; i++)
		{	
			float x = (pc.x + cc.race.width * ((20f * i)) / cc.maxHealth);
			shapeRenderer.rectLine(x, pc.y + cc.race.height + 17.5f,x, pc.y + cc.race.height + 15f, 1f);
		}
		
		shapeRenderer.setColor(Color.BLUE);
		for(int i = 1; i < cc.maxEnergy / 20f; i++)
		{	
			float x = (pc.x + cc.race.width * ((20f * i)) / cc.maxEnergy);
			shapeRenderer.rectLine(x, pc.y + cc.race.height + 11.25f,x, pc.y + cc.race.height + 10f, 1f);
		}
		
		
		int i = 0;
		for(Skill s : cc.skill) {
			s.SkillRender(batch, shapeRenderer ,cc, pc, i);
			i++;
		}
		c.a = 0.5f;
		batch.setColor(c);
		circle.Draw(batch,  pc.x - 16f, pc.y - 16f, 32, 32, 64, 64,0, false, false, cc.rotation);

		
	}

}
