package com.myertse.dontstoprunning;

import com.example.dontstoprunning.R;
import com.myertse.framework.impl.Graphics;
import com.myertse.framework.impl.InputController;
import com.myertse.framework.impl.Physics;
import com.myertse.framework.impl.GameScreen;
import com.myertse.framework.impl.Ticker;

import android.app.Activity;
import android.util.Log;

public class Game {

	Activity mainActivity;
	
	// TODO: Not all members are initialized 
	DataManager worldData;
	GameScreen screen;
	
	boolean isRunning;
	float prevTime;
	
	public Game(MainActivity mainActivity) {
		this.mainActivity = mainActivity;
		isRunning = true;
	}

	/**
	 * Initializes any preliminary files that the game may use
	 * and adds it to the GamaDataManager
	 */
	public void initialize() {
		// TODO load any preliminary files here like highscores
		// initilialize all systems
		
	}

	/**
	 * Loads all game content files and sets up a preliminary 
	 * loading screen if needed.
	 * All game content is then added to respective 
	 * initialized systems.
	 */
	public void loadContent() {
		// TODO load all nessisary graphics and sounds here
		// THIS WILL NOT STAY AS THE LAYOUT
		mainActivity.setContentView(R.layout.activity_main);
		
		prevTime = getCurrentTime();
	}

	/**
	 * Gets the Current time from the system clock
	 * in milliseconds
	 * 
	 * @return float - deltaTime
	 */
	private float getCurrentTime() {
		long nano = System.nanoTime();
		return (nano / 100000);
	}

	/**
	 * Main Game Loop
	 */
	public void run() {
		// TODO Main Game loop
		while (isRunning) {
			// check what part of the game we are on and act occordingly
			// will need to change this
			try {
				float deltaTime = getCurrentTime() - prevTime;
				// update all our systems
				InputController.update(deltaTime, screen); // view is the screen being used
				Physics.update(deltaTime, worldData);
				Graphics.update(deltaTime, screen); // sv or Frame
				Ticker.update(deltaTime);
				worldData.update(deltaTime); // manage score & distance & etc
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
				Log.e("InterruptedException", e.getMessage());
			}
			isRunning = false;
		}
	}

	/**
	 * Gets called when the game is closing.
	 */
	public void exit() {
		// TODO save files and delete memory and close the application
		
	}

	/**
	 * Gets called when the user switches to another 
	 * activity outside the game and the gamescreen
	 * is not visible
	 */
	public void onStop() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Gets called when the user re-enters this
	 * game activity
	 */
	public void onRestart() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Gets called when the user switches to another
	 * activity outside the program and the gamescreen
	 * is visible
	 */
	public void onPause() {
		// TODO Auto-generated method stub
		
	}

}
