package com.alice.arena.components;

import java.util.HashMap;

import com.alice.arena.data.Race;
import com.alice.arena.data.Skill;
import com.alice.arena.data.Style;
import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

public class CharactherComponent implements Component {
	
	public Race race;
	public Style style;
	public Vector2 lookDir;
	public float rotation;
		
	public float maxHealth;
	public float maxEnergy;
	
	public float health;
	public float energy;
	
	public float speed;	
	public float strength;
	public float armor;
	public float visibility;
	public float healthRegen;
	public float energyRegen;
	public HashMap<String, Object> var = new HashMap<String, Object>();
	
	
	public Skill[] skill;
	public float[] progress;

	
	
	
}
