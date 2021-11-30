package br.com.easytravel.Entity;

public class LocalEntity {
    private Integer id;
    private String nome;

    public LocalEntity(String nome) {
        this.nome = nome;
    }

    public LocalEntity() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
