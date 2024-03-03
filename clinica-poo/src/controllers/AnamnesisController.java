package controllers;

import java.util.List;

import Model.Anamnesis;
import Model.Patient;
import View.AnamneseView;
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
        Anamnesis newAnamnesis = anamneseView.readAnamnesis();
        anaminesisRepository.add(newAnamnesis);
    }


    public List<Anamnesis> list() {
        return anaminesisRepository.list();
    }

    public void update() {
        Anamnesis updatedAnamnesis = anamneseView.readAnamnesis();
        anaminesisRepository.update(updatedAnamnesis);
        
    }

    public Anamnesis buscarAnamnese() {
        long id = anamneseView.inputId();
        anamneseView.listOne(anaminesisRepository.findById(id));
        return null;
        
        
    }

    public void init() {
        PatientRepository patientRepository = PatientRepository.getInstance();
        PatientController controlePaciente = new PatientController(patientRepository);
        Patient p1 = controlePaciente.findByCNS(01);
        // ANAMNESES
        // Anamnese 1
        
        Anamnesis a1 = Anamnesis.getInstance(p1, "dor no ouvido", "ouvindo tava doendo", "problema de ouvido").get();
        anaminesisRepository.add(a1);
        // Anamnese 2
        Patient p2 = controlePaciente.findByCNS(02);
        Anamnesis a2 = Anamnesis.getInstance(p2, "dor no olho", "olho tava doendo demais", "problema nos olhos").get();
        anaminesisRepository.add(a2);
        
        // Anamnese 3
        Patient p3 = controlePaciente.findByCNS(03);
        Anamnesis a3 = Anamnesis.getInstance(p3, "dor na garganta", "garganta tava doendo demais",
        "problema na garganta").get();
        anaminesisRepository.add(a3);

    }
    
}
