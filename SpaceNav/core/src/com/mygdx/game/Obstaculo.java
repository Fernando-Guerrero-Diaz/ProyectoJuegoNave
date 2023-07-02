package com.mygdx.game;

public abstract class Obstaculo extends Elemento {
	private int daño;
	public int getDaño() {
		return daño;
	}
	public void setDaño(int daño) {
		this.daño=daño;
	}

	public abstract void recibeDaño(int daño);
}
