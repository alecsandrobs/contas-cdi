package com.stolk.alecsandro.obra.modelo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.stolk.alecsandro.obra.util.LocalDateDeserializer;
import com.stolk.alecsandro.obra.util.LocalDateSerializer;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;

import static javax.persistence.EnumType.STRING;

@Entity
@Table(name = "lancamentos")
public class Lancamento extends EntidadeId {

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate data;

    @ManyToOne
    private Fornecedor fornecedor;

    @ManyToOne
    private Conta conta;

    @Enumerated(STRING)
    private TipoLancamento tipo;

    private Double valor;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate pagamento;
    private String observacoes;

    public Lancamento() {
    }

    public Lancamento(LocalDate data, Fornecedor fornecedor, Conta conta, TipoLancamento tipo, Double valor, LocalDate pagamento, String observacoes) {
        this.data = data;
        this.fornecedor = fornecedor;
        this.conta = conta;
        this.tipo = tipo;
        this.valor = valor;
        this.pagamento = pagamento;
        this.observacoes = observacoes;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    public TipoLancamento getTipo() {
        return tipo;
    }

    public void setTipo(TipoLancamento tipo) {
        this.tipo = tipo;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public LocalDate getPagamento() {
        return pagamento;
    }

    public void setPagamento(LocalDate pagamento) {
        this.pagamento = pagamento;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public enum TipoLancamento {
        PAGAMENTO("PAGAMENTO", "Pagamento"),
        RECEBIMENTO("RECEBIMENTO", "Recebimento");

        private String tipo;
        private String valor;

        TipoLancamento(String tipo, String valor) {
            this.tipo = tipo;
            this.valor = valor;
        }

        public static TipoLancamento fromValue(String valor) {
            for (TipoLancamento tipo : TipoLancamento.values()) {
                if (tipo.valor.equalsIgnoreCase(valor)) {
                    return tipo;
                }
            }
            return null;
        }

        public String getTipo() {
            return tipo;
        }

        public String getValor() {
            return valor;
        }

    }
}
