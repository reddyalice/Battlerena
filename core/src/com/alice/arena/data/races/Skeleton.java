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

public class Skeleton extends StandartRaceAdapter {

	public Skeleton() {
		super(6, "Skeleton", new TextureHolder(Assets.GetTexture("skeleton"), 16, 24), 32, 48, 
				100, 80, 1.2f, 6, 5,
				1, 0.8f, 3, 8, 
				Registry.SKILLS.OrcRage);
		
		this.show = false;
		
		// TODO Auto-generated constructor stub
	}

}
