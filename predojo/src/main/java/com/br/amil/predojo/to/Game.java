package com.br.amil.predojo.to;

import java.util.Map;
/**
 * Classe responsavel por guardar informações de um jogo
 * @author lfelipelias
 *
 */
public class Game {
	
	private Long idJogo;
	
	private Map<String, Jogador> jogadores;
	
	public Game(){
		
	}
	public Game(Long idJogo){
		this.idJogo = idJogo;
	}

	public Long getIdJogo() {
		return idJogo;
	}

	public void setIdJogo(Long idJogo) {
		this.idJogo = idJogo;
	}
	public Map<String, Jogador> getJogadores() {
		return jogadores;
	}
	public void setJogadores(Map<String, Jogador> jogadores) {
		this.jogadores = jogadores;
	}

}
