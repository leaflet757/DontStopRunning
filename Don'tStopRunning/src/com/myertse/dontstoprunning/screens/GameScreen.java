package com.myertse.dontstoprunning.screens;

import java.util.List;
import java.util.Random;

import android.util.Log;

import com.myertse.dontstoprunning.Assets;
import com.myertse.dontstoprunning.InputWrapper;
import com.myertse.dontstoprunning.WorldManager;
import com.myertse.dontstoprunning.entities.DodgeEnemy;
import com.myertse.dontstoprunning.entities.JumpableEnemy;
import com.myertse.dontstoprunning.entities.MovingThing;
import com.myertse.dontstoprunning.entities.Player;
import com.myertse.dontstoprunning.enums.GameState;
import com.myertse.dontstoprunning.enums.PlayerMovementState;
import com.myertse.framework.Game;
import com.myertse.framework.Graphics;
import com.myertse.framework.Input;
import com.myertse.framework.Pixmap;
import com.myertse.framework.Screen;

public class GameScreen extends Screen {

	// Contains all information relevant to the player
	WorldManager worldManager;
	
	// Controls Game Specific Input
	InputWrapper inputWrapper;
	
	// Current state of the game
	GameState gameState;
	
	// Pause Screen
	PauseScreen pauseScreen;
	
	// Background Image
	Pixmap background = Assets.background;

	// THIS IS THE PLAYER IF YOU COULD NOT TELL
	Player player;

	// TODO test enemy
	Random rand;


	public GameScreen(Game game) {
		super(game);

		init();
	}

	private void init() {

		Graphics g = GAME.getGraphics();
		
		worldManager = new WorldManager(g.getWidth(), g.getHeight());

		inputWrapper = new InputWrapper(GAME.getInput(), g.getWidth(),
				g.getHeight());


		rand = new Random();
		
		player = new Player(worldManager.getLanes(), worldManager.getMiddleLane(),
				g.getHeight() - Assets.stepLeft.getHeight()
				- Assets.protaganistMid.getHeight());

		
		pauseScreen = new PauseScreen(GAME);
		
		
		gameState = GameState.STARTING;
	}

	@Override
	public void update(float deltaTime) {
		// TODO Auto-generated method stub
		Log.d("GameScreen", "updating...");

		inputWrapper.update(deltaTime);

		switch (gameState) {
		case STARTING:
			// TODO: reset game
			gameState = GameState.RUNNING;
			break;
		case GAME_OVER:
			Graphics g = GAME.getGraphics();
			g.drawPixmap(Assets.dead_text, 0, 0);
			break;
		case PAUSED:
			if (pauseScreen.isBackButtonPressed()) {
				gameState = GameState.RUNNING;
			} 
			else if (pauseScreen.isOptionsPressed()) {
				// TODO: change screen
			}
			else if (pauseScreen.isRestartPressed()) {
				gameState = GameState.STARTING;
			}
			
			break;
		case RUNNING:
			runGame(deltaTime);
			break;
		}

	}

	private void runGame(float deltaTime) {
		// TODO: should not be getting screen height here
		final int HEIGHT = GAME.getGraphics().getHeight();
		
		// get the latest player touch movement state
		PlayerMovementState state = inputWrapper.getPlayerMovementState();
		switch (state) {
		case ALTERNATING:
			Log.d("Input", "Alternating click");
			player.changeRunningImage();
			worldManager.incrementStepCount();
			break;
		case DOUBLETAP_LEFT:
			player.moveLeft();
			Assets.tap.play(1);
			Log.d("Input", "DoubleTap Left");
			break;
		case DOUBLETAP_RIGHT:
			player.moveRight();
			Assets.explosion.play(1);
			Log.d("Input", "Double Tap Right");
			break;
		case JUMPING:
			// jump action
			Log.d("Input", "Double Down");
			break;
		case STOPPING:
			// speed slows
			worldManager.decreaseSpeed();
			Log.d("Input", "Stopping - Nothing Pressed");
			break;
		case PAUSING:
			// pause button clicked
			gameState = GameState.PAUSED;
			Log.d("Input", "Player Paused Game");
			break;
		default:
			// if null
			Log.d("Input", "inputWrapper is null");
			break;
		}
		
		// Find current player speed & distance
		worldManager.update(deltaTime);
		worldManager.calcCurrentSpeed(deltaTime);
		worldManager.calcDistance(deltaTime);
		
		// Update all obstacles
		List<MovingThing> obstList = worldManager.getObstacles();
		for (int i = 0; i < obstList.size(); i++) {
			MovingThing obst = obstList.get(i);
			
			// TODO: set obstacle speed relative to the player's speed
			obst.setySpeed(worldManager.getCurrentSpeed());
			
			// Check for collisions
			if (player.collidesWith(obst)) {
				// gameState = GameState.GAME_OVER;
			}
			
			// TODO: check if obstacles are outside screen
			// if yes then mark the object for deletion
//			obst.update(deltaTime);
//			if (obst.getyPosition() > HEIGHT - Assets.stepLeft.getHeight()) {
//				obst.setyPosition(-obst.getImage().getHeight());
//				obst.setxPosition(worldManager.getLanes()[rand.nextInt(3)]);
//			}
		}

	}

	@Override
	public void present(float deltaTime) {
		Graphics g = GAME.getGraphics();
		g.clear(1);

		
		// Display Background Image
		g.drawPixmap(background, 0, 0);
		g.drawPixmap(Assets.pause_button, 0, 0);
		g.drawPixmap(Assets.step_counter, g.getWidth() - Assets.step_counter.getWidth(), 0);

		// Draw Obstacles and Enemies
		List<MovingThing> obstList = worldManager.getObstacles();
		for (int i = 0; i < obstList.size(); i++) {
			MovingThing obst = obstList.get(i);
			obst.draw(g);
		}

		// Display the player
		player.draw(g);
		Log.d("GameScreen", "The value of step Counter is " + worldManager.getStepCount());

		// Buttons and UI Drawing
		g.drawPixmap(Assets.stepLeft, 0,
				g.getHeight() - Assets.stepLeft.getHeight());
		g.drawPixmap(Assets.stepRight, g.getWidth() / 2, g.getHeight()
				- Assets.stepRight.getHeight());
		
		// Draw Distance and Speed
		g.drawText(200, 200, 20, "" + worldManager.getDistance());
		
		// Draw pause screen if paused
		if (gameState == GameState.PAUSED) {
			pauseScreen.drawPauseScreen(g);
		}
		
		
		Log.d("GameScreen", "presenting...");
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub		
		Log.d("GameScreen", "pausing...");
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		Log.d("GameScreen", "resuming...");
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		Log.d("GameScreen", "disposing...");
	}

}
