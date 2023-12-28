package Date;

import java.util.List;

import Date.exceptions.AnamneseInvalidaException;
import Date.exceptions.AnamneseNaoEncontradaException;
import Model.Anamnese;

public interface IRepositorioAnamnese {
    public void add(Anamnese a) throws AnamneseInvalidaException;
    public List<Anamnese> listar();
	public Anamnese buscar(long idAnamnese);
	public void alterar(Anamnese aNova, long id) throws AnamneseNaoEncontradaException;
    public boolean existeAnamnese(long id);
    public Anamnese buscaAnamnese(String nome, String nomeMae) throws AnamneseNaoEncontradaException;
    public boolean existeHomonimo(String nome);

}