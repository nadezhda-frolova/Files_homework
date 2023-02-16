package model;

public class CustomerData {
    public String firstName;
    public String lastName;
    public String gender;
    public Integer age;
    public Address address;

    public static class Address{
        public Integer streetAddress;
        public String city;
        public String state;
    }

}
