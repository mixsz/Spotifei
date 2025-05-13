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
              String genero, int idUsuario) throws SQLException {
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
        int contagem = 0;
        for (Musica musica : musicas) {
            if(contagem >= 10){
                break;
            }
            adicionarMusicaNoHistorico(idUsuario, musica.getId());
            contagem++;
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
    
    public void adicionarMusicaNoHistorico(int idUsuario, int idMusica) throws SQLException {
            // Passo 1: verifica se a musica ja ta no historico
            
            String sqlVerificar = "SELECT id FROM historico WHERE id_usuario = ? "
                    + "AND id_musica = ?";
            PreparedStatement stmtVerificar = conn.prepareStatement(sqlVerificar);
            stmtVerificar.setInt(1, idUsuario);
            stmtVerificar.setInt(2, idMusica);
            ResultSet rs = stmtVerificar.executeQuery();

            // OBS: se ja estiver, ela vai pro final da fila
            if (rs.next()) {
                String sqlExcluir = "DELETE FROM historico WHERE id_usuario = "
                        + "? AND id_musica = ?";
                PreparedStatement stmtExcluir = conn.prepareStatement(sqlExcluir);
                stmtExcluir.setInt(1, idUsuario);
                stmtExcluir.setInt(2, idMusica);
                stmtExcluir.executeUpdate();
            }

            // Passo 2: adiciona no final do historico
            String sqlInserir = "INSERT INTO historico (id_usuario, "
                    + "id_musica) VALUES (?, ?)";
            PreparedStatement stmtInserir = conn.prepareStatement(sqlInserir);
            stmtInserir.setInt(1, idUsuario);
            stmtInserir.setInt(2, idMusica);
            stmtInserir.executeUpdate();

            // Verifica se já tem 10 musicas no id desse usuario
            String sqlHistorico = "SELECT COUNT(*) AS total FROM historico"
                    + " WHERE id_usuario = ?";
            PreparedStatement stmtContar = conn.prepareStatement(sqlHistorico);
            stmtContar.setInt(1, idUsuario);
            ResultSet rsContagem = stmtContar.executeQuery();

            if (rsContagem.next() && rsContagem.getInt("total") > 10) {
                // remove a musica mais antiga
                String sqlExcluiAntiga = "DELETE FROM historico "
                        + "WHERE id_usuario = ? AND id = (SELECT id FROM historico "
                        + "WHERE id_usuario = ? ORDER BY id ASC LIMIT 1)";
            // a musica com o menor id sera removida pois esta sendo ORDENADO ASC
                PreparedStatement stmtExcluirMaisAntiga = conn.prepareStatement(sqlExcluiAntiga);
                stmtExcluirMaisAntiga.setInt(1, idUsuario);
                stmtExcluirMaisAntiga.setInt(2, idUsuario);
                stmtExcluirMaisAntiga.executeUpdate();
            }
        }
    
    public ArrayList<Musica> buscarUltimasMusicas(int idUsuario) throws SQLException {
        ArrayList<Musica> musicas = new ArrayList<>();

        // Busca as 10 últimas musicas
        String sql = "SELECT m.id, m.nome, m.genero, m.ano_lancamento, "
                        + "m.album, a.nome_artistico AS artista_nome " +
                     "FROM musica m " +
                     "JOIN artista a ON m.artista_id = a.id " +
                     "JOIN historico h ON m.id = h.id_musica " +
                     "WHERE h.id_usuario = ? " +
                     "ORDER BY h.id DESC " +
                     "LIMIT 10";
        /** 
         * o LIMIT 10 é redundante, mas como n vai mudar nada e é mais seguro,
         * vou deixar
        */
        
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, idUsuario);

        ResultSet rs = stmt.executeQuery();

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
            musicas.add(musica);  // Adiciona a música na lista
        }

        return musicas;
    }   

    
}
