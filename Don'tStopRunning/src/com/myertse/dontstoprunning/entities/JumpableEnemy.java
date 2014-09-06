package com.myertse.dontstoprunning.entities;

import java.util.Random;

import com.myertse.dontstoprunning.Assets;
import com.myertse.framework.Pixmap;

public class JumpableEnemy extends Enemy {

	//Dodge enemy list
	Pixmap[] pictureList = new Pixmap[]{Assets.jump_enemy1, Assets.jump_enemy2, Assets.jump_enemy3};
	private int lane = -1000;
		
	public JumpableEnemy(Pixmap image, int initialX, int initialY, int initialSpeed) {
		super(image, initialX, initialY, initialSpeed);
		Random random = new Random();
		Pixmap newImage = pictureList[random.nextInt(pictureList.length - 1)];
		super.image = newImage;
	}

	public int getLane() {
		return lane;
	}

	public void setLane(int lane) {
		this.lane = lane;
	}
	
	//TO ADD: Hitbox and collision

}
