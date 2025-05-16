/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;

/**
 * Representa uma playlist de músicas relacionada a um usuário.
 * 
 * informações: ID da playlist, nome da playlist, usuário dono da playlist,
 * lista de músicas associadas, e identificadores auxiliares para relacionamento
 * com banco de dados.
 * 
 * Varios construtores que provavelmente nem todos sao utilizados
 * Alem disso, alguns construtores usam o objeto Usuario, outros apenas utilizam
 * o id de um usuario
 * 
 * @author Danilo
 */

public class Playlist {
    private int id;
    private String nome;
    private Usuario usuario;
    private ArrayList<Musica> musicas;
    private int idUsuario; // importante na criacao de uma playlist 
    private int idMusica;  // importante na adicao de uma musica na playlist
    
    /**
     * 
     * @param id
     * @param nome
     * @param usuario
     * @param musicas 
     */
    
    public Playlist(int id, String nome, Usuario usuario, ArrayList<Musica> musicas) {
        this.id = id;
        this.nome = nome;
        this.usuario = usuario;
        this.musicas = musicas;
    }
    /**
     * 
     * @param nome
     * @param usuario
     * @param musicas 
     */
    
    public Playlist(String nome, Usuario usuario, ArrayList<Musica> musicas) {
        this.nome = nome;
        this.usuario = usuario;
        this.musicas = musicas;
    }
    /**
     * 
     * @param id
     * @param nome
     * @param musicas
     * @param idUsuario 
     */
    
    public Playlist(int id, String nome, ArrayList<Musica> musicas, int idUsuario) {
        this.id = id;
        this.nome = nome;
        this.musicas = musicas;
        this.idUsuario = idUsuario;
    }
    /**
     * 
     * @param nome
     * @param musicas
     * @param idUsuario
     * @param idMusica 
     */
    
    public Playlist(String nome, ArrayList<Musica> musicas, int idUsuario, 
                    int idMusica) {
        this.nome = nome;
        this.musicas = musicas;
        this.idUsuario = idUsuario;
        this.idMusica = idMusica;
    }
    /**
     * 
     * @param nome
     * @param idUsuario 
     */
    public Playlist(String nome, int idUsuario) {
        this.nome = nome;
        this.idUsuario = idUsuario;
    }
    /**
     * 
     * @param id
     * @param nome
     * @param idUsuario 
     */
    public Playlist(int id, String nome, int idUsuario) {
        this.id = id;
        this.nome = nome;
        this.idUsuario = idUsuario;
    }
    // GET e SET de todos os atributos
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public ArrayList<Musica> getMusicas() {
        return musicas;
    }

    public void setMusicas(ArrayList<Musica> musicas) {
        this.musicas = musicas;
    }
    
    public void adicionarMusica(Musica musica) {
        this.musicas.add(musica);
    }

    public void removerMusica(Musica musica) {
        this.musicas.remove(musica);
    }   

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdMusica() {
        return idMusica;
    }

    public void setIdMusica(int idMusica) {
        this.idMusica = idMusica;
    }
    
    
}
