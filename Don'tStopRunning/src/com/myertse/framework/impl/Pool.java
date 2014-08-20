package com.myertse.framework.impl;

import java.util.ArrayList;
import java.util.List;

// This class will allow us to reuse previously created instances
// so that we do not have to create new objects on each event call
// Otherwise we would be creating temporary instances very fast,
// over and over and over again

public class Pool<T> {
	public interface PoolObjectFactory<T> {
		public T createObject();
	}

	private final List<T> freeObjects;
	private final PoolObjectFactory<T> factory;
	private final int maxSize;

	public Pool(PoolObjectFactory<T> factory, int maxSize) {
		this.factory = factory;
		this.maxSize = maxSize;
		this.freeObjects = new ArrayList<T>(maxSize);
	}

	// This will be responsible for giving us an object to use
	// This will either be a new object or one in our freeObjects List
	public T newObject() {
		T object = null;
		if (freeObjects.size() == 0)
			object = factory.createObject();
		else
			object = freeObjects.remove(freeObjects.size() - 1);
		return object;
	}

	// Allows us to insert objects that are no longer in use
	public void free(T object) {
		if (freeObjects.size() < maxSize)
			freeObjects.add(object);
	}
}

/*
 * EXAMPLE IMPLEMENTATION
 * 
 * PoolObjectFactory<TouchEvent> factory = new PoolObjectFactory<TouchEvent>() {
 * 		@Override
 * 		public TouchEvent createObject() { return new TouchEvent(); }
 * };
 * Pool<TouchEvent> touchEventPool = new Pool<TouchEvent>(factory, 50);
 * TouchEvent touchEvent = touchEventPool.newObject();
 * … do something here …
 * touchEventPool.free(touchEvent);
 * 
 * This will avoid issues with the garbage collector. Remember! We want to be 
 * good programmers and have our own garbage collection management. Even though 
 * java does not trust us with garbage collection
 * 
 * NOTE: This sample implemention does not fully reinitiallize used instances
 */
