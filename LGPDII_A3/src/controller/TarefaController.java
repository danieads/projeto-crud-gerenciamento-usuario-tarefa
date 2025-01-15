package controller;

import model.Tarefa;
import service.TarefaService;
import service.CategoriaService;
import model.Categoria;

import javax.swing.*;
import java.util.List;

public class TarefaController {

    private TarefaService tarefaService; // Serviço para gerenciar tarefas
    private CategoriaService categoriaService; // Serviço para gerenciar categorias

    public TarefaController() {
        this.tarefaService = new TarefaService();
        this.categoriaService = new CategoriaService();
    }

    // Método para selecionar uma categoria a partir de uma lista
    private int selectCategoria() {
        List<Categoria> categorias = categoriaService.findCategorias();
        StringBuilder categoriasList = new StringBuilder("Categorias disponíveis:\n");
        for (Categoria categoria : categorias) {
            categoriasList.append(categoria.getId()).append(" - ").append(categoria.getNome()).append("\n");
        }
        String input = JOptionPane.showInputDialog(categoriasList.toString() + "Digite o ID da categoria:");
        return Integer.parseInt(input); 
    }

    // Método para cadastrar uma nova tarefa
    public void registerTarefa() {
        boolean verificado = false;
        while (!verificado) {
            try {
                String titulo = JOptionPane.showInputDialog("Digite o título da tarefa:");
                String descricao = JOptionPane.showInputDialog("Digite a descrição da tarefa:");
                int status = Integer.parseInt(JOptionPane.showInputDialog("Digite o status da tarefa (1 - Concluída, 0 - Pendente):"));

                int categoriaId = selectCategoria();
                int usuarioId = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID do usuário:"));

                Tarefa tarefa = new Tarefa(titulo, descricao, status, usuarioId, categoriaId); 
                tarefaService.saveTarefa(tarefa); 
                JOptionPane.showMessageDialog(null, "Tarefa cadastrada com sucesso!");
                verificado = true; 
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro ao cadastrar tarefa: " + e.getMessage());
            }
        }
    }

    // Método para listar todas as tarefas cadastradas
    public void findAllTarefas() {
        List<Tarefa> tarefas = tarefaService.addFindAllTarefas();
        StringBuilder tarefasList = new StringBuilder("Tarefas cadastradas:\n");
        for (Tarefa tarefa : tarefas) {
            tarefasList.append(tarefa).append("\n");
        }
        JOptionPane.showMessageDialog(null, tarefasList.toString());
    }

    // Método para atualizar uma tarefa existente
    public void updateTarefa() {
        boolean verificado = false; 
        while (!verificado) {
            try {
                int id = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID da tarefa a ser atualizada:"));
                String titulo = JOptionPane.showInputDialog("Digite o novo título da tarefa:");
                String descricao = JOptionPane.showInputDialog("Digite a nova descrição da tarefa:");
                int status = Integer.parseInt(JOptionPane.showInputDialog("Digite o novo status da tarefa (1 - Concluído, 0 - Não Concluído):"));
                int usuarioId = Integer.parseInt(JOptionPane.showInputDialog("Digite o novo ID do usuário:"));
                int categoriaId = Integer.parseInt(JOptionPane.showInputDialog("Digite o novo ID da categoria:"));

                Tarefa tarefa = new Tarefa(id, titulo, descricao, status, usuarioId, categoriaId);
                tarefaService.updateTarefa(tarefa);
                JOptionPane.showMessageDialog(null, "Tarefa atualizada com sucesso!");
                verificado = true; 
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro ao atualizar tarefa: " + e.getMessage());
            }
        }
    }

    // Método para filtrar tarefas por status
    public void findAllStatusTarefas() {
        int status = Integer.parseInt(JOptionPane.showInputDialog
        ("Digite o status da tarefa para filtrar (1 - Concluído, 0 - Não Concluído):"));
        List<Tarefa> tarefas = tarefaService.filtrarTarefas(status);
        StringBuilder tarefasList = new StringBuilder("Tarefas filtradas:\n");
        for (Tarefa tarefa : tarefas) {
            tarefasList.append(tarefa).append("\n");
        }
        JOptionPane.showMessageDialog(null, tarefasList.toString());
    }

    // Método para ordenar tarefas por status
    public void orderByStatsTarefas() {
        List<Tarefa> tarefas = tarefaService.ordenarTarefas(); 
        StringBuilder tarefasList = new StringBuilder("Tarefas ordenadas:\n");
        for (Tarefa tarefa : tarefas) {
            tarefasList.append(tarefa).append("\n"); 
        }
        JOptionPane.showMessageDialog(null, tarefasList.toString());
    }

    // Método para exibir um relatório com o total de tarefas
    public void displayReportTarefas() {
        int totalTarefas = tarefaService.exibirRelatorioTarefas(); 
        JOptionPane.showMessageDialog(null, "Total de tarefas: " + totalTarefas);
    }
}