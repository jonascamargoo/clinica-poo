package repositories;

import java.util.List;

import Model.Anamnese;
import Model.Patient;
import exceptions.AlterarPacienteException;
import exceptions.ExcluirPacienteException;
import exceptions.PacienteInvalidoException;

public interface IRepositorioPaciente {
    public void add(Patient p) throws PacienteInvalidoException;
    public List<Patient> listar();
    public void excluir(long numCNS) throws ExcluirPacienteException;
	public Patient buscar(long idPaciente);
	public void alterar(Patient pAlterado) throws AlterarPacienteException;
    public Patient findByCNS(long numCNS);
    public boolean pacienteAtreladoAnamnese(long id);
    public Anamnese buscaAnamnese(long id);
    public boolean existePaciente(long cns);
    public Patient findByName(String nome);
    public boolean existeAnamnese(long id);


}