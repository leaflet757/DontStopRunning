package com.myertse.dontstoprunning.screens;

import com.myertse.dontstoprunning.Assets;
import com.myertse.framework.Game;
import com.myertse.framework.Graphics;
import com.myertse.framework.Input;
import com.myertse.framework.Screen;

public class EndScreen extends Screen{

	int retryButtonX = 62;
	int retryButtonY = 342;
	
	//62, 342
	boolean released = false;
	
	public EndScreen(Game game) {
		super(game);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(float deltaTime) {
		// TODO check to see what button was pressed on the main menu
		Input input = GAME.getInput();
		if(!input.isTouchDown(0))
		{
			released = true;
		}
		int xTouch = input.getTouchX(0);
		int yTouch = input.getTouchY(0);
		
		//If in retry button range
		if(input.isTouchDown(0) && 
				xTouch >= retryButtonX && xTouch <= retryButtonX + Assets.game_over_retry.getWidth() &&
				yTouch >= retryButtonY && yTouch <= retryButtonY + Assets.game_over_retry.getHeight() &&
				released == true)
		{
			GAME.setScreen(new GameScreen(GAME));
		}
	}

	@Override
	public void present(float deltaTime) {
		// TODO Auto-generated method stub
		Graphics g = GAME.getGraphics();
		g.clear(0);
		g.drawPixmap(Assets.game_over, 0, 0);
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
