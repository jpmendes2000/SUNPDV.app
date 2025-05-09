package com.Admin;

public class Usuario {
    private String nome;
    private String email;
    private String senha;
    private int codigoCargo;

    public Usuario(String nome, String email, String senha, int codigoCargo) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.codigoCargo = codigoCargo;
    }

    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public String getSenha() { return senha; }
    public int getCodigoCargo() { return codigoCargo; }
}
