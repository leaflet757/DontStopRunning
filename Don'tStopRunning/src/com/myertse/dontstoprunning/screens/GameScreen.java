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
	int x = 0;
	
	//will be hardocded eventually, y value should not move
	int y = 0;
	
	//The location of he player at the time the starting at zero
	//the player can move left (negative) or right, (positive)
	int location = 0;
	int lastLocation = 0;
	
	public GameScreen(Game game) {
		super(game);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(float deltaTime) {
		// TODO Auto-generated method stub
		Log.d("GameScreen", "updating...");
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
			break;
		case DOUBLETAP_LEFT:
			location--;
			isLeftPressed = true;
			break;
		case DOUBLETAP_RIGHT:
			location++;
			isRightPressed = true;
			break;
		case JUMPING:
			// jump action
			break;
		case STOPPING:
			// speed slows
			break;
		case STOPPED:
			// game over
			break;
		}
		
		if(GAME.getInput().isTouchDown(0))
		{
			x = GAME.getInput().getTouchX(0);
			y = GAME.getInput().getTouchY(0);
			if(x < (g.getWidth()/2) && y > (g.getHeight() - g.getHeight()/4))
			{
				location--;
				isLeftPressed = true;
			}
			if(x > (g.getWidth()/2) && y > (g.getHeight() - g.getHeight()/4))
			{
				location++;
				isRightPressed = true;
			}
		
			//Reaction if
			if(isLeftPressed && isRightPressed)
			{
				//jump condition
			}
			else if(isLeftPressed)
			{
				isRightPressed = false;
			}
			else
			{
				isLeftPressed = false;
			}
			//g.drawPixmap(Assets.protaganistMid, x, y);
			
		}
		else
		{
			
		}
		
	}

	@Override
	public void present(float deltaTime) {
		Graphics g = GAME.getGraphics();
		// TODO Auto-generated method stub
		Pixmap assetToDraw = Assets.protaganistMid;
		if(location < 0)
		{
			
			assetToDraw = Assets.protaganistLeft;
			x = 0;
			//force minimum cap limit
			location = -1;
		}
		else if(location > 0)
		{
			assetToDraw = Assets.protaganistRight;
			x = g.getWidth() - g.getWidth()/3;
			//force max cap limit
			location = 1;
		}
		else
		{
			x = g.getWidth()/3;
		}
		if(lastLocation != location)
		{
			Assets.tap.play(1);
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
