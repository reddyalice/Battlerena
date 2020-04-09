package com.alice.arena;

import java.io.Serializable;
import java.util.HashMap;

@SuppressWarnings("serial")
public class Account implements Serializable{
	
	public int level;
	public int[] inventory = new int[256];
	public HashMap<Integer, Integer> raceLevels;
	public HashMap<Integer, Integer> styleLevels;
	
	
	
}
