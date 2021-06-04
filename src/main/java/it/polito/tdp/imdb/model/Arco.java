package it.polito.tdp.imdb.model;

public class Arco {
	
	private Actor actor1;
	private Actor actor2;
	private int peso;
	
	
	public Arco(Actor actor1, Actor actor2, int peso) {
		this.actor1 = actor1;
		this.actor2 = actor2;
		this.peso = peso;
	}


	public Actor getActor1() {
		return actor1;
	}


	public void setActor1(Actor actor1) {
		this.actor1 = actor1;
	}


	public Actor getActor2() {
		return actor2;
	}


	public void setActor2(Actor actor2) {
		this.actor2 = actor2;
	}


	public int getPeso() {
		return peso;
	}


	public void setPeso(int peso) {
		this.peso = peso;
	}
	
	

}
