package com.alice.arena.data;

import java.util.HashMap;
import java.util.HashSet;

import com.alice.arena.components.CharactherComponent;
import com.alice.arena.components.PositionComponent;
import com.alice.arena.components.VelocityComponent;
import com.alice.arena.utils.TextureHolder;
import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.graphics.Texture;

public abstract class Style {

	public int id;
	public String name;
	public TextureHolder texture;
	public int width;
	public int height;
	

	public int healthPercentMul;
	public int energyPercentMul;
	public int speedPercentMul;	
	public int strengthPercentMul;
	public int armorPercentMul;
	public int visibilityPercentMul;
	public int visionPercentMul;
	public int healthRegenPercentMul;
	public int energyRegenPercentMul;
	
	
	public HashSet<Skill> styleSkills = new HashSet<Skill>();

	public Style(int id, String name, TextureHolder texture,int width, int height,
			int healthPercentMul, int energyPercentMul, int speedPercentMul, int strengthPercentMul, int armorPercentMul,
			int visibilityPercentMul, int visionPercentMul, int healthRegenPercentMul, int energyRegenPercentMul,
			Skill... skills ) {
		
		this.id = id;
		this.name = name;
		this.texture = texture;
		this.width = width;
		this.height = height;
		
		this.healthPercentMul = healthPercentMul;
		this.energyPercentMul = energyPercentMul;
		this.speedPercentMul = speedPercentMul;
		this.strengthPercentMul = strengthPercentMul;
		this.armorPercentMul = armorPercentMul;
		this.visibilityPercentMul = visibilityPercentMul;
		this.visionPercentMul = visionPercentMul;
		this.healthPercentMul = healthPercentMul;
		this.energyPercentMul = energyPercentMul;
		
		
		for(Skill skill : skills) {
			styleSkills.add(skill);
		}
		
		Registry.styleList.put(id, this);

	}
	
	
	
	
	public abstract void StyleInit(CharactherComponent cc);
	public abstract void StyleUpdate(CharactherComponent cc, float deltaTime, PositionComponent pc, VelocityComponent vc);
	
	
	
}
