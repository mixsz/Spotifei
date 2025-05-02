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
        
       StringBuilder sql = new StringBuilder("SELECT m.nome, m.genero, "
                                            + "m.ano_lancamento, "
                                            + "m.album, "
                                            + "a.nome_artistico AS artista_nome "
                                            + "FROM musica m "
                                            + "JOIN artista a ON m.artista_id = a.id " 
                                            + "WHERE 1=1"
                                            );
       
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
        
        int index = 1;
        

        if (nome != null && !nome.isEmpty()) {
            statement.setString(index++, nome + "%"); 
        }
        
        if (artista != null && !artista.isEmpty()) {
            statement.setString(index++, artista + "%"); 
        }
        
        if (genero != null && !genero.isEmpty()) {
            statement.setString(index++, genero + "%");
        }
        // PESQUISA DE ACORDO COM OS CAMPOS PREENCHIDOS (SE TIVER NULL, IGNORA)
        // POSSÍVEL ERRO: SE O USUÁRIO DER UM ESPAÇO EM BRANCO, 
        //                ELE INCLUI COMO UM VALOR PREENCHIDO CORRETO
        
        ResultSet rs = statement.executeQuery();


        while (rs.next()) { 
            String nomeArtista = rs.getString("artista_nome");

            Artista artistaObj = new Artista(nomeArtista);
            Musica musica = new Musica(
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
}
