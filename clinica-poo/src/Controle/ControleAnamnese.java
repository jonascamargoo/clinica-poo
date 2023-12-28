package Controle;

import java.util.List;
import Date.IRepositorioAnamnese;
import Date.IRepositorioPaciente;
import Date.RepositorioPacienteList;
import Date.exceptions.AnamneseInvalidaException;
import Date.exceptions.AnamneseNaoEncontradaException;
import Model.Anamnese;
import Model.Paciente;
import View.AnamneseView;

public class ControleAnamnese {
    private AnamneseView anamneseView;
    private IRepositorioPaciente repositorioPaciente;
    private IRepositorioAnamnese repoAnamnese;
    

    public ControleAnamnese(IRepositorioAnamnese repoAnamnese) {
		anamneseView = new AnamneseView();
		this.repoAnamnese = repoAnamnese;
        this.init();
        
	}

    public void add() {
        Anamnese a = anamneseView.lerAnamnese();
        try {
            repoAnamnese.add(a);
        } catch (AnamneseInvalidaException e) {
            e.printStackTrace();
        }
        
    }

    public void alterar() {
        Anamnese aNova = anamneseView.lerAnamnese();
        long id = aNova.getId();
        try {
            repoAnamnese.alterar(aNova, id);
        } catch (AnamneseNaoEncontradaException e) {
            e.printStackTrace();
        }
        
    }

    public List<Anamnese> listarAnamneses() {
        return repoAnamnese.listar();
    }

    public boolean existeAnamnese(long id) {
        if(repoAnamnese.existeAnamnese(id)) {
            return true;
        }
        return false;
    }

    public Anamnese buscarAnamnese() {
        //preciso
        long id = anamneseView.recebeId();
        anamneseView.listarApenasUmaA(repoAnamnese.buscar(id));
        return null;
        
        
    }

    public boolean existeHomonimo(String nome) {
        if(repoAnamnese.existeHomonimo(nome)) {
            return true;
        }
        return false;
    }

    public void init() {
        RepositorioPacienteList repositorioPacienteList = RepositorioPacienteList.getInstance();
        ControlePaciente controlePaciente = new ControlePaciente(repositorioPacienteList);
        Paciente p1 = controlePaciente.findByCNS(01);
        // ANAMNESES
        // Anamnese 1
        
        Anamnese a1 = Anamnese.getInstance(p1, "dor no ouvido", "ouvindo tava doendo", "problema de ouvido");
        try {
            repoAnamnese.add(a1);
        } catch (AnamneseInvalidaException e) {
            
            e.printStackTrace();
        }
        // Anamnese 2
        Paciente p2 = controlePaciente.findByCNS(02);
        Anamnese a2 = Anamnese.getInstance(p2, "dor no olho", "olho tava doendo demais", "problema nos olhos");
        try {
            repoAnamnese.add(a2);
        } catch (AnamneseInvalidaException e) {
            
            e.printStackTrace();
        }
        
        // Anamnese 3
        Paciente p3 = controlePaciente.findByCNS(03);
        Anamnese a3 = Anamnese.getInstance(p3, "dor na garganta", "garganta tava doendo demais",
        "problema na garganta");
        try {
            repoAnamnese.add(a3);
        } catch (AnamneseInvalidaException e) {
            
            e.printStackTrace();
        }

    }
    
}
