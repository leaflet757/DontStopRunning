package com.myertse.dontstoprunning.entities;

import android.graphics.Rect;

import com.myertse.dontstoprunning.Assets;
import com.myertse.dontstoprunning.enums.Enemy_type;
import com.myertse.framework.Pixmap;

public class Cliff extends Enemy{

	public Cliff(int initialX, int initialY, int initialSpeed) {
		super(initialX, initialY, initialSpeed);
		setImage(Assets.cliff);
		hitbox = new Rect(0, 0, Assets.cliff.getWidth(), Assets.cliff.getHeight());
		type = Enemy_type.CHASM;
	}

}
