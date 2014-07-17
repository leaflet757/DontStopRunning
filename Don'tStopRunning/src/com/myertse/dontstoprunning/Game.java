package com.myertse.dontstoprunning;

import com.example.dontstoprunning.R;
import com.myertse.framework.impl.Graphics;
import com.myertse.framework.impl.InputController;
import com.myertse.framework.impl.Physics;
import com.myertse.framework.impl.Screen;
import com.myertse.framework.impl.Ticker;

import android.app.Activity;
import android.util.Log;

public class Game {

	Activity mainActivity;
	
	// TODO: Not all members are initialized 
	DataManager worldData;
	Screen screen;
	
	boolean isRunning;
	float prevTime;
	
	public Game(MainActivity mainActivity) {
		this.mainActivity = mainActivity;
		isRunning = true;
	}

	public void initialize() {
		// TODO load any preliminary files here like highscores
		// initilialize all systems
		
	}

	public void loadContent() {
		// TODO load all nessisary graphics and sounds here
		// THIS WILL NOT STAY AS THE LAYOUT
		mainActivity.setContentView(R.layout.activity_main);
		
		prevTime = getDeltaTime();
	}

	private float getDeltaTime() {
		long nano = System.nanoTime();
		return (nano / 100000);
	}

	public void run() {
		// TODO Main Game loop
		while (isRunning) {
			// check what part of the game we are on and act occordingly
			// will need to change this
			try {
				float deltaTime = getDeltaTime() - prevTime;
				// update all our systems
				InputController.update(deltaTime, screen); // view is the screen being used
				worldData.update(deltaTime); // manage score & distance & etc
				Physics.update(deltaTime, worldData);
				Graphics.update(deltaTime, screen); // sv or Frame
				Ticker.update(deltaTime);
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
				Log.e("InterruptedException", e.getMessage());
			}
			isRunning = false;
		}
	}

	public void exit() {
		// TODO save files and delete memory and close the application
		
	}

}
