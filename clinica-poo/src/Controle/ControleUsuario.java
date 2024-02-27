package Controle;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import Date.IRepositorioUsuario;
import Date.exceptions.UsuarioNaoEncontradoException;
import Model.User;
import View.UsuarioView;
import enums.Type;

public class ControleUsuario {

    private UsuarioView usuarioView;
    private IRepositorioUsuario repoUsuario;

    public ControleUsuario(IRepositorioUsuario repoUsuario) {
        usuarioView = new UsuarioView();
        this.repoUsuario = repoUsuario;
        this.init();
    }

    public boolean add() {
        User u = usuarioView.lerUsuario();
        if (repoUsuario.add(u)) {
            return true;
        }
        return false;

    }

    public boolean excluir() {
        long id = usuarioView.excluirUsuario();
        try {
            repoUsuario.excluir(id);
        } catch (UsuarioNaoEncontradoException e) {
            e.printStackTrace();
        }

        return false;
    }

    public User findUser(long id) {
        return repoUsuario.findUser(id);
    }

    public void alterar() {
        User usuarioAlt = usuarioView.alterarUsuario();
        try {
            repoUsuario.alterar(usuarioAlt);
        } catch (UsuarioNaoEncontradoException e) {
            e.printStackTrace();
        }

    }

    public List<User> listar() {
        return repoUsuario.listar();
    }

    public boolean usuarioExiste(long id) {
        if (repoUsuario.usuarioExiste(id)) {
            return true;
        }
        return false;
    }

    public void init() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date data = new Date();

        // USUARIOS
        // Usuario 1 vladvostok - médico

        User u1 = User.getInstance("vladvostok v", "vlad123", "vladSenha",
                Type.DOCTOR).orElseThrow();
        repoUsuario.add(u1);

        // Usuario 2 salim - assistente

        User u2 = User.getInstance("Salim s", "salim123", "salimSenha",
                Type.ASSISTANT).orElseThrow();
        repoUsuario.add(u2);
    }
}
