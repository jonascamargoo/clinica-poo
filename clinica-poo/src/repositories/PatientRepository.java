package repositories;

import java.util.ArrayList;
import java.util.List;

import Model.Anamnesis;
import Model.Patient;
import Model.PatientWithDisability;
import exceptions.AlterarPacienteException;
import exceptions.ExcluirPacienteException;
import exceptions.PacienteInvalidoException;

public class PatientRepository implements IPatientRepository {
    private List<Patient> patients;
    private List<Anamnesis> anamneses;
    private long proxId;

    private static PatientRepository patientRepository;

    public static PatientRepository getInstance() {
        if (patientRepository == null) {
            patientRepository = new PatientRepository();

        }
        return patientRepository;
    }

    protected PatientRepository() {
        patients = new ArrayList<Patient>();
        proxId = 1;
    }

    public void add(Patient patient) throws PacienteInvalidoException {
        if (patient == null) {
            throw new PacienteInvalidoException("Dados inválidos");
        }
        for (int i = 0; i < this.patients.size(); i++) {
            // checagem do requisito 'Não deve ser possível inserir mais de um paciente com
            // o mesmo nome e mesmo nome de mãe'
            if (this.patients.get(i) != null) {
                if (this.patients.get(i).getName().equals(patient.getName())
                        && this.patients.get(i).getMotherName().equals(patient.getMotherName())) {
                    throw new PacienteInvalidoException("Dados inválidos");
                }
                // CNS deve ser único
                if (this.patients.get(i).getNumCNS() == patient.getNumCNS()) {
                    throw new PacienteInvalidoException("Dados inválidos");
                }
            }
        }

        this.patients.add(patient);

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
        Patient patient = this.findByCNS(numCNS);
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

    public Patient findByCNS(long numCNS) {
        for (int i = 0; i < patients.size(); i++) {
            if (patients.get(i) != null) {
                if (patients.get(i).getNumCNS() == numCNS) {
                    return patients.get(i);
                }
            }
        }
        return null;
    }

    public Patient findById(long idPatient) {
        for (int i = 0; i < patients.size(); i++) {
            if (patients.get(i).getNumCNS() == idPatient) {
                return patients.get(i);
            }
        }
        return null;
    }

    public boolean patientExists(long cns) {
        for (Patient paciente : patients) {
            if (paciente.getNumCNS() == cns) {
                return true;
            }
        }
        return false;
    }

    // Faz sentido o uso de id?
    public boolean isPatientLinkedToAnamnesis(long id) {
        if (patientExists(id) && anamnesisExists(id)) {
            Patient patient = findByCNS(id);
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
