package com.myertse.dontstoprunning;

import com.myertse.dontstoprunning.enums.PlayerMovementState;
import com.myertse.framework.Input;

public class InputWrapper {

	Input input;
	
	PlayerMovementState currentState;
	PlayerMovementState previousState;
	
	public InputWrapper(Input input) {
		this.input = input;
	}
	
	public PlayerMovementState getPlayerMovementState() {
		// TODO Auto-generated method stub
		return null;
	}

}
