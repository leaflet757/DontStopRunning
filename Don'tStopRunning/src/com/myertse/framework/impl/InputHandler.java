package com.myertse.framework.impl;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class InputHandler implements OnTouchListener{

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		RenderView view = (RenderView)v;
		int[] color = view.getRGB();
		Log.d("click", "niggas be clickin");
		if (color[2] < 256) {
		//	color[2] += 5;
		} else {
		//	color[2] = 0;
		}
		view.setRGB(color[0],color[1],color[2]);
		Canvas c = view.getHolder().lockCanvas();
		c.drawRect(new Rect((int)event.getX(), (int)event.getY(), 10,10), new Paint(Color.BLACK));
		view.getHolder().unlockCanvasAndPost(c);
		
		
		return true;
	}

}
