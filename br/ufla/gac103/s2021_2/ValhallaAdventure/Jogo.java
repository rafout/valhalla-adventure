package br.ufla.gac103.s2021_2.ValhallaAdventure;
import br.ufla.gac103.s2021_2.baseJogo.InterfaceUsuario;
import java.util.ArrayList;

/**
 *  Essa eh a classe principal da aplicacao "Valhalla Adventure".
 *  "Valhalla Adventure" eh um jogo de aventura muito simples, baseado em texto.
 *  Usuarios podem caminhar em um cenario, coletar itens e desbloquear passagens.
 * 
 *  Para jogar esse jogo, crie uma instancia dessa classe e chame o metodo
 *  "jogar".
 * 
 *  Essa classe principal cria e inicializa todas as outras: ela cria os
 *  ambientes, cria o analisador e comeca o jogo. Ela tambeme avalia e 
 *  executa os comandos que o analisador retorna.
 * 
 * @author  Michael Kölling and David J. Barnes (traduzido por Julio Cesar Alves)(editado e extendido por Igor e Rafael).
 * @version 2011.07.31 (2016.02.01) (2022.03.22)
 */

public class Jogo 
{
    private Analisador analisador;
    private Ambiente ambienteAtual;
    private Viking viking;
    private InterfaceUsuario interfaceUsuario;
    private ArrayList<String> usuarios;
    private DadosRecordes dados;
    private String jogador;
    private static int contadorDeMovimentos = 0;
        
    /**
     * Cria o jogo e incializa seu mapa interno.
     */
    public Jogo(InterfaceUsuario interfaceUsuario) 
    {
        this.interfaceUsuario = interfaceUsuario;
        criarAmbientes();
        analisador = new Analisador(interfaceUsuario);
        viking = new Viking("Guerreiro");
        dados = new DadosRecordes();
        interfaceUsuario.ambienteAtualMudou(ambienteAtual);
    }

    /**
     * Cria todos os ambientes e liga as saidas deles
     */
    private void criarAmbientes()
    {
        Ambiente salao, saidaDaVila, floresta, vila, cabana, salaDeArmas, cais, barco, taverna, terreiro, ferreiro, mar;
      
        // cria os ambientes
        salao = new Ambiente("Salão do Rei","no Salão do Rei.", new Bracelete("imagens/bracelete.png"), "imagens/salaoRei.jpg");
        saidaDaVila = new Ambiente("Saida da Vila","na saida da Vila.", "imagens/saidaVila.png");
        floresta = new Ambiente("Floresta","na floresta.", "imagens/floresta.png");
        vila = new Ambiente("Centro da Vila","no centro da Vila.", new Remo("imagens/remo.png"), "imagens/centro.png");
        cabana = new Ambiente("Cabana","na cabana dos guerreiros.", "imagens/cabanaGuerreiros.png");
        salaDeArmas = new Ambiente("Sala de Armas","na sala de armas.", new Machado("imagens/machado.png"), "imagens/salaDeArmas.png");
        cais = new Ambiente("Cais","no cais. \n Um inimigo vigia o barco, ataque-o para passar para o barco!", "imagens/cais.png");
        barco = new Ambiente("Barco","no barco Viking.", "imagens/barco.png");
        taverna = new Ambiente("Taverna","na taverna Viking. Aproveite para tomar um hidromel!", "imagens/taverna.png");
        terreiro = new Ambiente("Terreiro","no terreiro dos animais.", "imagens/terreiro.png");
        ferreiro = new Ambiente("Cabana do Ferreiro","na cabana do Ferreiro", "imagens/ferreiro.png");
        mar = new Ambiente("Mar","no mar, parabéns! Você conseguiu fugir da ilha", "imagens/mar.png");
        
        // inicializa as saidas dos ambientes
        salao.ajustarSaida("sul", vila);
        salao.ajustarSaida("leste", saidaDaVila);
        
        saidaDaVila.ajustarSaida("subir", floresta);
        saidaDaVila.ajustarSaida("oeste", salao);
        
        floresta.ajustarSaida("descer", saidaDaVila);
        
        vila.ajustarSaida("norte", salao);
        vila.ajustarSaida("sul", cais);
        vila.ajustarSaida("leste", cabana);
        vila.ajustarSaida("oeste", taverna);
        
        taverna.ajustarSaida("leste", vila);
        taverna.ajustarSaida("norte", terreiro);
        
        terreiro.ajustarSaida("norte", ferreiro);
        terreiro.ajustarSaida("sul", taverna);
        
        cabana.ajustarSaida("oeste", vila);
        cabana.ajustarSaida("baixo", salaDeArmas);
        
        salaDeArmas.ajustarSaida("cima", cabana);
        
        cais.ajustarSaida("norte", vila);
        // inicializa um abiente com saida bloqueada 
        cais.ajustarSaidaBloqueada("oeste", barco, "Machado");
        
        barco.ajustarSaida("leste", cais);
        barco.ajustarSaidaBloqueada("sul", mar, "Remo");
        
        ferreiro.ajustarSaida("sul", terreiro);
        ferreiro.ajustarSaidaBloqueada("", mar, "Bracelete");
        
        ambienteAtual = salao;  // o jogo comeca no Salão do Rei
    }

    /**
     *  Rotina principal do jogo. Fica em loop ate terminar o jogo.
     */
    public void jogar() 
    {            
        interfaceUsuario.exibirMensagem("Usuario que já zeraram o Valhalla Adventure:");
        dados.carregarContas();
        
        interfaceUsuario.continuarMensagem("Digite seu nome: ");
        jogador = interfaceUsuario.obterInformacao(jogador);
        
        imprimirBoasVindas();

        // Entra no loop de comando principal. Aqui nos repetidamente lemos
        // comandos e os executamos ate o jogo terminar.
                
        boolean terminado = false;
        while (!terminado) {
            Comando comando = analisador.pegarComando();
            terminado = processarComando(comando);
            if(ambienteAtual.getNome().equals("Mar")){
                dados.salvarUsuario(jogador, contadorDeMovimentos);
                terminado = true;
                interfaceUsuario.exibirMensagem("Você zerou o jogo! Parabéns!!!");
            }
        }
        interfaceUsuario.exibirMensagem("Obrigado por jogar. Ate mais!");
    }

    /**
     * Imprime a mensagem de abertura para o jogador.
     */
    private void imprimirBoasVindas()
    {
        interfaceUsuario.exibirMensagem("Bem-vindo ao Valhalla Adventure, " + jogador + "!");
        interfaceUsuario.continuarMensagem("Nesse jogo você é um Guerreiro Viking e está tentando sair da ilha.");
        interfaceUsuario.continuarMensagem("Digite 'ajuda' se voce precisar de ajuda.");
        
        exibirLocalizacaoAtual();
    }
    
    private void exibirLocalizacaoAtual()
    {
        interfaceUsuario.exibirMensagem("Voce esta " + ambienteAtual.getDescricao());
        
        interfaceUsuario.continuarMensagem("Saidas: " + ambienteAtual.getSaidas());
    }
    
    /**
     * Dado um comando, processa-o (ou seja, executa-o)
     * @param comando O Comando a ser processado.
     * @return true se o comando finaliza o jogo.
     */
    private boolean processarComando(Comando comando) 
    {
        boolean querSair = false;

        if(comando.ehDesconhecido()) {
            interfaceUsuario.exibirMensagem("Eu nao entendi o que voce disse...");
            return false;
        }

        String palavraDeComando = comando.getPalavraDeComando();
        if (palavraDeComando.equals("ajuda")) {
            imprimirAjuda();
        }
        else if (palavraDeComando.equals("ir")) {
            irParaAmbiente(comando);
        }
        else if (palavraDeComando.equals("sair")) {
            querSair = sair(comando);
        }
        else if (palavraDeComando.equals("observar")) {
            observar(comando);
        }
        else if (palavraDeComando.equals("pegar"))
        {
            pegar(comando);
        }
        else if (palavraDeComando.equals("usar"))
        {
            usar(comando);
        }

        return querSair;
    }
    
    // método que processa o comando usar(item)
    private void usar(Comando comando)
    {
        if(!comando.temSegundaPalavra()) {
            // se nao ha segunda palavra, nao sabemos o que usar...
            interfaceUsuario.exibirMensagem("Usar o que?");
            return;
        }
        String item = comando.getSegundaPalavra();
        
        if(viking.buscarItem(item) == null)
        {
            interfaceUsuario.exibirMensagem("Você não possui esse item!");            
        }
        else if(item.equalsIgnoreCase(ambienteAtual.getItemDesbloquearSaida()))
        {
            Item i = viking.buscarItem(item);
            ambienteAtual.usarItem(i);     
            viking.retirarItem(item);
            interfaceUsuario.exibirMensagem(ambienteAtual.verificarItem(item));
            interfaceUsuario.jogadorDescartouItem(i);
        }
        else
        {
            interfaceUsuario.exibirMensagem("Você não pode usar esse item nesse ambiente!");    
        }
        
    }
    
    // método que processa o comando pegar(item)
    private void pegar(Comando comando)
    {
        if(!comando.temSegundaPalavra()) {
            // se nao ha segunda palavra, nao sabemos o que pegar...
            interfaceUsuario.exibirMensagem("Pegar o que?");
            return;
        }       
        String item = comando.getSegundaPalavra();
        
        Item i = ambienteAtual.pegarItem(item);
        if(i==null){
            interfaceUsuario.exibirMensagem("Item não encontrado!");    
        }
        else{
            viking.guardarItem(i);
            interfaceUsuario.exibirMensagem("Item recolhido!");
            interfaceUsuario.jogadorPegouItem(i);
        }
    }
    
    // método que processa o comando observar(ambiente)
    private void observar(Comando comando)
    {
        interfaceUsuario.exibirMensagem("Você está ");
        interfaceUsuario.continuarMensagem(ambienteAtual.getDescricaoLonga());
        interfaceUsuario.continuarMensagem(viking.getNome() + " " + "está carregando: ");
        interfaceUsuario.continuarMensagem(viking.todosItens());
    }

    // Implementacoes dos comandos do usuario

    /**
     * Printe informacoes de ajuda.
     * Aqui nos imprimimos algo bobo e enigmatico e a lista de 
     * palavras de comando
     */
    private void imprimirAjuda() 
    {
        interfaceUsuario.exibirMensagem("Voce esta perdido. Voce esta sozinho. Voce caminha");
        interfaceUsuario.continuarMensagem(" pela aldeia Viking.");
        interfaceUsuario.continuarMensagem("Suas palavras de comando sao: ");
        interfaceUsuario.continuarMensagem(" " + analisador.getComandos());
    }

    /** 
     * Tenta ir em uma direcao. Se existe uma saida entra no 
     * novo ambiente, caso contrario imprime mensagem de erro.
     */
    private void irParaAmbiente(Comando comando) 
    {
        if(!comando.temSegundaPalavra()) {
            // se nao ha segunda palavra, nao sabemos pra onde ir...
            interfaceUsuario.exibirMensagem("Ir pra onde?");
            return;
        }

        String direcao = comando.getSegundaPalavra();

        // Tenta sair do ambiente atual
        Ambiente proximoAmbiente = ambienteAtual.getSaida(direcao);

        if (proximoAmbiente == null) {
            interfaceUsuario.exibirMensagem("Nao ha passagem!");
        }
        else {
            ambienteAtual = proximoAmbiente;
            
            exibirLocalizacaoAtual();
            interfaceUsuario.ambienteAtualMudou(proximoAmbiente);
            contadorDeMovimentos++;
        }
    }

    /** 
     * "Sair" foi digitado. Verifica o resto do comando pra ver
     * se nos queremos realmente sair do jogo.
     * @return true, se este comando sai do jogo, false, caso contrario
     */
    private boolean sair(Comando comando) 
    {
        if(comando.temSegundaPalavra()) {
            interfaceUsuario.exibirMensagem("Sair o que?");
            return false;
        }
        else {
            return true;  // sinaliza que nos queremos sair
        }
    }
}
