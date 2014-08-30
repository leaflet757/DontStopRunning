package com.myertse.dontstoprunning;

import com.myertse.dontstoprunning.enums.PlayerMovementState;
import com.myertse.framework.Input;

public class InputWrapper {

	enum Button {LEFT, RIGHT}
	Button lastButtonPressed;
	
	Input input;
	
	boolean released = true;
	final int DOUBLETAP_INTERVAL = 1000;
	
	int width;
	int height;
	
	PlayerMovementState currentState = PlayerMovementState.ALTERNATING;
	PlayerMovementState previousState;
	
	public InputWrapper(Input input, int screenWidth, int screenHeight) {
		this.input = input;
		width = screenWidth;
		height = screenHeight;
	}
	
	public PlayerMovementState getPlayerMovementState() {
		// TODO Auto-generated method stub
		return currentState;
	}

	public void update(float deltaTime) {
		// TODO Auto-generated method stub
		previousState = currentState;
		currentState = PlayerMovementState.STOPPING;
		
		// Check for Jumping
		if (released && input.isTouchDown(0) && input.isTouchDown(1)) {
			int x = input.getTouchX(0);
			int y = input.getTouchY(0);
			int x2 = input.getTouchX(1);
			int y2 = input.getTouchY(1);
			if(x < (width/2) && y > (height - height/4) &&
					x2 > (width/2) && y2 > (height - height/4))
			{
				currentState = PlayerMovementState.JUMPING;
				released = false;
			}
		}
		// check for alternating or doubletap
		else if (released && input.isTouchDown(0)) {
			int x = input.getTouchX(0);
			int y = input.getTouchY(0);
			if(x < (width/2) && y > (height - height/4)) {
				if (lastButtonPressed == Button.LEFT) { 
					currentState = PlayerMovementState.DOUBLETAP_LEFT;
				} else {
					currentState = PlayerMovementState.ALTERNATING;
				}
				lastButtonPressed = Button.LEFT;
			}
			if(x > (width/2) && y > (height - height/4)) {
				if (lastButtonPressed == Button.RIGHT) { 
					currentState = PlayerMovementState.DOUBLETAP_RIGHT;
				} else {
					currentState = PlayerMovementState.ALTERNATING;
				}
				lastButtonPressed = Button.RIGHT;
			}
			released = false;
		// check to see if nothing is touching
		} else if (!input.isTouchDown(0)){
			released = true;
		}	

	}

}
