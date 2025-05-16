/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 * representa uma música com seus atributos como nome, artista, gênero,
 * ano de lançamento e album.
 * 
 * Cada música pode estar associada a um artista (composicao)
 * 
 * fornece varios construtores, porem é mt provavel que nao tenha sido
 * utilizado todos os construtores
 * 
 * @author Danilo
 */

public class Musica {
    private int id;
    private String nome;
    private Artista artista; // composicao
    private String genero;
    private int anoLancamento;
    private String album;

    /**
     * 
     * @param nome
     * @param artista
     * @param genero
     * @param anoLancamento
     * @param album 
     */
    
    public Musica(String nome, Artista artista, String genero, 
            int anoLancamento, String album) {
        this.nome = nome;
        this.artista = artista;
        this.genero = genero;
        this.anoLancamento = anoLancamento;
        this.album = album;
    }
    /**
     * 
     * @param nome
     * @param genero
     * @param album 
     */
    public Musica(String nome, String genero, String album) {
        this.nome = nome;
        this.genero = genero;
        this.album = album;
    }
    /**
     * 
     * @param nome
     * @param genero
     * @param anoLancamento
     * @param album 
     */
    public Musica(String nome, String genero, int anoLancamento, String album) {
        this.nome = nome;
        this.genero = genero;
        this.anoLancamento = anoLancamento;
        this.album = album;
    }
    /**
     * 
     * @param id
     * @param nome
     * @param artista
     * @param genero
     * @param anoLancamento
     * @param album 
     */
    public Musica(int id, String nome, Artista artista, String genero, 
            int anoLancamento, String album) {
        this.id = id;
        this.nome = nome;
        this.artista = artista;
        this.genero = genero;
        this.anoLancamento = anoLancamento;
        this.album = album;
    }
    
    public Musica(String nome) {
        this.nome = nome;
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

    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getAnoLancamento() {
        return anoLancamento;
    }

    public void setAnoLancamento(int anoLancamento) {
        this.anoLancamento = anoLancamento;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    @Override
    public String toString() {
        return "Musica{" + "nome=" + nome + ", artista=" + artista +
                ", genero=" + genero + ", anoLancamento=" + anoLancamento +
                ", album=" + album + '}';
    }
    
    
}
