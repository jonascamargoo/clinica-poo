package Model;

import java.util.Date;

import enums.Sex;

public class Paciente {
    protected static long id = 0;
    protected String nome;
    protected String nomeMae;
    protected Date dataNasc;
    protected Sex sex;
    protected Endereco endereco;
    protected String telefone;
    protected long numCNS;

    protected Paciente(String nome, String nomeMae, Date dataNasc, Sex sex, Endereco endereco, String telefone, boolean ehCopia) {
        this.nome = nome;
        this.nomeMae = nomeMae;
        this.dataNasc = dataNasc;
        switch (sex.getSexo()) {
            case "m":
                this.sex = Sex.MALE;
                break;
            case "f":
                this.sex = Sex.FEMALE;
                break;
            case "i":
                this.sex = Sex.INTERSEX;
                break;
            default:
                break;
        }
        this.endereco = endereco;
        this.telefone = telefone;
        if (!ehCopia) {
            this.numCNS = id;
            id++;
        }

    }

    public void setId(long id) {
        this.numCNS = id;
    }

    public long setCNS(long cns) {
        return this.numCNS = cns;
    }

    public Paciente(Paciente p) {
        this(p.nome, p.nomeMae, new Date(p.dataNasc.getTime()), Sex.valueOf(p.getSex().toString()),
                new Endereco(p.endereco), p.telefone, true);

        long cns = p.getNumCNS();
        this.setId(cns);
    }

    public static Paciente getInstance(String nome, String nomeMae, Date dataNasc, Sex sex, Endereco endereco,
            String telefone) {
        if (nome != null && nomeMae != null && dataNasc != null && sex != null && endereco != null
                && telefone != null) {
            return new Paciente(nome, nomeMae, dataNasc, sex, endereco, telefone, false);
        }
        return null;
    }

    public long getNumCNS() {
        return this.numCNS;
    }

    public String getNome() {
        return this.nome;
    }

    public String getNomeMae() {
        return this.nomeMae;
    }

    public Date getDataNasc() {
        return this.dataNasc;
    }

    public Sex getSex() {
        return this.sex;
    }

    public Endereco getEndereco() {
        return this.endereco;
    }

    public String getTelefone() {
        return this.telefone;
    }

    public void add(Endereco e) {
        this.endereco = e;
    }

}
