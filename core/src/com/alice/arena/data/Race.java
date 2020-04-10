package com.alice.arena.data;

import java.util.HashMap;
import java.util.HashSet;

import com.alice.arena.components.CharactherComponent;
import com.alice.arena.components.PositionComponent;
import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Race {
	
	public int id;
	public Texture racialTexture;
	public String name;
	
	public float baseHealth;
	public float baseEnergy;
	public float baseSpeed;	
	public float baseStrength;
	public float baseArmor;
	public float baseVisibility;
	public float baseHealthRegen;
	public float baseEnergyRegen;

	
	
	
	
	
	public HashSet<Skill> raceSkills = new HashSet<Skill>();
	
	
	
	public Race(int id, String name, Texture texture, float baseHealth,
			float baseEnergy, float baseSpeed,  float baseStrength, float baseArmor, float baseVisibility,
			float baseHealthRegen, float baseEnergyRegen,
			Skill... skills){
		
		this.id = id;
		this.name = name;
		this.racialTexture = texture;
		this.baseHealth = baseHealth;
		this.baseEnergy = baseEnergy;
		this.baseSpeed = baseSpeed;
		this.baseStrength = baseStrength;
		this.baseArmor = baseArmor;
		this.baseVisibility = baseVisibility;
		this.baseHealthRegen = baseHealthRegen;
		this.baseEnergyRegen = baseEnergyRegen;
		
		for(Skill skill : skills) {
			raceSkills.add(skill);
		}
		
		Registry.raceList.put(id, this);
		
	}
	
	
	public abstract void RacialInit(CharactherComponent cc);
	public abstract void RacialUpdate(CharactherComponent cc,Engine en, float delta, PositionComponent pc);
	
	
}
