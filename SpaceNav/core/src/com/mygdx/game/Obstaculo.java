package com.mygdx.game;

public abstract class Obstaculo extends Elemento {
	private int daño;
	public int getDaño() {
		return daño;
	}
	public void setDaño(int daño) {
		this.daño=daño;
	}
	public void colisionNave() {
		Nave nave = Nave.getNaveInstance();
		nave.recibirDaño(this.getDaño());
		recibeDaño(3);	
	}
	public void ganarPuntos(int a) {
		Nave nave =Nave.getNaveInstance();
		nave.agregarPuntos(a);
	}

	public abstract void recibeDaño(int daño);
}
