package br.com.easytravel.Entity;

import java.time.LocalDate;

public class PassagemEntity {
    private Integer id;
    private CompanhiaAereaEntity companhiaAerea;
    private LocalEntity origem;
    private LocalEntity destino;
    private Double precoUnitario;
    private LocalDate data;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CompanhiaAereaEntity getCompanhiaAerea() {
        return companhiaAerea;
    }

    public void setCompanhiaAerea(CompanhiaAereaEntity companhiaAerea) {
        this.companhiaAerea = companhiaAerea;
    }

    public LocalEntity getOrigem() {
        return origem;
    }

    public void setOrigem(LocalEntity origem) {
        this.origem = origem;
    }

    public LocalEntity getDestino() {
        return destino;
    }

    public void setDestino(LocalEntity destino) {
        this.destino = destino;
    }

    public Double getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(Double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }
}
