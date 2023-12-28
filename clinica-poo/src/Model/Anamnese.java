package Model;

public class Anamnese {
    private static long codigo = 0;
    private long id;

    private Paciente paciente;
    private String motivo;
    private String relato;
    private String diagnostico;

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMotivo() {
        return this.motivo;
    }

    public String getRelato() {
        return this.relato;
    }

    public String getDiagnostico() {
        return this.diagnostico;
    }

    public Paciente getPaciente() {
        return this.paciente;
    }

    private Anamnese(Paciente paciente, String motivo, String relato, String diagnostico, boolean ehCopia) {
        this.diagnostico = diagnostico;
        this.motivo = motivo;
        this.relato = relato;
        this.paciente = paciente;
        if (!ehCopia) {
            this.id = codigo;
            codigo++;
        }
    }

    public Anamnese(Anamnese a) {
        this(new Paciente(a.paciente), a.motivo, a.relato, a.diagnostico, true);
        this.setId(a.getId());
    }

    public static Anamnese getInstance(Paciente paciente, String motivo, String relato, String diagnostico) {
        if (paciente != null && motivo != null && relato != null && diagnostico != null) {
            return new Anamnese(paciente, motivo, relato, diagnostico, false);
        }
        return null;
    }
}