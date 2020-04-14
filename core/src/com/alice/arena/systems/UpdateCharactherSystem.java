package com.alice.arena.systems;

import java.util.ArrayList;

import com.alice.arena.Core;
import com.alice.arena.components.CharactherComponent;
import com.alice.arena.components.PhysicsComponent;
import com.alice.arena.components.PositionComponent;
import com.alice.arena.components.VelocityComponent;
import com.alice.arena.data.Skill;
import com.alice.arena.screens.PlayScreen;
import com.alice.arena.utils.Actions;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.ashley.utils.ImmutableArray;

public class UpdateCharactherSystem extends EntitySystem {




	private ComponentMapper<PositionComponent> pm = ComponentMapper.getFor(PositionComponent.class);
	private ComponentMapper<VelocityComponent> vm = ComponentMapper.getFor(VelocityComponent.class);
	private ComponentMapper<PhysicsComponent> phm = ComponentMapper.getFor(PhysicsComponent.class);
	private ComponentMapper<CharactherComponent> cm = ComponentMapper.getFor(CharactherComponent.class);
	private Engine engine;
	private ImmutableArray<Entity> entities;
	
	
	@Override
	public void addedToEngine(Engine engine) {
		this.engine = engine;
		entities = engine.getEntitiesFor(Family.all(VelocityComponent.class, PositionComponent.class, PhysicsComponent.class, CharactherComponent.class).get());
	}
	
	
	protected void processEntity(Entity entity, float deltaTime) {
		PositionComponent pc = pm.get(entity);
		CharactherComponent cc = cm.get(entity);
		VelocityComponent vc = vm.get(entity);
		PhysicsComponent phc = phm.get(entity);
		cc.lookDir.nor();
		float vis = (cc.visibility + PlayScreen.playerChar.vision) / 2f;
		float dot = -cc.lookDir.x;
		float det = -cc.lookDir.y;
		cc.rotation = (float) Math.atan2(det, dot);
		cc.rotation *= 180f / (float)(Math.PI);
		
		
		if(vc.speed2 != 0) {
			cc.visibility = cc.noMoveVisibility * 125f / 100f;
			if(cc.idleTime > 0)
				cc.idleTime -= deltaTime;
			if(cc.idleTime < 0)
				cc.idleTime = 0;
		}
		else
		{
			cc.visibility = cc.noMoveVisibility;
			cc.idleTime = 3f;
		}
		
		cc.coneLight.setPosition(pc.x  + cc.race.width / 2f +  30f * cc.lookDir.x, pc.y + cc.race.height / 2f - 10f  + 30f * cc.lookDir.y);
		cc.coneLight.setDirection(180f + cc.rotation);
		//cc.pointLight.setPosition(pc.x + cc.race.width / 2f, pc.y +  cc.race.height / 2f);
		//cc.pointLight.setDistance(vis >= 0.5f ? 70 * vis : 0);
		cc.race.RacialUpdate(cc, deltaTime, pc, vc);
		cc.style.StyleUpdate(cc, deltaTime, pc, vc);

		
		if(cc.energy < cc.maxEnergy) {
			cc.energy += cc.energyRegen * deltaTime;
		}
		
		
		
		if(cc.energy > cc.maxEnergy)
			cc.energy = cc.maxEnergy;
		
		if(cc.energy < 0)
			cc.energy = 0;
		
		
		
		if(cc.health < cc.maxHealth) {
			cc.health += cc.healthRegen * deltaTime;
		}
		
		if(cc.health > cc.maxHealth)
			cc.health = cc.maxHealth;
		
		

		
		int i = 0;
		for(Skill s : cc.skill) {
			s.SkillUpdate(cc, engine, deltaTime, pc, vc, i);
			i++;
		}
		
		if(cc.health < 0) {
			cc.health = 0;
			Actions.KillCharacther(this.engine, entity, cc, phc);
		}
		
		
	}
	
	
	@Override
	public void update(float deltaTime) {
		ArrayList<Thread> threads = new ArrayList<Thread>();
		for (int i = 0; i < entities.size(); ++i) {
			Entity e = entities.get(i);
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
				e.printStackTrace();
			}
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
