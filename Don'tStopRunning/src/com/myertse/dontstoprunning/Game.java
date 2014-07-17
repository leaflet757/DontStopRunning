package com.myertse.dontstoprunning;

import com.example.dontstoprunning.R;

import android.app.Activity;

public class Game {

	Activity mainActivity;
	
	boolean isRunning;
	
	public Game(MainActivity mainActivity) {
		this.mainActivity = mainActivity;
		isRunning = true;
	}

	public void initialize() {
		// TODO load any preliminary files here like highscores
		// THIS WILL NOT STAY AS THE LAYOUT
		mainActivity.setContentView(R.layout.activity_main);
	}

	public void loadContent() {
		// TODO load all nessisary graphics and sounds here
		
	}

	public void play() {
		// TODO Main Game loop
		while (isRunning) {
			// check what part of the game we are on and act occordingly
			isRunning = false;
		}
	}

	public void exit() {
		// TODO save files and delete memory and close the application
		
	}

}
