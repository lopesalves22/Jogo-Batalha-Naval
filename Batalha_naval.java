/*
 Batalha naval: desenvolvido por welton
        1- jogador
        2- computador / outro jogador       
 */
package batalha_naval;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
public class Batalha_naval {
    
    static String nomeJogador1, nomeJogador2; //ver metodo obterNomesDosJogadores
    static int tamanhoX, tamanhoY;
    static int tabuleirojogador1[][], tabuleirojogador2[][];
    static int quantidadeDeNavios, limiteMaximoDeNavios;
    Scanner input = new Scanner(System.in);
    
    /*
    metodo para obter o tamanho do tabuleiro
    */
    public static void obterTamanhoDosTabuleiros(){
            /*
            Aqui e opcional caso o jogador queira dimensional do tamanho da tela
            */
            Scanner input;  
        try{
            input = new Scanner(System.in);
            System.out.println("Digite a quantidade de linhas no tabuleiro");
            tamanhoX = input.nextInt();
            System.out.println("Digite a quantidade de colunas no tabuleiro");
            tamanhoY = input.nextInt();
            
        }
        catch(InputMismatchException erro){
            System.out.println("Digite um valor numerico");
        }
     
    }
    //criado nova instancia do jogador 1 e 2 ver metodo "retornarNovoTabuleiroVazio"
    private static void iniciandoOsTamanhosDosTabuleiros(){
            tabuleirojogador1 = retornarNovoTabuleiroVazio();
            tabuleirojogador2 = retornarNovoTabuleiroVazio();
    }
     //informar a quantidade de navios
     public static void quantidadeDeNavios(){
        Scanner input = new Scanner(System.in);
        System.out.println("Digite a quantidade de navios do jogo");
        System.out.println("MAX: " + limiteMaximoDeNavios + " navios");
        quantidadeDeNavios = input.nextInt();
        if(quantidadeDeNavios < 1 && quantidadeDeNavios > limiteMaximoDeNavios){
            quantidadeDeNavios = limiteMaximoDeNavios; //aqui tenho uma regra que caso ele queira mais que 8 navios
        }
    }
     //informar a quantidade maxima de navios
    public static void calcularQuantidadeMaximaDeNaviosNoJogo(){
        limiteMaximoDeNavios = (tamanhoX * tamanhoY) / 3;// no caso 10 x 10 = 100 /3 = 33,3....
    }
    //criando um tabuleiro do zero
    public static int[][] retornarNovoTabuleiroVazio(){
        return new int[tamanhoX][tamanhoY];
        //neste caso o tabuleiro e bidimensional
    }
    //criando o tabuleiro com os navios dos jogadores
    public static int[][] RetornarNovoTabuleiroComOsNavios(){
        //aqui realizo a logica de organização dos navios no tabuleiro
        int novoTabuleiro[][] = retornarNovoTabuleiroVazio();
        int quantidadeRestanteDeNavios = quantidadeDeNavios;  //organizar os navios no tabuleiro
        int x=0, y=0;
        Random numeroAleatorio = new Random();
        do{
            x=0;
            y=0;
            for(int[] linha: novoTabuleiro){  //para cada linha possui sua coluna
            for (int coluna : linha){
                if(numeroAleatorio.nextInt(100) <= 10){ //10% fr chance de colocar aqui
                    if(coluna == 0){  //se não tiver navios
                        novoTabuleiro[x][y] = 1; //logica  para prencher os navios nos tabuleiros
                        quantidadeRestanteDeNavios--;  //decremendo da coluna onde ficará o navio
                        break;
                    }        
                }
                            //verificar se a quantidade de navios acabou 
                            if(quantidadeRestanteDeNavios <= 0){
                                    break;
                                }
                            y++;
                        } y = 0;x++;
                    }
                        if(quantidadeRestanteDeNavios <= 0){
                                    break;
                                }
                    }while (quantidadeRestanteDeNavios > 0);
        return novoTabuleiro;
    }
    
    public static void inserirOsNaviosNosTabuleirosDosJogadores(){
       tabuleirojogador1 = RetornarNovoTabuleiroComOsNavios();
       tabuleirojogador2 = RetornarNovoTabuleiroComOsNavios();
    }
    
    public static void exibirTabuleiro(String nomeDoJogador, int[][] tabuleiro, boolean seuTabuleiro){ //metodo para preencher nome
         /*
        Criando o visual do tabuleiro
        */
        System.out.println("|-----" + nomeDoJogador + " -----|");
        /*
            inserindo as letras
        */
        char letraDaColuna = 65;
        String letrasDoTabuleiro = "    ";
        for(int i = 0; i< tamanhoY; i++){
            letrasDoTabuleiro += (letraDaColuna++) + " ";
            
        }
        System.out.println(letrasDoTabuleiro);
        /*
            fim
        */
        String linhaDotabuleiro = "";
        int numeroDaLinha = 1;
        for(int[] linha : tabuleiro){
            linhaDotabuleiro = +(numeroDaLinha++)+ " |";  //quanto mais linhas eu tiver mais incrementa
            for (int coluna : linha){
                switch(coluna){
                    case 0: //vazio
                        linhaDotabuleiro += " |";
                        break;
                    case 1: //possui navio
                        if(seuTabuleiro){      //se for o tabuleiro do jpgador ele mostra N
                        linhaDotabuleiro += "■|";
                        break;   
                        }
                        else{  //caso não mostra vazio
                            linhaDotabuleiro += " |";
                            break;
                        }
                       
                    case 2: //erro
                        linhaDotabuleiro += "X|";
                        break;
                    case 3: //acertou
                        linhaDotabuleiro += "†|";
                        break;
                }
            }System.out.println(linhaDotabuleiro);
        }
    }
    public static void obterNomesDosJogadores(){
        Scanner input = new Scanner(System.in);
        System.out.println("Digite o nome do jogador");
        nomeJogador1 = input.next();
        /*System.out.println("Digite o nome do jogador2");
        nomeJogador2 = input.next();*/
    }
    
    public static void exibirTabuleiroDosJogadores(){ //metodo para criar o novo tabuleiro para ambos
        exibirTabuleiro(nomeJogador1, tabuleirojogador1, true); //chama o nome e mais o tabuleiro do jogador
        //exibirTabuleiro(nomeJogador2, tabuleirojogador2, false);
    }
    
    public static void TiroDoJogador(){
         /*
        Configurando o tiro do jogador
        */
        
        System.out.println("Digite a posição do seu tiro ex: a1");
        Scanner input = new Scanner(System.in);
        String tiroDoJogador = input.next();
        //verificar o que o usuario está digitando
        int quantidadeDeNumeros = (tamanhoY > 10) ? 2 : 1;
        //valid~ção se realmente está digitando o valor correto
        String expressaoDeVerificacao = "^[A-Za-z]{1}[0-9]{"+quantidadeDeNumeros+"}$";
        if (tiroDoJogador.matches(expressaoDeVerificacao)){
            System.out.println("Navio destruido!!!");
            String tiro = tiroDoJogador.toLowerCase();
            int posicaoX = tiro.charAt(0) - 97; //a posiçãoX "a" é 97 primeiro vetor da posição
            int posicaoY = Integer.parseInt(tiro.substring(1)) - 1;
        //verificação de posição
            System.out.println((int) posicaoX);         
        }else{
            System.out.println("ERROUUUUUUUUUU");
            
        }
        
        
        
    }
    public static int [] retornarPosicoesDigitadasPeloJogador(String tiroDoJogador){
        
        //retornar as posições digitadas pelo jogador
        String tiro = tiroDoJogador.toLowerCase();
        int[] retorno = new int[2];
        retorno[0] = tiro.charAt(0) -97;
        retorno[1] = Integer.parseInt(tiro.substring(1))-1;
        
        /*
       ****************** ALTERAR OS CARACTERES NO TABULEIRO ******************
        */
        int[] posicao = retornarPosicoesDigitadasPeloJogador(tiroDoJogador);
        if (tabuleirojogador1[posicao[0]][posicao[1]] == 1){
            tabuleirojogador1[posicao[0]][posicao[1]] = 3;
        }
        else{
            tabuleirojogador1[posicao[0]][posicao[1]] = 2;
        }
       
        return retorno;
    }
   
        

    public static void Loop(){
        boolean jogoAtivo =true;
        do{
            exibirTabuleiroDosJogadores();
            TiroDoJogador(); 
            if (jogoAtivo = true){
                //verificar fim do jogo
            }
        }while(jogoAtivo);
    }
    
    public static void main(String[] args) {
        obterNomesDosJogadores();
        obterTamanhoDosTabuleiros(); //iniciado o metodo do tamanho
        calcularQuantidadeMaximaDeNaviosNoJogo();//calculado da quantidade maxima de navios
        iniciandoOsTamanhosDosTabuleiros(); //iniciado o metodo dos jogadores
        quantidadeDeNavios(); //chamada para quantidade de navios
        inserirOsNaviosNosTabuleirosDosJogadores(); //inserindo os navios no tabuleiro de forma aleatória
        exibirTabuleiroDosJogadores();
        //TiroDoJogador();
        Loop();
        
        
       
      
        
        
        
    }
   
    
}
