package Date;

import java.util.ArrayList;
import java.util.List;
import Date.exceptions.UsuarioNaoEncontradoException;
import Model.Usuario;

public class RepositorioUsuarioList implements IRepositorioUsuario {
	private List<Usuario> usuarios;
	private long proxId;

	private static RepositorioUsuarioList repositorioUsuarioList;

	public RepositorioUsuarioList() {
		usuarios = new ArrayList<Usuario>();
		proxId = 1;
	}

	public static RepositorioUsuarioList getInstance() {
		if (repositorioUsuarioList == null) {
			repositorioUsuarioList = new RepositorioUsuarioList();
		}
		return repositorioUsuarioList;
	}

	@Override
	public boolean add(Usuario u) {
		if (u == null) {
			return false;
		} else {
			u.setId(proxId);
			proxId++;
			if (usuarios.add(u)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void excluir(long id) throws UsuarioNaoEncontradoException {
		if (!usuarioExiste(id)) {
			throw new UsuarioNaoEncontradoException("Usuário não existe");
		}
		for (int i = 0; i < usuarios.size(); i++) {
			if (usuarios.get(i).getId() == id) {
				usuarios.remove(i);
			}
		}
	}

	public Usuario findUser(long id) {
		for (int i = 0; i < usuarios.size(); i++) {
			if (usuarios.get(i).getId() == id) {
				return usuarios.get(i);
			}
		}
		return null;
	}

	@Override
	public Usuario buscar(long idUsuario) {
		for (Usuario usuario : usuarios) {
			if (usuario.getId() == idUsuario)
				return usuario;
		}
		return null;
	}

	@Override
	public void alterar(Usuario usuarioAlt) throws UsuarioNaoEncontradoException {
		if (usuarioAlt == null) {
			throw new NullPointerException();
		}
		if (!usuarioExiste(usuarioAlt.getId())) {
			throw new UsuarioNaoEncontradoException("Usuário não existe");
		}
		for (int i = 0; i < usuarios.size(); i++) {
			if (usuarios.get(i).getId() == usuarioAlt.getId()) {
				usuarios.set(i, usuarioAlt); // substitui o objeto na posicao i
			}
		}

	}

	public List<Usuario> listar() {
		List<Usuario> usuariosCopia = new ArrayList<Usuario>();
		for (Usuario usuario : usuarios) {
			usuariosCopia.add(new Usuario(usuario));
		}

		return usuariosCopia;
	}

	public boolean usuarioExiste(long id) {
		for (Usuario usuario : usuarios) {
			if (usuario.getId() == id) {
				return true;
			}
		}
		return false;
	}

}
