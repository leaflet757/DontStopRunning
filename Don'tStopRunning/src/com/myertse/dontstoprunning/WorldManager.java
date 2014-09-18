package com.myertse.dontstoprunning;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.util.Log;

import com.myertse.dontstoprunning.entities.DodgeEnemy;
import com.myertse.dontstoprunning.entities.JumpableEnemy;
import com.myertse.dontstoprunning.entities.MovingThing;
import com.myertse.dontstoprunning.enums.SpawnerState;
import com.myertse.framework.impl.UniqueRandom;


public class WorldManager {
	
	// Lane & Screen Information
	final int SCREEN_WIDTH;
	final int SCREEN_HEIGHT;
	int xLeftLane;
	int xMidLane;
	int xRightLane;
	private int[] lanes;
	
	// high score tracker
	private int highScoreDistance = 0;
	
	// Player Information
	private int distance;
	private int stepCount;
	private int previousStepCounter;
	private int currentSpeed;
	private final int MAX_SPEED = 10;
	final int THRESHOLD = 3;
	float elapsedTime = 0;
	int displayDistance = 0;
	int previousDisplayDistance = 0;
	
	// Obstacle Information
	ArrayList<MovingThing> obstacleList;
	
	SpawnerState spawnState = SpawnerState.NONE;
	final int LEVEL_1 = 25; // TODO - We will have to change max speed and level markers
	final int LEVEL_2 = 75;
	final int LEVEL_3 = 125;
	final int LEVEL_4 = 200;
	final int LEVEL_5 = 215;
	final int LEVEL_6 = 250;
	
	Random randD;
	int distanceCounter;
	
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
		obstacleList = new ArrayList<MovingThing>(0);
		
		// initialize player information
		distance = 0;
		stepCount = 0;
		previousStepCounter = -1;
		currentSpeed = 0;
		
		randD = new Random();
		distanceCounter = LEVEL_1;
		previousDisplayDistance = 0;

	}
	
	public void addObstacle(MovingThing obj) {
		obstacleList.add(obj);
	}
	
	public List<MovingThing> getObstacles() {
		return obstacleList;
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
		distance = distance + currentSpeed;
		displayDistance = distance/50;
	}

	public void update(float deltaTime) {
		
		// update highscore if needed
		if (highScoreDistance < displayDistance) {
			highScoreDistance = displayDistance;
		}
		
		// check what level player is on
		if (displayDistance > LEVEL_6) {
			Log.d("LEVEL 6", "Greater than 250");
			spawnState = SpawnerState.END_GAME_SPAWN;
		}
		else if (displayDistance > LEVEL_5) {
			Log.d("LEVEL 5", "Greater than 215");
			spawnState = SpawnerState.TRIPLE_OBJECT_SPAWN;
		}
		else if (displayDistance > LEVEL_4) {
			Log.d("LEVEL 4", "Greater than 200");
			spawnState = SpawnerState.CHASM_SPAWN;
		}
		else if (displayDistance > LEVEL_3) {
			Log.d("LEVEL 3", "Greater than 125");
			spawnState = SpawnerState.DOUBLE_OBJECT_SPAWN;
		}
		else if (displayDistance > LEVEL_2) {
			Log.d("LEVEL 2", "Greater than 75");
			spawnState = SpawnerState.MOVING_OBJECT_SPAWN;
		}
		else if (displayDistance > LEVEL_1) {
			spawnState = SpawnerState.SINGLE_SPAWN;
			Log.d("LEVEL 1", "Greater than 25");
		}
		// TODO: should game still get harder over time?
		
		
		
		// spawns an obstacle based on the spawning state
		if (objectShouldSpawn()) {
			spawnObstacle();
		}
		
		// Calculate speed and distances
		calcCurrentSpeed(deltaTime);
		calcDistance(deltaTime);
	}

	/*
	 * A set of obstacles should spawn between 8-12 distance
	 */
	private boolean objectShouldSpawn() {
		if (displayDistance >= previousDisplayDistance + distanceCounter) {
			previousDisplayDistance = displayDistance;
			return true;
		} else {
			return false;
		}
	}

	public int getNextSpawnDistance() {
		return previousDisplayDistance + distanceCounter;
	}
	
	private void spawnObstacle() {
		distanceCounter = randD.nextInt(5) + 8;
		// TODO: still need to spawn obstacle
		//int numObstaclesToSpawn = spawnState.getValue();
		createObstacle();
	}

	private void createObstacle() {
		UniqueRandom random = new UniqueRandom(0, 2);
		int r = random.nextUniqueRandom();
		
		switch(spawnState) { // TODO: createt more obstacles
		case CHASM_SPAWN:
			addObstacle(new JumpableEnemy(lanes[r], -Assets.jump_enemy1.getHeight(), currentSpeed));
			r = random.nextUniqueRandom();
			addObstacle(new JumpableEnemy(lanes[r], -Assets.jump_enemy1.getHeight(), currentSpeed));
			break;
		case DOUBLE_OBJECT_SPAWN:
			addObstacle(new JumpableEnemy(lanes[r], -Assets.jump_enemy1.getHeight(), currentSpeed));
			r = random.nextUniqueRandom();
			addObstacle(new DodgeEnemy(lanes[r], -Assets.dodge_enemy2.getHeight(), currentSpeed));
			break;
		case END_GAME_SPAWN:
			addObstacle(new JumpableEnemy(lanes[r], -Assets.jump_enemy1.getHeight(), currentSpeed));
			r = random.nextUniqueRandom();
			addObstacle(new JumpableEnemy(lanes[r], -Assets.jump_enemy1.getHeight(), currentSpeed));
			r = random.nextUniqueRandom();
			addObstacle(new DodgeEnemy(lanes[r], -Assets.dodge_enemy2.getHeight(), currentSpeed));
			break;
		case MOVING_OBJECT_SPAWN:
			addObstacle(new JumpableEnemy(lanes[r], -Assets.dodge_enemy2.getHeight(), currentSpeed));
			break;
		case SINGLE_SPAWN:
			addObstacle(new JumpableEnemy(lanes[r], -Assets.jump_enemy1.getHeight(), currentSpeed));
			break;
		case TRIPLE_OBJECT_SPAWN:
			addObstacle(new JumpableEnemy(lanes[r], -Assets.jump_enemy1.getHeight(), currentSpeed));
			r = random.nextUniqueRandom();
			addObstacle(new JumpableEnemy(lanes[r], -Assets.jump_enemy1.getHeight(), currentSpeed));
			r = random.nextUniqueRandom();
			addObstacle(new JumpableEnemy(lanes[r], -Assets.jump_enemy1.getHeight(), currentSpeed));
			break;
		case NONE:
			break;
		}
	}

	public int getHighScoreDistance() {
		return highScoreDistance;
	}

	public void setHighScoreDistance(int highScoreDistance) {
		this.highScoreDistance = highScoreDistance;
	}

}
