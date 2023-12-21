package br.ufla.gac103.s2021_2.ValhallaAdventure;
/**
 * Classe Remo - o item remo do jogo Valhalla Adventure
 * 
 * @author (Rafael Bastos Andrade, Igor Tavares Sales) 
 * @version (22/03/2022)
 */
public class Remo extends Item
{
    private int velocidadeRemada;
    
    public Remo(String caminho)
    {
        super("de madeira", "Remo", 10, caminho, 0);
        velocidadeRemada = 15;
    }
    // método para retornar a velocidade de remada
    public int getVelocidadeRemada()
    {
        return velocidadeRemada;
    }

}
