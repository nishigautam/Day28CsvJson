package com.addressbook.csv.json;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class AddressBookCSV {
    public String addressBookCSV;
    public AddressBookCSV(String addressBookCSV) {
        super();
        this.addressBookCSV = addressBookCSV;
    }

    public void writeContactDetailsToFile(List<ContactDetail> contactDetailList) {
        try (Writer fileWriter = Files.newBufferedWriter(Paths.get(this.addressBookCSV));)
        {
            StatefulBeanToCsv<ContactDetail> beanToCsv = new StatefulBeanToCsvBuilder(fileWriter)
                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER).build();
            beanToCsv.write(contactDetailList);
        }
        catch (CsvDataTypeMismatchException | CsvRequiredFieldEmptyException | IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public int readFromCSVFile() {
        List<ContactDetail> contactDetailList = new ArrayList<>();
        try (Reader reader = Files.newBufferedReader(Paths.get(this.addressBookCSV));)
        {
            CSVReader csvReader = new CSVReader(reader);
            List<String[]> contactFileStrings = csvReader.readAll();
            contactFileStrings.remove(0);
            contactFileStrings.stream().forEach(contactRecord -> {
                String firstName = contactRecord[0];
                String lastName = contactRecord[1];
                String address = contactRecord[2];
                String city = contactRecord[3];
                String state = contactRecord[4];
                String phoneNumber = contactRecord[5];
                int zipCode = Integer.parseInt(contactRecord[6]);

                ContactDetail contactDetailObj = new ContactDetail(firstName, lastName, address, city, state, phoneNumber, zipCode);
                contactDetailList.add(contactDetailObj);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contactDetailList.size();
    }
}
