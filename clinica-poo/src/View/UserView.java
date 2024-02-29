package View;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import Model.User;
import enums.Type;

public class UserView {
    private Scanner scn;

    public UserView() {
        scn = new Scanner(System.in);
    }

    public User userRead() {
        System.out.print("Nome: ");
        String name = scn.next();
        name += scn.nextLine();
        System.out.print("Nome Login: ");
        String loginName = scn.next();
        name += scn.nextLine();
        System.out.print("Senha: ");
        String password = scn.next();
        password += scn.nextLine();
        Type type = Type.UNDEFINED;
        boolean control = true;
        while (control) {
            System.out.print("Tipo: ");
            try {
                String typeString = scn.next();
                typeString += scn.nextLine();
                String typeStringUpper = typeString.toUpperCase();
                if (typeStringUpper.equals("ASSISTENTE")) {
                    type = Type.ASSISTANT;
                }
                if (typeStringUpper.equals("MEDICO")) {
                    type = Type.DOCTOR;
                }

                control = false;
            } catch (InputMismatchException i) {
                System.out.println("Número não deve ser String");
                scn.nextLine();
            }
        }
        User user = User.getInstance(name, loginName, password, type).orElseThrow();
        return user;
    }

    public String[] userRead(boolean autentica) {
        String[] data = new String[2];
        System.out.print("Nome Login: ");
        String loginName = scn.next();
        loginName += scn.nextLine();
        data[0] = loginName;
        System.out.print("Senha: ");
        String password = scn.next();
        password += scn.nextLine();
        data[1] = password;

        return data;

    }

    public long userDelete() {
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

    public User userUpdate() {
        System.out.print("Alteração - Num. ID: ");
        long idUsuarioAntigo = 0;
        try {
            System.out.println("Digite o id correspondente: ");
            idUsuarioAntigo = scn.nextLong();
        } catch (InputMismatchException e) {
            scn.nextLine();
            System.out.println("Num. ID inválido, tente novamente");
        }
        User uNovo = userRead();
        uNovo.setId(idUsuarioAntigo);
        return uNovo;
    }

    public void userList(List<User> usuarios) {
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
