package com.myertse.dontstoprunning;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import android.util.Log;

import com.myertse.dontstoprunning.entities.DodgeEnemy;
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
	private final int MAX_SPEED = 10;
	final int THRESHOLD = 3;
	float elapsedTime = 0;
	int displayDistance = 0;
	
	// Obstacle Information
	ArrayList<MovingThing> obstacles;
	SpawnerState spawnState;
	final int LEVEL_1 = 25; // TODO - We will have to change max speed and level markers
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
		elapsedAmount = 0;
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
				if (currentSpeed < MAX_SPEED) {
					setCurrentSpeed(currentSpeed + 1);
				}
				elapsedTime = 0;
			}
			previousStepCounter = stepCount;
			elapsedTime = 0;
		}
	}
	
	float elapsedAmount = 0;
	final int INTERVAL_AMOUNT = 1;
	float decAmount = 0.02f; // TODO: this is bad
	public void decreaseSpeed() {
		elapsedAmount += decAmount;
		if (elapsedAmount >= INTERVAL_AMOUNT) {
			if (currentSpeed > 0) {
				currentSpeed--;
			}
			elapsedAmount = 0;
		}
	}

	public void calcDistance(float deltaTime) {
		// TODO
		distance = distance + currentSpeed;
		displayDistance = distance/50;
	}

	public void update(float deltaTime) {
		
		if (displayDistance > LEVEL_1) {
			spawnState = SpawnerState.SINGLE_SPAWN;
			Log.d("LEVEL 1", "Less than 25");
		}
		else if (displayDistance > LEVEL_2) {
			Log.d("LEVEL 2", "Less than 75");
			decAmount += 0.01;
			spawnState = SpawnerState.MOVING_OBJECT_SPAWN;  
		}
		else if (displayDistance > LEVEL_3) {
			Log.d("LEVEL 3", "Less than 125");
			spawnState = SpawnerState.DOUBLE_OBJECT_SPAWN;
		}
		else if (displayDistance > LEVEL_4) {
			Log.d("LEVEL 4", "Less than 200");
			decAmount += 0.01;
			spawnState = SpawnerState.CHASM_SPAWN;
		}
		else if (displayDistance > LEVEL_5) {
			Log.d("LEVEL 5", "Less than 215");
			spawnState = SpawnerState.TRIPLE_OBJECT_SPAWN;
		}
		else if (displayDistance > LEVEL_6) {
			Log.d("LEVEL 6", "Less than 250");
			decAmount += 0.01;
			spawnState = SpawnerState.END_GAME_SPAWN;
		}
		// TODO: should game still get harder over time?
		
		
		
		// spawns an obstacle based on the spawning state
		if (objectShouldSpawn()) {
			//spawnObstacle();
		}
		
		// Calculate speed and distances
		calcCurrentSpeed(deltaTime);
		calcDistance(deltaTime);
	}

	private boolean objectShouldSpawn() {
		return true;
	}

	private void spawnObstacle() {
		if ((spawnState.getValue() & SpawnerState.SINGLE_SPAWN.getValue()) 
				== SpawnerState.SINGLE_SPAWN.getValue()) {
			if (obstacles.size() < 10)
			obstacles.add(new DodgeEnemy(Assets.dodge_enemy1, 0, 0, currentSpeed));
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
