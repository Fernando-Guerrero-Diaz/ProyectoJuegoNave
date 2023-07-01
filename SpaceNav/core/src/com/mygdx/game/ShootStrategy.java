package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.Texture;

public interface ShootStrategy {
	public void shoot(GerenteElementos gerente, float rotacion, Sprite spr, Texture txBala);
	public int disparos();
	}
