package com.alice.arena.utils;

import com.alice.arena.components.CharactherComponent;
import com.alice.arena.components.PhysicsComponent;
import com.alice.arena.data.Registry;
import com.alice.arena.screens.PlayScreen;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;

public class Actions {

	public static void KillCharacther(Engine e, Entity en, CharactherComponent cc, PhysicsComponent phc) {
		
		cc.coneLight.remove();
		cc.pointLight.remove();
		PlayScreen.world.destroyBody(phc.body);
		cc.var.clear();
		e.removeEntity(en);
		Registry.chars.remove(cc.id);
		
	}
	

}
