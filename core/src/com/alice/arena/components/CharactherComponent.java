package com.alice.arena.components;

import java.util.HashMap;

import com.alice.arena.data.Race;
import com.alice.arena.data.Skill;
import com.alice.arena.data.Style;
import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;

import box2dLight.ConeLight;
import box2dLight.Light;
import box2dLight.PointLight;

public class CharactherComponent implements Component {
	
	public Race race;
	public Style style;
	public Vector2 lookDir;
	public float rotation;
	public String name;
	public String team;
	public int id;
	
	
	public ConeLight coneLight;
	public PointLight pointLight;
	

	
	public boolean flip;
	public int raceAnimationStep;
	public int styleAnimationStep;
	public float raceTimeHolder;
	public float styleTimeHolder;
	public float idleTime = 3f;
	
	public float maxHealth;
	public float maxEnergy;
	public float noMoveVisibility;
	
	public float health;
	public float energy;
	
	public float speed;	
	public float strength;
	public float armor;
	public float visibility;
	public float vision;
	public float healthRegen;
	public float energyRegen;
	public HashMap<String, Object> var = new HashMap<String, Object>();
	
	
	public Skill[] skill;
	public float[] progress;


	
	
}
