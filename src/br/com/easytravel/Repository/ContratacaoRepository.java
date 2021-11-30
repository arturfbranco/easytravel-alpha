package br.com.easytravel.Repository;

import br.com.easytravel.Entity.ContratacaoEntity;

public class ContratacaoRepository {

    public ContratacaoEntity createContratacao( ContratacaoEntity contratacao ){
        contratacao.setId( FakeDb.getNextContratacaoId() );
        FakeDb.contratacoes.add( contratacao );
        return contratacao;
    }
    public ContratacaoEntity updateContratacao( ContratacaoEntity contratacao, Integer id ){
        contratacao.setId( id );
        for( int i = 0; i < FakeDb.contratacoes.size(); i++ ){
            if( FakeDb.contratacoes.get( i ).getId().equals( contratacao.getId() ) ){
                FakeDb.contratacoes.set( i, contratacao );
                return contratacao;
            }
        }
        return null;
    }

    public ContratacaoEntity findById( Integer id ){
        return FakeDb.contratacoes
                .stream()
                .filter( cont -> cont.getId().equals( id ) )
                .findFirst()
                .orElse( null );
    }
}
