package com.alice.arena.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;

public class PhysicsComponent implements Component {

	
	public Body body;
	public Fixture fixture;
	public Vector2 pivot;

}
