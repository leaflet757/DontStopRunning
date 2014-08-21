package com.myertse.dontstoprunning;

import com.myertse.dontstoprunning.screens.LoadingScreen;
import com.myertse.framework.Screen;
import com.myertse.framework.impl.AndroidGame;

public class MainActivity extends AndroidGame {

	@Override
	public Screen getStartScreen() {
		return new LoadingScreen(this);
	}

}
