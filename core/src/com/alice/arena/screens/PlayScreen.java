package com.alice.arena.screens;

import com.alice.arena.Core;
import com.alice.arena.components.CharactherComponent;
import com.alice.arena.data.Race;
import com.alice.arena.data.Skill;
import com.alice.arena.data.Style;
import com.alice.arena.systems.ControlSystem;
import com.alice.arena.systems.RenderingSystem;
import com.alice.arena.systems.UpdateCharactherSystem;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

public class PlayScreen implements Screen {

	OrthographicCamera camera;
	ExtendViewport viewport;
	Engine engine;
	SpriteBatch batch;
	
	
	
	public PlayScreen(Race selectedR, Style selectedS, Skill... selectedSS) {
	
		camera = new OrthographicCamera();
		viewport = new ExtendViewport(640, 480, camera);
		viewport.apply(true);
		batch = new SpriteBatch();
		
		
		engine = new Engine();
		engine.addSystem(new UpdateCharactherSystem());
		engine.addSystem(new ControlSystem(viewport));
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
		
		batch.begin();
		batch.setProjectionMatrix(camera.combined);
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
	
	}

}
