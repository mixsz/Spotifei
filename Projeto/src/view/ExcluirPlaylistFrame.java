/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import controller.ControllerPlaylist;
import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.JPanel;
import model.Musica;
import model.Playlist;

/**
 * Interface responsavel por mostrar todas as playlists do usuario, e dar a 
 * possibilidade da exclusao de cada playlist, atraves de um botao 'X'
 * @author Mixzq
 */
public class ExcluirPlaylistFrame extends javax.swing.JFrame {

    /**
     * Creates new form ExcluirPlaylistFrame
     */
    private String usuario;
    private int id;
    private ControllerPlaylist controller;
    private ArrayList<Playlist> playlists;
    /**
     * Alem de receber o username do usuario e o id, é instanciado um objeto
     * controller da classe ControllerPlaylist, que serve para pegar os metodos
     * da mesma classe.
     * No caso foi utilizado para pegar todas as playlists do usuario (linha 52)
     * @param username
     * @param id 
     */
    public ExcluirPlaylistFrame(String username, int id) {
        initComponents();
        this.usuario = username;
        this.id = id; 
        this.controller = new ControllerPlaylist(this.usuario,this.id,this);
        this.playlists = controller.getPlaylists(id);
        //exibirPlaylists();
        mostrarPlaylists(playlists);
    }
    /**
     * Print para TESTAR o retorno da playlist pelo terminal
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
     * O metodo que mostra todas as playlists, divididas por blocos (PlaylistPanel),
     * junto com o botao 'X' para excluir tal playlist
     * 
     * obs: é necessario o parametro 'playlists' para a atualizacao imediata da 
     * tela apos
     * uma exclusao (ele sera removido da tela apos o usuario confirmar)
     * @param playlists 
     */
    public void mostrarPlaylists(ArrayList<Playlist> playlists) {
        telaMostrar.removeAll();
        telaMostrar2.removeAll();
        telaMostrar3.removeAll(); 
        telaMostrar4.removeAll();

        telaMostrar.setLayout(new BoxLayout(telaMostrar, BoxLayout.Y_AXIS));
        telaMostrar2.setLayout(new BoxLayout(telaMostrar2, BoxLayout.Y_AXIS));
        telaMostrar3.setLayout(new BoxLayout(telaMostrar3, BoxLayout.Y_AXIS));
        telaMostrar4.setLayout(new BoxLayout(telaMostrar4, BoxLayout.Y_AXIS));

        if (playlists.isEmpty()) {
            excluirlbl.setText("");
            JLabel lblSemPlaylists = new JLabel("Ops! Nenhuma playlist por aqui!");
            lblSemPlaylists.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 50));
            telaMostrar.add(lblSemPlaylists);
            telaMostrar.revalidate();
            telaMostrar.repaint();
            return;
        }

        int limite = Math.min(playlists.size(), 12); // até 3 por coluna, até 12 total

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

            // BOTAO EXCLUIR
            JPanel colDireita = new JPanel(new BorderLayout());
            colDireita.setBackground(new java.awt.Color(144, 238, 144));

            // CANTOR INFERIOR DIREIT
            JPanel painelInferior = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            painelInferior.setBackground(new java.awt.Color(144, 238, 144));

            JButton btnX = new JButton("X");
            btnX.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14));
            btnX.setForeground(Color.WHITE);
            btnX.setBackground(new java.awt.Color(204, 0, 0));
            btnX.setFocusPainted(false);
            btnX.setPreferredSize(new java.awt.Dimension(45, 25));
            btnX.setMaximumSize(new java.awt.Dimension(45, 25));

            btnX.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    controller.deletarPlaylist(p.getId());
                }
            });

            painelInferior.add(btnX);
            colDireita.add(painelInferior, BorderLayout.SOUTH);

            playlistPanel.add(colEsquerda);
            playlistPanel.add(Box.createHorizontalStrut(10));
            playlistPanel.add(colDireita);

            playlistPanel.setPreferredSize(new java.awt.Dimension(370, 200));
            playlistPanel.setMaximumSize(new java.awt.Dimension(370, 200));

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


    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
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
        excluirlbl = new javax.swing.JLabel();
        telaMostrar4 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Excluir Playlist");

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
                .addGap(680, 680, 680)
                .addComponent(titulo4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addComponent(titulo4)
                .addGap(21, 21, 21))
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
            .addGap(0, 315, Short.MAX_VALUE)
        );
        telaMostrar3Layout.setVerticalGroup(
            telaMostrar3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 630, Short.MAX_VALUE)
        );

        excluirlbl.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        excluirlbl.setForeground(new java.awt.Color(51, 51, 51));
        excluirlbl.setText("Excluir Playlist");

        telaMostrar4.setBackground(new java.awt.Color(144, 238, 144));

        javax.swing.GroupLayout telaMostrar4Layout = new javax.swing.GroupLayout(telaMostrar4);
        telaMostrar4.setLayout(telaMostrar4Layout);
        telaMostrar4Layout.setHorizontalGroup(
            telaMostrar4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 344, Short.MAX_VALUE)
        );
        telaMostrar4Layout.setVerticalGroup(
            telaMostrar4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                        .addComponent(telaMostrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(telaMostrar2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(telaMostrar3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(telaMostrar4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(painelLayout.createSequentialGroup()
                        .addGroup(painelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnVoltar1, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(excluirlbl))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        painelLayout.setVerticalGroup(
            painelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelLayout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(excluirlbl)
                .addGap(12, 12, 12)
                .addGroup(painelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(telaMostrar3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(telaMostrar2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(telaMostrar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(telaMostrar4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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
                .addContainerGap()
                .addComponent(painel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    /**
     * Volta na pagina anterior, no caso Gerenciar Playlist
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
//            java.util.logging.Logger.getLogger(ExcluirPlaylistFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(ExcluirPlaylistFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(ExcluirPlaylistFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(ExcluirPlaylistFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new ExcluirPlaylistFrame().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnVoltar1;
    private javax.swing.JLabel excluirlbl;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel painel;
    private javax.swing.JPanel telaMostrar;
    private javax.swing.JPanel telaMostrar2;
    private javax.swing.JPanel telaMostrar3;
    private javax.swing.JPanel telaMostrar4;
    private javax.swing.JLabel titulo4;
    // End of variables declaration//GEN-END:variables
}
