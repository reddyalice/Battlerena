package com.alice.arena.data;

import com.alice.arena.components.CharactherComponent;
import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Style {

	public int id;
	public Texture styleTexture;

	public int healthPercentMul;
	public int energyPercentMul;
	public int speedPercentMul;	
	public int strengthPercentMul;
	public int armorPercentMul;
	
	
	
	
	public abstract void StyleInit(CharactherComponent cc);
	public abstract void StyleUpdate(CharactherComponent cc,Engine en, float deltaTime);
	
	
	
}
