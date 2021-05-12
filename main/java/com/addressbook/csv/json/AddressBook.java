package com.addressbook.csv.json;


import java.net.PortUnreachableException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author Mentalist
 *
 */
public class AddressBook {
    public enum IOService{CONSOLE_IO, FILE_IO, DB_IO, REST_IO}

    private ArrayList<ContactDetail> contactArray;
    private Map<String, ContactDetail> contactMap;
    private Map<String,ContactDetail> cityToContactEntryMap;
    private Map<String,ContactDetail> stateToContactEntryMap;
    AddressBookFileIO addressBookFileIO = new AddressBookFileIO();
    public AddressBook() {
        contactArray = new ArrayList<>();
        contactMap = new HashMap<>();
        cityToContactEntryMap=new TreeMap<String,ContactDetail>();
        stateToContactEntryMap=new TreeMap<String,ContactDetail>();
    }

    public List<ContactDetail> getContactArray(){
        return contactArray;
    }
    public Map<String,ContactDetail> getContactMap()
    {
        return contactMap;
    }
    /**
     * uc2
     */
    public void addNewContact() {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter First Name of the contact");
        String firstName = s.nextLine();
        for(int i=1;i>0;i++){
            if(duplicateEntryValidation(firstName))
                continue;
            else
                break;
        }
        System.out.println("Enter Last Name of the contact");
        String lastName = s.nextLine();
        System.out.println("Enter Address of the contact");
        String address = s.next();
        System.out.println("Enter City of the contact");
        String city = s.next();
        System.out.println("Enter State of the contact");
        String state = s.next();
        System.out.println("Enter Phone Number of the contact");
        String phoneNumber = s.next();
        System.out.println("Enter ZipCode of the contact");
        int zipcode = s.nextInt();
        ContactDetail contact = new ContactDetail(firstName, lastName, address, city, state, phoneNumber, zipcode);
        contactArray.add(contact);
        contactMap.put(firstName, contact);
    }

    /**
     * uc3
     */
    public void editContact() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter The First Name Whose contact details is to be Edited: ");
        String fn = sc.nextLine();
        ContactDetail obj = contactMap.get(fn);
        System.out.println();
        System.out.println("Enter the Address");
        String address = sc.nextLine();
        obj.setAddress();
        System.out.println("Enter the City");
        String city = sc.next();
        obj.setCity();
        System.out.println("Enter the State");
        String state = sc.next();
        obj.setState();
        System.out.println("Enter the PhoneNumber");
        String phone = sc.next();
        obj.setPhoneNumber();
        System.out.println("Enter the Zipcode");
        int zip = sc.nextInt();
        obj.setZipcode(zip);
    }

    public void printContact() {
        System.out.println(contactArray);
    }

    /**
     * uc4
     */
    public void deleteContact() {
        Scanner sd = new Scanner(System.in);
        System.out.println("Enter The First Name to delete the contact details");
        String first = sd.nextLine();
        ContactDetail obj = contactMap.get(first);
        contactArray.remove(obj);
    }

    /**
     * uc5
     * &uc 6
     *args
     */
    public void  multipleAddressBook() {
        Scanner sa = new Scanner(System.in);
        for(int i=1;i>0;i++)
        {
            System.out.println("\n1. Add Contact Details");
            System.out.println("\n2. Edit Contact Details");
            System.out.println("\n3. Delete Contact Details");
            System.out.println("\n4. Sorting Contact Details by Person's name:");
            System.out.println("\n5. Sorting Contact Details City:");
            System.out.println("\n6. Sorting Contact Details by ZipCode:");
            System.out.println("\n7. Sorting Contact Details by State:");
            System.out.println("\n8. Exit");
            System.out.println("\nEnter your choice");
            int index=sa.nextInt();
            switch(index)
            {
                case 1:addNewContact();
                    break;
                case 2:if(contactArray.size()==0)
                    System.out.println("Please Enter the next contacts");
                else
                    editContact();
                    break;
                case 3:if(contactArray.size()==0)
                    System.out.println("Plese Enter the next contacts");
                else
                    deleteContact();
                    break;
                case 4:alphabeticSorting();
                case 5:sortingByCity();
                case 6:sortingByZip();
                case 7:sortingByState();
                case 8:System.out.println("Exit");
                    System.exit(0);
                    break;
            }
        }
    }

    /**
     * uc7
     * @return
     */
    public boolean duplicateEntryValidation(String name) {
        Predicate<ContactDetail> checkName = n -> n.equals(name);
        boolean result = contactArray.stream().anyMatch(checkName);
        return result;
    }
    /**
     * uc11
     */
    public void alphabeticSorting()
    {
        List<ContactDetail> sortedList = contactArray.stream()
                .sorted(Comparator.comparing(ContactDetail::getFirstname))
                .collect(Collectors.toList());
        sortedList.forEach(System.out::println);
    }
    /**
     * uc12
     */
    public void sortingByCity()
    {
        List<ContactDetail> sortedList = contactArray.stream()
                .sorted(Comparator.comparing(ContactDetail::getCity))
                .collect(Collectors.toList());
        sortedList.forEach(System.out::println);
    }
    public void sortingByZip()
    {
        List<ContactDetail> sortedList = contactArray.stream()
                .sorted(Comparator.comparingInt(ContactDetail::getZipcode))
                .collect(Collectors.toList());
        sortedList.forEach(System.out::println);
    }
    public void sortingByState()
    {
        List<ContactDetail> sortedList = contactArray.stream()
                .sorted(Comparator.comparing(ContactDetail::getState))
                .collect(Collectors.toList());
        sortedList.forEach(System.out::println);
    }

    public void writeDataInContactBook(IOService ioService) {
        if(ioService.equals(IOService.CONSOLE_IO))
            System.out.println("Console :" + contactArray);
    }
}