package repositories;

import java.util.List;

import Model.Anamnesis;
import exceptions.AnamneseInvalidaException;
import exceptions.AnamneseNaoEncontradaException;

public interface IAnamnesis {
    public void add(Anamnesis anamnesis) throws AnamneseInvalidaException;
    public List<Anamnesis> list();
	public Anamnesis findById(long idAnamnesis);
	public void update(Anamnesis newAnamnesis, long idAnamnesis) throws AnamneseNaoEncontradaException;
    public boolean anamnesisExists(long idAnamnesis);
    public Anamnesis findAnamnesisByMotherName(String name, String motherName) throws AnamneseNaoEncontradaException;
    public boolean homonymExists(String name);

}