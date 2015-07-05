package com.br.amil.predojo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.br.amil.predojo.service.GameService;
import com.br.amil.predojo.service.impl.GameServiceImpl;

/**
 * Classe que inicia a aplicação:
 * - Leitura do arquivo de texto
 * - Tratamento de suas linhas
 * - Chamada à classe de serviço para adicionar jogadas e gerar o relatorio
 *
 */
public class App 
{
	static Logger log = Logger.getLogger(App.class.getName());
	
	final static GameService game = new GameServiceImpl();	
	
	private final static List<String>linhasArquivo = new ArrayList<String>();
	
    public static void main( String[] args )  {
	    log.info("Iniciando aplicação...");	
    	lerArquivo();
    	tratarLinhas();
				
    }

    /**
     * Metodo para leitura das linnhas do arquivo de log
     */
	private static void lerArquivo()  {
		log.info("Iniciando leitura de arquivo de log");		
		FileReader input;
		BufferedReader bufRead;
		try {
			input = new FileReader("gamelog.txt");
			bufRead = new BufferedReader(input);
	    	String myLine = null;
	    	
	    	while ( (myLine = bufRead.readLine()) != null) {    
	    		linhasArquivo.add(myLine);
	    	}
	    	
	    	input.close();
	    	bufRead.close();
		} catch (FileNotFoundException e) {
			log.log(Level.SEVERE, "Arquivo nao encontrado: " + e.getMessage());
		} catch (IOException e) {
			log.log(Level.SEVERE, "IOException: " + e.getMessage());
		}
    	
    	
    	
	}
	
	/**
	 * Metodo para validar as linhas, linhas fora do formato do REGEX são ignoradas
	 */
	private static void tratarLinhas(){
		log.info("iniciando tratamento das linhas lidas do arquivo");
		
		for (String entry : linhasArquivo) {
			
			if (entry.matches("^(\\d{2})/(\\d{2})/(\\d{4})\\s(\\d{2}):(\\d{2}):(\\d{2})\\s-\\s.+")) {
				//Todas linhas seguem o mesmo padrão, portanto substring posicional é seguro aqui
				entry = entry.substring(22);
				
				//Toda linha apos o substring que nao comece com New Match ou Match é informação do jogo
				if(!"New match".equalsIgnoreCase(entry.substring(0, 9))
						&& !"Match".equalsIgnoreCase(entry.substring(0, 5))){
					
					final String[] info = entry.split(" ");
					game.adicionarJogada(info[0], info[2], info[4]);
					
				} else if("New match".equalsIgnoreCase(entry.substring(0, 9))){
					final String[] info = entry.split(" ");
					game.criarJogo(Long.valueOf(info[2]));
					
				} else if("Match".equalsIgnoreCase(entry.substring(0, 5))){
					log.info("Linha de finalização do jogo");
					game.gerarRelatorio();
				}
					
			} else {
				//Em caso de linha fora do padrão no arquivo
				log.log(Level.WARNING, "Linha em formato errado sera ignorada: " + entry);
			}
		}
	} 
}
