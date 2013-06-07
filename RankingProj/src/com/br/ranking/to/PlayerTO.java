package com.br.ranking.to;

import java.util.Date;
import java.util.List;

public class PlayerTO {

	private String name;
	private int gameNumber;
	private Date gameDate;
	private List<PlayerTO> kills;
	private List<PlayerTO> deaths;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getGameNumber() {
		return gameNumber;
	}
	public void setGameNumber(int gameNumber) {
		this.gameNumber = gameNumber;
	}
	
	public Date getGameDate() {
		return gameDate;
	}
	public void setGameDate(Date gameDate) {
		this.gameDate = gameDate;
	}
	public List<PlayerTO> getKills() {
		return kills;
	}
	public void setKills(List<PlayerTO> kills) {
		this.kills = kills;
	}
	public List<PlayerTO> getDeaths() {
		return deaths;
	}
	public void setDeaths(List<PlayerTO> deaths) {
		this.deaths = deaths;
	}
	
	
	
	
}
