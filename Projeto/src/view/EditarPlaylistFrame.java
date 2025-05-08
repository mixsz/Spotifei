/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import controller.ControllerPlaylist;
import java.awt.Component;
import java.awt.Font;
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
    
    private void mostrarPlaylists() {
    telaMostrar.removeAll();
    telaMostrar2.removeAll(); 
    telaMostrar.setLayout(new BoxLayout(telaMostrar, BoxLayout.Y_AXIS));
    telaMostrar2.setLayout(new BoxLayout(telaMostrar2, BoxLayout.Y_AXIS));

    // Aqui você pode determinar o número máximo de playlists a exibir, como no caso das músicas
    int limite = Math.min(playlists.size(), 10);

    for (int i = 0; i < limite; i++) {
        Playlist p = playlists.get(i);

        JPanel playlistPanel = new JPanel();
        playlistPanel.setLayout(new BoxLayout(playlistPanel, BoxLayout.X_AXIS)); 
        playlistPanel.setBackground(new java.awt.Color(144, 238, 144));

        // Borda com tamanhos fixos e cores fixas
        playlistPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new java.awt.Color(60, 179, 113), 1),
            BorderFactory.createEmptyBorder(3, 15, 10, 15)
        ));

        // Painel interno que possui as informações da playlist
        JPanel painelEsquerdo = new JPanel();
        painelEsquerdo.setLayout(new BoxLayout(painelEsquerdo, BoxLayout.Y_AXIS)); 
        painelEsquerdo.setBackground(new java.awt.Color(144, 238, 144));

        // Nome da Playlist possui uma fonte maior
        JLabel lblNomePlaylist = new JLabel(p.getNome());
        lblNomePlaylist.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 20));
        lblNomePlaylist.setAlignmentX(Component.LEFT_ALIGNMENT);
        painelEsquerdo.add(lblNomePlaylist);

        // Exibir as músicas da playlist
        ArrayList<Musica> musicasPlaylist = p.getMusicas();
        for (Musica musica : musicasPlaylist) {
            JLabel lblMusica = new JLabel("<html><b>•</b> " + musica.getNome() + "</html>");
            lblMusica.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 15));
            lblMusica.setAlignmentX(Component.LEFT_ALIGNMENT);
            painelEsquerdo.add(lblMusica);
        }

        // Painel para os botões Renomear, Adicionar, Remover
        JPanel painelDireito = new JPanel();
        painelDireito.setLayout(new BoxLayout(painelDireito, BoxLayout.Y_AXIS));
        painelDireito.setBackground(new java.awt.Color(144, 238, 144));

        // Botão Renomear
        JButton btnRenomear = new JButton("Renomear");
        btnRenomear.setBackground(new java.awt.Color(51,51,51));  
        btnRenomear.setForeground(new java.awt.Color(0,153,0));
        btnRenomear.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnRenomear.setPreferredSize(new java.awt.Dimension(100, 25)); 
        btnRenomear.setMaximumSize(new java.awt.Dimension(100, 25));

        // Botão Adicionar Música
        JButton btnAdicionar = new JButton("Adicionar");
        btnAdicionar.setBackground(new java.awt.Color(51,51,51));  
        btnAdicionar.setForeground(new java.awt.Color(0,153,255));
        btnAdicionar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnAdicionar.setPreferredSize(new java.awt.Dimension(100, 25)); 
        btnAdicionar.setMaximumSize(new java.awt.Dimension(100, 25)); 

        // Botão Remover
        JButton btnRemover = new JButton("Remover");
        btnRemover.setBackground(new java.awt.Color(51,51,51));  
        btnRemover.setForeground(new java.awt.Color(255, 0, 0));
        btnRemover.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnRemover.setPreferredSize(new java.awt.Dimension(100, 25)); 
        btnRemover.setMaximumSize(new java.awt.Dimension(100, 25)); 

        // Adicionando os botões ao painel
        painelDireito.add(btnRenomear);
        painelDireito.add(Box.createVerticalStrut(8)); // Espaço entre botões
        painelDireito.add(btnAdicionar);
        painelDireito.add(Box.createVerticalStrut(8)); // Espaço entre botões
        painelDireito.add(btnRemover);

        // Painel Direita: Botões ---- Painel Esquerdo: Informações
        playlistPanel.add(painelEsquerdo);
        playlistPanel.add(painelDireito);

        // Tamanhos fixos de painéis
        playlistPanel.setPreferredSize(new java.awt.Dimension(500, 200));
        playlistPanel.setMaximumSize(new java.awt.Dimension(500, 200));

        // Adiciona as playlists na tela com base no índice
        if (i < 5) {
            telaMostrar.add(playlistPanel);
            telaMostrar.add(Box.createVerticalStrut(8)); // Gap entre objetos
        } else {
            playlistPanel.setPreferredSize(new java.awt.Dimension(500, 199)); 
            playlistPanel.setMaximumSize(new java.awt.Dimension(500, 199));
            telaMostrar2.add(playlistPanel);
            telaMostrar2.add(Box.createVerticalStrut(8)); // Gap entre objetos 
        }
    }

    // Comandos que garantem a exibição na tela
    telaMostrar.revalidate();
    telaMostrar.repaint();
    telaMostrar2.revalidate();
    telaMostrar2.repaint();
}
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
