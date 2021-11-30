package br.com.easytravel.View;

import br.com.easytravel.Entity.*;
import br.com.easytravel.Main;
import br.com.easytravel.Service.CompanhiaAereaService;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class CompanhiaAereaView {

    private static final CompanhiaAereaService companhiaAereaService = new CompanhiaAereaService();

    public static void home(){
        Scanner scanner = new Scanner( System.in );
        int op = 0;
        Main.criarLinha();
        System.out.println( "ÁREA DA COMPANHIA AÉREA" );
        while ( op != 4 ){
            System.out.println( "1. Ver passagens.\n2. Adicionar passagem." +
                    "\n3. Ver saldo.\n4. Fazer logout." );
            op = scanner.nextInt();
            if( op == 1 ) verPassagens();
            if( op == 2 ) adicionarPassagem();
            if( op == 3 ) verSaldo();
            if( op == 4 ) companhiaAereaService.fazerLogout();
            if( op != 1 && op != 2 && op != 3 && op != 4 ) Main.opcaoInvalida();
        }
    }
    private static void verPassagens(){
        Main.criarLinha();
        System.out.println( "Passagens cadastradas na plataforma pela empresa:" );
        Main.criarLinha();
        for( PassagemEntity passagem : companhiaAereaService.verMinhasPassagens() ){
            GenericView.imprimirPassagem( passagem );
        }
        Main.criarLinha();
    }
    private static void adicionarPassagem(){
        Scanner scanner = new Scanner( System.in );
        PassagemEntity passagem = new PassagemEntity();
        Main.criarLinha();
        System.out.println( "Informe os dados referentes a passagem aérea que deseja adicionar ao sistema." );
        System.out.println( "ORIGEM:" );
        passagem.setOrigem( new LocalEntity( scanner.nextLine().toUpperCase() ) );
        System.out.println( "DESTINO:" );
        passagem.setDestino( new LocalEntity(scanner.nextLine().toUpperCase() ) );
        System.out.println( "PREÇO:" );
        System.out.println( "*Digite o valor no formato xxx.xx" );
        passagem.setPrecoUnitario( Double.parseDouble( scanner.nextLine() ) );
        System.out.println( "Data:" );
        System.out.println( "*Digite a data no formato YYYY-MM-DD" );
        passagem.setData( getData() );
        PassagemEntity passagemSalva = companhiaAereaService.adicionarPassagem( passagem );
        if( passagemSalva != null ){
            System.out.println( "Passagem registrada com sucesso." );
            Main.criarLinha();
            GenericView.imprimirPassagem( passagemSalva );
        }
    }
    private static void verSaldo(){
        Main.criarLinha();
        System.out.println( "Saldo total de "
                + companhiaAereaService.getCompanhiaAereaEmSessao().getNome() + ": R$ "
                + companhiaAereaService.getCompanhiaAereaEmSessao().getSaldo() );
        Main.criarLinha();
    }

    private static LocalDate getData(){
        Scanner scanner = new Scanner( System.in );
        LocalDate data = null;
        try {
            return LocalDate.parse( scanner.nextLine() );
        }catch ( DateTimeParseException e ){
            System.out.println( "Data inserida no formato incorreto! Tente novamente." );
            return getData();
        }
    }
}
