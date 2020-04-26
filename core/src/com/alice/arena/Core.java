package com.alice.arena;

import com.alice.arena.components.CharactherComponent;
import com.alice.arena.data.Registry;
import com.alice.arena.screens.SelectScreen;
import com.alice.arena.utils.Assets;
import com.alice.arena.utils.EvE;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.crashinvaders.vfx.VfxManager;
import com.crashinvaders.vfx.effects.BloomEffect;
import com.crashinvaders.vfx.effects.ChromaticAberrationEffect;
import com.crashinvaders.vfx.effects.OldTvEffect;
import com.crashinvaders.vfx.effects.VignetteEffect;

import box2dLight.RayHandler;



public class Core extends Game {

	public static Color backgroundColor = Color.BLUE;
	public static Core instance;
	public static Account account;
	
	public static Entity Player; 
	public static CharactherComponent playerChar;
	public static Vector2 playerSpawnPoint;
	
	public static float deltaTime = 1f/60f;
	public static int WIDTH = 1024, HEIGHT = 768;
	
	
	
	public static VfxManager VfxManager;
	
	public static ChromaticAberrationEffect chromeEffect;
	public static VignetteEffect vignetteEffect;
	public static OldTvEffect oldTVEffect;
	public static BloomEffect bloom;
	
	
	
	
	
	
	public static World world;
	public static RayHandler rayHandler;
	public static EvE<Contact> beginContantCalls = new EvE<Contact>();
	public static EvE<Contact> endContantCalls = new EvE<Contact>();
	
	@Override
	public void create () {
		instance = this;
		Assets.fonts.put("empty", new BitmapFont());
		Gdx.app.log("Init", "Loading " + Registry.RACES.Human);
		Gdx.app.log("Init", "Loading " + Registry.STYLES.Warrior);
		
		
		VfxManager = new VfxManager(Format.RGBA8888);
		chromeEffect = new ChromaticAberrationEffect();
		vignetteEffect = new VignetteEffect(false);
		chromeEffect.setMaxDistortion(0.2f);
		oldTVEffect = new OldTvEffect();
		bloom = new BloomEffect(Pixmap.Format.RGBA8888);
		
		
		
		
		world = new World(new Vector2(0,0), false);
		rayHandler = new RayHandler(world, Core.WIDTH / 8, Core.HEIGHT / 8);
		
		world.setContactListener(new ContactListener() {
			
			@Override
			public void preSolve(Contact contact, Manifold oldManifold) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void postSolve(Contact contact, ContactImpulse impulse) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void endContact(Contact contact) {
				endContantCalls.Broadcast(contact);
				
			}
			
			@Override
			public void beginContact(Contact contact) {
				beginContantCalls.Broadcast(contact);
				
			}
		});
		
		
		
		
		
		
		
		
		account = new Account("Player");
		
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
		VfxManager.resize(width, height);
		rayHandler.resizeFBO(width / 8, height / 8);
		super.resize(width, height);
	}
	
	
	@Override
	public void dispose () {
		VfxManager.dispose();
		chromeEffect.dispose();
		vignetteEffect.dispose();
		oldTVEffect.dispose();
		bloom.dispose();
		rayHandler.dispose();
		world.dispose();
		Assets.Dispose();
		super.dispose();
	}
	


	
}
