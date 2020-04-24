package com.alice.arena.screens;

import java.util.ArrayList;
import java.util.HashSet;

import com.alice.arena.Core;
import com.alice.arena.data.Race;
import com.alice.arena.data.Registry;
import com.alice.arena.data.Skill;
import com.alice.arena.data.Style;
import com.alice.arena.utils.Assets;
import com.alice.arena.utils.TextureHolder;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.crashinvaders.vfx.VfxManager;
import com.crashinvaders.vfx.effects.ChromaticAberrationEffect;
import com.crashinvaders.vfx.effects.OldTvEffect;
import com.crashinvaders.vfx.effects.VignetteEffect;

public class SelectScreen implements Screen {
	
	private SpriteBatch batch;
	
	private VfxManager vfxManager;
    private ChromaticAberrationEffect chromeEffect;
    private VignetteEffect vignetteEffect;
	private OldTvEffect oldTVEffect;
	
	
	private Texture skillSocket;
	private Texture holder;
	private Texture indic;
	private Texture lock;
	private BitmapFont font;
	
	
	private ArrayList<Race> races = new ArrayList<Race>();
	private ArrayList<Style> styles = new ArrayList<Style>();
	

	private int selectedRaceIndex = 0;
	private int selectedStyleIndex = 0;
	private boolean down = false;
	
	private HashSet<Skill> raceSkills = new HashSet<Skill>();
	private HashSet<Skill> styleSkills = new HashSet<Skill>();
	
	private HashSet<Skill> selectedRaceSkills = new HashSet<Skill>();
	private HashSet<Skill> selectedStyleSkills = new HashSet<Skill>();
	
	
	private ExtendViewport viewport;
	private OrthographicCamera camera;
	
	private float scale = 5;
	private int regionPointer = 0; 
	float timeHolder;
	
	private int sLimit;
	private int rLimit;
	

	public SelectScreen() {
		Core.backgroundColor = Color.BLACK;
		skillSocket = Assets.GetTexture("socket");
		holder = Assets.GetTexture("holder"); 
		indic = Assets.GetTexture("indic");
		font = Assets.GetFont("fff");
		lock = Assets.GetTexture("lock");
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		viewport = new ExtendViewport(Core.WIDTH, Core.HEIGHT, camera);
		viewport.apply(true);
		
		vfxManager = new VfxManager(Pixmap.Format.RGBA8888);
		chromeEffect = new ChromaticAberrationEffect();
		vignetteEffect = new VignetteEffect(false);
		chromeEffect.setMaxDistortion(0.1f);
		oldTVEffect = new OldTvEffect();
        vfxManager.addEffect(chromeEffect);
        vfxManager.addEffect(vignetteEffect);
        vfxManager.addEffect(oldTVEffect);;
		scale *= ((float)Core.WIDTH / 1024f);

		for(int key : Registry.raceList.keySet()) 
			if(Registry.raceList.get(key).show)
				races.add(Registry.raceList.get(key));
		for(int key : Registry.styleList.keySet()) 
			if(Registry.styleList.get(key).show)
				styles.add(Registry.styleList.get(key));
		
		styleSkills = new HashSet<Skill>();
		sLimit = styles.get(selectedStyleIndex).skillLimit;
		for(Skill k : styles.get(selectedStyleIndex).styleSkills)
			styleSkills.add(k);
		
		raceSkills = new HashSet<Skill>();
		rLimit = races.get(selectedRaceIndex).skillLimit;
		for(Skill k : races.get(selectedRaceIndex).raceSkills)
			raceSkills.add(k);
		
		
	}

	@Override
	public void show() {
		if(races.isEmpty() || styles.isEmpty())
		{
			for(int key : Registry.raceList.keySet()) 
				if(Registry.raceList.get(key).show)
					races.add(Registry.raceList.get(key));
			for(int key : Registry.styleList.keySet()) 
				if(Registry.styleList.get(key).show)
					styles.add(Registry.styleList.get(key));
			
		}

	}

	@Override
	public void render(float delta) {
		
		if(races.isEmpty() || styles.isEmpty())
		{
			for(int key : Registry.raceList.keySet()) 
				if(Registry.raceList.get(key).show)
					races.add(Registry.raceList.get(key));
			for(int key : Registry.styleList.keySet()) 
				if(Registry.styleList.get(key).show)
					styles.add(Registry.styleList.get(key));
			
		}
		
		camera.update();
		
		if(Gdx.input.isKeyJustPressed(Input.Keys.RIGHT))
			if(down) {
				if(selectedStyleIndex < styles.size() - 1) {
					selectedStyleIndex++;
					styleSkills = new HashSet<Skill>();
					sLimit = styles.get(selectedStyleIndex).skillLimit;
					for(Skill k : styles.get(selectedStyleIndex).styleSkills)
						styleSkills.add(k);
					
				}
			}
			else {
				if(selectedRaceIndex < races.size() - 1) {
					selectedRaceIndex++;
					raceSkills = new HashSet<Skill>();
					rLimit = races.get(selectedRaceIndex).skillLimit;
					for(Skill k : races.get(selectedRaceIndex).raceSkills)
						raceSkills.add(k);
				}
			}	
		
		
		if(Gdx.input.isKeyJustPressed(Input.Keys.LEFT))
			if(down) {
				if(selectedStyleIndex > 0) {
					selectedStyleIndex--;
					styleSkills = new HashSet<Skill>();
					sLimit = styles.get(selectedStyleIndex).skillLimit;
					for(Skill k : styles.get(selectedStyleIndex).styleSkills)
						styleSkills.add(k);
				}
			}else {
				if(selectedRaceIndex > 0)
				{
					selectedRaceIndex--;
					raceSkills = new HashSet<Skill>();
					rLimit = races.get(selectedRaceIndex).skillLimit;
					for(Skill k : races.get(selectedRaceIndex).raceSkills)
						raceSkills.add(k);
					
				}
			}
		
		
		if(Gdx.input.isKeyJustPressed(Input.Keys.UP))
			if(down)
				down = false;
		
		if(Gdx.input.isKeyJustPressed(Input.Keys.DOWN))
			if(!down)
				down = true;
		
		
		
		vfxManager.cleanUpBuffers(Color.BLACK);
		vfxManager.beginCapture();
		batch.begin();
		batch.setProjectionMatrix(camera.combined);
		
		batch.setColor(Color.WHITE);
		
		float offset = (Core.WIDTH - (32 * scale * 6f)) / 2f;
		
		
		batch.draw(holder, offset, Core.HEIGHT - 38 *scale - (50), (32 * scale * 6f), 38 * scale);
		batch.draw(holder, offset, Core.HEIGHT - 38 *scale * 2f - (50), (32 * scale * 6f), 38 * scale);
		
		for(int i = 0; i < races.size(); i++) {
			Race r  = races.get(i);
		
			float x = 32 * scale * 2.5f + scale * 32 * (i - selectedRaceIndex) + offset;
			float y = Core.HEIGHT - 48 * scale;
			
			
			
			int dist = Math.abs(i - selectedRaceIndex);
			if(dist < 3) {
				
				//batch.setColor(1,1,1,  (3f - dist) / 3f);
				r.texture.Draw(batch, x, y, (32 * scale / 2f) , (48 * scale / 2f),  (int)(32f * scale),  (int)(48 * scale), i == selectedRaceIndex ? regionPointer : 0, false, false, 0);
			}
		}
		
		
		for(int i = 0; i < styles.size(); i++) {
			Race r  = races.get(selectedRaceIndex);
			Style s = styles.get(i);
		
			float x = 32 * scale * 2.5f + scale * 32 * (i - selectedStyleIndex) + offset;
			float y = Core.HEIGHT - 48 * scale -  38 *scale;
			
			
			
			int dist = Math.abs(i - selectedStyleIndex);
			if(dist < 3) {
				
				//batch.setColor(1,1,1,  (3f - dist) / 3f);
				
				r.texture.Draw(batch, x, y, (32 * scale / 2f) , (48 * scale / 2f),  (int)(32f * scale),  (int)(48 * scale), i == selectedStyleIndex ? regionPointer : 0, false, false, 0);
				s.texture.Draw(batch, x, y, (32 * scale / 2f) , (48 * scale / 2f), (int)(32f * scale),  (int)(48 * scale),  i == selectedStyleIndex ? regionPointer : 0, false, false, 0);
			}
		}
		font.getData().setScale(scale / 5f);
		font.draw(batch, races.get(selectedRaceIndex).name + " " + styles.get(selectedStyleIndex).name, 32 ,  Core.HEIGHT - 48 * scale -  38 *scale - 32f);
		
		if(down) {
			batch.draw(indic, 32 * scale * 2.5f + offset, Core.HEIGHT - 48 * scale -  38 *scale - 15f, 32 * scale, 42 * scale);
		}else {
			batch.draw(indic, 32 * scale * 2.5f + offset, Core.HEIGHT - 48 * scale -15f , 32 * scale, 42 * scale);
		}
		
		
		for(int i = 0; i < sLimit; i++) {
			float x =  Core.WIDTH - 38 * 2 - 32 - 38 * 2 * i;
			float y = Core.HEIGHT - 48 * scale -  38 *scale - 38 * 5;
			batch.draw(skillSocket, x, y, 38 * 2, 38 * 2);
		}
		

		for(int i = 0; i < rLimit; i++) {
			float x =  Core.WIDTH - 38 * 2 - 32 - 38 * 2 * i;
			float y = Core.HEIGHT - 48 * scale -  38 *scale - 38 * 8;
			batch.draw(skillSocket, x, y, 38 * 2, 38 * 2);
		}
		
		
		
		int i = 0;
		for(Skill rc : raceSkills) {
			
			float x =  32 + 38 * 2 * (scale / 5f) * i;
			float y = Core.HEIGHT - 48 * scale - 38 * scale * 2;
			
			batch.draw(skillSocket, x, y, (38 * 2f  * scale / 5f), (38 * 2f * scale / 5f));
			rc.iconTexture.Draw(batch, x + 6f, y + 6f, 32 * scale / 5f, 32 * scale / 5f, (int)(64 * scale / 5f), (int)(64 * scale / 5f), 0, false, false, 0);
			if(rc.level > Core.account.raceLevels.get(races.get(selectedRaceIndex).id))
				batch.draw(lock, x, y, (38 * 2f  * scale / 5f), (38 * 2f * scale / 5f));
			
			i++;
			
		}
		
		i = 0;
		for(Skill rc : styleSkills) {
			
			float x =  32 + 38 * 2 * (scale / 5f) * i;
			float y = Core.HEIGHT - 48 * scale -  38 *scale - 38 * 8 * (scale / 5f);
			
			batch.draw(skillSocket, x, y, (38 * 2f  * scale / 5f), (38 * 2f * scale / 5f));
			rc.iconTexture.Draw(batch, x + 6f, y + 6f, 32 * scale / 5f, 32 * scale / 5f, (int)(64 * scale / 5f), (int)(64 * scale / 5f), 0, false, false, 0);
			if(rc.level > Core.account.styleLevels.get(styles.get(selectedStyleIndex).id))
				batch.draw(lock, x, y, (38 * 2f  * scale / 5f), (38 * 2f * scale / 5f));
			
			i++;
			
		}
		
		
		
		
		batch.end();
		vfxManager.endCapture();
		vfxManager.applyEffects();
		vfxManager.renderToScreen();
		
		
		timeHolder += delta * 10f;
		regionPointer = 15 + (int) (timeHolder) % 3;
		
		
	}

	@Override
	public void resize(int width, int height) {
		viewport.update(width, height, true);
		vfxManager.resize(width, height);
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
		vfxManager.dispose();
		chromeEffect.dispose();
		vignetteEffect.dispose();
		oldTVEffect.dispose();

	}

}
