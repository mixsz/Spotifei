/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import javax.swing.JOptionPane;
import model.Usuario;
import view.CadastroFrame;
import view.LoginFrame;

/**
 *
 * @author Mixzq
 */
public class ControllerCadastro extends javax.swing.JFrame{
    
    private CadastroFrame view;

     public ControllerCadastro(CadastroFrame cadastroFrame) {
        this.view = cadastroFrame;
    }
     
    public void Cadastro(){
        String nome = view.getTxtNome().getText();
        String sobrenome = view.getTxtUsername2().getText(); //username2=sobrenome
        String username = view.getTxtUsername().getText();
        String idade = view.getTxtUsername1().getText(); //username1 = idade
        String senha = view.getTxtSenha().getText();        
        String confirmarSenha = view.getTxtSenha1().getText();
        String sexo = null;
        if(view.getBtnMasculino().isSelected()){
                sexo = "masculino";
        }
        else if(view.getBtnFeminino().isSelected()){
                sexo = "feminino";
        }
        
        if (nome == null || nome.isEmpty() || sobrenome == null || 
                sobrenome.isEmpty() || 
        username == null || username.isEmpty() || senha == null || 
                senha.isEmpty() || 
        confirmarSenha == null || confirmarSenha.isEmpty() || idade == null || 
                idade.isEmpty() || sexo == null || sexo.isEmpty()) {
        
            JOptionPane.showMessageDialog(null, "Todos os dados devem ser informados!", 
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if(!senha.equals(confirmarSenha)){
            JOptionPane.showMessageDialog(
            null,                            
            "Senha incorreta. Tente novamente!",
            "Erro de Senha",                
            JOptionPane.ERROR_MESSAGE);
            return;
        }
        int idadeInt = 0;
        try{
            idadeInt = Integer.parseInt(idade);
        }
        catch(NumberFormatException e){
            JOptionPane.showMessageDialog(
            null, 
            "Idade inválida. Tente novamente!",
            "Erro de Idade",                 
            JOptionPane.ERROR_MESSAGE);          
            return; 
        }
        
        nome = nome.substring(0, 1).toUpperCase()  
               +  nome.substring(1).toLowerCase(); 
        
        //deixa a string padronizada: eXemplO -> Exemplo
        
        sobrenome = sobrenome.substring(0, 1).toUpperCase() 
                + sobrenome.substring(1).toLowerCase();    
        
        Usuario usuario = new Usuario(nome,sobrenome,username,idadeInt,senha,sexo);
        
        JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!", 
            "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        LoginFrame loginFrame = new LoginFrame();
        view.setVisible(false);
        loginFrame.setLocationRelativeTo(null);
        loginFrame.setVisible(true); 
    }
    
    
}
