/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;

/**
 * Representa um artista, é uma especialização da classe Pessoa.
 * atributos como nacionalidade, status, gênero musical, nome artístico
 * e uma lista de músicas associadas ao artista.
 * 
 * herda os atributos básicos de Pessoa, como nome, sobrenome, idade e sexo.
 * 
 * @author Danilo
 */

public class Artista extends Pessoa{
    private String nacionalidade, status, generoMusical, nomeArtistico;
    private ArrayList<Musica> músicas;
    
    
    /**
     * Possíveis contrutores a serem utilizado durante o código
     * obs: nem todos foram utilizados durante o código.
     * 
     * @param nacionalidade
     * @param status
     * @param generoMusical
     * @param nomeArtistico
     * @param nome
     * @param sobrenome
     * @param idade
     * @param sexo 
     */
    
    public Artista(String nacionalidade, String status, String generoMusical, 
            String nomeArtistico, String nome, String sobrenome, int idade, 
            String sexo) {
        super(nome, sobrenome, idade, sexo);
        this.nacionalidade = nacionalidade;
        this.status = status;
        this.generoMusical = generoMusical;
        this.nomeArtistico = nomeArtistico;
    }   // construtor com nome artistico

    /**
     * 
     * @param nome
     * @param sobrenome
     * @param idade
     * @param sexo
     * @param nacionalidade
     * @param status
     * @param generoMusical 
     */
    
    public Artista(String nome, String sobrenome, int idade, String sexo, 
                   String nacionalidade, String status, String generoMusical) {
        super(nome, sobrenome, idade, sexo);  
        this.nacionalidade = nacionalidade;
        this.status = status;
        this.generoMusical = generoMusical;
    }   // construtor sem nome artistico
    
    /**
     * 
     * @param nacionalidade
     * @param status
     * @param generoMusical
     * @param nomeArtistico
     * @param músicas
     * @param nome
     * @param sobrenome
     * @param idade
     * @param sexo 
     */
    
    public Artista(String nacionalidade, String status, String generoMusical, 
                   String nomeArtistico, ArrayList<Musica> músicas, String nome, 
                   String sobrenome, int idade, String sexo) {
        super(nome, sobrenome, idade, sexo);
        this.nacionalidade = nacionalidade;
        this.status = status;
        this.generoMusical = generoMusical;
        this.nomeArtistico = nomeArtistico;
        this.músicas = músicas;
    } // construtor com o arraylist

    /**
     * 
     * @param nome
     * @param sobrenome
     * @param idade
     * @param sexo 
     */
    
    public Artista(String nome, String sobrenome, int idade, String sexo) {
        super(nome, sobrenome, idade, sexo);
    }  
    
    public Artista(String nomeArtistico) {
        this.nomeArtistico = nomeArtistico;
    }

    
    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getGeneroMusical() {
        return generoMusical;
    }

    public void setGeneroMusical(String generoMusical) {
        this.generoMusical = generoMusical;
    }

    public String getNomeArtistico() {
        return nomeArtistico;
    }

    public void setNomeArtistico(String nomeArtistico) {
        this.nomeArtistico = nomeArtistico;
    }

    @Override
    public String toString() {
        return nomeArtistico;
    }

    
   
}
