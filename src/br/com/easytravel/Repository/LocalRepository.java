package br.com.easytravel.Repository;

import br.com.easytravel.Entity.LocalEntity;

public class LocalRepository {
    public LocalEntity createLocal( LocalEntity local ){
        local.setId( FakeDb.getNextLocalId() );
        FakeDb.locais.add( local );
        return local;
    }
    public LocalEntity findById( Integer id ){
        return FakeDb.locais
                .stream()
                .filter( local -> local.getId().equals( id ) )
                .findFirst()
                .orElse( null );
    }
    public LocalEntity findByNome( String nome ){
        return FakeDb.locais
                .stream()
                .filter( local -> local.getNome().equals( nome ) )
                .findFirst()
                .orElse( null );
    }
}
