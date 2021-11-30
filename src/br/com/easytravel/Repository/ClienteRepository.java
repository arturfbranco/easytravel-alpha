package br.com.easytravel.Repository;

import br.com.easytravel.Entity.ClienteEntity;

public class ClienteRepository {

    public ClienteEntity createCliente(ClienteEntity cliente ){
        cliente.setId( FakeDb.getNextClienteId() );
        FakeDb.clientes.add( cliente );
        return cliente;
    }
    public ClienteEntity updateCliente( ClienteEntity cliente, Integer id ){
        cliente.setId( id );
        for ( int i = 0; i < FakeDb.clientes.size(); i++ ){
            if( FakeDb.clientes.get(i).getId().equals( cliente.getId() ) ){
                FakeDb.clientes.set( i, cliente );
                return cliente;
            }
        }
        return null;
    }

    public ClienteEntity findByUsername( String username ){
        return FakeDb.clientes
                .stream()
                .filter( cliente -> cliente.getUsername().equals( username ) )
                .findFirst()
                .orElse( null );
    }
}
