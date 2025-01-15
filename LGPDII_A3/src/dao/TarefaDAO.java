package dao;

import model.Tarefa;
import util.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Usuario;

public class TarefaDAO {

    public void save(Tarefa tarefa) {
        String sql = "INSERT INTO Tarefa(titulo, descricao, status, usuario_id, categoria_id) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, tarefa.getTitulo());
            stmt.setString(2, tarefa.getDescricao());
            stmt.setInt(3, tarefa.getStatus());
            stmt.setInt(4, tarefa.getUsuarioId());
            stmt.setInt(5, tarefa.getCategoriaId());

            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    tarefa.setId(rs.getInt(1));
                }
            }

        } catch (SQLIntegrityConstraintViolationException e) {
            System.err.println("Erro: Usuário ou Categoria não encontrados. Verifique os IDs informados.");
        } catch (SQLException e) {
            System.err.println("Erro ao salvar tarefa: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Erro inesperado ao salvar tarefa: " + e.getMessage());
        }
    }

    public List<Tarefa> findAll() {
        String sql = "SELECT * FROM Tarefa";
        List<Tarefa> tarefas = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Tarefa tarefa = new Tarefa();
                tarefa.setId(rs.getInt("id"));
                tarefa.setTitulo(rs.getString("titulo"));
                tarefa.setDescricao(rs.getString("descricao"));
                tarefa.setStatus(rs.getInt("status"));
                tarefa.setUsuarioId(rs.getInt("usuario_id"));
                tarefa.setCategoriaId(rs.getInt("categoria_id"));
                tarefas.add(tarefa);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar tarefas: " + e.getMessage());
        }

        return tarefas;
    }

    public void updateTarefaDAO(Tarefa tarefa) {
        String sql = "UPDATE Tarefa SET titulo = ?, descricao = ?, status = ?, usuario_id = ?, categoria_id = ? WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, tarefa.getTitulo());
            stmt.setString(2, tarefa.getDescricao());
            stmt.setInt(3, tarefa.getStatus());
            stmt.setInt(4, tarefa.getUsuarioId());
            stmt.setInt(5, tarefa.getCategoriaId());
            stmt.setInt(6, tarefa.getId());

            if (stmt.executeUpdate() == 0) {
                System.err.println("Erro: Nenhuma tarefa foi atualizada. Verifique se o ID é válido.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar tarefa: " + e.getMessage());
        }
    }

    public void delete(int id) {
       String sql = "DELETE FROM Tarefa WHERE id = ?";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setInt(1, id);
            
            if (stmt.executeUpdate() == 0) {
                System.err.println("Erro: Nenhuma tarefa foi encontrada com o ID fornecido.");
            }
            
        } catch (SQLException e) {
            System.err.println("Erro ao deletar tarefa: " + e.getMessage());
        }
    }

    public List<Tarefa> findByStatus(int status) {
        List<Tarefa> tarefas = new ArrayList<>();
        String sql = "SELECT * FROM Tarefa WHERE status = ?";
        try (Connection conn = ConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, status);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Tarefa tarefa = new Tarefa();
                    tarefa.setId(rs.getInt("id"));
                    tarefa.setTitulo(rs.getString("titulo"));
                    tarefa.setDescricao(rs.getString("descricao"));
                    tarefa.setStatus(rs.getInt("status"));
                    tarefa.setUsuarioId(rs.getInt("usuario_id"));
                    tarefa.setCategoriaId(rs.getInt("categoria_id"));
                    tarefas.add(tarefa);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar tarefas por status: " + e.getMessage());
        }
        return tarefas;
    }

    public List<Tarefa> findAllOrderedByStatus() {
        List<Tarefa> tarefas = new ArrayList<>();
        String sql = "SELECT * FROM Tarefa ORDER BY status";
        try (Connection conn = ConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Tarefa tarefa = new Tarefa();
                tarefa.setId(rs.getInt("id"));
                tarefa.setTitulo(rs.getString("titulo"));
                tarefa.setDescricao(rs.getString("descricao"));
                tarefa.setStatus(rs.getInt("status"));
                tarefa.setUsuarioId(rs.getInt("usuario_id"));
                tarefa.setCategoriaId(rs.getInt("categoria_id"));
                tarefas.add(tarefa);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar tarefas ordenadas por status: " + e.getMessage());
        }
        return tarefas;
    }

    public int countAll() {
        int count = 0;
        String sql = "SELECT COUNT(*) FROM Tarefa";
        try (Connection conn = ConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao contar tarefas: " + e.getMessage());
        }
        return count;
    }
    
    //delete usuario Forengh Key 
    public void deleteByUsuarioId(int usuarioId) {
        String sql = "DELETE FROM Tarefa WHERE usuario_id = ?";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setInt(1, usuarioId);
            stmt.executeUpdate();
            
        } catch (SQLException e) {
            System.err.println("Erro ao deletar tarefas associadas ao usuário: " + e.getMessage());
        }
    }
}