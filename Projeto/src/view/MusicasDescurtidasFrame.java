/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import controller.ControllerMusica;
import java.awt.Component;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.Musica;

/**
 * Nessa tela, o usuário é capaz de ver todas as suas músicas descurtidas
 * @author Mixzq
 */
public class MusicasDescurtidasFrame extends javax.swing.JFrame {

    /**
     * Creates new form MusicasDescurtidasFrame
     */
    private String usuario;
    private int id;
    private ControllerMusica controller;
    private ArrayList<Musica> musicas;
     /**
     * é necessário o id do usuario e o username (usuario), um objeto
     * 'controller' é instanciado dentro do construtor para ser utilizado o
     * metodo de buscar musicas descurtidas (que esta dentro do ControllerMusica),
     * que serve para retornar um arrayList com as musicas descurtidas do proprio
     * user
     * 
     * @param usuario
     * @param id 
     */
    public MusicasDescurtidasFrame(String usuario, int id) {
        initComponents();
        this.usuario = usuario;
        this.id = id;
        this.controller = new ControllerMusica(this,this.usuario,this.id);
        ArrayList<Musica> musicas = controller.buscarMusicasDescurtidas();
        //mostraMusicasPRINT();
        mostrarMusicasDescurtidas(musicas);
    }   
    /**
     * Mostra o arrayList retornado apos chamar o metodo de buscar musicas 
     * descurtidas (via terminal)
     * Metodo de teste
     */
    public void mostraMusicasPRINT(){
       ArrayList<Musica> musicas = controller.buscarMusicasCurtidas();
       for(Musica m : musicas){
           System.out.println(m.getNome());
       }
    }  
    /**
     * Esse metodo é responsavel por mostrar todas as musicas descurtidas do 
     * usuario
     * na tela. 
     * É dividido por 4 colunas capazes de armazenar 7 musicas (total = 28)
     * @param curtidas 
     */
    private void mostrarMusicasDescurtidas(ArrayList<Musica> descurtidas) {
        telaMostrar.removeAll();
        telaMostrar2.removeAll();
        telaMostrar3.removeAll();
        telaMostrar4.removeAll();  

        telaMostrar.setLayout(new BoxLayout(telaMostrar, BoxLayout.Y_AXIS));
        telaMostrar2.setLayout(new BoxLayout(telaMostrar2, BoxLayout.Y_AXIS));
        telaMostrar3.setLayout(new BoxLayout(telaMostrar3, BoxLayout.Y_AXIS));
        telaMostrar4.setLayout(new BoxLayout(telaMostrar4, BoxLayout.Y_AXIS)); 

        telaMostrar.setAlignmentY(Component.TOP_ALIGNMENT);
        telaMostrar2.setAlignmentY(Component.TOP_ALIGNMENT);
        telaMostrar3.setAlignmentY(Component.TOP_ALIGNMENT);
        telaMostrar4.setAlignmentY(Component.TOP_ALIGNMENT);

        if (descurtidas.isEmpty()) {
            titulo5.setText("");
            JLabel lblSemDescurtidas = new JLabel("                                   "
                    + "                     Ops! Nenhuma música descurtida por aqui!");
            lblSemDescurtidas.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 28));
            lblSemDescurtidas.setAlignmentX(Component.CENTER_ALIGNMENT);
            telaMostrar.add(Box.createVerticalGlue());
            telaMostrar.add(lblSemDescurtidas);
            telaMostrar.add(Box.createVerticalGlue());
            telaMostrar.revalidate();
            telaMostrar.repaint();
            return;
        }

        int limite = Math.min(descurtidas.size(), 28); // máximo de 28 músicas

        for (int i = 0; i < limite; i++) {
            Musica m = descurtidas.get(i);

            JPanel musicaPanel = new JPanel();
            musicaPanel.setLayout(new BoxLayout(musicaPanel, BoxLayout.X_AXIS));
            musicaPanel.setBackground(new java.awt.Color(144, 238, 144));
            musicaPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new java.awt.Color(60, 179, 113), 1),
                BorderFactory.createEmptyBorder(3, 15, 10, 15)
            ));

            JPanel painelEsquerdo = new JPanel();
            painelEsquerdo.setLayout(new BoxLayout(painelEsquerdo, BoxLayout.Y_AXIS));
            painelEsquerdo.setBackground(new java.awt.Color(144, 238, 144));

            JLabel lblNome = new JLabel(m.getNome());
            lblNome.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 16));
            lblNome.setAlignmentX(Component.LEFT_ALIGNMENT);
            painelEsquerdo.add(lblNome);

            JLabel lblArtista = new JLabel("<html><b>Artista:</b> " +
                    m.getArtista().getNomeArtistico() + "</html>");
            JLabel lblGenero = new JLabel("<html><b>Gênero:</b> " +
                    m.getGenero() + "</html>");
            JLabel lblAno = new JLabel("<html><b>Ano:</b> " +
                    m.getAnoLancamento() + "</html>");
            JLabel lblAlbum = new JLabel("<html><b>Álbum:</b> " +
                    m.getAlbum() + "</html>");

            Font fonte = new Font("Segoe UI Semibold", Font.PLAIN, 13);
            for (JLabel lbl : new JLabel[]{lblArtista, lblGenero, lblAno, lblAlbum}) {
                lbl.setFont(fonte);
                lbl.setAlignmentX(Component.LEFT_ALIGNMENT);
                painelEsquerdo.add(lbl);
            }

            musicaPanel.add(painelEsquerdo);
            musicaPanel.setPreferredSize(new java.awt.Dimension(350, 110));
            musicaPanel.setMaximumSize(new java.awt.Dimension(350, 110));

            // distribui em 4 colunas
            if (i < 7) {
                telaMostrar.add(musicaPanel);
                telaMostrar.add(Box.createVerticalStrut(6));
            } else if (i < 14) {
                telaMostrar2.add(musicaPanel);
                telaMostrar2.add(Box.createVerticalStrut(6));
            } else if (i < 21) {
                telaMostrar3.add(musicaPanel);
                telaMostrar3.add(Box.createVerticalStrut(6));
            } else {
                telaMostrar4.add(musicaPanel);
                telaMostrar4.add(Box.createVerticalStrut(6));
            }
        }

        // atualiza as telas
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
        titulo5 = new javax.swing.JLabel();
        btnVoltar1 = new javax.swing.JButton();
        telaMostrar = new javax.swing.JPanel();
        telaMostrar2 = new javax.swing.JPanel();
        telaMostrar3 = new javax.swing.JPanel();
        telaMostrar4 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Músicas descurtidas");

        painel.setBackground(new java.awt.Color(144, 238, 144));
        painel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 0));
        painel.setForeground(new java.awt.Color(0, 102, 0));
        painel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jPanel8.setBackground(new java.awt.Color(51, 51, 51));
        jPanel8.setForeground(new java.awt.Color(102, 102, 102));

        titulo4.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        titulo4.setForeground(new java.awt.Color(73, 151, 51));
        titulo4.setText("Spotifei");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(titulo4)
                .addGap(649, 649, 649))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addComponent(titulo4)
                .addGap(24, 24, 24))
        );

        titulo5.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        titulo5.setForeground(new java.awt.Color(51, 51, 51));
        titulo5.setText("Músicas descurtidas:");

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
            .addGap(0, 803, Short.MAX_VALUE)
        );

        telaMostrar2.setBackground(new java.awt.Color(144, 238, 144));

        javax.swing.GroupLayout telaMostrar2Layout = new javax.swing.GroupLayout(telaMostrar2);
        telaMostrar2.setLayout(telaMostrar2Layout);
        telaMostrar2Layout.setHorizontalGroup(
            telaMostrar2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
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
            .addGap(0, 275, Short.MAX_VALUE)
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
            .addGap(0, 292, Short.MAX_VALUE)
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
                    .addComponent(titulo5)
                    .addGroup(painelLayout.createSequentialGroup()
                        .addGroup(painelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(telaMostrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnVoltar1, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(telaMostrar2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(telaMostrar3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(telaMostrar4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(43, Short.MAX_VALUE))
        );
        painelLayout.setVerticalGroup(
            painelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelLayout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(titulo5, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelLayout.createSequentialGroup()
                        .addComponent(telaMostrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnVoltar1)
                        .addGap(16, 16, 16))
                    .addComponent(telaMostrar2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(telaMostrar3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(telaMostrar4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(painel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(painel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    /**
     * Volta para a tela anterior apos o click, nesse caso a Visualizar Historic
     * @param evt 
     */
    private void btnVoltar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoltar1ActionPerformed
        this.setVisible(false);
        VisualizarHistoricoFrame hm = new VisualizarHistoricoFrame(usuario,id);
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
//            java.util.logging.Logger.getLogger(MusicasDescurtidasFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(MusicasDescurtidasFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(MusicasDescurtidasFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(MusicasDescurtidasFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new MusicasDescurtidasFrame().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnVoltar1;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel painel;
    private javax.swing.JPanel telaMostrar;
    private javax.swing.JPanel telaMostrar2;
    private javax.swing.JPanel telaMostrar3;
    private javax.swing.JPanel telaMostrar4;
    private javax.swing.JLabel titulo4;
    private javax.swing.JLabel titulo5;
    // End of variables declaration//GEN-END:variables
}
