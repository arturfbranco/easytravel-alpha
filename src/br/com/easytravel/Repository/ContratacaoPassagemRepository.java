package br.com.easytravel.Repository;

import br.com.easytravel.Entity.ContratacaoPassagemEntity;

import java.util.List;
import java.util.stream.Collectors;

public class ContratacaoPassagemRepository {
    public ContratacaoPassagemEntity createContratacaoPassagem( ContratacaoPassagemEntity contratacaoPassagem ){
        contratacaoPassagem.setId( FakeDb.getNextContratacaoPassagemId() );
        FakeDb.contratacaoPassagens.add( contratacaoPassagem );
        return contratacaoPassagem;
    }
    public ContratacaoPassagemEntity updateContratacaoPassagem( ContratacaoPassagemEntity cp, Integer id ){
        cp.setId( id );
        for( int i = 0; i < FakeDb.contratacaoPassagens.size(); i++ ){
            if( FakeDb.contratacaoPassagens.get( i ).getId().equals( cp.getId() ) ){
                FakeDb.contratacaoPassagens.set( i, cp );
                return cp;
            }
        }
        return null;
    }
    public ContratacaoPassagemEntity findById( Integer id ){
        return FakeDb.contratacaoPassagens
                .stream()
                .filter( cp -> cp.getId().equals( id ) )
                .findFirst()
                .orElse( null );
    }
    public List<ContratacaoPassagemEntity> findByContratacao( Integer contratacaoId ){
        return FakeDb.contratacaoPassagens
                .stream()
                .filter( cp -> cp.getContratacao().getId().equals( contratacaoId ) )
                .collect( Collectors.toList() );
    }
    public List<ContratacaoPassagemEntity> findByPassagem( Integer passagemId ){
        return FakeDb.contratacaoPassagens
                .stream()
                .filter( cp -> cp.getPassagem().getId().equals( passagemId ) )
                .collect( Collectors.toList() );
    }
}
