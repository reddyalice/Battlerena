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

public class Orc extends StandartRaceAdapter {

	public Orc() {
		super(1, "Orc", new TextureHolder(Assets.GetTexture("orc"),16,24), 32, 48, 
				200, 100, 0.9f, 3, 3, 
				2, 1, 3, 10, 
				Registry.SKILLS.OrcRage);
		// TODO Auto-generated constructor stub
	}

}
