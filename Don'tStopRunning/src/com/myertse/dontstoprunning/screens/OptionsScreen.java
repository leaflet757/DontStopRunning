package com.myertse.dontstoprunning.screens;

import android.util.Log;

import com.myertse.dontstoprunning.Assets;
import com.myertse.dontstoprunning.WorldManager;
import com.myertse.framework.Game;
import com.myertse.framework.Graphics;
import com.myertse.framework.Input;
import com.myertse.framework.Screen;

public class OptionsScreen extends Screen {
	
	Screen previousScreen;
	int backButtonX = 46;
	int backButtonY = 570;
	
	int soundButtonX = 43; int soundButtonY = 314;
	int soundButtonX2 = 417; int soundButtonY2 = 470;
	
	int musicButtonX = 44; int musicButtonY = 75;
	int musicButtonX2 = 417; int musicButtonY2 = 250;
	
	boolean released = false;
	public OptionsScreen(Game game, Screen screen) {
		super(game);
		previousScreen = screen;
	}

	@Override
	public void update(float deltaTime) {
		
		Input input = GAME.getInput();
		if(!input.isTouchDown(0))
		{
			released = true;
		}
		int xTouch = input.getTouchX(0);
		int yTouch = input.getTouchY(0);
		
		//Check for Back Button
		if(input.isTouchDown(0) &&
				xTouch >= backButtonX && xTouch <= backButtonX + Assets.options_back_button.getWidth() &&
				yTouch >= backButtonY && yTouch <= backButtonY + Assets.options_back_button.getWidth() &&
				released == true)
		{
			while(input.isTouchDown(0))
			{
				//wait for release
			}
			GAME.setScreen(previousScreen);
			previousScreen.resume();
			
			/**********KNOWN BUG: Options back button does not fire properly on time sometimes ******************/
		}
		
		//Check for Sound button
		if(input.isTouchDown(0) &&
				xTouch >= soundButtonX && xTouch <= soundButtonX2 &&
				yTouch >= soundButtonY && yTouch <= soundButtonY2 &&
				released == true)
		{
			while(input.isTouchDown(0))
			{
				//wait for release
			}
			if(WorldManager.sound)
			{
				WorldManager.sound = false;
			}
			else
			{
				WorldManager.sound = true;
			}
			Assets.tap.play(1);
		}
		
		//Check for Sound button
		if(input.isTouchDown(0) &&
				xTouch >= musicButtonX && xTouch <= musicButtonX2 &&
				yTouch >=  musicButtonY && yTouch <= musicButtonY2 &&
				released == true)
		{
			while(input.isTouchDown(0))
			{
				//wait for release
			}
			if(WorldManager.music)
			{
				WorldManager.music = false;
			}
			else
			{
				WorldManager.music = true;
			}
			Assets.explosion.play(1);
		}
	}

	@Override
	public void present(float deltaTime) {
		// TODO Auto-generated method stub
		Graphics g = GAME.getGraphics();
		g.clear(0);
		g.drawPixmap(Assets.options_menu, 0,0);
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
