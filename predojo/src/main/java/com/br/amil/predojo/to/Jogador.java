package com.br.amil.predojo.to;

import java.util.List;
import java.util.Map;
/**
 * Classe responsavel por armazenar informações do jogador como vitorias e mortes durante um jogo
 * @author lfelipelias
 *
 */
public class Jogador implements Comparable<Jogador>{

	private String name;
	private Map<String, List<String>> wins;
	private Map<String, List<String>> deaths;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Map<String, List<String>> getWins() {
		return wins;
	}
	public void setWins(Map<String, List<String>> wins) {
		this.wins = wins;
	}
	public Map<String, List<String>> getDeaths() {
		return deaths;
	}
	public void setDeaths(Map<String, List<String>> deaths) {
		this.deaths = deaths;
	}
	
	/**
	 * Metodo utilizado para ordenar a lista de jogadores ao fim do jogo baseado no número de oponentes mortos
	 */
	public int compareTo(Jogador oponente) {
		int vitoriasJogador = 0;
		
		for(List<String>lista : wins.values()){
			vitoriasJogador = vitoriasJogador + lista.size();
		}
		int vitoriasOponente = 0;
		
		for(List<String>lista :oponente.getWins().values()){
			vitoriasOponente = vitoriasOponente + lista.size();
		}
		
		return vitoriasOponente - vitoriasJogador;
	}
	
}
