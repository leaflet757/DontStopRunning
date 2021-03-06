package com.myertse.dontstoprunning.entities;

import java.util.Timer;
import java.util.TimerTask;

import android.graphics.Rect;

import com.myertse.dontstoprunning.Assets;
import com.myertse.framework.Graphics;
import com.myertse.framework.Pixmap;

public class Player extends MovingThing {

	int lanePosition;
	int imageIndex;
	int oldImageIndex;
	
	int[] lanes;
	int x;
	int y;
	
	long startingTime = 0;
	long endingTime = 0;
	boolean jumpButtonPressed = false;
	
	public boolean dead = false;
	
	final int OFFSET = 50;
	
	Pixmap[] runningImages;

	public Player(int[] lanes, int initialX, int initialY) {
		super(initialX, initialY, 0);
		image = Assets.protaganistMid;
		this.lanes = lanes;
		x = initialX;
		y = initialY;
		runningImages = new Pixmap[5];
		runningImages[0] = Assets.protaganistLeft;
		runningImages[1] = Assets.protaganistMid;
		runningImages[2] = Assets.protaganistRight;
		runningImages[3] = Assets.protaganistJump;
		runningImages[4] = Assets.protaganistDeath;
		hitbox = new Rect(initialX + OFFSET, initialY + OFFSET, 
				initialX + image.getWidth() - OFFSET, initialY + image.getHeight() - OFFSET);
		lanePosition = 1;
		imageIndex = 1;
	}

	public void moveLeft() {
		if (lanePosition > 0) {
			lanePosition--;
			x = lanes[lanePosition];
		}
	}

	public void moveRight() {
		if (lanePosition < lanes.length - 1) {
			lanePosition++;
			x = lanes[lanePosition];
		}
	}
	
	public void jump()
	{
		startingTime = System.nanoTime();
		if(!jumpButtonPressed)
		{
			//Add 1000 milliseconds to time
			endingTime = startingTime + 1000000000;
			jumpButtonPressed = true;
			oldImageIndex = imageIndex;
		}
		else
		{
			if(startingTime >= endingTime)
			{
				jumpButtonPressed = false;
				imageIndex = oldImageIndex;
				return;
			}
		}
		
		//Assign to jump picture
		imageIndex = 3;
	}
	
	public boolean die()
	{
		startingTime = System.nanoTime();
		if(!dead)
		{
			endingTime = startingTime + 800000000;
			dead = true;
		}
		else
		{
			if(startingTime >= endingTime)
			{
				return true;
			}
		}
		return false;
	}

	@Override
	public void draw(Graphics g) {
		if(!dead)
		{
			g.drawPixmap(runningImages[imageIndex], x, y);
		}
		else
		{
			g.drawPixmap(runningImages[4], x, y);
			
		}
	}
	
	@Override
	public void update(float deltaTime) {
		hitbox.offsetTo(x + OFFSET, y + OFFSET);
	}

	public void changeRunningImage() {
		if (imageIndex == 0) {
			imageIndex = 2;
		} else {
			imageIndex = 0;
		}
	}
	
	public boolean isJumping()
	{
		return jumpButtonPressed;
	}
}
