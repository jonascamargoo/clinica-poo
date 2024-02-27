package Controle;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import Model.Paciente;
import Date.IRepositorioAnamnese;
import Date.IRepositorioPaciente;
import Date.exceptions.AlterarPacienteException;
import Date.exceptions.ExcluirPacienteException;
import Date.exceptions.PacienteInvalidoException;
import Model.Anamnese;
import Model.Endereco;
import View.PacienteView;
import enums.Sex;

public class ControlePaciente {
    private List<Paciente> pacientes;
    private PacienteView pacienteView;
    private IRepositorioPaciente repoPaciente;
    private IRepositorioAnamnese repoAnamnese;
    private List<Anamnese> anamneses;

    public ControlePaciente(IRepositorioPaciente repoPaciente) {
        pacienteView = new PacienteView();
        this.repoPaciente = repoPaciente;
        this.init();
    }

    public void add() {
        Paciente p = pacienteView.lerPaciente();
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

    public List<Paciente> listar() {
        return repoPaciente.listar();
    }

    public void alterar() {
        Paciente pAlterado = pacienteView.alterarPaciente();
        try {
            repoPaciente.alterar(pAlterado);
        } catch (AlterarPacienteException e) {
            e.printStackTrace();
        }
    }

    public Paciente findByCNS(long cns) {
        return repoPaciente.findByCNS(cns);
    }

    public boolean pacienteAtreladoAnamnese(long id) {
        if (existePaciente(id) && existeAnamnese(id)) {
            Paciente p = findByCNS(id);
            Anamnese a = buscaAnamnese(id);
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

    public Anamnese buscaAnamnese(long id) {
        return repoPaciente.buscaAnamnese(id);
    }

    public boolean existePaciente(long cns) {
        if (repoPaciente.existePaciente(cns)) {
            return true;
        }
        return false;
    }

    public Paciente findByName(String nome) {
        for (Paciente paciente : pacientes) {
            if (paciente.getNome().equals(nome)) {
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
        Endereco e1 = new Endereco("labuta", "cidadeAlta", "SE", 45);

        Paciente p1 = Paciente.getInstance("Billie", "Ollie", data, Sex.INTERSEX,
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
        Endereco e2 = new Endereco("labuta", "cidadeAlta", "SP", 25);

        Paciente p2 = Paciente.getInstance("Joe", "maeJoe", data, Sex.FEMALE, e2,
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

        Endereco e3 = new Endereco("labuta", "cidadeAlta", "PA", 25);

        Paciente p3 = Paciente.getInstance("Joanne", "maeJoane", data, Sex.FEMALE,
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

        Endereco e4 = new Endereco("labuta", "cidadeAlta", "MA", 25);

        Paciente p4 = Paciente.getInstance("Kayne", "maeKayne", data, Sex.FEMALE,
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

        Endereco e5 = new Endereco("labuta", "cidadeAlta", "MG", 25);
        Paciente p5 = Paciente.getInstance("Kayne", "maeJoao", data, Sex.FEMALE,
                e5,
                "12345678");
        try {
            repoPaciente.add(p5);
        } catch (PacienteInvalidoException e) {
            e.printStackTrace();
        }
    }

}
