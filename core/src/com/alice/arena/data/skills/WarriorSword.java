package com.alice.arena.data.skills;

import com.alice.arena.components.CharactherComponent;
import com.alice.arena.components.PositionComponent;
import com.alice.arena.components.VelocityComponent;
import com.alice.arena.data.Registry;
import com.alice.arena.data.Skill;
import com.alice.arena.screens.PlayScreen;
import com.alice.arena.utils.Assets;
import com.alice.arena.utils.Builder;
import com.alice.arena.utils.TextureHolder;
import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class WarriorSword extends Skill {

	private int rotationLimit = 90;
	private float rotSpeed = 450;
	private float damage = 10f;
	
	public WarriorSword() {
		super("Warrior's Sword", new TextureHolder(Assets.GetTexture("sword")),  new TextureHolder(Assets.GetTexture("sword")), 1, 0.5f, 5f, "A Warrior's Trustworthy Sword");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void SkillInit(CharactherComponent cc) {
		cc.var.put("swingSword", false);
		cc.var.put("swordPosX", 0f);
		cc.var.put("swordPosY", 0f);
		cc.var.put("swordRot", 0f);
		Body b =  Builder.CreateASimpleBody(BodyType.DynamicBody, 0f, 0f, 33, 6, 0, 0, "sword" +  "/" + cc.team, true);
		b.setBullet(true);
		cc.var.put("swordBody",b);
		
		if(!PlayScreen.beginContantCalls.consumers.containsKey("swordHit"))
			PlayScreen.beginContantCalls.Add("swordHit", x -> {
				String fA = (String) x.getFixtureA().getUserData();
				String fB = (String) x.getFixtureB().getUserData();
				
				
				if(fA.startsWith("sword")) {
					
					String[] p = fA.split("/");
					String team = p[1];
					
					if(fB.startsWith("char")) {
						String[] ct = fB.split("/");
						String charTeam = ct[1];
						int id = Integer.parseInt(ct[2]);
						if(!charTeam.contentEquals(team))
						{
							Registry.chars.get(id).getComponent(CharactherComponent.class).health -= damage;
						}
						
					}
					
					
				}
				
				if(fB.startsWith("sword")) {
					
					String[] p = fB.split("/");
					String team = p[1];
					
					if(fA.startsWith("char")) {
						String[] ct = fA.split("/");
						String charTeam = ct[1];
						int id = Integer.parseInt(ct[2]);
						if(!charTeam.contentEquals(team))
						{
							Registry.chars.get(id).getComponent(CharactherComponent.class).health -= damage;
						}
						
					}
				}
				
				
				
				
			});
		
		
		
	}

	@Override
	public void SkillUpdate(CharactherComponent cc, Engine en, float delta, PositionComponent pc, VelocityComponent vc, int index) {
		boolean swing = (boolean)cc.var.get("swingSword");
		float rot = (float)cc.var.get("swordRot");
		Body body = (Body)cc.var.get("swordBody");
		if(swing)
		{
			body.setActive(true);
			Vector2 angleTov;
			if(rot <= rotationLimit) {
				rot += delta * rotSpeed;
				
				cc.var.put("swordRot", rot);
				rot -= 45f;
				angleTov = new Vector2(-(float)(Math.cos((cc.rotation + (cc.flip ? rot  :  180f-rot) ) * Math.PI / 180f )), (float)(Math.sin((cc.rotation + (cc.flip ? rot  :  180f-rot) ) * Math.PI / 180f)));
				body.setTransform(pc.x + cc.race.width / 2f + angleTov.x * 24f * (cc.flip ? 1 : -1), pc.y + cc.race.height / 2f - 16f + (angleTov.y * 24f) * (cc.flip ? - 1 : 1), (float)((cc.rotation + (cc.flip ? rot  :  180f-rot) ) * Math.PI / 180f) );
			
				
			}else {
				cc.var.put("swordRot", 0f);
				body.setTransform(pc.x + cc.race.width / 2f, pc.y + cc.race.height / 2f - 16f, 0 );
				cc.var.put("swingSword", false);
			}
			
			cc.var.put("swordPosX", pc.x + cc.race.width / 2f - 32f);
			cc.var.put("swordPosY", pc.y + cc.race.height / 2f - 16f);
			
		}else {
			body.setActive(false);

			cc.var.put("swordPosX", pc.x + cc.race.width / 2f - 16f);
			cc.var.put("swordPosY", pc.y + cc.race.height / 2f - 30f + 20f);

			body.setTransform( (pc.x + cc.race.width / 2f - 16f) + 16f,(pc.y + cc.race.height / 2f - 30f + 20f), (float)((cc.flip ?  Math.PI : Math.PI / 2f) - Math.PI / 4f));
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
			texture.Draw(batch, x, y, 32,0, 32, 32, 0, false, false,   cc.rotation + (cc.flip ? rot  :  180f-rot - 90f ));
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

	@Override
	public void SkillAIUpdate(CharactherComponent cc, Engine en, float delta, PositionComponent pc,
			VelocityComponent vc, int index) {
		// TODO Auto-generated method stub
		
	}





}
