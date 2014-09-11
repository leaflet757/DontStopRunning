package com.myertse.dontstoprunning;

import java.util.ArrayList;
import java.util.List;

import com.myertse.dontstoprunning.entities.MovingThing;
import com.myertse.dontstoprunning.enums.SpawnerState;


public class WorldManager {
	
	// Lane & Screen Information
	final int SCREEN_WIDTH;
	final int SCREEN_HEIGHT;
	int xLeftLane;
	int xMidLane;
	int xRightLane;
	private int[] lanes;
	
	// Player Information
	private int distance;
	private int stepCount;
	private int previousStepCounter;
	private int currentSpeed;
	final int THRESHOLD = 3;
	float elapsedTime = 0;
	int displayDistance = 0;
	
	// Obstacle Information
	ArrayList<MovingThing> obstacles;
	SpawnerState spawnState;
	final int LEVEL_1 = 25;
	final int LEVEL_2 = 75;
	final int LEVEL_3 = 125;
	final int LEVEL_4 = 200;
	final int LEVEL_5 = 215;
	final int LEVEL_6 = 250;
	
	
	public WorldManager(int width, int height ) {
		
		SCREEN_WIDTH = width;
		SCREEN_HEIGHT = height;
		
		// initialize map values
		xLeftLane = 0;
		xMidLane = width / 3;
		xRightLane = width - width / 3;
		lanes = new int[3];
		lanes[0] = xLeftLane;
		lanes[1] = xMidLane;
		lanes[2] = xRightLane;
		
		// initialize obstacles
		obstacles = new ArrayList<MovingThing>();
		
		// initialize player information
		distance = 0;
		stepCount = 0;
		previousStepCounter = -1;
		currentSpeed = 0;
	}
	
	public void addObstacle(MovingThing obj) {
		obstacles.add(obj);
	}
	
	public List<MovingThing> getObstacles() {
		return obstacles;
	}

	public int getStepCount() {
		return stepCount;
	}

	public void incrementStepCount( ) {
		this.stepCount++;
	}

	public int getDistance() {
		return displayDistance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public int getPreviousStepCounter() {
		return previousStepCounter;
	}

	public void setPreviousStepCounter(int previousStepCounter) {
		this.previousStepCounter = previousStepCounter;
	}
	
	public int getMiddleLane() {
		return xMidLane;
	}

	public int[] getLanes() {
		return lanes;
	}

	public int getCurrentSpeed() {
		return currentSpeed;
	}

	public void setCurrentSpeed(int currentSpeed) {
		this.currentSpeed = currentSpeed;
	}

	public void calcCurrentSpeed(float deltaTime) {
		elapsedTime += deltaTime;
		if(elapsedTime > 1000)
		{
			if(stepCount > previousStepCounter + THRESHOLD )
			{
				setCurrentSpeed(currentSpeed + 1);
				elapsedTime = 0;
			}
			previousStepCounter = currentSpeed;
			elapsedTime = 0;
		}
	}
	
	float elapsedAmount = 0;
	final int INTERVAL_AMOUNT = 1;
	final float decAmount = 0.001f; // TODO: this is bad
	public void decreaseSpeed() {
		if (currentSpeed > 1) {
			currentSpeed--;
		}
//		elapsedAmount += decAmount;
//		if (elapsedAmount >= INTERVAL_AMOUNT) {
//			if (currentSpeed > 1) {
//				currentSpeed--;
//			}
//			elapsedAmount = 0;
//		}
	}

	public void calcDistance(float deltaTime) {
		// TODO
		distance = distance + currentSpeed;
		displayDistance = distance/50;
	}

	public void update(float deltaTime) {
		
		if (displayDistance > LEVEL_1) {
			spawnState.setValue(SpawnerState.SINGLE_SPAWN.getValue());
		}
		if (displayDistance > LEVEL_2) {
			spawnState.setValue(spawnState.getValue() | SpawnerState.MOVING_OBJECT_SPAWN.getValue());  
		}
		if (displayDistance > LEVEL_3) {
			spawnState.setValue(spawnState.getValue() | SpawnerState.DOUBLE_OBJECT_SPAWN.getValue());
		}
		if (displayDistance > LEVEL_4) {
			spawnState.setValue(spawnState.getValue() | SpawnerState.CHASM_SPAWN.getValue());
		}
		if (displayDistance > LEVEL_5) {
			spawnState.setValue(spawnState.getValue() | SpawnerState.TRIPLE_OBJECT_SPAWN.getValue());
		}
		if (displayDistance > LEVEL_6) {
			spawnState.setValue(spawnState.getValue() | SpawnerState.END_GAME_SPAWN.getValue());
		}
		
		// spawns an obstacle based on the spawning state
		if (objectShouldSpawn()) {
			spawnObstacle();
		}
		
		// Calculate speed and distances
		calcCurrentSpeed(deltaTime);
		calcDistance(deltaTime);
	}

	private boolean objectShouldSpawn() {
		// TODO Auto-generated method stub
		return false;
	}

	private void spawnObstacle() {
		if ((spawnState.getValue() & SpawnerState.SINGLE_SPAWN.getValue()) 
				== SpawnerState.SINGLE_SPAWN.getValue()) {
			
		}
		if ((spawnState.getValue() & SpawnerState.CHASM_SPAWN.getValue()) 
				== SpawnerState.CHASM_SPAWN.getValue()) {
			
		}
		if ((spawnState.getValue() & SpawnerState.DOUBLE_OBJECT_SPAWN.getValue()) 
				== SpawnerState.END_GAME_SPAWN.getValue()) {
			
		}
		if ((spawnState.getValue() & SpawnerState.MOVING_OBJECT_SPAWN.getValue()) 
				== SpawnerState.MOVING_OBJECT_SPAWN.getValue()) {
			
		}
		if ((spawnState.getValue() & SpawnerState.TRIPLE_OBJECT_SPAWN.getValue()) 
				== SpawnerState.TRIPLE_OBJECT_SPAWN.getValue()) {
			
		}
	}
}
