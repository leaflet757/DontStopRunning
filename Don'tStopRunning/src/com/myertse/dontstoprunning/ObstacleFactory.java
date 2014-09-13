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
		// TODO Auto-generated method stub
		return null;
	}

}
