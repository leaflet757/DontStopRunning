package com.myertse.dontstoprunning.entities;

import android.graphics.Rect;

import com.myertse.dontstoprunning.Assets;
import com.myertse.framework.Graphics;
import com.myertse.framework.Pixmap;
import com.myertse.framework.impl.Actor2D;

public class MovingThing extends Actor2D {

	protected Pixmap image;
	
	protected Rect hitbox;
	protected int xPosition;
	protected int yPosition;
	protected int lanePosition;
	
	protected int ySpeed;
	
	public MovingThing(int initialX, int initialY, int initialSpeed) {
		super();
		image = null;
		setxPosition(initialX);
		setyPosition(initialY);
		setySpeed(initialSpeed);
	}
	
	
	@Override
	public void update(float deltaTime) {
		yPosition += ySpeed;
		hitbox.offsetTo(xPosition, yPosition);
	}

	@Override
	public void draw(Graphics g) {
		g.drawPixmap(image, xPosition, yPosition);
	}


	public int getxPosition() {
		return xPosition;
	}


	public void setxPosition(int xPosition) {
		this.xPosition = xPosition;
	}


	public int getyPosition() {
		return yPosition;
	}


	public void setyPosition(int yPosition) {
		this.yPosition = yPosition;
	}


	public int getLanePosition() {
		return lanePosition;
	}


	public void setLanePosition(int lanePosition) {
		this.lanePosition = lanePosition;
	}


	public int getySpeed() {
		return ySpeed;
	}


	public void setySpeed(int ySpeed) {
		this.ySpeed = ySpeed;
	}


	public Pixmap getImage() {
		return image;
	}


	public void setImage(Pixmap image) {
		this.image = image;
	}


	public Rect getHitbox() {
		return hitbox;
	}


	public void setHitbox(Rect hitbox) {
		this.hitbox = hitbox;
	}
	
	public boolean collidesWith(MovingThing thing)
	{
		if(this.hitbox.intersect(thing.getHitbox())) {
			return true;
		}
		return false;
	}
	
}
