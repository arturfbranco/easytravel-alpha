package br.com.easytravel.Entity;

public class ContratacaoEntity {
    private Integer id;
    private Double valor;
    private boolean isConsolidada;

    public ContratacaoEntity() {
        this.isConsolidada = false;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public boolean isConsolidada() {
        return isConsolidada;
    }

    public void setConsolidada(boolean consolidada) {
        isConsolidada = consolidada;
    }
}
