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

public class Mage extends StandartStyleAdapter {

	public Mage() {
		super(2, "Mage", new TextureHolder(Assets.GetTexture("mage"), 16, 24), 32, 48, 
				-30, 20, -10, 0,
				-10, 20, 50, 15, 20,
				Registry.SKILLS.ShootArrow);
		// TODO Auto-generated constructor stub
	}


}
