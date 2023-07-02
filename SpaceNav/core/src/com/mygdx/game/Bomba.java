package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;

public class Bomba extends Tesoro {

	public Bomba(int x, int y, int size, int xSpeed, int ySpeed, Texture tx, int puntos) {
		super(x, y, size, xSpeed, ySpeed, tx, puntos);

	}
	
	public void getPower() {
		Nave nave = Nave.getNaveInstance();
		nave.changeStrategy(new BalasTotales());
	}
}
