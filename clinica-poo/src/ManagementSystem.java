import java.util.List;

import Model.User;
import View.AnamneseView;
import View.MenuView;
import View.PatientView;
import View.UserView;
import controllers.AnamnesisController;
import controllers.PatientController;
import controllers.UserController;
import enums.Type;
import repositories.AnamnesisRepository;
import repositories.IAnamnesis;
import repositories.IPatient;
import repositories.IUser;
import repositories.PatientRepository;
import repositories.UserRepository;

public class ManagementSystem {

    private UserController userController;
    private PatientController patientController;
    private AnamnesisController anamnesisController;

    private MenuView menuView;
    private PatientView patientView;
    private UserView userView;
    private AnamneseView anamnesisView;

    private IUser userRepository;
    private IPatient patientRepository;
    private IAnamnesis anamnesisRepository;

    private static ManagementSystem system;

    // Singleton
    public static ManagementSystem getInstance() {
        if (system == null) {
            system = new ManagementSystem();
        }
        return system;
    }

    private ManagementSystem() {
        this.patientView = new PatientView();
        this.anamnesisView = new AnamneseView();
        this.userRepository = UserRepository.getInstance();
        this.anamnesisRepository = AnamnesisRepository.getInstance();
        this.patientRepository = PatientRepository.getInstance();
        this.userController = new UserController(this.userRepository);
        this.patientController = new PatientController(this.patientRepository);
        this.anamnesisController = new AnamnesisController(this.anamnesisRepository);
        this.iniciar();
    }

    public User autenticar(String loginName, String password) {
        List<User> users = userRepository.list();
        for (User user : users) {
            if (user.auth(loginName, password))
                return user;
        }
        return null;
    }

    public void iniciar() {
        menuView = new MenuView();
        // patientView = new patientView();
        userView = new UserView();
        // anamneseView = new AnamneseView();

        int selecaoMenuPrimario = 0;
        do {
            selecaoMenuPrimario = menuView.menuPrincipal();
            switch (selecaoMenuPrimario) {
                case 1:
                    userView.userList(userController.list());
                    String[] dados = userView.userRead(true);
                    User uLogin = this.autenticar(dados[0], dados[1]);
                    if (uLogin != null) {
                        if (uLogin.getType().compareTo(Type.ASSISTANT) == 0) {
                            this.interfaceAssist();
                        } else if (uLogin.getType().compareTo(Type.DOCTOR) == 0) {
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
                            userController.add();
                            break;
                        case 2:
                            userView.userList(userController.list());
                            userController.delete();
                            break;
                        case 3:
                            userView.userList(userController.list());
                            userController.update();
                            break;
                        case 4:
                            userView.userList(userController.list());
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
                    patientController.add();
                    break;
                case 2:
                    patientView.patientList(patientController.list());
                    patientController.delete();
                    break;
                case 3:
                    patientView.patientList(patientController.list());
                    patientController.update();
                    break;
                case 4:
                    patientView.patientList(patientController.list());
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
                    anamnesisController.add();
                    break;
                case 2:
                anamnesisController.buscarAnamnese();
                    break;
                case 3:
                    anamnesisController.update();
                    break;
                case 4:
                    anamnesisView.listAll(anamnesisController.list());
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Seleção inválida");
            }
        } while (selecaoMedico != 5);
    }
}