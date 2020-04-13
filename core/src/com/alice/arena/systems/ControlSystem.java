package com.alice.arena.systems;

import com.alice.arena.components.CharactherComponent;
import com.alice.arena.components.PlayerComponent;
import com.alice.arena.components.PositionComponent;
import com.alice.arena.components.VelocityComponent;
import com.alice.arena.screens.PlayScreen;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;

public class ControlSystem extends EntitySystem {

	private ImmutableArray<Entity> entities;
	private ComponentMapper<PositionComponent> pm = ComponentMapper.getFor(PositionComponent.class);
	private ComponentMapper<VelocityComponent> vm = ComponentMapper.getFor(VelocityComponent.class);
	private ComponentMapper<CharactherComponent> cm = ComponentMapper.getFor(CharactherComponent.class);
	private Viewport viewport;
	
	
	
	public ControlSystem(Viewport viewport) {
		super();
		this.viewport = viewport;
	}
	
	
	@Override
	public void addedToEngine(Engine engine) {
		super.addedToEngine(engine);
	}
	
	@Override
	public void update(float deltaTime) {
		
		Vector2 diff = viewport.unproject(new Vector2(Gdx.input.getX(), Gdx.input.getY()));
		Entity en = PlayScreen.Player;
		VelocityComponent vc = vm.get(en);
		CharactherComponent cc = cm.get(en);
		PositionComponent pc = pm.get(en);
		diff.sub(pc.x + 16f, pc.y + 16f);
		diff.nor();
		Vector2 m = new Vector2(0,0);
			
		if(Gdx.input.isKeyPressed(Input.Keys.W)) {
			m.y++;
		}
		if(Gdx.input.isKeyPressed(Input.Keys.A)) {
			m.x--;
		}
		if(Gdx.input.isKeyPressed(Input.Keys.S)) {
			m.y--;
		}
		if(Gdx.input.isKeyPressed(Input.Keys.D)) {
			m.x++;
		}
			
		if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT))
			if(cc.skill.length > 0)
			cc.skill[0].ActiveCall(cc, pc, 0);
		
		if(Gdx.input.isButtonJustPressed(Input.Buttons.RIGHT))
			if(cc.skill.length > 1)
			cc.skill[1].ActiveCall(cc, pc, 1);
		
		if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT))
			if(cc.skill.length > 2)
			cc.skill[2].ActiveCall(cc, pc, 2);
			
			
		if(Gdx.input.isButtonJustPressed(Input.Buttons.RIGHT) && Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT))
			if(cc.skill.length > 3)
			cc.skill[3].ActiveCall(cc, pc, 3);
		
			
			
			
			
		m.nor();
			
		vc.x = m.x * cc.speed * 25f;
		vc.y = m.y * cc.speed * 25f;
		viewport.getCamera().position.set(pc.x, pc.y, 0);
		cc.lookDir = diff;
			
			
		
		super.update(deltaTime);
	}
	
	
	
}
