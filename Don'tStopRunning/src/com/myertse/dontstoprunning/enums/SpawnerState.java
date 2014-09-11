package com.myertse.dontstoprunning.enums;

public enum SpawnerState {
	SINGLE_SPAWN(2),
	MOVING_OBJECT_SPAWN(4),
	DOUBLE_OBJECT_SPAWN(8),
	CHASM_SPAWN(16),
	TRIPLE_OBJECT_SPAWN(32),
	END_GAME_SPAWN(64);
	
	private int value;
	
	SpawnerState(int id) {
		value = id;
	}
	public int getValue() { return value; }
	public void setValue(int v) { value = v; }
	
}
