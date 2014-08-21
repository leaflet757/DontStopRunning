package com.myertse.dontstoprunning.screens;

import android.util.Log;

import com.myertse.dontstoprunning.Assets;
import com.myertse.framework.Game;
import com.myertse.framework.Graphics;
import com.myertse.framework.Screen;

public class GameScreen extends Screen {

	enum GameState { RUNNING, PAUSED, GAME_OVER }
	
	public GameScreen(Game game) {
		super(game);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(float deltaTime) {
		// TODO Auto-generated method stub
		Log.d("GameScreen", "updating...");
		Graphics g = GAME.getGraphics();
		g.drawPixmap(Assets.playerShip, 0, 0);
	}

	@Override
	public void present(float deltaTime) {
		// TODO Auto-generated method stub
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
