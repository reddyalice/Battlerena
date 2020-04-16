package com.alice.arena.data;

import java.util.HashMap;

import com.alice.arena.data.skills.*;
import com.alice.arena.components.CharactherComponent;
import com.alice.arena.data.races.*;
import com.alice.arena.data.styles.*;
import com.badlogic.ashley.core.Entity;



public class Registry {
	
	public static HashMap<Integer, Race> raceList = new HashMap<Integer, Race>();
	public static HashMap<Integer, Style> styleList = new HashMap<Integer, Style>();
	public static HashMap<Integer, Item> itemList = new HashMap<Integer, Item>();
	public static HashMap<Integer,  Entity> chars = new HashMap<Integer, Entity>();
	public static HashMap<String, Entity> teamMembers = new HashMap<String, Entity>();
	
	public static class SKILLS{
		public static final Skill VeryNormal = new VeryNormal();
		public static final Skill WarriorSword = new WarriorSword();
		public static final Skill WarriorSheild = new WarriorShield();
		public static final Skill HumanSpirit = new HumanSpirit();
		public static final Skill OrcRage = new OrcRage();
		public static final Skill ShootArrow = new ShootArrow();
		public static final Skill NatureSpirit = new NatureSpirit();
		public static final Skill RaiseDead = new RaiseDead();
		public static final Skill CallAid = new CallAid();
	}
	
	public static class RACES{
		public static final Race Human = new Human();
		public static final Race Orc = new Orc();
		public static final Race Glitch = new Glitch();
		public static final Race Elf = new Elf();
		public static final Race Ghost = new Ghost();
	}
	
	public static class STYLES{
		public static final Style Warrior = new Warrior();
		public static final Style Hunter = new Hunter();
		public static final Style Mage = new Mage();
		public static final Style Commander = new Commander();
		public static final Style Necromancer = new Necromancer();
		public static final Style Assassin = new Assassin();
	}
	
	public static class ITEMS{
		
	}

	
}
