/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 * representa a pessoa com informações como nome, sobrenome, idade e sexo.
 * 
 * Classe pai de outras classes (artista, usuario)
 * 
 * Possui 3 construtores: completo, apenas com o nome (utilizado no artista) e
 * um vazio
 * 
 * @author Danilo
 */

public class Pessoa {
    private String nome, sobrenome,sexo; 
    private int idade;
    
    /**
     * 
     * @param nome
     * @param sobrenome
     * @param idade
     * @param sexo 
     */
    
    public Pessoa(String nome, String sobrenome, int idade, String sexo) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.idade = idade;
        this.sexo = sexo;
    }
    
    /**
     * 
     * @param nome 
     */

    public Pessoa(String nome) {
        this.nome = nome;
    }
    
      public Pessoa() {
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }   
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }   
}
