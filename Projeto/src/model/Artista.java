/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author Danilo
 */
public class Artista extends Pessoa{
    private String nacionalidade, status, generoMusical, nomeArtistico;
    private ArrayList<Musica> músicas;
    
    public Artista(String nacionalidade, String status, String generoMusical, 
            String nomeArtistico, String nome, String sobrenome, int idade, 
            String sexo) {
        super(nome, sobrenome, idade, sexo);
        this.nacionalidade = nacionalidade;
        this.status = status;
        this.generoMusical = generoMusical;
        this.nomeArtistico = nomeArtistico;
    }   // construtor com nome artistico

    public Artista(String nome, String sobrenome, int idade, String sexo, 
                   String nacionalidade, String status, String generoMusical) {
        super(nome, sobrenome, idade, sexo);  
        this.nacionalidade = nacionalidade;
        this.status = status;
        this.generoMusical = generoMusical;
    }   // construtor sem nome artistico

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
    
        //Comandos SQL p/ artista:
    
/*  SELECT PRA VERIFICAR SE HÁ ARTISTA REPETIDO:
 *
 *      SELECT nome_artistico, nome, sobrenome, COUNT(*)
 *      FROM artista
 *      GROUP BY nome_artistico, nome, sobrenome
 *      HAVING COUNT(*) > 1;
*/
    
 /* CASO PRECISE, COMANDO PARA CRIAR TABLE artista:
  *  
  *  CREATE TABLE artista (
  *  id SERIAL PRIMARY KEY,
  *  nome_artistico VARCHAR(255) NOT NULL,
  *  nome VARCHAR(255) NOT NULL,
  *  sobrenome VARCHAR(255) NOT NULL,
  *  idade INT NOT NULL,
  *  sexo VARCHAR(10) NOT NULL,
  *  nacionalidade VARCHAR(100),
  *  status VARCHAR(50),
  *  genero_musical VARCHAR(100)
);
*/

    @Override
    public String toString() {
        return nomeArtistico;
    }

    
   
}
