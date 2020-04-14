package com.alice.arena.systems;

import java.util.ArrayList;

import com.alice.arena.components.PhysicsComponent;
import com.alice.arena.components.PositionComponent;
import com.alice.arena.components.VelocityComponent;
import com.alice.arena.screens.PlayScreen;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Vector2;

public class MovementSystem extends EntitySystem {



	private ImmutableArray<Entity> entities;
	
	public MovementSystem() {
		super();
	}
	
	
	@Override
	public void addedToEngine(Engine engine) {
		entities = engine.getEntitiesFor(Family.all(PositionComponent.class, VelocityComponent.class, PhysicsComponent.class).get());
	}

	private void processEntity(Entity entity, float deltaTime) {
		
		PhysicsComponent phc = entity.getComponent(PhysicsComponent.class);
		PositionComponent pc = entity.getComponent(PositionComponent.class);
		VelocityComponent vc = entity.getComponent(VelocityComponent.class);
		
		phc.body.setLinearVelocity(vc.x, vc.y);
		Vector2  velL =  phc.body.getLinearVelocity();
		vc.speed2 = velL.len2();

		
		Vector2 pos = phc.body.getPosition();
		
		
		pc.x = pos.x - phc.pivot.x;
		pc.y = pos.y - phc.pivot.y;
		

	}
	
	
	@Override
	public void update(float deltaTime) {
		Thread[] threads = new Thread[entities.size()];
		for (int i = 0; i < entities.size(); ++i) {
			Entity e = this.entities.get(i);
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
		
	}
	

}
