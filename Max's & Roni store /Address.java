package RoniAndMax;

public class Address {
    public String street;
    public int number;
    public String city;
    public String country;

    public Address(String Street, int number, String city, String country) {
        this.city = city;
        this.street = Street;
        this.number = number;
        this.country = country;
    }

    public Address(Address address) {
        this.city = address.city;
        this.street = address.street;
        this.number = address.number;
        this.country = address.country;
    }

    public String getStreet() {
        return street;
    }

    public int getNumber() {
        return number;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    @Override
    public String toString() {return street + " " + number + "," + city + "," + country;}
}