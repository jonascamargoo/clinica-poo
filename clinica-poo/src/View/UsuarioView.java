package View;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import Model.Type;
import Model.User;

public class UsuarioView {
    private Scanner scn;

    public UsuarioView() {
        scn = new Scanner(System.in);
    }

    public User lerUsuario() {
        System.out.print("Nome: ");
        String nome = scn.next();
        nome += scn.nextLine();
        System.out.print("Nome Login: ");
        String nomeLogin = scn.next();
        nome += scn.nextLine();
        System.out.print("Senha: ");
        String senha = scn.next();
        senha += scn.nextLine();
        Type tipo = Type.INDEFINIDO;
        boolean controller = true;
        while (controller) {
            System.out.print("Tipo: ");
            try {
                String tipoString = scn.next();
                tipoString += scn.nextLine();
                String tipoStringUpper = tipoString.toUpperCase();
                if (tipoStringUpper.equals("ASSISTENTE")) {
                    tipo = Type.ASSISTENTE;
                }
                if (tipoStringUpper.equals("MEDICO")) {
                    tipo = Type.MEDICO;
                }

                controller = false;
            } catch (InputMismatchException i) {
                System.out.println("Número não deve ser String");
                scn.nextLine();
            }
        }
        // User u = User.getInstance(nome, nomeLogin, senha, tipo);
        User u = User.getInstance(nome, nomeLogin, senha, tipo).orElseThrow();
        return u;

        // public Place getPlaceById(UUID id) {
        //     return placeRepository.findById(id).orElseThrow(PlaceNotFoundException::new);
        // }

    }

    public String[] lerUsuario(boolean autentica) {
        String[] dados = new String[2];
        System.out.print("Nome Login: ");
        String nomeLogin = scn.next();
        nomeLogin += scn.nextLine();
        dados[0] = nomeLogin;
        System.out.print("Senha: ");
        String senha = scn.next();
        senha += scn.nextLine();
        dados[1] = senha;

        return dados;

    }

    public long excluirUsuario() {
        long id = -1;
        System.out.println("Exclusão: Qual o id do usuário?");
        try {
            id = scn.nextLong();
        } catch (Exception e) {
            scn.nextLine();
            System.out.println("Por favor, digite um número");
        }

        return id;
    }

    public User alterarUsuario() {
        System.out.print("Alteração - Num. ID: ");
        long idUsuarioAntigo = 0;
        try {
            System.out.println("Digite o id correspondente: ");
            idUsuarioAntigo = scn.nextLong();
        } catch (InputMismatchException e) {
            scn.nextLine();
            System.out.println("Num. ID inválido, tente novamente");
        }
        User uNovo = lerUsuario();
        uNovo.setId(idUsuarioAntigo);
        return uNovo;
    }

    public void listarUsuario(List<User> usuarios) {
        System.out.println();
        System.out.printf("%7s%15s%12s", "ID", "NOME", "TIPO\n");
        for (User usuario : usuarios) {
            System.out.printf("%7s%15s%12s", usuario.getId(), usuario.getName(),
                    usuario.getType() + "\n");
        }
        System.out.println("\n");

    }

    public void print(String msg){
        System.out.println(msg);
    }

}
