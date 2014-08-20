package com.myertse.framework;

public abstract class Screen {
	protected final Game GAME;

	public Screen(Game game) {
		GAME = game;
	}

	public abstract void update(float deltaTime);

	public abstract void present(float deltaTime);

	public abstract void pause();

	public abstract void resume();

	public abstract void dispose();
}
