package com.myertse.dontstoprunning.screens;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;

import com.myertse.dontstoprunning.Assets;
import com.myertse.dontstoprunning.InputWrapper;
import com.myertse.dontstoprunning.enums.PlayerMovementState;
import com.myertse.framework.Game;
import com.myertse.framework.Graphics;
import com.myertse.framework.Input.TouchEvent;
import com.myertse.framework.Pixmap;
import com.myertse.framework.Screen;
import com.myertse.framework.Sound;

public class GameScreen extends Screen {

	InputWrapper inputWrapper;
	
	
	//Boolean watch variable for left button
	boolean isLeftPressed = false;
	boolean isRightPressed = false;
	
	// Background Image
	Pixmap background = Assets.background;
	
	
	// TODO: MOVE TO SEPPARATE CLASS, PLAYER INFORMATION
	//the Pixmap to draw
	Pixmap assetToDraw = Assets.protaganistMid;
	// position
	int x = 0;
	int y = 0;
	// current lane
	int lane = 1;
	
	// lane & map information
	//location values
	int xLeftLane;
	int xMidLane;
	int xRightLane;
	int[] lanes = new int[3];
	
	
	// i dont know what these are for :D
	
	//The location of he player at the time the starting at zero
	//the player can move left (negative) or right, (positive)
	int location = 0;
	int lastLocation = 0;
	
	public GameScreen(Game game) {
		super(game);

		Graphics g = game.getGraphics();
		
		inputWrapper = new InputWrapper(game.getInput(),
				g.getWidth(), g.getHeight());
		
		
		// initialize map values
		xLeftLane = 0;
		xMidLane = g.getWidth()/3;
		xRightLane = g.getWidth() - g.getWidth()/3;
		lanes[0] = xLeftLane;
		lanes[1] = xMidLane;
		lanes[2] = xRightLane;
	}

	@Override
	public void update(float deltaTime) {
		// TODO Auto-generated method stub
		Log.d("GameScreen", "updating...");
		
		lastLocation = location;
		
		inputWrapper.update(deltaTime);
		
		// TODO: get the latest game touch action
		PlayerMovementState state = inputWrapper.getPlayerMovementState();
		switch (state) {
		case ALTERNATING:
			// increase speed
			Log.d("Input", "Alternating click");
			break;
		case DOUBLETAP_LEFT:
			if (lane > 0) lane--;
			isLeftPressed = true;
			Assets.tap.play(1);
			assetToDraw = Assets.protaganistLeft;
			Log.d("Input", "DoubleTap Left");
			break;
		case DOUBLETAP_RIGHT:
			if (lane < lanes.length-1) lane++;
			isRightPressed = true;
			Assets.explosion.play(1);
			assetToDraw = Assets.protaganistRight;
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
	
	}

	@Override
	public void present(float deltaTime) {
		Graphics g = GAME.getGraphics();
		g.clear(1);
		
		g.drawPixmap(background, 0, 0);

		//issue picture needs to scale		
		g.drawPixmap(Assets.stepLeft, 0, g.getHeight() - Assets.stepLeft.getHeight());
		g.drawPixmap(Assets.stepRight, g.getWidth()/2, g.getHeight() - Assets.stepRight.getHeight());

		
		
		// player x position
		x = lanes[lane];
		//player y position
		y = g.getHeight()/2;
		
		g.drawPixmap(assetToDraw, x, y);
		
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
