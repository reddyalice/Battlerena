package com.alice.arena.ai;

import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.ai.pfa.Heuristic;
import com.badlogic.gdx.math.Vector2;

public class AnyHeuristic implements Heuristic<Vector2> {

	private InGraph graph;
	public AnyHeuristic(InGraph graph) {
		this.graph = graph;
	}

	@Override
	public float estimate(Vector2 node, Vector2 endNode) {
		Vector2 diff = (new Vector2(node).sub(endNode));
		diff.x = (int)(diff.x);
		diff.y = (int)(diff.y);
		Vector2 pos = new Vector2(node);
		int cost = 0;
		int xA = (int)Math.abs(diff.x);
		int yA = (int)Math.abs(diff.y);
		int dX = (int) (diff.x / xA);
		int dY = (int) (diff.y / yA);
		if(xA > yA) {
			
			
			int cr = dX == dY ? (dX == -1 ? 1 : 5) : (dX == -1 ? 7 : 3); 
			int dio = dX == -1 ? 0 : 4;
			
			for(int i = 0; i < yA; i++) {
				Connection<Vector2> con = graph.getConnections(pos).get(cr);
				cost += con.getCost();
				pos = con.getToNode();
			}
			
			
			for(int i = 0; i < xA; i++) {
				Connection<Vector2> con = graph.getConnections(pos).get(dio);
				cost += con.getCost();
				pos = con.getToNode();
			}
			
			
			
		}else if(diff.y > diff.x)
		{
			
			int cr = dX == dY ? (dX == -1 ? 1 : 5) : (dX == -1 ? 7 : 3); 
			int dio = dY == -1 ? 2 : 6;
			
			for(int i = 0; i < xA; i++) {
				Connection<Vector2> con = graph.getConnections(pos).get(cr);
				cost += con.getCost();
				pos = con.getToNode();
			}
			
			
			for(int i = 0; i < yA; i++) {
				Connection<Vector2> con = graph.getConnections(pos).get(dio);
				cost += con.getCost();
				pos = con.getToNode();
			}
			
			
		}else if(diff.x == diff.y) {
			
			int cr = dX == dY ? (dX == -1 ? 1 : 5) : (dX == -1 ? 7 : 3); 
			
			for(int i = 0; i < xA; i++) {
				Connection<Vector2> con = graph.getConnections(pos).get(cr);
				cost += con.getCost();
				pos = con.getToNode();
			}
			
			
		}
		
		
		
		return cost;
	}

}
