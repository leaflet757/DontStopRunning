package com.myertse.dontstoprunning.screens;

import com.myertse.dontstoprunning.Assets;
import com.myertse.framework.Game;
import com.myertse.framework.Graphics;
import com.myertse.framework.Input;
import com.myertse.framework.Screen;

public class PauseScreen {
	
	Game game;
	int backButtonX = 146;
	int backButtonY = 467;
	
	int tryAgainX = 136;
	int tryAgainY = 471;
	
	int optionsX = 139;
	int optionsY = 239;
	//36,271 try again
	//39,39 options
	
	
	boolean released = false;
	
	public PauseScreen(Game game) {
		this.game = game;
	}

	public boolean isBackButtonPressed() {
		Input input = game.getInput();
		if(!input.isTouchDown(0))
		{
			released = true;
		}
		int xTouch = input.getTouchX(0);
		int yTouch = input.getTouchY(0);
		
		if(input.isTouchDown(0) &&
				xTouch >= backButtonX && xTouch <= backButtonX + Assets.pause_back_button.getWidth() &&
				yTouch >= backButtonY && yTouch <= backButtonY + Assets.pause_back_button.getHeight() &&
				released == true)
		{
			released = false;
			return true;
		}
		
		return false;
	}
	
	public boolean isOptionsButtonPressed(){
		Input input = game.getInput();
		if(!input.isTouchDown(0))
		{
			released = true;
		}
		int xTouch = input.getTouchX(0);
		int yTouch = input.getTouchY(0);
		
		if(input.isTouchDown(0) &&
				xTouch >= optionsX && xTouch <= optionsX + Assets.pause_options_button.getWidth() &&
				yTouch >= optionsY && yTouch <= optionsY + Assets.pause_options_button.getHeight() &&
				released == true)
		{
			released = false;
			return true;
		}
		return false;
	}
	
	public boolean isRestartButtonPressed(){
		Input input = game.getInput();
		if(!input.isTouchDown(0))
		{
			released = true;
		}
		int xTouch = input.getTouchX(0);
		int yTouch = input.getTouchY(0);
		
		if(input.isTouchDown(0) &&
				xTouch >= tryAgainX && xTouch <= tryAgainX + Assets.pause_retry_button.getWidth() &&
				yTouch >= tryAgainY && yTouch <= tryAgainY + Assets.pause_retry_button.getHeight() &&
				released == true)
		{
			released = false;
			return true;
		}
		return false;
	}

	public void drawPauseScreen(Graphics g) {
		g.drawPixmap(Assets.pause_screen, 100, 200);
	}

}
