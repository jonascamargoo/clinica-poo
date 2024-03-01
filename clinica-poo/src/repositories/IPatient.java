package repositories;

import java.util.List;
import java.util.Optional;

import Model.Anamnesis;
import Model.Patient;
import exceptions.AlterarPacienteException;
import exceptions.ExcluirPacienteException;
import exceptions.InvalidPatientException;

public interface IPatient {
    public void add(Patient patient) throws InvalidPatientException;
    public List<Patient> list();
    public void delete(long numCNS) throws ExcluirPacienteException;
    // nao precisa achar por Id, pois o numCNS eh o id
	// public Optional<Patient> findById(long idPatient);
	public void update(Patient updatedPatient) throws AlterarPacienteException;
    public Optional<Patient> findByCNS(long numCNS);
    public boolean isPatientLinkedToAnamnesis(long id);
    public Optional<Anamnesis> findAnamnesis(long id);
    public boolean patientExists(long cns);
    public Optional<Patient> findByName(String nome);
    public boolean anamnesisExists(long id);

}