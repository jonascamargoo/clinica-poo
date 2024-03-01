package repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import Model.Anamnesis;
import Model.Patient;
import Model.PatientWithDisability;
import exceptions.AlterarPacienteException;
import exceptions.ExcluirPacienteException;
import exceptions.InvalidPatientException;
import exceptions.LinkedAnamnesisException;
import exceptions.PatientNotFoundException;

public class PatientRepository implements IPatient {
    // Storage In memory
    private List<Patient> patients;
    private IAnamnesis anamnesisRepository;
    //Copia da lista real
    private List<Anamnesis> anamneses = anamnesisRepository.list();
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
        nextId = 1;
    }

    // orElse() em vez de if(x == null)

    //Não deve ser possível inserir mais de um paciente com mesmo nome, nome de mae ou mesmo numCNS
    public void add(Patient patient) throws InvalidPatientException {
        if(duplicateName(patient) || duplicateCNS(patient) || duplicateMotherName(patient))
            throw new InvalidPatientException("Dados inválidos");
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
    public List<Patient> list() {
        List<Patient> patientsCopy = new ArrayList<Patient>();
        for (Patient patient : patients) {
            if (patient instanceof PatientWithDisability) {
                patientsCopy.add(new PatientWithDisability((PatientWithDisability) patient));
            } else {
                patientsCopy.add(new Patient(patient));
            }
        }
        return patientsCopy;
    }

    public void delete(long numCNS) {
        Patient patient = findByCNS(numCNS).orElseThrow(() -> new PatientNotFoundException());
        if(!isPatientLinkedToAnamnesis(numCNS)) {
            throw new LinkedAnamnesisException();
        }
        patients.remove(patient);
    }

    public boolean isPatientLinkedToAnamnesis(long numCNS) {
        return anamneses.stream()
                .anyMatch(anamnesis ->
                    anamnesis.getPatient().getNumCNS() == numCNS
                );
    }

    public void update(Patient updatedPatient) throws AlterarPacienteException {
        if (!patientExists(updatedPatient.getNumCNS())) {
            throw new AlterarPacienteException("Paciente não encontrado");
        }
        for (int i = 0; i < patients.size(); i++) {
            if (patients.get(i) != null) {
                if (patients.get(i).getNumCNS() == updatedPatient.getNumCNS()) {
                    patients.set(i, updatedPatient);
                }
            }
        }
    }

    public Optional<Patient> findByCNS(long numCNS) {
        return patients.stream()
                .filter(patient -> patient.getNumCNS() == numCNS)
                .findFirst();
    }


    public boolean patientExists(long cns) {
        return findByCNS(cns).isPresent();
    }


    public Optional<Patient> findByName(String nome) {
        return patients.stream()
            .filter(patient -> patient.getName().equals(nome))
            .findFirst();
    }

    // Esse metodo busca na lista real?? ou apenas na lista que esta nesse repositorio?
    // public Optional<Anamnesis> findAnamnesis(long id) {
    //     for (Anamnesis anamnesis : anamneses) {
    //         if (anamnesis.getId() == id) {
    //             return anamnesis;
    //         }
    //     }

    //     return null;
    // }





}
