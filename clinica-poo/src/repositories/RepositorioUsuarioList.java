package repositories;

import java.util.ArrayList;
import java.util.List;

import Model.User;
import exceptions.UsuarioNaoEncontradoException;

public class RepositorioUsuarioList implements IRepositorioUsuario {
	private List<User> usuarios;
	private long proxId;

	private static RepositorioUsuarioList repositorioUsuarioList;

	public RepositorioUsuarioList() {
		usuarios = new ArrayList<User>();
		proxId = 1;
	}

	public static RepositorioUsuarioList getInstance() {
		if (repositorioUsuarioList == null) {
			repositorioUsuarioList = new RepositorioUsuarioList();
		}
		return repositorioUsuarioList;
	}

	@Override
	public boolean add(User u) {
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

	public User findUser(long id) {
		for (int i = 0; i < usuarios.size(); i++) {
			if (usuarios.get(i).getId() == id) {
				return usuarios.get(i);
			}
		}
		return null;
	}

	@Override
	public User buscar(long idUsuario) {
		for (User usuario : usuarios) {
			if (usuario.getId() == idUsuario)
				return usuario;
		}
		return null;
	}

	@Override
	public void alterar(User usuarioAlt) throws UsuarioNaoEncontradoException {
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

	public List<User> listar() {
		List<User> usuariosCopia = new ArrayList<User>();
		for (User usuario : usuarios) {
			usuariosCopia.add(new User(usuario));
		}

		return usuariosCopia;
	}

	public boolean usuarioExiste(long id) {
		for (User usuario : usuarios) {
			if (usuario.getId() == id) {
				return true;
			}
		}
		return false;
	}

}
