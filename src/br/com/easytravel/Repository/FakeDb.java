package br.com.easytravel.Repository;

import br.com.easytravel.Entity.*;

import java.util.ArrayList;
import java.util.List;

public class FakeDb {
    public static Integer clienteSeq = -1;
    public static List<ClienteEntity> clientes = new ArrayList<>();

    public static Integer companhiaAereaSeq = -1;
    public static List<CompanhiaAereaEntity> companhiasAereas = new ArrayList<>();

    public static Integer contatoSeq = -1;
    public static List<ContatoEntity> contatos = new ArrayList<>();

    public static Integer contratacaoSeq = -1;
    public static List<ContratacaoEntity> contratacoes = new ArrayList<>();

    public static Integer contratacaoPassagemSeq = -1;
    public static List<ContratacaoPassagemEntity> contratacaoPassagens = new ArrayList<>();

    public static Integer localSeq = -1;
    public static List<LocalEntity> locais = new ArrayList<>();

    public static Integer passagemSeq = -1;
    public static List<PassagemEntity> passagens = new ArrayList<>();


    public static Integer getNextClienteId() {
        clienteSeq += 1;
        return clienteSeq;
    }

    public static Integer getNextCompanhiaAereaId() {
        companhiaAereaSeq += 1;
        return companhiaAereaSeq;
    }

    public static Integer getNextContatoId() {
        contatoSeq += 1;
        return contatoSeq;
    }
    public static Integer getNextContratacaoId() {
        contratacaoSeq += 1;
        return contratacaoSeq;
    }
    public static Integer getNextContratacaoPassagemId() {
        contratacaoPassagemSeq += 1;
        return contratacaoPassagemSeq;
    }
    public static Integer getNextLocalId() {
        localSeq += 1;
        return localSeq;
    }
    public static Integer getNextPassagemId() {
        passagemSeq += 1;
        return passagemSeq;
    }




}
