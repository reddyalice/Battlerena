package com.alice.arena.data.skills;

import com.alice.arena.components.CharactherComponent;
import com.alice.arena.components.PositionComponent;
import com.alice.arena.components.VelocityComponent;
import com.alice.arena.data.Skill;
import com.alice.arena.utils.Assets;
import com.alice.arena.utils.TextureHolder;
import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class WarriorSword extends Skill {

	float rotationLimit = 90;
	float rotSpeed = 900;
	public WarriorSword() {
		super("Warrior's Sword", new TextureHolder(Assets.GetTexture("sword")),  new TextureHolder(Assets.GetTexture("sword")), 1, 1, 5f, "A Warrior's Trustworthy Sword");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void SkillInit(CharactherComponent cc) {
		cc.var.put("swingSword", false);
		cc.var.put("swordPosX", 0f);
		cc.var.put("swordPosY", 0f);
		cc.var.put("swordRot", rotationLimit);
	}

	@Override
	public void SkillUpdate(CharactherComponent cc, Engine en, float delta, PositionComponent pc, VelocityComponent vc, int index) {
		boolean swing = (boolean)cc.var.get("swingSword");
		float rot = (float)cc.var.get("swordRot");
		if(swing)
		{
			if(rot >= 0) {
				rot -= delta * rotSpeed;
				cc.var.put("swordRot", rot);
			}else {
				cc.var.put("swordRot", rotationLimit);
				cc.var.put("swingSword", false);
			}
			
			cc.var.put("swordPosX", pc.x + cc.race.width / 2f - 32f);
			cc.var.put("swordPosY", pc.y + cc.race.height / 2f - 16f);
			
		}else {

			cc.var.put("swordPosX", pc.x + cc.race.width / 2f - 16f);
			cc.var.put("swordPosY", pc.y + cc.race.height / 2f - 30f + 20f);
		}
		
		if(cc.progress[index] > 0)
			cc.progress[index] -= delta;
		
	}

	@Override
	public void SkillRender(SpriteBatch batch, ShapeRenderer shapeRenderer, CharactherComponent cc, PositionComponent pc, int index) {
		
		float x = (float)cc.var.get("swordPosX");
		float y = (float)cc.var.get("swordPosY");		
		boolean swing = (boolean)cc.var.get("swingSword");
		float rot = (float)cc.var.get("swordRot");
		
		if(swing)
		{
			texture.Draw(batch, x, y, 32,0, 32, 32, 0, false, false,  cc.rotation + rot );
		}
		else {
			texture.Draw(batch, x, y, 16,0, 16, 16, 0, false, false, cc.flip ?  180 : 90 );
		}
		
	}

	@Override
	public void ActiveCall(CharactherComponent cc, PositionComponent pc, int index) {
		boolean swing = (boolean)cc.var.get("swingSword"); 
		
		if(cc.progress[index] <= 0 && !swing) {
			
			cc.var.put("swingSword", true);
			cc.progress[index] = cooldown;
			cc.energy -= energyCost;
		}
		
	}





}
