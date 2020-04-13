package com.alice.arena.data.skills;

import java.util.ArrayList;

import com.alice.arena.Core;
import com.alice.arena.components.CharactherComponent;
import com.alice.arena.components.PositionComponent;
import com.alice.arena.components.VelocityComponent;
import com.alice.arena.data.Skill;
import com.alice.arena.screens.PlayScreen;
import com.alice.arena.utils.Assets;
import com.alice.arena.utils.TextureHolder;
import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

import box2dLight.PointLight;

public class ShootArrow extends Skill {

	
	int range =  500;
	float speed = 5;
	BitmapFont font;
	
	
	public ShootArrow() {
		super("Shoot Arrow", new TextureHolder(Assets.GetTexture("shootarrow")), new TextureHolder(Assets.GetTexture("shootarrow")), 1, 0.1f, 10f, "Shoot magnificent powerfull arrows while there is nothing else to do..");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void SkillInit(CharactherComponent cc) {
		font = Assets.GetFont("empty"); 
		cc.var.put("lastShootArrowN", -1);
		PlayScreen.UIDraws.Add("arrow", x -> {
			font.draw(x, "Existing Arrow Count : " + (1 + (int)cc.var.get("lastShootArrowN")), 30, 30);
		});

	}

	@Override
	public void SkillUpdate(CharactherComponent cc, Engine en, float delta, PositionComponent pc, VelocityComponent vc,
			int index) {
		int lastN = (int)cc.var.get("lastShootArrowN");
		ArrayList<Thread> threads = new ArrayList<Thread>();
		for(int i = 0; i <= lastN; i++) {
			if(cc.var.containsKey("shootArrow" + i)) {
				final int k = i;
				Thread t = new Thread(new Runnable() {
					@Override
					public void run() {
						Vector2 pos = (Vector2)cc.var.get("shootArrow" + k);
						Vector2 lookDir = (Vector2)cc.var.get("shootArrowLook" + k);
						Vector2 Opos = (Vector2)cc.var.get("shootArrowO" + k);
						//PointLight light = (PointLight)cc.var.get("shootArrowLight" + k);
						Body body = (Body) cc.var.get("shootArrawBody" + k);
						float dist = Vector2.dst(pos.x, pos.y, Opos.x, Opos.y);
						if(dist <= range) {
							Vector2 p = body.getPosition();
							//body.setLinearVelocity( lookDir.x * speed * 200f,lookDir.y * speed * 200f);
							pos.x = p.x - 15f - 3f;
							pos.y = p.y - 6f - 9f;
							cc.var.put("shootArrowRot" + k, (float)(body.getAngle() * 180f / Math.PI));
							//light.setPosition(pos.x + lookDir.x * 32f, pos.y + lookDir.y * 32f + 10f);
						}else {
							cc.var.remove("shootArrow" + k);
							cc.var.remove("shootArrowLook" + k);
							cc.var.remove("shootArrowO" + k);
							cc.var.remove("shootArrowRot" + k);
							//light.remove();
							PlayScreen.world.destroyBody(body);
							cc.var.remove("shootArrawBody" + k);
							//cc.var.remove("shootArrowLight" + k);
							if(k == lastN) {
								cc.var.put("lastShootArrowN", k - 1);
								
							}
						}
						
					}
				});
				threads.add(t);
				t.run();
			}
		}
		
		for(Thread t : threads)
			try {
				t.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		if(cc.progress[index] > 0)
			cc.progress[index] -= delta;

	}

	@Override
	public void SkillRender(SpriteBatch batch, ShapeRenderer shapeRenderer, CharactherComponent cc, PositionComponent pc, int index) {
		int lastN = (int)cc.var.get("lastShootArrowN");
		//shapeRenderer.set(ShapeType.Filled);
		for(int i = 0; i <= lastN; i++) {
			if(cc.var.containsKey("shootArrow" + i)) {
				Vector2 pos = (Vector2)cc.var.get("shootArrow" + i);
				float rot = (float)cc.var.get("shootArrowRot" + i);
				texture.Draw(batch, pos.x, pos.y, 32, 32, 0, false, false, rot + 45f);
								
			}
		}

	}
	
	@Override
	public void ActiveCall(CharactherComponent cc, PositionComponent pc, int index) {
		
		if(cc.progress[index] <= 0 && cc.energy >= energyCost) {
			int n = 0;
			boolean con = false; 
			do {
				if(cc.var.containsKey("shootArrow" + n)) {
					con = true;
					n++;
				}else {
					con = false;
					cc.var.put("shootArrow" + n, new Vector2(pc.x + cc.race.width / 2f - 16f, pc.y + cc.race.height / 2f - 16f - 10f));
					cc.var.put("shootArrowO" + n, new Vector2(pc.x + cc.race.width / 2f - 16f, pc.y + cc.race.height / 2f - 16f - 10f));
					cc.var.put("shootArrowRot" + n, cc.rotation);
					cc.var.put("shootArrowLook" + n, cc.lookDir);
					
					Body b = Core.CreateASimpleBody(BodyType.KinematicBody,pc.x + cc.race.width / 2f - 16f + 2.5f + 29/2f,  pc.y + cc.race.height / 2f - 16f + 6f , 32f, 6f, 15f,  3f, "projectile/" + cc.team, true);
					b.setTransform(pc.x + cc.race.width / 2f - 16f + 2.5f,  pc.y + cc.race.height / 2f - 16f, (float)((cc.rotation) * Math.PI / 180f));
					//PointLight l =  Core.CreatePointLight(PlayScreen.rayHandler, pc.x + cc.race.width / 2f + 16f, pc.y + cc.race.height / 2f + 16f - 10f, Color.WHITE, 128, 5);
					b.setLinearVelocity( cc.lookDir.x * speed * 50f,cc.lookDir.y * speed * 50f);
					b.setBullet(true);
					//l.attachToBody(b);
					cc.var.put("shootArrawBody" + n, b);
					//cc.var.put("shootArrowLight" + n,l);
					cc.progress[index] = cooldown;
				}
			}
			while(con);
			if(n > (int)cc.var.get("lastShootArrowN")) {
				cc.var.put("lastShootArrowN", n);
			}
			cc.energy -= energyCost;
		}
	}

}
