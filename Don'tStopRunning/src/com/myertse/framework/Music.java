package com.myertse.framework;

/*
 * Used for Longer Sound Files
 */

public interface Music {
	public void play();

	public void stop();

	public void pause();

	public void setLooping(boolean looping);

	public void setVolume(float volume);

	public boolean isPlaying();

	public boolean isStopped();

	public boolean isLooping();

	public void dispose();
}