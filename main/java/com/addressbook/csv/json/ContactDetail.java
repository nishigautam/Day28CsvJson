package com.addressbook.csv.json;

import com.opencsv.bean.CsvBindByName;

public class ContactDetail {
    /**
     * assigning all the details giving its value
     *
     * creating contact details inside AddressBook
     */
    @CsvBindByName
    public String firstName;
    @CsvBindByName
    public String lastName;
    @CsvBindByName
    public String address;
    @CsvBindByName
    public String city;
    @CsvBindByName
    public String state;
    @CsvBindByName
    public String phoneNumber;
    @CsvBindByName
    public int zipCode;

    /**
     * assigning variables to each details
     * @param firstName
     * @param lastName
     * @param address
     * @param city
     * @param state
     * @param phoneNumber
     * @param zipCode
     */
    public ContactDetail(String firstName, String lastName, String address, String city,
                         String state,  String phoneNumber, int zipCode ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.state = state;
        this.phoneNumber = phoneNumber;
        this.zipCode = zipCode;
    }


    public String getFirstname() {
        return firstName;
    }

    public void setFirstname() {
        this.firstName = firstName;
    }

    public String getLastname() {
        return lastName;
    }

    public void setLastname() {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress() {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity() {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState() {
        this.state = state;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber() {
        this.phoneNumber = phoneNumber;
    }

    public int getZipcode() {
        return zipCode;
    }

    public void setZipcode(int zipCode) {
        this.zipCode = zipCode;
    }

    /**
     * display the contact created in address book.
     */
    @Override
    public String toString() {
        return "AddressBook [" + "firstname=" + firstName + ", lastname=" + lastName +
                ", city=" + city + ", state=" + state + ", phoneNumber=" + phoneNumber + ", zipcode=" + zipCode + "]";
    }
}
