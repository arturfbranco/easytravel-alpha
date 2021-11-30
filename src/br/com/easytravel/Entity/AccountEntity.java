package br.com.easytravel.Entity;

import java.util.List;

public class AccountEntity {
    protected Integer id;
    protected String username;
    protected String senha;
    protected String nome;
    protected List<ContatoEntity> contatos;

    public AccountEntity( AccountEntity acc ) {
        this.username = acc.getUsername();
        this.senha = acc.getSenha();
        this.nome = acc.getNome();
        this.contatos = acc.getContatos();
    }

    public AccountEntity() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<ContatoEntity> getContatos() {
        return contatos;
    }

    public void setContatos(List<ContatoEntity> contatos) {
        this.contatos = contatos;
    }
}
