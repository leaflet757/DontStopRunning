package com.myertse.dontstoprunning.entities;

import java.util.Random;

import android.graphics.Rect;

import com.myertse.dontstoprunning.Assets;
import com.myertse.framework.Pixmap;

public class JumpableEnemy extends Enemy {

	//Dodge enemy list
	Pixmap[] pictureList = {Assets.jump_enemy1, Assets.jump_enemy2, Assets.jump_enemy3};
	private int lane = -1000;
		
	public JumpableEnemy(int initialX, int initialY, int initialSpeed) {
		super(initialX, initialY, initialSpeed);
		Random random = new Random();
		Pixmap newImage = pictureList[random.nextInt(pictureList.length - 1)];
		setImage(newImage);
		hitbox = new Rect(initialX, initialY, initialX + image.getWidth(), initialY + image.getHeight());
	}

	public int getLane() {
		return lane;
	}

	public void setLane(int lane) {
		this.lane = lane;
	}
	
	//TO ADD: Hitbox and collision

}
