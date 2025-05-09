/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import DAO.Conexao;
import DAO.PlaylistDAO;
import javax.swing.JOptionPane;
import model.Playlist;
import view.CriarPlaylistFrame;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.ArrayList;
import view.EditarPlaylistFrame;
import view.GerenciarPlaylistFrame;
import java.sql.Connection;
import view.RenomearPlaylistFrame;

/**
 *
 * @author Danilo
 */
public class ControllerPlaylist {
    private CriarPlaylistFrame view;
    private String usuario;
    private int id;
    private EditarPlaylistFrame view2;
    private RenomearPlaylistFrame view3;


    public ControllerPlaylist(CriarPlaylistFrame view, String usuario, int id) {
        this.view = view;
        this.usuario = usuario;
        this.id = id;
    }

    public ControllerPlaylist(String usuario, int id, EditarPlaylistFrame view2) {
        this.usuario = usuario;
        this.id = id;
        this.view2 = view2;
    }

    public ControllerPlaylist(String usuario, int id, RenomearPlaylistFrame view3) {
        this.usuario = usuario;
        this.id = id;
        this.view3 = view3;
    }
    
    
    
    public void Criar(){
        String nomePlaylist = view.getTxtNomePlaylist().getText();
        if(nomePlaylist == null || nomePlaylist.isEmpty()){
            JOptionPane.showMessageDialog(null, "O nome da playlist precisa "
                    + "ser informado!", 
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return;          
        }
        Playlist playlist = new Playlist(nomePlaylist,id);
        try{
            Conexao conexao = new Conexao();
            Connection conn = conexao.getConnection(); 
            PlaylistDAO playlistDAO = new PlaylistDAO(conn);
            
            boolean existe = playlistDAO.verificaNome(nomePlaylist,id);
            if(existe){
                JOptionPane.showMessageDialog(null, "Você já utilizou esse nome!", 
                "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            boolean sucesso = playlistDAO.criarPlaylist(playlist);
            if(sucesso){
                JOptionPane.showMessageDialog(null, "Playlist criada com sucesso!", 
                "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                GerenciarPlaylistFrame gp = new GerenciarPlaylistFrame(usuario,id);
                view.setVisible(false);
                gp.setLocationRelativeTo(null);
                gp.setVisible(true); 
            }
        }
        catch(SQLException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro de conexão!", 
                                          "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }  

     public ArrayList<Playlist> getPlaylists(int usuarioId) {
        try {
            Conexao conexao = new Conexao();

            Connection conn = conexao.getConnection(); 
            
            PlaylistDAO playlistDAO = new PlaylistDAO(conn);

            return playlistDAO.acharPlaylists(usuarioId);
        } 
        catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
     
    public void renomearPlaylist() {
        String nome = view3.getTxtNomePlaylist().getText();
        int idPlaylist = view3.getIdPlaylist(); 

        if (nome == null || nome.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, 
                                        "O nome está vazio!",
                                         "Erro", JOptionPane.ERROR_MESSAGE); 
            return;
        }

        try {
            Conexao conexao = new Conexao();
            Connection conn = conexao.getConnection();
            PlaylistDAO playlistDAO = new PlaylistDAO(conn);
            
             boolean existe = playlistDAO.verificaNome(nome, id);

                if (existe) {
                JOptionPane.showMessageDialog(null, 
                        "Este nome de playlist está em uso!", 
                        "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
                
            boolean sucesso = playlistDAO.renomearPlaylistDAO(idPlaylist, nome);

            if (sucesso) {
                 JOptionPane.showMessageDialog(null, 
                                    "Playlist renomeada com sucesso!", 
                                    "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                view3.dispose();
                EditarPlaylistFrame hm = new EditarPlaylistFrame(usuario,id);
                hm.setLocationRelativeTo(null);
                hm.setVisible(true);
            } 
            else {
                 JOptionPane.showMessageDialog(null, 
                                        "Erro ao renomear playlist!",
                                         "Erro", JOptionPane.ERROR_MESSAGE); 
            }
        } 
        catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro de conexão!",
                                         "Erro", JOptionPane.ERROR_MESSAGE);
        }
    } 
}
