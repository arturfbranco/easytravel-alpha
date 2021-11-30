package br.com.easytravel.Service;

import br.com.easytravel.Entity.CompanhiaAereaEntity;
import br.com.easytravel.Entity.ContratacaoEntity;
import br.com.easytravel.Entity.ContratacaoPassagemEntity;
import br.com.easytravel.Entity.PassagemEntity;
import br.com.easytravel.Repository.CompanhiaAereaRepository;
import br.com.easytravel.Repository.ContratacaoPassagemRepository;

import java.util.List;

public class CompanhiaAereaService {
    private static CompanhiaAereaEntity companhiaAereaEmSessao;
    private final CompanhiaAereaRepository companhiaAereaRepository = new CompanhiaAereaRepository();
    private final ContatoService contatoService = new ContatoService();
    private final PassagemService passagemService = new PassagemService();
    private final ContratacaoPassagemRepository contratacaoPassagemRepository = new ContratacaoPassagemRepository();

    public boolean fazerLogin( String username, String senha ){
        CompanhiaAereaEntity companhiaAerea = this.companhiaAereaRepository.findByUsername( username );
        if( companhiaAerea != null ){
            if( companhiaAerea.getSenha().equals( senha ) ){
                companhiaAereaEmSessao = companhiaAerea;
                return true;
            }
        }
        return false;
    }
    public void fazerLogout(){
        companhiaAereaEmSessao = null;
    }

    public boolean cadastrarcompanhiaAerea(CompanhiaAereaEntity companhiaAerea ){
        if( this.usernameNaoExistente( companhiaAerea.getUsername() ) ){
            companhiaAerea.setContatos( this.contatoService.cadastrarContatos( companhiaAerea.getContatos() ) );
            this.companhiaAereaRepository.createCompanhiaAerea( companhiaAerea );
            return true;
        }
        return false;
    }

    public boolean atualizarCompanhiaAerea( CompanhiaAereaEntity companhiaAerea, Integer id ){
        if( this.usernameNaoExistente( companhiaAerea.getUsername() ) ){
            companhiaAerea.setContatos( this.contatoService.cadastrarContatos( companhiaAerea.getContatos() ) );
            companhiaAereaEmSessao = this.companhiaAereaRepository.updateCompanhiaAerea( companhiaAerea, id );
            return true;
        }
        return false;
    }

    private boolean usernameNaoExistente( String username ){
        return this.companhiaAereaRepository.findByUsername( username ) == null;
    }

    public CompanhiaAereaEntity getCompanhiaAereaEmSessao(){
        return companhiaAereaEmSessao;
    }

    public PassagemEntity adicionarPassagem( PassagemEntity passagem ){
        passagem.setCompanhiaAerea( companhiaAereaEmSessao );
        return this.passagemService.criarPassagem( passagem );
    }
    public List<PassagemEntity> verMinhasPassagens(){
        return this.passagemService.buscarPorCompanhiaAerea( companhiaAereaEmSessao.getId() );
    }


    public void creditarCompanhiasAereas( ContratacaoEntity contratacao ){
        for(ContratacaoPassagemEntity cp : this.contratacaoPassagemRepository
                                            .findByContratacao( contratacao.getId() ) ){

            CompanhiaAereaEntity companhiaAerea = this.companhiaAereaRepository
                    .findById( cp.getPassagem()
                            .getCompanhiaAerea()
                            .getId()
                    );

            companhiaAerea.setSaldo( companhiaAerea.getSaldo() + cp.getPassagem().getPrecoUnitario() );
            this.companhiaAereaRepository.updateCompanhiaAerea( companhiaAerea, companhiaAerea.getId() );
        }
    }
}
