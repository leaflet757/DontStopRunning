package com.myertse.dontstoprunning.screens;

import com.myertse.dontstoprunning.Assets;
import com.myertse.framework.Game;
import com.myertse.framework.Graphics;
import com.myertse.framework.Input;
import com.myertse.framework.Screen;

public class MenuScreen extends Screen {

	//34,592	start top
	//196,662   start bot
	
	//256,591  options top
	//414,661  options bot
	
	//34,695   high scores top
	//196,757  hight scores bot
	
	//255,696  help top  
	//416,757  help bot
	
	int startButtonX = 34; int startButtonY = 592;
	int startButtonX2 = 196; int startButtonY2 = 662;
	
	int optionsButtonX = 256; int optionsButtonY = 591;
	int optionsButtonX2 = 414; int optionsButtonY2 = 661;
	
	int highScoresButtonX = 34; int highScoresButtonY = 695;
	int highScoresButtonX2 = 196; int highScoresButtonY2 = 757;
	
	int helpButtonX = 255; int helpButtonY = 696;
	int helpButtonX2 = 416; int helpButtonY2 = 757;
	
	
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
				xTouch >= startButtonX && xTouch <= startButtonX2 &&
				yTouch >= startButtonY && yTouch <= startButtonY2 &&
				released == true)
		{
			GAME.setScreen(new GameScreen(GAME));
		}
		
		//if in options button range
		else if(input.isTouchDown(0) &&
				xTouch >= optionsButtonX && xTouch <= optionsButtonX2 &&
				yTouch >= optionsButtonY && yTouch <= optionsButtonY2 &&
				released == true)
		{
			GAME.setScreen(new OptionsScreen(GAME, this));
		}
		
		//If in high scores button range
		else if(input.isTouchDown(0) &&
				xTouch >= highScoresButtonX && xTouch <= highScoresButtonX2 &&
				yTouch >= highScoresButtonY && yTouch <= highScoresButtonY2 &&
				released == true)
		{
			GAME.setScreen(new HighScoreScreen(GAME, this));
		}
		
		//If in help button range
		else if(input.isTouchDown(0) &&
				xTouch >= helpButtonX && xTouch <= helpButtonX2 &&
				yTouch >= helpButtonY && yTouch <= helpButtonY2 &&
				released == true)
		{
			GAME.setScreen(new HelpScreen(GAME, this));
		}
		
		
	}

	@Override
	public void present(float deltaTime) {
		// TODO Auto-generated method stub
		Graphics g = GAME.getGraphics();
		g.clear(0);
		g.drawPixmap(Assets.title_page, 0, 0);
		//g.drawPixmap(Assets.title_play_button, startButtonX, startButtonY);
		//g.drawPixmap(Assets.title_options_button, optionsButtonX, optionsButtonY);
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
