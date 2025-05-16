/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;

/**
 * Representa um usuário, herando de pessoa e implementando de Autenticacao.
 * 
 * Contém atributos de autenticação (username e senha), um id
 * e uma lista de playlists associadas ao usuário. 
 * Possui vários construtores assim como as outras classes
 * 
 * Oferece método de autenticação (que foi implementado)
 * 
 * @author Danilo
 */

public class Usuario extends Pessoa implements Autenticacao{
    private String username, senha;
    private int id;
    private ArrayList<Playlist> playlists;

    /**
     * 
     * @param nome
     * @param sobrenome
     * @param username
     * @param idade
     * @param senha
     * @param sexo 
     */
    
    public Usuario(String nome, String sobrenome, String username, // pra cadastro
            int idade, String senha, String sexo) {
        super(nome, sobrenome, idade,sexo);
        this.username = username;
        this.senha = senha;
    }
    /**
     * 
     * @param username
     * @param senha 
     */
    public Usuario(String username, String senha) { // pra login
        this.username = username;
        this.senha = senha;
    }
    /**
     * 
     * @param username
     * @param senha
     * @param id
     * @param nome
     * @param sobrenome
     * @param idade
     * @param sexo 
     */
    public Usuario(String username, String senha, int id, String nome, 
                   String sobrenome, int idade, String sexo) {
        super(nome, sobrenome, idade, sexo);
        this.username = username;
        this.senha = senha;
        this.id = id;
    }
    /**
     * 
     * @param username
     * @param senha
     * @param id
     * @param playlists
     * @param nome
     * @param sobrenome
     * @param idade
     * @param sexo 
     */
    public Usuario(String username, String senha, int id, ArrayList<Playlist> playlists,
                   String nome, String sobrenome, int idade, String sexo) {
        super(nome, sobrenome, idade, sexo);
        this.username = username;
        this.senha = senha;
        this.id = id;
        this.playlists = playlists;
    }
    
    public Usuario() {
    }
    
    // GET e SET de todos os atributos

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
     /**
     * Método de autenticação que verifica username e senha
     * 
     * @param username nome do usuario.
     * @param senha senha digitada.
     * @return true se as infos baterem (alem de avisar no terminal).
     */
    
     @Override
    public boolean autenticar(String username, String senha) {
        if(this.username.equals(username) && this.senha.equals(senha)){
            System.out.println("Usuário autenticado!\n");
            return true;
        }
        return false;
    }
}
