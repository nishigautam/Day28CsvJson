package com.addressbook.csv.json;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class AddressBookFileIO {
    private static String ADDRESS_BOOK_FILE = "C:\\Users\\Nishi\\IdeaProjects\\AddressCsvMavn\\src\\main\\resources";

    public void writeContactFile(List<ContactDetail> contactDetailList) {
        StringBuffer contactBuffer = new StringBuffer();
        contactDetailList.forEach(contact -> contactBuffer.append(contact.toString().getBytes()));
        try {
            Files.write(Paths.get(ADDRESS_BOOK_FILE),contactBuffer.toString().getBytes());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<ContactDetail> readContactFile() {
        List<ContactDetail> contactDetailList = new ArrayList<>();
        try {
            Files.lines(Paths.get(ADDRESS_BOOK_FILE + "Contact.txt")).forEach(lines -> {
                String[] contactRecord = lines.split(",");
                String firstName = contactRecord[0];
                String lastName = contactRecord[1];
                String address = contactRecord[2];
                String city = contactRecord[3];
                String state = contactRecord[4];
                String phoneNumber = contactRecord[5];
                int zipCode = Integer.parseInt(contactRecord[6]);

                ContactDetail contactDetailObj = new ContactDetail(firstName,lastName,address,city,state,phoneNumber,zipCode);
                contactDetailList.add(contactDetailObj);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contactDetailList;
    }

    public void printContactFile(){
        try {
            Files.lines(new File(ADDRESS_BOOK_FILE + "Contact.txt")
                    .toPath()).forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}