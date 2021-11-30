package br.com.easytravel.Repository;

import br.com.easytravel.Entity.ClienteEntity;
import br.com.easytravel.Entity.CompanhiaAereaEntity;

public class CompanhiaAereaRepository {

    public CompanhiaAereaEntity createCompanhiaAerea(CompanhiaAereaEntity companhiaAerea ){
        companhiaAerea.setId( FakeDb.getNextCompanhiaAereaId() );
        FakeDb.companhiasAereas.add( companhiaAerea );
        return companhiaAerea;
    }
    public CompanhiaAereaEntity updateCompanhiaAerea( CompanhiaAereaEntity companhiaAerea, Integer id ){
        companhiaAerea.setId( id );
        for ( int i = 0; i < FakeDb.companhiasAereas.size(); i++ ){
            if( FakeDb.companhiasAereas.get(i).getId().equals( companhiaAerea.getId() ) ){
                FakeDb.companhiasAereas.set( i, companhiaAerea );
                return companhiaAerea;
            }
        }
        return null;
    }

    public CompanhiaAereaEntity findById( Integer id ){
        return FakeDb.companhiasAereas
                .stream()
                .filter( ca -> ca.getId().equals( id ) )
                .findFirst()
                .orElse( null );
    }

    public CompanhiaAereaEntity findByUsername( String username ){
        return FakeDb.companhiasAereas
                .stream()
                .filter( companhiaAerea -> companhiaAerea.getUsername().equals( username ) )
                .findFirst()
                .orElse( null );
    }
}
