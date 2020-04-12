package com.alice.arena.systems;

import java.util.ArrayList;

import com.alice.arena.components.PositionComponent;
import com.alice.arena.components.VelocityComponent;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.ashley.utils.ImmutableArray;

public class MovementSystem extends EntitySystem {

	private ComponentMapper<PositionComponent> pm = ComponentMapper.getFor(PositionComponent.class);
	private ComponentMapper<VelocityComponent> vm = ComponentMapper.getFor(VelocityComponent.class);
	private ImmutableArray<Entity> entities;
	
	public MovementSystem() {
		super();
	}
	
	
	@Override
	public void addedToEngine(Engine engine) {
		entities = engine.getEntitiesFor(Family.all(PositionComponent.class, VelocityComponent.class).get());
	}

	protected void processEntity(Entity entity, float deltaTime) {
		
		PositionComponent pc = pm.get(entity);
		VelocityComponent vc = vm.get(entity);
		vc.speed2 = (float) (Math.pow(vc.x, 2) + Math.pow(vc.y, 2));
		
		
		pc.x += vc.x * 200f;
		pc.y += vc.y * 200f;
		

	}
	
	
	@Override
	public void update(float deltaTime) {
		ArrayList<Thread> threads = new ArrayList<Thread>();
		for (int i = 0; i < entities.size(); ++i) {
			Entity e = this.entities.get(i);
			Thread t = new Thread(new Runnable() {
				
				@Override
				public void run() {
					processEntity(e, deltaTime);
					
				}
			});
			threads.add(t);
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
