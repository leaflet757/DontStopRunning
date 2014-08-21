package com.myertse.dontstoprunning.screens;

import android.util.Log;

import com.myertse.dontstoprunning.Assets;
import com.myertse.framework.Game;
import com.myertse.framework.Graphics;
import com.myertse.framework.Graphics.PixmapFormat;
import com.myertse.framework.Screen;

public class LoadingScreen extends Screen {

	Game game;
	
	public LoadingScreen(Game game) {
		super(game);
		this.game = game;
	}

	@Override
	public void update(float deltaTime) {
		Graphics g = game.getGraphics();
		Assets.background = g.newPixmap("background/space_background.png", PixmapFormat.RGB565);
		Assets.playerShip = g.newPixmap("sprites/test_ship.png", PixmapFormat.ARGB4444);
		Assets.explosion = game.getAudio().newSound("sounds/explosion.ogg");
		Assets.backgroundMusic = game.getAudio().newMusic("music/taviandscratch.mp3");
		game.setScreen(new GameScreen(game));
		Log.d("LoadingScreen", "updating...");
	}

	@Override
	public void present(float deltaTime) {
		// unimplemented
	}

	@Override
	public void pause() {
		// unimplemented
	}

	@Override
	public void resume() {
		// unimplemented
	}

	@Override
	public void dispose() {
		// unimplemented
	}

}
