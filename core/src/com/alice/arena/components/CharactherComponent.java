package com.alice.arena.components;

import com.alice.arena.data.Race;
import com.alice.arena.data.Skill;
import com.alice.arena.data.Style;
import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

public class CharactherComponent implements Component {
	
	public Race race;
	public Style style;
	public Vector2 lookDir;
	
	public float maxHealth;
	public float maxEnergy;
	
	public float health;
	public float energy;
	
	public float speed;	
	public float strength;
	public float armor;
	
	public Skill[] skill = new Skill[4];
	public float[] progress = new float[4];

	
}
