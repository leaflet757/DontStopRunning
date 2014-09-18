package com.myertse.dontstoprunning.entities;

import com.myertse.dontstoprunning.enums.Enemy_type;
import com.myertse.framework.Pixmap;

public class Cliff extends Enemy{

	public Cliff(Pixmap image, int initialX, int initialY, int initialSpeed) {
		super(initialX, initialY, initialSpeed);
		type = Enemy_type.CHASM;
	}

}
