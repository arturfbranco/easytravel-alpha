package br.com.easytravel.Service;

import br.com.easytravel.Entity.ContatoEntity;
import br.com.easytravel.Repository.ContatoRepository;

import java.util.ArrayList;
import java.util.List;

public class ContatoService {

    private final ContatoRepository contatoRepository = new ContatoRepository();

    public List<ContatoEntity> cadastrarContatos(List<ContatoEntity> contatos ){
        List<ContatoEntity> contatosCadastrados = new ArrayList<>();
        for ( ContatoEntity contato : contatos ){
            contatosCadastrados.add( contatoRepository.createContato( contato ) );
        }
        return contatosCadastrados;
    }
}
