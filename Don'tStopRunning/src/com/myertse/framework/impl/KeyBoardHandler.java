package com.myertse.framework.impl;

import java.util.ArrayList;
import java.util.List;

import android.view.View;
import android.view.View.OnKeyListener;

import com.myertse.framework.Input.KeyEvent;
import com.myertse.framework.impl.Pool;
import com.myertse.framework.impl.Pool.PoolObjectFactory;

// Note that we are using both our KeyEvent class and View's 
// KeyEvent class
public class KeyBoardHandler implements OnKeyListener {

	// based on KeyEver.KEYCODE_XXX
	// range [0,127]
	boolean[] pressedKeys = new boolean[128];
	// This is to make the garbage collector happy
	Pool<KeyEvent> keyEventPool;
	// Each time we get a new KeyEvent, we add it to this list
	List<KeyEvent> keyEventsBuffer = new ArrayList<KeyEvent>();
	// This stores the KeyEvents we return via getKeyEvents()
	List<KeyEvent> keyEvents = new ArrayList<KeyEvent>();

	public KeyBoardHandler(View view) {
		PoolObjectFactory<KeyEvent> factory = new PoolObjectFactory<KeyEvent>() {
			@Override
			public KeyEvent createObject() {
				return new KeyEvent();
			}
		};
		keyEventPool = new Pool<KeyEvent>(factory, 100);
		view.setOnKeyListener(this);
		view.setFocusableInTouchMode(true);
		view.requestFocus();
	}

	@Override
	public boolean onKey(View v, int keyCode, android.view.KeyEvent event) {
		if (event.getAction() == android.view.KeyEvent.ACTION_MULTIPLE)
			return false; // ignore multiple key presses
		
		// keyEvents are received on one thread but read on the main loop thread
		// therefore we need this to be synchronized so none of our members
		// are accessed in parallel
		synchronized (this) {
			// fetch a keyEvent from our pool
			KeyEvent keyEvent = keyEventPool.newObject();
			// set the keycode and char properties
			keyEvent.keyCode = keyCode;
			keyEvent.keyChar = (char) event.getUnicodeChar();
			if (event.getAction() == android.view.KeyEvent.ACTION_DOWN) {
				keyEvent.type = KeyEvent.KEY_DOWN;
				if (keyCode > 0 && keyCode < 127)
					pressedKeys[keyCode] = true;
			}
			if (event.getAction() == android.view.KeyEvent.ACTION_UP) {
				keyEvent.type = KeyEvent.KEY_UP;
				if (keyCode > 0 && keyCode < 127)
					pressedKeys[keyCode] = false;
			}
			keyEventsBuffer.add(keyEvent);
		}
		return false;
	}

	// This is what we will most likely call to actually see if a key is pressed
	// By giving this a keyCode Value, we can see if the key was pressed
	public boolean isKeyPressed(int keyCode) {
		if (keyCode < 0 || keyCode > 127)
			return false;
		return pressedKeys[keyCode];
	}

	// Will be called from a different thread so we need synchronized
	// Remember that we don't want two thread accessing variables at 
	// at the same time
	public List<KeyEvent> getKeyEvents() {
		synchronized (this) {
			int len = keyEvents.size();
			for (int i = 0; i < len; i++)
				keyEventPool.free(keyEvents.get(i));
			keyEvents.clear();
			keyEvents.addAll(keyEventsBuffer);
			keyEventsBuffer.clear();
			return keyEvents;
		}
		// we have to call
		// KeyboardHandler.getKeyEvents() frequently or the keyEvents list fills up quickly, and no
		// objects are returned to the Pool. Problems can be avoided as long as we remember this
	}

}
