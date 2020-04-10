package com.alice.arena.screens;

import com.alice.arena.Core;
import com.alice.arena.data.Race;
import com.alice.arena.data.Skill;
import com.alice.arena.data.Style;
import com.alice.arena.systems.ControlSystem;
import com.alice.arena.systems.MovementSystem;
import com.alice.arena.systems.RenderingSystem;
import com.alice.arena.systems.UpdateCharactherSystem;
import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.ExtendViewport;



public class PlayScreen implements Screen {

	private OrthographicCamera camera;
	private ExtendViewport viewport;
	private Engine engine;
   
	private SpriteBatch batch;
	private World world;
	
	
	public PlayScreen(Race selectedR, Style selectedS, Skill... selectedSS) {
	
		camera = new OrthographicCamera();
		viewport = new ExtendViewport(640, 480, camera);
		viewport.apply(true);
		batch = new SpriteBatch();
		world = new World(new Vector2(0,0), false);
		
		engine = new Engine();
		engine.addSystem(new UpdateCharactherSystem());
		engine.addSystem(new ControlSystem(viewport));
		engine.addSystem(new MovementSystem());
		engine.addSystem(new RenderingSystem(batch));
		engine.addEntity(Core.SpawnPlayerCharacther(0, 0, selectedR, selectedS, selectedSS));
		
		

		
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
		engine.update(delta);
		batch.end();

	}

	@Override
	public void resize(int width, int height) {
		viewport.update(width,height, true);

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
		batch.dispose();
	}

}
