package com.alice.arena.screens;

import java.util.HashSet;

import com.alice.arena.Core;
import com.alice.arena.components.CharactherComponent;
import com.alice.arena.data.Race;
import com.alice.arena.data.Registry;
import com.alice.arena.data.Skill;
import com.alice.arena.data.Style;
import com.alice.arena.systems.AIUpdateSystem;
import com.alice.arena.systems.CharactherRenderingSystem;
import com.alice.arena.systems.ControlSystem;
import com.alice.arena.systems.MovementSystem;
import com.alice.arena.systems.UpdateCharactherSystem;
import com.alice.arena.utils.Assets;
import com.alice.arena.utils.Builder;
import com.alice.arena.utils.EvE;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.SharedLibraryLoader;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.crashinvaders.vfx.VfxManager;
import com.crashinvaders.vfx.effects.BloomEffect;
import com.crashinvaders.vfx.effects.ChromaticAberrationEffect;
import com.crashinvaders.vfx.effects.VignetteEffect;
import com.crashinvaders.vfx.effects.OldTvEffect;
import box2dLight.RayHandler;



public class PlayScreen implements Screen {

	private OrthographicCamera camera;
	private OrthographicCamera UICamera;
	private ExtendViewport viewport;
	private ExtendViewport UIViewport;
	private Engine engine;
   
	

	
	public static EvE<SpriteBatch> UIDraws = new EvE<SpriteBatch>();

	public static PlayScreen screen;
	
	public boolean change = false;
	
	private int numberOfSteps = 6;
	
	private SpriteBatch batch;
	private SpriteBatch UIBatch;
	private ShapeRenderer shapeRenderer;
	private Box2DDebugRenderer debugRenderer;
	private OrthogonalTiledMapRenderer mapRenderer;
	private float mapScale = 2;
	
	

	
	public PlayScreen(Race selectedR, Style selectedS, Skill... selectedSS) {
		screen = this;
		Core.backgroundColor = Color.BLACK;
		
		
		camera = new OrthographicCamera();
		viewport = new ExtendViewport(Core.WIDTH, Core.HEIGHT, camera);
		viewport.apply(true);
		UICamera = new OrthographicCamera();
		UIViewport = new ExtendViewport(Core.WIDTH, Core.HEIGHT, UICamera);
		UIViewport.apply(true);
		
		batch = new SpriteBatch();
		UIBatch = new SpriteBatch();
		TiledMap map = Assets.GetMap("dungeon");
		mapRenderer = new OrthogonalTiledMapRenderer(map, mapScale, batch);

		shapeRenderer = new ShapeRenderer();
	
		
		//vfxManager.addEffect(chromeEffect);
        //vfxManager.addEffect(vignetteEffect);
        //vfxManager.addEffect(oldTVEffect);;
		shapeRenderer.setAutoShapeType(true);
		debugRenderer = new Box2DDebugRenderer();
		

		Core.rayHandler.setAmbientLight(0.5f);
	 	
	 	
	 	
	 	
		
		BodyDef def = new BodyDef();
		PolygonShape shape = new PolygonShape();
		FixtureDef fdef = new FixtureDef();
		Body b;
		
		for(RectangleMapObject mObject : map.getLayers().get("walls").getObjects().getByType(RectangleMapObject.class)) {
 			Rectangle rect = mObject.getRectangle();
		
			shape.setAsBox(rect.width / 2f * mapScale, rect.height / 2f * mapScale );
			def.type = BodyType.StaticBody;
			def.position.set(rect.getX() * mapScale + rect.width / 2f *mapScale, rect.getY() * mapScale + rect.height / 2f * mapScale);
			fdef.shape = shape;
			
			b = Core.world.createBody(def);
			Fixture f = b.createFixture(fdef);
			f.setUserData("wall");

		
		}
		
		
		for(RectangleMapObject mObject : map.getLayers().get("pits").getObjects().getByType(RectangleMapObject.class)) {
 			Rectangle rect = mObject.getRectangle();
		
			shape.setAsBox(rect.width / 2f * mapScale, rect.height / 2f * mapScale );
			def.type = BodyType.StaticBody;
			def.position.set(rect.getX() * mapScale + rect.width / 2f *mapScale, rect.getY() * mapScale + rect.height / 2f * mapScale);
			fdef.shape = shape;
			
			b = Core.world.createBody(def);
			Fixture f = b.createFixture(fdef);
			f.setUserData("pit");

		
		}
			
		
		HashSet<Entity> ais = new HashSet<Entity>();
		
		
		for(RectangleMapObject mObject : map.getLayers().get("spawns").getObjects().getByType(RectangleMapObject.class))
			
		{
			if(mObject.getName().contentEquals("playerSpawn")) {

				RectangleMapObject playerSpawn = mObject;

				Core.playerSpawnPoint = new Vector2(playerSpawn.getRectangle().x * mapScale, (float)playerSpawn.getRectangle().y * mapScale);
				Core.Player = Builder.SpawnPlayerCharacther(Core.rayHandler,Core.playerSpawnPoint.x, Core.playerSpawnPoint.y, selectedR, selectedS, "player", selectedSS);

			}
			
			if(mObject.getName().startsWith("ai")) {
				RectangleMapObject aiSpawn = mObject;
				ais.add(Builder.SpawnRandomAICharacther(Core.rayHandler, aiSpawn.getRectangle().getX()  * mapScale, aiSpawn.getRectangle().getY() * mapScale, 1, Math.random() < 0.5d ? "test" : "not test"));
			}
			
			
		}
		
		
		
		
		engine = new Engine();

		
		engine.addSystem(new ControlSystem(viewport));
		engine.addSystem(new AIUpdateSystem());
		engine.addSystem(new UpdateCharactherSystem());
		engine.addSystem(new MovementSystem());
		engine.addSystem(new CharactherRenderingSystem(batch, shapeRenderer));
		
		for(Entity en : ais)
			engine.addEntity(en);
		Core.playerChar = Core.Player.getComponent(CharactherComponent.class);
		
		engine.addEntity(Core.Player);
		UIDraws.Add("FPS", x -> {
			BitmapFont f = Assets.GetFont("fff");
			f.getData().setScale(0.4f);
			f.draw(x, "FPS : " + Gdx.graphics.getFramesPerSecond(), 30, Core.HEIGHT - 10);
			f.draw(x, "Health : " + Core.playerChar.health + "/" + Core.playerChar.maxHealth, 30, Core.HEIGHT - 30);
			f.draw(x, "Energy : " + Core.playerChar.energy + "/" + Core.playerChar.maxEnergy, 30,Core.HEIGHT - 50);
			f.draw(x, "Speed : " + Core.playerChar.speed, 30,Core.HEIGHT - 70);
			f.draw(x, "Armor : " + Core.playerChar.armor, 30,Core.HEIGHT - 90);
			f.draw(x, "Visibility : " + Core.playerChar.visibility, 30,Core.HEIGHT - 110);
			f.draw(x, "Vision : " +  Core.playerChar.vision, 30,Core.HEIGHT - 130);
			f.draw(x, "Health Regen : " + Core.playerChar.healthRegen, 30,Core.HEIGHT - 150);
			f.draw(x, "Energy Regen : " + Core.playerChar.energyRegen, 30,Core.HEIGHT - 170);

		});
		
		
		if(Core.playerChar.race == Registry.RACES.Glitch) {	
			Core.VfxManager.removeAllEffects();
			
			Core.VfxManager.addEffect(Core.vignetteEffect);
			Core.VfxManager.addEffect(Core.oldTVEffect);;
		
		}else if(Core.playerChar.race == Registry.RACES.Elf) {
			Core.VfxManager.removeAllEffects();
			Core.VfxManager.addEffect(Core.chromeEffect);
		
		}
		
		
		else {
			
			
			
			Core.VfxManager.removeAllEffects();
		}

		camera.zoom = Math.max(0.3f, Math.min(0.6f * Core.playerChar.vision, camera.zoom));
		Gdx.input.setInputProcessor(new InputAdapter() {
			
			@Override
			public boolean scrolled(int amount) {
				camera.zoom += amount * Core.deltaTime * 3f;
				camera.zoom = Math.max(0.3f, Math.min(0.6f * Core.playerChar.vision, camera.zoom));
				return super.scrolled(amount);
			}
			
		});
	
	
	}
	
	
	@Override
	public void show() {
	}

	@Override
	public void render(float delta) {
		
		

		Thread[] worldSteps = new Thread[numberOfSteps + 1];
		for(int i = 0; i < numberOfSteps; i++) {
		Thread worldT = new Thread(new Runnable() {
			
			@Override
			public void run() {
				Core.world.step(delta, 12, 6);
				
			}
		});
		worldSteps[i] = worldT;

		worldT.run();
		
			
		}
		
		Thread rayH = new Thread(new Runnable() {
			
			@Override
			public void run() {
				Core.rayHandler.update();
				
			}
		});
		worldSteps[numberOfSteps] = rayH;
		rayH.run();
		
		Core.VfxManager.cleanUpBuffers();
		Core.VfxManager.beginCapture();
		camera.update();
		mapRenderer.setView(camera);
		mapRenderer.render();
		
		batch.begin();
		shapeRenderer.begin();
		
		batch.setProjectionMatrix(camera.combined);
		shapeRenderer.setProjectionMatrix(camera.combined);
		
		
		
		
		engine.update(delta);
		Core.rayHandler.setCombinedMatrix(camera);
		
		batch.end();
		
		shapeRenderer.end();
		
		//debugRenderer.render(world, camera.combined);
		Core.VfxManager.endCapture();
		Core.VfxManager.applyEffects();
		Core.VfxManager.renderToScreen();
		
		Core.rayHandler.render();
		UIBatch.begin();
		UIBatch.setProjectionMatrix(UICamera.combined);
		UIDraws.Broadcast(UIBatch);
		UIBatch.end();
		
		
		
		
		
		for(Thread worldT : worldSteps)
		try {
			worldT.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(change) {
			this.dispose();
			Core.instance.setScreen(new SelectScreen());
		}
		
		
	}

	@Override
	public void resize(int width, int height) {
		
		UIViewport.update(width,height, true);
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
		UIDraws.Dispose();
		shapeRenderer.dispose();
		
		batch.dispose();
		debugRenderer.dispose();
		mapRenderer.dispose();
	}

}
