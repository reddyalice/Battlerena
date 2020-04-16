package com.alice.arena.components;

import java.util.ArrayList;

import com.alice.arena.utils.AIState;
import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public class AIComponent implements Component {
	public AIState state = AIState.LookAround;
	public Entity target;
	public ArrayList<Entity> seen = new ArrayList<Entity>();
	public Vector2 tempPos;
	public Vector2 homePos;
	public float memoryTimer = 0;
}
