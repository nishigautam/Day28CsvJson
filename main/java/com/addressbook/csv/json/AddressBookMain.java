package com.addressbook.csv.json;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.IOException;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
/**
 * @author Mentalist
 *
 */

public class AddressBookMain {


    private static Map<String, AddressBook> addressMap = new TreeMap<String, AddressBook>();
    public static Map<String, ContactDetail> cityToContactEntryMap = new TreeMap<>();
    public static Map<String, ContactDetail> stateToContactEntryMap = new TreeMap<>();

    /**
     * uc6
     */
    public void addNewAddressBook()
    {
        Scanner sc=new Scanner(System.in);
        AddressBook object = new AddressBook();
        System.out.println("Enter the name of the address book");
        String name=sc.next();
        addressMap.put(name,object);
        object.multipleAddressBook();
    }

    /**
     * uc9
     */
    public static void displayContactsByCityGrouping() {
        Set<String> listOfCity = cityToContactEntryMap.keySet();
        for(String cityName : listOfCity) {
            System.out.println("The Address book entries based on city: " + cityName);
            personSearch(cityName);
        }
    }
    public static void displayContactsByStateGrouping() {
        Set<String> listOfState = stateToContactEntryMap.keySet();
        for(String stateName : listOfState) {
            System.out.println("The Address book entries based on state: " + stateName);
            personSearch(stateName);
        }
    }

    /**
     * uc10
     */
    public static void numberByCity() {
        Set<String> listOfCity = cityToContactEntryMap.keySet();
        for(String cityName : listOfCity) {
            ContactDetail contactNumber = cityToContactEntryMap.get(cityName);
            System.out.println("Number of Contacts from city " + cityName + " " + ((Map<String, AddressBookMain>) contactNumber).size());
        }
    }
    public static void numberByState() {
        Set<String> listOfState = stateToContactEntryMap.keySet();
        for(String stateName : listOfState) {
            ContactDetail contactNumber = stateToContactEntryMap.get(stateName);
            System.out.println("Number of Contacts from state " + stateName + " " + ((Map<String, AddressBookMain>) contactNumber).size());
        }
    }
    public void displayAddressBooks() {
        System.out.println("\nThe Address Books Added are: \n");
        addressMap.forEach((k, v) -> System.out.println(k + "\n"));
    }


    /**
     * uc8
     * @param searchIn
     */
    public static void personSearch(String searchIn) {
        Predicate<ContactDetail> search = n -> n.getFirstname().equals(searchIn) ? true : false;
        Consumer<ContactDetail> display = n -> System.out.println(n);
        addressMap.forEach((k, m) -> {
            m.getContactArray().stream().filter(search).forEach(display);
        });
    }
    public static void main(String args[])
    {
        AddressBook addressBook = new AddressBook();
        AddressBookMain AddressBook = new AddressBookMain();
        AddressBookCSV addressBookCSV = new AddressBookCSV();
        AddressBookFileIO addressBookFileIO = new AddressBookFileIO();
        List<ContactDetail> contactDetailList;
        while(true)
        {   Scanner sn=new Scanner(System.in);
            System.out.println("\n1.Adding a Address Book");
            System.out.println("\n2.Searching for a Person in Address Books");
            System.out.println("\n3. Displaying Contacts grouped by city");
            System.out.println("\n4. Displaying Contacts grouped by state");
            System.out.println("\n5. Number of Contacts according to city");
            System.out.println("\n6. Number of Contacts according to state");
            System.out.println("\n7. Write txt file");
            System.out.println("\n8. Read txt file");
            System.out.println("\n9. Write Data in CSV file");
            System.out.println("\n10. Read Data from CSV file");
            System.out.println("\n Enter Your Choice:");
            int num=sn.nextInt();
            switch(num)
            {
                case 1:
                    while (true) {
                        AddressBook main = new AddressBook();
                        System.out.println("Enter name for address Book");
                        String name = sn.next();
                        if (AddressBook.addressMap.containsKey(name)) {
                            System.out.println("\nDuplicate Address Book Entry not allowed \n");
                            continue;
                        } else {
                            AddressBook.addressMap.put(name, main);
                            System.out.println("\n  Address Book : " + name);
                            main.multipleAddressBook();
                            break;
                        }
                    }
                    break;
                case 2:
                    System.out.println("Enter city or state to search for a person");
                    String searchIn = sn.next();
                    AddressBook.personSearch(searchIn);
                    break;

                case 3:
                    displayContactsByCityGrouping();
                    break;
                case 4:
                    displayContactsByStateGrouping();
                    break;
                case 5:
                    numberByCity();
                    break;
                case 6:
                    numberByState();
                    break;
                case 7:
                    addressBook.printContact();
                    break;
                case 8:
                    addressBookFileIO.readContactFile();
                    addressBookFileIO.printContactFile();
                    break;
                case 9:
                    try {
                        addressBookCSV.writeContactDetailsToFile();
                    } catch (IOException | CsvRequiredFieldEmptyException | CsvDataTypeMismatchException e) {
                        e.printStackTrace();
                    }
                    break;
                case 10:
                    addressBookCSV.readFromCSVFile();
                    break;
            }
        }
    }
}