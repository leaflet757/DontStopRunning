package com.myertse.framework.components;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public interface IDrawable {
	
	public void redraw(float deltaTime, Canvas canvas);

	public Bitmap getObjPic();
	
	public void setObjPic(Bitmap objPic);
}
