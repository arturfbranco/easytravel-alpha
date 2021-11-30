package br.com.easytravel.Service;

import br.com.easytravel.Entity.ContratacaoEntity;
import br.com.easytravel.Entity.ContratacaoPassagemEntity;
import br.com.easytravel.Repository.ContratacaoPassagemRepository;
import br.com.easytravel.Repository.ContratacaoRepository;

import java.util.List;

public class ContratacaoService {
    private final ContratacaoRepository contratacaoRepository = new ContratacaoRepository();
    private final ContratacaoPassagemRepository contratacaoPassagemRepository = new ContratacaoPassagemRepository();

    public ContratacaoEntity criarContratacao( List<ContratacaoPassagemEntity> cps ){
        ContratacaoEntity contratacao = new ContratacaoEntity();
        contratacao.setValor( this.calcularValor( cps ) );
        ContratacaoEntity contratacaoSalva = this.contratacaoRepository.createContratacao( contratacao );
        for( ContratacaoPassagemEntity cp : cps ){
            cp.setContratacao( contratacaoSalva );
            contratacaoPassagemRepository.createContratacaoPassagem( cp );
        }
        return contratacaoSalva;
    }

    public ContratacaoEntity consolidarContratacao( Integer id ){
        ContratacaoEntity ct = this.contratacaoRepository.findById( id );
        ct.setConsolidada( true );
        return this.contratacaoRepository.updateContratacao( ct, ct.getId() );
    }
    public ContratacaoEntity buscarPorId( Integer id ){
        return this.contratacaoRepository.findById( id );
    }

    public List<ContratacaoPassagemEntity> buscarPassagensDeContratacao( Integer contratacaoId ){
        return this.contratacaoPassagemRepository.findByContratacao( contratacaoId );
    }

    private Double calcularValor(List<ContratacaoPassagemEntity> cps ){
        return cps
                .stream()
                .map( cp -> cp.getPassagem().getPrecoUnitario() )
                .reduce( ( acc, cp ) -> acc + cp )
                .get();

    }
}
