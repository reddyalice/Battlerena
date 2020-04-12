package com.alice.arena;

import java.io.Serializable;
import java.util.HashMap;

import com.alice.arena.data.Registry;

@SuppressWarnings("serial")
public class Account implements Serializable{
	
	public String name;
	public int level;
	public int[] inventory = new int[256];
	public HashMap<Integer, Integer> raceLevels = new HashMap<Integer, Integer>();
	public HashMap<Integer, Integer> styleLevels = new HashMap<Integer, Integer>();
	
	public Account(String name) {
		this.name = name;
		level = 1;
		for(int raceK : Registry.raceList.keySet())
			raceLevels.put(raceK, 1);
		for(int styleK : Registry.styleList.keySet())
			styleLevels.put(styleK, 1);
	}
	
	
	
	
}
