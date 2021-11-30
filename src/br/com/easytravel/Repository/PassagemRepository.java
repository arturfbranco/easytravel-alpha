package br.com.easytravel.Repository;

import br.com.easytravel.Entity.PassagemEntity;

import java.util.List;
import java.util.stream.Collectors;

public class PassagemRepository {

    public PassagemEntity createPassagem( PassagemEntity passagem ){
        passagem.setId( FakeDb.getNextPassagemId() );
        FakeDb.passagens.add( passagem );
        return passagem;
    }

    public List<PassagemEntity> findAll(){
        return FakeDb.passagens;
    }

    public PassagemEntity findById( Integer id ){
        return FakeDb.passagens
                .stream()
                .filter( p -> p.getId().equals( id ) )
                .findFirst()
                .orElse( null );
    }
    public List<PassagemEntity> findByCompanhiaAerea( Integer companhiaAereaId ){
        return FakeDb.passagens
                .stream()
                .filter( p -> p.getCompanhiaAerea().getId().equals( companhiaAereaId ) )
                .collect( Collectors.toList() );
    }
    public List<PassagemEntity> findByOrigem( String localNome ){
        return FakeDb.passagens
                .stream()
                .filter( p -> p.getOrigem().getNome().equals( localNome ) )
                .collect( Collectors.toList() );
    }
    public List<PassagemEntity> findByDestino( String localNome ){
        return FakeDb.passagens
                .stream()
                .filter( p -> p.getDestino().getNome().equals( localNome ) )
                .collect( Collectors.toList() );
    }
}
