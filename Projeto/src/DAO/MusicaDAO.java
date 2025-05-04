/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Musica;
import java.sql.ResultSet;
import model.Artista;
/**
 *
 * @author Danilo
 */
public class MusicaDAO {
    private Connection conn;

    public MusicaDAO(Connection conn) {
        this.conn = conn;
    }
    
      public ArrayList<Musica> buscarMusicas(String nome, String artista, 
              String genero) throws SQLException {
        ArrayList<Musica> musicas = new ArrayList<>();
        
       StringBuilder sql = new StringBuilder("SELECT m.id, m.nome, m.genero, "
                                      + "m.ano_lancamento, "
                                      + "m.album, "
                                      + "a.nome_artistico AS artista_nome "
                                      + "FROM musica m "
                                      + "JOIN artista a ON m.artista_id = a.id " 
                                      + "WHERE 1=1");

       
       // faz um INNER JOIN com a table artista para salvar o nome do cantor(a)
        
        if (nome != null && !nome.isEmpty()) {
            sql.append(" AND m.nome ILIKE ?");
        }
        // ADICIONA ESSES COMANDOS SQL CASO O USUARIO TENHA PREENCHIDO O CAMPO
        
        if (artista != null && !artista.isEmpty()) {
            sql.append(" AND a.nome_artistico ILIKE ?");
        }
        
        if (genero != null && !genero.isEmpty()) {
            sql.append(" AND m.genero ILIKE ?");
        }
        // FOI UTILIZADO O 'ILIKE' POIS ELE IGNORA CHAR MAIUSCULO E MINUSCULO
        
        PreparedStatement statement = conn.prepareStatement(sql.toString());
        
        int atb = 1;
        

        if (nome != null && !nome.isEmpty()) {
            statement.setString(atb++, nome + "%"); 
        }
        
        if (artista != null && !artista.isEmpty()) {
            statement.setString(atb++, artista + "%"); 
        }
        
        if (genero != null && !genero.isEmpty()) {
            statement.setString(atb++, genero + "%");
        }
        // PESQUISA DE ACORDO COM OS CAMPOS PREENCHIDOS (SE TIVER NULL, IGNORA)
        // POSSÍVEL ERRO: SE O USUÁRIO DER UM ESPAÇO EM BRANCO, 
        //                ELE INCLUI COMO UM VALOR PREENCHIDO CORRETO
        
        ResultSet rs = statement.executeQuery();


        while (rs.next()) { 
            int id = rs.getInt("id");
            String nomeArtista = rs.getString("artista_nome");

            Artista artistaObj = new Artista(nomeArtista);
            Musica musica = new Musica(
                id,
                rs.getString("nome"),
                artistaObj,
                rs.getString("genero"),
                rs.getInt("ano_lancamento"),
                rs.getString("album")         
            );
            musicas.add(musica);           
            // TRANSFORMA EM OBJETO E DEPOIS ADICIONA NO ARRAYLIST DE MUSICAS
        }
        
        return musicas;
    }
      
      
    // CURTIR E DESCURTIR MÚSICA
      
     public void curtirMusica(int usuarioId, int musicaId) throws SQLException {

        String sql = "INSERT INTO interacao (usuario_id, musica_id, status) " +
                 "VALUES (?, ?, ?) " +
                 "ON CONFLICT (usuario_id, musica_id) "
                + "DO UPDATE SET status = EXCLUDED.status";
        
        // ON CONFLICT serve pra caso o id do usuario e musica ja exista na tabela
        // entao ele faz UPDATE em vez de INSERT
        
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, usuarioId);
            stmt.setInt(2, musicaId);
            stmt.setString(3, "curtida");
            stmt.executeUpdate();
        } 
        catch (SQLException e) {
            System.err.println("Erro ao curtir música: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void descurtirMusica(int usuarioId, int musicaId) {
        String sql = "INSERT INTO interacao (usuario_id, musica_id, status) " +
                     "VALUES (?, ?, ?) " +
                     "ON CONFLICT (usuario_id, musica_id) DO UPDATE SET status = EXCLUDED.status";
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, usuarioId);
            stmt.setInt(2, musicaId);
            stmt.setString(3, "descurtida");
            stmt.executeUpdate();
        } 
        catch (SQLException e) {
            System.err.println("Erro ao descurtir música: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
