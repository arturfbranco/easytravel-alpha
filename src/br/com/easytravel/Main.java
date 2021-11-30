package br.com.easytravel;

import br.com.easytravel.View.ClienteView;
import br.com.easytravel.View.CompanhiaAereaView;
import br.com.easytravel.View.GenericView;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner( System.in );
        int op = 0;
        criarLinha();
        System.out.println( "EASY TRAVEL" );
        System.out.println( "A forma certa de planejar suas viagens." );
        while ( op != 3 ){
            System.out.println( "Escolha uma das opções abaixo para começar." );
            System.out.println( "1. Sou Cliente.\n2. Sou Companhia Aérea.\n3. Fechar aplicativo." );
            op = scanner.nextInt();
            if( op == 1 ) GenericView.comecar( true );
            if( op == 2 ) GenericView.comecar( false );
            if( op != 1 && op != 2 && op != 3 ) opcaoInvalida();
            criarLinha();
        }
    }

    public static void criarLinha(){
        System.out.println( "--------------------------------------------" );
    }
    public static void opcaoInvalida(){
        System.out.println( "Opção inválida. Tente novamente." );
    }
}
