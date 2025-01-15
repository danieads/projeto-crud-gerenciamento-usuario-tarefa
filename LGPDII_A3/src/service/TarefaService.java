package service;

import dao.TarefaDAO;
import model.Tarefa;

import javax.swing.*;
import java.util.ArrayList;

public class TarefaService {
    private TarefaDAO tarefaDAO;

    public TarefaService() {
        this.tarefaDAO = new TarefaDAO();
    }

    public boolean verificationSaveTarefa(Tarefa tarefa) {
        // Verifica se os dados da tarefa são válidos
        if (tarefa.getUsuarioId() <= 0) {
            JOptionPane.showMessageDialog(null, "Erro: ID do usuário inválido.");
            return false;
        }

        if (tarefa.getCategoriaId() <= 0) {
            JOptionPane.showMessageDialog(null, "Erro: ID da categoria inválido.");
            return false;
        }

        if (tarefa.getTitulo() == null || tarefa.getTitulo().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Erro: O título da tarefa não pode ser vazio.");
            return false;
        }

        if (tarefa.getDescricao() == null || tarefa.getDescricao().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Erro: A descrição da tarefa não pode ser vazia.");
            return false;
        }

        if (tarefa.getStatus() < 0 || tarefa.getStatus() > 1) { // Supondo status 0 e 1
            JOptionPane.showMessageDialog(null, "Erro: Status inválido. Use 0 para pendente e 1 para concluída.");
            return false;
        }

        return true; // Se todas as validações passaram
    }

    public void saveTarefa(Tarefa tarefa) {
        if (verificationSaveTarefa(tarefa)) {
            tarefaDAO.save(tarefa);
        } else {
            JOptionPane.showMessageDialog(null, "Erro: Não foi possível salvar a tarefa devido a erros de validação.");
        }
    }

    public ArrayList<Tarefa> addFindAllTarefas() {
        return (ArrayList<Tarefa>) tarefaDAO.findAll();
    }

    public boolean verificationUpdateTarefa(Tarefa tarefa) {
        if (tarefa.getId() <= 0) {
            JOptionPane.showMessageDialog(null, "Erro: ID inválido para atualização.");
            return false;
        }

        // Validações de dados
        return verificationSaveTarefa(tarefa); // Reutiliza a validação de salvar
    }

    public void updateTarefa(Tarefa tarefa) {
        if (verificationUpdateTarefa(tarefa)) {
            tarefaDAO.updateTarefaDAO(tarefa);
        } else {
            JOptionPane.showMessageDialog(null, "Erro: Não foi possível atualizar a tarefa devido a erros de validação.");
        }
    }

    public boolean verificationDeleteTarefa(int id) {
        if (id <= 0) {
            JOptionPane.showMessageDialog(null, "Erro: ID inválido para exclusão.");
            return false; 
        }

        tarefaDAO.delete(id);
        return true; 
    }

    public ArrayList<Tarefa> filtrarTarefas(int status) {
        return (ArrayList<Tarefa>) tarefaDAO.findByStatus(status);
    }

    public ArrayList<Tarefa> ordenarTarefas() {
        return (ArrayList<Tarefa>) tarefaDAO.findAllOrderedByStatus();
    }

    public int exibirRelatorioTarefas() {
        return tarefaDAO.countAll();
    }
}