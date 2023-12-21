package br.ufla.gac103.s2021_2.ValhallaAdventure;
import br.ufla.gac103.s2021_2.baseJogo.InterfaceUsuario;
import br.ufla.gac103.s2021_2.baseJogo.EntidadeGrafica;
import java.util.Scanner;
/**
 * A classe Terminal trata entradas e saidas para o usuario.
 * 
 * Igor Tavares e Rafael Bastos 
 * @version 1.0
 */
public class Terminal implements InterfaceUsuario
{
    Scanner entrada = new Scanner(System.in);
    
    @Override 
    public void exibirMensagem(String mensagem){
        System.out.println(mensagem);
    }
    
    @Override
    public void continuarMensagem(String mensagem){
        System.out.println(mensagem);
    }
    
    @Override
    public String obterComando(){
        String linha = entrada.nextLine();;
        return linha;
    }
    
    @Override
    public String obterInformacao(String instrucao){
        instrucao = entrada.nextLine();;
        return instrucao;
    }
    
    @Override
    public void ambienteAtualMudou(EntidadeGrafica ambiente){

    }
    
    @Override
    public void jogadorPegouItem(EntidadeGrafica item){
        
    }
    
    @Override
    public void jogadorDescartouItem(EntidadeGrafica item){
        
    }  
}
