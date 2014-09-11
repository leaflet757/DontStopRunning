package com.myertse.dontstoprunning.screens;

import com.myertse.dontstoprunning.Assets;
import com.myertse.framework.Game;
import com.myertse.framework.Graphics;
import com.myertse.framework.Input;
import com.myertse.framework.Screen;

public class MenuScreen extends Screen {

	int startButtonX = 30;
	int startButtonY = 420;
	
	int optionsButtonX = 208;
	int optionsButtonY = 630;
	
	boolean released = false;
	
	public MenuScreen(Game game) {
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
		
		//If in start button range
		if(input.isTouchDown(0) && 
				xTouch >= startButtonX && xTouch <= startButtonX + Assets.title_play_button.getWidth() &&
				yTouch >= startButtonY && yTouch <= startButtonY + Assets.title_play_button.getHeight() &&
				released == true)
		{
			GAME.setScreen(new GameScreen(GAME));
		}
		
		//if in options button range
		if(input.isTouchDown(0) &&
				xTouch >= optionsButtonX && xTouch <= optionsButtonX + Assets.title_options_button.getWidth() &&
				yTouch >= optionsButtonY && yTouch <= optionsButtonY + Assets.title_options_button.getHeight() &&
				released == true)
		{
			GAME.setScreen(new OptionsScreen(GAME, this));
		}
	}

	@Override
	public void present(float deltaTime) {
		// TODO Auto-generated method stub
		Graphics g = GAME.getGraphics();
		g.clear(0);
		g.drawPixmap(Assets.title_page, 0, 0);
		g.drawPixmap(Assets.title_play_button, startButtonX, startButtonY);
		g.drawPixmap(Assets.title_options_button, optionsButtonX, optionsButtonY);
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
