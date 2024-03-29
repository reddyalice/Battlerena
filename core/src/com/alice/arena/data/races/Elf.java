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

public class Elf extends StandartRaceAdapter {

	public Elf() {
		super(3, "Elf", new TextureHolder(Assets.GetTexture("elf"), 16, 24), 32, 48, 
				100, 150, 1, 1, 1,
				1, 1.5f, 5, 10, 
				Registry.SKILLS.NatureSpirit);
		// TODO Auto-generated constructor stub
	}

}
