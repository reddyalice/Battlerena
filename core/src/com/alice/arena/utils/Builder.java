package com.alice.arena.utils;

import java.util.ArrayList;

import javax.swing.text.Position;

import com.alice.arena.Core;
import com.alice.arena.ai.SteeringAgent;
import com.alice.arena.components.AIComponent;
import com.alice.arena.components.CharactherComponent;
import com.alice.arena.components.MPComponent;
import com.alice.arena.components.PhysicsComponent;
import com.alice.arena.components.PlayerComponent;
import com.alice.arena.components.PositionComponent;
import com.alice.arena.components.VelocityComponent;
import com.alice.arena.data.Race;
import com.alice.arena.data.Registry;
import com.alice.arena.data.Skill;
import com.alice.arena.data.Style;
import com.alice.arena.screens.PlayScreen;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.ai.steer.SteerableAdapter;
import com.badlogic.gdx.ai.steer.SteeringAcceleration;
import com.badlogic.gdx.ai.steer.SteeringBehavior;
import com.badlogic.gdx.ai.utils.Location;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

import box2dLight.ConeLight;
import box2dLight.PointLight;
import box2dLight.RayHandler;

public class Builder {

	private static Entity CreateCharactherEntity(RayHandler rh, float x, float y, Race race, Style style,String name, String team, Skill... skills ) {
		
		Entity e = new Entity();
		CharactherComponent cc = new CharactherComponent();
		int id = Registry.chars.size();

		
		
		cc.id = id;
		cc.race = race;
		cc.style = style;
		cc.skill = skills;
		cc.progress = new float[cc.skill.length];
		cc.maxHealth = race.baseHealth + (race.baseHealth * style.healthPercentMul / 100f);
		cc.maxEnergy = race.baseEnergy + (race.baseEnergy * style.energyPercentMul / 100f);
		cc.health = cc.maxHealth;
		cc.energy = cc.maxEnergy;
		cc.speed = race.baseSpeed + (race.baseSpeed * style.speedPercentMul / 100f);
		cc.strength = race.baseStrength + (race.baseStrength * style.strengthPercentMul / 100f);
		cc.armor = race.baseArmor + (race.baseArmor * style.armorPercentMul / 100f);
		cc.noMoveVisibility = race.baseVisibility + (race.baseVisibility * style.visibilityPercentMul / 100f);
		cc.visibility = cc.noMoveVisibility;
		cc.vision = race.baseVision + (race.baseVision * style.visionPercentMul / 100f);
		cc.healthRegen = race.baseHealthRegen + (race.baseHealthRegen * style.healthRegenPercentMul / 100f);
		cc.energyRegen = race.baseEnergyRegen + (race.baseEnergyRegen * style.energyRegenPercentMul / 100f);
		
		cc.lookDir = new Vector2(1,0);
		cc.rotation = 0;
		Color c = new Color(Color.WHITE);
		c.mul(0.5f);
		Color b = new Color(Color.WHITE);
		b.mul(0.75f);
		//cc.coneLight = CreateSpotLight(rh, x, y, c, 250, 1000,cc.rotation, 40f);
		cc.pointLight = CreatePointLight(rh, x, y, b, 50f, 100);
		cc.pointLight.setSoft(true);
		cc.name = name;
		cc.team = team;
		
		PhysicsComponent phc = new PhysicsComponent();
		
		phc.pivot = new Vector2(cc.race.width / 2f, cc.race.height / 2.75f);
		phc.body = CreateASimpleBody(BodyType.DynamicBody, x, y, cc.race.width / 2, cc.race.height / 2.5f, phc.pivot.x,  phc.pivot.y, "char/" + cc.team + "/" + id, false);
		phc.fixture = phc.body.getFixtureList().first();
		cc.pointLight.attachToBody(phc.body);
		
		
		
		e.add(cc);
		e.add(new PositionComponent(x,y));
		e.add(new VelocityComponent());
		e.add(phc);
		cc.race.RacialInit(cc);
		cc.style.StyleInit(cc);
		for(Skill s : cc.skill)
			s.SkillInit(cc);
		
		Registry.chars.put(id, e);
		return e;
		
		
		
	}
	
	
	public static Entity SpawnPlayerCharacther(RayHandler rh, float x, float y, Race race, Style style, String team, Skill... skills ) {
		Entity e = CreateCharactherEntity(rh, x, y, race, style, Core.account.name, team,  skills);
		e.add(new PlayerComponent());
		return e;
	}
	
	
	public static Entity SpawnAICharacther(RayHandler rh, float x, float y, Race race, Style style,  String team, Skill... skills ) {
		Entity e = CreateCharactherEntity(rh, x,y, race, style, "AI", team, skills);
		AIComponent aic = new AIComponent();
	
		
		aic.agent = new SteeringAgent(e);
		
		e.add(aic);
		return e;
	}
	
	
	public static Entity SpawnMPCharacther(RayHandler rh, float x, float y, Race race, Style style, String name, String team, Skill... skills )
	{
		Entity e = CreateCharactherEntity(rh, x, y, race, style,name, team, skills);
		e.add(new MPComponent());
		return e;
	}
	
	public static Entity SpawnRandomAICharacther(RayHandler rh, float x, float y, int level, String team) {
		int r = (int)(Math.random() * Registry.raceList.size());
		int c = (int)(Math.random() * Registry.styleList.size());
		ArrayList<Skill> rcs = new ArrayList<Skill>();
		ArrayList<Skill> scs = new ArrayList<Skill>();
		Race ra = Registry.raceList.values().toArray(new Race[0])[r];
		Style sa = Registry.styleList.values().toArray(new Style[0])[c];
		ArrayList<Skill> skills =  new ArrayList<Skill>();
		
		
		
		for(Skill skill : ra.raceSkills)
		{
			if(skill.level <= level)
				rcs.add(skill);
		}
		
		for(Skill skill : sa.styleSkills)
		{
			if(skill.level <= level)
				scs.add(skill);
		}
		
		for(int i = rcs.size() - 1; i > rcs.size() - 3; i--)
		{
			if(i < 0) break;
			skills.add(rcs.get(i));
		}
		

		for(int i = scs.size() - 1; i > scs.size() - 3; i--)
		{
			if(i < 0) break;
			skills.add(scs.get(i));
		}
		
		return SpawnAICharacther(rh, x, y, ra, sa, team, skills.toArray(new Skill[0]));
		

	}
	
	public static PointLight CreatePointLight(RayHandler rayHandler, float x, float y, Color lightColor, float range, int numberOfRays) {
		 
		 PointLight light = new PointLight(rayHandler, numberOfRays, lightColor, range, x, y);
		 return light;
	 }
	 
	 public static  ConeLight CreateSpotLight(RayHandler rayHandler, float x, float y, Color lightColor, float range, int numberOfRays, float directionDegree, float coneDegree) {
		 
		 ConeLight light = new ConeLight(rayHandler, numberOfRays, lightColor, range, x, y, directionDegree, coneDegree);
		 return light;
	 }
	
	public static Body CreateASimpleBody(BodyType type, float x, float y,float width, float height, float pivotX, float pivotY, String userData, boolean trigger) {
		
		BodyDef def = new BodyDef();
		PolygonShape shape = new PolygonShape();
		FixtureDef fdef = new FixtureDef();
		shape.setAsBox(width/2f, height/2f);
		def.type = type;
		def.position.set(x  + pivotX, y +pivotY );
		fdef.shape = shape;
		Body body = PlayScreen.world.createBody(def);
		Fixture fixture =body.createFixture(fdef);
		fixture.setUserData(userData);
		fixture.setSensor(trigger);
		return body;
	}
	

}
