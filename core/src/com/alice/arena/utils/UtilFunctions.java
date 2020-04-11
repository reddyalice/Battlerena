package com.alice.arena.utils;

import com.alice.arena.components.CharactherComponent;
import com.alice.arena.components.VelocityComponent;

public class UtilFunctions {
	
	public static void StandartRaceAnim(CharactherComponent cc, VelocityComponent vc, float deltaTime) {
		if(Math.pow(vc.x, 2) + Math.pow(vc.y, 2) != 0) {
			cc.raceTimeHolder	+= deltaTime * 10f;
			cc.raceAnimationStep =  15 + (int)(cc.raceTimeHolder ) % 3;
		}else
		{
			cc.raceAnimationStep = 0;
		}
	}
	
	public static void StandartStyleAnim(CharactherComponent cc, VelocityComponent vc, float deltaTime) {
			if(Math.pow(vc.x, 2) + Math.pow(vc.y, 2) != 0) {
				cc.styleTimeHolder	+= deltaTime * 10f;
				cc.styleAnimationStep =  15 + (int)(cc.styleTimeHolder ) % 3;
			}else
			{
				cc.styleAnimationStep = 0;
			}
	}

}
