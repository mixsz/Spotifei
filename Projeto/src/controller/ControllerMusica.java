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
 * controlador responsável por lidar com todas as ações de músicas,
 * buscar, curtir, descurtir, salvar no histórico e exibir o historico
 * 
 * tem varios construtores com parametros diferente, pois é uma classe utilizada por 
 * diversas outras interfaces graficas
 * obs: todos os construtores obrigatoriamente tem 2 parametros: id e username
 * 
 * @author Danilo
 */
public class ControllerMusica {
    private BuscarMusicaFrame view;
    /**
     * Todos os construtores possuem tanto o nome do usuário quanto o id do usuário
     * que está logado. 
     * Isso serve para nao perder a informacao do usuario durante
     * a troca de telas
     * 
     */
    
    private String usuario; 
    private int id;
    private ResultadoMusicaFrame view2;
    private AdicionarMusicaFrame view3;
    private UltimasDezMusicasFrame view4;
    private MusicasCurtidasFrame view5;
    private MusicasDescurtidasFrame view6;
    /**
     * 
     * @param view
     * @param usuario
     * @param id 
     */
    public ControllerMusica(BuscarMusicaFrame view,String usuario,int id) {
        this.view = view;
        this.usuario = usuario;
        this.id = id;
    }
    /**
     * 
     * @param usuario
     * @param id
     * @param view2 
     */
    public ControllerMusica(String usuario, int id, ResultadoMusicaFrame view2) {
        this.usuario = usuario;
        this.id = id;
        this.view2 = view2;
    }
    /**
     * 
     * @param view3
     * @param usuario
     * @param id 
     */
    public ControllerMusica(AdicionarMusicaFrame view3,String usuario,int id) {
        this.view3 = view3;
        this.usuario = usuario;
        this.id = id;
    }
    /**
     * 
     * @param view4
     * @param usuario
     * @param id 
     */
    public ControllerMusica(UltimasDezMusicasFrame view4,
            String usuario,int id) {
        this.view4 = view4;
        this.usuario = usuario;
        this.id = id;
    }
    /**
     * 
     * @param view5
     * @param usuario
     * @param id 
     */
     public ControllerMusica(MusicasCurtidasFrame view5,
            String usuario,int id) {
        this.view5 = view5;
        this.usuario = usuario;
        this.id = id;
    }
    /**
     * 
     * @param view6
     * @param usuario
     * @param id 
     */
    public ControllerMusica(MusicasDescurtidasFrame view6,
          String usuario,int id) {
      this.view6 = view6;
      this.usuario = usuario;
      this.id = id;
  }
    /**
     * realiza a busca de músicas com base nos campos preenchidos na tela
     * obs: é necessario apenas um campo preenchido + utilizado na tela principal
     * de buscar musicas
     * 
     * obs2: esse metodo n tem return, porem ele abre a tela de resultados de
     * musicas utilizando o construtor da mesma com os parametros:
     * id usuario
     * username (usuario)
     * ArrayList de Musicas encontradas
     */
    
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
    
    /**
     * utilizado na tela de adicionar musicas na playlist
     * @return arraylist com tais informacoes fornecidas
     * que possui apenas o nome das musicas, sem outras infos
     */
    
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
    
    
    /** 
     * realizacao a interacao de curtir musica e mostrar um pop-up conforme o res
     * @param musicaId ID da música a ser curtida.
     */
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
    /** 
     * realizacao a interacao de descurtir musica e mostrar um pop-up conforme o res
     * @param musicaId ID da música a ser descurtida.
     */
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

     /**
     * retorna a lista com as últimas 10 músicas buscadas pelo usuario.
     * 
     * @return arraylist com as ultimas 10 musicas buscadas pelo user.
     */
    
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
    
    /**
     * retorna a lista com as musicas curtidas pelo usuario.
     * 
     * @return arraylist com as musicas curtidas pelo user.
     */
    
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
    
     /**
     * retorna a lista com as musicas descurtidas pelo usuario.
     * 
     * @return arraylist com as musicas descurtidas pelo user.
     */
    
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


