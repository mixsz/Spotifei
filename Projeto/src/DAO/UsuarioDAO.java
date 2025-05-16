package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import model.Usuario;

/**
 * Classe responsável pelas interações com o DB relacionadas a entidade Usuario.
 * Fornece cadastro, login, verificação de username e autenticação
 * 
 * @author Danilo
 */

public class UsuarioDAO {
    private Connection conn;
    
     /**
     * Construtor que recebe a conexão com o banco de dados.
     * 
     * @param conn conexão já aberta com o banco de dados.
     */
    
    public UsuarioDAO(Connection conn) {
        this.conn = conn;
    }
    
     /**
     * cadastra um novo usuário no DB.
     * 
     * @param usuario objeto Usuario com informações recebidas.
     * @return true: cadastro bem-sucedido - false: caso contrário.
     * @throws SQLException se ocorrer erro no banco de dados.
     */
    
    public boolean cadastrarUsuarioDB(Usuario usuario) throws SQLException {
        String sql = "insert into usuario (nome, sobrenome, username, idade, "
                + "sexo, senha) values (?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = conn.prepareStatement(sql);
       
        statement.setString(1, usuario.getNome());
        statement.setString(2, usuario.getSobrenome());
        statement.setString(3, usuario.getUsername());
        statement.setInt(4, usuario.getIdade());
        statement.setString(5, usuario.getSexo());
        statement.setString(6, usuario.getSenha());
        int linha = statement.executeUpdate();

        return linha>0;
    }
    
     /**
     * Verifica se o username digitado já está em uso (no banco de dados).
     * 
     * @param username nome que vai ser verificado.
     * @return true: se o nome de usuário já existir - false: caso contrário.
     * @throws SQLException se ocorrer erro no db.
     */
    
    public boolean verificaUsername(String username) throws SQLException {
        String sql = "select count(*) from usuario where username = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, username);
        ResultSet res = statement.executeQuery();

        if (res.next()) {
            int count = res.getInt(1);
            return count > 0; // se for maior que ZERO é porque ja existe
        }

        return false; 
    }
    
     /**
     * realiza a autenticação de um usuário pelo username e senha.
     * obs: utilizado o jeito não hackeavel aprendido em aula.
     * 
     * @param usuario objeto Usuario contendo apenas username e senha.
     * @return ResultSet se encontrado, retorna os dados do usuario.
     * @throws SQLException se ocorrer erro na execução do db.
     */
    
     public ResultSet logar(Usuario usuario) throws SQLException{
        String sql = "select * from usuario where username = ? AND senha = ?"; 
        //jeito nao hackeavel!!
        
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, usuario.getUsername());
        statement.setString(2, usuario.getSenha());
        statement.execute();
        ResultSet resultado = statement.getResultSet();
        return resultado;
    }
     
}
