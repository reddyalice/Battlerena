package com.alice.arena.data.skills;

import com.alice.arena.components.CharactherComponent;
import com.alice.arena.components.PositionComponent;
import com.alice.arena.components.VelocityComponent;
import com.alice.arena.data.Skill;
import com.alice.arena.utils.Assets;
import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class WarriorShield extends Skill{

	
	private boolean renderSheild = false;
	private BitmapFont font;
	public WarriorShield() {
		super("Warrior's Shield", Assets.GetTexture("warriorshield"), 1, 10, "Warrior's Trusted Shield");
		font = new BitmapFont();
	}

	@Override
	public void SkillInit(CharactherComponent cc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void SkillUpdate(CharactherComponent cc, Engine en, float delta, PositionComponent pc, VelocityComponent vc, int index) {
		
		if(cc.progress[index] >= cooldown)
		{
			renderSheild = false;
			cc.progress[index] = cooldown;
		}
		
		
		if(renderSheild) {
			cc.progress[index] += delta;
		}else {
			if(cc.progress[index] > 0)
				cc.progress[index] -= delta * 5f;
			if(cc.progress[index] < 0)
				cc.progress[index] = 0;
		}
		
		
	}

	@Override
	public void SkillRender(SpriteBatch batch, CharactherComponent cc, PositionComponent pc, int index) {
		
		float x = pc.x  + cc.lookDir.x * 40f;
		float y = pc.y + cc.lookDir.y * 40f;
		
		if(renderSheild)
		{
			batch.draw(texture, x, y, 32,32, 64, 64, 1, 1, cc.rotation, 0,0
					,texture.getWidth(), texture.getHeight(), false, false);
			
			font.draw(batch, (cooldown - cc.progress[index]) + "",x, y);
			
			
			
			
			
		}
		else {
			if(cc.progress[index] > 0)
			font.draw(batch, "Recharging in \n" +  (cc.progress[index]), x, y);
			else
			font.draw(batch, "Sheild Ready", x, y);
		}
		
	}

	@Override
	public void ActiveCall(CharactherComponent cc,int index) {
		if(!renderSheild) {
			if(cc.progress[index] == 0)
			{
				renderSheild = true;
			}
		}
		
	}





}
