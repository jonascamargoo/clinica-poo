package repositories;

import java.util.List;

import Model.User;
import exceptions.UserNotFoundException;

public interface IUser {
	public void add(User u);
	public void delete(long idUser);
	public User findById(long idUser) throws UserNotFoundException;
	public void update(User updatedUser);
	public List<User> list();
	public boolean userExists(long idUser);
}