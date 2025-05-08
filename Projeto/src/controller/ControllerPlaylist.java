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

/**
 *
 * @author Danilo
 */
public class ControllerPlaylist {
    private CriarPlaylistFrame view;
    private String usuario;
    private int id;
    private EditarPlaylistFrame view2;

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
    
}
