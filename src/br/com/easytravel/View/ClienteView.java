package br.com.easytravel.View;

import br.com.easytravel.Entity.*;
import br.com.easytravel.Main;
import br.com.easytravel.Service.ClienteService;
import br.com.easytravel.Service.ContratacaoService;
import br.com.easytravel.Service.PassagemService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ClienteView {

    private static final ClienteService clienteService = new ClienteService();
    private static final ContratacaoService contratacaoService = new ContratacaoService();
    private static final PassagemService passagemService = new PassagemService();

    public static void home(){
        Scanner scanner = new Scanner( System.in );
        int op = 0;
        Main.criarLinha();
        System.out.println( "ÁREA DO CLIENTE" );
        while ( op != 3 ){
            System.out.println( "Selecione uma opção para continuar." );
            System.out.println( "1. Meu Crédito.\n2. Minhas Contratações.\n3. Fazer logout." );
            Main.criarLinha();
            op = scanner.nextInt();
            if( op == 1 ) meuCredito();
            if( op == 2 ) minhasContratacoes();
            if( op == 3 ) clienteService.fazerLogout();
            if( op != 1 && op != 2 && op != 3 ) Main.opcaoInvalida();
        }
    }
    private static void meuCredito(){
        Scanner scanner = new Scanner( System.in );
        String op = "0";
        while ( !op.equals( "2" ) ){
            Main.criarLinha();
            System.out.println( "Crédito atual: R$ " + clienteService.getClienteEmSessao().getCredito()  );
            Main.criarLinha();
            System.out.println( "1. Adicionar mais crédito.\n2. Voltar." );
            op = scanner.nextLine();
            if( op.equals( "1" ) ){
                Main.criarLinha();
                System.out.println( "Insira o valor de crédito a ser adicionado." );
                System.out.println( "R$: " );
                String valor = scanner.nextLine();
                System.out.println( "Deseja finalizar o pagamento? (S/N)" );
                String confirmacao = scanner.nextLine().toUpperCase();
                if( confirmacao.equals( "S" ) ){
                    clienteService.adicionarCredito( Double.parseDouble( valor ) );
                    System.out.println( "O saldo foi creditado à sua conta com sucesso." );
                }
            }
            if( !op.equals( "1" ) && !op.equals( "2" ) ) Main.opcaoInvalida();
        }
    }
    private static void minhasContratacoes(){
        Scanner scanner = new Scanner( System.in );
        String op = "0";
        while( !op.equals( "6" ) ){
            Main.criarLinha();
            System.out.println( "MINHAS CONTRATAÇÕES" );
            System.out.println( "1. Todas as contratações\n2. Contratações pendentes." +
                    "\n3. Contratações consolidadas.\n4. Consolidar contratação." +
                    "\n5. Simular contratação.\n6. Voltar." );
            op = scanner.nextLine();
            if( op.equals( "1" ) ){
                Main.criarLinha();
                for( ContratacaoEntity contratacao : clienteService.buscarContratacoes() ){
                    imprimirContratacao( contratacao );
                }
            }
            if( op.equals( "2" ) ){
                Main.criarLinha();
                for( ContratacaoEntity contratacao : clienteService.buscarContratacoesPendentes() ){
                    imprimirContratacao( contratacao );
                }
            }
            if( op.equals( "3" ) ){
                Main.criarLinha();
                for( ContratacaoEntity contratacao : clienteService.buscarContratacoesConsolidadas() ){
                    imprimirContratacao( contratacao );
                }
            }
            if( op.equals( "4" ) ){
                System.out.println( "Informe o ID da contratação que deseja consolidar." );
                int idC = Integer.parseInt( scanner.nextLine() );
                ContratacaoEntity contratacao = contratacaoService.buscarPorId( idC );
                if( contratacao != null ){
                    if( contratacao.getValor() <= clienteService.getClienteEmSessao().getCredito() ){
                        System.out.println( "Contratação selecionada:" );
                        imprimirContratacao( contratacao );
                        System.out.println( "Deseja confirmar o pagamento da contratação? (S/N)" );
                        String confirmacao = scanner.nextLine().toUpperCase();
                        if( confirmacao.equals( "S" ) ){
                            ContratacaoEntity cPaga = clienteService.consolidarContratacao( idC );
                            if( cPaga != null ){
                                System.out.println( "Contratação paga com sucesso. O saldo já foi descontado da sua conta." );
                                imprimirContratacao( cPaga );
                            }else {
                                System.out.println( "Erro ao realizar pagamento da contratação. Verifique o ID e tente novamente." );
                            }
                        }
                    }else {
                        System.out.println( "Não há crédito suficiente na conta. Adicione mais crédito e tente novamente." );
                    }
                }else {
                    System.out.println( "Contratação não encontrada." );
                }
            }
            if( op.equals( "5" ) ){
                realizarSimulacao();
            }
        }
    }

    private static void realizarSimulacao(){
        List<ContratacaoPassagemEntity> carrinho = new ArrayList<>();
        Scanner scanner = new Scanner( System.in );
        String op = "0";
        Main.criarLinha();
        System.out.println( "NOVA CONTRATAÇÃO" );
        while ( !op.equals( "3" ) ){
            Main.criarLinha();
            System.out.println( "MEU CARRINHO" );
            for( ContratacaoPassagemEntity cp : carrinho ){
                imprimirContratacaoPassagem( cp );
            }
            Main.criarLinha();
            System.out.println( "1. Adicionar passagem.\n2. Finalizar.\n3. Cancelar." );
            op = scanner.nextLine();
            if( op.equals( "1" ) ){
                Main.criarLinha();
                System.out.println( "1. Ver todas passagens disponíveis." +
                        "\n2. Buscar por origem.\n3. Buscar por destino." );
                String filtro = scanner.nextLine();
                if( filtro.equals( "1" ) ){
                    for( PassagemEntity passagem : passagemService.buscarTodas() ){
                        GenericView.imprimirPassagem( passagem );
                    }
                }
                if( filtro.equals( "2" ) ){
                    System.out.println( "Informe a origem desejada:" );
                    for( PassagemEntity passagem : passagemService.buscarPorOrigem( scanner.nextLine().toUpperCase() ) ){
                        GenericView.imprimirPassagem( passagem );
                    }
                }
                if( filtro.equals( "3" ) ){
                    System.out.println( "Informe o destino desejado:" );
                    for( PassagemEntity passagem : passagemService.buscarPorDestino( scanner.nextLine().toUpperCase() ) ){
                        GenericView.imprimirPassagem( passagem );
                    }
                }
                Main.criarLinha();
                System.out.println( "Informe o ID da passagem a ser adicionada:" );
                System.out.println( "Para cancelar digite -1." );
                int id = Integer.parseInt( scanner.nextLine() );
                if( id == -1 ) break;
                PassagemEntity passagem = passagemService.buscarPorId( id );
                if( passagem != null ){
                    ContratacaoPassagemEntity cp = new ContratacaoPassagemEntity();
                    cp.setPassagem( passagem );
                    System.out.println( "Informe o nome completo do portador da passagem no momento do embarque:" );
                    cp.setPortador( scanner.nextLine().toUpperCase() );
                    carrinho.add( cp );
                }
            }
            if( op.equals( "2" ) ){
                if( carrinho.isEmpty() ){
                    System.out.println( "Não é possível finalizar uma contratação com o carrinho vazio." );
                }else {
                    ContratacaoEntity contratacaoFinalizada = clienteService.adicionarContratacao( carrinho );
                    if( contratacaoFinalizada != null ){
                        System.out.println( "Contratação finalizada com sucesso!" );
                        Main.criarLinha();
                        imprimirContratacao( contratacaoFinalizada );
                        break;
                    }
                }
            }
        }
    }

    private static void imprimirContratacao( ContratacaoEntity contratacao ){
        System.out.println( "ID: " + contratacao.getId() );
        System.out.println( "Valor: R$ " + contratacao.getValor() );
        String status = contratacao.isConsolidada() ? "CONSOLIDADA" : "PENDENTE";
        System.out.println( "Status: " + status );
        Main.criarLinha();
        for(ContratacaoPassagemEntity cp : contratacaoService.buscarPassagensDeContratacao( contratacao.getId() )){
            imprimirContratacaoPassagem( cp );
        }
        Main.criarLinha();
    }
    private static void imprimirContratacaoPassagem( ContratacaoPassagemEntity cp ){
        String idCp = cp.getId() == null ? "GERADO APÓS A CONTRATAÇÃO" : cp.getId().toString();
        System.out.println( "ID único de contratação da passagem: " + idCp );
        System.out.println( "Portador atribuído à passagem: " + cp.getPortador() );
        GenericView.imprimirPassagem( cp.getPassagem() );
        Main.criarLinha();
    }
}
