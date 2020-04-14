package com.alice.arena;

import java.util.ArrayList;

import com.alice.arena.components.AIComponent;
import com.alice.arena.components.CharactherComponent;
import com.alice.arena.components.MPComponent;
import com.alice.arena.components.PhysicsComponent;
import com.alice.arena.components.PlayerComponent;
import com.alice.arena.components.PositionComponent;
import com.alice.arena.components.VelocityComponent;
import com.alice.arena.data.Race;
import com.alice.arena.data.Registry;
import com.alice.arena.data.Skill;
import com.alice.arena.data.Style;
import com.alice.arena.screens.PlayScreen;
import com.alice.arena.screens.SelectScreen;
import com.alice.arena.utils.Assets;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

import box2dLight.ConeLight;
import box2dLight.PointLight;
import box2dLight.RayHandler;


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
