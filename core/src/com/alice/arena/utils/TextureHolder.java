package com.alice.arena.utils;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TextureHolder {
	public TextureRegion[] textureRegions;
	
	public TextureHolder(Texture texture, int regionWidth, int regionHeight) {
		this.textureRegions = new TextureRegion[(int)((texture.getWidth() /  regionWidth)  * ( texture.getHeight() / regionHeight))];
		for(int x = 0; x < texture.getWidth() / regionWidth; x++) 
			for(int y = 0; y < texture.getHeight() / regionHeight; y++)
				this.textureRegions[y * (texture.getWidth() / regionWidth) + x] = 
				new TextureRegion(texture, x * regionWidth, y * regionHeight, 
						 regionWidth,  regionHeight);
	}
	
	
	public TextureHolder(Texture texture) {
		this(texture, texture.getWidth(), texture.getHeight());
	}
	
	
	public void Draw(SpriteBatch batch, float x, float y, int width, int height, int regionPointer, boolean flipX, boolean flipY, float rotation) {
		batch.draw(textureRegions[regionPointer], flipX ? x+width : x, flipY ? y+height : y,  width/2f ,  flipY ? -height / 2f : height / 2f , flipX ? -width : width, flipY ? -height : height, 1f,1f, rotation);
	}
	
	

}
