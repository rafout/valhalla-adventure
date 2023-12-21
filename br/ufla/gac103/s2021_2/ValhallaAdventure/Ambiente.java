package br.ufla.gac103.s2021_2.ValhallaAdventure;
import java.util.HashMap;
import br.ufla.gac103.s2021_2.baseJogo.EntidadeGrafica;

/**
 * Classe Ambiente - um ambiente em um jogo adventure.
 *
 * Esta classe eh parte da aplicacao "World of Zuul".
 * "World of Zuul" eh um jogo de aventura muito simples, baseado em texto.  
 *
 * Um "Ambiente" representa uma localizacao no cenario do jogo. Ele eh
 * conectado aos outros ambientes atraves de saidas. As saidas sao
 * nomeadas como norte, sul, leste e oeste. Para cada direcao, o ambiente
 * guarda uma referencia para o ambiente vizinho, ou null se nao ha
 * saida naquela direcao.
 * 
 * @author  Michael Kölling and David J. Barnes (traduzido por Julio Cesar Alves)
 * @version 2011.07.31 (2016.02.01)
 */
public class Ambiente extends EntidadeGrafica
{
    private String nome;
    private String descricao;
    private HashMap<String, Ambiente> saidas;
    private Item item;
    private String saidaBloqueada;
    private String itemDesbloquearSaida;
    
    public Ambiente getSaida(String direcao)
    {
        if(direcao.equalsIgnoreCase(saidaBloqueada))
        {
            return null;
        }
        return saidas.get(direcao);    
    }
    /**
     * Cria um ambiente com a "descricao" passada. Inicialmente, ele
     * nao tem saidas. "descricao" eh algo como "uma cozinha" ou
     * "
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "um jardim aberto".
     * @param descricao A descricao do ambiente.
     */
    public Ambiente(String nome, String descricao, String caminho) 
    {
        super(caminho);
        this.nome = nome;
        this.descricao = descricao;
        saidas = new HashMap<String, Ambiente>();
        saidaBloqueada = null;
        itemDesbloquearSaida = null;
    }
    
    public Ambiente(String nome, String descricao, Item item, String caminho)
    {
        this(nome, descricao, caminho);
        this.item = item;
    }
    
    public void ajustarSaidaBloqueada(String direcao, Ambiente ambiente, String itemParaDesbloqueio)
    {
        ajustarSaida(direcao, ambiente);
        saidaBloqueada = direcao;
        itemDesbloquearSaida = itemParaDesbloqueio;
    }

    /**
     * Define as saidas do ambiente. Cada direcao ou leva a um
     * outro ambiente ou eh null (nenhuma saida para la).
     * @param norte A saida norte.
     * @param leste A saida leste.
     * @param sul A saida sul.
     * @param oeste A saida oeste.
     */
    public void ajustarSaida(String direcao, Ambiente ambiente) 
    {
        saidas.put(direcao, ambiente); 
    }

    /**
     * @return A descricao do ambiente.
     */
    public String getDescricao()
    {
        return descricao;
    }
    
    public String getDescricaoLonga()
    {
        String desc = descricao + "\n" + "Saidas: " + getSaidas() + "\n";
        if(item != null)
        {
            desc += " " + "Há um(a) " + item.getNome() + " " + item.getDescricao();
        }
        else
        {
            desc += " Não há nada aqui.";
        }
        return desc;
    }
    
    public String getSaidas()
    {
        String saidasTexto = "";
        for(String direcao : saidas.keySet())
        {
            saidasTexto = saidasTexto + direcao + " ";
        }
        return saidasTexto;
    }
    // método que verifica se existe um item no ambiente
    public boolean existeItem()
    {
        if(item != null)
        {
            return true;
        }
        return false;
    }
    // método que retorna o nome do item se ele existir
    public String getNomeItem()
    {
        if(existeItem())
        {
            return item.getNome();
        }
        return " ";
    }
    // método que retorna o item se ele existir
    public Item pegarItem(String nomeItem)
    {
        if(existeItem() && getNomeItem().equalsIgnoreCase(nomeItem))
        {
            Item i = item;
            item = null;
            return i;
        }
        else if(existeItem() && !getNomeItem().equalsIgnoreCase(nomeItem))
        {
            return null;
        }
            return null;
    }
    /** método para usar o item e desbloquear a saida, 
      e retorna true se a saida for desbloqueada
       e false caso contrario*/  
    public boolean usarItem(Item item)
    {
        if(item.getNome().equalsIgnoreCase(itemDesbloquearSaida))
        {
            saidaBloqueada = null;
            return true;
        }
        return false;
    }
    // retorna o item que desbloqueia a saida
    public String getItemDesbloquearSaida()
    {
        return itemDesbloquearSaida;
    }
    // retorna uma string de acordo com o nome do item passado
    public String verificarItem(String i)
    {
        if(i.equalsIgnoreCase("machado")){
            return "Você matou o inimigo, passagem para o barco desbloqueada!";
        }
        else if(i.equalsIgnoreCase("remo")){
            return "Você conseguiu fugir da ilha! Vá para o mar para zerar o jogo!";
        }
        else if(i.equalsIgnoreCase("bracelete")){
            return "Você deu o bracelete ao ferreiro e ele aumentou a força do seu machado!";
        }
            return "Você não possui esse item";
    }
    
    @Override
    public String getNome()
    {
        return nome;
    }
}
