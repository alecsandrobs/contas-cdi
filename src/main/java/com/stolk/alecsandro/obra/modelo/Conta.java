package com.stolk.alecsandro.obra.modelo;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "contas")
public class Conta extends EntidadeId {

    @NotBlank(message = "O banco é obrigatório")
    private String banco;

    @NotBlank(message = "A agência é obrigatória")
    private String agencia;

    @NotBlank(message = "O número da conta é obrigatório")
    private String numero;

    public Conta() {
    }

    public Conta(@NotBlank(message = "O banco é obrigatório") String banco, @NotBlank(message = "A agência é obrigatória") String agencia, @NotBlank(message = "O número da conta é obrigatório") String numero) {
        this.banco = banco;
        this.agencia = agencia;
        this.numero = numero;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
}
