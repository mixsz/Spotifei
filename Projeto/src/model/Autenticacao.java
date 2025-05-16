/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 * interface que define o metodo para autenticação de usuários.
 * 
 * Qualquer classe que implementar essa interface deve fornecer a utilizacao
 * do método {autenticar}, que verifica se um usuário pode ser autenticado
 * com um username e senha fornecidos e faz um print pelo terminal se
 * o user foi autenticado.
 * 
 */



public interface Autenticacao {
    /**
    * verifica se o usuário pode ser autenticado pelo nome e senha recebidos.
    * 
    * @param username nome do usuario inserido.
    * @param senha senha do usuario inserida.
    * @return true se estiverem iguais.
    */
    boolean autenticar(String username, String senha);
}
