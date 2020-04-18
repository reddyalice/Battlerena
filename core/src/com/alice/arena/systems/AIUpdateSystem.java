package com.alice.arena.systems;

import java.util.ArrayList;

import com.alice.arena.ai.AIState;
import com.alice.arena.ai.AnyHeuristic;
import com.alice.arena.ai.Direction;
import com.alice.arena.ai.InGraph;
import com.alice.arena.components.AIComponent;
import com.alice.arena.components.CharactherComponent;
import com.alice.arena.components.PhysicsComponent;
import com.alice.arena.components.PositionComponent;
import com.alice.arena.components.VelocityComponent;
import com.alice.arena.data.Registry;
import com.alice.arena.data.Skill;
import com.alice.arena.screens.PlayScreen;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.RayCastCallback;
import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.ai.pfa.GraphPath;
import com.badlogic.gdx.ai.pfa.Heuristic;
import com.badlogic.gdx.ai.pfa.indexed.IndexedAStarPathFinder;
import com.badlogic.gdx.ai.pfa.indexed.IndexedGraph;
import com.badlogic.gdx.ai.pfa.indexed.IndexedHierarchicalGraph;
import com.badlogic.gdx.ai.steer.behaviors.RaycastObstacleAvoidance;
import com.badlogic.gdx.ai.utils.Collision;
import com.badlogic.gdx.ai.utils.Location;
import com.badlogic.gdx.ai.utils.Ray;
import com.badlogic.gdx.ai.utils.RaycastCollisionDetector;
import com.badlogic.gdx.math.Vector2;

public class AIUpdateSystem extends EntitySystem {

	private Engine engine;
	private ImmutableArray<Entity> entities;

	@Override
	public void addedToEngine(Engine engine) {
		this.engine = engine;
		this.entities = engine.getEntitiesFor(Family.all(AIComponent.class).get());
	}
	
	

	InGraph graph = new InGraph();
	AnyHeuristic heuristic = new AnyHeuristic(graph);
	
	private void processEntity(Entity entity, float deltaTime) {
		PositionComponent pc =  entity.getComponent(PositionComponent.class);
		CharactherComponent cc = entity.getComponent(CharactherComponent.class);
		VelocityComponent vc =  entity.getComponent(VelocityComponent.class);
		PhysicsComponent phc = entity.getComponent(PhysicsComponent.class);
		AIComponent aic = entity.getComponent(AIComponent.class);
		

		if(aic.state == AIState.LookAround) {
			Vector2 ls = new Vector2(cc.lookDir);
			ls.rotate(-90f);
			ArrayList<Entity> seen = new ArrayList<Entity>();
			RayCastCallback callback = new RayCastCallback() {

					@Override
			public float reportRayFixture(Fixture fixture, Vector2 point, Vector2 normal, float fraction) {
					String f = (String)fixture.getUserData();
					if(f.startsWith("char")) {
						String[] p = f.split("/");
						String team = p[1];
						int id = Integer.parseInt(p[2]);
						if(!team.contentEquals(cc.team)) {
							seen.add(Registry.chars.get(id));
							return 0;
						}
					}

					return 1;
				}
			};

			for(int i = 0; i < 30; i++) {
				PlayScreen.world.rayCast(callback, pc.x + cc.race.width / 2f , pc.y + cc.race.height / 2f, pc.x  + cc.race.width / 2f + ls.x * 500f,  pc.y + cc.race.height / 2f + ls.y * 500f);
				ls.rotate(6f);
			}
			
			
			Entity closest = null;
				for(Entity e : seen) {
					if(closest == null) {
						closest = e;
						}else {
						PositionComponent epc = e.getComponent(PositionComponent.class);
						PositionComponent lpc = closest.getComponent(PositionComponent.class);
						Vector2 ldiff = new Vector2(lpc.x - pc.x, lpc.y - pc.y);
						Vector2 diff = new Vector2(epc.x - pc.x, epc.y - pc.y);
						if(diff.len() < ldiff.len())
							closest = e;
						}
					}

					aic.target = closest;
					if(aic.target != null) {
							aic.state = AIState.Follow;
					}
			}
				
			if(aic.state == AIState.Follow) {
				if(aic.target != null) {
					
					CharactherComponent ecc = aic.target.getComponent(CharactherComponent.class);
					PhysicsComponent ephc = aic.target.getComponent(PhysicsComponent.class); 
					
					Location<Vector2> loc = new Location<Vector2>() {
						
						@Override
						public float vectorToAngle (Vector2 vector) {
							return (float)Math.atan2(-vector.x, vector.y);
						}
						
						@Override
						public void setOrientation(float orientation) {
							// TODO Auto-generated method stub
							
						}
						
						@Override
						public Location<Vector2> newLocation() {
							// TODO Auto-generated method stub
							return null;
						}
						
						@Override
						public Vector2 getPosition() {
							Vector2 a = new Vector2(ephc.body.getPosition());
							return (new Vector2(a).sub(cc.lookDir.x * 30f, cc.lookDir.y * 30f));
						}
						
						@Override
						public float getOrientation() {
							// TODO Auto-generated method stub
							return vectorToAngle(ecc.lookDir);
						}
						
						@Override
						public Vector2 angleToVector (Vector2 outVector, float angle) {
							outVector.x = -(float)Math.sin(angle);
							outVector.y = (float)Math.cos(angle);
							return outVector;
						}
					};
					if((new Vector2(aic.agent.getPosition()).sub( loc.getPosition() )).len() > 30)
						aic.agent.update(deltaTime, loc);
					else
					{
						vc.x = -cc.lookDir.y * 25f * cc.speed;
						vc.y = cc.lookDir.x * 25f * cc.speed;
						cc.lookDir = new Vector2(ephc.body.getPosition()).sub(  aic.agent.getPosition()).nor();
					}
				}else
					aic.state = AIState.LookAround;
				
			}
		
		
		cc.race.RacialAIUpdate(cc, aic, deltaTime, pc, vc);
		cc.style.StyleAIUpdate(cc, aic, deltaTime, pc, vc);
		Thread[] skillThreads = new Thread[cc.skill.length];
		int i = 0;
		for(Skill s : cc.skill) {
			int k = i;
			skillThreads[i] = new Thread(new Runnable() {
				@Override
				public void run() {
					s.SkillAIUpdate(cc, aic, engine, deltaTime, pc, vc, k);
					
				}
			});
			skillThreads[i].run();
			i++;
		}
		
		
		for(Thread t : skillThreads)
			try {
				t.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
		

		
	}
	
	@Override
	public void update(float deltaTime) {
		Thread[] threads = new Thread[entities.size()];
		for (int i = 0; i < entities.size(); ++i) {
			Entity e = entities.get(i);
			Thread t = new Thread(new Runnable() {
				
				@Override
				public void run() {
					processEntity(e, deltaTime);
					
				}
			});
			threads[i] = t;
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
