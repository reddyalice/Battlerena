package com.alice.arena.data;

import com.alice.arena.components.CharactherComponent;
import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Skill {
	
	public int id;
	public int level;
	public String name;
	public int cooldown;
	public String description;
	
	public abstract void SkillInit(CharactherComponent cc);
	public abstract void SkillUpdate(CharactherComponent cc,Engine en, float delta);
	public abstract void SkillRender(SpriteBatch batch, CharactherComponent cc);
	
	
	
	
	

}
