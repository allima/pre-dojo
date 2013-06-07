package com.br.ranking.core;


import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;



import com.br.ranking.to.PlayerTO;
import com.br.ranking.to.RankingTO;

/**
 * Simulei uma classe DAO para evitar a necessidade de ser criado o banco de dados
 * no ambiente do cliente, configurações do Hibernate (PersistenceUnit, EntityManager, Factoru, etc)
 * e rodar scripts de INSERT para massa de dados.
 * Como simulei um DAO, a SELECT que seria feita aqui seria usando JOINS das tabelas
 * e ORDER BY no número de assassinatos.
 * 
 * @author felipe
 *
 */
public class RankingDAO {

	private RankingTO ranking = new RankingTO();
	private PlayerTO player;
	
	public RankingTO queryRanking(){
		
		ranking.setPlayerList(new LinkedHashMap<String, PlayerTO>());
		genarateSources();
		
		return ranking;
	}
	

	public PlayerTO  createPlayer(String name, Date gameDate){

		PlayerTO player = new PlayerTO();
		
		player.setName(name);
		player.setGameDate(gameDate);
		player.setKills(new ArrayList<PlayerTO>());
		player.setDeaths(new ArrayList<PlayerTO>());

		return player;
	}
	
	public void createKillersAndDeaths(PlayerTO player, String[] names, boolean isKillers){
		
		PlayerTO playerLocal = new PlayerTO();
		
		for (int i = 0; i < names.length; i++) {
			
			if(!names[i].equalsIgnoreCase("WORLD")){
				playerLocal.setName(names[i]);
				
				if(isKillers){
					player.getKills().add(playerLocal);
				} else {
					player.getDeaths().add(playerLocal);
				}
				playerLocal = new PlayerTO();
			}
		}
		
	}
	
	public void genarateSources(){
		
		player = createPlayer("FELIPE", new Date());
		String [] killersFelipe = {"WORLD", "WORLD", "EDUARDO", "BRUNO", "ALMIR", "MARCELO"};
		String [] deathsFelipe = {"EDUARDO", "BRUNO", "ALMIR", "MARCELO", "MARCELO", "BRUNO"};
		createKillersAndDeaths(player, killersFelipe, true);
		createKillersAndDeaths(player, deathsFelipe, false);
		ranking.getPlayerList().put("FELIPE", player);
		
		
		player = createPlayer("EDUARDO", new Date());
		String [] killersEduardo = {"FELIPE", "WORLD", "ALMIR", "ALMIR"};
		String [] deathsEduardo = {"FELIPE", "BRUNO", "ALMIR", "MARCELO"};
		createKillersAndDeaths(player, killersEduardo, true);
		createKillersAndDeaths(player, deathsEduardo, false);
		ranking.getPlayerList().put("EDUARDO", player);
		
		player = createPlayer("ALMIR", new Date());
		String [] killersAlmir = {"FELIPE", "EDUARDO"};
		String [] deathsAlmir = {"FELIPE", "EDUARDO", "EDUARDO"};
		createKillersAndDeaths(player, killersAlmir, true);
		createKillersAndDeaths(player, deathsAlmir, false);
		ranking.getPlayerList().put("ALMIR", player);
		
		player = createPlayer("MARCELO", new Date());
		String [] killersMarcelo = {"FELIPE", "FELIPE", "EDUARDO"};
		String [] deathsMarcelo = {"FELIPE", "FELIPE"};
		createKillersAndDeaths(player, killersMarcelo, true);
		createKillersAndDeaths(player, deathsMarcelo, false);
		ranking.getPlayerList().put("MARCELO", player);
			
	}

}
