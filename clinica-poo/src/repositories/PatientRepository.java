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

public class PatientRepository implements IPatient {
    private List<Patient> patients;
    private List<Anamnesis> anamneses;
    private long nextId;

    // Storage In memory
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

    public void delete(long numCNS) throws ExcluirPacienteException {
        Patient patient = this.findByCNS(numCNS).get();
        if (patient != null) {
            for (int j = 0; j < this.anamneses.size(); j++) {
                if (this.anamneses.get(j) != null) {
                    if (this.anamneses.get(j).getPatient().getNumCNS() == numCNS) {
                        throw new ExcluirPacienteException("Usuário não pode ser excluído");
                    }
                }
            }
            if (this.patients.contains(patient)) {
                this.patients.remove(patient);
            } else {
                throw new ExcluirPacienteException("Usuário não encontrado");
            }
        } else {
            throw new ExcluirPacienteException("Usuário não encontrado");
        }
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

    public boolean isPatientLinkedToAnamnesis(long id) {
        if (patientExists(id) && anamnesisExists(id)) {
            Patient patient = findByCNS(id).get();
            Anamnesis anamnesis = findAnamnesis(id);
            if (anamnesis.getPatient() == patient) {
                return true;
            }
        }
        return false;
    }

    public Patient findByName(String nome) {
        for (Patient patient : patients) {
            if (patient.getName().equals(nome)) {
                return patient;
            }
        }
        return null;
    }

    public Anamnesis findAnamnesis(long id) {
        for (Anamnesis anamnesis : anamneses) {
            if (anamnesis.getId() == id) {
                return anamnesis;
            }
        }

        return null;
    }

    public boolean anamnesisExists(long id) {
        for (Anamnesis anamnesis : anamneses) {
            if (anamnesis.getId() == id) {
                return true;
            }
        }
        return false;
    }

}
