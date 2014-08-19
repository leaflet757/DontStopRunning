package com.myertse.framework.components;

import com.myertse.framework.impl.Graphics;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class DrawableComponent implements IDrawable{
	
	private int scaleX = 1;
	private int scaleY = 1;
	private Rect srcRect;
	private Rect dstRect;
	private Bitmap objPic;
	private Paint paint;
	
	public DrawableComponent(Bitmap image) {
		// TODO: Check scale
		// TODO: add rotation
		scaleX = Math.round(Graphics.getScaleX());
		scaleY = Math.round(Graphics.getScaleY());
		objPic = Bitmap.createBitmap(image, 0, 0, 
				image.getWidth()*scaleX, image.getHeight()*scaleY);
		srcRect = new Rect(0,0,objPic.getWidth(),objPic.getHeight());
		dstRect = new Rect(0,0,objPic.getWidth(),objPic.getHeight());
		paint = new Paint();
		paint.setARGB(0, 255, 255, 255);
	}
	
	@Override
	public Bitmap getObjPic() {
		return objPic;
	}
	
	@Override
	public void setObjPic(Bitmap objPic) {
		this.objPic = objPic;
	}
	
	@Override
	public void redraw(float deltaTime, Canvas canvas) {
		canvas.drawBitmap(objPic, srcRect, dstRect, paint);
	}

	public Rect getSourceRect() {
		return srcRect;
	}

	public void setSourceRect(Rect sourceRect) {
		this.srcRect = sourceRect;
	}

	public Rect getDstRect() {
		return dstRect;
	}

	public void setDstRect(Rect dstRect) {
		this.dstRect = dstRect;
	}
}
