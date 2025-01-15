package dao;

import model.Categoria;
import util.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {

    public void save(Categoria categoria) {
        String sql = "INSERT INTO Categoria(nome, descricao) VALUES (?, ?)";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
             
            stmt.setString(1, categoria.getNome());
            stmt.setString(2, categoria.getDescricao());
            
            stmt.executeUpdate();
            
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    categoria.setId(rs.getInt(1));
                }
            }
            
        } catch (SQLIntegrityConstraintViolationException e) {
            System.err.println("Erro: Categoria já existe com esse nome.");
        } catch (SQLException e) {
            System.err.println("Erro ao salvar categoria: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Erro inesperado ao salvar categoria: " + e.getMessage());
        }
    }

    public List<Categoria> findAll() {
        String sql = "SELECT * FROM Categoria";
        List<Categoria> categorias = new ArrayList<>();
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
             
            while (rs.next()) {
                Categoria categoria = new Categoria();
                categoria.setId(rs.getInt("id"));
                categoria.setNome(rs.getString("nome"));
                categoria.setDescricao(rs.getString("descricao"));
                categorias.add(categoria);
            }
            
        } catch (SQLException e) {
            System.err.println("Erro ao listar categorias: " + e.getMessage());
        }
        
        return categorias;
    }

    public void update(Categoria categoria) {
        String sql = "UPDATE Categoria SET nome = ?, descricao = ? WHERE id = ?";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setString(1, categoria.getNome());
            stmt.setString(2, categoria.getDescricao());
            stmt.setInt(3, categoria.getId());
            
            if (stmt.executeUpdate() == 0) {
                System.err.println("Erro: Nenhuma categoria foi atualizada. Verifique se o ID é válido.");
            }
            
        } catch (SQLIntegrityConstraintViolationException e) {
            System.err.println("Erro: Não foi possivel");
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar categoria: " + e.getMessage());
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM Categoria WHERE id = ?";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setInt(1, id);
            
            if (stmt.executeUpdate() == 0) {
                System.err.println("Erro: Nenhuma categoria foi encontrada com o ID fornecido.");
            }
            
        } catch (SQLException e) {
            System.err.println("Erro ao deletar categoria: " + e.getMessage());
        }
    }
}
