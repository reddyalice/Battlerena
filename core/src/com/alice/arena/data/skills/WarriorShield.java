package com.alice.arena.data.skills;

import com.alice.arena.components.CharactherComponent;
import com.alice.arena.components.PositionComponent;
import com.alice.arena.components.VelocityComponent;
import com.alice.arena.data.Skill;
import com.alice.arena.utils.Assets;
import com.alice.arena.utils.TextureHolder;
import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class WarriorShield extends Skill{

	
	
	private BitmapFont font;
	public WarriorShield() {
		super("Warrior's Shield", new TextureHolder(Assets.GetTexture("warriorshield")), 1, 10, "Warrior's Trusted Shield");
		font = new BitmapFont();
	}

	@Override
	public void SkillInit(CharactherComponent cc) {
		cc.var.put("renderSheild", false);		
		
	}

	@Override
	public void SkillUpdate(CharactherComponent cc, Engine en, float delta, PositionComponent pc, VelocityComponent vc, int index) {
		
		if(cc.progress[index] >= cooldown)
		{
			cc.var.put("renderSheild", false);
			cc.progress[index] = cooldown;
		}
		
		
		if((boolean)(cc.var.get("renderSheild"))) {
			cc.progress[index] += delta;
		}else {
			if(cc.progress[index] > 0)
				cc.progress[index] -= delta * 5f;
			if(cc.progress[index] < 0)
				cc.progress[index] = 0;
		}
		
		
	}

	@Override
	public void SkillRender(SpriteBatch batch, ShapeRenderer shapeRenderer, CharactherComponent cc, PositionComponent pc, int index) {
		
		float x = pc.x -32f + cc.race.width / 2f + cc.lookDir.x * 20f;
		float y = pc.y -32f + cc.race.height / 2f - 10f + cc.lookDir.y * 20f;
		
		if((boolean)(cc.var.get("renderSheild")))
		{
			
			
			
			texture.Draw(batch, x, y, 64, 64, 0, false, cc.lookDir.x > 0 ? true : false, cc.rotation);
			
			font.draw(batch, (cooldown - cc.progress[index]) + "",x + 64, y + 32f);			
		}
		else {
			if(cc.progress[index] > 0)
			font.draw(batch, "Recharging in \n" +  (cc.progress[index]), x + 64f, y + 32f);
			else
			font.draw(batch, "Sheild Ready", x + 64f, y + 32f);
		}
		
	}

	@Override
	public void ActiveCall(CharactherComponent cc, PositionComponent pc, int index) {
		if(!(boolean)(cc.var.get("renderSheild"))) {
			if(cc.progress[index] == 0)
			{
				cc.var.put("renderSheild", true);
			}
		}
		
	}




}
