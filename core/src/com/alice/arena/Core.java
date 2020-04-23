package com.alice.arena;

import com.alice.arena.data.Registry;
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
	public static int WIDTH = 640, HEIGHT = 480;
	
	@Override
	public void create () {
		instance = this;
		account = new Account("Player");
		Assets.fonts.put("empty", new BitmapFont());
		System.out.println(Registry.RACES.Human);
		System.out.println(Registry.STYLES.Warrior);
		setScreen(new SelectScreenDemo());
	}

	@Override
	public void render () {
		deltaTime = Gdx.graphics.getDeltaTime();
		Gdx.gl.glClearColor(backgroundColor.r, backgroundColor.g, backgroundColor.b, backgroundColor.a);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		super.render();
		
	}
	
	@Override
	public void resize(int width, int height) {
		WIDTH = width;
		HEIGHT = height;
		super.resize(width, height);
	}
	
	@Override
	public void dispose () {
		Assets.Dispose();
		super.dispose();
	}
	


	
}
