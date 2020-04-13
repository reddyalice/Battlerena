package com.alice.arena.utils;

import com.alice.arena.components.CharactherComponent;
import com.alice.arena.components.VelocityComponent;

public class UtilFunctions {
	
	public static void StandartRaceAnim(CharactherComponent cc, VelocityComponent vc, float deltaTime) {
		if(vc.speed2 != 0) {
			cc.raceTimeHolder	+= deltaTime * 10f;
			cc.raceAnimationStep =  15 + (int)(cc.raceTimeHolder ) % 3;
		}else
		{
			
			if(cc.idleTime > 0) {
				cc.raceAnimationStep = 0;
			
			}else {
				cc.raceTimeHolder	+= deltaTime * 10f;
				cc.raceAnimationStep =  8 + (int)(cc.raceTimeHolder ) % 3;
			
			}
			
		}
	}
	
	public static void StandartStyleAnim(CharactherComponent cc, VelocityComponent vc, float deltaTime) {
			if(vc.speed2 != 0) {
				cc.styleTimeHolder	+= deltaTime * 10f;
				cc.styleAnimationStep =  15 + (int)(cc.styleTimeHolder ) % 3;
				
			}else
			{
				if(cc.idleTime > 0) {
					cc.styleAnimationStep = 0;
			
				}
				else{
					
					cc.styleTimeHolder	+= deltaTime * 10f;
					cc.styleAnimationStep =  8 + (int)(cc.styleTimeHolder ) % 3;
					
				}
				
			}
	}

}
