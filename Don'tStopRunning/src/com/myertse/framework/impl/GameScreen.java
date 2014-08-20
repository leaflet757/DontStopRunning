package com.myertse.framework.impl;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

public class GameScreen {

	SurfaceView surfaceView;
	
	public GameScreen(Activity mainActivity, int surfaceview1) {
		surfaceView = (SurfaceView)mainActivity.findViewById(0x7f080000);
		if (surfaceView == null) Log.e("surface", "shits still jenk yo");
	}

	public SurfaceHolder getHolder(){
		if (surfaceView.getHolder() == null) Log.d("HOLDER", "DOES NOT EXIST");
		return surfaceView.getHolder();
	}

	public SurfaceView getSurfaceView() {
		return surfaceView;
	}

}
