package repositories;

import java.util.ArrayList;
import java.util.List;

import Model.Anamnese;
import Model.Patient;
import Model.PatientWithDisability;
import exceptions.AlterarPacienteException;
import exceptions.ExcluirPacienteException;
import exceptions.PacienteInvalidoException;

public class RepositorioPacienteList implements IRepositorioPaciente {
    private List<Patient> pacientes;
    private List<Anamnese> anamneses;
    private long proxId;

    private static RepositorioPacienteList repositorioPacienteList;

    public static RepositorioPacienteList getInstance() {
        if (repositorioPacienteList == null) {
            repositorioPacienteList = new RepositorioPacienteList();

        }
        return repositorioPacienteList;
    }

    protected RepositorioPacienteList() {
        pacientes = new ArrayList<Patient>();
        proxId = 1;
    }

    public void add(Patient p) throws PacienteInvalidoException {
        if (p == null) {
            throw new PacienteInvalidoException("Dados inválidos");
        }
        for (int i = 0; i < this.pacientes.size(); i++) {
            // checagem do requisito 'Não deve ser possível inserir mais de um paciente com
            // o mesmo nome e mesmo nome de mãe'
            if (this.pacientes.get(i) != null) {
                if (this.pacientes.get(i).getName().equals(p.getName())
                        && this.pacientes.get(i).getNomeMae().equals(p.getNomeMae())) {
                    throw new PacienteInvalidoException("Dados inválidos");
                }
                // CNS deve ser único
                if (this.pacientes.get(i).getNumCNS() == p.getNumCNS()) {
                    throw new PacienteInvalidoException("Dados inválidos");
                }
            }
        }

        this.pacientes.add(p);

    }

    public List<Patient> listar() {
        List<Patient> pacientesCopia = new ArrayList<Patient>();
        for (Patient paciente : pacientes) {
            if (paciente instanceof PatientWithDisability) {
                pacientesCopia.add(new PatientWithDisability((PatientWithDisability) paciente));
            } else {
                pacientesCopia.add(new Patient(paciente));
            }
        }

        return pacientesCopia;
    }

    public void excluir(long numCNS) throws ExcluirPacienteException {
        Patient p = this.findByCNS(numCNS);
        if (p != null) {
            for (int j = 0; j < this.anamneses.size(); j++) {
                if (this.anamneses.get(j) != null) {
                    if (this.anamneses.get(j).getPaciente().getNumCNS() == numCNS) {
                        throw new ExcluirPacienteException("Usuário não pode ser excluído");
                    }
                }
            }
            if (this.pacientes.contains(p)) {
                this.pacientes.remove(p);
            } else {
                throw new ExcluirPacienteException("Usuário não encontrado");
            }
        } else {
            throw new ExcluirPacienteException("Usuário não encontrado");
        }

    }

    public void alterar(Patient pAlterado) throws AlterarPacienteException {
        if (!existePaciente(pAlterado.getNumCNS())) {
            throw new AlterarPacienteException("Paciente não encontrado");
        }
        for (int i = 0; i < pacientes.size(); i++) {
            if (pacientes.get(i) != null) {
                if (pacientes.get(i).getNumCNS() == pAlterado.getNumCNS()) {
                    pacientes.set(i, pAlterado);
                }
            }
        }
    }

    public Patient findByCNS(long numCNS) {
        for (int i = 0; i < pacientes.size(); i++) {
            if (pacientes.get(i) != null) {
                if (pacientes.get(i).getNumCNS() == numCNS) {
                    return pacientes.get(i);
                }
            }
        }
        return null;
    }

    public Patient buscar(long idPaciente) {
        for (int i = 0; i < pacientes.size(); i++) {
            if (pacientes.get(i).getNumCNS() == idPaciente) {
                return pacientes.get(i);
            }
        }
        return null;
    }

    public boolean existePaciente(long cns) {
        for (Patient paciente : pacientes) {
            if (paciente.getNumCNS() == cns) {
                return true;
            }
        }
        return false;
    }

    public boolean pacienteAtreladoAnamnese(long id) {
        if (existePaciente(id) && existeAnamnese(id)) {
            Patient p = findByCNS(id);
            Anamnese a = buscaAnamnese(id);
            if (a.getPaciente() == p) {
                return true;
            }
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

    public Anamnese buscaAnamnese(long id) {
        for (Anamnese anamnese : anamneses) {
            if (anamnese.getId() == id) {
                return anamnese;
            }
        }

        return null;
    }

    public boolean existeAnamnese(long id) {
        for (Anamnese anamnese : anamneses) {
            if (anamnese.getId() == id) {
                return true;
            }
        }
        return false;
    }

}
