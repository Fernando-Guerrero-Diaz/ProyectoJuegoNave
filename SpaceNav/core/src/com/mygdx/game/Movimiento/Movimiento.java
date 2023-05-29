package com.mygdx.game.Movimiento;

import com.badlogic.gdx.graphics.g2d.Sprite;

public abstract class Movimiento {
	protected int y;
	protected int x;
	protected float xVel;
	protected float yVel;
	protected Sprite spr;
	


public abstract void moverse();
	
}
