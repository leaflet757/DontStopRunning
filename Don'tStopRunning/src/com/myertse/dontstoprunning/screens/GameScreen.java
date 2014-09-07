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
import com.myertse.framework.Pixmap;
import com.myertse.framework.Screen;

public class GameScreen extends Screen {

	// Contains all information relevant to the player
	WorldManager worldManager;
	
	// Controls Game Specific Input
	InputWrapper inputWrapper;
	
	// Current state of the game
	GameState gameState;
	
	// Background Image
	Pixmap background = Assets.background;

	// THIS IS THE PLAYER IF YOU COULD NOT TELL
	Player player;

	// TODO test enemy
	Random rand;
	
	final int THRESHOLD = 3;
	float elapsedTime = 0;

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

		// TODO: will eliminate this after testing
		// 	     need more advanced spawner
		DodgeEnemy enemy = new DodgeEnemy(Assets.dodge_enemy1, worldManager.getLanes()[0],
				-Assets.dodge_enemy1.getHeight(), 1);
		worldManager.addObstacle(enemy);
		JumpableEnemy bigEnemy = new JumpableEnemy(Assets.dodge_enemy2, worldManager.getLanes()[1],
				-Assets.dodge_enemy2.getHeight(), 10);
		worldManager.addObstacle(bigEnemy);

		gameState = GameState.STARTING;
	}

	@Override
	public void update(float deltaTime) {
		// TODO Auto-generated method stub
		Log.d("GameScreen", "updating...");

		inputWrapper.update(deltaTime);

		switch (gameState) {
		case STARTING:
			gameState = GameState.RUNNING;
			break;
		case GAME_OVER:
			Graphics g = GAME.getGraphics();
			g.drawPixmap(Assets.dead_text, 0, 0);
			break;
		case PAUSED:
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
			player.step();
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
			Log.d("Input", "Stopping - Nothing Pressed");
			break;
		case PAUSING:
			// pause button clicked
			Log.d("Input", "Player Paused Game");
			break;
		default:
			// if null
			Log.d("Input", "inputWrapper is null");
			break;
		}
		
		// TODO: find current player speed
		elapsedTime += deltaTime;
		if(elapsedTime > 1000)
		{
			if(worldManager.getStepCount() > worldManager.getPreviousStepCounter() + THRESHOLD )
			{
				player.setSpeed((player.getSpeed() + 1));
				elapsedTime = 0;
			}
			worldManager.setPreviousStepCounter(worldManager.getStepCount());
			elapsedTime = 0;
		}
		
		// Update all obstacles
		List<MovingThing> obstList = worldManager.getObstacles();
		for (int i = 0; i < obstList.size(); i++) {
			MovingThing obst = obstList.get(i);
			
			// TODO: set obstacle speed relative to the player's speed
			//obst.setySpeed(player.getySpeed());
			
			// Check for collisions
			if (player.collidesWith(obst)) {
				// gameState = GameState.GAME_OVER;
			}
			
			// check if obstacles are outside screen
			obst.update(deltaTime);
			if (obst.getyPosition() > HEIGHT - Assets.stepLeft.getHeight()) {
				obst.setyPosition(-obst.getImage().getHeight());
				obst.setxPosition(worldManager.getLanes()[rand.nextInt(3)]);
			}
		}

	}

	@Override
	public void present(float deltaTime) {
		Graphics g = GAME.getGraphics();
		g.clear(1);

		if(worldManager.getStepCount() < 1)
		{
			g.drawPixmap(Assets.title_page, 0, 0);
			return;
		}		
		//g.drawPixmap(Assets.pause_button, x, y)
		
		// Display Background Image
		g.drawPixmap(background, 0, 0);

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
