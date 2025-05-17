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
import java.util.ArrayList;
import view.EditarPlaylistFrame;
import view.GerenciarPlaylistFrame;
import java.sql.Connection;
import model.Musica;
import view.AdicionarMusicaFrame;
import view.ExcluirMusicaFrame;
import view.ExcluirPlaylistFrame;
import view.RenomearPlaylistFrame;
/**
 * controlador responsável por gerenciar metodos relacionadas as playlists,
 * como criar, renomear, excluir, adicionar/remover músicas.
 * 
 * obs: existem varios construtores para essa classe, pois muitas interfaces
 * graficas diferentes utilizam metodos que estao nessa classe.
 * 
 * obs2: todos os construtores tem obrigatoriamente 2 parametros: id e username
 */
public class ControllerPlaylist {
    private CriarPlaylistFrame view;
    private String usuario;
    private int idUser;
    private EditarPlaylistFrame view2;
    private RenomearPlaylistFrame view3;
    private AdicionarMusicaFrame view4;
    private ExcluirMusicaFrame view5;
    private ExcluirPlaylistFrame view6;
    private int idPlaylist;
    private ArrayList<Playlist> playlists;
    
    /**
     * 
     * @param view
     * @param usuario
     * @param id 
     */
    
    public ControllerPlaylist(CriarPlaylistFrame view, String usuario, int id) {
        this.view = view;
        this.usuario = usuario;
        this.idUser = id;
    }
    /**
     * 
     * @param usuario
     * @param id
     * @param view2 
     */
    public ControllerPlaylist(String usuario, int id, EditarPlaylistFrame view2) {
        this.usuario = usuario;
        this.idUser = id;
        this.view2 = view2;
    }
    /**
     * 
     * @param usuario
     * @param id
     * @param view3
     * @param idPlaylist 
     */
    public ControllerPlaylist(String usuario, int id, RenomearPlaylistFrame view3,
            int idPlaylist) {
        this.usuario = usuario;
        this.idUser = id;
        this.view3 = view3;
        this.idPlaylist=idPlaylist;
    }
    /**
     * 
     * @param usuario
     * @param idUser
     * @param view4
     * @param idPlaylist 
     */
     public ControllerPlaylist(String usuario, int idUser, 
             AdicionarMusicaFrame view4,
     int idPlaylist) {
        this.usuario = usuario;
        this.idUser = idUser;
        this.view4 = view4;
        this.idPlaylist = idPlaylist;
    }
    /**
     * 
     * @param usuario
     * @param idUser
     * @param view5
     * @param idPlaylist 
     */
    public ControllerPlaylist(String usuario, int idUser, ExcluirMusicaFrame view5,
     int idPlaylist) {
        this.usuario = usuario;
        this.idUser = idUser;
        this.view5 = view5;
        this.idPlaylist = idPlaylist;
    }
    /**
     * 
     * @param usuario
     * @param idUser
     * @param view6 
     */
    public ControllerPlaylist(String usuario, int idUser,
            ExcluirPlaylistFrame view6) {
        this.usuario = usuario;
        this.idUser = idUser;
        this.view6 = view6;
    }
     /**
     * Metodo que cria uma nova playlist para o usuario conforme o nome dado,
     * verifica se o nome já existe entre as próprias playlists criadas por ele.
     */
    public void Criar(){
        String nomePlaylist = view.getTxtNomePlaylist().getText();
        if(nomePlaylist == null || nomePlaylist.isEmpty()){
            JOptionPane.showMessageDialog(null, "O nome da playlist precisa "
                    + "ser informado!", 
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return;          
        }
        Playlist playlist = new Playlist(nomePlaylist,idUser);
        try{
            Conexao conexao = new Conexao();
            Connection conn = conexao.getConnection(); 
            PlaylistDAO playlistDAO = new PlaylistDAO(conn);
            
            boolean existe = playlistDAO.verificaNome(nomePlaylist,idUser);
            if(existe){
                JOptionPane.showMessageDialog(null, "Você já utilizou esse nome!", 
                "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            boolean sucesso = playlistDAO.criarPlaylist(playlist);
            if(sucesso){
                JOptionPane.showMessageDialog(null, "Playlist criada com sucesso!", 
                "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                GerenciarPlaylistFrame gp = new GerenciarPlaylistFrame(usuario,idUser);
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
    /**
     * Metodo que retorna todas as playlists (com as musicas) pelo id do usuario
     * @param usuarioId
     * @return 
     */

     public ArrayList<Playlist> getPlaylists(int usuarioId) {
        try {
            Conexao conexao = new Conexao();

            Connection conn = conexao.getConnection(); 
            
            PlaylistDAO playlistDAO = new PlaylistDAO(conn);

            return playlistDAO.acharPlaylists(usuarioId);
        } 
        catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>(); // retorna um arraylist vazio se der erro
        }
    }
    /**
     * Metodo que troca o nome da playlist que o usuário selecionou (linha 189)
     * O metodo verifica se o nome ja foi utilizado (215) para evitar erros
     */
     
    public void renomearPlaylist() {
        String nome = view3.getTxtNomePlaylist().getText();
        int idPlaylist = view3.getIdPlaylist(); 

        if (nome == null || nome.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, 
                                          "O nome está vazio!",
                                          "Erro", JOptionPane.ERROR_MESSAGE); 
            return;
        }
        int resposta = JOptionPane.showConfirmDialog(null, 
                                                     "Tem certeza que deseja "
                                                    + "renomear a playlist?",
                                                     "Confirmar Renomeação", 
                                                     JOptionPane.YES_NO_OPTION, 
                                                     JOptionPane.QUESTION_MESSAGE);

        if (resposta != JOptionPane.YES_OPTION) {
            // Se o usuário clicar em nao, n acontece nd
            return;
        }

        try {
            Conexao conexao = new Conexao();
            Connection conn = conexao.getConnection();
            PlaylistDAO playlistDAO = new PlaylistDAO(conn);

            boolean existe = playlistDAO.verificaNome(nome, idUser);

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
                EditarPlaylistFrame hm = new EditarPlaylistFrame(usuario, idUser);
                hm.setLocationRelativeTo(null);
                hm.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, 
                                              "Erro ao renomear playlist!",
                                              "Erro", JOptionPane.ERROR_MESSAGE); 
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro de conexão!",
                                          "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Adiciona uma música (selecionada pelo user) na playlist pelo id dela.
     * @param idMusica ID da música a ser adicionada
     */
    
    public void adicionaMusicaNaPlaylist(int idMusica){
        try{
            Conexao conexao = new Conexao();
            Connection conn = conexao.getConnection();
            PlaylistDAO playlistDAO = new PlaylistDAO(conn);
            if(!playlistDAO.verificaMusicaPlaylist(idPlaylist, idMusica)){
                boolean sucesso = playlistDAO.adicionarMusicaPlaylist
                                                (idPlaylist, idMusica);
                if(sucesso){
                    JOptionPane.showMessageDialog(view4,
                                "Música adicionada com sucesso!",
                                "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                }
                else{
                    JOptionPane.showMessageDialog(view4, 
                            "Erro ao adicionar música!", 
                            "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
            else{
                JOptionPane.showMessageDialog(view4, 
                        "A música já está na playlist!",
                        "Atenção", JOptionPane.WARNING_MESSAGE);
            }
        }
        catch(SQLException e){
             e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro de conexão!",
                                         "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Remove musica da playlist apos confirmacao.
     * Alem disso, atualiza a tela (view5).
     * @param idPlaylist
     * @param idMusica
     */
    
    public void removerMusicaNaPlaylist(int idPlaylist, int idMusica) {
        if (JOptionPane.showConfirmDialog(
                view5, 
                "Tem certeza que deseja remover essa música?", 
                "Confirmar remoção", 
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
            try {
                Conexao conexao = new Conexao();
                Connection conn = conexao.getConnection();
                PlaylistDAO playlistDAO = new PlaylistDAO(conn);

                boolean sucesso = playlistDAO.removerMusicaPlaylist(idPlaylist, idMusica);

                if (sucesso) {
                    ArrayList<Musica> musicasNEW = 
                    retornarMusicasDaPlaylistCompleto();
                    view5.mostrarMusicas(musicasNEW); 
                    JOptionPane.showMessageDialog(view5, 
                        "Música removida com sucesso!", 
                        "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                  
                } 
                else {
                    JOptionPane.showMessageDialog(view5, 
                        "Erro ao remover a música da playlist!", 
                        "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } 
            catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, 
                    "Erro de conexão!", 
                    "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    /**
     * Retorna as músicas da playlist (sem as outras informacoes, apenas o nome)
     * @return arrayList musicas
     */
    
    public ArrayList <Musica> retornarMusicasDaPlaylist(){
        ArrayList <Musica> musicas = new ArrayList<>();
        try{
            Conexao conexao = new Conexao();
            Connection conn = conexao.getConnection();
            PlaylistDAO playlistDAO = new PlaylistDAO(conn);
            musicas = playlistDAO.buscarMusicas(idPlaylist);
            
        }
        catch(SQLException e){
             e.printStackTrace();
        }
        return musicas;      
    }
    
    /**
     * Diferente do metodo anterior, aqui é retornado as musicas com todas as
     * informações
     * 
     * @return arralist musicas
     */
     public ArrayList <Musica> retornarMusicasDaPlaylistCompleto(){
        ArrayList <Musica> musicas = new ArrayList<>();
        try{
            Conexao conexao = new Conexao();
            Connection conn = conexao.getConnection();
            PlaylistDAO playlistDAO = new PlaylistDAO(conn);
            musicas = playlistDAO.buscarMusicasCompleto(idPlaylist);
            
        }
        catch(SQLException e){
             e.printStackTrace();
        }
        return musicas;      
    }    
    
     /**
     * Exclui a playlist escolhida apos confirmacao.
     * atualiza a janela apos exclusao (view6).
     * @param idPlay ID da playlist.
     */
     
    public void deletarPlaylist(int idPlay) {
        // TESTANDO System.out.println("id no controller: "+idPlay);
        
        int confirma = JOptionPane.showConfirmDialog(null, 
                    "Você tem certeza que deseja excluir esta playlist?", 
                    "Confirmar Exclusão", 
                    JOptionPane.YES_NO_OPTION, 
                    JOptionPane.WARNING_MESSAGE);

        if (confirma == JOptionPane.YES_OPTION) {
            try{
                Conexao conexao = new Conexao();
                Connection conn = conexao.getConnection();
                PlaylistDAO playlistDAO = new PlaylistDAO(conn);
                
                boolean sucesso = playlistDAO.excluirPlaylist(idPlay);

                if (sucesso) {
                    playlists = getPlaylists(idUser); //atualiza a propria playlist
                    view6.mostrarPlaylists(playlists); // e joga na tela dnv
                    JOptionPane.showMessageDialog(view6, 
                                                "Playlist excluída com sucesso!",
                                                "Sucesso",
                                                JOptionPane.INFORMATION_MESSAGE);
                } 
                else {
                     JOptionPane.showMessageDialog(view6, 
                        "Erro ao remover a playlist!", 
                        "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } 
            catch (SQLException e) {
                  JOptionPane.showMessageDialog(view6, 
                        "Erro de conexão!", 
                        "Erro", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
    }
}
