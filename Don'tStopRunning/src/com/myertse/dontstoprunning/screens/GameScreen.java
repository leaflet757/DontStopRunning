package com.myertse.dontstoprunning.screens;

import java.util.List;
import java.util.Random;

import android.util.Log;

import com.myertse.HighScoreManager;
import com.myertse.dontstoprunning.Assets;
import com.myertse.dontstoprunning.InputWrapper;
import com.myertse.dontstoprunning.WorldManager;
import com.myertse.dontstoprunning.entities.DodgeEnemy;
import com.myertse.dontstoprunning.entities.JumpableEnemy;
import com.myertse.dontstoprunning.entities.MovingThing;
import com.myertse.dontstoprunning.entities.Player;
import com.myertse.dontstoprunning.enums.Enemy_type;
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
	
	// HighScoreManager Handles FileIO
	HighScoreManager highScoreManager;
	
	// Controls Game Specific Input
	InputWrapper inputWrapper;
	
	// Current state of the game
	GameState gameState;
	
	// Pause Screen
	PauseScreen pauseScreen;
	
	// Background Image
	Pixmap background1 = Assets.background;
	Pixmap background2 = Assets.background;
	int bgx1 = 0;
	int bgy1 = 0;
	int bgx2 = 0;
	int bgy2 = 0;

	// THIS IS THE PLAYER IF YOU COULD NOT TELL
	Player player;
	
	//To see if he is alive, I swear
	boolean alive = true;

	//To see if we should have sound effects
	boolean sound;
	
	//To see if we should have background music
	boolean music;

	public GameScreen(Game game) {
		super(game);
		
		init();
	}

	private void init() {

		Graphics g = GAME.getGraphics();
		
		worldManager = new WorldManager(g.getWidth(), g.getHeight());
		
		highScoreManager = new HighScoreManager(GAME);

		inputWrapper = new InputWrapper(GAME.getInput(), g.getWidth(),
				g.getHeight());
		
		player = new Player(worldManager.getLanes(), worldManager.getMiddleLane(),
				g.getHeight() - Assets.stepLeft.getHeight()
				- Assets.protaganistMid.getHeight());
		
		pauseScreen = new PauseScreen(GAME);
		
		bgy1 = 0;
		bgy2 = bgy1 - background2.getHeight();
		
		gameState = GameState.STARTING;
		music  = WorldManager.music;
		if(music)
		{
			Assets.backgroundMusic.play();
			Assets.backgroundMusic.setLooping(true);
		}
	}

	@Override
	public void update(float deltaTime) {

		Log.d("GameScreen", "updating...");

		if(worldManager.getCurrentSpeed() < 2
				&& worldManager.getDistance() > 10 && alive)
		{
			gameState = GameState.DYING;
		}
		
		
		if(player.isJumping())
		{
		}
		else
		{
			inputWrapper.update(deltaTime);
		}

		switch (gameState) {
		case STARTING:
			worldManager.setHighScoreDistance(highScoreManager.gethighScore());
			gameState = GameState.RUNNING;
			break;
		case GAME_OVER:
			if(alive)
			{
				gameState = GameState.DYING;
				break;
			}
			
			if (worldManager.getHighScoreDistance() > highScoreManager.gethighScore()) {
				highScoreManager.saveHighScore(worldManager.getHighScoreDistance());
			}
			GAME.setScreen(new EndScreen(GAME, highScoreManager, worldManager));
			gameState = GameState.STARTING;
			alive = true;
			break;
		case PAUSED:
			if (pauseScreen.isBackButtonPressed()) {
				gameState = GameState.RUNNING;
			}
			else if (pauseScreen.isOptionsButtonPressed()) {
				GAME.setScreen(new OptionsScreen(GAME, this));
			}
			else if (pauseScreen.isRestartButtonPressed()) {
				// save if new high score
				if (worldManager.getHighScoreDistance() > highScoreManager.gethighScore()) {
					highScoreManager.saveHighScore(worldManager.getHighScoreDistance());
				}
				GAME.setScreen(new GameScreen(GAME)); // TODO: I don't know how I feel about this... MAN UP
			}
			break;
		case RUNNING:
			runGame(deltaTime);
			break;
			
		case DYING:
			alive = false;
			if(player.die())
			{
				gameState = GameState.GAME_OVER;
			}
			break;
		}
		

	}

	private void runGame(float deltaTime) {
		// TODO: should not be getting screen height here
		final int HEIGHT = GAME.getGraphics().getHeight();
		
		// get the latest player touch movement state
		PlayerMovementState state = inputWrapper.getPlayerMovementState();
		if(player.isJumping())
			state = state.JUMPING;
		if(alive)
		switch (state) {
		case ALTERNATING:
			Log.d("Input", "Alternating click");
			player.changeRunningImage();
			worldManager.incrementStepCount();
			break;
		case DOUBLETAP_LEFT:
			player.moveLeft();
			sound = WorldManager.sound;
			if(sound)
				Assets.tap.play(1);
			Log.d("Input", "DoubleTap Left");
			break;
		case DOUBLETAP_RIGHT:
			player.moveRight();
			sound = WorldManager.sound;
			if(sound)
				Assets.explosion.play(1);
			Log.d("Input", "Double Tap Right");
			break;
		case JUMPING:
			// jump action
			inputWrapper.setLastButtonPressed(inputWrapper.getLastButtonPressed());
			player.jump();
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
		
		// update the player
		player.update(deltaTime);
		
		// Update the world
		worldManager.update(deltaTime);
		
		// Update the background
		bgy1 += worldManager.getCurrentSpeed();
		bgy2 += worldManager.getCurrentSpeed();
		
		// Update all obstacles
		List<MovingThing> obstList = worldManager.getObstacles();
		for (int i = 0; i < obstList.size(); i++) {
			MovingThing obst = obstList.get(i);
			
			// set obstacle speed relative to the player's speed
			obst.setySpeed(worldManager.getCurrentSpeed());
			
			// Check for collisions
			if (player.collidesWith(obst)) {
				gameState = GameState.GAME_OVER;
				if (player.isJumping() &&
					(obst.getType() == Enemy_type.CHASM ||
					obst.getType() == Enemy_type.JUMPABLE)) {
					gameState = GameState.RUNNING;
				}
			}
			
			// TODO: check if obstacles are outside screen
			// if yes then mark the object for deletion
			obst.update(deltaTime);
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

		// update background
		if (bgy1 >= g.getHeight()) {
			bgy1 = bgy2 - background1.getHeight();
		}
		if (bgy2 >= g.getHeight()) {
			bgy2 = bgy1 - background2.getHeight();
		}
		
		// Display Background Image
		g.drawPixmap(background1, bgx1, bgy1);
		g.drawPixmap(background2, bgx2, bgy2);
		
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
		
		//Draw Speed Bar
		g.drawPixmap( Assets.speed_bar, g.getWidth() - Assets.speed_bar.getWidth(), g.getHeight()/4);
		int locationOfSpeed = worldManager.getCurrentSpeed();
		locationOfSpeed = (g.getHeight()/4) + Assets.speed_bar.getHeight() - Assets.speed_bar_arrow.getHeight()
				- (worldManager.getCurrentSpeed() * 20);
		g.drawPixmap(Assets.speed_bar_arrow, g.getWidth()- Assets.speed_bar_arrow.getWidth() - 2, locationOfSpeed);
		
		
		// Draw Distance and Speed and High Score
		//g.drawText(200, 200, 20, "" + worldManager.getDistance());
		//g.drawText(250, 200, 20, "" + highScoreManager.gethighScore());
		g.drawText(g.getWidth() - Assets.step_counter.getWidth() + 3,
				Assets.step_counter.getHeight()/2, 20, "" + worldManager.getDistance());
		//g.drawText(200, 260, 32, "" + worldManager.getNextSpawnDistance());
		
		// Draw pause screen if paused
		if (gameState == GameState.PAUSED) {
			pauseScreen.drawPauseScreen(g);
		}
		
		
		Log.d("GameScreen", "presenting...");
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

		if (worldManager.getHighScoreDistance() > highScoreManager.gethighScore()) {
			highScoreManager.saveHighScore(worldManager.getHighScoreDistance());
		}
		if(Assets.backgroundMusic.isPlaying())
		{
			Assets.backgroundMusic.pause();
		}
		
		Log.d("GameScreen", "pausing...");
	}

	@Override
	public void resume() {
		
		if(!Assets.backgroundMusic.isPlaying() && worldManager.music)
		{
			Assets.backgroundMusic.play();
		}
		
		Log.d("GameScreen", "resuming...");
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		Log.d("GameScreen", "disposing...");
	}

}
