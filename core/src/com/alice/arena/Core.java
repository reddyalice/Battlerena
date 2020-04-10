package com.alice.arena;

import java.util.ArrayList;

import com.alice.arena.components.AIComponent;
import com.alice.arena.components.CharactherComponent;
import com.alice.arena.components.MPComponent;
import com.alice.arena.components.PlayerComponent;
import com.alice.arena.components.PositionComponent;
import com.alice.arena.components.VelocityComponent;
import com.alice.arena.data.Race;
import com.alice.arena.data.Registry;
import com.alice.arena.data.Skill;
import com.alice.arena.data.Style;
import com.alice.arena.screens.PlayScreen;
import com.alice.arena.utils.Assets;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;


public class Core extends Game {

	public static Color backgroundColor = Color.BLUE;
	public static Core instance;
	public static float deltaTime = 1f/60f;
	
	
	@Override
	public void create () {
		instance = this;
		setScreen(new PlayScreen(Registry.RACES.Human, Registry.STYLES.Warrior, Registry.SKILLS.WarriorSheild, 
				Registry.SKILLS.WarriorSword,Registry.SKILLS.VeryNormal, Registry.SKILLS.HumanSpirit));
	}

	@Override
	public void render () {
		deltaTime = Gdx.graphics.getDeltaTime();
		Gdx.gl.glClearColor(backgroundColor.r, backgroundColor.g, backgroundColor.b, backgroundColor.a);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		super.render();
		
	}
	
	@Override
	public void dispose () {
		Assets.Dispose();
		super.dispose();
	}
	
	private static Entity CreateCharactherEntity(Race race, Style style,Skill... skills ) {
		
		Entity e = new Entity();
		CharactherComponent cc = new CharactherComponent();
		

		
		
		
		cc.race = race;
		cc.style = style;
		cc.skill = skills;
		cc.progress = new float[cc.skill.length];
		cc.maxHealth = race.baseHealth + (race.baseHealth * style.healthPercentMul / 100f);
		cc.maxEnergy = race.baseEnergy + (race.baseEnergy * style.energyPercentMul / 100f);
		cc.health = cc.maxHealth;
		cc.energy = cc.maxEnergy;
		cc.speed = race.baseSpeed + (race.baseSpeed * style.speedPercentMul / 100f);
		cc.strength = race.baseStrength + (race.baseStrength * style.strengthPercentMul / 100f);
		cc.armor = race.baseArmor + (race.baseArmor * style.armorPercentMul / 100f);
		cc.visibility = race.baseVisibility + (race.baseVisibility * style.visibilityPercentMul / 100f);
		cc.healthRegen = race.baseHealthRegen + (race.baseHealthRegen * style.healthRegenPercentMul / 100f);
		cc.energyRegen = race.baseEnergyRegen + (race.baseEnergyRegen * style.energyRegenPercentMul / 100f);
		cc.lookDir = new Vector2(1,0);
		cc.rotation = 0;
		
		
		
		e.add(cc);
		e.add(new VelocityComponent());
		cc.race.RacialInit(cc);
		cc.style.StyleInit(cc);
		for(Skill s : cc.skill)
			s.SkillInit(cc);
		
		
		return e;
		
		
		
	}
	
	
	public static Entity SpawnPlayerCharacther(float x, float y, Race race, Style style,Skill... skills ) {
		Entity e = CreateCharactherEntity(race, style, skills);
		e.add(new PositionComponent(x,y));
		e.add(new PlayerComponent());
		return e;
	}
	
	
	public static Entity SpawnAICharacther(float x, float y, Race race, Style style,Skill... skills ) {
		Entity e = CreateCharactherEntity(race, style, skills);
		e.add(new PositionComponent(x,y));
		e.add(new AIComponent());
		return e;
	}
	
	
	public static Entity SpawnMPCharacther(float x, float y, Race race, Style style,Skill... skills )
	{
		Entity e = CreateCharactherEntity(race, style, skills);
		e.add(new PositionComponent(x,y));
		e.add(new MPComponent());
		return e;
	}
	
	public static Entity SpawnRandomAICharacther(float x, float y, int level) {
		int r = (int)(Math.random() * Registry.raceList.size());
		int c = (int)(Math.random() * Registry.styleList.size());
		ArrayList<Skill> rcs = new ArrayList<Skill>();
		ArrayList<Skill> scs = new ArrayList<Skill>();
		Race ra = Registry.raceList.values().toArray(new Race[0])[r];
		Style sa = Registry.styleList.values().toArray(new Style[0])[c];
		ArrayList<Skill> skills =  new ArrayList<Skill>();
		
		
		
		for(Skill skill : ra.raceSkills)
		{
			if(skill.level <= level)
				rcs.add(skill);
		}
		
		for(Skill skill : sa.styleSkills)
		{
			if(skill.level <= level)
				scs.add(skill);
		}
		
		for(int i = rcs.size() - 1; i > rcs.size() - 3; i--)
		{
			if(i < 0) break;
			skills.add(rcs.get(i));
		}
		

		for(int i = scs.size() - 1; i > scs.size() - 3; i--)
		{
			if(i < 0) break;
			skills.add(scs.get(i));
		}
		
		return SpawnAICharacther(x, y, ra, sa, skills.toArray(new Skill[0]));
		

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
