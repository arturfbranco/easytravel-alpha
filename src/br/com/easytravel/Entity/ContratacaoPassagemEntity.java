package br.com.easytravel.Entity;

public class ContratacaoPassagemEntity {
    private Integer id;
    private ContratacaoEntity contratacao;
    private PassagemEntity passagem;
    private String portador;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ContratacaoEntity getContratacao() {
        return contratacao;
    }

    public void setContratacao(ContratacaoEntity contratacao) {
        this.contratacao = contratacao;
    }

    public PassagemEntity getPassagem() {
        return passagem;
    }

    public void setPassagem(PassagemEntity passagem) {
        this.passagem = passagem;
    }

    public String getPortador() {
        return portador;
    }

    public void setPortador(String portador) {
        this.portador = portador;
    }
}
