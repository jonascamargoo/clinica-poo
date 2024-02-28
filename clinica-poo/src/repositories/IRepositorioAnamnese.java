package repositories;

import java.util.List;

import Model.Anamnesis;
import exceptions.AnamneseInvalidaException;
import exceptions.AnamneseNaoEncontradaException;

public interface IRepositorioAnamnese {
    public void add(Anamnesis a) throws AnamneseInvalidaException;
    public List<Anamnesis> listar();
	public Anamnesis buscar(long idAnamnese);
	public void alterar(Anamnesis aNova, long id) throws AnamneseNaoEncontradaException;
    public boolean existeAnamnese(long id);
    public Anamnesis buscaAnamnese(String nome, String nomeMae) throws AnamneseNaoEncontradaException;
    public boolean existeHomonimo(String nome);

}