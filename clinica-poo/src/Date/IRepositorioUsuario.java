package Date;

import java.util.List;
import Date.exceptions.UsuarioNaoEncontradoException;
import Model.User;

public interface IRepositorioUsuario {

	public boolean add(User u);

	public void excluir(long id) throws UsuarioNaoEncontradoException;

	public User buscar(long idUsuario);

	public void alterar(User usuarioAlt) throws UsuarioNaoEncontradoException;

	public User findUser(long id);

	public List<User> listar();

	public boolean usuarioExiste(long id);


}