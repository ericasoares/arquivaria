package tech.ericasoares.arquivaria.model;

import lombok.Data;

@Data
public class Registro {
    private String nome;
    private String cpf;
    private String cartao;
    private double saldo;
    private String data;
}