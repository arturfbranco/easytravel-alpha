package br.com.easytravel.Repository;

import br.com.easytravel.Entity.ContatoEntity;

public class ContatoRepository {

    public ContatoEntity createContato(ContatoEntity contato ){
        contato.setId( FakeDb.getNextContatoId() );
        FakeDb.contatos.add( contato );
        return contato;
    }
}
