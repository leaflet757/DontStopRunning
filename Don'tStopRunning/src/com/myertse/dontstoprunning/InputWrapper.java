package com.myertse.dontstoprunning;

import com.myertse.dontstoprunning.enums.PlayerMovementState;
import com.myertse.framework.Input;

public class InputWrapper {

	enum Button {LEFT, RIGHT}
	Button lastButtonPressed;
	
	Input input;
	
	boolean released = true;
	final int DOUBLETAP_INTERVAL = 1000;
	int leftCounter = 0;
	int rightCounter = 0;
	
	int width;
	int height;
	
	PlayerMovementState currentState = PlayerMovementState.ALTERNATING;
	PlayerMovementState previousState;
	
	public InputWrapper(Input input, int screenWidth, int screenHeight) {
		this.input = input;
		width = screenWidth;
		height = screenHeight - Assets.stepLeft.getHeight();
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
			if(x < (width/2) && y > (height) &&
					x2 > (width/2) && y2 > (height))
			{
				currentState = PlayerMovementState.JUMPING;
				released = false;
			}
		}
		// check for alternating or doubletap
		else if (released && input.isTouchDown(0)) {
			int x = input.getTouchX(0);
			int y = input.getTouchY(0);
			if(x < (width/2) && y > (height)) {
				if (lastButtonPressed == Button.LEFT) {
					if (leftCounter % 2 == 1) {
						currentState = PlayerMovementState.DOUBLETAP_LEFT;
					}
				} else {
					currentState = PlayerMovementState.ALTERNATING;
					leftCounter = 0;
				}
				lastButtonPressed = Button.LEFT;
				leftCounter++;
			}
			if(x > (width/2) && y > (height)) {
				if (lastButtonPressed == Button.RIGHT) { 
					if (rightCounter % 2 == 1) {
						currentState = PlayerMovementState.DOUBLETAP_RIGHT;
					}
				} else {
					currentState = PlayerMovementState.ALTERNATING;
					rightCounter = 0;
				}
				lastButtonPressed = Button.RIGHT;
				rightCounter++;
			}
			released = false;
		// check to see if nothing is touching
		} else if (!input.isTouchDown(0)){
			released = true;
		}	

	}

}
