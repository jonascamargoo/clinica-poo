package View;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import Model.Anamnesis;
import Model.Patient;
import exceptions.ReadAnamnesisException;
import repositories.IPatient;
import repositories.PatientRepository;

public class AnamneseView {

    private IPatient patientRepository = PatientRepository.getInstance();
    private Scanner scn;

    public AnamneseView() {
		scn = new Scanner(System.in);
	}

    public long inputId() {
        System.out.println("ID: ");
        return scn.nextLong();
    }

    public String inputName() {
        System.out.print("Nome: ");
        return scn.next();
    }

    public String inputMotherName() {
        System.out.print("Nome da mãe: ");
        return scn.next();
    }

    public long inputCNS() {
        System.out.println("Digite o num do CNS: ");
        try {
            return scn.nextLong();
        } catch (InputMismatchException e) {
            System.out.println("Por favor, digite um num válido");
            scn.nextLine();
            return -1;
        }
    }

    public Anamnesis readAnamnesis() {
        long cns = inputCNS();
        if (cns == -1)
            return null;
        String reason = getInput("Motivo: ");
        String report = getInput("Relato: ");
        String diagnosis = getInput("Diagnóstico: ");
        Patient patient = patientRepository.findByCNS(cns);
        return Anamnesis.getInstance(patient, reason, report, diagnosis)
                .orElseThrow(() -> new ReadAnamnesisException());
    }

    private String getInput(String prompt) {
        System.out.print(prompt);
        try {
            return scn.next() + scn.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida");
            scn.nextLine();
            return "";
        }
    }

    public void listOne(Anamnesis anamnesis) {
        listHeader();
        listBody(anamnesis);
    }

    public void listAll(List<Anamnesis> anamneses) {
        listHeader();
        anamneses.forEach(this::listBody);
        System.out.println("\n");
    }

    // consertar formatacao do header e body - seguir padrao do patient
    // private void listHeader() {
    //     System.out.printf("%7s%15s%25s%30s%30s%15s", "ID", "PACIENTE", "MÃE DO PACIENTE", "MOTIVO", "RELATO",
    //     "DIAGNOSTICO\n");
    // }

    // private void listBody(Anamnesis anamnesis) {
    //     System.out.printf("%7s%15s%20s%30s%30s%20s", anamnesis.getId(), anamnesis.getPatient().getName(),
    //     anamnesis.getPatient().getMotherName(), anamnesis.getReason(), anamnesis.getReport(),
    //     anamnesis.getDiagnosis() + "\n");
    // }


    
}


 