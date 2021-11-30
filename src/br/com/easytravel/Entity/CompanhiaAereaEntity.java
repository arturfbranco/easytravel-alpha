package br.com.easytravel.Entity;

public class CompanhiaAereaEntity extends AccountEntity{
    private String cnpj;
    private Double saldo;

    public CompanhiaAereaEntity() {
        this.saldo = 0.0;
    }

    public CompanhiaAereaEntity( AccountEntity acc ){
        super( acc );
        this.saldo = 0.0;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }
}
