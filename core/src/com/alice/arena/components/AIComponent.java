package com.alice.arena.components;

import com.alice.arena.utils.AIState;
import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public class AIComponent implements Component {
	public AIState state = AIState.LookAround;
	public Entity target;
	public Vector2 tempPos;
}
