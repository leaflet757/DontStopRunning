package com.myertse.framework.impl;

import java.util.ArrayList;

import com.myertse.framework.components.IDrawable;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.Log;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.WindowManager;

public abstract class Graphics {

	private static ArrayList<IDrawable> graphicObjects;
	
	private final static int TARGET_WIDTH = 1280;
	private final static int TARGET_HEIGHT = 720;
	
	private static float scaleX = 1;
	private static float scaleY = 1;
	
	public static void update(float deltaTime, GameScreen screen) {
		Canvas canvas = null;
		SurfaceHolder holder = screen.getHolder();
		try {
			canvas = holder.lockCanvas();
			Log.d("Graphics", "Screen has been locked");
			// TODO: draw game objects
			//Paint myPaint = new Paint();
			//myPaint.setColor(Color.rgb(255, 0, 0));
			//myPaint.setStrokeWidth(10);
			//canvas.drawRect(100, 100, scaleX * 200, scaleY * 200, myPaint);
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			holder.unlockCanvasAndPost(canvas);
			Log.d("screen", "canvas posted");
		}
	}

	private static void findRelativeAspectRatio(Activity mainActivity) {
		// TODO Auto-generated method stub
		WindowManager wm = (WindowManager) mainActivity.getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		int width = size.x;
		scaleX = width / TARGET_WIDTH;
		int height = size.y; 
		scaleY = height / TARGET_HEIGHT; 
	}

	public static void init(Activity mainActivity) {
		mainActivity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		findRelativeAspectRatio(mainActivity);
		
		//graphicObjects = new ArrayList<IDrawable>();
	}

	public static float getScaleX() {
		return scaleX;
	}

	public static float getScaleY() {
		return scaleY;
	}

	public static void add(IDrawable graphicsComponent) {
		//graphicObjects.add(graphicsComponent);
	}

}
