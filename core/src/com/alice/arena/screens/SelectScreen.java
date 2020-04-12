package com.alice.arena.screens;

import java.util.ArrayList;

import com.alice.arena.Core;
import com.alice.arena.data.Race;
import com.alice.arena.data.Registry;
import com.alice.arena.data.Skill;
import com.alice.arena.data.Style;
import com.alice.arena.utils.Assets;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

public class SelectScreen implements Screen {

	public SpriteBatch batch;
	public int selectedRace;
	public int selectedStyle;
	int regionPointer = 0;
	public Texture selectionT;
	public ArrayList<Skill> selectedSkills = new ArrayList<Skill>();
	
	ExtendViewport viewport;
	OrthographicCamera camera;
	BitmapFont font;
	int n = 4;
	
	float timeHolder;
	
	public SelectScreen() {
		System.out.println(Registry.RACES.Human);
		System.out.println(Registry.STYLES.Warrior);
		selectionT = Assets.GetTexture("selectionBox");
	}
	
	
	@Override
	public void show() {
		batch = new SpriteBatch();
		font = new BitmapFont(); 
		
		camera = new OrthographicCamera();
		viewport =  new ExtendViewport(Core.WIDTH, Core.HEIGHT, camera);
		viewport.apply(true);
	}

	@Override
	public void render(float delta) {
		
		camera.update();
		Vector2 ms = viewport.unproject(new Vector2(Gdx.input.getX(), Gdx.input.getY()));
		ArrayList<Skill> skills = new ArrayList<Skill>();
		batch.begin();
		batch.setProjectionMatrix(camera.combined);
		batch.setColor(Color.WHITE);
		int i = 0;
		for(int k : Registry.raceList.keySet()) {

			float x = 30 + 3 * 32 * i;
			float y = 480 - 48 * 3;
			
			if(ms.x <= x + 32 * 3f && ms.x >= x 
					&& ms.y >= y && ms.y <= y + 48 * 3)
				if(Gdx.input.isButtonPressed(0))
				{
					selectedRace = k;
					selectedSkills.clear();

				}
			
			
			if(k == selectedRace)
			{
				batch.draw(selectionT, x, y, 32 * 3, 48 * 3);
				skills.addAll(Registry.raceList.get(k).raceSkills);
			}
			Registry.raceList.get(k).texture.Draw(batch, x,y, 32 * 3, 48 * 3, k == selectedRace ? regionPointer : 0, false, false, 0);
			font.draw(batch, Registry.raceList.get(k).name , x + 32 * 3f/2f - 20f , y + 48 * 2 + 20f);
			
			i++;
		}
		
		i = 0;
		for(int k : Registry.styleList.keySet()) {

			float x = 30 + 3 * 32 * i;
			float y = 480 - 48 * 3 * 2;
			
			if(ms.x <= x + 32 * 3f && ms.x >= x 
					&& ms.y >= y && ms.y <= y + 48 * 3)
				if(Gdx.input.isButtonPressed(0))
				{
					selectedStyle = k;
					selectedSkills.clear();
				}
			
			
			if(k == selectedStyle) {
				batch.draw(selectionT, x,y, 32 * 3, 48 * 3);
				skills.addAll(Registry.styleList.get(k).styleSkills);
			}
			
			Registry.raceList.get(selectedRace).texture.Draw(batch, x,y, 32 * 3, 48 * 3, k == selectedStyle ? regionPointer : 0, false, false, 0);
			Registry.styleList.get(k).texture.Draw(batch, x, y, 32 * 3, 48 * 3, k == selectedStyle ? regionPointer : 0, false, false, 0);
			font.draw(batch, Registry.styleList.get(k).name , x + 32 * 3f/2f - 20f , y + 48 * 2 + 20f);
			i++;
		}
		
		
		i = 0;
		for(Skill s : skills) {
			float x = 30 + 2* 32 * 2 * i;
			float y = 480 - 48 * 3 * 2 - 32 * 2 - 30f;
			
			if(ms.x <= x + 32 * 2f && ms.x >= x 
					&& ms.y >= y && ms.y <= y + 32 * 2)
				if(Gdx.input.isButtonPressed(0))
					if(selectedSkills.contains(s))
					{
						selectedSkills.remove(s);
						n++;
					}else
					{
						if(n > 0) {
							selectedSkills.add(s);
							n--;
						}
					}
			
			
			if(selectedSkills.contains(s))
				batch.draw(selectionT, x,y, 32 * 2, 32 * 2);

			font.draw(batch, s.name , x , y + 32f * 2f + 20f);
			s.iconTexture.Draw(batch, x, y, 32 * 2, 32 * 2, 0, false, false, 0);
			i++;
			
			
			
		}
		
		
		
		
		
		
		
		batch.end();
		
		
		timeHolder	+= delta * 10f;
		regionPointer =  15 + (int)(timeHolder ) % 3;
		
		
		if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER))
			Core.instance.setScreen(new PlayScreen(Registry.raceList.get(selectedRace), Registry.styleList.get(selectedStyle), selectedSkills.toArray(new Skill[0])));

	}

	@Override
	public void resize(int width, int height) {
		viewport.update(width, height, true);

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		batch.dispose();

	}

}
