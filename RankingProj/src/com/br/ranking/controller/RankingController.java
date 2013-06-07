package com.br.ranking.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.br.ranking.service.RankingService;
import com.br.ranking.to.RankingTO;

/**
 * Classe controller usando padrão MVC com componentes do SpringMVC
 * 
 * @author felipe
 *
 */
@Controller
public class RankingController {
	
	@Autowired
	private RankingService service;
	private RankingTO ranking;
	
	@RequestMapping("/")
	public String setupForm(Map<String, Object> map){
		
		//Chamada ao serviço para manter a regra de negócio separada
		ranking = service.searchRanking();
	
		
		map.put("rankingMap", ranking);
		
		return "ranking";
	}
}
