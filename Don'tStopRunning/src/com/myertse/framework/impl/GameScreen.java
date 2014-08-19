package com.myertse.framework.impl;

import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameScreen extends SurfaceView {

	SurfaceHolder holder;
	
	public GameScreen(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		holder = this.getHolder();
		if (holder == null) Log.d("holder", "holder does not live");
		else Log.d("Holder", holder.toString());
	}
	
	public Canvas lockCanvas() {
		return holder.lockCanvas();
	}

	public void unlockCanvasAndPost(Canvas c) {
		holder.unlockCanvasAndPost(c);
	}
	
	public SurfaceHolder getHolder(){
		return holder;
	}

}
