package com.alice.arena.screens;

import com.alice.arena.Core;
import com.alice.arena.components.CharactherComponent;
import com.alice.arena.data.Race;
import com.alice.arena.data.Skill;
import com.alice.arena.data.Style;
import com.alice.arena.systems.ControlSystem;
import com.alice.arena.systems.MovementSystem;
import com.alice.arena.systems.RenderingSystem;
import com.alice.arena.systems.UpdateCharactherSystem;
import com.alice.arena.utils.Assets;
import com.alice.arena.utils.EvE;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import box2dLight.RayHandler;



public class PlayScreen implements Screen {

	private OrthographicCamera camera;
	private OrthographicCamera UICamera;
	private ExtendViewport viewport;
	private ExtendViewport UIViewport;
	private Engine engine;
	public static Entity Player; 
	public static CharactherComponent playerChar;
	public static EvE<SpriteBatch> UIDraws = new EvE<SpriteBatch>();
	
	
	
	private SpriteBatch batch;
	private SpriteBatch UIBatch;
	
	
	
	public static World world;
	public static RayHandler rayHandler;
	
	public PlayScreen(Race selectedR, Style selectedS, Skill... selectedSS) {
	
		camera = new OrthographicCamera();
		viewport = new ExtendViewport(640, 480, camera);
		viewport.apply(true);
		UICamera = new OrthographicCamera();
		UIViewport = new ExtendViewport(640, 480, UICamera);
		UIViewport.apply(true);
		
		batch = new SpriteBatch();
		UIBatch = new SpriteBatch();
		world = new World(new Vector2(0,0), false);
		rayHandler = new RayHandler(world, 640 / 8, 480 / 8);
	 	rayHandler.setAmbientLight(0.1f);
		engine = new Engine();
		engine.addSystem(new UpdateCharactherSystem());
		engine.addSystem(new ControlSystem(viewport));
		engine.addSystem(new MovementSystem());
		engine.addSystem(new RenderingSystem(batch));
		Player = Core.SpawnPlayerCharacther(rayHandler, 0, 0, selectedR, selectedS, selectedSS);
		engine.addEntity(Player);
		playerChar = Player.getComponent(CharactherComponent.class);
		
		
		UIDraws.Add("FPS", x -> {
			BitmapFont f = Assets.GetFont("empty");
			f.draw(x, "FPS : " + Gdx.graphics.getFramesPerSecond(), 30, 480 - 10);
			f.draw(x, "Health : " + playerChar.health + "/" + playerChar.maxHealth, 30, 480 - 30);
			f.draw(x, "Energy : " + playerChar.energy + "/" + playerChar.maxEnergy, 30,480 - 50);
			f.draw(x, "Speed : " + playerChar.speed, 30,480 - 70);
			f.draw(x, "Armor : " + playerChar.armor, 30,480 - 90);
			f.draw(x, "Visibility : " + playerChar.visibility, 30,480 - 110);
			f.draw(x, "Vision : " +  playerChar.vision, 30,480 - 130);
			f.draw(x, "Health Regen : " + playerChar.healthRegen, 30,480 - 150);
			f.draw(x, "Energy Regen : " + playerChar.energyRegen, 30,480 - 170);

		});

		
		Gdx.input.setInputProcessor(new InputAdapter() {
			
			@Override
			public boolean scrolled(int amount) {
				camera.zoom += amount * Core.deltaTime * 3f;
				camera.zoom = Math.max(0.8f, Math.min(3.6f, camera.zoom));
				return super.scrolled(amount);
			}
			
		});
	
	
	}
	
	
	@Override
	public void show() {
	}

	@Override
	public void render(float delta) {
		
		
		camera.update();
		batch.begin();
		batch.setProjectionMatrix(camera.combined);
		world.step(delta, 8, 3);
		rayHandler.update();
		
		engine.update(delta);
		rayHandler.setCombinedMatrix(camera);
		
		batch.end();
		rayHandler.render();
		
		UIBatch.begin();
		UIBatch.setProjectionMatrix(UICamera.combined);
		UIDraws.Broadcast(UIBatch);
		UIBatch.end();
		
		
		
	}

	@Override
	public void resize(int width, int height) {
		UIViewport.update(width,height, true);
		viewport.update(width,height, true);
		rayHandler.resizeFBO(width / 8, height / 8);
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		UIDraws.Dispose();
		batch.dispose();
		world.dispose();
		rayHandler.dispose();
	}

}
