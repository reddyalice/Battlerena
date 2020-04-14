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
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
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
	public static EvE<Contact> beginContantCalls = new EvE<Contact>();
	public static EvE<Contact> endContantCalls = new EvE<Contact>();
	
	private int numberOfSteps = 3;
	
	private SpriteBatch batch;
	private SpriteBatch UIBatch;
	private ShapeRenderer shapeRenderer;
	private Box2DDebugRenderer debugRenderer;
	private OrthogonalTiledMapRenderer mapRenderer;
	private float mapScale = 2;
	
	
	public static World world;
	public static RayHandler rayHandler;
	
	public PlayScreen(Race selectedR, Style selectedS, Skill... selectedSS) {
		
		Core.backgroundColor = Color.BLACK;
		
		
		camera = new OrthographicCamera();
		viewport = new ExtendViewport(640, 480, camera);
		viewport.apply(true);
		UICamera = new OrthographicCamera();
		UIViewport = new ExtendViewport(640, 480, UICamera);
		UIViewport.apply(true);
		
		batch = new SpriteBatch();
		UIBatch = new SpriteBatch();
		TiledMap map = Assets.GetMap("dungeon");
		mapRenderer = new OrthogonalTiledMapRenderer(map, mapScale, batch);

		shapeRenderer = new ShapeRenderer();
		
		
		shapeRenderer.setAutoShapeType(true);
		debugRenderer = new Box2DDebugRenderer();
		
		world = new World(new Vector2(0,0), false);
		rayHandler = new RayHandler(world, 640 / 8, 480 / 8);
	 	rayHandler.setAmbientLight(0.5f);
	 	
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
			
			b = world.createBody(def);
			Fixture f = b.createFixture(fdef);
			f.setUserData("wall");

		
		}
		
		
		for(RectangleMapObject mObject : map.getLayers().get("pits").getObjects().getByType(RectangleMapObject.class)) {
 			Rectangle rect = mObject.getRectangle();
		
			shape.setAsBox(rect.width / 2f * mapScale, rect.height / 2f * mapScale );
			def.type = BodyType.StaticBody;
			def.position.set(rect.getX() * mapScale + rect.width / 2f *mapScale, rect.getY() * mapScale + rect.height / 2f * mapScale);
			fdef.shape = shape;
			
			b = world.createBody(def);
			Fixture f = b.createFixture(fdef);
			f.setUserData("pit");

		
		}
	
		RectangleMapObject playerSpawn = (RectangleMapObject) map.getLayers().get("spawns").getObjects().get("playerSpawn");
		
		
		Entity[] ais = new Entity[4];
		
		for(int i = 0; i < 4; i++) {
			RectangleMapObject aiSpawn = (RectangleMapObject) map.getLayers().get("spawns").getObjects().get("ai" + (i + 1));
			ais[i] = Core.SpawnRandomAICharacther(rayHandler, aiSpawn.getRectangle().getX()  * mapScale, aiSpawn.getRectangle().getY() * mapScale, 1, "test");
		}
		
		
		
		
		
		engine = new Engine();
		Player = Core.SpawnPlayerCharacther(rayHandler,playerSpawn.getRectangle().x * mapScale, (float)playerSpawn.getRectangle().y * mapScale, selectedR, selectedS, "player", selectedSS);

		engine.addSystem(new UpdateCharactherSystem());
		engine.addSystem(new ControlSystem(viewport));
		engine.addSystem(new MovementSystem());
		engine.addSystem(new RenderingSystem(batch, shapeRenderer));
		
		for(Entity en : ais)
			engine.addEntity(en);
		playerChar = Player.getComponent(CharactherComponent.class);
		
		engine.addEntity(Player);
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
		
		Thread[] worldSteps = new Thread[numberOfSteps + 1];
		for(int i = 0; i < numberOfSteps; i++) {
		Thread worldT = new Thread(new Runnable() {
			
			@Override
			public void run() {
				world.step(delta, 12, 6);
				world.step(delta, 12, 6);
				
			}
		});
		worldSteps[i] = worldT;

		worldT.run();
		
			
		}
		
		Thread rayH = new Thread(new Runnable() {
			
			@Override
			public void run() {
				rayHandler.update();
				
			}
		});
		worldSteps[numberOfSteps] = rayH;
		rayH.run();
		
		

		camera.update();
		mapRenderer.setView(camera);
		mapRenderer.render();
		batch.begin();
		shapeRenderer.begin();
		
		batch.setProjectionMatrix(camera.combined);
		shapeRenderer.setProjectionMatrix(camera.combined);
		
		
		
		
		engine.update(delta);
		rayHandler.setCombinedMatrix(camera);
		
		batch.end();
		rayHandler.render();
		shapeRenderer.end();
		//debugRenderer.render(world, camera.combined);
		
		
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
		shapeRenderer.dispose();
		batch.dispose();
		world.dispose();
		rayHandler.dispose();
		debugRenderer.dispose();
		mapRenderer.dispose();
	}

}
