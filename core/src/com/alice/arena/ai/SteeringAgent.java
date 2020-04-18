package com.alice.arena.ai;

import com.alice.arena.components.CharactherComponent;
import com.alice.arena.components.PhysicsComponent;
import com.alice.arena.components.PositionComponent;
import com.alice.arena.components.VelocityComponent;
import com.alice.arena.screens.PlayScreen;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.ai.steer.SteerableAdapter;
import com.badlogic.gdx.ai.steer.SteeringAcceleration;
import com.badlogic.gdx.ai.steer.behaviors.Seek;
import com.badlogic.gdx.ai.steer.behaviors.BlendedSteering;
import com.badlogic.gdx.ai.steer.behaviors.CollisionAvoidance;
import com.badlogic.gdx.ai.steer.behaviors.RaycastObstacleAvoidance;
import com.badlogic.gdx.ai.steer.utils.RayConfiguration;
import com.badlogic.gdx.ai.steer.SteeringBehavior;
import com.badlogic.gdx.ai.utils.Collision;
import com.badlogic.gdx.ai.utils.Location;
import com.badlogic.gdx.ai.utils.Ray;
import com.badlogic.gdx.ai.utils.RaycastCollisionDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.RayCastCallback;

public class SteeringAgent extends SteerableAdapter<Vector2>{

		private  final SteeringAcceleration<Vector2> steeringOutput = 
			new SteeringAcceleration<Vector2>(new Vector2(0.5f,0.5f));
		VelocityComponent vc;
		PositionComponent pc;
		CharactherComponent cc;
		PhysicsComponent phc;
		
		
		Seek<Vector2> arrive;
		BlendedSteering<Vector2> blend;
	    RaycastObstacleAvoidance<Vector2> avoid;
		float orientation;
		
		
		
		
		
		
		Vector2 linearVelocity;
		float angularVelocity;
		float maxSpeed;
		boolean independentFacing;
		SteeringBehavior<Vector2> steeringBehavior;


		@Override
		public float vectorToAngle (Vector2 vector) {
			return (float)Math.atan2(-vector.x, vector.y);
		}
		
		
		
		private class RCallback implements RayCastCallback{
			public boolean collided = false;
			public Collision<Vector2> outputCollision;
			
			@Override
			public float reportRayFixture(Fixture fixture, Vector2 point, Vector2 normal, float fraction) {
				String fA = (String) fixture.getUserData();
				if(fA.contentEquals("wall") || fA.contentEquals("pit") || fA.startsWith("projectile"))
				{
					
					collided = true;
					outputCollision = new Collision<Vector2>(point, normal);
					return 0;
					
				}
				
				
				return 1;
			}
			
		}
		
		
		
		public SteeringAgent(Entity e) {
			this.phc  = e.getComponent(PhysicsComponent.class);
			this.cc = e.getComponent(CharactherComponent.class);
			this.pc  = e.getComponent(PositionComponent.class);
			this.vc = e.getComponent(VelocityComponent.class);
		
			arrive = new Seek<Vector2>(this);
			 avoid = new RaycastObstacleAvoidance<Vector2>(this, new RayConfiguration<Vector2>() {
			    	
			    	@Override
			    	public Ray<Vector2>[] updateRays() {
			    		
			    		Ray<Vector2>[] rays = new Ray[1];
			    		
			    		Vector2 ls = new Vector2(cc.lookDir);
						
							Vector2 start = new Vector2( pc.x + cc.race.width / 2f , pc.y + cc.race.height / 2f);
							Vector2 end = new Vector2(pc.x  + cc.race.width / 2f + ls.x * 30f,  pc.y + cc.race.height / 2f + ls.y * 30f);
							rays[0] = new Ray<Vector2>(start, end);
							

			    		
			    		return rays;
			    	}
			    	
				}, new RaycastCollisionDetector<Vector2>() {
					
					@Override
					public boolean collides(Ray<Vector2> ray) {
						RCallback callback = new RCallback();
						PlayScreen.world.rayCast(callback, ray.start, ray.end);
						return callback.collided;
					}
					
					
					@Override
					public boolean findCollision(Collision<Vector2> outputCollision, Ray<Vector2> inputRay) {
						
						
						
						
						RCallback callback = new RCallback();
						
						outputCollision = callback.outputCollision;
						PlayScreen.world.rayCast(callback, inputRay.start, inputRay.end);
						return callback.collided;
					}
					
					
				});
			 arrive.setEnabled(true);
			 avoid.setEnabled(true);
			 blend = new BlendedSteering<Vector2>(this);
			 blend.setEnabled(true);
			 blend.add(avoid, 2f);
			 blend.add(arrive, 1f);
			 
			
		}
		
		
		
		
		
		
		
		
		
		
		

		// Actual implementation depends on your coordinate system.
		// Here we assume the y-axis is pointing upwards.
		@Override
		public Vector2 angleToVector (Vector2 outVector, float angle) {
			outVector.x = -(float)Math.sin(angle);
			outVector.y = (float)Math.cos(angle);
			return outVector;
		}

		public void update (float delta, Location<Vector2> target) {
			boolean e = true;
			for(Ray<Vector2> r  : avoid.getRayConfiguration().updateRays())
				e &= avoid.getRaycastCollisionDetector().collides(r);
			
			

			arrive.setTarget(target);
			
			blend.calculateSteering(steeringOutput);
			applySteering(steeringOutput, delta);
			cc.lookDir = new Vector2(target.getPosition()).sub(  getPosition()).nor();
		}

		private void applySteering (SteeringAcceleration<Vector2> steering, float time) {
			// Update position and linear velocity. Velocity is trimmed to maximum speed
			//System.out.println(steering.linear);
			vc.x += steering.linear.x * time * 25f;
			vc.y += steering.linear.y * time * 25f;
			
			vc.x = Math.max(-getMaxLinearSpeed(), Math.min(getMaxLinearSpeed(), vc.x));
			vc.y = Math.max(-getMaxLinearSpeed(), Math.min(getMaxLinearSpeed(), vc.y));
		
			
			// Update orientation and angular velocity
		/*
				// For non-independent facing we have to align orientation to linear velocity
				float newOrientation = calculateOrientationFromLinearVelocity(this);
				if (newOrientation != this.orientation) {
					this.angularVelocity = (newOrientation - this.orientation) * time;
					this.orientation = newOrientation;
					
					cc.lookDir = angleToVector(cc.lookDir, this.orientation);
					
				}
			*/
			
		
			
		}
		
		public static float calculateOrientationFromLinearVelocity (Steerable<Vector2> character) {
			// If we haven't got any velocity, then we can do nothing.
			if (character.getLinearVelocity().isZero(character.getZeroLinearSpeedThreshold()))
				return character.getOrientation();

			return character.vectorToAngle(character.getLinearVelocity());
		}
		
		
		
		
		@Override
		public float getMaxLinearSpeed() {
			// TODO Auto-generated method stub
			return 25f * cc.speed;
		}
		
		@Override
		public Vector2 getLinearVelocity() {
		// TODO Auto-generated method stub
		return phc.body.getLinearVelocity();
		}
		
		@Override
		public float getAngularVelocity() {
		
		return angularVelocity;
		}
		
		public Vector2 getPosition() {
			return phc.body.getPosition();
		}
		
		
		@Override
		public float getMaxLinearAcceleration() {
		// TODO Auto-generated method stub
		return  25f * cc.speed;
		}
		
		
		
		
		
		
		
		@Override
		public float getMaxAngularSpeed() {
		// TODO Auto-generated method stub
		return (float)(2*Math.PI);
		}
		
		public void setAngularVelocity(float angularVelocity) {
			this.angularVelocity = angularVelocity;
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
}
