package com.alice.arena.systems;

import com.alice.arena.components.CharactherComponent;
import com.alice.arena.components.PositionComponent;
import com.alice.arena.components.VelocityComponent;
import com.alice.arena.data.Skill;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

public class UpdateCharactherSystem extends IteratingSystem {

	public UpdateCharactherSystem() {
		super(Family.all(VelocityComponent.class, PositionComponent.class, CharactherComponent.class).get());
		// TODO Auto-generated constructor stub
	}



	private ComponentMapper<PositionComponent> pm = ComponentMapper.getFor(PositionComponent.class);
	private ComponentMapper<VelocityComponent> vm = ComponentMapper.getFor(VelocityComponent.class);
	private ComponentMapper<CharactherComponent> cm = ComponentMapper.getFor(CharactherComponent.class);
	private Engine engine;
	
	@Override
	public void addedToEngine(Engine engine) {
		this.engine = engine;

		super.addedToEngine(engine);
	}
	
	
	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		PositionComponent pc = pm.get(entity);
		CharactherComponent cc = cm.get(entity);
		VelocityComponent vc = vm.get(entity);
		cc.lookDir.nor();
		
		float dot = -cc.lookDir.x;
		float det = -cc.lookDir.y;
		cc.rotation = (float) Math.atan2(det, dot);
		cc.rotation *= 180f / (float)(Math.PI);
		
		cc.coneLight.setPosition(pc.x  + cc.race.width / 2f +  30f * cc.lookDir.x, pc.y+ cc.race.height / 2f + 30f * cc.lookDir.y);
		cc.coneLight.setDirection(180f + cc.rotation);
		cc.pointLight.setPosition(pc.x + cc.race.width / 2f, pc.y +  cc.race.height / 2f);
		cc.pointLight.setDistance(70 * cc.visibility);
		cc.race.RacialUpdate(cc, deltaTime, pc, vc);
		cc.style.StyleUpdate(cc, deltaTime, pc, vc);
		
		int i = 0;
		for(Skill s : cc.skill) {
			s.SkillUpdate(cc, engine, deltaTime, pc, vc, i);
			i++;
		}
		
	}

}
