package com.myertse.dontstoprunning.screens;

import com.myertse.dontstoprunning.Assets;
import com.myertse.framework.Game;
import com.myertse.framework.Graphics;
import com.myertse.framework.Input;
import com.myertse.framework.Screen;

public class PauseScreen extends Screen{
	
	Screen gameScreenToSave;
	int backButtonX = 46;
	int backButtonY = 267;
	boolean released = false;
	
	public PauseScreen(Game game, Screen screen) {
		super(game);
		gameScreenToSave = screen;
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
		
		if(input.isTouchDown(0) &&
				xTouch >= backButtonX && xTouch <= backButtonX + Assets.pause_back_button.getWidth() &&
				yTouch >= backButtonY && yTouch <= backButtonY + Assets.pause_back_button.getWidth() &&
				released == true)
		{
			GAME.setScreen(gameScreenToSave);
			while(input.isTouchDown(0))
			{
				//wait for release
			}
		}
	}

	@Override
	public void present(float deltaTime) {
		// TODO Auto-generated method stub
		Graphics g = GAME.getGraphics();
		g.clear(0);
		g.drawPixmap(Assets.pause_back_button, 100, 200);
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
