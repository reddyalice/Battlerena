package com.alice.arena;

import com.alice.arena.data.Registry;
import com.alice.arena.screens.SelectScreen;
import com.alice.arena.screens.SelectScreenDemo;
import com.alice.arena.utils.Assets;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;



public class Core extends Game {

	public static Color backgroundColor = Color.BLUE;
	public static Core instance;
	public static Account account;
	public static float deltaTime = 1f/60f;
	public static int WIDTH = 1024, HEIGHT = 768;
	
	@Override
	public void create () {
		instance = this;
		account = new Account("Player");
		Assets.fonts.put("empty", new BitmapFont());
		Gdx.app.log("Init", "Loading " + Registry.RACES.Human);
		Gdx.app.log("Init", "Loading " + Registry.STYLES.Warrior);
		setScreen(new SelectScreen());
	}

	@Override
	public void render () {
		deltaTime = Gdx.graphics.getDeltaTime();
		Gdx.gl.glClearColor(backgroundColor.r, backgroundColor.g, backgroundColor.b, backgroundColor.a);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		super.render();
		
	}
	
	
	@Override
	public void dispose () {
		Assets.Dispose();
		super.dispose();
	}
	


	
}
