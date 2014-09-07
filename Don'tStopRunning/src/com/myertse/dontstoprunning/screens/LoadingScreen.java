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
		Assets.background = g.newPixmap("images/background.png", PixmapFormat.ARGB4444);
		Assets.playerShip = g.newPixmap("images/test_ship.png", PixmapFormat.ARGB4444);
		Assets.protaganistMid = g.newPixmap("images/charlie_start.png", PixmapFormat.ARGB4444);
		Assets.protaganistLeft = g.newPixmap("images/charlie_left.png", PixmapFormat.ARGB4444);
		Assets.protaganistRight = g.newPixmap("images/charlie_right.png", PixmapFormat.ARGB4444);
		Assets.stepLeft = g.newPixmap("images/step_left.png", PixmapFormat.ARGB4444);
		Assets.stepRight = g.newPixmap("images/step_rite.png", PixmapFormat.ARGB4444);
		Assets.jump_enemy1 = g.newPixmap("images/jump_enemy1.png", PixmapFormat.ARGB4444);
		Assets.jump_enemy2 = g.newPixmap("images/jump_enemy2.png", PixmapFormat.ARGB4444);
		Assets.jump_enemy3 = g.newPixmap("images/jump_enemy3.png", PixmapFormat.ARGB4444);
		Assets.dodge_enemy1 = g.newPixmap("images/dodge_enemy1.png", PixmapFormat.ARGB4444);
		Assets.dodge_enemy2 = g.newPixmap("images/dodge_enemy2.png", PixmapFormat.ARGB4444);
		Assets.cliff = g.newPixmap("images/cliff.png", PixmapFormat.ARGB4444);
		Assets.explosion = game.getAudio().newSound("sounds/explosion.ogg");
		Assets.tap = game.getAudio().newSound("sounds/tp_effect.ogg");
		Assets.dead_text = g.newPixmap("fonts/dead_text.png", PixmapFormat.ARGB4444);
		Assets.title_page = g.newPixmap("images/title_page.png", PixmapFormat.ARGB4444);
		Assets.pause_button = g.newPixmap("images/pause_button.png", PixmapFormat.ARGB4444);
		Assets.pause_screen = g.newPixmap("images/pause_menu.png", PixmapFormat.ARGB4444);
		Assets.step_counter = g.newPixmap("images/step_counter.png", PixmapFormat.ARGB4444);
		
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
