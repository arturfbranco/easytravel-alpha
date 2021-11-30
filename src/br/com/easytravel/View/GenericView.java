package br.com.easytravel.View;

import br.com.easytravel.Entity.*;
import br.com.easytravel.Main;
import br.com.easytravel.Service.ClienteService;
import br.com.easytravel.Service.CompanhiaAereaService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GenericView {
    private static final ClienteService clienteService = new ClienteService();
    private static final CompanhiaAereaService companhiaAereaService = new CompanhiaAereaService();

    public static void comecar( boolean isCliente ){
        Scanner scanner = new Scanner( System.in );
        int op = 0;
        while ( op != 3 ){
            System.out.println( "Já possui uma conta?\n1. Fazer login.\n2. Cadastrar uma conta.\n3. Voltar à Tela Inicial." );
            op = scanner.nextInt();
            if( op == 1 ) fazerLogin( isCliente );
            if( op == 2 ) fazerCadastro( isCliente );
            if( op != 1 && op != 2 && op != 3 ) Main.opcaoInvalida();
        }
    }

    private static void fazerCadastro( boolean isCliente ){
        AccountEntity cadastro = solicitarDados();
        if( cadastro.getContatos().isEmpty() ){
            System.out.println( "Ops! Parece que você não adicionou nenhum contato. Tente novamente." );
            return;
        }
        boolean sucesso = false;
        if( isCliente ){
            ClienteEntity cliente = solicitarDados( new ClienteEntity( cadastro ) );
            sucesso = clienteService.cadastrarCliente( cliente );
        }else {
            CompanhiaAereaEntity companhiaAerea = solicitarDados( new CompanhiaAereaEntity( cadastro ) );
            sucesso = companhiaAereaService.cadastrarcompanhiaAerea( companhiaAerea );
        }
        if( sucesso ){
            System.out.println( "Cadastro realizado com sucesso." );
        }else {
            System.out.println( "Ops! Ocorreu um problema ao finalizar seu cadastro. Verifique os dados e tente novamente." );
        }
        Main.criarLinha();
    }

    private static AccountEntity solicitarDados() {
        Scanner scanner = new Scanner( System.in );
        Main.criarLinha();
        AccountEntity cadastro = new AccountEntity();
        System.out.println( "Precisaremos agora de alguns dados seus." );
        System.out.println( "Username para acesso à plataforma:" );
        cadastro.setUsername( scanner.nextLine() );
        System.out.println( "Senha:" );
        cadastro.setSenha( scanner.nextLine() );
        System.out.println( "Nome:" );
        cadastro.setNome( scanner.nextLine().toUpperCase() );
        cadastro.setContatos( solicitarContatos() );
        return cadastro;
    }
    private static ClienteEntity solicitarDados( ClienteEntity cliente ){
        Scanner scanner = new Scanner( System.in );
        System.out.println( "CPF:" );
        cliente.setCpf( scanner.nextLine() );
        return cliente;
    }
    private static CompanhiaAereaEntity solicitarDados( CompanhiaAereaEntity companhiaAerea ){
        Scanner scanner = new Scanner( System.in );
        System.out.println( "CNPJ:" );
        companhiaAerea.setCnpj( scanner.nextLine() );
        return companhiaAerea;
    }

    private static List<ContatoEntity> solicitarContatos(){
        Main.criarLinha();
        List<ContatoEntity> contatos = new ArrayList<>();
        Scanner scanner = new Scanner( System.in );
        int seletor = 0;
        System.out.println( "Adição de contatos. É necessário que seja fornecido ao menos um meio de contato." );
        while ( seletor != 2 ){
            int seletorTipoContato = 0;
            Main.criarLinha();
            System.out.println( "1. Adicionar Contato." );
            System.out.println( "2. Finalizar." );
            seletor = scanner.nextInt();
            if( seletor != 1 && seletor != 2 ) System.out.println( "Opção inválida. Tente novamente." );
            if( seletor == 1 ){
                TipoContatoEnum tipoContato = null;
                System.out.println( "Qual o tipo de contato que deseja adicionar?" );
                System.out.println( "1. Telefone\n2. WhatsApp\n3. E-mail" );
                seletorTipoContato = scanner.nextInt();
                if( seletorTipoContato == 1 ) tipoContato = TipoContatoEnum.TELEFONE;
                if( seletorTipoContato == 2 ) tipoContato = TipoContatoEnum.WHATSAPP;
                if( seletorTipoContato == 3 ) tipoContato = TipoContatoEnum.EMAIL;
                System.out.println( "Informe o contato:" );
                scanner.nextLine();
                String valor = scanner.nextLine();
                if ( tipoContato != null ){
                    contatos.add( new ContatoEntity( tipoContato, valor ) );
                }
            }
        }
        return contatos;
    }

    private static void fazerLogin( boolean isCliente ){
        Scanner scanner = new Scanner( System.in );
        Main.criarLinha();
        System.out.println( "Username:" );
        String username = scanner.nextLine();
        System.out.println( "Senha:" );
        String senha = scanner.nextLine();
        boolean sucesso = isCliente ? clienteService.fazerLogin( username, senha )
                : companhiaAereaService.fazerLogin( username, senha );
        if ( sucesso ){
            System.out.println( "Autenticação performada com sucesso." );
            Main.criarLinha();
            if( isCliente ){
                ClienteView.home();
            }else {
                CompanhiaAereaView.home();
            }
        }else {
            System.out.println( "Falha na autenticação. Redirecionando-o à tela inicial." );
            Main.criarLinha();
        }
    }

    public static void imprimirPassagem( PassagemEntity passagem ){
        System.out.println( "ID da passagem: " + passagem.getId() );
        System.out.println( "Companhia aérea: " + passagem.getCompanhiaAerea().getNome() );
        System.out.println( "Local de origem: " + passagem.getOrigem().getNome() );
        System.out.println( "Local de destino: " + passagem.getDestino().getNome() );
        System.out.println( "Data: " + passagem.getData() );
        System.out.println( "Preço unitário: R$ " + passagem.getPrecoUnitario() );
        Main.criarLinha();
    }
}
