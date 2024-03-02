package repositories;

import java.util.List;

import Model.Anamnesis;
import exceptions.AnamnesisNotFoundException;

public interface IAnamnesis {
    public void add(Anamnesis anamnesis);
    public List<Anamnesis> list();
	public Anamnesis findById(long idAnamnesis) throws AnamnesisNotFoundException;
	public void update(Anamnesis newAnamnesis);
}