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
	
	//the Pixmap to draw
	Pixmap assetToDraw = Assets.protaganistMid;
	
	//where the person is touching
	int touchX = 0;
	int touchY = 0;
	
	int x = 0;
	
	//the lane that the character is currently in
	int lane = 1;
	
	//will be hardocded eventually, y value should not move
	int y = 0;
	
	//The location of he player at the time the starting at zero
	//the player can move left (negative) or right, (positive)
	int location = 0;
	int lastLocation = 0;
	
	public GameScreen(Game game) {
		super(game);

		inputWrapper = new InputWrapper(game.getInput(), 
				game.getGraphics().getWidth(), game.getGraphics().getHeight());
	}

	@Override
	public void update(float deltaTime) {
		// TODO Auto-generated method stub
		Log.d("GameScreen", "updating...");
		
		inputWrapper.update(deltaTime);
		
		
		Graphics g = GAME.getGraphics();
		g.clear(1);

		//issue picture needs to scale		
		g.drawPixmap(Assets.stepLeft, 0, g.getHeight() - Assets.stepLeft.getHeight());
		g.drawPixmap(Assets.stepRight, g.getWidth()/2, g.getHeight() - Assets.stepRight.getHeight());
		
		lastLocation = location;
		
		// TODO: get the latest game touch action
		PlayerMovementState state = inputWrapper.getPlayerMovementState();
		switch (state) {
		case ALTERNATING:
			// increase speed
			Log.d("Input", "Alternating click");
			break;
		case DOUBLETAP_LEFT:
			location--;
			isLeftPressed = true;
			Log.d("Input", "DoubleTap Left");
			break;
		case DOUBLETAP_RIGHT:
			location++;
			isRightPressed = true;
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
		// TODO Auto-generated method stub
		

		//Hard coded location values
		
				int xLeftLane = 0;
				int xMidLane = g.getWidth()/3;
				int xRightLane = g.getWidth() - g.getWidth()/3;
				int[] lanes = {xLeftLane, xMidLane, xRightLane};
		if(isLeftPressed)
		{
			Assets.tap.play(1);
			assetToDraw = Assets.protaganistLeft;
			if(location < -1)
			{
				if(lane > 0)
				{
					lane--;
				}
				location = 0;
			}
			
			isLeftPressed = false;
			
			//debug purposes
			try
			{
				Thread.sleep(500);
			}
			catch(Exception e)
			{
			}
			
		}
		else if(isRightPressed)
		{
			Assets.explosion.play(1);
			assetToDraw = Assets.protaganistRight;
			if(location > 1)
			{
				if(lane < 2)
				{
					lane++;
				}
				location = 0;
			}
			isRightPressed = false;
			
			//debug purposes
			try
			{
				Thread.sleep(500);
			}
			catch(Exception e)
			{
			}
		}
		else
		{
			//assetToDraw = Assets.protaganistMid;
		}
		x = lanes[lane];
		
		if(lastLocation != location)
		{
			//Assets.tap.play(1);
		}
		//hard code height
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
