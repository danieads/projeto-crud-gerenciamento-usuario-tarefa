package controller;

import java.awt.HeadlessException;
import model.Usuario;
import service.UsuarioService;

import javax.swing.*;
import java.util.ArrayList;

public class UsuarioController {

    private UsuarioService usuarioService;

    public UsuarioController() {
        this.usuarioService = new UsuarioService();
    }

    public void saveUsuario() {
        boolean verificado = false;
        while (!verificado) {
            try{
            String nome = JOptionPane.showInputDialog("Digite o nome do usuário:");
            String email = JOptionPane.showInputDialog("Digite o email do usuário:");
            String senha = JOptionPane.showInputDialog("Digite a senha do usuário:");

            Usuario usuario = new Usuario(nome, email, senha);

            // Verifica se o usuário pode ser salvo
            verificado = usuarioService.verificationSaveUsuario(usuario);

                usuarioService.saveUsuario(usuario); // Salva o usuário
                JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!");
                verificado = true; 
            } catch(Exception e){
                // Se não foi verificado, mostra um erro e reinicia o loop
                JOptionPane.showMessageDialog(null, "Erro ao cadastrar usuário. Tente novamente.");
            }
        }
    }

    public void findAllUsuarios() {

        ArrayList<Usuario> usuarios = usuarioService.addFindAllUsuarios();
        StringBuilder usuariosList = new StringBuilder("Usuários cadastrados:\n");
        for (Usuario usuario : usuarios) {
            usuariosList.append(usuario).append("\n");
        }
        JOptionPane.showMessageDialog(null, usuariosList.toString());
    }

    public void updateUsuario() {
        boolean verificado = false;
        while (!verificado) {
            try {
                int id = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID do usuário a ser atualizado:"));
                String nome = JOptionPane.showInputDialog("Digite o novo nome do usuário:");
                String email = JOptionPane.showInputDialog("Digite o novo email do usuário:");
                String senha = JOptionPane.showInputDialog("Digite a nova senha do usuário:");

                Usuario usuario = new Usuario(id, nome, email, senha);

                verificado = usuarioService.verificationUpdateUsuario(usuario);
                JOptionPane.showMessageDialog(null, "Usuário atualizado com sucesso!");
                verificado = true; 
            } catch (HeadlessException | NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Erro ao Atualizar o usuario!");
            }
        }
    }

    public void deleteUsuario() {
        boolean verificado = false;
        while (!verificado) {
            try {
                int id = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID do usuário a ser excluído:"));
                verificado = usuarioService.verificationDeleteUsuario(id);
                JOptionPane.showMessageDialog(null, "Usuário excluído com sucesso!");
                verificado = true; 
            } catch (HeadlessException | NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Usuário excluído com sucesso!");
            }
        }
    }
}
