package com.alice.arena.utils;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

public class Assets {
	
	private static TmxMapLoader mapLoader = new TmxMapLoader();
	public static HashMap<String, Texture> textures = new HashMap<String, Texture>();
	public static HashMap<String, Sound> sounds = new HashMap<String,Sound>();
	public static HashMap<String, Music> musics = new HashMap<String,Music>();
	public static HashMap<String,TiledMap> maps = new HashMap<String, TiledMap>();
	public static HashMap<String, BitmapFont> fonts = new HashMap<String, BitmapFont>();
	
	public static void AddTexture(String name) {
		if(textures.containsKey(name))
		{
			System.out.println("Texture with a " + name + " already exist!");
		}
		else {
			try{
				textures.put(name, new Texture("textures/" + name + ".png"));
			}catch(Exception e) {
				System.out.println(e.getMessage());
				throw new NoSuchMethodError();
			}
			
		}
	}
	
	
	public static void AddFont(String name) {
		try {
			fonts.put(name, new BitmapFont(Gdx.files.internal("fonts/" + name  + ".fnt"), Gdx.files.internal("fonts/" + name  + ".png"), false));
		}catch(Exception e) {
			System.out.println("Font file doesnt exist at " + "fonts/" + name  + ".fnt");
			throw new NoSuchMethodError();
		}
	}
	
	
	public static void AddMap(String name) {
		try {
			maps.put(name, mapLoader.load("maps/" + name + ".tmx"));
		}catch(Exception e) {
			System.out.println("Map file doesnt exist at " + "maps/" + name  + ".tmx");
			throw new NoSuchMethodError();
		}
	}
	
	
	public static TiledMap GetMap(String name) {
		if(maps.containsKey(name))
			return maps.get(name);
		else
		{
			try {
				Assets.AddMap(name);
				return GetMap(name);
			}catch(Exception e) {
			System.out.println("Font with name " + name + " doesn't exist!");
			return null;
			}
		}
	}
	
	public static BitmapFont GetFont(String name) {
		if(fonts.containsKey(name))
			return fonts.get(name);
		else
		{
			try {
				Assets.AddFont(name);
				return GetFont(name);
			}catch(Exception e) {
			System.out.println("Font with name " + name + " doesn't exist!");
			return null;
			}
		}
	}
	
	
	public static Texture GetTexture(String name) {
		if(textures.containsKey(name))
			return textures.get(name);
		else
		{
			try {
				Assets.AddTexture(name);
				return GetTexture(name);
			}catch(Exception e) {
			System.out.println("Texture with name " + name + " doesn't exist!");
			return null;
			}
		}
	}
	
	
	public static void Dispose()
	{
		for(String name : textures.keySet()) 
			textures.get(name).dispose();
		textures.clear();
		for(String name : fonts.keySet()) 
			fonts.get(name).dispose();
	}
	
	
	
	
	
}
