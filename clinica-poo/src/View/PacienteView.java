package View;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import Model.Endereco;
import Model.Paciente;
import Model.PacienteComDeficiencia;
import enums.Disability;
import enums.Sex;
import Date.IRepositorioPaciente;

public class PacienteView {

    private IRepositorioPaciente repoPaciente;
    Scanner scn = new Scanner(System.in);
    // ControlePaciente controlePaciente = new ControlePaciente(repoPaciente);
    private MenuView menuView;

    public void listarPacientes(List<Paciente> pacientes) {
        System.out.println("\n");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        System.out.printf("%7s%12s%15s%15s%15s%15s%15s\n", "NUM_CNS", "SEXO", "DATA_NASC", "NOME", "NOME_MÃE",
                "TELEFONE", "DEFICIENCIA\n");

        for (int i = 0; i < pacientes.size(); i++) {
            if (pacientes.get(i) instanceof PacienteComDeficiencia) {
                PacienteComDeficiencia p = (PacienteComDeficiencia) pacientes.get(i);
                System.out.printf("%7s%12s%15s%15s%15s%15s%15s\n", p.getNumCNS(), p.getSex(),
                        sdf.format(p.getDataNasc()), p.getNome(), p.getNomeMae(),
                        p.getTelefone(), p.getTipo() + "\n");
            } else {
                System.out.printf("%7s%12s%15s%15s%15s%15s\n", pacientes.get(i).getNumCNS(), pacientes.get(i).getSex(),
                        sdf.format(pacientes.get(i).getDataNasc()), pacientes.get(i).getNome(),
                        pacientes.get(i).getNomeMae(),
                        pacientes.get(i).getTelefone() + "\n");
            }
        }
        System.out.println("\n");
    }

    public Paciente lerPaciente() {

        boolean controller = true;
        Paciente p;
        Paciente pacienteComDeficiencia;
        char s;
        Sex sex;
        System.out.print("Nome: ");
        String nome = scn.next();
        nome += scn.nextLine();
        System.out.print("Nome da mãe: ");
        String nomeMae = scn.next();
        nomeMae += scn.nextLine();
        Date d = new Date();
        while (controller) {
            System.out.print("Data de nascimento(dd/MM/yyyy): ");
            String dataNasc = scn.next();

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date data2 = null;
            d = new Date();
            try {
                data2 = sdf.parse(dataNasc);
                d = data2;
                controller = false;

            } catch (ParseException e) {
                System.out.println("Data inválida");
            }
        }
        controller = true;

        do {
            System.out.print("Sexo (M/F/I): ");
            s = scn.next().charAt(0);

            switch (s) {
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
        } while (s != 'M' && s != 'm' && s != 'f' && s != 'F' && s != 'i' && s != 'I');
        Endereco endereco = lerEndereco();

        System.out.print("Telefone: ");
        String telefone = scn.next();

        System.out.println("Tem deficiência? [S/N] ");
        String temDeficiencia = scn.next();
        Disability tipoDeficiencia = Disability.OTHER;
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
                        tipoDeficiencia = Disability.MOTOR;
                        break;
                    case "me":
                    case "Me":
                    case "mE":
                    case "ME":
                        tipoDeficiencia = Disability.MENTAL;
                        break;
                    case "v":
                    case "V":
                        tipoDeficiencia = Disability.VISUAL;
                        break;
                    case "o":
                    case "O":
                        tipoDeficiencia = Disability.OTHER;
                        break;
                    default:
                        System.out.println("Criando um paciente com o tipo de deficiência OUTRO");
                        break;
                }
                System.out.println("Fator Complicador: ");
                String fatorComplicador = scn.next();
                fatorComplicador += scn.nextLine();
                pacienteComDeficiencia = PacienteComDeficiencia.getInstance(nome, nomeMae, d, sex, endereco, telefone,
                        tipoDeficiencia, fatorComplicador);
                return pacienteComDeficiencia;
            }
        }
        p = Paciente.getInstance(nome, nomeMae, d, sex, endereco, telefone);
        return p;

    }

    public long excluirPaciente() {
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

    public Paciente alterarPaciente() {
        System.out.print("Alteração - Num. CNS: ");
        long cns = 0;
        long id = 0;
        try {
            cns = scn.nextLong();
        } catch (InputMismatchException e) {
            scn.nextLine();
            System.out.println("Por favor, digite um número");

        }

        Paciente p = lerPaciente();
        p.setId(cns);
        return p;
    }

    public Endereco lerEndereco() {
        boolean controller = true;
        String logradouro, cidade, uf;
        int numero = 0;
        System.out.print("Logradouro: ");
        logradouro = scn.next();
        logradouro += scn.nextLine();
        System.out.print("Cidade: ");
        cidade = scn.next();
        cidade = scn.nextLine();
        while (controller) {
            System.out.print("Numero: ");
            try {
                numero = scn.nextInt();
                controller = false;
            } catch (InputMismatchException i) {
                System.out.println("Número não deve ser String");
                scn.nextLine();
            }
        }
        System.out.print("UF: ");
        uf = scn.next();
        return new Endereco(logradouro, cidade, uf, numero);
    }

}
