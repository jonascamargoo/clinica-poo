package Date;

import java.util.ArrayList;
import java.util.List;

import Date.exceptions.AlterarPacienteException;
import Date.exceptions.ExcluirPacienteException;
import Date.exceptions.PacienteInvalidoException;
import Model.Anamnese;
import Model.Paciente;
import Model.PacienteComDeficiencia;

public class RepositorioPacienteList implements IRepositorioPaciente {
    private List<Paciente> pacientes;
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
        pacientes = new ArrayList<Paciente>();
        proxId = 1;
    }

    public void add(Paciente p) throws PacienteInvalidoException {
        if (p == null) {
            throw new PacienteInvalidoException("Dados inválidos");
        }
        for (int i = 0; i < this.pacientes.size(); i++) {
            // checagem do requisito 'Não deve ser possível inserir mais de um paciente com
            // o mesmo nome e mesmo nome de mãe'
            if (this.pacientes.get(i) != null) {
                if (this.pacientes.get(i).getNome().equals(p.getNome())
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

    public List<Paciente> listar() {
        List<Paciente> pacientesCopia = new ArrayList<Paciente>();
        for (Paciente paciente : pacientes) {
            if (paciente instanceof PacienteComDeficiencia) {
                pacientesCopia.add(new PacienteComDeficiencia((PacienteComDeficiencia) paciente));
            } else {
                pacientesCopia.add(new Paciente(paciente));
            }
        }

        return pacientesCopia;
    }

    public void excluir(long numCNS) throws ExcluirPacienteException {
        Paciente p = this.findByCNS(numCNS);
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

    public void alterar(Paciente pAlterado) throws AlterarPacienteException {
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

    public Paciente findByCNS(long numCNS) {
        for (int i = 0; i < pacientes.size(); i++) {
            if (pacientes.get(i) != null) {
                if (pacientes.get(i).getNumCNS() == numCNS) {
                    return pacientes.get(i);
                }
            }
        }
        return null;
    }

    public Paciente buscar(long idPaciente) {
        for (int i = 0; i < pacientes.size(); i++) {
            if (pacientes.get(i).getNumCNS() == idPaciente) {
                return pacientes.get(i);
            }
        }
        return null;
    }

    public boolean existePaciente(long cns) {
        for (Paciente paciente : pacientes) {
            if (paciente.getNumCNS() == cns) {
                return true;
            }
        }
        return false;
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

    public Paciente findByName(String nome) {
        for (Paciente paciente : pacientes) {
            if (paciente.getNome().equals(nome)) {
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
