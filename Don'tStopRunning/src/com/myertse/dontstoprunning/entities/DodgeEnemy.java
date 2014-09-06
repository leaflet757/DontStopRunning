package com.myertse.dontstoprunning.entities;

import java.util.Random;

import com.myertse.dontstoprunning.Assets;
import com.myertse.framework.Pixmap;

public class DodgeEnemy extends Enemy{

	//Dodge enemy list
	Pixmap[] pictureList = new Pixmap[]{Assets.dodge_enemy1, Assets.dodge_enemy2};
	private int lane = -1000;
	
	//When built assign random enemy type picture
	public DodgeEnemy(Pixmap image,int initialX, int initialY, int initialSpeed) {
		super(image, initialX, initialY, initialSpeed);
		Random random = new Random();
		Pixmap newImage = pictureList[random.nextInt(pictureList.length - 1)];
		setImage(newImage);
	}

	public int getLane() {
		return lane;
	}

	public void setLane(int lane) {
		this.lane = lane;
	}
	
	//TO ADD: Hitbox and collision

}
