package com.alice.arena.systems;

import java.util.ArrayList;

import com.alice.arena.components.AIComponent;
import com.alice.arena.components.CharactherComponent;
import com.alice.arena.components.PhysicsComponent;
import com.alice.arena.components.PositionComponent;
import com.alice.arena.components.VelocityComponent;
import com.alice.arena.data.Skill;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;

public class AIUpdateSystem extends EntitySystem {

	private Engine engine;
	private ImmutableArray<Entity> entities;

	@Override
	public void addedToEngine(Engine engine) {
		this.engine = engine;
		this.entities = engine.getEntitiesFor(Family.all(AIComponent.class).get());
	}
	
	
	private void processEntity(Entity entity, float deltaTime) {
		PositionComponent pc =  entity.getComponent(PositionComponent.class);
		CharactherComponent cc = entity.getComponent(CharactherComponent.class);
		VelocityComponent vc =  entity.getComponent(VelocityComponent.class);
		PhysicsComponent phc = entity.getComponent(PhysicsComponent.class);
		AIComponent aic = entity.getComponent(AIComponent.class);
		
		
		cc.race.RacialAIUpdate(cc, deltaTime, pc, vc);
		cc.style.StyleAIUpdate(cc, deltaTime, pc, vc);
		Thread[] skillThreads = new Thread[cc.skill.length];
		int i = 0;
		for(Skill s : cc.skill) {
			int k = i;
			skillThreads[i] = new Thread(new Runnable() {
				@Override
				public void run() {
					s.SkillAIUpdate(cc, engine, deltaTime, pc, vc, k);
					
				}
			});
			skillThreads[i].run();
			i++;
		}
		
		
		for(Thread t : skillThreads)
			try {
				t.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
		
		
		
	}
	
	@Override
	public void update(float deltaTime) {
		Thread[] threads = new Thread[entities.size()];
		for (int i = 0; i < entities.size(); ++i) {
			Entity e = entities.get(i);
			Thread t = new Thread(new Runnable() {
				
				@Override
				public void run() {
					processEntity(e, deltaTime);
					
				}
			});
			threads[i] = t;
			t.run();
		}
		
		for(Thread t : threads)
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		
		
		
	}

}
