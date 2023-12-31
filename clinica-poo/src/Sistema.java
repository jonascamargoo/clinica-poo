import java.util.List;

import Controle.ControleAnamnese;
import Controle.ControlePaciente;
import Controle.ControleUsuario;
import Date.RepositorioAnamneseList;
import Date.RepositorioPacienteList;
import Date.RepositorioUsuarioList;
import Model.Tipo;
import Model.Usuario;
import View.AnamneseView;
import View.MenuView;
import View.PacienteView;
import View.UsuarioView;

public class Sistema {

    private ControleUsuario controleUsuario;
    private ControlePaciente controlePaciente;
    private ControleAnamnese controleAnamnese;
    private MenuView menuView;
    private PacienteView pacienteView;
    private UsuarioView usuarioView;
    private AnamneseView anamneseView;
    private RepositorioUsuarioList repositorioUsuarioList;
    private RepositorioPacienteList repositorioPacienteList;
    private RepositorioAnamneseList repositorioAnamneseList;

    private static Sistema sistema;

    // Singleton
    public static Sistema getInstance() {
        if (sistema == null) {
            sistema = new Sistema();
        }
        return sistema;
    }

    private Sistema() {
        this.pacienteView = new PacienteView();
        this.anamneseView = new AnamneseView();
        this.repositorioUsuarioList = RepositorioUsuarioList.getInstance();
        this.repositorioAnamneseList = RepositorioAnamneseList.getInstance();
        this.repositorioPacienteList = RepositorioPacienteList.getInstance();
        this.controleUsuario = new ControleUsuario(this.repositorioUsuarioList);
        this.controlePaciente = new ControlePaciente(this.repositorioPacienteList);
        this.controleAnamnese = new ControleAnamnese(this.repositorioAnamneseList);
        this.iniciar();
    }

    public Usuario autenticar(String nomeLogin, String senha) {
        List<Usuario> usuarios = repositorioUsuarioList.listar();
        for (Usuario usuario : usuarios) {
            if (usuario.autentica(nomeLogin, senha))
                return usuario;
        }
        return null;
    }

    public void iniciar() {

        menuView = new MenuView();
        // pacienteView = new PacienteView();
        usuarioView = new UsuarioView();
        // anamneseView = new AnamneseView();

        int selecaoMenuPrimario = 0;
        do {
            selecaoMenuPrimario = menuView.menuPrincipal();
            switch (selecaoMenuPrimario) {
                case 1:
                    usuarioView.listarUsuario(controleUsuario.listar());
                    String[] dados = usuarioView.lerUsuario(true);
                    Usuario uLogin = this.autenticar(dados[0], dados[1]);
                    if (uLogin != null) {
                        if (uLogin.getTipo().compareTo(Tipo.ASSISTENTE) == 0) {
                            this.interfaceAssist();
                        } else if (uLogin.getTipo().compareTo(Tipo.MEDICO) == 0) {
                            this.interfaceMed();
                        } else {
                            System.out.println("Usuário de tipo indefinido. Selecione um médico ou assistente");
                        }
                    } else {
                        System.out.println("Usuário não encontrado aqui!");
                    }
                    break;
                case 2:
                    int selecaoMenuCrudUm = menuView.menuUsuario();
                    switch (selecaoMenuCrudUm) {
                        case 1:
                            controleUsuario.add();
                            break;
                        case 2:
                            usuarioView.listarUsuario(controleUsuario.listar());
                            controleUsuario.excluir();
                            break;
                        case 3:
                            usuarioView.listarUsuario(controleUsuario.listar());
                            controleUsuario.alterar();
                            break;
                        case 4:
                            usuarioView.listarUsuario(controleUsuario.listar());
                            break;
                        case 5:
                            break;
                        default:
                            System.out.println("Seleção inválida");
                    }

                case 3:
                    break;
                default:
                    System.out.println("Seleção inválida");
            }
        } while (selecaoMenuPrimario != 3);
    }

    public void interfaceAssist() {
        menuView = new MenuView();
        System.out.println("\nMenu do assistente - selecione uma ação: ");
        int selecaoAssistente = 0;
        do {
            selecaoAssistente = menuView.menuAssistente();
            switch (selecaoAssistente) {
                case 1:
                    controlePaciente.add();
                    break;
                case 2:
                    pacienteView.listarPacientes(controlePaciente.listar());
                    controlePaciente.excluir();
                    break;
                case 3:
                    pacienteView.listarPacientes(controlePaciente.listar());
                    controlePaciente.alterar();
                    break;
                case 4:
                    pacienteView.listarPacientes(controlePaciente.listar());
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Seleção inválida");
                    break;
            }
        } while (selecaoAssistente != 5);
    }

    public void interfaceMed() {
        menuView = new MenuView();
        System.out.println("\nMenu do medico(a) - selecione uma ação: ");
        int selecaoMedico = 0;
        do {
           
            selecaoMedico = menuView.menuMedico();
            switch (selecaoMedico) {
                case 1:
                    controleAnamnese.add();
                    break;
                case 2:
                controleAnamnese.buscarAnamnese();
                    break;
                case 3:
                    controleAnamnese.alterar();
                    break;
                case 4:
                    anamneseView.listarAnamneses(controleAnamnese.listarAnamneses());
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Seleção inválida");
            }
        } while (selecaoMedico != 5);
    }
}