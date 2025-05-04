/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import DAO.Conexao;
import javax.swing.JOptionPane;
import view.BuscarMusicaFrame;
import java.sql.SQLException;
import DAO.MusicaDAO;
import java.util.ArrayList;
import model.Musica;
import java.sql.Connection;
import view.ResultadoMusicaFrame;

/**
 *
 * @author Danilo
 */
public class ControllerMusica {
    private BuscarMusicaFrame view;
    private String usuario;
    private int id;
    private ResultadoMusicaFrame view2;
    
    public ControllerMusica(BuscarMusicaFrame view,String usuario,int id) {
        this.view = view;
        this.usuario = usuario;
        this.id = id;
    }

    public ControllerMusica(String usuario, int id, ResultadoMusicaFrame view2) {
        this.usuario = usuario;
        this.id = id;
        this.view2 = view2;
    }
     
    
    public void buscarMusica(){
        String nome = view.getTxtNome().getText();
        String artista = view.getTxtArtista().getText();
        String genero = view.getTxtGenero().getText();
        
         if ((nome == null || nome.isEmpty()) && 
            (artista == null || artista.isEmpty()) && 
            (genero == null || genero.isEmpty())) {        
            JOptionPane.showMessageDialog(null, "Nenhum campo foi preenchido!", 
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try{
            Conexao conexao = new Conexao();
            Connection conn = conexao.getConnection();
            MusicaDAO musicaDAO = new MusicaDAO(conn);
            ArrayList <Musica> musicas = new ArrayList<>();
            
            // ARRAYLIST QUE RECEBE AS MÚSICAS ENCONTRADAS
            
            musicas = musicaDAO.buscarMusicas(
            nome.isEmpty() ? null : nome, 
            artista.isEmpty() ? null : artista, 
            genero.isEmpty() ? null : genero);
            
            // BUSCA AS MUSICAS E RETORNA O RESULTADO NO ARRAYLIST 
            
            if (musicas.isEmpty()) {
                JOptionPane.showMessageDialog(null, 
                                              "Nenhuma música encontrada.",
                                              "Resultado", 
                                              JOptionPane.INFORMATION_MESSAGE);
            } 
            else {
//                StringBuilder sb = new StringBuilder("Músicas encontradas:\n");
//                for (Musica musica : musicas) {
//                    sb.append(musica.getNome()).append("\n");
//                }
//                JOptionPane.showMessageDialog(null, sb.toString(), 
//                        "Resultado da busca", JOptionPane.INFORMATION_MESSAGE);               
//                for(Musica m : musicas){
//                    System.out.println("Artista: " + m.getArtista() +
//                            "\nNome: " + m.getNome() +
//                            "\nGenero: " + m.getGenero() +
//                            "\nAno: " + m.getAnoLancamento() + 
//                            "\nAlbum: " + m.getAlbum() + 
//                            '\n');
//                }
                view.setVisible(false);
                ResultadoMusicaFrame tela = 
                        new ResultadoMusicaFrame(this.usuario, this.id, musicas);
                
                tela.setLocationRelativeTo(null);
                tela.setVisible(true);
            }
            
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Erro ao buscar músicas: ",
                                      "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    
    
    // METODOS PARA MOSTRAR POP-UP DE ÊXITO E CHAMAR O METODO curtir E descurtir
    // LOCALIZADO NO ARQUIVO MusicaDAO.java
    
    public void curtirMusica(int musicaId) {
        System.out.println("ID MUSICA: "+musicaId);

        try {
            Conexao conexao = new Conexao();
            Connection conn = conexao.getConnection();
            MusicaDAO musicaDAO = new MusicaDAO(conn);
            System.out.println();
            musicaDAO.curtirMusica(id, musicaId);

            JOptionPane.showMessageDialog(view, "Música curtida!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } 
        catch (SQLException e) {          
            JOptionPane.showMessageDialog(view, "Erro ao curtir música!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void descurtirMusica(int musicaId) {
        try {
            Conexao conexao = new Conexao();
            Connection conn = conexao.getConnection();
            MusicaDAO musicaDAO = new MusicaDAO(conn);
            musicaDAO.descurtirMusica(id, musicaId);

            JOptionPane.showMessageDialog(view, "Música descurtida!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } 
        catch (SQLException e) {
            JOptionPane.showMessageDialog(view, "Erro ao descurtir música!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}


