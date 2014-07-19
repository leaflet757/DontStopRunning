package com.myertse.framework.impl;

import android.graphics.Bitmap;
import android.graphics.Point;

import com.myertse.framework.components.ICollider;

public abstract class Actor2D {
	private Point location;
	private Bitmap objPic;
	private int speed;
	
	//Next friday need Screen, input, main charactaer, obstacles
	/*
	 * Object list Notes
	 * Hero
	 * Obstacles - double layered as well
	 * Tap UI buttons left and right
	 * Generic button (For things like settings)
	 * Flipboard corner counter
	 * Title Screen
	 * Pause screen
	 * Game screen
	 * Intro Cinematic
	 * Lose Screen
	 * Borders
	 * Big Jump object
	 * Big jump instructions
	 * map
	 */
	
	/*
	 * Base Object Class - TO ADD: Screen connection
	 */
	public Actor2D(Point start, Bitmap objPic, int speed) {
		location.x = start.x;
		location.y = start.y;
		this.objPic = objPic;
		this.setSpeed(speed);
	}
	
	abstract public void redraw(float deltaTime, GameScreen display);
	abstract public void onColision(ICollider obj);
	abstract public void timerTick(float deltaTime);

	public Point getLocation() {
		return location;
	}

	public void setLocation(Point location) {
		this.location = location;
	}

	public Bitmap getObjPic() {
		return objPic;
	}

	public void setObjPic(Bitmap objPic) {
		this.objPic = objPic;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}
}
