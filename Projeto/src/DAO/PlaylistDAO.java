/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import model.Playlist;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.Artista;
import model.Musica;
/**
 *
 * @author Danilo
 */
public class PlaylistDAO {
    private Connection conn;
    
    public PlaylistDAO(Connection conn){
        this.conn = conn;
    }
    
    
    public boolean criarPlaylist(Playlist playlist) throws SQLException{
        String sql = "INSERT INTO playlist (nome, id_usuario) VALUES (?, ?)";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, playlist.getNome());
        statement.setInt(2, playlist.getIdUsuario());
        int linha = statement.executeUpdate();
        return linha > 0; // se for maior que 0 é porque foi criado!
    }   
    
    public boolean verificaNome(String nomePlaylist, int id) throws SQLException{
        String sql = "select count(*) from playlist where id_usuario = ? "
                     + "AND nome = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, id);
        statement.setString(2, nomePlaylist);
        ResultSet res = statement.executeQuery();
        if (res.next()) {
            int count = res.getInt(1);
            return count > 0; // se for maior que 0 é porque ja existe!
        }
        return false;
    }
    
    public ArrayList<Playlist> acharPlaylists(int usuarioId) throws SQLException {
        ArrayList<Playlist> playlists = new ArrayList<>();
        String sql = "SELECT * FROM playlist WHERE id_usuario = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, usuarioId);
        ResultSet rs = statement.executeQuery();

        while (rs.next()) {
            int id = rs.getInt("id");
            String nome = rs.getString("nome");

            Playlist playlist = new Playlist(id, nome, usuarioId);

            ArrayList<Musica> musicas = buscarMusicas(id);
            playlist.setMusicas(musicas);

            playlists.add(playlist);
        }

        return playlists;
    }
    
    public ArrayList<Musica> buscarMusicas(int idPlaylist) throws SQLException {
        ArrayList<Musica> musicas = new ArrayList<>();
        String sql = "SELECT m.* FROM musica m " +
                     "JOIN musicas_da_playlist pm ON m.id = pm.id_musica " +
                     "WHERE pm.id_playlist = ?"; 
            // busca musicas da playlist com o id solicitado

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, idPlaylist);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Musica musica = new Musica(rs.getString("nome"));
            musicas.add(musica);
        }

        return musicas;
    }
       
       
    public boolean renomearPlaylistDAO(int idPlaylist, String nome) throws SQLException {
        String sql = "UPDATE playlist SET nome = ? WHERE id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, nome);
        stmt.setInt(2, idPlaylist);

        int rowsUpdated = stmt.executeUpdate();
        return rowsUpdated > 0;
    }
    
    public boolean adicionarMusicaPlaylist(int idPlaylist, int idMusica) throws SQLException{
        String sql = "INSERT INTO musicas_da_playlist (id_playlist, id_musica)"
                + "VALUES (?, ?)";          
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, idPlaylist);
        stmt.setInt(2, idMusica);
        int rowsUpdated = stmt.executeUpdate();
        return rowsUpdated > 0;
    }
    
    public boolean removerMusicaPlaylist(int idPlaylist, int idMusica) throws SQLException{
            String sql = "DELETE FROM musicas_da_playlist WHERE "
                    + "id_playlist = ? AND id_musica = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, idPlaylist);
        stmt.setInt(2, idMusica);
        int rowsUpdated = stmt.executeUpdate();
        return rowsUpdated > 0;
    }
    
    public boolean verificaMusicaPlaylist(int idPlaylist, int idMusica)throws SQLException{
        String sql = "SELECT COUNT(*) FROM musicas_da_playlist "
                + "WHERE id_playlist = ? AND id_musica = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, idPlaylist);
        stmt.setInt(2, idMusica);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
           return rs.getInt(1) > 0; // verifica por COUNT se a musica ja esta 
                                    // na playlist
       }
       return false;
   }
    
  public ArrayList<Musica> buscarMusicasCompleto(int idPlaylist) throws SQLException {
        ArrayList<Musica> musicas = new ArrayList<>();

        String sql = "SELECT m.id, m.nome, m.genero, m.ano_lancamento, m.album, " +
                     "a.nome_artistico AS artista_nome " +
                     "FROM musica m " +
                     "JOIN artista a ON m.artista_id = a.id " +
                     "JOIN musicas_da_playlist pm ON pm.id_musica = m.id " +
                     "WHERE pm.id_playlist = ?";

        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, idPlaylist);

        ResultSet rs = statement.executeQuery();

           while (rs.next()) {
    int id = rs.getInt("id");
    String nomeArtista = rs.getString("artista_nome");
    String nomeMusica = rs.getString("nome");
    String genero = rs.getString("genero");
    int anoLancamento = rs.getInt("ano_lancamento");
    String album = rs.getString("album");

    System.out.println("Nome: " + nomeMusica + ", Gênero: " + genero + ", Ano: " + anoLancamento + ", Álbum: " + album);

    Artista artistaObj = new Artista(nomeArtista);
    Musica musica = new Musica(
        id,
        nomeMusica,
        artistaObj,
        genero,
        anoLancamento,
        album
    );
    musicas.add(musica);
}
        return musicas;
        }



}
