package com.myertse.dontstoprunning;

import com.myertse.dontstoprunning.entities.MovingThing;
import com.myertse.dontstoprunning.enums.SpawnerState;
import com.myertse.framework.impl.Pool.PoolObjectFactory;

public class ObstacleFactory implements PoolObjectFactory<MovingThing>{
	
	SpawnerState spawnState;

	public void setCurrentSpawnState(SpawnerState state) {
		spawnState = state;
	}
	
	@Override
	public MovingThing createObject() {
		switch(spawnState) {
		case CHASM_SPAWN:
			
			break;
		case DOUBLE_OBJECT_SPAWN:
			
			break;
		case END_GAME_SPAWN:
			
			break;
		case MOVING_OBJECT_SPAWN:
			
			break;
		case SINGLE_SPAWN:
			
			break;
		case TRIPLE_OBJECT_SPAWN:
			
			break;
		}
		return null;
	}

}
