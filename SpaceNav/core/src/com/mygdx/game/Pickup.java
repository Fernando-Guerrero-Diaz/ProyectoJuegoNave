package com.mygdx.game;

public abstract class Pickup extends Elemento{
	
	public abstract int getPuntos();
	public abstract void getPower();
	public void colisionNave() {
		getPower();
		Nave nave = Nave.getNaveInstance();
		nave.agregarPuntos(getPuntos());
	}

}
