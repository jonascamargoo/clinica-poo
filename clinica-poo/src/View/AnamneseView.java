package View;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import Model.Anamnesis;
import Model.Patient;
import repositories.IPatient;
import repositories.PatientRepository;

public class AnamneseView {

    private IPatient patientRepository = PatientRepository.getInstance();

    public AnamneseView() {
		scn = new Scanner(System.in);
	}

    Scanner scn = new Scanner(System.in);
    private MenuView menuView;


    public long inputId() {
        System.out.println("ID: ");
        long id = scn.nextLong();
        return id;
    }

    public String inputName() {
        System.out.print("Nome: ");
        return scn.next();
    }

    public String inputMotherName() {
        System.out.print("Nome da mãe: ");
        return scn.next();
    }

    public Anamnesis readAnamnesis() {
        long cns;
        System.out.print("Digite o num do CNS: ");
        String reason = "", report = "", diagnostico = "";
        try {
            cns = scn.nextLong();
        } catch (InputMismatchException e) {
            System.out.println("Por favor, digite um num válido");
            scn.nextLine();
            return null;
        }
        Patient patient = patientRepository.findByCNS(cns);
        

        System.out.print("Motivo: ");
        try {
            reason = scn.next();
            reason += scn.nextLine();

        } catch (InputMismatchException e) {
            System.out.println("Por favor, digite o motivo");
        }

        System.out.print("Relato: ");
        try {
            report = scn.next();
            report += scn.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Por favor, digite o relato do paciente");
        }

        System.out.print("Diagnóstico: ");
        
       
        try {
            diagnostico = scn.next();
            diagnostico += scn.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Por favor, dê um diagnóstico válido");
        }
        Anamnesis anamnesis = Anamnesis.getInstance(patient, reason, report, diagnostico).get();
        return anamnesis;
    }
    

    public void listarApenasUmaA(Anamnesis a) {
        System.out.printf("%7s%15s%25s%30s%30s%30s", "ID", "PACIENTE", "MÃE DO PACIENTE", "MOTIVO", "RELATO",
        "DIAGNOSTICO\n");
        System.out.printf("%7s%15s%25s%30s%30s%30s", a.getId(), a.getPatient().getName(),
                    a.getPatient().getMotherName(), a.getReason(), a.getReport(),
                    a.getDiagnosis() + "\n");

    }

    public void listarAnamneses(List<Anamnesis> anamneses) {
        System.out.printf("%7s%15s%25s%30s%30s%30s", "ID", "PACIENTE", "MÃE DO PACIENTE", "MOTIVO", "RELATO",
                "DIAGNOSTICO\n");
        for (Anamnesis anamnese : anamneses) {
            System.out.printf("%7s%15s%25s%30s%30s%30s", anamnese.getId(), anamnese.getPatient().getName(),
                    anamnese.getPatient().getMotherName(), anamnese.getReason(), anamnese.getReport(),
                    anamnese.getDiagnosis() + "\n");
        }
        System.out.println("\n");
    }


    public long inputCNS() {
        System.out.println("Digite o cns");
        return scn.nextLong();
    }

    
}
