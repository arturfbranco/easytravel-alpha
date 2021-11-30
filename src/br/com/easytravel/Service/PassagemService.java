package br.com.easytravel.Service;

import br.com.easytravel.Entity.LocalEntity;
import br.com.easytravel.Entity.PassagemEntity;
import br.com.easytravel.Repository.LocalRepository;
import br.com.easytravel.Repository.PassagemRepository;

import java.util.List;

public class PassagemService {
    private final PassagemRepository passagemRepository = new PassagemRepository();
    private final LocalRepository localRepository = new LocalRepository();

    public PassagemEntity criarPassagem(PassagemEntity passagem){
        passagem.setOrigem( this.construirLocal( passagem.getOrigem() ) );
        passagem.setDestino( this.construirLocal( passagem.getDestino() ) );
        return this.passagemRepository.createPassagem( passagem );
    }

    private LocalEntity construirLocal( LocalEntity local ){
        LocalEntity localSalvo = this.localRepository.findByNome( local.getNome() );
        if( localSalvo == null ){
            localSalvo = this.localRepository.createLocal( local );
        }
        return localSalvo;
    }

    public List<PassagemEntity> buscarTodas(){
        return this.passagemRepository.findAll();
    }

    public PassagemEntity buscarPorId( Integer id ){
        return this.passagemRepository.findById( id );
    }
    public List<PassagemEntity> buscarPorOrigem( String localNome ){
        return this.passagemRepository.findByOrigem( localNome );
    }
    public List<PassagemEntity> buscarPorDestino( String localNome ){
        return this.passagemRepository.findByDestino( localNome );
    }
    public List<PassagemEntity> buscarPorCompanhiaAerea(Integer companhiaAereaId){
        return this.passagemRepository.findByCompanhiaAerea( companhiaAereaId );
    }
}
