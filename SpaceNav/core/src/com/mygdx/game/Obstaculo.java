package com.mygdx.game;

public interface Obstaculo extends Elemento {
	public int getDaño();
	public boolean esDestructible();
	public boolean estaActivo();
	public void recibeDaño(int daño);
}
