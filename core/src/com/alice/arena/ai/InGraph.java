package com.alice.arena.ai;

import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.ai.pfa.indexed.IndexedGraph;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class InGraph implements IndexedGraph<Vector2> {

	public InGraph() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Array<Connection<Vector2>> getConnections(Vector2 fromNode) {
		Array<Connection<Vector2>> connections = new Array<Connection<Vector2>>(8);
		connections.add(new Direction(fromNode, new Vector2(-1f, 0f)));
		connections.add(new Direction(fromNode, new Vector2(-1f, -1f)));
		connections.add(new Direction(fromNode, new Vector2(0, -1f)));
		connections.add(new Direction(fromNode, new Vector2(1f, -1f)));
		connections.add(new Direction(fromNode, new Vector2(1f, 0f)));
		connections.add(new Direction(fromNode, new Vector2(1f, 1f)));
		connections.add(new Direction(fromNode, new Vector2(0f, 1f)));
		connections.add(new Direction(fromNode, new Vector2(-1f, 1f)));
		return connections;
	}

	@Override
	public int getIndex(Vector2 node) {
		
		return node.hashCode();
	}

	@Override
	public int getNodeCount() {
		// TODO Auto-generated method stub
		return 0;
	}


}
