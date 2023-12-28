package View;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import Model.Tipo;
import Model.Usuario;

public class UsuarioView {
    private Scanner scn;

    public UsuarioView() {
        scn = new Scanner(System.in);
    }

    public Usuario lerUsuario() {
        System.out.print("Nome: ");
        String nome = scn.next();
        nome += scn.nextLine();
        System.out.print("Nome Login: ");
        String nomeLogin = scn.next();
        nome += scn.nextLine();
        System.out.print("Senha: ");
        String senha = scn.next();
        senha += scn.nextLine();
        Tipo tipo = Tipo.INDEFINIDO;
        boolean controller = true;
        while (controller) {
            System.out.print("Tipo: ");
            try {
                String tipoString = scn.next();
                tipoString += scn.nextLine();
                String tipoStringUpper = tipoString.toUpperCase();
                if (tipoStringUpper.equals("ASSISTENTE")) {
                    tipo = Tipo.ASSISTENTE;
                }
                if (tipoStringUpper.equals("MEDICO")) {
                    tipo = Tipo.MEDICO;
                }

                controller = false;
            } catch (InputMismatchException i) {
                System.out.println("Número não deve ser String");
                scn.nextLine();
            }
        }
        Usuario u = Usuario.getInstance(nome, nomeLogin, senha, tipo);
        return u;

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

    public Usuario alterarUsuario() {
        System.out.print("Alteração - Num. ID: ");
        long idUsuarioAntigo = 0;
        try {
            System.out.println("Digite o id correspondente: ");
            idUsuarioAntigo = scn.nextLong();
        } catch (InputMismatchException e) {
            scn.nextLine();
            System.out.println("Num. ID inválido, tente novamente");
        }
        Usuario uNovo = lerUsuario();
        uNovo.setId(idUsuarioAntigo);
        return uNovo;
    }

    public void listarUsuario(List<Usuario> usuarios) {
        System.out.println();
        System.out.printf("%7s%15s%12s", "ID", "NOME", "TIPO\n");
        for (Usuario usuario : usuarios) {
            System.out.printf("%7s%15s%12s", usuario.getId(), usuario.getNome(),
                    usuario.getTipo() + "\n");
        }
        System.out.println("\n");

    }

    public void print(String msg){
        System.out.println(msg);
    }

}
