/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Danilo
 */
public class Usuario extends Pessoa implements Autenticacao{
    private String username, senha;

    public Usuario(String nome, String sobrenome, String username, // pra cadastro
            int idade, String senha, String sexo) {
        super(nome, sobrenome, idade,sexo);
        this.username = username;
        this.senha = senha;
    }
    
    public Usuario(String username, String senha) { // pra login
        this.username = username;
        this.senha = senha;
    }
    public Usuario() {
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

     @Override
    public boolean autenticar(String username, String senha) {
        return this.username.equals(username) && this.senha.equals(senha);
    }
    //retorna verdadeiro se o username e senha digitados correspondem ao mesmo
    // armazenado no objeto   
}
