package Model;

import java.util.Date;
import java.util.Optional;

import enums.Sex;

public class Patient {
    protected static long id = 0;
    protected String name;
    protected String motherName;
    protected Date birthDate;
    protected Sex sex;
    protected Address address;
    protected String phoneNumber;
    protected long numCNS;

    protected Patient(
            String name, String motherName, Date birthDate, 
            Sex sex, Address address, String phoneNumber, 
            boolean isCopy
        ) {
        this.name = name;
        this.motherName = motherName;
        this.birthDate = birthDate;
        this.sex = sex;
        this.address = address;
        this.phoneNumber = phoneNumber;
        assignNumCNS(isCopy);   
    }
    
    private void assignNumCNS(boolean isCopy) {
        this.numCNS = isCopy ? this.numCNS : id++;
    }

    public void setId(long id) {
        this.numCNS = id;
    }

    public long setCNS(long cns) {
        return this.numCNS = cns;
    }

    public Patient(Patient p) {
        this(
            p.name, p.motherName, 
            new Date(p.birthDate.getTime()), 
            Sex.valueOf(p.getSex().toString()),
            new Address(p.address), 
            p.phoneNumber, true
        );
        long cns = p.getNumCNS();
        this.setId(cns);
    }


    public static Optional<Patient> getInstance(
            String name, String motherName, Date birthDate, 
            Sex sex, Address address, String phoneNumber
        ) {
        return (
            name != null && motherName != null && 
            birthDate != null && sex != null && 
            address != null && phoneNumber != null
        ) ? Optional.of(new Patient(name, motherName, birthDate, sex, address, phoneNumber, false)
        ) : Optional.empty();
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

    public Address getAddress() {
        return this.address;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void add(Address e) {
        this.address = e;
    }

}
