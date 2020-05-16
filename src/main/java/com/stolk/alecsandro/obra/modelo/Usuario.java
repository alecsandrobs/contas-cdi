package com.stolk.alecsandro.obra.modelo;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "usuarios")
public class Usuario extends EntidadeId {

    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    @NotBlank(message = "O apelido é obrigatório")
    private String apelido;

    @NotBlank(message = "A senha é obrigatória")
    private String senha;

    public Usuario() {
    }

    public Usuario(@NotBlank(message = "O nome é obrigatório") String nome, @NotBlank(message = "O apelido é obrigatório") String apelido, @NotBlank(message = "A senha é obrigatória") String senha) {
        this.nome = nome;
        this.apelido = apelido;
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
