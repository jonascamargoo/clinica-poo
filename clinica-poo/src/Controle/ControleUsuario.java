package Controle;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import Date.IRepositorioUsuario;
import Date.exceptions.UsuarioNaoEncontradoException;
import Model.Tipo;
import Model.Usuario;
import View.UsuarioView;

public class ControleUsuario {

    private UsuarioView usuarioView;
    private IRepositorioUsuario repoUsuario;

    public ControleUsuario(IRepositorioUsuario repoUsuario) {
        usuarioView = new UsuarioView();
        this.repoUsuario = repoUsuario;
        this.init();
    }

    public boolean add() {
        Usuario u = usuarioView.lerUsuario();
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

    public Usuario findUser(long id) {
        return repoUsuario.findUser(id);
    }

    public void alterar() {
        Usuario usuarioAlt = usuarioView.alterarUsuario();
        try {
            repoUsuario.alterar(usuarioAlt);
        } catch (UsuarioNaoEncontradoException e) {
            e.printStackTrace();
        }

    }

    public List<Usuario> listar() {
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

        Usuario u1 = Usuario.getInstance("vladvostok v", "vlad123", "vladSenha",
                Tipo.MEDICO);
        repoUsuario.add(u1);

        // Usuario 2 salim - assistente

        Usuario u2 = Usuario.getInstance("Salim s", "salim123", "salimSenha",
                Tipo.ASSISTENTE);
        repoUsuario.add(u2);
    }
}
