/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Danilo
 */
public class Musica {
    private String nome;
    private Artista artista; // composicao
    private String genero;
    private int anoLancamento;
    private String album;

    public Musica(String nome, Artista artista, String genero, 
            int anoLancamento, String album) {
        this.nome = nome;
        this.artista = artista;
        this.genero = genero;
        this.anoLancamento = anoLancamento;
        this.album = album;
    }

    public Musica(String nome, String genero, String album) {
        this.nome = nome;
        this.genero = genero;
        this.album = album;
    }

    public Musica(String nome, String genero, int anoLancamento, String album) {
        this.nome = nome;
        this.genero = genero;
        this.anoLancamento = anoLancamento;
        this.album = album;
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
    
    
    /* CASO PRECISE, COMANDO SQL PRA CRIAR TABLE MUSICA:
     *
     *   CREATE TABLE musica (
     *   id SERIAL PRIMARY KEY,
     *   nome VARCHAR(255) NOT NULL,
     *   artista_id INT NOT NULL, 
     *   genero VARCHAR(100),
     *   ano_lancamento INT,
     *   album VARCHAR(255),
     *   FOREIGN KEY (artista_id) REFERENCES artista(id) ON DELETE CASCADE 
     *   );
    */
}
