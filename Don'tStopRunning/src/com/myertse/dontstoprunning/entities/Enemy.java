package com.myertse.dontstoprunning.entities;

import com.myertse.framework.Pixmap;

abstract public class Enemy extends MovingThing {

	public Enemy(Pixmap image, int initialX, int initialY, int initialSpeed) {
		super(image, initialX, initialY, initialSpeed);
	}
	
}
