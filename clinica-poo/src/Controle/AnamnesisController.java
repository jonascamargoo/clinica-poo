package Controle;

import java.util.List;

import Model.Anamnesis;
import Model.Patient;
import View.AnamneseView;
import exceptions.AnamneseInvalidaException;
import exceptions.AnamneseNaoEncontradaException;
import repositories.IAnamnesis;
import repositories.PatientRepository;

public class AnamnesisController {
    private AnamneseView anamneseView;
    private IAnamnesis anaminesisRepository;
    

    public AnamnesisController(IAnamnesis anaminesisRepository) {
		anamneseView = new AnamneseView();
		this.anaminesisRepository = anaminesisRepository;
        this.init();
        
	}

    public void add() {
        Anamnesis anamnesis = anamneseView.readAnamnesis();
        try {
            anaminesisRepository.add(anamnesis);
        } catch (AnamneseInvalidaException e) {
            e.printStackTrace();
        }
    }

    public void alterar() {
        Anamnesis aNova = anamneseView.readAnamnesis();
        long id = aNova.getId();
        try {
            anaminesisRepository.update(aNova, id);
        } catch (AnamneseNaoEncontradaException e) {
            e.printStackTrace();
        }
        
    }

    public List<Anamnesis> listarAnamneses() {
        return anaminesisRepository.list();
    }

    public boolean existeAnamnese(long id) {
        if(anaminesisRepository.anamnesisExists(id)) {
            return true;
        }
        return false;
    }

    public Anamnesis buscarAnamnese() {
        //preciso
        long id = anamneseView.inputId();
        anamneseView.listarApenasUmaA(anaminesisRepository.findById(id));
        return null;
        
        
    }

    public boolean existeHomonimo(String nome) {
        if(anaminesisRepository.homonymExists(nome)) {
            return true;
        }
        return false;
    }

    public void init() {
        PatientRepository patientRepository = PatientRepository.getInstance();
        PatientController controlePaciente = new PatientController(patientRepository);
        Patient p1 = controlePaciente.findByCNS(01);
        // ANAMNESES
        // Anamnese 1
        
        Anamnesis a1 = Anamnesis.getInstance(p1, "dor no ouvido", "ouvindo tava doendo", "problema de ouvido").get();
        try {
            anaminesisRepository.add(a1);
        } catch (AnamneseInvalidaException e) {
            
            e.printStackTrace();
        }
        // Anamnese 2
        Patient p2 = controlePaciente.findByCNS(02);
        Anamnesis a2 = Anamnesis.getInstance(p2, "dor no olho", "olho tava doendo demais", "problema nos olhos").get();
        try {
            anaminesisRepository.add(a2);
        } catch (AnamneseInvalidaException e) {
            
            e.printStackTrace();
        }
        
        // Anamnese 3
        Patient p3 = controlePaciente.findByCNS(03);
        Anamnesis a3 = Anamnesis.getInstance(p3, "dor na garganta", "garganta tava doendo demais",
        "problema na garganta").get();
        try {
            anaminesisRepository.add(a3);
        } catch (AnamneseInvalidaException e) {
            
            e.printStackTrace();
        }

    }
    
}
