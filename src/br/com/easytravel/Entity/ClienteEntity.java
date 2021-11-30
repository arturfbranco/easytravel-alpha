package br.com.easytravel.Entity;

import java.util.ArrayList;
import java.util.List;

public class ClienteEntity extends AccountEntity{
    private String cpf;
    private Double credito;
    private List<ContratacaoEntity> contratacoes;

    public ClienteEntity() {
        this.credito = 0.0;
        this.contratacoes = new ArrayList<>();
    }

    public ClienteEntity( AccountEntity acc ){
        super( acc );
        this.credito = 0.0;
        this.contratacoes = new ArrayList<>();
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Double getCredito() {
        return credito;
    }

    public void setCredito(Double credito) {
        this.credito = credito;
    }

    public List<ContratacaoEntity> getContratacoes() {
        return contratacoes;
    }

    public void adicionarContratacao( ContratacaoEntity contratacao ){
        this.contratacoes.add( contratacao );
    }
}
