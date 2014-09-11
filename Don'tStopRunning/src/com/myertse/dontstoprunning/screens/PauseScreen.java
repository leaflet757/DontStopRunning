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
				yTouch >= backButtonY && yTouch <= backButtonY + Assets.pause_back_button.getWidth() &&
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

	public boolean isOptionsPressed() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isRestartPressed() {
		// TODO Auto-generated method stub
		return false;
	}

}
