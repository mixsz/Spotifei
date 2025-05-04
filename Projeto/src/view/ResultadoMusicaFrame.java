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
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.Musica;

/**
 *
 * @author Danilo
 */
public class ResultadoMusicaFrame extends javax.swing.JFrame {

    /**
     * Creates new form ResultadoMusicaFrame
     */
    private String usuario;
    private int id;
    private ArrayList <Musica> musicas;
    private ControllerMusica controllerMusica;

    public ResultadoMusicaFrame(String usuario, int id, ArrayList<Musica> musicas) {
        initComponents();
        this.controllerMusica = controllerMusica;
        this.usuario = usuario;
        this.id = id;
        this.musicas = musicas;
        mostrarMusicas();
         for(Musica m : musicas){
            System.out.println("Artista: " + m.getArtista() +
                                "\nNome: " + m.getNome() +
                                "\nGenero: " + m.getGenero() +
                                "\nAno: " + m.getAnoLancamento() + 
                                "\nAlbum: " + m.getAlbum() + 
                                "\nID: " + m.getId() +
                                '\n');
        }
    }
    
private void mostrarMusicas() {
    telaMostrar.removeAll();
    telaMostrar2.removeAll(); 
    telaMostrar.setLayout(new BoxLayout(telaMostrar, BoxLayout.Y_AXIS));
    telaMostrar2.setLayout(new BoxLayout(telaMostrar2, BoxLayout.Y_AXIS));

    // A PESQUISA SÓ VAI ATÉ 10 PORQUE MAIOR QUE ISSO PASSA DO TAMANHO DA TELA
    // 5 DE CADA LADO: 0 a 4 -> telaMostrar /// 5 a 9 -> telaMostrar2
    int limite = Math.min(musicas.size(), 10);

    for (int i = 0; i < limite; i++) {
        Musica m = musicas.get(i);

        JPanel musicaPanel = new JPanel();
        musicaPanel.setLayout(new BoxLayout(musicaPanel, BoxLayout.X_AXIS)); 
        musicaPanel.setBackground(new java.awt.Color(144, 238, 144));

        // BORDA COM TAMANHOS FIXOS E CORES FIXAS
        musicaPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new java.awt.Color(60, 179, 113), 1),
            BorderFactory.createEmptyBorder(3, 15, 10, 15)  
        ));

        // PAINEL INTERNO QUE POSSUI CADA MUSICA
        JPanel painelEsquerdo = new JPanel();
        painelEsquerdo.setLayout(new BoxLayout(painelEsquerdo, BoxLayout.Y_AXIS)); 
        painelEsquerdo.setBackground(new java.awt.Color(144, 238, 144));

        // NOME DA MUSICA POSSUI UMA FONTE MAIOR
        JLabel lblNome = new JLabel(m.getNome());
        lblNome.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 20));
        lblNome.setAlignmentX(Component.LEFT_ALIGNMENT);
        painelEsquerdo.add(lblNome);

        // INFORMAÇÕES FORNECIDAS, ONDE O NOME DO DADO ESTÁ EM NEGRITO
        JLabel lblArtista = new JLabel("<html><b>Artista:</b> " 
                               + m.getArtista().getNomeArtistico() + "</html>");
        
        JLabel lblGenero = new JLabel("<html><b>Gênero:</b> "
                               + m.getGenero() + "</html>");
        
        JLabel lblAno = new JLabel("<html><b>Ano:</b> "
                               + m.getAnoLancamento() + "</html>");
        
        JLabel lblAlbum = new JLabel("<html><b>Álbum:</b> " 
                               + m.getAlbum() + "</html>");

        Font fonte = new Font("Segoe UI Semibold", Font.PLAIN, 15);
        for (JLabel lbl : new JLabel[]{lblArtista, lblGenero, lblAno, lblAlbum}) {
            lbl.setFont(fonte);
            lbl.setAlignmentX(Component.LEFT_ALIGNMENT);
            painelEsquerdo.add(lbl);
        }

        // PAINEL PARA OS BOTÕES CURTIR E DESCURTIR
        JPanel painelDireito = new JPanel();
        painelDireito.setLayout(new BoxLayout(painelDireito, BoxLayout.Y_AXIS));
        painelDireito.setBackground(new java.awt.Color(144, 238, 144));

        // BOTÃO CURTIR
        JButton btnCurtir = new JButton("Curtir");
        btnCurtir.setBackground(new java.awt.Color(51,51,51));  
        btnCurtir.setForeground(new java.awt.Color(0,153,0));
        btnCurtir.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnCurtir.setPreferredSize(new java.awt.Dimension(80, 25)); 
        btnCurtir.setMaximumSize(new java.awt.Dimension(80, 25)); 

        // BOTÃO DESCURTIR
        JButton btnDescurtir = new JButton("Descurtir");
        btnDescurtir.setBackground(new java.awt.Color(51,51,51));  
        btnDescurtir.setForeground(new java.awt.Color(200,0,0));
        btnDescurtir.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnDescurtir.setPreferredSize(new java.awt.Dimension(80, 25)); 
        btnDescurtir.setMaximumSize(new java.awt.Dimension(80, 25)); 

        // Adicionando ActionListener ao botão Curtir
        btnCurtir.addActionListener(e -> {
            ControllerMusica controller = new ControllerMusica(usuario, id, this); 
            
            controller.curtirMusica(m.getId()); 
        });

        // Adicionando ActionListener ao botão Descurtir
        btnDescurtir.addActionListener(e -> {
            ControllerMusica controller = new ControllerMusica(usuario, id, this);
            controller.descurtirMusica(m.getId());
        });

        painelDireito.add(btnCurtir);
        painelDireito.add(Box.createVerticalStrut(8)); // ESPACO ENTRE BOTOES
        painelDireito.add(btnDescurtir);

        // PAINEL DIREITO: BOTOES ---- PAINEL ESQUERDO: INFORMAÇÕES
        musicaPanel.add(painelEsquerdo);
        musicaPanel.add(painelDireito);

        // TAMANHOS FIXOS DE PAINEIS
        musicaPanel.setPreferredSize(new java.awt.Dimension(400, 130));
        musicaPanel.setMaximumSize(new java.awt.Dimension(400, 130));

        // OBJETOS ANTES DO 5 FICAM NA COLUNA DA ESQUERDA
        // OBJETOS DEPOIS DO 5 FICAM NA COLUNA DA DIREITA
        if (i < 5) {
            telaMostrar.add(musicaPanel);
            telaMostrar.add(Box.createVerticalStrut(8)); // GAP ENTRE OBJETOS
        } else {
            musicaPanel.setPreferredSize(new java.awt.Dimension(400, 129)); 
            musicaPanel.setMaximumSize(new java.awt.Dimension(400, 129));
            telaMostrar2.add(musicaPanel);
            telaMostrar2.add(Box.createVerticalStrut(8)); // GAP ENTRE OBJETOS 
        }
    }

    // COMANDOS Q GARANTEM A EXIBIÇÃO NA TELA
    telaMostrar.revalidate();
    telaMostrar.repaint();
    telaMostrar2.revalidate();
    telaMostrar2.repaint();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Músicas encontradas");
        setBackground(new java.awt.Color(144, 238, 144));

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
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    
    
    private void btnVoltar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoltar1ActionPerformed
        this.setVisible(false);
        BuscarMusicaFrame hm = new BuscarMusicaFrame(usuario,id);
        hm.setLocationRelativeTo(null);
        hm.setVisible(true);
    }//GEN-LAST:event_btnVoltar1ActionPerformed
    
    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnVoltar1;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel painel;
    private javax.swing.JPanel telaMostrar;
    private javax.swing.JPanel telaMostrar2;
    private javax.swing.JLabel titulo4;
    // End of variables declaration//GEN-END:variables
}