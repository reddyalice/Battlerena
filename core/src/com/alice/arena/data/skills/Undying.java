package com.alice.arena.data.skills;

import com.alice.arena.components.AIComponent;
import com.alice.arena.components.CharactherComponent;
import com.alice.arena.components.PositionComponent;
import com.alice.arena.components.VelocityComponent;
import com.alice.arena.data.Registry;
import com.alice.arena.data.Skill;
import com.alice.arena.utils.Assets;
import com.alice.arena.utils.TextureHolder;
import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Undying extends Skill {

	public Undying() {
		super("Undying", new TextureHolder(Assets.GetTexture("icon_undying")), null, 
				1, 0, 0, "You will reborn as skeleton once you are dead.");
		
	}

	@Override
	public void SkillInit(CharactherComponent cc) {
		cc.var.put("died", false);

	}

	@Override
	public void SkillUpdate(CharactherComponent cc, Engine en, float delta, PositionComponent pc, VelocityComponent vc,
			int index) {
		// TODO Auto-generated method stub

	}

	@Override
	public void SkillAIUpdate(CharactherComponent cc, AIComponent aic, Engine en, float delta, PositionComponent pc,
			VelocityComponent vc, int index) {
		// TODO Auto-generated method stub

	}

	@Override
	public void SkillRender(SpriteBatch batch, ShapeRenderer shapeRebderer, CharactherComponent cc,
			PositionComponent pc, int index) {
		// TODO Auto-generated method stub

	}

	@Override
	public void ActiveCall(CharactherComponent cc, PositionComponent pc, int index) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public boolean SkillDeadCall(CharactherComponent cc) {
		boolean died = (boolean) cc.var.get("died");
		if(died) {
			
			
			return true;
		}else {
			
			cc.race = Registry.RACES.Skeleton;
			
			cc.maxHealth = cc.race.baseHealth + (cc.race.baseHealth * cc.style.healthPercentMul / 100f);
			cc.maxEnergy = cc.race.baseEnergy + (cc.race.baseEnergy * cc.style.energyPercentMul / 100f);
			cc.health = cc.maxHealth;
			cc.energy = cc.maxEnergy;
			cc.speed = cc.race.baseSpeed + (cc.race.baseSpeed * cc.style.speedPercentMul / 100f);
			cc.strength = cc.race.baseStrength + (cc.race.baseStrength * cc.style.strengthPercentMul / 100f);
			cc.armor = cc.race.baseArmor + (cc.race.baseArmor * cc.style.armorPercentMul / 100f);
			cc.noMoveVisibility = cc.race.baseVisibility + (cc.race.baseVisibility * cc.style.visibilityPercentMul / 100f);
			cc.visibility = cc.noMoveVisibility;
			cc.vision = cc.race.baseVision + (cc.race.baseVision * cc.style.visionPercentMul / 100f);
			cc.healthRegen = cc.race.baseHealthRegen + (cc.race.baseHealthRegen * cc.style.healthRegenPercentMul / 100f);
			cc.energyRegen = cc.race.baseEnergyRegen + (cc.race.baseEnergyRegen * cc.style.energyRegenPercentMul / 100f);
			
			cc.var.put("died", true);
			return false;
		}
		
	}

}
