package com.alice.arena.data.races;

import com.alice.arena.components.AIComponent;
import com.alice.arena.components.CharactherComponent;
import com.alice.arena.components.PositionComponent;
import com.alice.arena.components.VelocityComponent;
import com.alice.arena.data.Race;
import com.alice.arena.data.Registry;
import com.alice.arena.data.Skill;
import com.alice.arena.data.StandartRaceAdapter;
import com.alice.arena.utils.Assets;
import com.alice.arena.utils.TextureHolder;
import com.alice.arena.utils.UtilFunctions;

public class Ghost extends StandartRaceAdapter {

	public Ghost() {
		super(4, "Ghost", new TextureHolder(Assets.GetTexture("ghost"), 16, 24), 32, 48, 
				100, 120, 1.5f, 2, 0,
				0.6f, 1, 3, 10, 
				Registry.SKILLS.HumanSpirit);
		// TODO Auto-generated constructor stub
	}

}
