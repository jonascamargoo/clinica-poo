package Model;

import java.util.Date;
import java.util.Optional;

import enums.Disability;
import enums.Sex;

public class PatientWithDisability extends Patient {
    private String complicatingFactor;
    private Disability disability;

    public String getComplicatingFactor() {
        return this.complicatingFactor;
    }

    public Disability getDisability() {
        return this.disability;
    }

    public PatientWithDisability(PatientWithDisability patient) {
        super(patient.getName(), patient.getMotherName(), new Date(patient.getBirthDate().getTime()),
                patient.getSex(), new Endereco(patient.getAddress()), patient.getPhoneNumber(), true);

        long cns = patient.getNumCNS();
        this.setId(cns);
        this.disability = patient.getDisability();
        this.complicatingFactor = patient.complicatingFactor;
    }

    private PatientWithDisability(String name, String motherName, Date birthDate, Sex sex, Endereco address,
            String phoneNumber, Disability disability, String complicatingFactor, boolean isCopy) {
        super(name, motherName, birthDate, sex, address, phoneNumber, isCopy);
        this.disability = disability;
        this.complicatingFactor = complicatingFactor;
    }

    public static Optional<PatientWithDisability> getInstance(String name, String motherName, Date birthDate, Sex sex,
            Endereco address, String phoneNumber, Disability disability, String complicatingFactor) {
        if (!(name != null && motherName != null && birthDate != null && sex != null &&
                address != null && phoneNumber != null && disability != null && complicatingFactor != null)) {
            return Optional.empty();
        }
        return Optional.of(new PatientWithDisability(name, motherName, birthDate, sex, address, phoneNumber, disability,
                complicatingFactor, false));
    }

}
