package com.alice.arena.data;

import com.alice.arena.components.CharactherComponent;
import com.alice.arena.components.PositionComponent;
import com.alice.arena.components.VelocityComponent;
import com.alice.arena.utils.TextureHolder;
import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public abstract class Skill {
	
	public String name;
	public TextureHolder texture;
	public Texture UITexture;
	
	
	public int level;
	public float cooldown;
	public String description;
	
	
	public Skill(String name, TextureHolder texture,  int level, float cooldown, String description) {
		this.name = name;
		this.texture = texture;
		this.level = level;
		this.cooldown = cooldown;
		this.description = description;
	}
	
	
	
	public abstract void SkillInit(CharactherComponent cc);
	public abstract void SkillUpdate(CharactherComponent cc,Engine en, float delta, PositionComponent pc,VelocityComponent vc,int index);
	public abstract void SkillRender(SpriteBatch batch, ShapeRenderer shapeRebderer, CharactherComponent cc, PositionComponent pc, int index);
	public abstract void ActiveCall(CharactherComponent cc, PositionComponent pc, int index);
	
	
	
	

}
