/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Janela após login. Obrigatoriamente trabalha com os dados principais do usuario
 * (id e username do usuario), assim
 * como todas as proximas telas
 * @author Danilo
 */
public class HomeFrame extends javax.swing.JFrame {

    /**
     * Creates new form HomeFrame
     */
    private String usuario;
    private int id;
    /**
     * Recebe username e id do usuario como parametro, alem disso o lblUsername
     * é alterado para o username do usuario
     * @param username
     * @param id 
     */
    public HomeFrame(String username, int id) {
    initComponents();
    this.usuario = username;
    lblUsername.setText(usuario);
    this.id = id;    
    System.out.println("Usuário: "+usuario);
    System.out.println("Id: "+id+'\n');
    } // os prints sao utilizados para verificar se na ida e volta das telas
      // os dados sao mantidos ou perdidos!

    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblUsername = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        titulo1 = new javax.swing.JLabel();
        btnBuscarMusica = new javax.swing.JButton();
        btnGerenciarPlaylist = new javax.swing.JButton();
        btnVisualizarHistorico = new javax.swing.JButton();
        btnDesconectar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Home");

        jPanel1.setBackground(new java.awt.Color(144, 238, 144));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Leelawadee UI", 1, 36)); // NOI18N
        jLabel1.setText("Olá,");

        lblUsername.setFont(new java.awt.Font("Leelawadee UI", 1, 36)); // NOI18N
        lblUsername.setText("Usuario");

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));
        jPanel2.setForeground(new java.awt.Color(102, 102, 102));

        titulo1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        titulo1.setForeground(new java.awt.Color(73, 151, 51));
        titulo1.setText("Spotifei");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(333, 333, 333)
                .addComponent(titulo1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(titulo1)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        btnBuscarMusica.setBackground(new java.awt.Color(51, 51, 51));
        btnBuscarMusica.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnBuscarMusica.setForeground(new java.awt.Color(73, 151, 51));
        btnBuscarMusica.setText("Buscar música");
        btnBuscarMusica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarMusicaActionPerformed(evt);
            }
        });

        btnGerenciarPlaylist.setBackground(new java.awt.Color(51, 51, 51));
        btnGerenciarPlaylist.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnGerenciarPlaylist.setForeground(new java.awt.Color(73, 151, 51));
        btnGerenciarPlaylist.setText("Gerenciar playlist");
        btnGerenciarPlaylist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGerenciarPlaylistActionPerformed(evt);
            }
        });

        btnVisualizarHistorico.setBackground(new java.awt.Color(51, 51, 51));
        btnVisualizarHistorico.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnVisualizarHistorico.setForeground(new java.awt.Color(73, 151, 51));
        btnVisualizarHistorico.setText("Visualizar histórico");
        btnVisualizarHistorico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVisualizarHistoricoActionPerformed(evt);
            }
        });

        btnDesconectar.setBackground(new java.awt.Color(51, 51, 51));
        btnDesconectar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnDesconectar.setForeground(new java.awt.Color(153, 0, 0));
        btnDesconectar.setText("Desconectar");
        btnDesconectar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDesconectarActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Leelawadee UI", 1, 36)); // NOI18N
        jLabel2.setText("!");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(305, 305, 305)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblUsername)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btnDesconectar, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnVisualizarHistorico, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnGerenciarPlaylist, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnBuscarMusica, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(272, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUsername)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(21, 21, 21)
                .addComponent(btnBuscarMusica, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(btnGerenciarPlaylist, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(btnVisualizarHistorico, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(btnDesconectar, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(56, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 620, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    /**
     * Após o click no botao, o usuario é redirecionado para tela Buscar Musica
     * Levando o username e id 
     * @param evt 
     */
    private void btnBuscarMusicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarMusicaActionPerformed
         this.setVisible(false);
         BuscarMusicaFrame bm = new BuscarMusicaFrame(this.usuario,this.id); 
         bm.setLocationRelativeTo(null);
         bm.setVisible(true);
    }//GEN-LAST:event_btnBuscarMusicaActionPerformed
    /**
     * Após o click no botao, o usuario é redirecionado para tela Gerenciar 
     * Playlist
     * Levando o username e id 
     * @param evt 
     */
    private void btnGerenciarPlaylistActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGerenciarPlaylistActionPerformed
         this.setVisible(false);
         GerenciarPlaylistFrame gm = new GerenciarPlaylistFrame(this.usuario,this.id); 
         gm.setLocationRelativeTo(null);
         gm.setVisible(true);
    }//GEN-LAST:event_btnGerenciarPlaylistActionPerformed
     /**
     * Após o click no botao, o usuario é redirecionado para tela Visualizar
     * Historico
     * Levando o username e id 
     * @param evt 
     */
    private void btnVisualizarHistoricoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVisualizarHistoricoActionPerformed
         this.setVisible(false);
         VisualizarHistoricoFrame gm = new VisualizarHistoricoFrame(this.usuario,this.id); 
         gm.setLocationRelativeTo(null);
         gm.setVisible(true);
    }//GEN-LAST:event_btnVisualizarHistoricoActionPerformed
    /**
     * Se confirmado, o usuário é deslogado e volta para a tela de Login
     * @param evt 
     */
    private void btnDesconectarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDesconectarActionPerformed
        int confirmar = JOptionPane.showConfirmDialog(this, "Deseja realmente "
                                                    + "se desconectar?", "Confirmação",
                                                    JOptionPane.YES_NO_OPTION);   
        if (confirmar == JOptionPane.YES_OPTION) {
            this.setVisible(false);
            new LoginFrame().setVisible(true);
        }
    }//GEN-LAST:event_btnDesconectarActionPerformed

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
//            java.util.logging.Logger.getLogger(HomeFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(HomeFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(HomeFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(HomeFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new HomeFrame().setVisible(true);
//            }
//        });
//    }
    
    // GET e SET dos elementos
    
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public JButton getBtnBuscarMusica() {
        return btnBuscarMusica;
    }

    public void setBtnBuscarMusica(JButton btnBuscarMusica) {
        this.btnBuscarMusica = btnBuscarMusica;
    }

    public JButton getBtnDesconectar() {
        return btnDesconectar;
    }

    public void setBtnDesconectar(JButton btnDesconectar) {
        this.btnDesconectar = btnDesconectar;
    }

    public JButton getBtnGerenciarPlaylist() {
        return btnGerenciarPlaylist;
    }

    public void setBtnGerenciarPlaylist(JButton btnGerenciarPlaylist) {
        this.btnGerenciarPlaylist = btnGerenciarPlaylist;
    }

    public JButton getBtnVisualizarHistorico() {
        return btnVisualizarHistorico;
    }

    public void setBtnVisualizarHistorico(JButton btnVisualizarHistorico) {
        this.btnVisualizarHistorico = btnVisualizarHistorico;
    }

    public JLabel getjLabel1() {
        return jLabel1;
    }

    public void setjLabel1(JLabel jLabel1) {
        this.jLabel1 = jLabel1;
    }

    public JPanel getjPanel1() {
        return jPanel1;
    }

    public void setjPanel1(JPanel jPanel1) {
        this.jPanel1 = jPanel1;
    }

    public JPanel getjPanel2() {
        return jPanel2;
    }

    public void setjPanel2(JPanel jPanel2) {
        this.jPanel2 = jPanel2;
    }

    public JLabel getTitulo1() {
        return titulo1;
    }

    public void setTitulo1(JLabel titulo1) {
        this.titulo1 = titulo1;
    }

    public JLabel getLblUsername() {
        return lblUsername;
    }

    public void setLblUsername(JLabel lblUsername) {
        this.lblUsername = lblUsername;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscarMusica;
    private javax.swing.JButton btnDesconectar;
    private javax.swing.JButton btnGerenciarPlaylist;
    private javax.swing.JButton btnVisualizarHistorico;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JLabel titulo1;
    // End of variables declaration//GEN-END:variables
}
