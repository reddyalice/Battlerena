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
import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.graphics.Texture;

public class Warrior extends StandartStyleAdapter {

	public Warrior() {
		super(0, "Warrior", new TextureHolder(Assets.GetTexture("warrior"), 16, 24), 32, 48, 
				10, 0, -10, 20, 
				20, 0, 0, 0, 0,
				Registry.SKILLS.WarriorSword,
				Registry.SKILLS.WarriorSheild);
	}
}
