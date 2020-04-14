package com.alice.arena.utils;

import com.alice.arena.components.CharactherComponent;
import com.alice.arena.components.PhysicsComponent;
import com.alice.arena.data.Registry;
import com.alice.arena.screens.PlayScreen;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.physics.box2d.Body;

import box2dLight.Light;

public class Actions {

	public static void KillCharacther(Engine e, Entity en, CharactherComponent cc, PhysicsComponent phc) {
		
		//cc.coneLight.remove();
		cc.pointLight.remove();
		PlayScreen.world.destroyBody(phc.body);
		for(String key : cc.var.keySet()) {
			Object c = cc.var.get(key);
			if(c instanceof Body) {
				PlayScreen.world.destroyBody((Body)c);
			}else if(c instanceof Light)
			{
				((Light)c).remove();
			}
			
		}
		
		
		cc.var.clear();
		e.removeEntity(en);
		Registry.chars.remove(cc.id);
		
	}
	

}
