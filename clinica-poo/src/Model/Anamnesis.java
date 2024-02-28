package Model;

import java.util.Optional;

public class Anamnesis {
    private static long code = 0;
    private long id;

    private Patient patient;
    private String reason;
    private String report;
    private String diagnosis;

    private Anamnesis(Patient patient, String reason, String report, String diagnosis, boolean isCopy) {
        this.diagnosis = diagnosis;
        this.reason = reason;
        this.report = report;
        this.patient = patient;
        if (!isCopy) {
            this.id = code;
            code++;
        }
    }

    public Anamnesis(Anamnesis a) {
        this(new Patient(a.patient), a.reason, a.report, a.diagnosis, true);
        this.setId(a.getId());
    }

    // private void assignCode(boolean isCopy) {
    //     this.code = isCopy ? this.code : id++;
    // }

    public static Optional<Anamnesis> getInstance(Patient patient, String reason, String report, String diagnosis) {
        return (patient != null && reason != null && report != null && diagnosis != null) 
            ? Optional.of(new Anamnesis(patient, reason, report, diagnosis, false)) 
            : Optional.empty();
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getReason() {
        return this.reason;
    }

    public String getReport() {
        return this.report;
    }

    public String getDiagnosis() {
        return this.diagnosis;
    }

    public Patient getPatient() {
        return this.patient;
    }

}