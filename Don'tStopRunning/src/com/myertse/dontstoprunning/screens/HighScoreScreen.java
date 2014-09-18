package com.myertse.dontstoprunning.screens;

import com.myertse.dontstoprunning.Assets;
import com.myertse.framework.Game;
import com.myertse.framework.Graphics;
import com.myertse.framework.Input;
import com.myertse.framework.Screen;

public class HighScoreScreen extends Screen {


	Screen previousScreen;
	boolean released = false;
	public HighScoreScreen(Game game, Screen previousScreen) {
		super(game);
		this.previousScreen = previousScreen;
		}

	@Override
	public void update(float deltaTime) {
		Input input = GAME.getInput();
		if(!input.isTouchDown(0))
		{
			released = true;
		}
		if(input.isTouchDown(0) && released)
		{
			GAME.setScreen(previousScreen);
		}
	}

	@Override
	public void present(float deltaTime) {
		Graphics g = GAME.getGraphics();
		g.drawPixmap(Assets.high_score_screen, 150, 300);
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
