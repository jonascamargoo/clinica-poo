package Date;

import java.util.List;
import Date.exceptions.UsuarioNaoEncontradoException;
import Model.Usuario;

public interface IRepositorioUsuario {

	public boolean add(Usuario u);

	public void excluir(long id) throws UsuarioNaoEncontradoException;

	public Usuario buscar(long idUsuario);

	public void alterar(Usuario usuarioAlt) throws UsuarioNaoEncontradoException;

	public Usuario findUser(long id);

	public List<Usuario> listar();

	public boolean usuarioExiste(long id);


}