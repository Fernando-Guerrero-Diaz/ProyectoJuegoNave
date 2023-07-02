package com.mygdx.game;

public abstract class Obstaculo extends Elemento {
	private int daño;
	public int getDaño() {
		return daño;
	}
	public void setDaño(int daño) {
		this.daño=daño;
	}
	//Recibe el daño y se lo manda a Nave.
	public void colisionNave() {
		Nave nave = Nave.getNaveInstance();
		nave.recibirDaño(this.getDaño());
		recibeDaño(3);	
	}
	//Mete el puntaje que puedes ser adquirido de diferentes clases (pickup o enemigos) y se lo suma a Nave
	public void ganarPuntos(int a) {
		Nave nave =Nave.getNaveInstance();
		nave.agregarPuntos(a);
	}

	public abstract void recibeDaño(int daño);
}
