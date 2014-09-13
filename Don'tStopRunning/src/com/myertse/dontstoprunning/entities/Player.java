package com.myertse.dontstoprunning.entities;

import android.graphics.Rect;

import com.myertse.dontstoprunning.Assets;
import com.myertse.framework.Graphics;
import com.myertse.framework.Pixmap;

public class Player extends MovingThing {

	int lanePosition;
	int imageIndex;
	
	int[] lanes;
	int x;
	int y;
	
	Pixmap[] runningImages;

	public Player(int[] lanes, int initialX, int initialY) {
		super(initialX, initialY, 0);
		image = Assets.protaganistMid;
		this.lanes = lanes;
		x = initialX;
		y = initialY;
		runningImages = new Pixmap[3];
		runningImages[0] = Assets.protaganistLeft;
		runningImages[1] = Assets.protaganistMid;
		runningImages[2] = Assets.protaganistRight;
		hitbox = new Rect(initialX, initialY, initialX + image.getWidth(), initialY + image.getHeight());
		lanePosition = 1;
		imageIndex = 0;
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

	@Override
	public void draw(Graphics g) {
		g.drawPixmap(runningImages[imageIndex], x, y);
	}
	
	@Override
	public void update(float deltaTime) {
		hitbox.offsetTo(x, y);
	}

	public void changeRunningImage() {
		if (imageIndex == 0) {
			imageIndex = 2;
		} else {
			imageIndex = 0;
		}
	}
}
