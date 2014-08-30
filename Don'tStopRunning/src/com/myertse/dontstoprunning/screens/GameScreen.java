package com.myertse.dontstoprunning.screens;

import android.util.Log;

import com.myertse.dontstoprunning.Assets;
import com.myertse.framework.Game;
import com.myertse.framework.Graphics;
import com.myertse.framework.Pixmap;
import com.myertse.framework.Screen;
import com.myertse.framework.Sound;

public class GameScreen extends Screen {

	enum GameState { RUNNING, PAUSED, GAME_OVER }
	//Boolean watch variable for left button
	boolean isLeftPressed = false;
	boolean isRightPressed = false;
	
	//the Pixmap to draw
	Pixmap assetToDraw = Assets.protaganistMid;
	
	//where the person is touching
	int touchX = -1;
	int touchY = -1;
	
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
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(float deltaTime) {
		// TODO Auto-generated method stub
		Log.d("GameScreen", "updating...");
		Graphics g = GAME.getGraphics();
		g.clear(0);
		
		
		
		//issue picture needs to scale		
		g.drawPixmap(Assets.stepLeft, 0, g.getHeight() - Assets.stepLeft.getHeight());
		g.drawPixmap(Assets.stepRight, g.getWidth()/2, g.getHeight() - Assets.stepRight.getHeight());
		
		lastLocation = location;
		if(GAME.getInput().isTouchDown(0))
		{
			
			touchX = GAME.getInput().getTouchX(0);
			touchY = GAME.getInput().getTouchY(0);
			if(touchX < (g.getWidth()/2) && touchY > (g.getHeight() - g.getHeight()/4))
			{	
				location--;
				isLeftPressed = true;
			}
			if(touchX > (g.getWidth()/2) && touchY > (g.getHeight() - g.getHeight()/4))
			{
				location++;
				isRightPressed = true;
			}
		
			//Reaction if
			if(isLeftPressed && isRightPressed)
			{
				//jump condition
			}
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
