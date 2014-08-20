package com.myertse.framework.impl;

import android.content.Context;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class RenderView extends SurfaceView implements Runnable {
	Thread renderThread = null;
	SurfaceHolder holder;
	
	int[] color;
	// use volatile when the order of something matters
	// this is mostly in the onPause Method
	// running must be set to false first otherwise
	// infinite loop
	volatile boolean running = false;

	public RenderView(Context context) {
		super(context);
		holder = getHolder();
		
		color = new int[3];
		color[0] = 200;
		color[1] = 100;
		color[2] = 0;
	}

	public void resume() {
		running = true;
		renderThread = new Thread(this);
		renderThread.start();
	}

	public void run() {
		while (running) {
			if (!holder.getSurface().isValid())
				continue;
			// if the surface is valid, it will always be valid until the
			// pause is called
			Canvas canvas = holder.lockCanvas();
			// this is where drawing goes
			canvas.drawRGB(color[0], color[1], color[2]);
			holder.unlockCanvasAndPost(canvas);
		}
	}

	public void pause() {
		while (running) {
			try {
				running = false;
				renderThread.join();
			} catch (InterruptedException e) {
				running = true; // retry;
			}
		}
	}

	public void setRGB(int r, int g, int b) {
		// TODO Auto-generated method stub
		color[0] = r;
		color[1] = g;
		color[2] = b;
	}
	
	public int[] getRGB() {
		return color;
	}
}
