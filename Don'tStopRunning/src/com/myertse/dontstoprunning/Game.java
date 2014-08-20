package com.myertse.dontstoprunning;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

public class Game {

	Activity mainActivity;
	
	// TODO: Not all members are initialized 
	//DataManager worldData;

	FastRenderView renderScreen1;
	
	boolean isRunning;
	float prevTime;
	
	public Game(Activity mainActivity) {
		this.mainActivity = mainActivity;
		isRunning = true;
	}

	/**
	 * Initializes any preliminary files that the game may use
	 * and adds it to the GamaDataManager
	 */
	public void loadDependencies() {
		renderScreen1 = new FastRenderView(mainActivity);
		mainActivity.setContentView(renderScreen1);
	}

	/**
	 * Loads all game content files and sets up a preliminary 
	 * loading screen if needed.
	 * All game content is then added to respective 
	 * initialized systems.
	 */
	public void loadContent() {

	}

	/**
	 * Gets the Current time from the system clock
	 * in milliseconds
	 * 
	 * @return float - deltaTime
	 */
	public float getCurrentTime() {
		long nano = System.nanoTime();
		return (nano / 100000);
	}

	/**
	 * Main Game Loop
	 */
	public void run() {
		// TODO Main Game loop
		float timeToGet = this.getCurrentTime() + 4000;
		renderScreen1.resume();
		while (getCurrentTime() < timeToGet)
		{
			Log.d("Overlord", "Game is running");
		}
		
		Log.d("Overlord", "Finished Execution");
		//Log.d(this.getClass().toString(), "starting main thread");
		//while (isRunning) {
			
		//}
		//Log.d(this.getClass().toString(), "main thread finishing");
	}

	/**
	 * Gets called when the game is closing.
	 */
	public void exit() {
		// TODO save files and delete memory and close the application
		Log.d(this.getClass().toString(), "game exitting");
		isRunning = false;
		
		renderScreen1.pause();
	}

	/**
	 * Gets called when the user switches to another 
	 * activity outside the game and the gamescreen
	 * is not visible
	 */
	public void onStop() {
		// TODO Auto-generated method stub
		Log.d(this.getClass().toString(), "game stopping");
		isRunning = false;
	}

	/**
	 * Gets called when the user re-enters this
	 * game activity
	 */
	public void onRestart() {
		// TODO Auto-generated method stub
		Log.d(this.getClass().toString(), "game Restarting");
		isRunning = true;
	}

	/**
	 * Gets called when the user switches to another
	 * activity outside the program and the gamescreen
	 * is visible
	 */
	public void onPause() {
		// TODO Auto-generated method stub
		Log.d(this.getClass().toString(), "game pausing");
		isRunning = false;
	}
	
	class FastRenderView extends SurfaceView implements Runnable {
		Thread renderThread = null;
		SurfaceHolder holder;
		// use volatile when the order of something matters
		// this is mostly in the onPause Method
		// running must be set to false first otherwise
		// infinite loop
		volatile boolean running = false;

		public FastRenderView(Context context) {
			super(context);
			holder = getHolder();
		}

		public void resume() {
			running = true;
			renderThread = new Thread(this);
			renderThread.start();
			Log.d("thread", "starting thread");
		}

		public void run() {
			while (running) {
				if (holder == null) Log.d("thread", "holder does not exist");
				if (!holder.getSurface().isValid()) {
					Log.e("holder", "holder not valid");
					continue;
				}
				// if the surface is valid, it will always be valid until the
				// pause is called
				Canvas canvas = holder.lockCanvas();
				// this is where drawing goes
				canvas.drawRGB(255, 0, 0);
				Log.d("Iverkiadr", "draw thread running");
				holder.unlockCanvasAndPost(canvas);
			}
		}

		public void pause() {
			while (running) {
				try {
					running = false;
					renderThread.join();
				} catch (InterruptedException e) {
					running = true; // retry;
				}
			}
			Log.d("thread", "thread exiting");
		}
	}


}
