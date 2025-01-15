package service;

import dao.UsuarioDAO;
import dao.TarefaDAO;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Usuario;

public class UsuarioService {

    private UsuarioDAO usuarioDAO;
    private TarefaDAO tarefaDAO;

    public UsuarioService() {
        this.usuarioDAO = new UsuarioDAO();
        this.tarefaDAO = new TarefaDAO();
    }

    public boolean verificationSaveUsuario(Usuario usuario) {
        // Verifica se o e-mail já existe
        if (usuarioDAO.emailExists(usuario.getEmail())) {
            JOptionPane.showMessageDialog(null, "Erro: O e-mail '" + usuario.getEmail() + "' já está em uso. Por favor, escolha outro.");
            return false;
        }

        // Verifica se os dados do usuário são válidos
        if (usuario.getNome() == null || usuario.getNome().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Erro: O nome do usuário não pode ser vazio.");
            return false;
        }

        if (usuario.getEmail() == null || usuario.getEmail().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Erro: O e-mail do usuário não pode ser vazio.");
            return false;
        }

        if (usuario.getSenha() == null || usuario.getSenha().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Erro: A senha do usuário não pode ser vazia.");
            return false;
        }

        // Se todas as validações passaram
        return true;
    }

    public void saveUsuario(Usuario usuario) {
        if (verificationSaveUsuario(usuario)) {
            usuarioDAO.saveUserDao(usuario); // Certifique-se de que o método saveUser  está corretamente implementado no DAO
        } else {
            JOptionPane.showMessageDialog(null, "Erro: Não foi possível salvar o usuário devido a erros de validação.");
        }
    }

    public ArrayList<Usuario> addFindAllUsuarios() {
        return usuarioDAO.findAllUserDao(); // Certifique-se de que o método findAllUser  está corretamente implementado no DAO
    }

    public boolean verificationUpdateUsuario(Usuario usuario) {
        // Verifica se o ID é válido
        if (usuario.getId() <= 0) {
            JOptionPane.showMessageDialog(null, "Erro: ID inválido para atualização.");
            return false;
        }

        // Validação de dados
        if (usuario.getNome() == null || usuario.getNome().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Erro: O nome do usuário não pode ser vazio.");
            return false;
        }

        if (usuario.getEmail() == null || usuario.getEmail().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Erro: O e-mail do usuário não pode ser vazio.");
            return false;
        }

        if (usuario.getSenha() == null || usuario.getSenha().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Erro: A senha do usuário não pode ser vazia.");
            return false;
        }

        // Verifica se o e-mail já existe, exceto para o próprio usuário
        if (usuarioDAO.emailExists(usuario.getEmail(), usuario.getId())) {
            JOptionPane.showMessageDialog(null, "Erro: O e-mail '" + usuario.getEmail() + "' já está em uso por outro usuário. Por favor, escolha outro.");
            return false;
        }

        return true; // Todas as validações passaram
    }

    public void updateUsuario(Usuario usuario) {
        if (verificationUpdateUsuario(usuario)) {
            usuarioDAO.updateUserDao(usuario);
        } else {
            JOptionPane.showMessageDialog(null, "Erro: Não foi possível atualizar o usuário devido a erros de validação.");
        }
    }

    public boolean verificationDeleteUsuario(int id) {
        // Verifica se o ID é válido
        if (id <= 0) {
            JOptionPane.showMessageDialog(null, "Erro: ID inválido para exclusão.");
            return false;
        }

        // Verifica se o usuário existe antes de tentar deletar
        if (!usuarioDAO.userExistsDAO(id)) { // Certifique-se de que o método userExists está corretamente implementado no DAO
            JOptionPane.showMessageDialog(null, "Erro: Usuário com ID " + id + " não encontrado.");
            return false;
        }

        usuarioDAO.deleteUserDao(id); // Certifique-se de que o método deleteUser  está corretamente implementado no DAO
        return true; // Retorna true se a exclusão foi bem-sucedida
    }
        public void deleteUsuario(int usuarioId) {
        usuarioDAO.deleteUserDao(usuarioId);
    }
}
