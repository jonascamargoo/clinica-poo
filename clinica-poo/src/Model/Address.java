package Model;

public class Address {
    private String street;
    private String city;
    private String state;
    private String number;

    public Address(String street, String city, String state, String number) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.number = number;
    }

    public Address(Address other) {
        this.street = other.street;
        this.city = other.city;
        this.state = other.state;
        this.number = other.number;
    }

    public String getStreet() {
        return this.street;
    }

    public String getCity() {
        return this.city;
    }

    public String getState() {
        return this.state;
    }

    public String getNumber() {
        return this.number;
    }

}
