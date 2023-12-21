package br.ufla.gac103.s2021_2.ValhallaAdventure;
import br.ufla.gac103.s2021_2.baseJogo.EntidadeGrafica;

/**
 * Escreva uma descrição da classe Item aqui.
 * 
 * @Rafael Bastos Andrade, Igor Tavares Sales
 * @22/03/2022
 */
public class Item extends EntidadeGrafica
{
    private String nome;
    private String descricao;
    private int peso;
    private int forcaDeAtaque;
    /**
     * Construtor para objetos da classe Item
     */
    public Item(String descricao, String nome, int peso, String caminho, int forcaDeAtaque)
    {
        super(caminho);
        this.descricao = descricao;
        this.nome = nome;
        this.peso = peso;
        this.forcaDeAtaque = forcaDeAtaque;
    }
    //método que retorna a descrição do item
    public String getDescricao()
    {
        return descricao + " " + "que tem peso" + " " + peso;
    }
    //método que retorna o nome do item
    public String getNomeItem()
    {
        return nome;
    }
    //método que retorna o peso do item
    public int getPeso()
    {
        return peso;
    }
    
    public int getForcaDeAtaque()
    {
        return forcaDeAtaque;
    }

    @Override
    public String getNome()
    {
        return nome;
    }

}
