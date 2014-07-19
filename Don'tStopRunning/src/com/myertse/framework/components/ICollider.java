package com.myertse.framework.components;

import com.myertse.framework.impl.Actor2D;

public interface ICollider {
	public abstract void onCollision(Actor2D other);
}
