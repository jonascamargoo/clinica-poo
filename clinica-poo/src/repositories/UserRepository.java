package repositories;

import java.util.ArrayList;
import java.util.List;

import Model.User;
import exceptions.UserNotFoundException;

public class UserRepository implements IUser {
	private List<User> users;
	private long nextId;

	private static UserRepository userRepository;

	public static UserRepository getInstance() {
		if (userRepository == null) {
			userRepository = new UserRepository();
		}
		return userRepository;
	}

	public UserRepository() {
		users = new ArrayList<User>();
		nextId = 1;
	}


	public void add(User user) {
		user.setId(nextId);
		nextId++;
		this.users.add(user);
	}

	// poderia deletar por id diretamente, mas prefiro tratar o caso null apenas no findById. Além disso, nao há tratamento na view, portanto a melhor abordagem para esse caso e achar o user pelo findById e deletar utilizando o objeto em si
	public void delete(long id) {
		User user = findById(id);
		users.remove(user);
	}

	public User findById(long idUser) {
		return users.stream()
			.filter(user -> user.getId() == idUser)
			.findFirst()
			.orElseThrow(() -> new UserNotFoundException());
	}

	public void update(User updatedUser) {
		User user = findById(updatedUser.getId());
		users.set(users.indexOf(user), user);
	}

	public List<User> list() {
		return new ArrayList<User>(this.users);
	}

	public boolean userExists(long idUser) {
		return users.stream()
			.anyMatch(user -> user.getId() == idUser);

	}

}
