package Controle;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import Model.Patient;
import Model.Anamnesis;
import Model.Address;
import View.PacienteView;
import enums.Sex;
import exceptions.AlterarPacienteException;
import exceptions.ExcluirPacienteException;
import exceptions.PacienteInvalidoException;
import repositories.IRepositorioAnamnese;
import repositories.IRepositorioPaciente;

public class ControlePaciente {
    private List<Patient> pacientes;
    private PacienteView pacienteView;
    private IRepositorioPaciente repoPaciente;
    private IRepositorioAnamnese repoAnamnese;
    private List<Anamnesis> anamneses;

    public ControlePaciente(IRepositorioPaciente repoPaciente) {
        pacienteView = new PacienteView();
        this.repoPaciente = repoPaciente;
        this.init();
    }

    public void add() {
        Patient p = pacienteView.lerPaciente();
        try {
            repoPaciente.add(p);
        } catch (PacienteInvalidoException e) {
            e.printStackTrace();
        }
    }

    public void excluir() {
        long numCNS = pacienteView.excluirPaciente();
        try {
            repoPaciente.excluir(numCNS);
        } catch (ExcluirPacienteException e) {
            e.printStackTrace();
        }
    }

    public List<Patient> listar() {
        return repoPaciente.listar();
    }

    public void alterar() {
        Patient pAlterado = pacienteView.alterarPaciente();
        try {
            repoPaciente.alterar(pAlterado);
        } catch (AlterarPacienteException e) {
            e.printStackTrace();
        }
    }

    public Patient findByCNS(long cns) {
        return repoPaciente.findByCNS(cns);
    }

    public boolean pacienteAtreladoAnamnese(long id) {
        if (existePaciente(id) && existeAnamnese(id)) {
            Patient p = findByCNS(id);
            Anamnesis a = buscaAnamnese(id);
            if (a.getPaciente() == p) {
                return true;
            }
        }
        return false;
    }

    public boolean existeAnamnese(long id) {
        if (repoPaciente.pacienteAtreladoAnamnese(id)) {
            return true;
        }
        return false;
    }

    public Anamnesis buscaAnamnese(long id) {
        return repoPaciente.buscaAnamnese(id);
    }

    public boolean existePaciente(long cns) {
        if (repoPaciente.existePaciente(cns)) {
            return true;
        }
        return false;
    }

    public Patient findByName(String nome) {
        for (Patient paciente : pacientes) {
            if (paciente.getName().equals(nome)) {
                return paciente;
            }
        }
        return null;
    }

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
                e1, "132459876");
        try {
            repoPaciente.add(p1);
        } catch (PacienteInvalidoException e6) {
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
                "159423687");

        try {
            repoPaciente.add(p2);
        } catch (PacienteInvalidoException e6) {
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
                "132459876");
        p3.add(e3);
        try {
            repoPaciente.add(p3);
        } catch (PacienteInvalidoException e6) {
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
                "987465321");

        try {
            repoPaciente.add(p4);
        } catch (PacienteInvalidoException e6) {
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
                "12345678");
        try {
            repoPaciente.add(p5);
        } catch (PacienteInvalidoException e) {
            e.printStackTrace();
        }
    }

}
