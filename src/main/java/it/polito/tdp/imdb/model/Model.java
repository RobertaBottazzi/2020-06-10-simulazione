package it.polito.tdp.imdb.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.imdb.db.ImdbDAO;

public class Model {
	
	private SimpleWeightedGraph<Actor, DefaultWeightedEdge> grafo;
	private ImdbDAO dao;
	private Map<Integer, Actor> idMap;
	
	public Model() {
		this.dao= new ImdbDAO();
		this.idMap= new HashMap<>();
		this.dao.loadAllActors(idMap);
	}
	
	public void creaGrafo(String genre) {
		this.grafo= new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		Graphs.addAllVertices(this.grafo, this.dao.getAllActorsByGenre(genre, idMap));
		for(Arco arco: this.dao.getArchi(genre, idMap)) {
			if(!this.grafo.containsEdge(this.grafo.getEdge(arco.getActor1(), arco.getActor2())) && this.grafo.containsVertex(arco.getActor1()) && this.grafo.containsVertex(arco.getActor2()))
				Graphs.addEdge(this.grafo, arco.getActor1(), arco.getActor2(), arco.getPeso());
		}
	}
	
	public List<Actor> getAttoriSimili(Actor actor){
		List<Actor> attoriSimili= new ArrayList<>();
		attoriSimili.addAll(Graphs.successorListOf(this.grafo, actor));
		Collections.sort(attoriSimili, (a,b)-> a.getLastName().compareTo(b.getLastName()));
		return attoriSimili;		
	}
	
	public List<String> getAllGenres(){
		return this.dao.getAllGenres();
	}
	
	public SimpleWeightedGraph<Actor, DefaultWeightedEdge> getGrafo(){
		return this.grafo;
	}
	
	
}
