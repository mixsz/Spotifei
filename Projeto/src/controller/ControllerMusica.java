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
import view.AdicionarMusicaFrame;
import view.MusicasCurtidasFrame;
import view.MusicasDescurtidasFrame;
import view.ResultadoMusicaFrame;
import view.UltimasDezMusicasFrame;

/**
 *
 * @author Danilo
 */
public class ControllerMusica {
    private BuscarMusicaFrame view;
    private String usuario;
    private int id;
    private ResultadoMusicaFrame view2;
    private AdicionarMusicaFrame view3;
    private UltimasDezMusicasFrame view4;
    private MusicasCurtidasFrame view5;
    private MusicasDescurtidasFrame view6;
    
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
     
    public ControllerMusica(AdicionarMusicaFrame view3,String usuario,int id) {
        this.view3 = view3;
        this.usuario = usuario;
        this.id = id;
    }
    
    public ControllerMusica(UltimasDezMusicasFrame view4,
            String usuario,int id) {
        this.view4 = view4;
        this.usuario = usuario;
        this.id = id;
    }
    
     public ControllerMusica(MusicasCurtidasFrame view5,
            String usuario,int id) {
        this.view5 = view5;
        this.usuario = usuario;
        this.id = id;
    }
     
    public ControllerMusica(MusicasDescurtidasFrame view6,
          String usuario,int id) {
      this.view6 = view6;
      this.usuario = usuario;
      this.id = id;
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
            genero.isEmpty() ? null : genero,id);
            
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
    
    public ArrayList<Musica> buscarMusicasSimples() {
        String nome = view3.getTxtMusica().getText();
        String artista = view3.getTxtArtista().getText();
        String genero = view3.getTxtGenero().getText();
        ArrayList<Musica> musicas = new ArrayList<>();

      
          if ((nome == null || nome.isEmpty()) && 
            (artista == null || artista.isEmpty()) && 
            (genero == null || genero.isEmpty())) {        
            return new ArrayList<>();
        }
          
        try {
            Conexao conexao = new Conexao();
            Connection conn = conexao.getConnection();
            MusicaDAO musicaDAO = new MusicaDAO(conn);

            musicas = musicaDAO.buscarMusicas(
                nome.isEmpty() ? null : nome, 
                artista.isEmpty() ? null : artista, 
                genero.isEmpty() ? null : genero,id
            );
             
        } 
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, 
            "Erro ao buscar músicas: " + e.getMessage(),
            "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return new ArrayList<>(); 
        }
        return musicas;
    }
    
    
    // METODOS PARA MOSTRAR POP-UP DE ÊXITO E CHAMAR O METODO curtir E descurtir
    // LOCALIZADO NO ARQUIVO MusicaDAO.java
    
    public void curtirMusica(int musicaId) {
        try {
            Conexao conexao = new Conexao();
            Connection conn = conexao.getConnection();
            MusicaDAO musicaDAO = new MusicaDAO(conn);

            boolean sucesso = musicaDAO.curtirMusica(id, musicaId);

            if (sucesso) {
                JOptionPane.showMessageDialog(view, "Música curtida!", 
                        "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(view, "Você já curtiu essa música!", 
                        "Aviso", JOptionPane.WARNING_MESSAGE);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(view, "Erro ao curtir música!", 
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void descurtirMusica(int musicaId) {
        try {
            Conexao conexao = new Conexao();
            Connection conn = conexao.getConnection();
            MusicaDAO musicaDAO = new MusicaDAO(conn);

            boolean sucesso = musicaDAO.descurtirMusica(id, musicaId);

            if (sucesso) {
                JOptionPane.showMessageDialog(view, "Música descurtida!", 
                        "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(view, "Você já descurtiu essa música!", 
                        "Aviso", JOptionPane.WARNING_MESSAGE);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(view, "Erro ao descurtir música!", 
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    
    public ArrayList<Musica> buscarUltimasBuscas() {
    
        ArrayList<Musica> musicas = new ArrayList<>();
        try {
            Conexao conexao = new Conexao();
            Connection conn = conexao.getConnection();
            MusicaDAO musicaDAO = new MusicaDAO(conn);

            musicas = musicaDAO.buscarUltimasMusicas(this.id);
        } 
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, 
                "Erro ao exibir histórico! ", 
                "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

        return musicas;
    }

    public ArrayList<Musica> buscarMusicasCurtidas() {
    
        ArrayList<Musica> musicas = new ArrayList<>();
        try {
            Conexao conexao = new Conexao();
            Connection conn = conexao.getConnection();
            MusicaDAO musicaDAO = new MusicaDAO(conn);

            musicas = musicaDAO.buscarMusicasCurtidas(this.id);
        } 
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, 
                "Erro ao exibir músicas curtidas!", 
                "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

        return musicas;
    }
    
    public ArrayList<Musica> buscarMusicasDescurtidas() {
    
        ArrayList<Musica> musicas = new ArrayList<>();
        try {
            Conexao conexao = new Conexao();
            Connection conn = conexao.getConnection();
            MusicaDAO musicaDAO = new MusicaDAO(conn);

            musicas = musicaDAO.buscarMusicasDescurtidas(this.id);
        } 
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, 
                "Erro ao exibir músicas descurtidas!", 
                "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

        return musicas;
    }
    
}


