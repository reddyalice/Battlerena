package com.alice.arena.components;

import java.util.ArrayList;

import com.alice.arena.ai.AIState;
import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.ai.pfa.GraphPath;
import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public class AIComponent implements Component {
	public AIState state = AIState.LookAround;
	public Entity target;
	public int nodeIndex;
	public GraphPath<Connection<Vector2>> outPath;
}
