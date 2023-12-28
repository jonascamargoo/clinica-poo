package Model;

import java.util.Date;

public class PacienteComDeficiencia extends Paciente {

    private String fatorComplicador;
    private Deficiencia tipo;

    public String getFatorComplicador() {
        return this.fatorComplicador;
    }

    public Deficiencia getTipo() {
        return this.tipo;
    }

    public PacienteComDeficiencia(PacienteComDeficiencia p){
        super(p.nome, p.nomeMae, new Date(p.dataNasc.getTime()), Sexo.valueOf(p.getSexo().toString()),
        new Endereco(p.endereco), p.telefone,
        true);
        long cns = p.getNumCNS();
        this.setId(cns);
        this.tipo = Deficiencia.valueOf(p.getTipo().toString());
        this.fatorComplicador = p.fatorComplicador;
    }

    private PacienteComDeficiencia(String nome, String nomeMae, Date dataNasc, Sexo sexo, Endereco endereco,
            String telefone, Deficiencia deficiencia, String fatorComplicador, boolean ehCopia) {
        super(nome, nomeMae, dataNasc, sexo, endereco, telefone, ehCopia);
        this.tipo = deficiencia;
        this.fatorComplicador = fatorComplicador;
    }

    public static PacienteComDeficiencia getInstance(String nome, String nomeMae, Date dataNasc, Sexo sexo, Endereco endereco,
            String telefone, Deficiencia deficiencia, String fatorComplicador) {
        if (nome != null && nomeMae != null && dataNasc != null && sexo != null && endereco != null
                && telefone != null && deficiencia!=null && fatorComplicador!=null) {
            return new PacienteComDeficiencia(nome, nomeMae, dataNasc, sexo, endereco, telefone, deficiencia, fatorComplicador, false);
        }
        return null;
    }

}
