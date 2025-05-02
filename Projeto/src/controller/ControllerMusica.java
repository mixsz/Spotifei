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

/**
 *
 * @author Danilo
 */
public class ControllerMusica {
    private BuscarMusicaFrame view;

    public ControllerMusica(BuscarMusicaFrame view) {
        this.view = view;
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
                StringBuilder sb = new StringBuilder("Músicas encontradas:\n");
                for (Musica musica : musicas) {
                    sb.append(musica.getNome()).append("\n");
                }
                JOptionPane.showMessageDialog(null, sb.toString(), 
                        "Resultado da busca", JOptionPane.INFORMATION_MESSAGE);
                
                for(Musica m : musicas){
                    System.out.println("Artista: " + m.getArtista() +
                            "\nNome: " + m.getNome() +
                            "\nGenero: " + m.getGenero() +
                            "\nAno: " + m.getAnoLancamento() + 
                            "\nAlbum: " + m.getAlbum() + 
                            '\n');
                }
            }
            
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Erro ao buscar músicas: ",
                                      "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}
