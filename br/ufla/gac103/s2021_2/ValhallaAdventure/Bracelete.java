package br.ufla.gac103.s2021_2.ValhallaAdventure;
/**
 * Classe Bracelete - o item bracelete do jogo Valhalla Adventure
 * 
 * @author (Rafael Bastos Andrade, Igor Tavares Sales) 
 * @version (22/03/2022)
 */
public class Bracelete extends Item
{
    private String braceleteDosDeuses;
    
    public Bracelete(String caminho)
    {
        super("de couro", "Bracelete", 5, caminho, 0);
        braceleteDosDeuses = "de Odin";
    }
    // método para retornar o nome do deus
    public String getBraceleteDosDeuses()
    {
        return braceleteDosDeuses;
    }
}
