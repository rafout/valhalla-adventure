package br.ufla.gac103.s2021_2.ValhallaAdventure;
import br.ufla.gac103.s2021_2.baseJogo.Tela;

public class ProgramaGrafico
{
    public static void main(String[] args) {
        Jogo jogo = new Jogo(new Tela("Valhalla Adventure"));
        
        jogo.jogar();
    }
}
