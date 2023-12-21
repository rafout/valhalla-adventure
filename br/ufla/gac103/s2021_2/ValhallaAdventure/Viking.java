package br.ufla.gac103.s2021_2.ValhallaAdventure;
import java.util.ArrayList;
/**
 * A classe Viking é a classe que cria o jogador para o jogo.
 * 
 * @author (Rafael Bastos Andrade) 
 * @version (22/03/2022)
 */
public class Viking
{
    //atributos da classe Viking
    private String nome;
    private ArrayList<Item> bolsaViking;
    
    //construr da classe Viking que recebe o nome como parâmetro
    public Viking(String nome)
    {
        this.nome = nome;
        bolsaViking = new ArrayList<Item>();
    }
    // método que retorna o nome 
    public String getNome()
    {
        return nome;
    }
    // método para adicionar(guardar) um item na bolsa do Viking
    public void guardarItem(Item item)
    {
        if(item!=null){
            bolsaViking.add(item);
        }
    }
    /* método para remover(retirar) um item da bolsa Viking, retorna o 
     * item removido se ele estiver na bolsa e retorna null se ele não estiver*/
    public Item retirarItem(String nomeItem)
    {
        Item i = buscarItem(nomeItem);
        if(i!=null)
        {
            Item removido = i;
            bolsaViking.remove(i);
            return removido;
        }
        return null;
    }
    /* método para verificar se um item está na bolsa a partir do seu nome
     * retorna null se o item não estiver e retorna o próprio item se 
       ele estiver */
    public Item buscarItem(String nomeItem)
    {
        for(Item i: bolsaViking)
        {
            if(i.getNome().equalsIgnoreCase(nomeItem))
            {
                return i;    
            }
        }
        return null;
    }
    // método para exibir todos os itens contidos na bolsa viking
    public String todosItens()
    {
        String listaItens = "";
        for(Item i: bolsaViking)
        {
            listaItens = listaItens + " " + i.getNome() + " ";
        }
            return listaItens;
    }
}
