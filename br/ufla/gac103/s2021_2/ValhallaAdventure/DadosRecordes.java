package br.ufla.gac103.s2021_2.ValhallaAdventure;
import java.io.FileWriter;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Classe dadosRecordes, feita para salvar dados dos usuarios que zerarem o jogo.
 * 
 * Igor Tavares e Rafael Bastos 
 * @version 1.0
 */
public class DadosRecordes
{
    
    public void salvarUsuario(String nome, int pontuacao) {
         try {
             FileWriter arq = new FileWriter("usuarios.txt", true);
             arq.write(nome + "," + " com: " + " "+ pontuacao + " " +"movimentos.\n");
             arq.close();
         }
         catch (Exception e) {
             System.out.println("Erro " + e.getMessage());   
         }
    }
    
    public void carregarContas() {  
        try {
             BufferedReader arq = new BufferedReader(new FileReader("usuarios.txt"));
        
             String linha = arq.readLine();
            
             while(linha != null){
                 System.out.println(linha);
                 linha = arq.readLine();
             }           
        }
        catch (Exception e) {
            System.out.println("Erro " + e.getMessage());
        }
    }
}
