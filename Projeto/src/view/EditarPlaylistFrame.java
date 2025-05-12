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

    public EditarPlaylistFrame(String username, int id) {
        initComponents();
        this.usuario = username;
        this.id = id; 
        this.controller = new ControllerPlaylist(this.usuario,this.id,this);
        this.playlists = controller.getPlaylists(id);
//        exibirPlaylists(); // ver se funciona
        mostrarPlaylists();
    }
    
    private void exibirPlaylists() { // pra testar
        for (Playlist playlist : playlists) {
            System.out.println("Playlist: " + playlist.getNome());
            for (Musica musica : playlist.getMusicas()) {
                System.out.println("  - " + musica.getNome());
            }
        }
    }
    private void mostrarPlaylists() {
        telaMostrar.removeAll();
        telaMostrar2.removeAll(); 
        telaMostrar.setLayout(new BoxLayout(telaMostrar, BoxLayout.Y_AXIS));
        telaMostrar2.setLayout(new BoxLayout(telaMostrar2, BoxLayout.Y_AXIS));

        if (playlists.isEmpty()) {
            // SE N TIVER PLAYLISTS MOSTRA ISSO:
            JLabel lblSemPlaylists = new JLabel("Ops! Nenhuma playlist por aqui!");
            lblSemPlaylists.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 50));
            telaMostrar.add(lblSemPlaylists);
            telaMostrar.revalidate();
            telaMostrar.repaint();
            return;
        }

        int limite = Math.min(playlists.size(), 6);  // 3 PLAYLISTS POR COLUNA

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

            // TITULO
            JLabel lblNome = new JLabel(p.getNome());
            lblNome.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 20));
            colEsquerda.add(lblNome);

            // GAP DO TITULO E MUSICAS
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
                // SE N TIVER MUSICA NA PLAYLIST, APARECE ESSA MENSAGEM
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

            // BTN RENOMEAR
            JButton btnRenomear = new JButton("Renomear");
            btnRenomear.setBackground(new java.awt.Color(204, 102, 0));
            btnRenomear.setForeground(new java.awt.Color(51, 51, 51));
            btnRenomear.setAlignmentX(Component.CENTER_ALIGNMENT);
            btnRenomear.setPreferredSize(new java.awt.Dimension(95, 25));
            btnRenomear.setMaximumSize(new java.awt.Dimension(95, 25)); 
            
            btnRenomear.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    
                    int idPlaylist = p.getId(); 
                    String nomePlaylist = p.getNome(); 

                    RenomearPlaylistFrame renomearFrame = 
                            new RenomearPlaylistFrame(usuario, id, 
                                                    idPlaylist,nomePlaylist);
                    dispose();
                    renomearFrame.setLocationRelativeTo(null);

                    renomearFrame.setVisible(true);
                    
                }
            });
            
            colDireita.add(Box.createVerticalStrut(0)); 
            colDireita.add(btnRenomear);

            colDireita.add(Box.createVerticalStrut(15));

            // BTN ADICIONAR
            JButton btnAdicionar = new JButton("Adicionar");
            btnAdicionar.setBackground(new java.awt.Color(73, 151, 51)); 
            btnAdicionar.setForeground(new java.awt.Color(51, 51, 51));
            btnAdicionar.setAlignmentX(Component.CENTER_ALIGNMENT);
            btnAdicionar.setPreferredSize(new java.awt.Dimension(95, 25)); 
            btnAdicionar.setMaximumSize(new java.awt.Dimension(95, 25));
            btnAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    int idPlaylist = p.getId();
                    String nomePlaylist = p.getNome();
                    
                    AdicionarMusicaFrame adicionarMusicaFrame =
                        new AdicionarMusicaFrame(usuario, id, idPlaylist,nomePlaylist);

                    dispose(); // fecha a tela atual, se quiser
                    adicionarMusicaFrame.setLocationRelativeTo(null);
                    adicionarMusicaFrame.setVisible(true);
                }
            });
            colDireita.add(Box.createVerticalStrut(70));  
            colDireita.add(btnAdicionar);

            // BTN REMOVER
            JButton btnRemover = new JButton("Remover");
            btnRemover.setBackground(new java.awt.Color(153, 51, 0)); 
            btnRemover.setForeground(new java.awt.Color(51, 51, 51));
            btnRemover.setAlignmentX(Component.CENTER_ALIGNMENT);
            btnRemover.setPreferredSize(new java.awt.Dimension(95, 25));
            btnRemover.setMaximumSize(new java.awt.Dimension(95, 25));
            btnRemover.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    int idPlaylist = p.getId();
                    String nomePlaylist = p.getNome();
                    
                    ExcluirMusicaFrame removerMusicaFrame =
                        new ExcluirMusicaFrame(usuario, id, idPlaylist,nomePlaylist);

                    dispose(); // fecha a tela atual, se quiser
                    removerMusicaFrame.setLocationRelativeTo(null);
                    removerMusicaFrame.setVisible(true);
                }
            });
            colDireita.add(Box.createVerticalStrut(10)); 
            colDireita.add(btnRemover);

            // ADICIONA NA PLAYLIST 
            playlistPanel.add(colEsquerda);
            playlistPanel.add(colDireita);

            // TAMANHO AJUSTADO
            playlistPanel.setPreferredSize(new java.awt.Dimension(370, 200));
            playlistPanel.setMaximumSize(new java.awt.Dimension(370, 200));

            // DEPENDENDO DO INDICE DA PLAYLIST, VAI PRA COLUNA X
            if (i < 3) {
                telaMostrar.add(playlistPanel);
                telaMostrar.add(Box.createVerticalStrut(10)); 
            } else {
                telaMostrar2.add(playlistPanel);
                telaMostrar2.add(Box.createVerticalStrut(10));
            }
        }

        telaMostrar.revalidate();
        telaMostrar.repaint();
        telaMostrar2.revalidate();
        telaMostrar2.repaint();
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
                .addGap(333, 333, 333)
                .addComponent(titulo4)
                .addContainerGap(332, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(titulo4)
                .addContainerGap(26, Short.MAX_VALUE))
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
            .addGap(0, 688, Short.MAX_VALUE)
        );

        telaMostrar2.setBackground(new java.awt.Color(144, 238, 144));

        javax.swing.GroupLayout telaMostrar2Layout = new javax.swing.GroupLayout(telaMostrar2);
        telaMostrar2.setLayout(telaMostrar2Layout);
        telaMostrar2Layout.setHorizontalGroup(
            telaMostrar2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        telaMostrar2Layout.setVerticalGroup(
            telaMostrar2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout painelLayout = new javax.swing.GroupLayout(painel);
        painel.setLayout(painelLayout);
        painelLayout.setHorizontalGroup(
            painelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(painelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelLayout.createSequentialGroup()
                        .addComponent(btnVoltar1, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(painelLayout.createSequentialGroup()
                        .addComponent(telaMostrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(telaMostrar2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        painelLayout.setVerticalGroup(
            painelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelLayout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(painelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(telaMostrar2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(telaMostrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(5, 5, 5)
                .addComponent(btnVoltar1)
                .addGap(21, 21, 21))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(painel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
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
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel painel;
    private javax.swing.JPanel telaMostrar;
    private javax.swing.JPanel telaMostrar2;
    private javax.swing.JLabel titulo4;
    // End of variables declaration//GEN-END:variables
}

