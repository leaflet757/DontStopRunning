package com.myertse.framework.impl;

import android.media.SoundPool;

import com.myertse.framework.Sound;

public class AndroidSound implements Sound {

	int soundId = 0;
	int streamID;
	SoundPool soundPool;

	public AndroidSound(SoundPool soundPool, int soundId) {
		this.soundId = soundId;
		this.soundPool = soundPool;
	}

	@Override
	public void play(float volume) {
		streamID = soundPool.play(soundId, volume, volume, 0, 0, 1);
	}
	public void stop()
	{
		soundPool.stop(streamID);
	}

	@Override
	public void dispose() {
		soundPool.unload(soundId);
	}

}
