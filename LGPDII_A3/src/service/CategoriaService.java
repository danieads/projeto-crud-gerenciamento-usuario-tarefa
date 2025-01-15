package service;

import dao.CategoriaDAO;
import model.Categoria;

import java.util.List;
import javax.swing.JOptionPane;

public class CategoriaService {
    private CategoriaDAO categoriaDAO = new CategoriaDAO();

    public boolean verificationSaveCategoria(Categoria categoria) {
        // Validações de dados
        
        if (categoria.getNome() == null || categoria.getNome().isEmpty()) {
            JOptionPane.showMessageDialog(null,"Erro: O nome da categoria não pode ser vazio.");
            return false;
        }
        
        if (categoria.getDescricao() == null || categoria.getDescricao().isEmpty()) {
            JOptionPane.showMessageDialog(null,"Erro: A descrição da categoria não pode ser vazia.");
            return false;
        }
        return true;
    }

    public void saveCategoria(Categoria categoria) {
        if (verificationSaveCategoria(categoria)) {
            categoriaDAO.save(categoria);
        } else {
            JOptionPane.showMessageDialog(null, "Erro: Não foi possível salvar o usuário devido a erros de validação.");
        }
    }
    public List<Categoria> findCategorias() {
        return categoriaDAO.findAll();
    }

    public boolean verificationUpdateCategoria(Categoria categoria) {
        if (categoria.getId() <= 0) {
            JOptionPane.showMessageDialog(null,"Erro: ID inválido para atualização.");
            return true;
        }

        // Validações de dados
        if (categoria.getNome() == null || categoria.getNome().isEmpty()) {
            JOptionPane.showMessageDialog(null,"Erro: O nome da categoria não pode ser vazio.");
            return true;
        }
        return true;
    }

    public void saveUpdate(Categoria categoria) {
        if (verificationUpdateCategoria(categoria)) {
            categoriaDAO.update(categoria);
        } else {
            JOptionPane.showMessageDialog(null, "Erro: Não foi possível salvar o usuário devido a erros de validação.");
        }
    }
    
    public boolean deleteCategoria(int id) {
        if (id <= 0) {
            JOptionPane.showMessageDialog(null,"Erro: ID inválido para exclusão.");
            return false;
        }
        categoriaDAO.delete(id);
        return true;
    }
}
