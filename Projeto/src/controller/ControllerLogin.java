/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import DAO.Conexao;
import DAO.UsuarioDAO;
import javax.swing.JOptionPane;
import model.Usuario;
import view.LoginFrame;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import view.HomeFrame;

/**
 *
 * @author Danilo
 */
public class ControllerLogin{
    
    private LoginFrame view;
        
    public ControllerLogin(LoginFrame loginFrame) {
        this.view = loginFrame;
    }
        
    public void loginUsuario(){
        Usuario usuario = new Usuario(view.getTxtUsername().getText(),
                            view.getTxtSenha().getText());
        Conexao conexao = new Conexao();
         try{
            Connection conn = conexao.getConnection();
            UsuarioDAO dao = new UsuarioDAO(conn);
            ResultSet res = dao.logar(usuario);
            if(res.next()){
                int idUsuario = res.getInt("id");
                if (usuario.autenticar(usuario.getUsername(), usuario.getSenha())){
                //se possuir linha(s), verifica se o username e senha batem
                    JOptionPane.showMessageDialog(view, 
                                                  "Login efetuado!","Aviso",
                                                  JOptionPane.INFORMATION_MESSAGE);
                    HomeFrame home = new HomeFrame(usuario.getUsername(),idUsuario);
                    view.setVisible(false); 
                    home.setLocationRelativeTo(null);
                    home.setVisible(true);
                }
                else{
                    JOptionPane.showMessageDialog(view, "Username ou senha i"
                                                  + "ncorreto!","Aviso",
                                                  JOptionPane.ERROR_MESSAGE);
                }
            }
            else{
                 JOptionPane.showMessageDialog(view, "Username ou senha i"
                                               + "ncorreto!","Aviso",
                                               JOptionPane.ERROR_MESSAGE);
            }
        }
        catch(SQLException e){
             JOptionPane.showMessageDialog(view, "Erro de conexão!","Aviso",
                                           JOptionPane.ERROR_MESSAGE);
        }
    }
}
