package com.alice.arena.data.skills;

import com.alice.arena.Core;
import com.alice.arena.components.CharactherComponent;
import com.alice.arena.components.PositionComponent;
import com.alice.arena.components.VelocityComponent;
import com.alice.arena.data.Skill;
import com.alice.arena.screens.PlayScreen;
import com.alice.arena.utils.Assets;
import com.alice.arena.utils.TextureHolder;
import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import box2dLight.PointLight;

public class ShootArrow extends Skill {

	
	int range =  500;
	float speed = 5f;
	BitmapFont font;
	
	
	public ShootArrow() {
		super("Shoot Arrow", new TextureHolder(Assets.GetTexture("shootarrow")), 1, 0.1f, "Shoot magnificent powerfull arrows while there is nothing else to do..");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void SkillInit(CharactherComponent cc) {
		font = new BitmapFont();
		cc.var.put("lastShootArrowN", -1);
		PlayScreen.UIDraws.Add("arrow", x -> {
			font.draw(x, "Existing Arrow Count : " + (1 + (int)cc.var.get("lastShootArrowN")), 30, 30);
		});

	}

	@Override
	public void SkillUpdate(CharactherComponent cc, Engine en, float delta, PositionComponent pc, VelocityComponent vc,
			int index) {
		int lastN = (int)cc.var.get("lastShootArrowN");
		for(int i = 0; i <= lastN; i++) {
			if(cc.var.containsKey("shootArrow" + i)) {
				Vector2 pos = (Vector2)cc.var.get("shootArrow" + i);
				Vector2 lookDir = (Vector2)cc.var.get("shootArrowLook" + i);
				Vector2 Opos = (Vector2)cc.var.get("shootArrowO" + i);
				PointLight light = (PointLight)cc.var.get("shootArrowLight" + i);
				float dist = Vector2.dst(pos.x, pos.y, Opos.x, Opos.y);
				if(dist <= range) {
					pos.x += lookDir.x * speed * 200f * delta;
					pos.y += lookDir.y * speed * 200f * delta;
					light.setPosition(pos.x + lookDir.x * 32f, pos.y + lookDir.y * 32f + 10f);
				}else {
					cc.var.remove("shootArrow" + i);
					cc.var.remove("shootArrowLook" + i);
					cc.var.remove("shootArrowO" + i);
					cc.var.remove("shootArrowRot" + i);
					light.remove();
					//light.dispose();
					cc.var.remove("shootArrowLight" + i);
					if(i == lastN) {
						cc.var.put("lastShootArrowN", lastN - 1);
						
					}
				}
			}
		}
		
		if(cc.progress[index] > 0)
			cc.progress[index] -= delta;

	}

	@Override
	public void SkillRender(SpriteBatch batch, ShapeRenderer shapeRenderer, CharactherComponent cc, PositionComponent pc, int index) {
		int lastN = (int)cc.var.get("lastShootArrowN");
		for(int i = 0; i <= lastN; i++) {
			if(cc.var.containsKey("shootArrow" + i)) {
				Vector2 pos = (Vector2)cc.var.get("shootArrow" + i);
				float rot = (float)cc.var.get("shootArrowRot" + i);
				texture.Draw(batch, pos.x, pos.y, 32, 32, 0, false, false, rot);
			}
		}

	}
	
	@Override
	public void ActiveCall(CharactherComponent cc, PositionComponent pc, int index) {
		
		if(cc.progress[index] <= 0) {
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
					cc.var.put("shootArrowLight" + n, Core.CreatePointLight(PlayScreen.rayHandler, pc.x + cc.race.width / 2f + 16f, pc.y + cc.race.height / 2f + 16f - 10f, Color.WHITE, 128, 5));
					cc.progress[index] = cooldown;
				}
			}
			while(con);
			if(n > (int)cc.var.get("lastShootArrowN")) {
				cc.var.put("lastShootArrowN", n);
			}
		}
	}

}
