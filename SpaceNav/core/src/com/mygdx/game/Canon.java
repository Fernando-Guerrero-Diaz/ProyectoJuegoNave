package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;

public class Canon extends Tesoro {

	public Canon(int x, int y, int size, int xSpeed, int ySpeed, Texture tx, int puntos) {
		super(x, y, size, xSpeed, ySpeed, tx, puntos);
	}
	

	public void getPower() {
		Nave nave= Nave.getNaveInstance();
		nave.changeStrategy(new BalasFrontales());
	}
}
