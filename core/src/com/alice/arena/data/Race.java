package com.alice.arena.data;

import java.util.HashMap;

import com.alice.arena.components.CharactherComponent;
import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Race {
	
	public int id;
	public Texture racialTexture;
	
	
	public int baseHealth;
	public int baseEnergy;
	public int baseSpeed;	
	public int baseStrength;
	public int baseArmor;
	
	
	
	public HashMap<Skill, Integer> raceSkills;
	
	
	public abstract void RacialInit(CharactherComponent cc);
	public abstract void RacialUpdate(CharactherComponent cc,Engine en, float delta);
	
	
}
