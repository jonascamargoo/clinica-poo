package Model;

import java.util.Date;

import enums.Sex;

public class Patient {
    protected static long id = 0;
    protected String name;
    protected String motherName;
    protected Date birthDate;
    protected Sex sex;
    protected Endereco address;
    protected String phoneNumber;
    protected long numCNS;

    protected Patient(String name, String motherName, Date birthDate, Sex sex, Endereco address, String phoneNumber, boolean isCopy) {
        this.name = name;
        this.motherName = motherName;
        this.birthDate = birthDate;
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
        this.address = address;
        this.phoneNumber = phoneNumber;
        if (!isCopy) {
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

    public Patient(Patient p) {
        this(p.name, p.motherName, new Date(p.birthDate.getTime()), Sex.valueOf(p.getSex().toString()),
                new Endereco(p.address), p.phoneNumber, true);

        long cns = p.getNumCNS();
        this.setId(cns);
    }

    public static Patient getInstance(String name, String motherName, Date birthDate, Sex sex, Endereco address,
            String phoneNumber) {
        if (name != null && motherName != null && birthDate != null && sex != null && address != null
                && phoneNumber != null) {
            return new Patient(name, motherName, birthDate, sex, address, phoneNumber, false);
        }
        return null;
    }

    public long getNumCNS() {
        return this.numCNS;
    }

    public String getName() {
        return this.name;
    }

    public String getMotherName() {
        return this.motherName;
    }

    public Date getBirthDate() {
        return this.birthDate;
    }

    public Sex getSex() {
        return this.sex;
    }

    public Endereco getAddress() {
        return this.address;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void add(Endereco e) {
        this.address = e;
    }

}
