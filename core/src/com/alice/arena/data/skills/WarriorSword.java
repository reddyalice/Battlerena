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

	public WarriorSword() {
		super("Warrior's Sword", new TextureHolder(Assets.GetTexture("sword")),  new TextureHolder(Assets.GetTexture("sword")), 1, 1, 10f, "A Warrior's Trustworthy Sword");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void SkillInit(CharactherComponent cc) {
		cc.var.put("swingSword", false);
		cc.var.put("swordPosX", 0f);
		cc.var.put("swordPosY", 0f);
	}

	@Override
	public void SkillUpdate(CharactherComponent cc, Engine en, float delta, PositionComponent pc, VelocityComponent vc, int index) {
		boolean swing = (boolean)cc.var.get("swingSword");
		
		if(swing)
		{
			
			
			
		}else {

			cc.var.put("swordPosX", pc.x + cc.race.width / 2f - 32f);
			cc.var.put("swordPosY", pc.y + cc.race.height / 2f - 30f + 16f);
		}
		
	}

	@Override
	public void SkillRender(SpriteBatch batch, ShapeRenderer shapeRenderer, CharactherComponent cc, PositionComponent pc, int index) {
		
		float x = (float)cc.var.get("swordPosX");
		float y = (float)cc.var.get("swordPosY");		
		
		
		texture.Draw(batch, x, y, 32,0, 32, 32, 0, false, false,  cc.flip ? 45f :  180f + 45f);
		
	}

	@Override
	public void ActiveCall(CharactherComponent cc, PositionComponent pc, int index) {
		// TODO Auto-generated method stub
		
	}





}
