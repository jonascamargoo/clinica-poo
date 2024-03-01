package controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import Model.Patient;
import Model.Anamnesis;
import Model.Address;
import View.PatientView;
import enums.Sex;
import exceptions.AlterarPacienteException;
import exceptions.ExcluirPacienteException;
import exceptions.InvalidPatientException;
import repositories.IPatient;

// OS NULOS DEVEM SER TRATADOS AQUI UTILIZAMOS OS METODOS DO OPTIONAL
public class PatientController {
    private PatientView pacienteView;
    private IPatient patientRepository;

    public PatientController(IPatient patientRepository) {
        pacienteView = new PatientView();
        this.patientRepository = patientRepository;
        this.init();
    }

    public void add() {
        Patient p = pacienteView.patientRead();
        try {
            patientRepository.add(p);
        } catch (InvalidPatientException e) {
            e.printStackTrace();
        }
    }

    public void excluir() {
        long numCNS = pacienteView.patientDelete();
        try {
            patientRepository.delete(numCNS);
        } catch (ExcluirPacienteException e) {
            e.printStackTrace();
        }
    }

    public List<Patient> listar() {
        return patientRepository.list();
    }

    public void alterar() {
        Patient updatedPatient = pacienteView.patientUpdate();
        try {
            patientRepository.update(updatedPatient);
        } catch (AlterarPacienteException e) {
            e.printStackTrace();
        }
    }

    // Utilizar o orElseThrow??
    public Patient findByCNS(long cns) {
        return patientRepository.findByCNS(cns).orElseThrow();
    }


    // public boolean existeAnamnese(long id) {
    //     if (patientRepository.isPatientLinkedToAnamnesis(id)) {
    //         return true;
    //     }
    //     return false;
    // }

    public void init() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date data = new Date();

        try {
            data = sdf.parse("05/01/1965");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Address e1 = new Address("labuta", "cidadeAlta", "SE", "45");

        Patient p1 = Patient.getInstance("Billie", "Ollie", data, Sex.INTERSEX,
                e1, "132459876").get();
        try {
            patientRepository.add(p1);
        } catch (InvalidPatientException e6) {
            e6.printStackTrace();
        }

        // Paciente 2

        try {
            data = sdf.parse("04/01/1965");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Address e2 = new Address("labuta", "cidadeAlta", "SP", "25");

        Patient p2 = Patient.getInstance("Joe", "maeJoe", data, Sex.FEMALE, e2,
                "159423687").get();

        try {
            patientRepository.add(p2);
        } catch (InvalidPatientException e6) {
            e6.printStackTrace();
        }

        // Paciente 3 Joanne

        try {
            data = sdf.parse("04/01/1965");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Address e3 = new Address("labuta", "cidadeAlta", "PA", "25");

        Patient p3 = Patient.getInstance("Joanne", "maeJoane", data, Sex.FEMALE,
                e3,
                "132459876").get();
        p3.setAddress(e3);
        try {
            patientRepository.add(p3);
        } catch (InvalidPatientException e6) {
            e6.printStackTrace();
        }

        // Paciente 4 Kayne

        try {
            data = sdf.parse("04/01/1965");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Address e4 = new Address("labuta", "cidadeAlta", "MA", "25");

        Patient p4 = Patient.getInstance("Kayne", "maeKayne", data, Sex.FEMALE,
                e4,
                "987465321").get();

        try {
            patientRepository.add(p4);
        } catch (InvalidPatientException e6) {
            e6.printStackTrace();
        }

        // Paciente 5 Joao

        try {
            data = sdf.parse("04/01/1965");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Address e5 = new Address("labuta", "cidadeAlta", "MG", "25");
        Patient p5 = Patient.getInstance("Kayne", "maeJoao", data, Sex.FEMALE,
                e5,
                "12345678").get();
        try {
            patientRepository.add(p5);
        } catch (InvalidPatientException e) {
            e.printStackTrace();
        }
    }

}
