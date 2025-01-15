package main;

import controller.CategoriaController;
import controller.TarefaController;
import controller.UsuarioController;

import javax.swing.*;

public class MainTeste {

    public static void main(String[] args) {
        UsuarioController usuarioController = new UsuarioController();
        CategoriaController categoriaController = new CategoriaController();
        TarefaController tarefaController = new TarefaController();

        // Criação do JFrame
        JFrame frame = new JFrame("Gerenciamento de Tarefas");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // Desabilita o botão de fechar

        // Adiciona um listener para o fechamento
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                int confirm = JOptionPane.showConfirmDialog(frame, "Você realmente deseja sair?", "Confirmação", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    System.exit(0); // Encerra a aplicação
                }
            }
        }
    );

        while (true) {
            String[] options = {"Gerenciar Usuários", "Gerenciar Categorias", "Gerenciar Tarefas", "Sair"};
            int opcao = JOptionPane.showOptionDialog(frame, "Menu Principal", "Selecione uma opção",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

            switch (opcao) {
                case 0:
                    menuUsuarios(usuarioController, frame);
                    break;
                case 1:
                    menuCategorias(categoriaController, frame);
                    break;
                case 2:
                    menuTarefas(tarefaController, frame);
                    break;
                case 3:
                    int confirm = JOptionPane.showConfirmDialog(frame, "Você realmente deseja sair?", "Confirmação", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        System.exit(0); // Encerra a aplicação
                    }
                    break;
                default:
                    JOptionPane.showMessageDialog(frame, "Opção inválida! Tente novamente.");
            }
        }
    }

    private static void menuUsuarios(UsuarioController usuarioController, JFrame frame) {
        while (true) {
            String[] options = {"Cadastrar Usuário", "Listar Usuários", "Atualizar Usuário", "Excluir Usuário", "Voltar"};
            int opcao = JOptionPane.showOptionDialog(frame, "Menu de Usuários", "Selecione uma opção",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

            if (opcao == -1) {
                return; // Volta ao menu principal
            }

            switch (opcao) {
                case 0:
                    while (true) {
                        try {
                            usuarioController.saveUsuario();
                            break; // Sai do loop se o cadastro for bem-sucedido
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(frame, "Erro: " + e.getMessage() + "\nTente novamente.");
                        }
                    }
                    break;
                case 1:
                    usuarioController.findAllUsuarios();
                    break;
                case 2:
                    while (true) {
                        try {
                            usuarioController.updateUsuario();
                            break;
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(frame, "Erro: " + e.getMessage() + "\nTente novamente.");
                        }
                    }
                    break;
                case 3:
                    while (true) {
                        try {
                            usuarioController.deleteUsuario();
                            break;
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(frame, "Erro: " + e.getMessage() + "\nTente novamente.");
                        }
                    }
                    break;
                case 4:
                    return; 
                default:
                    JOptionPane.showMessageDialog(frame, "Opção inválida! Tente novamente.");
            }
        }
    }

    private static void menuCategorias(CategoriaController categoriaController, JFrame frame) {
        while (true) {
            String[] options = {"Cadastrar Categoria", "Listar Categorias", "Atualizar Categoria", "Excluir Categoria", "Voltar"};
            int opcao = JOptionPane.showOptionDialog(frame, "Menu de Categorias", "Selecione uma opção",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

            if (opcao == -1) {
                return; 
            }

            switch (opcao) {
                case 0:
                    while (true) {
                        try {
                            categoriaController.saveCategoria();
                            break; 
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(frame, "Erro: " + e.getMessage() + "\nTente novamente.");
                        }
                    }
                    break;
                case 1:
                    categoriaController.findAllCategorias();
                    break;
                case 2:
                    while (true) {
                        try {
                            categoriaController.updateCategoria();
                            break; 
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(frame, "Erro: " + e.getMessage() + "\nTente novamente.");
                        }
                    }
                    break;
                case 3:
                    while (true) {
                        try {
                            categoriaController.deleteCategoria();
                            break; 
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(frame, "Erro: " + e.getMessage() + "\nTente novamente.");
                        }
                    }
                    break;
                case 4:
                    return; 
                default:
                    JOptionPane.showMessageDialog(frame, "Opção inválida! Tente novamente.");
            }
        }
    }

    private static void menuTarefas(TarefaController tarefaController, JFrame frame) {
        while (true) {
            String[] options = {"Cadastrar Tarefa", "Listar Tarefas", "Atualizar Tarefa", "Filtrar Tarefas", "Ordenar Tarefas", "Exibir Relatório de Tarefas", "Voltar"};
            int opcao = JOptionPane.showOptionDialog(frame, "Menu de Tarefas", "Selecione uma opção",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

            if (opcao == -1) {
                return; // Volta ao menu principal
            }

            switch (opcao) {
                case 0:
                    while (true) {
                        try {
                            tarefaController.registerTarefa();// Altere para saveTarefa
                            break; // Sai do loop se o cadastro for bem-sucedido
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(frame, "Erro: " + e.getMessage() + "\nTente novamente.");
                        }
                    }
                    break;
                case 1:
                    tarefaController.findAllTarefas(); // Chama o método de listagem diretamente
                    break;
                case 2:
                    while (true) {
                        try {
                            tarefaController.updateTarefa();
                            break; // Sai do loop se a atualização for bem-sucedida
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(frame, "Erro: " + e.getMessage() + "\nTente novamente.");
                        }
                    }
                    break;
                case 3:
                    tarefaController.findAllStatusTarefas(); // Método para filtrar tarefas por status
                    break;
                case 4:
                    tarefaController.orderByStatsTarefas(); // Método para ordenar tarefas
                    break;
                case 5:
                    tarefaController.displayReportTarefas();// Método para exibir relatório de tarefas
                    break;
                case 6:
                    return; // Volta ao menu principal
                default:
                    JOptionPane.showMessageDialog(frame, "Opção inválida! Tente novamente.");
            }
        }
    }
}
