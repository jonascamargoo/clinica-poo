package Model;

import java.util.Date;

public class Paciente {
    protected static long id = 0;
    protected String nome;
    protected String nomeMae;
    protected Date dataNasc;
    protected Sexo sexo;
    protected Endereco endereco;
    protected String telefone;
    protected long numCNS;

    protected Paciente(String nome, String nomeMae, Date dataNasc, Sexo sexo, Endereco endereco, String telefone, boolean ehCopia) {
        this.nome = nome;
        this.nomeMae = nomeMae;
        this.dataNasc = dataNasc;
        switch (sexo.getSexo()) {
            case "m":
                this.sexo = Sexo.MASCULINO;
                break;
            case "f":
                this.sexo = Sexo.FEMININO;
                break;
            case "i":
                this.sexo = Sexo.INTERSEXO;
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
        this(p.nome, p.nomeMae, new Date(p.dataNasc.getTime()), Sexo.valueOf(p.getSexo().toString()),
                new Endereco(p.endereco), p.telefone, true);

        long cns = p.getNumCNS();
        this.setId(cns);
    }

    public static Paciente getInstance(String nome, String nomeMae, Date dataNasc, Sexo sexo, Endereco endereco,
            String telefone) {
        if (nome != null && nomeMae != null && dataNasc != null && sexo != null && endereco != null
                && telefone != null) {
            return new Paciente(nome, nomeMae, dataNasc, sexo, endereco, telefone, false);
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

    public Sexo getSexo() {
        return this.sexo;
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
