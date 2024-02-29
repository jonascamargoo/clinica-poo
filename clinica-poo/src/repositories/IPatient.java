package repositories;

import java.util.List;

import Model.Anamnesis;
import Model.Patient;
import exceptions.AlterarPacienteException;
import exceptions.ExcluirPacienteException;
import exceptions.PacienteInvalidoException;

public interface IPatient {
    public void add(Patient patient) throws PacienteInvalidoException;
    public List<Patient> list();
    public void delete(long numCNS) throws ExcluirPacienteException;
	public Patient findById(long idPatient);
	public void update(Patient updatedPatient) throws AlterarPacienteException;
    public Patient findByCNS(long numCNS);
    public boolean isPatientLinkedToAnamnesis(long id);
    public Anamnesis findAnamnesis(long id);
    public boolean patientExists(long cns);
    public Patient findByName(String nome);
    public boolean anamnesisExists(long id);

}