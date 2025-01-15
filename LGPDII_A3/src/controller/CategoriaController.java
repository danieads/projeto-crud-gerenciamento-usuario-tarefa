package controller;

import model.Categoria;
import service.CategoriaService;

import javax.swing.*;
import java.util.List;

public class CategoriaController {
    private CategoriaService categoriaService; // Instância do serviço de categoria

    public CategoriaController() {
        this.categoriaService = new CategoriaService();
    }

    // Método para cadastrar uma nova categoria
    public void saveCategoria() {
        boolean verificado = false; 
        while (!verificado) {
            try {
                String nome = JOptionPane.showInputDialog("Digite o nome da categoria:");
                String descricao = JOptionPane.showInputDialog("Digite a descrição da categoria:");

                Categoria categoria = new Categoria(nome, descricao); 
                categoriaService.saveCategoria(categoria); 
                JOptionPane.showMessageDialog(null, "Categoria cadastrada com sucesso!");
                verificado = true; 
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro ao cadastrar categoria: " + e.getMessage());
            }
        }
    }

    // Método para listar todas as categorias cadastradas
    public void findAllCategorias() {
        List<Categoria> categorias = categoriaService.findCategorias(); 
        StringBuilder categoriasList = new StringBuilder("Categorias cadastradas:\n");
        for (Categoria categoria : categorias) {
            categoriasList.append(categoria).append("\n"); 
        }
        JOptionPane.showMessageDialog(null, categoriasList.toString());
    }

    // Método para atualizar uma categoria existente
    public void updateCategoria() {
        boolean verificado = false;
        while (!verificado) {
            try {
                int id = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID da categoria a ser atualizada:"));
                String nome = JOptionPane.showInputDialog("Digite o novo nome da categoria:");
                String descricao = JOptionPane.showInputDialog("Digite a nova descrição da categoria:");

                Categoria categoria = new Categoria(id, nome, descricao); 
                categoriaService.saveUpdate(categoria); 
                JOptionPane.showMessageDialog(null, "Categoria atualizada com sucesso!");
                verificado = true; 
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro ao atualizar categoria: " + e.getMessage());
            }
        }
    }

    // Método para excluir uma categoria
    public void deleteCategoria() {
        boolean verificado = false;
        while (!verificado) {
            try {
                int id = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID da categoria a ser excluída:"));
                categoriaService.deleteCategoria(id); 
                JOptionPane.showMessageDialog(null, "Categoria excluída com sucesso!");
                verificado = true; 
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro ao excluir categoria: " + e.getMessage());
            }
        }
    }
}