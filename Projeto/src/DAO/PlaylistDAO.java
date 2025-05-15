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
 * Classe que manipula as tables que envolvam Playlists.
 * Pode criar, editar, excluir playlists e/ou gerenciar músicas dentro dela
 * 
 * @author Danilo
 */

public class PlaylistDAO {
    private Connection conn;
    
    /**
     * Construtor que recebe a conexão com o banco de dados.
     * 
     * @param conn conexão já aberta com o banco de dados.
     */
    
    public PlaylistDAO(Connection conn){
        this.conn = conn;
    }
    
     /**
     * Cria uma nova playlist no banco onde o id do usuário é responsável
     * (chave primária).
     * 
     * @param playlist Objeto Playlist.
     * @return true: playlist criada com sucesso - false: caso contrário.
     * @throws SQLException em caso de erro no banco de dados.
     */
    
    public boolean criarPlaylist(Playlist playlist) throws SQLException{
        String sql = "INSERT INTO playlist (nome, id_usuario) VALUES (?, ?)";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, playlist.getNome());
        statement.setInt(2, playlist.getIdUsuario());
        int linha = statement.executeUpdate();
        return linha > 0; // se for maior que 0 é porque foi criado!
    }   
     /**
     * Verifica se já existe uma playlist com o mesmo nome PARA O USUÁRIO.
     * 
     * @param nomePlaylist nome que o usuário escreveu.
     * @param id ID do usuário
     * @return true caso já exista uma playlist do mesmo usuário com esse nome.
     * @throws SQLException Em caso de erro no banco de dados.
     */
    
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
    
      /**
     * Busca todas as playlists do usuário logado, junto com as músicas.
     * 
     * @param usuarioId ID do usuário.
     * @return a lista de playlists do usuário.
     * @throws SQLException Em caso de erro no banco de dados.
     */
    
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
    
     /**
     * Busca as músicas de uma playlist específica.
     * Apenas com o nome da música.
     * Utilizado para mostrar as 7 músicas iniciais na view de Editar Playlists
     * 
     * @param idPlaylist ID da playlist.
     * @return Lista de músicas da playlist.
     * @throws SQLException Em caso de erro no banco de dados.
     */
    
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
       
    /**
     * Renomeia uma playlist do usuário.
     * 
     * @param idPlaylist ID da playlist que será renomeada.
     * @param nome inserido pelo usuário.
     * @return true: atualizado com sucesso - false: caso contrário.
     * @throws SQLException Em caso de erro no banco de dados.
     */
    
    public boolean renomearPlaylistDAO(int idPlaylist, String nome) throws SQLException {
        String sql = "UPDATE playlist SET nome = ? WHERE id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, nome);
        stmt.setInt(2, idPlaylist);

        int linha = stmt.executeUpdate();
        return linha > 0;
    }
     
      /**
     * Adiciona uma música selecionada na playlist que o usuário escolheu.
     * 
     * @param idPlaylist ID da playlist escolhida.
     * @param idMusica ID da música que será adicionada.
     * @return true: adicionada com sucesso - false: caso contrário.
     * @throws SQLException Em caso de erro no banco de dados.
     */
    
    public boolean adicionarMusicaPlaylist(int idPlaylist, int idMusica) throws SQLException{
        String sql = "INSERT INTO musicas_da_playlist (id_playlist, id_musica)"
                + "VALUES (?, ?)";          
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, idPlaylist);
        stmt.setInt(2, idMusica);
        int linha = stmt.executeUpdate();
        return linha > 0;
    }
    
     /**
     * Remove uma música selecionada na playlist que o usuário escolheu.
     * 
     * @param idPlaylist ID da playlist escolhida.
     * @param idMusica ID da música que será removida.
     * @return true: removida com sucesso - false: caso contrário.
     * @throws SQLException Em caso de erro no banco de dados.
     */
    
    public boolean removerMusicaPlaylist(int idPlaylist, int idMusica) throws SQLException{
            String sql = "DELETE FROM musicas_da_playlist WHERE "
                    + "id_playlist = ? AND id_musica = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, idPlaylist);
        stmt.setInt(2, idMusica);
        int linha = stmt.executeUpdate();
        return linha > 0;
    }
    
     /**
     * Verifica se uma música já está em uma playlist.
     * 
     * @param idPlaylist ID da playlist.
     * @param idMusica ID da música.
     * @return true: música está na playlist - false: caso contrário.
     * @throws SQLException Em caso de erro no banco de dados.
     */
    
    public boolean verificaMusicaPlaylist(int idPlaylist, int idMusica)throws SQLException{
        String sql = "SELECT COUNT(*) FROM musicas_da_playlist "
                + "WHERE id_playlist = ? AND id_musica = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, idPlaylist);
        stmt.setInt(2, idMusica);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
           return rs.getInt(1) > 0; /**
                                      * verifica por COUNT se a musica ja esta 
                                      * na playlist
                                     **/
       }
       return false;
   }
    
     /**
     * Esse método faz a mesma coisa que o buscarMusicas.
     * Ele busca músicas de uma determinada playlist, a única diferença é que 
     * ele retorna todos os atributos de cada música
     * 
     * @param idPlaylist ID da playlist.
     * @return Lista de músicas da playlist escolhida.
     * @throws SQLException Em caso de erro no banco de dados.
     */
    
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

//            System.out.println("Nome: " + nomeMusica + ", Gênero: " + genero + 
//                    ", Ano: " + anoLancamento + ", Álbum: " + album);

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
  
    /**
     * Exclui playlist selecionada do DB.
     * Obs: As músicas relacionadas com essa playlist são removidas automaticamente
     * pois foi utilizado "ON DELETE CASCADE" que remove sozinho.
     * 
     * @param idPlaylist ID da playlist a ser excluída.
     * @return true se a playlist foi excluída com sucesso, false caso contrário.
     * @throws SQLException Em caso de erro no banco de dados.
     */
  
    public boolean excluirPlaylist(int idPlaylist) throws SQLException {
        /**
         * como a tabela foi criada com ON DELETE CASCADE, todas as musicas relacionadas
         * com o id da playlist ja são removidas automaticamente, entao n
         * e necessario excluir as musicas dessa playlist manualmente!
         */ 
        
        String sqlDeletePlaylist = "DELETE FROM playlist WHERE id = ?";
        PreparedStatement stmt = conn.prepareStatement(sqlDeletePlaylist);
        stmt.setInt(1, idPlaylist);
        int linhas = stmt.executeUpdate();
        
        System.out.println("playlist deletada: " + linhas);

        return linhas > 0;
    }
}
