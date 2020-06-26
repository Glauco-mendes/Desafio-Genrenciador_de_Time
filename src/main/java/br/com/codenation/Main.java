package br.com.codenation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class Main {

    public static void main(String[] args){

        DesafioMeuTimeApplication meuTime = new DesafioMeuTimeApplication();

        meuTime.incluirTime(1l,"Teste", LocalDate.now(),"Branco","Vermelho");


        meuTime.incluirTime(2l,"Teste2", LocalDate.now(),"Azul","Amarelo");

        meuTime.incluirJogador(1l, 1l, "Jogador1", LocalDate.now(), 40, BigDecimal.valueOf(20));
        meuTime.incluirJogador(2l, 1l, "Jogador2", LocalDate.now(), 40, BigDecimal.valueOf(50));
        meuTime.incluirJogador(3l, 2l, "Jogador3", LocalDate.now(), 60, BigDecimal.valueOf(90));

        meuTime.definirCapitao(1l);

        System.out.println(meuTime.buscarCapitaoDoTime(1l));

        meuTime.definirCapitao(2l);

        System.out.println(meuTime.buscarCapitaoDoTime(1l));

        meuTime.definirCapitao(3l);



        List<Long> J = meuTime.buscarJogadoresDoTime(1l);
             for(long id:J)
                System.out.println(id);


           System.out.println(meuTime.buscarMelhorJogadorDoTime(1l)) ;
        System.out.println("Times") ;
        meuTime.buscarTimes().forEach(System.out::println);

        System.out.println("Jogadores do Times") ;
        meuTime.buscarJogadoresDoTime(1l).forEach(System.out::println);

        System.out.println("Melhores") ;

           meuTime.buscarTopJogadores(2).forEach(System.out::println);

    }


}
