package com.alice.arena.ai;

import com.alice.arena.screens.PlayScreen;
import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.RayCastCallback;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class Direction implements Connection<Vector2> {
	
	private Vector2 origin;
	public Vector2 direction;
	private Vector2 destinaion;
	private int cost;

	public Direction(Vector2 origin, Vector2 direction) {
		this.origin = origin;
		this.direction = direction;
		this.destinaion = new Vector2(origin.x + direction.x, origin.y + direction.y);
		cost = 1;
		RayCastCallback callBack = new RayCastCallback() {
			
			@Override
			public float reportRayFixture(Fixture fixture, Vector2 point, Vector2 normal, float fraction) {
				String fA = (String) fixture.getUserData();
				if(fA.contentEquals("wall") || fA.contentEquals("pit"))
				{
					cost = 999;
					return 1;
				}
				return 0;
			}
		};
		PlayScreen.world.rayCast(callBack, origin, destinaion);
		
		
	}

	@Override
	public float getCost() {
		
		return cost;
	}

	@Override
	public Vector2 getFromNode() {
		// TODO Auto-generated method stub
		return origin;
	}

	@Override
	public Vector2 getToNode() {
		// TODO Auto-generated method stub
		return destinaion;
	}

}
