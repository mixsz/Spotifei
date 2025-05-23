/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import controller.ControllerCadastro;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 * Ttela principal para o usuário se cadastrar
 * @author Danilo
 */
public class CadastroFrame extends javax.swing.JFrame {

    /**
     * construtor que apenas chama o metodo criado pela propria IDE
     */
    
    public CadastroFrame() {
        initComponents();
    }
    
    /**
     * o objeto foi instanciado fora do construtor, porem nao houve problemas
     * entao foi deixado assim
    */
    
    ControllerCadastro controller = new ControllerCadastro(this);
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sexo = new javax.swing.ButtonGroup();
        jPanel5 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        lblNome = new javax.swing.JLabel();
        lblCadastro = new javax.swing.JLabel();
        btnAcessar2 = new javax.swing.JButton();
        lblUsername = new javax.swing.JLabel();
        lblSenha = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        txtUsername = new javax.swing.JTextField();
        txtSenha = new javax.swing.JPasswordField();
        txtSenha1 = new javax.swing.JPasswordField();
        lblSenha1 = new javax.swing.JLabel();
        lblUsername1 = new javax.swing.JLabel();
        txtUsername1 = new javax.swing.JTextField();
        lblUsername2 = new javax.swing.JLabel();
        txtUsername2 = new javax.swing.JTextField();
        btnMasculino = new javax.swing.JRadioButton();
        btnFeminino = new javax.swing.JRadioButton();
        lblSenha2 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        title = new javax.swing.JLabel();
        btnVoltar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Cadastro");

        jPanel5.setBackground(new java.awt.Color(144, 238, 144));

        jPanel1.setBackground(new java.awt.Color(153, 255, 153));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblNome.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblNome.setForeground(new java.awt.Color(51, 51, 51));
        lblNome.setText("Nome:");

        lblCadastro.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblCadastro.setText("Cadastro");

        btnAcessar2.setBackground(new java.awt.Color(51, 51, 51));
        btnAcessar2.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        btnAcessar2.setForeground(new java.awt.Color(0, 153, 0));
        btnAcessar2.setText("Cadastrar");
        btnAcessar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAcessar2ActionPerformed(evt);
            }
        });

        lblUsername.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblUsername.setForeground(new java.awt.Color(51, 51, 51));
        lblUsername.setText("Username:");

        lblSenha.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblSenha.setForeground(new java.awt.Color(51, 51, 51));
        lblSenha.setText("Senha:");

        txtNome.setBackground(new java.awt.Color(204, 255, 204));
        txtNome.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomeActionPerformed(evt);
            }
        });

        txtUsername.setBackground(new java.awt.Color(204, 255, 204));
        txtUsername.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtUsername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsernameActionPerformed(evt);
            }
        });

        txtSenha.setBackground(new java.awt.Color(204, 255, 204));
        txtSenha.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSenhaActionPerformed(evt);
            }
        });

        txtSenha1.setBackground(new java.awt.Color(204, 255, 204));
        txtSenha1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtSenha1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSenha1ActionPerformed(evt);
            }
        });

        lblSenha1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblSenha1.setForeground(new java.awt.Color(51, 51, 51));
        lblSenha1.setText("Confirmar senha:");

        lblUsername1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblUsername1.setForeground(new java.awt.Color(51, 51, 51));
        lblUsername1.setText("Idade:");

        txtUsername1.setBackground(new java.awt.Color(204, 255, 204));
        txtUsername1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtUsername1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsername1ActionPerformed(evt);
            }
        });

        lblUsername2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblUsername2.setForeground(new java.awt.Color(51, 51, 51));
        lblUsername2.setText("Sobrenome:");

        txtUsername2.setBackground(new java.awt.Color(204, 255, 204));
        txtUsername2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtUsername2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsername2ActionPerformed(evt);
            }
        });

        btnMasculino.setBackground(new java.awt.Color(144, 238, 144));
        sexo.add(btnMasculino);
        btnMasculino.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnMasculino.setText("Masculino");
        btnMasculino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMasculinoActionPerformed(evt);
            }
        });

        btnFeminino.setBackground(new java.awt.Color(144, 238, 144));
        sexo.add(btnFeminino);
        btnFeminino.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnFeminino.setText("Feminino");
        btnFeminino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFemininoActionPerformed(evt);
            }
        });

        lblSenha2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblSenha2.setForeground(new java.awt.Color(51, 51, 51));
        lblSenha2.setText("Sexo:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(142, 142, 142)
                        .addComponent(lblCadastro))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(lblSenha2)
                                    .addGap(141, 141, 141))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtUsername, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtSenha, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblSenha, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblSenha1, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtSenha1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblNome)
                            .addComponent(txtUsername2, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblUsername2)
                            .addComponent(lblUsername)
                            .addComponent(lblUsername1)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnMasculino)
                                .addGap(28, 28, 28)
                                .addComponent(btnFeminino))
                            .addComponent(txtUsername1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(93, 93, 93)
                        .addComponent(btnAcessar2, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(106, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblCadastro)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNome)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblUsername2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtUsername2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblUsername)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(lblUsername1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtUsername1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSenha2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnMasculino)
                    .addComponent(btnFeminino))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSenha)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSenha1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSenha1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnAcessar2)
                .addGap(32, 32, 32))
        );

        jPanel6.setBackground(new java.awt.Color(51, 51, 51));

        title.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        title.setForeground(new java.awt.Color(73, 151, 51));
        title.setText("Spotifei");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(title)
                .addGap(312, 312, 312))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addComponent(title)
                .addGap(16, 16, 16))
        );

        btnVoltar.setBackground(new java.awt.Color(204, 204, 204));
        btnVoltar.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        btnVoltar.setText("Voltar");
        btnVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVoltarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(btnVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(98, 98, 98)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(182, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnVoltar))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 503, Short.MAX_VALUE))
                .addGap(16, 16, 16))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAcessar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAcessar2ActionPerformed
         controller.Cadastro();
    }//GEN-LAST:event_btnAcessar2ActionPerformed

    private void txtNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomeActionPerformed

    private void txtUsernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsernameActionPerformed
    /**
     * Volta na tela anterior após o click, nesse caso o LoginFrame
     * @param evt 
     */
    private void btnVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoltarActionPerformed
        this.setVisible(false);   
        LoginFrame loginFrame = new LoginFrame();
        loginFrame.setLocationRelativeTo(null);
        loginFrame.setVisible(true); 
    }//GEN-LAST:event_btnVoltarActionPerformed

    private void txtUsername1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsername1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsername1ActionPerformed

    private void txtUsername2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsername2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsername2ActionPerformed

    private void txtSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSenhaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSenhaActionPerformed

    private void txtSenha1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSenha1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSenha1ActionPerformed

    private void btnMasculinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMasculinoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnMasculinoActionPerformed

    private void btnFemininoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFemininoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnFemininoActionPerformed

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
//            java.util.logging.Logger.getLogger(CadastroFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(CadastroFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(CadastroFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(CadastroFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new CadastroFrame().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAcessar2;
    private javax.swing.JRadioButton btnFeminino;
    private javax.swing.JRadioButton btnMasculino;
    private javax.swing.JButton btnVoltar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JLabel lblCadastro;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblSenha;
    private javax.swing.JLabel lblSenha1;
    private javax.swing.JLabel lblSenha2;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JLabel lblUsername1;
    private javax.swing.JLabel lblUsername2;
    private javax.swing.ButtonGroup sexo;
    private javax.swing.JLabel title;
    private javax.swing.JTextField txtNome;
    private javax.swing.JPasswordField txtSenha;
    private javax.swing.JPasswordField txtSenha1;
    private javax.swing.JTextField txtUsername;
    private javax.swing.JTextField txtUsername1;
    private javax.swing.JTextField txtUsername2;
    // End of variables declaration//GEN-END:variables
    
    
    // GET e SET de todos os elementos
    
    public JButton getBtnAcessar2() {
        return btnAcessar2;
    }

    public void setBtnAcessar2(JButton btnAcessar2) {
        this.btnAcessar2 = btnAcessar2;
    }

    public JButton getBtnVoltar() {
        return btnVoltar;
    }

    public void setBtnVoltar(JButton btnVoltar) {
        this.btnVoltar = btnVoltar;
    }

    public JPanel getjPanel1() {
        return jPanel1;
    }

    public void setjPanel1(JPanel jPanel1) {
        this.jPanel1 = jPanel1;
    }

    public JLabel getLblCadastro() {
        return lblCadastro;
    }

    public void setLblCadastro(JLabel lblCadastro) {
        this.lblCadastro = lblCadastro;
    }

    public JLabel getLblNome() {
        return lblNome;
    }

    public void setLblNome(JLabel lblNome) {
        this.lblNome = lblNome;
    }

    public JLabel getLblSenha() {
        return lblSenha;
    }

    public void setLblSenha(JLabel lblSenha) {
        this.lblSenha = lblSenha;
    }

    public JLabel getLblSenha1() {
        return lblSenha1;
    }

    public void setLblSenha1(JLabel lblSenha1) {
        this.lblSenha1 = lblSenha1;
    }

    public JLabel getLblUsername() {
        return lblUsername;
    }

    public void setLblUsername(JLabel lblUsername) {
        this.lblUsername = lblUsername;
    }

    public JLabel getLblUsername1() {
        return lblUsername1;
    }

    public void setLblUsername1(JLabel lblUsername1) {
        this.lblUsername1 = lblUsername1;
    }

    public JLabel getLblUsername2() {
        return lblUsername2;
    }

    public void setLblUsername2(JLabel lblUsername2) {
        this.lblUsername2 = lblUsername2;
    }

    public void setTitle(JLabel title) {
        this.title = title;
    }

    public JTextField getTxtNome() {
        return txtNome;
    }

    public void setTxtNome(JTextField txtNome) {
        this.txtNome = txtNome;
    }

    public JPasswordField getTxtSenha() {
        return txtSenha;
    }

    public void setTxtSenha(JPasswordField txtSenha) {
        this.txtSenha = txtSenha;
    }

    public JPasswordField getTxtSenha1() {
        return txtSenha1;
    }

    public void setTxtSenha1(JPasswordField txtSenha1) {
        this.txtSenha1 = txtSenha1;
    }

    public JTextField getTxtUsername() {
        return txtUsername;
    }

    public void setTxtUsername(JTextField txtUsername) {
        this.txtUsername = txtUsername;
    }

    public JTextField getTxtUsername1() {
        return txtUsername1;
    }

    public void setTxtUsername1(JTextField txtUsername1) {
        this.txtUsername1 = txtUsername1;
    }

    public JTextField getTxtUsername2() {
        return txtUsername2;
    }

    public void setTxtUsername2(JTextField txtUsername2) {
        this.txtUsername2 = txtUsername2;
    }

    public ControllerCadastro getController() {
        return controller;
    }

    public void setController(ControllerCadastro controller) {
        this.controller = controller;
    }

    public JRadioButton getBtnFeminino() {
        return btnFeminino;
    }

    public void setBtnFeminino(JRadioButton btnFeminino) {
        this.btnFeminino = btnFeminino;
    }

    public JRadioButton getBtnMasculino() {
        return btnMasculino;
    }

    public void setBtnMasculino(JRadioButton btnMasculino) {
        this.btnMasculino = btnMasculino;
    }

    public JLabel getLblSenha2() {
        return lblSenha2;
    }

    public void setLblSenha2(JLabel lblSenha2) {
        this.lblSenha2 = lblSenha2;
    }

    public ButtonGroup getSexo() {
        return sexo;
    }

    public void setSexo(ButtonGroup sexo) {
        this.sexo = sexo;
    }
    
}
