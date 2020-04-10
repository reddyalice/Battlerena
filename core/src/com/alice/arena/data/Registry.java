package com.alice.arena.data;

import java.util.HashMap;

import com.alice.arena.data.skills.*;
import com.alice.arena.data.races.*;
import com.alice.arena.data.styles.*;



public class Registry {
	
	public static HashMap<Integer, Race> raceList = new HashMap<Integer, Race>();
	public static HashMap<Integer, Style> styleList = new HashMap<Integer, Style>();
	public static HashMap<Integer, Item> itemList = new HashMap<Integer, Item>();
	
	
	public static class SKILLS{
		public static final Skill VeryNormal = new VeryNormal();
		public static final Skill WarriorSword = new WarriorSword();
		public static final Skill WarriorSheild = new WarriorShield();
		public static final Skill HumanSpirit = new HumanSpirit();
	}
	
	public static class RACES{
		public static final Race Human = new Human();
	}
	
	public static class STYLES{
		public static final Style Warrior = new Warrior();
	}
	
	public static class ITEMS{
		
	}

	
}
