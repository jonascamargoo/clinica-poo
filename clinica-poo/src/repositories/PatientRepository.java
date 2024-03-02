package repositories;

import java.util.ArrayList;
import java.util.List;

import Model.Anamnesis;
import Model.Patient;
import exceptions.InvalidPatientException;
import exceptions.LinkedAnamnesisException;
import exceptions.PatientNotFoundException;

public class PatientRepository implements IPatient {
    // Storage In memory
    private List<Patient> patients;
    private IAnamnesis anamnesisRepository;
    //Copia da lista real
    private List<Anamnesis> anamneses;
    private long nextId;

    // pripriedade para criacao do metodo fabrica
    private static PatientRepository patientRepository;

    public static PatientRepository getInstance() {
        if (patientRepository == null) {
            patientRepository = new PatientRepository();
        }
        return patientRepository;
    }

    protected PatientRepository() {
        patients = new ArrayList<Patient>();
        anamnesisRepository = AnamnesisRepository.getInstance();
        anamneses = anamnesisRepository.list();
        nextId = 1;
    }

    public void add(Patient patient) {
        if (duplicateName(patient))
            throw new InvalidPatientException("paciente com o mesmo nome.");
        if (duplicateMotherName(patient))
            throw new InvalidPatientException("paciente com o mesmo nome da mãe.");
        if (duplicateCNS(patient)) 
            throw new InvalidPatientException("paciente com o mesmo número de CNS.");
        this.patients.add(patient);
    }

    private boolean duplicateName(Patient patient) {
        return patients.stream()
            .anyMatch(p -> p.getName().equals(patient.getName()));
    }

    private boolean duplicateMotherName(Patient patient) {
        return patients.stream()
            .anyMatch(p -> p.getMotherName().equals(patient.getMotherName()));
    }

    private boolean duplicateCNS(Patient patient) {
        return patients.stream()
            .anyMatch(p -> p.getNumCNS() == patient.getNumCNS());
    }

    // Imutabilidade - irei retornar a copia da lista para nao comprometer meu repositorio in memory
    // public List<Patient> list() {
    //     return patients.stream()
    //         .map(patient -> (patient instanceof PatientWithDisability) 
    //             ? new PatientWithDisability((PatientWithDisability) patient) 
    //             : new Patient(patient))
    //         .collect(Collectors.toList());
    // }

    public void delete(long numCNS) {
        Patient patient = findByCNS(numCNS);
        if(isPatientLinkedToAnamnesis(numCNS))
            throw new LinkedAnamnesisException();
        patients.remove(patient);
    }

    public boolean isPatientLinkedToAnamnesis(long numCNS) {
        return anamneses.stream().anyMatch(anamnesis ->
                anamnesis.getPatient().getNumCNS() == numCNS
            );
    }

    public void update(Patient updatedPatient) {
        Patient existingPatient = findByCNS(updatedPatient.getNumCNS());
        patients.set(patients.indexOf(existingPatient), updatedPatient);
    }

    public Patient findByCNS(long numCNS) throws PatientNotFoundException {
        return patients.stream()
                .filter(patient -> patient.getNumCNS() == numCNS)
                .findFirst()
                .orElseThrow(() -> new PatientNotFoundException());
        
    }

    public Patient findByName(String nome) {
        return patients.stream()
            .filter(patient -> patient.getName().equals(nome))
            .findFirst()
            .orElseThrow(() -> new PatientNotFoundException());

    }

    @Override //retornando copia
    public List<Patient> list() {
        return new ArrayList<>(this.patients);
    }


    

}
