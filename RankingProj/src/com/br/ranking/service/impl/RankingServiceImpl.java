package com.br.ranking.service.impl;

import java.util.LinkedHashMap;

import org.springframework.stereotype.Service;

import com.br.ranking.core.RankingDAO;
import com.br.ranking.service.RankingService;
import com.br.ranking.to.PlayerTO;
import com.br.ranking.to.RankingTO;

/**
 * Classe respons‡vel por chamar o DAO e recuperar os objetos TO
 * que ser‹o direcionados a camada VIEW
 * @author felipe
 *
 */
@Service
public class RankingServiceImpl implements RankingService {

	private RankingDAO dao = new RankingDAO();
	private RankingTO ranking; 
	
	
	public RankingTO searchRanking() {
		
		ranking = dao.queryRanking();
		
		if(ranking == null){
			ranking = new RankingTO();
			ranking.setPlayerList(new LinkedHashMap<String, PlayerTO>());
		}
		
		return ranking;
	
	}
	
	
}
