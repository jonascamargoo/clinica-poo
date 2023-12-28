package Date;

import java.util.List;

import Date.exceptions.AlterarPacienteException;
import Date.exceptions.ExcluirPacienteException;
import Date.exceptions.PacienteInvalidoException;
import Model.Anamnese;
import Model.Paciente;

public interface IRepositorioPaciente {
    public void add(Paciente p) throws PacienteInvalidoException;
    public List<Paciente> listar();
    public void excluir(long numCNS) throws ExcluirPacienteException;
	public Paciente buscar(long idPaciente);
	public void alterar(Paciente pAlterado) throws AlterarPacienteException;
    public Paciente findByCNS(long numCNS);
    public boolean pacienteAtreladoAnamnese(long id);
    public Anamnese buscaAnamnese(long id);
    public boolean existePaciente(long cns);
    public Paciente findByName(String nome);
    public boolean existeAnamnese(long id);


}