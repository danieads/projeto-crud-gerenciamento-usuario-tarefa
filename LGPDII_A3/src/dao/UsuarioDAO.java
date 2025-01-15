package dao;

import model.Usuario;
import util.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class UsuarioDAO {

    public void saveUserDao(Usuario usuario) {
        String sql = "INSERT INTO Usuario(nome, email, senha) VALUES (?, ?, ?)";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
             
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenha());
            stmt.executeUpdate();
            
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    usuario.setId(rs.getInt(1));
                }
            }
            
        } catch (SQLIntegrityConstraintViolationException e) {
            System.err.println("Erro: O e-mail já está em uso. Por favor, escolha outro.");
        } catch (SQLException e) {
            System.err.println("Erro ao salvar usuário: " + e.getMessage());
        }
    }

    public ArrayList<Usuario> findAllUserDao() {
        String sql = "SELECT * FROM Usuario";
        ArrayList<Usuario> usuarios = new ArrayList<>();
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
             
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setSenha(rs.getString("senha"));
                usuarios.add(usuario);
            }
            
        } catch (Exception e) {
            System.err.println("Erro ao listar usuários: " + e.getMessage());
        }
        
        return usuarios;
    }

    public void updateUserDao(Usuario usuario) {
        String sql = "UPDATE Usuario SET nome = ?, email = ?, senha = ? WHERE id = ?";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenha());
            stmt.setInt(4, usuario.getId()); 
            stmt.executeUpdate();
        } catch (Exception e) {
              JOptionPane.showMessageDialog(null,"Erro ao atualizar o usuario!");
        }
    }

    public void deleteUserDao(int id) {
       // Primeiro, remover as tarefas associadas
        TarefaDAO tarefaDAO = new TarefaDAO();
        tarefaDAO.deleteByUsuarioId(id);
        
        // Depois, remover o usuário
        String sql = "DELETE FROM Usuario WHERE id = ?";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setInt(1, id);
            
            if (stmt.executeUpdate() == 0) {
                System.err.println("Erro: Nenhum usuário foi encontrado com o ID fornecido.");
            }
            
        } catch (SQLException e) {
            System.err.println("Erro ao deletar usuário: " + e.getMessage());
        }
    }
    
// abaixo os complicados metodos para verificar se um email existe:
// Método para verificar se um usuário existe pelo ID
    public boolean userExistsDAO(int id) {
        String sql = "SELECT COUNT(*) FROM Usuario WHERE id = ?";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; // Retorna true se houver algum registro
            }
        } catch (SQLException e) {
            System.err.println("Erro ao verificar usuário: " + e.getMessage());
        }
        
        return false; // Se não houver registros, retorna false
    }

    // Método para verificar se o e-mail já existe
    public boolean emailExists(String email) {
        String sql = "SELECT COUNT(*) FROM Usuario WHERE email = ?";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; // Retorna true se houver algum registro
            }
        } catch (SQLException e) {
            System.err.println("Erro ao verificar e-mail: " + e.getMessage());
        }
        
        return false; // Se não houver registros, retorna false
    }

    // Método para verificar se o e-mail já existe, exceto para o usuário atual
    public boolean emailExists(String email, int userId) {
        String sql = "SELECT COUNT(*) FROM Usuario WHERE email = ? AND id != ?";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setString(1, email);
            stmt.setInt(2, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; // Retorna true se houver algum registro
            }
        } catch (SQLException e) {
            System.err.println("Erro ao verificar e-mail: " + e.getMessage());
        }
        
        return false; // Se não houver registros, retorna false
    }
}