package repositories;

import java.util.List;

import Model.Patient;
import exceptions.InvalidPatientException;
import exceptions.LinkedAnamnesisException;
import exceptions.PatientNotFoundException;

public interface IPatient {
    public void add(Patient patient) throws InvalidPatientException;
    public List<Patient> list();
    public void delete(long numCNS) throws LinkedAnamnesisException;
	public void update(Patient updatedPatient);
    public Patient findByCNS(long numCNS)throws PatientNotFoundException ;
    public Patient findByName(String nome) throws PatientNotFoundException;

}