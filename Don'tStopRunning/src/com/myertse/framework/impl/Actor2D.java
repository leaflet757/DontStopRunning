package com.myertse.framework.impl;

import android.graphics.Bitmap;
import android.graphics.Point;


import com.myertse.dontstoprunning.Actor_List;

import com.myertse.framework.components.DrawableComponent;

import com.myertse.framework.components.ICollider;
import com.myertse.framework.components.IDrawable;

public abstract class Actor2D {
	private IDrawable graphicsComponent = null;
	private Point location;
	private int speed;
	private Actor_List actorType;
	
	//Next Friday need Screen, input, main character, obstacles
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

	public Actor2D(Actor_List actorType, Point start, IDrawable graphicsComp, int speed) {
		if (graphicsComp != null) {
			graphicsComponent = graphicsComp;
			Graphics.add(graphicsComponent);
		}
		this.actorType = actorType;
		location.x = start.x;
		location.y = start.y;
		this.setSpeed(speed);
	}
	
	abstract public void onColision(ICollider obj);
	abstract public void timerTick(float deltaTime);

	public Point getLocation() {
		return location;
	}

	public void setLocation(Point location) {
		this.location = location;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public Actor_List getActorType() {
		return actorType;
	}

	public void setActorType(Actor_List actorType) {
		this.actorType = actorType;
	}
}
