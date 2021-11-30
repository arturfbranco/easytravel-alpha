package br.com.easytravel.Service;

import br.com.easytravel.Entity.ClienteEntity;
import br.com.easytravel.Entity.ContratacaoEntity;
import br.com.easytravel.Entity.ContratacaoPassagemEntity;
import br.com.easytravel.Repository.ClienteRepository;

import java.util.List;
import java.util.stream.Collectors;

public class ClienteService {

    private static ClienteEntity clienteEmSessao;
    private final ClienteRepository clienteRepository = new ClienteRepository();
    private final ContatoService contatoService = new ContatoService();
    private final ContratacaoService contratacaoService = new ContratacaoService();
    private final CompanhiaAereaService companhiaAereaService = new CompanhiaAereaService();

    public boolean fazerLogin( String username, String senha ){
        ClienteEntity cliente = this.clienteRepository.findByUsername( username );
        if( cliente != null ){
            if( cliente.getSenha().equals( senha ) ){
                clienteEmSessao = cliente;
                return true;
            }
        }
        return false;
    }
    public void fazerLogout(){
        clienteEmSessao = null;
    }

    public boolean cadastrarCliente(ClienteEntity cliente ){
        if( this.usernameNaoExistente( cliente.getUsername() ) ){
            cliente.setContatos( this.contatoService.cadastrarContatos( cliente.getContatos() ) );
            this.clienteRepository.createCliente( cliente );
            return true;
        }
        return false;
    }

    public boolean atualizarCliente( ClienteEntity cliente, Integer id ){
        if( this.usernameNaoExistente( cliente.getUsername() ) ){
            cliente.setContatos( this.contatoService.cadastrarContatos( cliente.getContatos() ) );
            clienteEmSessao = this.clienteRepository.updateCliente( cliente, id );
            return true;
        }
        return false;
    }

    private boolean usernameNaoExistente( String username ){
        return this.clienteRepository.findByUsername( username ) == null;
    }

    public ClienteEntity getClienteEmSessao(){
        return clienteEmSessao;
    }

    public void adicionarCredito( Double valor ){
        clienteEmSessao.setCredito( clienteEmSessao.getCredito() + valor );
        clienteEmSessao = this.clienteRepository.updateCliente( clienteEmSessao, clienteEmSessao.getId() );
    }

    public ContratacaoEntity adicionarContratacao(List<ContratacaoPassagemEntity> cps){
        ContratacaoEntity contratacao = this.contratacaoService.criarContratacao( cps );
        clienteEmSessao.adicionarContratacao( contratacao );
        clienteEmSessao = this.clienteRepository.updateCliente( clienteEmSessao, clienteEmSessao.getId() );
        return contratacao;
    }
    public List<ContratacaoEntity> buscarContratacoes(){
        return clienteEmSessao.getContratacoes();
    }
    public List<ContratacaoEntity> buscarContratacoesPendentes(){
        return clienteEmSessao.getContratacoes()
                .stream()
                .filter( c -> !c.isConsolidada() )
                .collect( Collectors.toList() );
    }
    public List<ContratacaoEntity> buscarContratacoesConsolidadas(){
        return clienteEmSessao.getContratacoes()
                .stream()
                .filter( c -> c.isConsolidada() )
                .collect( Collectors.toList() );
    }
    public ContratacaoEntity consolidarContratacao( Integer contratacaoId ){
        if( this.validarIdContratacao( contratacaoId ) ){
            this.transferirValores( contratacaoId );
            return this.contratacaoService.consolidarContratacao( contratacaoId );
        }
        return null;
    }
    private boolean validarIdContratacao( Integer id ){
        return !this.contratacaoService.buscarPorId( id ).isConsolidada()
                && clienteEmSessao.getContratacoes()
                .stream()
                .anyMatch( c -> c.getId().equals( id ) );
    }
    private void transferirValores( Integer contratacaoId ){
        ContratacaoEntity contratacao = this.contratacaoService.buscarPorId( contratacaoId );
        clienteEmSessao.setCredito( clienteEmSessao.getCredito() - contratacao.getValor() );
        clienteEmSessao = this.clienteRepository.updateCliente( clienteEmSessao, clienteEmSessao.getId() );
        this.companhiaAereaService.creditarCompanhiasAereas( contratacao );
    }

}
