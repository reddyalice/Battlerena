package com.alice.arena.data;

import com.badlogic.gdx.graphics.Texture;

public abstract class Item {
	
	public int id;
	public Texture texture;
	
	public Item(int id, Texture texture) {
		this.id = id;
		this.texture = texture;
		Registry.itemList.put(id, this);
	}
	
	

}
