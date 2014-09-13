package com.myertse.dontstoprunning.enums;

public enum SpawnerState {
	SINGLE_SPAWN(1), // 1 jumpable enemy
	MOVING_OBJECT_SPAWN(1), // 1 jumplable enemy
	DOUBLE_OBJECT_SPAWN(2), // 1 jumpable and 1 unjumpable
	CHASM_SPAWN(2), // special case
	TRIPLE_OBJECT_SPAWN(3), // 3 jumpable
	END_GAME_SPAWN(3), // special case... start is 1 non jump and 2 jump
	NONE(0);
	
	private int value;
	
	SpawnerState(int id) {
		value = id;
	}
	public int getValue() { return value; }
	public void setValue(int v) { value = v; }
	
}
