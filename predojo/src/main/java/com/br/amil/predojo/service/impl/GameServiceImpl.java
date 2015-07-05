package com.br.amil.predojo.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import com.br.amil.predojo.service.GameService;
import com.br.amil.predojo.to.Game;
import com.br.amil.predojo.to.Jogador;

public class GameServiceImpl implements GameService{

	private Game game;
	
	static Logger log = Logger.getLogger(GameServiceImpl.class.getName());
	
	public void criarJogo(Long idJogo){
		log.info("Criando jogo id:"+ idJogo);
		game = new Game(idJogo);
		game.setJogadores(new HashMap<String, Jogador>());
	}
	
	public void adicionarJogada(String matador, String vitma, String arma) {
		log.info("Adicionando jogada,"+ matador + " matou " + vitma + " usando " + arma);
		
		if(!"<WORLD>".equalsIgnoreCase(matador)){
			//contabilizando morte para o matador
			Jogador killer =  game.getJogadores().get(matador);
			
			if(killer == null){
				killer = new Jogador();
				killer.setName(matador);
				killer.setWins(new HashMap<String, List<String>>());
				killer.getWins().put(arma, new ArrayList<String>());
				game.getJogadores().put(matador, killer);
			}
			
			if(killer.getWins().get(arma) == null){
				killer.getWins().put(arma, new ArrayList<String>());
			}
			killer.getWins().get(arma).add(vitma);
		} else {
			//Quando matador for <WORLD> apenas a morte sera contabilizada
			log.info("<WORLD> matou " + vitma + " apenas a morte será contabilizada");
		}
		
		//contabilizando mortes ao jogador que foi morto	
		Jogador victim = game.getJogadores().get(vitma);
			
		if(victim == null){
			victim = new Jogador();
			victim.setName(vitma);
			victim.setWins(new HashMap<String, List<String>>());

			victim.setDeaths(new HashMap<String, List<String>>());
			victim.getDeaths().put(matador, new ArrayList<String>());
			game.getJogadores().put(vitma, victim);
			
		}
		
		if(victim.getDeaths() == null){
			victim.setDeaths(new HashMap<String, List<String>>());
			victim.getDeaths().put(matador, new ArrayList<String>());
		} else if(victim.getDeaths().get(matador) == null){
			victim.getDeaths().put(matador, new ArrayList<String>());
		}
		
		victim.getDeaths().get(matador).add(arma);
		
	}
	
	public void gerarRelatorio() {
		log.info("iniciando geração de relatório");
		Jogador[] jogadores = (Jogador[]) game.getJogadores().values().toArray(new Jogador[game.getJogadores().values().size()]);
		
		ordenarRanking(jogadores);
		gerarInformacoesVencedor(jogadores);
		System.out.println("--------------------------------FIM DO JOGO "+ game.getIdJogo() + "--------------------------------");	
	}

	private void ordenarRanking(Jogador[] jogadores) {
		System.out.println("--------------------------------RANKING DO JOGO "+game.getIdJogo()+"-------------------------------\n\n");
		
		//Ordenando ranking utilizando Comparator 
		Arrays.sort(jogadores);
		
		for(Jogador jogador: jogadores){
			
			int matou = 0;
			int morreu = 0;
			if(jogador.getWins() != null){
				for(List<String> listaVitorias: jogador.getWins().values()){
					matou = matou + listaVitorias.size();
				}
			}
			
			if(jogador.getDeaths() != null){
				for(List<String> listaDerrotas: jogador.getDeaths().values()){
					morreu = morreu + listaDerrotas.size();
				}
			}
			
			System.out.println(jogador.getName() + " matou " + matou + " e morreu  " + morreu + "");
			
			if(morreu == 0){
				System.out.println(jogador.getName() + " recebeu o prêmio por não ter morrido nenhuma vez" );
			}
		}
		
		System.out.println("\n");
	}

	
	private void gerarInformacoesVencedor(Jogador[] jogadores) {
		
		//Array ordenado, basta pegar o primeiro
		Jogador vencedor = jogadores[0];
		int mortesArmaPreferida = 0;
		String arma = "";
		
		//Varrendo lista de mortes por cada arma do jogador e armazenando a que mais matou
		for(Map.Entry<String, List<String>> entry : vencedor.getWins().entrySet()){
			if(entry.getValue().size() > mortesArmaPreferida){
				arma = entry.getKey();
				mortesArmaPreferida = entry.getValue().size();
			}
		}
		System.out.println("O vencedor foi "+ vencedor.getName());
		System.out.println("A arma mais utilizada por "+ vencedor.getName() + " foi "+ arma + " com total de "+ mortesArmaPreferida +" morte(s).\n");
	}
}
