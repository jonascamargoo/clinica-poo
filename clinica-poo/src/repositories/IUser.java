package repositories;

import java.util.List;

import Model.User;
import exceptions.UserNotFoundException;
import exceptions.UsuarioNaoEncontradoException;

public interface IUser {

	public boolean add(User u);

	public void delete(long idUser) throws UsuarioNaoEncontradoException;

	public User findById(long idUser);

	public void update(User updatedUser) throws UsuarioNaoEncontradoException;

	public List<User> list();

	public boolean userExists(long idUser);


}