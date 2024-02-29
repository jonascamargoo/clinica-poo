package repositories;

import java.util.ArrayList;
import java.util.List;

import Model.User;
import exceptions.UsuarioNaoEncontradoException;

public class UserRepository implements IUser {
	private List<User> users;
	private long proxId;

	private static UserRepository userRepository;

	public UserRepository() {
		users = new ArrayList<User>();
		proxId = 1;
	}

	public static UserRepository getInstance() {
		if (userRepository == null) {
			userRepository = new UserRepository();
		}
		return userRepository;
	}

	@Override
	public boolean add(User u) {
		if (u == null) {
			return false;
		} else {
			u.setId(proxId);
			proxId++;
			if (users.add(u)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void delete(long id) throws UsuarioNaoEncontradoException {
		if (!userExists(id)) {
			throw new UsuarioNaoEncontradoException("Usuário não existe");
		}
		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).getId() == id) {
				users.remove(i);
			}
		}
	}

	@Override
	public User findById(long idUser) {
		for (User user : users) {
			if (user.getId() == idUser)
				return user;
		}
		return null;
	}

	@Override
	public void update(User updatedUser) throws UsuarioNaoEncontradoException {
		if (updatedUser == null) {
			throw new NullPointerException();
		}
		if (!userExists(updatedUser.getId())) {
			throw new UsuarioNaoEncontradoException("Usuário não existe");
		}
		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).getId() == updatedUser.getId()) {
				users.set(i, updatedUser);
			}
		}
	}

	// isso aqui so pode ser piada
	public List<User> list() {
		List<User> usersCopia = new ArrayList<User>();
		for (User usuario : users) {
			usersCopia.add(new User(usuario));
		}

		return usersCopia;
	}

	public boolean userExists(long idUser) {
		for (User user : users) {
			if (user.getId() == idUser) {
				return true;
			}
		}
		return false;
	}


}
