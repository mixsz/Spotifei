package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Banco: spotifei
 * Usuario: definido por variável de ambiente
 *
 * Classe responsável por estabelecer conexão com o banco de dados.
 *
 * @author Danilo ou Mixzq
 */

public class Conexao {

    /**
     * @return conexao estabelecida
     * @throws SQLException caso de erro com a conexao com o banco de dados
     */
    public Connection getConnection() throws SQLException {

        String url = System.getenv("DB_URL");
        String user = System.getenv("DB_USER");
        String senha = System.getenv("DB_PASSWORD");

        Connection conexao = DriverManager.getConnection(url, user, senha);
        return conexao;
    }
}
