package View;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import Model.Anamnesis;
import Model.Patient;
import repositories.RepositorioPacienteList;

public class AnamneseView {

    private RepositorioPacienteList repositorioPacienteList = RepositorioPacienteList.getInstance();

    public AnamneseView() {
		scn = new Scanner(System.in);
	}

    Scanner scn = new Scanner(System.in);
    private MenuView menuView;


    public long recebeId() {
        System.out.println("ID: ");
        long id = scn.nextLong();
        return id;
    }

    public String recebeNome() {
        System.out.print("Nome: ");
        String nome = scn.next();
        return nome;
    }

    public String recebeNomeMae() {
        System.out.print("Nome da mãe: ");
        String nomeMae = scn.next();
        return nomeMae;
    }

    public Anamnesis lerAnamnese() {
        
        long cns;
        System.out.print("Digite o num do CNS: ");
        String motivo = "", relato = "", diagnostico = "";
        try {
            cns = scn.nextLong();
        } catch (InputMismatchException e) {
            System.out.println("Por favor, digite um num válido");
            scn.nextLine();
            return null;
        }
        Patient p = repositorioPacienteList.findByCNS(cns);
        

        System.out.print("Motivo: ");
        try {
            motivo = scn.next();
            motivo += scn.nextLine();

        } catch (InputMismatchException e) {
            System.out.println("Por favor, digite o motivo");
        }

        System.out.print("Relato: ");
        try {
            relato = scn.next();
            relato += scn.nextLine();
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
        Anamnesis a = Anamnesis.getInstance(p, motivo, relato, diagnostico);
        
        
        return a;
    }
    

    public void listarApenasUmaA(Anamnesis a) {
        System.out.printf("%7s%15s%25s%30s%30s%30s", "ID", "PACIENTE", "MÃE DO PACIENTE", "MOTIVO", "RELATO",
        "DIAGNOSTICO\n");
        System.out.printf("%7s%15s%25s%30s%30s%30s", a.getId(), a.getPaciente().getName(),
                    a.getPaciente().getNomeMae(), a.getMotivo(), a.getRelato(),
                    a.getDiagnostico() + "\n");

    }

    public void listarAnamneses(List<Anamnesis> anamneses) {
        System.out.printf("%7s%15s%25s%30s%30s%30s", "ID", "PACIENTE", "MÃE DO PACIENTE", "MOTIVO", "RELATO",
                "DIAGNOSTICO\n");
        for (Anamnesis anamnese : anamneses) {
            System.out.printf("%7s%15s%25s%30s%30s%30s", anamnese.getId(), anamnese.getPaciente().getName(),
                    anamnese.getPaciente().getNomeMae(), anamnese.getMotivo(), anamnese.getRelato(),
                    anamnese.getDiagnostico() + "\n");
        }
        System.out.println("\n");
    }


    public long recebeCNS() {
        System.out.println("Digite o cns");
        long cns = scn.nextLong();
        return cns;
    }

    public Patient findByCNS(List<Patient> pacientes, long cns) {
        for (int i = 0; i < pacientes.size(); i++) {
            if (pacientes.get(i) != null) {
                if (pacientes.get(i).getNumCNS() == cns) {
                    return pacientes.get(i);
                }
            }
        }
        return null;
    }

    
}
