package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import model.Usuario;

public class UsuarioDAO {
    private Connection conn;

    public UsuarioDAO(Connection conn) {
        this.conn = conn;
    }
    
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
