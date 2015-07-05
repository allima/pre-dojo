package com.br.amil.predojo.service;

public interface GameService {
	/**
	 * Metodo utilizado para iniciar o jogo
	 * @param idJogo
	 */
	void criarJogo(Long idJogo);
	
	/**
	 * Metodo que adiciona jogada ao jogo e cria objeto Jogador para matador e vitima caso ainda não existam no jogo
	 * @param matador
	 * @param vitma
	 * @param arma
	 */
	void adicionarJogada(String matador, String vitma, String arma);
	
	/**
	 * Metodo utilizado para gerar o Ranking com informações de vitorias, mortes,
	 * prêmio para jogador que não morrer nenhuma vez e dados do vencedor
	 */
	void gerarRelatorio();
	
}
