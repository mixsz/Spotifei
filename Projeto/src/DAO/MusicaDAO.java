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
 * Classe responsável pelo acesso e manipulação de Músicas no banco de dados.
 * Possui métodos pra curtir, descurtir, buscar músicas e gerenciar histórico.
 * 
 * @author Danilo
 */

public class MusicaDAO {
    private Connection conn;

    /**
     * Construtor que recebe a conexão com o banco de dados.
     * 
     * @param conn conexão já aberta com o banco de dados.
     */
    
    public MusicaDAO(Connection conn) {
        this.conn = conn;
    }
    
    /**
     * Busca músicas filtrando por nome, artista e/ou gênero e associando.
     * Além disso manipula o histórico das músicas buscadas do usuário.
     * 
     * Retorna a lista de músicas filtrada.
     * 
     * @param nome filtro pelo nome da música (pode ser null)
     * @param artista filtro pelo nome do artista (pode ser null)
     * @param genero filtro pelo gênero musical (pode ser null)
     * @param idUsuario id do usuário para registrar as músicas no histórico dele
     * @return lista de músicas que atendem aos filtros fornecidos
     * @throws SQLException se ocorrer erro na conexao ao db
     * 
     */
    
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
        //                ELE INCLUI COMO UM VALOR PREENCHIDO CORRETO!!!
        
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
      
      
     /**
     * Marca a música como curtida, ou seja muda/cria o status 'curtida'
     * entre o id da música e o id do usuário.
     * 
     * @param usuarioId id do usuário
     * @param musicaId id da música curtida
     * @return true: marcada como curtida - false: estava curtida
     * @throws SQLException se ocorrer erro no DB
     */
      
     public boolean curtirMusica(int usuarioId, int musicaId) throws SQLException {
        // primeiro verifica se ja foi curtida pelo usuario
        String verificaSql = "SELECT status FROM interacao WHERE usuario_id = ? "
                + "AND musica_id = ?";
        PreparedStatement verificaStmt = conn.prepareStatement(verificaSql);
        verificaStmt.setInt(1, usuarioId);
        verificaStmt.setInt(2, musicaId);
        ResultSet rs = verificaStmt.executeQuery();

        if (rs.next()) { // ja possui um status
            String statusAtual = rs.getString("status");
            if ("curtida".equalsIgnoreCase(statusAtual)) { 
            //verifica se foi curtida em vez de descurtida
                rs.close();
                verificaStmt.close();
                return false; // (ja curtida)
            }
        }
        
        rs.close();
        verificaStmt.close();

        String insertSql = "INSERT INTO interacao (usuario_id, musica_id, status) " +
                           "VALUES (?, ?, ?) " +
                           "ON CONFLICT (usuario_id, musica_id) "
                           + "DO UPDATE SET status = EXCLUDED.status";

        PreparedStatement insertStmt = conn.prepareStatement(insertSql);
        insertStmt.setInt(1, usuarioId);
        insertStmt.setInt(2, musicaId);
        insertStmt.setString(3, "curtida");
        insertStmt.executeUpdate();
        insertStmt.close();

        return true;
    }

    /**
     * Marca a música como descurtida, ou seja muda/cria o status 'descurtida'
     * entre o id da música e o id do usuário.
     * 
     * @param usuarioId id do usuário
     * @param musicaId id da música descurtida
     * @return true: marcada como descurtida - false: estava descurtida
     * @throws SQLException se ocorrer erro no DB
     */
     
    public boolean descurtirMusica(int usuarioId, int musicaId) throws SQLException {
        String verificaSql = "SELECT status FROM interacao WHERE usuario_id = ?"
                + " AND musica_id = ?";
        PreparedStatement verificaStmt = conn.prepareStatement(verificaSql);
        verificaStmt.setInt(1, usuarioId);
        verificaStmt.setInt(2, musicaId);
        ResultSet rs = verificaStmt.executeQuery();

        if (rs.next()) { // mesmo processo, aqui ela ja possui status
            String statusAtual = rs.getString("status");
            if ("descurtida".equalsIgnoreCase(statusAtual)) { // aqui verifica qual
                rs.close();
                verificaStmt.close();
                return false; // ja descurtida
            }
        }
        rs.close();
        verificaStmt.close();

        String insertSql = "INSERT INTO interacao (usuario_id, musica_id, status) " +
                           "VALUES (?, ?, ?) " +
                           "ON CONFLICT (usuario_id, musica_id) DO "
                            + "UPDATE SET status = EXCLUDED.status";

        PreparedStatement insertStmt = conn.prepareStatement(insertSql);
        insertStmt.setInt(1, usuarioId);
        insertStmt.setInt(2, musicaId);
        insertStmt.setString(3, "descurtida");
        insertStmt.executeUpdate();
        insertStmt.close();

        return true;
    }
    
     /**
     * Adiciona uma música no histórico do user.
     * Se a música já estiver no histórico, ela é movida para o final.
     * Limita o histórico pra 10 músicas.
     * 
     * @param idUsuario id do usuário
     * @param idMusica id da música que vai ser adicionada no historico.
     * @throws SQLException se ocorrer erro na operação no banco
     */
    
    public void adicionarMusicaNoHistorico(int idUsuario, int idMusica) throws SQLException {
            // Passo 1: verifica se a musica ja ta no historico
            
            String sqlVerificar = "SELECT id FROM historico WHERE id_usuario = ? "
                    + "AND id_musica = ?";
            PreparedStatement verificaStmt = conn.prepareStatement(sqlVerificar);
            verificaStmt.setInt(1, idUsuario);
            verificaStmt.setInt(2, idMusica);
            ResultSet rs = verificaStmt.executeQuery();

            // OBS: se ja estiver, ela vai pro final da fila
            if (rs.next()) {
                String sqlExcluir = "DELETE FROM historico WHERE id_usuario = "
                        + "? AND id_musica = ?";
                PreparedStatement excluiStmt = conn.prepareStatement(sqlExcluir);
                excluiStmt.setInt(1, idUsuario);
                excluiStmt.setInt(2, idMusica);
                excluiStmt.executeUpdate();
            }

            // Passo 2: adiciona no final do historico
            String sqlInserir = "INSERT INTO historico (id_usuario, "
                    + "id_musica) VALUES (?, ?)";
            PreparedStatement insereStmt = conn.prepareStatement(sqlInserir);
            insereStmt.setInt(1, idUsuario);
            insereStmt.setInt(2, idMusica);
            insereStmt.executeUpdate();

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
    
     /**
     * Aqui ele busca as músicas do histórico
     * 
     * @param idUsuario id do usuário
     * @return ArrayList com as 10 (ou menos) músicas buscadas por último
     * @throws SQLException se ocorrer erro no db
     */
    
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
            musicas.add(musica);  
        }

        return musicas;
    }   
    
     /**
     * Busca as músicas curtidas
     * 
     * @param idUsuario id do usuário
     * @return ArrayList com todas as musicas curtidas do usuario
     * @throws SQLException se ocorrer erro na consulta ao banco
     */

    public ArrayList<Musica> buscarMusicasCurtidas(int idUsuario) throws SQLException{
         ArrayList<Musica> musicas = new ArrayList<>();

        String sql = "SELECT m.id, m.nome, m.genero, m.ano_lancamento, " +
                 "m.album, a.nome_artistico AS artista_nome " +
                 "FROM interacao i " +
                 "JOIN musica m ON i.musica_id = m.id " +
                 "JOIN artista a ON m.artista_id = a.id " +
                 "WHERE i.usuario_id = ? AND i.status = 'curtida' " + // 
                 "ORDER BY m.nome ASC";

        
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
            musicas.add(musica);  
        }

        return musicas;
    }
    
    /**
     * Busca as músicas descurtidas
     * 
     * @param idUsuario id do usuário
     * @return ArrayList com todas as musicas descurtidas do usuario
     * @throws SQLException se ocorrer erro na consulta ao banco
     */
    
     public ArrayList<Musica> buscarMusicasDescurtidas(int idUsuario) throws SQLException{
         ArrayList<Musica> musicas = new ArrayList<>();

        String sql = "SELECT m.id, m.nome, m.genero, m.ano_lancamento, " +
                 "m.album, a.nome_artistico AS artista_nome " +
                 "FROM interacao i " +
                 "JOIN musica m ON i.musica_id = m.id " +
                 "JOIN artista a ON m.artista_id = a.id " +
                 "WHERE i.usuario_id = ? AND i.status = 'descurtida' " + // 
                 "ORDER BY m.nome ASC";

        
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
            musicas.add(musica); 
        }

        return musicas;
    }
}
