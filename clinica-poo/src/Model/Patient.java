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

    // Factory Method - encapsulando logica de criacao de objetos patient

    // Construtor principal - cria uma instancia de Patient e itera o id
    protected Patient(String name, String motherName, Date birthDate, Sex sex, Address address, String phoneNumber, boolean isCopy) {
        this.name = name;
        this.motherName = motherName;
        this.birthDate = birthDate;
        this.sex = sex;
        this.address = address;
        this.phoneNumber = phoneNumber;
        assignNumCNS(isCopy);
    }
 
    // Construtor de copia -  cria uma nova instancia de  Patient a partir de outra instancia existente
    // A motivacao para utilizar o metodo fabrica eh poder instanciar objetos com novos valores de id, sem alterar o id dos objetos subjacentes
    public Patient(Patient patient) {
        this(patient.name, patient.motherName, new Date(patient.birthDate.getTime()),
            Sex.valueOf(patient.getSex().toString()), new Address(patient.address),
            patient.phoneNumber, true
        );
        long cns = patient.getNumCNS();
        this.setId(cns);
    }

    // Construtor fabrica - valida os parametros 
    // e cria a instancia final. Caso invalidos, retorna um null
    public static Optional<Patient> getInstance(String name, String motherName, Date birthDate, Sex sex, Address address, String phoneNumber
        ) {
        return (
            name != null && motherName != null &&
                birthDate != null && sex != null &&
                address != null && phoneNumber != null
            ) ? Optional.of(
                new Patient(
                    name, motherName, birthDate, 
                    sex, address, phoneNumber, false
                )
            ) : Optional.empty();
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
