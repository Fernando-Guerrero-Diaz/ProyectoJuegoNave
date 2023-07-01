package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Shoot {
	private ShootStrategy shootStrategy;
	private int cantDisparos;
	public void setStrategy(ShootStrategy shootStrategy) {
		this.shootStrategy=shootStrategy;
		cantDisparos=shootStrategy.disparos();
	}
	public void shoot(GerenteElementos gerente, float rotacion, Sprite spr, Texture txBala) {
		shootStrategy.shoot(gerente, rotacion, spr, txBala);
		cantDisparos --;
		backLateral();
	}
	public void backLateral() {
		if (cantDisparos ==0) {
			setStrategy(new BalasLaterales());
		}
	}
}
