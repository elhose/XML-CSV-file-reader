package database;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import read.FileCheck;
import read.ReaderCSV;
import read.ReaderXML;

import java.io.File;
import java.sql.*;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class Connect {

    // == fields
    @Getter @Setter
    private static String url = "jdbc:sqlite:";

    // == private methods
    private static void createNewDatabaseWithTables(String nameOfDatabase) {

        String connectionURl = url.concat(nameOfDatabase + ".db");
        setUrl(connectionURl);

        String CREATE_CUSTOMERS_TABLE = "CREATE TABLE IF NOT EXISTS customers(ID INTEGER , NAME TEXT NOT NULL, SURNAME TEXT NOT NULL, AGE INTEGER, PRIMARY KEY (ID))";
        String CREATE_CONTACTS_TABLE = "CREATE TABLE IF NOT EXISTS contacts(ID INTEGER , ID_CUSTOMER INTEGER, TYPE INTEGER NOT NULL, CONTACT STRING, PRIMARY KEY (ID), FOREIGN KEY (ID_CUSTOMER) REFERENCES customers(ID))";

        try (Connection conn = DriverManager.getConnection(connectionURl);
             Statement statement = conn.createStatement()) {

                //customers TABLE
                statement.execute(CREATE_CUSTOMERS_TABLE);
                //CONTACTS table
                statement.execute(CREATE_CONTACTS_TABLE);


        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    private static void insertPerson(List<Person> persons) {

        String CUSTOMER_STATEMENT = "INSERT INTO customers (NAME, SURNAME, AGE) VALUES(?,?,?)";
        String CONTACTS_STATEMENT = "INSERT INTO contacts (ID_CUSTOMER, TYPE, CONTACT) VALUES(?,?,?)";

        try (Connection connection = DriverManager.getConnection(getUrl());
             PreparedStatement preparedCustomer = connection.prepareStatement(CUSTOMER_STATEMENT, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement preparedContact = connection.prepareStatement(CONTACTS_STATEMENT)) {

            connection.setAutoCommit(false);

            for (Person person : persons) {
                preparedCustomer.setString(1, person.getName());
                preparedCustomer.setString(2, person.getSurname());
                preparedCustomer.setString(3, person.getAge());
                preparedCustomer.executeUpdate();

                ResultSet rs = preparedCustomer.getGeneratedKeys();
                int ID_CUSTOMER = 0;

                if (rs.next()){
                    ID_CUSTOMER = rs.getInt(1);
                }

                for (int i = 0; i < person.getContacts().size(); i++) {


                    preparedContact.setInt(1, ID_CUSTOMER);
                    preparedContact.setInt(2, person.getContacts().get(i).getValue());
                    preparedContact.setString(3, person.getContacts().get(i).getContactString());
                    preparedContact.executeUpdate();
                }

                System.out.println("Inserting record number: " + ID_CUSTOMER);

            }

            connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();

        }

    }

    // == public methods
    public static void runReader() {
        long startTime;
        long endTime;
        while (true) {

            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter path to CSV/XML file:");

            String path = scanner.nextLine();
            File file = new File(path);

            if (file.isFile() && !file.isDirectory() && file.exists()) {

                System.out.println("Enter name of Database:");
                String databaseName = scanner.nextLine();

                startTime = System.nanoTime();

                Connect.createNewDatabaseWithTables(databaseName);

                if (FileCheck.checkFileExtension(path).equalsIgnoreCase("csv")) {
                    ReaderCSV readerCsv = new ReaderCSV(path);
                    readerCsv.readCSV();
                    Connect.insertPerson(readerCsv.getPersons());
                    break;
                } else if (FileCheck.checkFileExtension(path).equalsIgnoreCase("xml")) {
                    ReaderXML readerXML = new ReaderXML(path);
                    readerXML.readXML();
                    Connect.insertPerson(readerXML.getListPersons());
                    break;
                }
            } else {
                System.out.println("Wrong path to file. It must be full (with name and extension of file).");

            }

        }

        endTime = System.nanoTime();
        System.out.println("Time of Execution in ms: " + (endTime-startTime)/1000000);

    }


}
