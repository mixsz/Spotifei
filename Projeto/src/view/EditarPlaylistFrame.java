/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import DAO.PlaylistDAO;
import controller.ControllerPlaylist;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import model.Musica;
import model.Playlist;
import java.sql.SQLException;

/**
 * Uma das janelas mais importantes, aqui é mostrado todas as playlists criada
 * pelo usuario logado (separadas por bloco)
 * .
 * Junto com elas, existem 3 botões clickaveis em cada bloco de playlist: 
 * renomear,
 * adicionar,
 * excluir
 * 
 * @author Dan
 */

public class EditarPlaylistFrame extends javax.swing.JFrame {

    /**
     * Creates new form EditarPlaylistFrame
     */
    
    private String usuario;
    private int id;
    private ControllerPlaylist controller;
    private ArrayList<Playlist> playlists;
    /**
     * Alem da passagem de username(usuario) e id do usuario, é instanciado
     * um objeto controller da classe ControllerPlaylist utilizado para chamar
     * metodos especificos.
     * 
     * Alem disso, é criado um arrayList de playlists que recebe todas as 
     * playlists do usuario logado (atraves do metodo getPlaylists
     * 
     * @param username
     * @param id 
     */
    public EditarPlaylistFrame(String username, int id) {
        initComponents();
        this.usuario = username;
        this.id = id; 
        this.controller = new ControllerPlaylist(this.usuario,this.id,this);
        this.playlists = controller.getPlaylists(id);
//      exibirPlaylists(); // ver se funciona
        mostrarPlaylists();
    }
    
    
    /**
     * Método para testar se a playlist estavam sendo retornada corretamente
     */
   
    private void exibirPlaylists() { 
        for (Playlist playlist : playlists) {
            System.out.println("Playlist: " + playlist.getNome());
            for (Musica musica : playlist.getMusicas()) {
                System.out.println("  - " + musica.getNome());
            }
        }
    }
    /**
     * É mostrado, separado por blocos, todas as playlists do usuario (com as 
     * primeiras 7 musicas de cada playlist), cada bloco possui os botoes 
     * "renomear", "adicionar" e "remover"
     * 
     * Se nao tiver playlists, mostra uma mensagem (linha 102)
     */
    
    private void mostrarPlaylists() {
        telaMostrar.removeAll();
        telaMostrar2.removeAll();
        telaMostrar3.removeAll(); 
        telaMostrar4.removeAll();

        telaMostrar.setLayout(new BoxLayout(telaMostrar, BoxLayout.Y_AXIS));
        telaMostrar2.setLayout(new BoxLayout(telaMostrar2, BoxLayout.Y_AXIS));
        telaMostrar3.setLayout(new BoxLayout(telaMostrar3, BoxLayout.Y_AXIS));
        telaMostrar4.setLayout(new BoxLayout(telaMostrar4, BoxLayout.Y_AXIS));

        if (playlists.isEmpty()) {
            JLabel lblSemPlaylists = new JLabel("Ops! Nenhuma playlist por aqui!");
            lblSemPlaylists.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 50));
            telaMostrar.add(lblSemPlaylists);
            telaMostrar.revalidate();
            telaMostrar.repaint();
            return;
        }

        int limite = Math.min(playlists.size(), 12); // 3  por coluna, até 12 total

        for (int i = 0; i < limite; i++) {
            Playlist p = playlists.get(i);

            JPanel playlistPanel = new JPanel();
            playlistPanel.setLayout(new BoxLayout(playlistPanel, BoxLayout.X_AXIS));
            playlistPanel.setBackground(new java.awt.Color(144, 238, 144));
            playlistPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new java.awt.Color(60, 179, 113), 1),
                BorderFactory.createEmptyBorder(5, 15, 10, 15)
            ));

            // COLUNA ESQUERDA
            JPanel colEsquerda = new JPanel();
            colEsquerda.setLayout(new BoxLayout(colEsquerda, BoxLayout.Y_AXIS));
            colEsquerda.setBackground(new java.awt.Color(144, 238, 144));
            colEsquerda.setAlignmentX(Component.LEFT_ALIGNMENT);

            JLabel lblNome = new JLabel(p.getNome());
            lblNome.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 20));
            colEsquerda.add(lblNome);
            colEsquerda.add(Box.createVerticalStrut(5));

            Font fonte = new Font("Segoe UI Semibold", Font.PLAIN, 15);
            if (!p.getMusicas().isEmpty()) {
                ArrayList<Musica> musicasDaPlaylist = p.getMusicas();
                int maxMusicas = 6;

                for (int j = 0; j < Math.min(musicasDaPlaylist.size(), maxMusicas); j++) {
                    JLabel lblMusica = new JLabel("- " + musicasDaPlaylist.get(j).getNome());
                    lblMusica.setFont(fonte);
                    colEsquerda.add(lblMusica);
                }
                if (musicasDaPlaylist.size() > maxMusicas) {
                    JLabel lblMais = new JLabel("  Mais...");
                    Font f = new Font("Segoe UI Bold", Font.BOLD, 40);
                    lblMais.setForeground(new java.awt.Color(51, 51, 51));
                    colEsquerda.add(lblMais);
                }
            } else {
                JLabel lblVazia = new JLabel("Sem músicas por aqui!");
                lblVazia.setFont(fonte);
                colEsquerda.add(lblVazia);
                colEsquerda.add(Box.createVerticalGlue());
            }

            // COL DIREITA
            JPanel colDireita = new JPanel();
            colDireita.setLayout(new BoxLayout(colDireita, BoxLayout.Y_AXIS));
            colDireita.setBackground(new java.awt.Color(144, 238, 144));
            colDireita.setAlignmentX(Component.LEFT_ALIGNMENT);

            JButton btnRenomear = new JButton("Renomear");
            btnRenomear.setBackground(new java.awt.Color(204, 102, 0));
            btnRenomear.setForeground(new java.awt.Color(51, 51, 51));
            btnRenomear.setAlignmentX(Component.CENTER_ALIGNMENT);
            btnRenomear.setPreferredSize(new java.awt.Dimension(95, 25));
            btnRenomear.setMaximumSize(new java.awt.Dimension(95, 25));
            btnRenomear.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    int idPlaylist = p.getId();
                    String nomePlaylist = p.getNome();
                    RenomearPlaylistFrame renomearFrame =
                            new RenomearPlaylistFrame(usuario, id, idPlaylist, nomePlaylist);
                    dispose();
                    renomearFrame.setLocationRelativeTo(null);
                    renomearFrame.setVisible(true);
                }
            });

            colDireita.add(Box.createVerticalStrut(0));
            colDireita.add(btnRenomear);
            colDireita.add(Box.createVerticalStrut(15));

            JButton btnAdicionar = new JButton("Adicionar");
            btnAdicionar.setBackground(new java.awt.Color(73, 151, 51));
            btnAdicionar.setForeground(new java.awt.Color(51, 51, 51));
            btnAdicionar.setAlignmentX(Component.CENTER_ALIGNMENT);
            btnAdicionar.setPreferredSize(new java.awt.Dimension(95, 25));
            btnAdicionar.setMaximumSize(new java.awt.Dimension(95, 25));
            btnAdicionar.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    int idPlaylist = p.getId();
                    String nomePlaylist = p.getNome();
                    AdicionarMusicaFrame adicionarMusicaFrame =
                            new AdicionarMusicaFrame(usuario, id, idPlaylist, nomePlaylist);
                    dispose();
                    adicionarMusicaFrame.setLocationRelativeTo(null);
                    adicionarMusicaFrame.setVisible(true);
                }
            });
            colDireita.add(Box.createVerticalStrut(70));
            colDireita.add(btnAdicionar);

            JButton btnRemover = new JButton("Remover");
            btnRemover.setBackground(new java.awt.Color(153, 51, 0));
            btnRemover.setForeground(new java.awt.Color(51, 51, 51));
            btnRemover.setAlignmentX(Component.CENTER_ALIGNMENT);
            btnRemover.setPreferredSize(new java.awt.Dimension(95, 25));
            btnRemover.setMaximumSize(new java.awt.Dimension(95, 25));
            btnRemover.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    int idPlaylist = p.getId();
                    String nomePlaylist = p.getNome();
                    ExcluirMusicaFrame removerMusicaFrame =
                            new ExcluirMusicaFrame(usuario, id, idPlaylist, nomePlaylist);
                    dispose();
                    removerMusicaFrame.setLocationRelativeTo(null);
                    removerMusicaFrame.setVisible(true);
                }
            });
            colDireita.add(Box.createVerticalStrut(10));
            colDireita.add(btnRemover);

            playlistPanel.add(colEsquerda);
            playlistPanel.add(colDireita);

            playlistPanel.setPreferredSize(new java.awt.Dimension(370, 200));
            playlistPanel.setMaximumSize(new java.awt.Dimension(370, 200));

            // Agora com 4 colunas
            if (i < 3) {
                telaMostrar.add(playlistPanel);
                telaMostrar.add(Box.createVerticalStrut(10));
            } else if (i < 6) {
                telaMostrar2.add(playlistPanel);
                telaMostrar2.add(Box.createVerticalStrut(10));
            } else if (i < 9) {
                telaMostrar3.add(playlistPanel);
                telaMostrar3.add(Box.createVerticalStrut(10));
            } else {
                telaMostrar4.add(playlistPanel);
                telaMostrar4.add(Box.createVerticalStrut(10));
            }
        }

        telaMostrar.revalidate();
        telaMostrar.repaint();
        telaMostrar2.revalidate();
        telaMostrar2.repaint();
        telaMostrar3.revalidate();
        telaMostrar3.repaint();
        telaMostrar4.revalidate();
        telaMostrar4.repaint();
    }



    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        painel = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        titulo4 = new javax.swing.JLabel();
        btnVoltar1 = new javax.swing.JButton();
        telaMostrar = new javax.swing.JPanel();
        telaMostrar2 = new javax.swing.JPanel();
        telaMostrar3 = new javax.swing.JPanel();
        telaMostrar4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Editar Playlist");

        painel.setBackground(new java.awt.Color(144, 238, 144));
        painel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel8.setBackground(new java.awt.Color(51, 51, 51));
        jPanel8.setForeground(new java.awt.Color(102, 102, 102));

        titulo4.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        titulo4.setForeground(new java.awt.Color(73, 151, 51));
        titulo4.setText("Spotifei");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(683, 683, 683)
                .addComponent(titulo4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addComponent(titulo4)
                .addGap(24, 24, 24))
        );

        btnVoltar1.setBackground(new java.awt.Color(204, 204, 204));
        btnVoltar1.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        btnVoltar1.setText("Voltar");
        btnVoltar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVoltar1ActionPerformed(evt);
            }
        });

        telaMostrar.setBackground(new java.awt.Color(144, 238, 144));

        javax.swing.GroupLayout telaMostrarLayout = new javax.swing.GroupLayout(telaMostrar);
        telaMostrar.setLayout(telaMostrarLayout);
        telaMostrarLayout.setHorizontalGroup(
            telaMostrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 401, Short.MAX_VALUE)
        );
        telaMostrarLayout.setVerticalGroup(
            telaMostrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        telaMostrar2.setBackground(new java.awt.Color(144, 238, 144));

        javax.swing.GroupLayout telaMostrar2Layout = new javax.swing.GroupLayout(telaMostrar2);
        telaMostrar2.setLayout(telaMostrar2Layout);
        telaMostrar2Layout.setHorizontalGroup(
            telaMostrar2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 401, Short.MAX_VALUE)
        );
        telaMostrar2Layout.setVerticalGroup(
            telaMostrar2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        telaMostrar3.setBackground(new java.awt.Color(144, 238, 144));

        javax.swing.GroupLayout telaMostrar3Layout = new javax.swing.GroupLayout(telaMostrar3);
        telaMostrar3.setLayout(telaMostrar3Layout);
        telaMostrar3Layout.setHorizontalGroup(
            telaMostrar3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 266, Short.MAX_VALUE)
        );
        telaMostrar3Layout.setVerticalGroup(
            telaMostrar3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        telaMostrar4.setBackground(new java.awt.Color(144, 238, 144));

        javax.swing.GroupLayout telaMostrar4Layout = new javax.swing.GroupLayout(telaMostrar4);
        telaMostrar4.setLayout(telaMostrar4Layout);
        telaMostrar4Layout.setHorizontalGroup(
            telaMostrar4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 395, Short.MAX_VALUE)
        );
        telaMostrar4Layout.setVerticalGroup(
            telaMostrar4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 640, Short.MAX_VALUE)
        );

        jLabel1.setBackground(new java.awt.Color(51, 51, 51));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel1.setText("Editar Playlist");

        javax.swing.GroupLayout painelLayout = new javax.swing.GroupLayout(painel);
        painel.setLayout(painelLayout);
        painelLayout.setHorizontalGroup(
            painelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(painelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelLayout.createSequentialGroup()
                        .addGroup(painelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnVoltar1, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(painelLayout.createSequentialGroup()
                                .addComponent(telaMostrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(telaMostrar2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(telaMostrar3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(telaMostrar4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(12, 12, 12))
                    .addGroup(painelLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        painelLayout.setVerticalGroup(
            painelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelLayout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addGap(12, 12, 12)
                .addGroup(painelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(telaMostrar4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(telaMostrar3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(telaMostrar2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(telaMostrar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnVoltar1)
                .addGap(14, 14, 14))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(painel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(painel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    /**
     * Volta na janela anterior, nesse caso Gerenciar Playlist
     * @param evt 
     */
    private void btnVoltar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoltar1ActionPerformed
        this.setVisible(false);
        GerenciarPlaylistFrame hm = new GerenciarPlaylistFrame(usuario,id);
        hm.setLocationRelativeTo(null);
        hm.setVisible(true);
    }//GEN-LAST:event_btnVoltar1ActionPerformed
    
    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(EditarPlaylistFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(EditarPlaylistFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(EditarPlaylistFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(EditarPlaylistFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new EditarPlaylistFrame().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnVoltar1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel painel;
    private javax.swing.JPanel telaMostrar;
    private javax.swing.JPanel telaMostrar2;
    private javax.swing.JPanel telaMostrar3;
    private javax.swing.JPanel telaMostrar4;
    private javax.swing.JLabel titulo4;
    // End of variables declaration//GEN-END:variables
}

