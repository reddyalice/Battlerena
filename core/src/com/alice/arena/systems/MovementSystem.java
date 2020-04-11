package com.alice.arena.systems;

import com.alice.arena.components.PositionComponent;
import com.alice.arena.components.VelocityComponent;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

public class MovementSystem extends IteratingSystem {

	private ComponentMapper<PositionComponent> pm = ComponentMapper.getFor(PositionComponent.class);
	private ComponentMapper<VelocityComponent> vm = ComponentMapper.getFor(VelocityComponent.class);
	
	
	public MovementSystem() {
		super(Family.all(PositionComponent.class, VelocityComponent.class).get());
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		
		PositionComponent pc = pm.get(entity);
		VelocityComponent vc = vm.get(entity);
		vc.speed2 = (float) (Math.pow(vc.x, 2) + Math.pow(vc.y, 2));
		
		
		pc.x += vc.x * 100f;
		pc.y += vc.y * 100f;
		

	}

}
