package View;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import Model.Address;
import Model.Patient;
import Model.PatientWithDisability;
import enums.Disability;
import enums.Sex;

public class PatientView {
    Scanner scn = new Scanner(System.in);

    public void patientList(List<Patient> Patients) {
        System.out.println("\n");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        System.out.printf("%7s%12s%15s%15s%15s%15s%15s\n", "NUM_CNS", "SEXO", "DATA_NASC", "NOME", "NOME_MÃE",
                "TELEFONE", "DEFICIENCIA\n");

        for (int i = 0; i < Patients.size(); i++) {
            if (Patients.get(i) instanceof PatientWithDisability) {
                PatientWithDisability p = (PatientWithDisability) Patients.get(i);
                System.out.printf("%7s%12s%15s%15s%15s%15s%15s\n", p.getNumCNS(), p.getSex(),
                        sdf.format(p.getBirthDate()), p.getName(), p.getMotherName(),
                        p.getPhoneNumber() + "\n");
            } else {
                System.out.printf("%7s%12s%15s%15s%15s%15s\n", Patients.get(i).getNumCNS(), Patients.get(i).getSex(),
                        sdf.format(Patients.get(i).getBirthDate()), Patients.get(i).getName(),
                        Patients.get(i).getMotherName(),
                        Patients.get(i).getPhoneNumber() + "\n");
            }
        }
        System.out.println("\n");
    }

    public Patient patientRead() {
        boolean control = true;
        Patient patient;
        Patient patientWithDisability;
        char sexChar;
        Sex sex;
        System.out.print("Nome: ");
        String name = scn.next();
        name += scn.nextLine();
        System.out.print("Nome da mãe: ");
        String motherName = scn.next();
        motherName += scn.nextLine();
        Date date = new Date();
        while (control) {
            System.out.print("Data de nascimento(dd/MM/yyyy): ");
            String birthDate = scn.next();

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date data2 = null;
            date = new Date();
            try {
                data2 = sdf.parse(birthDate);
                date = data2;
                control = false;

            } catch (ParseException e) {
                System.out.println("Data inválida");
            }
        }
        control = true;

        do {
            System.out.print("Sexo (M/F/I): ");
            sexChar = scn.next().charAt(0);

            switch (sexChar) {
                case 'M':
                case 'm':
                    sex = Sex.MALE;
                    break;
                case 'F':
                case 'f':
                    sex = Sex.FEMALE;
                    break;
                case 'I':
                case 'i':
                    sex = Sex.INTERSEX;
                    break;
                default:
                    sex = Sex.INTERSEX;
            }
        } while (sexChar != 'M' && sexChar != 'm' && sexChar != 'f' && sexChar != 'F' && sexChar != 'i' && sexChar != 'I');
        Address address = addressRead();

        System.out.print("Telefone: ");
        String phoneNumber = scn.next();

        System.out.println("Tem deficiência? [S/N] ");
        String temDeficiencia = scn.next();
        Disability disability = Disability.OTHER;
        boolean PCD = false;
        if (temDeficiencia.equals("S") || temDeficiencia.equals("s") || temDeficiencia.equals("n")
                || temDeficiencia.equals("N")) {
            PCD = true;
            if (temDeficiencia.equals("S") || temDeficiencia.equals("s")) {
                System.out.println("Tipo:\n[mo]MOTORA\n[me]MENTAL\n[v]VISUAL\n[o]OUTRO");
                String tipo = scn.next();

                switch (tipo) {
                    case "mo":
                    case "mO":
                    case "Mo":
                    case "MO":
                        disability = Disability.MOTOR;
                        break;
                    case "me":
                    case "Me":
                    case "mE":
                    case "ME":
                        disability = Disability.MENTAL;
                        break;
                    case "v":
                    case "V":
                        disability = Disability.VISUAL;
                        break;
                    case "o":
                    case "O":
                        disability = Disability.OTHER;
                        break;
                    default:
                        System.out.println("Criando um paciente com o tipo de deficiência OUTRO");
                        break;
                }
                System.out.println("Fator Complicador: ");
                String complicatingFactor = scn.next();
                complicatingFactor += scn.nextLine();
                patientWithDisability = PatientWithDisability.getInstance(name, motherName, date, sex, address, phoneNumber,
                        disability, complicatingFactor).get();
                return patientWithDisability;
            }
        }
        patient = Patient.getInstance(name, motherName, date, sex, address, phoneNumber).get();
        return patient;

    }

    public long patientDelete() {
        long cns = -1;
        System.out.println("Exclusão: Qual o CNS do paciente? ");
        try {
            cns = scn.nextLong();
        } catch (InputMismatchException e) {
            scn.nextLine();
            System.out.println("Por favor, digite um número");
        }
        return cns;
    }

    public Patient patientUpdate() {
        System.out.print("Alteração - Num. CNS: ");
        long cns = 0;
        long id = 0;
        try {
            cns = scn.nextLong();
        } catch (InputMismatchException e) {
            scn.nextLine();
            System.out.println("Por favor, digite um número");

        }

        Patient p = patientRead();
        p.setId(cns);
        return p;
    }

    public Address addressRead() {
        boolean controller = true;
        String logradouro, cidade, uf;
        String numero = "";
        System.out.print("Logradouro: ");
        logradouro = scn.next();
        logradouro += scn.nextLine();
        System.out.print("Cidade: ");
        cidade = scn.next();
        cidade = scn.nextLine();
        while (controller) {
            System.out.print("Numero: ");
            try {
                numero = scn.next();
                controller = false;
            } catch (InputMismatchException i) {
                System.out.println("Número não deve ser String");
                scn.nextLine();
            }
        }
        System.out.print("UF: ");
        uf = scn.next();
        return new Address(logradouro, cidade, uf, numero);
    }

}
