package com.alice.arena.data.styles;

import com.alice.arena.components.AIComponent;
import com.alice.arena.components.CharactherComponent;
import com.alice.arena.components.PositionComponent;
import com.alice.arena.components.VelocityComponent;
import com.alice.arena.data.Registry;
import com.alice.arena.data.Skill;
import com.alice.arena.data.StandartStyleAdapter;
import com.alice.arena.data.Style;
import com.alice.arena.utils.Assets;
import com.alice.arena.utils.TextureHolder;
import com.alice.arena.utils.UtilFunctions;

public class Summoner extends StandartStyleAdapter {

	public Summoner() {
		super(6, "Summoner", new TextureHolder(Assets.GetTexture("summoner"), 16, 24), 32, 48, 
				0, 0, -20, 0,
				0, 0, 0, 0, 20,
				Registry.SKILLS.ShootArrow);
		// TODO Auto-generated constructor stub
	}

}
