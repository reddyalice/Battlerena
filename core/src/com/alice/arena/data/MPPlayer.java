package com.alice.arena.data;

import com.badlogic.ashley.core.Entity;

public class MPPlayer {
	
	public String name;
	public Entity playerEntity;
	public String playerID;
	public float ping;
	
	public MPPlayer(String name, Entity playerEntity, String playerID, float ping) {
		super();
		this.name = name;
		this.playerEntity = playerEntity;
		this.playerID = playerID;
		this.ping = ping;
	}
	
	
	
}
