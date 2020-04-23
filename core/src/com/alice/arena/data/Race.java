package com.alice.arena.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import com.alice.arena.components.AIComponent;
import com.alice.arena.components.CharactherComponent;
import com.alice.arena.components.PositionComponent;
import com.alice.arena.components.VelocityComponent;
import com.alice.arena.utils.TextureHolder;
import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Race {
	
	public int id;
	public TextureHolder texture;
	public String name;
	
	public float baseHealth;
	public float baseEnergy;
	public float baseSpeed;	
	public float baseStrength;
	public float baseArmor;
	public float baseVisibility;
	public float baseVision;
	public float baseHealthRegen;
	public float baseEnergyRegen;
	
	public boolean show = true;
	public int skillLimit = 3;
	
	
	
	
	public int width;
	public int height;
	
	
	
	
	public HashSet<Skill> raceSkills = new HashSet<Skill>();
	
	
	
	public Race(int id, String name, TextureHolder texture, int width, int height, float baseHealth,
			float baseEnergy, float baseSpeed,  float baseStrength, float baseArmor, float baseVisibility,
			float baseVision, float baseHealthRegen, float baseEnergyRegen,
			Skill... skills){
		
		this.id = id;
		this.name = name;
		this.texture = texture;
		this.width = width;
		this.height = height;
		
		
		
		this.baseHealth = baseHealth;
		this.baseEnergy = baseEnergy;
		this.baseSpeed = baseSpeed;
		this.baseStrength = baseStrength;
		this.baseArmor = baseArmor;
		this.baseVisibility = baseVisibility;
		this.baseVision = baseVision;
		this.baseHealthRegen = baseHealthRegen;
		this.baseEnergyRegen = baseEnergyRegen;
		
		for(Skill skill : skills) {
			raceSkills.add(skill);
		}
		
		Registry.raceList.put(id, this);
		
	}
	
	
	public abstract void RacialInit(CharactherComponent cc);
	public abstract void RacialUpdate(CharactherComponent cc, float delta, PositionComponent pc, VelocityComponent vc);
	public abstract void RacialAIUpdate(CharactherComponent cc, AIComponent aic, float delta, PositionComponent pc, VelocityComponent vc);
	
	
}
