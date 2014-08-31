package com.myertse.dontstoprunning.screens;

import java.util.Random;

import android.util.Log;

import com.myertse.dontstoprunning.Assets;
import com.myertse.dontstoprunning.InputWrapper;
import com.myertse.dontstoprunning.entities.DodgeEnemy;
import com.myertse.dontstoprunning.entities.Player;
import com.myertse.dontstoprunning.enums.PlayerMovementState;
import com.myertse.framework.Game;
import com.myertse.framework.Graphics;
import com.myertse.framework.Pixmap;
import com.myertse.framework.Screen;

public class GameScreen extends Screen {

	InputWrapper inputWrapper;
	
	
	//Boolean watch variable for left button
	boolean isLeftPressed = false;
	boolean isRightPressed = false;
	
	// Background Image
	Pixmap background = Assets.background;
	
	
	// TODO: MOVE TO SEPPARATE CLASS, PLAYER INFORMATION
	//the Pixmap to draw
	Player player;
	
	// TODO test enemy
	DodgeEnemy enemy;
	Random rand;
	
	// lane & map information
	//location values
	int xLeftLane;
	int xMidLane;
	int xRightLane;
	int[] lanes = new int[3];
	
	
	public GameScreen(Game game) {
		super(game);

		init();
	}
	
	private void init() {
		Graphics g = GAME.getGraphics();
		
		inputWrapper = new InputWrapper(GAME.getInput(),
				g.getWidth(), g.getHeight());
		
		
		// initialize map values
		xLeftLane = 0;
		xMidLane = g.getWidth()/3;
		xRightLane = g.getWidth() - g.getWidth()/3;
		lanes[0] = xLeftLane;
		lanes[1] = xMidLane;
		lanes[2] = xRightLane;
		
		rand = new Random();
		
		player = new Player(lanes, xMidLane, g.getHeight()
					-Assets.stepLeft.getHeight()
					-Assets.protaganistMid.getHeight());
		
		enemy = new DodgeEnemy(Assets.playerShip, lanes[0], -Assets.playerShip.getHeight(), 20);
	}

	@Override
	public void update(float deltaTime) {
		// TODO Auto-generated method stub
		Log.d("GameScreen", "updating...");
		
		inputWrapper.update(deltaTime);
		
		// TODO: get the latest game touch action
		PlayerMovementState state = inputWrapper.getPlayerMovementState();
		switch (state) {
		case ALTERNATING:
			// increase speed
			Log.d("Input", "Alternating click");
			player.changeRunningImage();
			break;
		case DOUBLETAP_LEFT:
			player.moveLeft();
			isLeftPressed = true;
			Assets.tap.play(1);
			//assetToDraw = Assets.protaganistLeft;
			Log.d("Input", "DoubleTap Left");
			break;
		case DOUBLETAP_RIGHT:
			player.moveRight();
			isRightPressed = true;
			Assets.explosion.play(1);
			//assetToDraw = Assets.protaganistRight;
			Log.d("Input", "Double Tap Right");
			break;
		case JUMPING:
			// jump action
			Log.d("Input", "Double Down");
			break;
		case STOPPING:
			// speed slows
			Log.d("Input", "Nothing Pressed");
			break;
		case STOPPED:
			// game over
			Log.d("Input", "Player Not Moving");
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
		
		enemy.update(deltaTime);
		if (enemy.getyPosition() > GAME.getGraphics().getHeight() - Assets.stepLeft.getHeight()) {
			enemy.setyPosition(-enemy.getImage().getHeight());
			enemy.setxPosition(lanes[rand.nextInt(3)]);
		}
	
	}

	@Override
	public void present(float deltaTime) {
		Graphics g = GAME.getGraphics();
		g.clear(1);
		
		g.drawPixmap(background, 0, 0);
		
		enemy.draw(g);
		//issue picture needs to scale		
		g.drawPixmap(Assets.stepLeft, 0, g.getHeight() - Assets.stepLeft.getHeight());
		g.drawPixmap(Assets.stepRight, g.getWidth()/2, g.getHeight() - Assets.stepRight.getHeight());
		
		player.draw(g);
		
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
