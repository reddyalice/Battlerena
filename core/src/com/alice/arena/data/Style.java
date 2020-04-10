package com.alice.arena.data;

import java.util.HashMap;
import java.util.HashSet;

import com.alice.arena.components.CharactherComponent;
import com.alice.arena.components.PositionComponent;
import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.graphics.Texture;

public abstract class Style {

	public int id;
	public String name;
	public Texture styleTexture;

	public int healthPercentMul;
	public int energyPercentMul;
	public int speedPercentMul;	
	public int strengthPercentMul;
	public int armorPercentMul;
	public int visibilityPercentMul;
	public int healthRegenPercentMul;
	public int energyRegenPercentMul;
	
	public HashSet<Skill> styleSkills = new HashSet<Skill>();

	public Style(int id, String name, Texture texture,
			int healthPercentMul, int energyPercentMul, int speedPercentMul, int strengthPercentMul, int armorPercentMul,
			int visibilityPercentMul, int healthRegenPercentMul, int energyRegenPercentMul,
			Skill... skills ) {
		
		this.id = id;
		this.name = name;
		this.styleTexture = texture;
		this.healthPercentMul = healthPercentMul;
		this.energyPercentMul = energyPercentMul;
		this.speedPercentMul = speedPercentMul;
		this.strengthPercentMul = strengthPercentMul;
		this.armorPercentMul = armorPercentMul;
		this.visibilityPercentMul = visibilityPercentMul;
		this.healthPercentMul = healthPercentMul;
		this.energyPercentMul = energyPercentMul;
		
		
		for(Skill skill : skills) {
			styleSkills.add(skill);
		}
		
		Registry.styleList.put(id, this);

	}
	
	
	
	
	public abstract void StyleInit(CharactherComponent cc);
	public abstract void StyleUpdate(CharactherComponent cc,Engine en, float deltaTime, PositionComponent pc);
	
	
	
}
