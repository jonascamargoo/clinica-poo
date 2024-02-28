package repositories;

import java.util.List;

import Model.Anamnese;
import exceptions.AnamneseInvalidaException;
import exceptions.AnamneseNaoEncontradaException;

public interface IRepositorioAnamnese {
    public void add(Anamnese a) throws AnamneseInvalidaException;
    public List<Anamnese> listar();
	public Anamnese buscar(long idAnamnese);
	public void alterar(Anamnese aNova, long id) throws AnamneseNaoEncontradaException;
    public boolean existeAnamnese(long id);
    public Anamnese buscaAnamnese(String nome, String nomeMae) throws AnamneseNaoEncontradaException;
    public boolean existeHomonimo(String nome);

}