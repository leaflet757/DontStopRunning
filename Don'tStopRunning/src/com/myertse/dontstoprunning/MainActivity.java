package com.myertse.dontstoprunning;

import com.example.dontstoprunning.R;
import com.myertse.framework.impl.Game;
import com.myertse.framework.impl.InputHandler;
import com.myertse.framework.impl.RenderView;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity {

	Game game;
	RenderView renderView;
	InputHandler input;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		renderView = new RenderView(this);
		
		input = new InputHandler();
		renderView.setOnTouchListener(input);
		
		
		
		setContentView(renderView);
	}

	protected void onResume() {
		super.onResume();
		renderView.resume();
		// TODO: game.run()
	}

	protected void onPause() {
		super.onPause();
		renderView.pause();
	}
}
