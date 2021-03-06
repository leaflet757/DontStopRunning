package com.myertse.dontstoprunning.screens;

import android.util.Log;

import com.myertse.dontstoprunning.Assets;
import com.myertse.dontstoprunning.MainActivity;
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
		Assets.title_page = g.newPixmap("images/start_page.png", PixmapFormat.ARGB4444);
		Assets.pause_button = g.newPixmap("images/pause_button.png", PixmapFormat.ARGB4444);
		Assets.pause_screen = g.newPixmap("images/pause_menu.png", PixmapFormat.ARGB4444);
		Assets.step_counter = g.newPixmap("images/step_counter.png", PixmapFormat.ARGB4444);
		Assets.title_play_button = g.newPixmap("images/title_play_button.png", PixmapFormat.ARGB4444);
		Assets.title_options_button = g.newPixmap("images/title_options_button.png", PixmapFormat.ARGB4444);
		Assets.options_back_button = g.newPixmap("images/options_back_button.png", PixmapFormat.ARGB4444);
		Assets.options_menu = g.newPixmap("images/options_menu.png", PixmapFormat.ARGB4444);
		Assets.pause_back_button = g.newPixmap("images/pause_back_button.png", PixmapFormat.ARGB4444);
		Assets.pause_options_button = g.newPixmap("images/pause_menu_options.png", PixmapFormat.ARGB4444);
		Assets.pause_retry_button = g.newPixmap("images/pause_menu_retry.png", PixmapFormat.ARGB4444);
		Assets.game_over = g.newPixmap("images/end_screen.png", PixmapFormat.ARGB4444);
		Assets.game_over_retry = g.newPixmap("images/end_screen_retry.png", PixmapFormat.ARGB4444);
		Assets.protaganistJump = g.newPixmap("images/charlie_jump.png", PixmapFormat.ARGB4444);
		Assets.backgroundMusic = game.getAudio().newMusic("music/dont_stop_running.wav");
		Assets.high_score_screen = g.newPixmap("images/high_score.png", PixmapFormat.ARGB4444);
		Assets.help_screen = g.newPixmap("images/help_screen.png", PixmapFormat.ARGB4444);
		Assets.protaganistDeath = g.newPixmap("images/charlie_dead.png", PixmapFormat.ARGB4444);
		Assets.speed_bar = g.newPixmap("images/speed_bar.png", PixmapFormat.ARGB4444);
		Assets.speed_bar_arrow = g.newPixmap("images/current_speed_icon.png", PixmapFormat.ARGB4444);
		
		game.setScreen(new MenuScreen(game));
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
