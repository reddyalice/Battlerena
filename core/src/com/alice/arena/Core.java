package com.alice.arena;

import com.alice.arena.components.CharactherComponent;
import com.alice.arena.data.Race;
import com.alice.arena.data.Skill;
import com.alice.arena.data.Style;
import com.alice.arena.screens.PlayScreen;
import com.alice.arena.utils.Assets;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;


public class Core extends Game {

	public static Color backgroundColor = Color.WHITE;
	public static Core instance;
	
	@Override
	public void create () {
		instance = this;
		setScreen(new PlayScreen());
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(backgroundColor.r, backgroundColor.g, backgroundColor.b, backgroundColor.a);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		super.render();
	}
	
	@Override
	public void dispose () {
		Assets.Dispose();
		super.dispose();
	}
	
	public static Entity CreateCharactherEntity(Engine engine, Race race, Style style,Skill[] skills ) {
		
		Entity e = new Entity();
		CharactherComponent cc = new CharactherComponent();
		cc.race = race;
		cc.style = style;
		cc.skill = skills;
		cc.health = race.baseHealth + (race.baseHealth * style.healthPercentMul / 100f);
		
		
		
		
		
		return e;
		
		
		
		
	}
	
	
}
