package com.myertse.dontstoprunning;

import java.util.ArrayList;
import java.util.List;

import com.myertse.dontstoprunning.entities.MovingThing;


public class WorldManager {
	
	int distance;
	int stepCount;
	float currentSpeed;
	
	ArrayList<MovingThing> obstacles;
	
	public WorldManager( ) {
		obstacles = new ArrayList<MovingThing>();
	}
	
	public void addObstacle(MovingThing obj) {
		obstacles.add(obj);
	}
	
	public List<MovingThing> getObstacles() {
		return obstacles;
	}
}
