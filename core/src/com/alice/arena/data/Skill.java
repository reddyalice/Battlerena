package com.alice.arena.data;

import com.alice.arena.components.CharactherComponent;
import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Skill {
	
	public String name;
	public Texture texture;
	public Texture UITexture;
	
	
	public int level;
	public int cooldown;
	public String description;
	
	
	public Skill(String name, Texture texture,  int level, int cooldown, String description) {
		this.name = name;
		this.texture = texture;
		this.level = level;
		this.cooldown = cooldown;
		this.description = description;
	}
	
	
	
	public abstract void SkillInit(CharactherComponent cc);
	public abstract void SkillUpdate(CharactherComponent cc,Engine en, float delta);
	public abstract void SkillRender(SpriteBatch batch, CharactherComponent cc);
	
	
	
	
	

}
