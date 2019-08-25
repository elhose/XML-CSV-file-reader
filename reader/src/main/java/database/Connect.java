package database;

import read.FileCheck;
import read.ReaderCSV;
import read.ReaderXML;

import java.io.File;
import java.sql.*;
import java.util.List;
import java.util.Scanner;

public class Connect {
    private static int counts = 1;
    private static String url = "jdbc:sqlite:";

    public static String getUrl() {
        return url;
    }

    public static void setUrl(String url) {
        Connect.url = url;
    }

    public static void createNewDatabaseWithTables(String nameOfDatabase) {

        String connectionURl = url.concat(nameOfDatabase + ".db");
        setUrl(connectionURl);

        try (Connection conn = DriverManager.getConnection(connectionURl);
             Statement statement = conn.createStatement()) {
            if (conn != null) {
                //customers TABLE
                statement.execute("CREATE TABLE IF NOT EXISTS customers(ID INTEGER PRIMARY KEY, NAME TEXT NOT NULL, SURNAME TEXT NOT NULL, AGE INTEGER)");
                //CONTACTS table
                statement.execute("CREATE TABLE IF NOT EXISTS contacts(ID INTEGER PRIMARY KEY, ID_CUSTOMER INTEGER, TYPE INTEGER NOT NULL, CONTACT STRING)");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    public static void insertPerson(List<Person> persons) {

        String customersStatement = "INSERT INTO customers (NAME, SURNAME, AGE) VALUES(?,?,?)";
        String contactsStatement = "INSERT INTO contacts (ID_CUSTOMER, TYPE, CONTACT) VALUES(?,?,?)";

        try (Connection connection = DriverManager.getConnection(getUrl());
             PreparedStatement preparedCustomer = connection.prepareStatement(customersStatement, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement preparedContact = connection.prepareStatement(contactsStatement)) {

            connection.setAutoCommit(false);

            for (Person person : persons) {
                preparedCustomer.setString(1, person.getName());
                preparedCustomer.setString(2, person.getSurname());
                preparedCustomer.setString(3, person.getAge());
                preparedCustomer.execute();

                for (int i = 0; i < person.getContacts().size(); i++) {
                    preparedContact.setInt(1, counts);
                    preparedContact.setInt(2, person.getContacts().get(i).getValue());
                    preparedContact.setString(3, person.getContacts().get(i).getContactString());
                    preparedContact.execute();
                }

                System.out.println("Inserting record number " + counts++);
            }

            connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void runReader() {
        while (true) {

            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter path to CSV/XML file:");

            String path = scanner.nextLine();
            File file = new File(path);

            if (file.isFile() && !file.isDirectory() && file.exists()) {

                System.out.println("Enter name of Database:");
                String databaseName = scanner.nextLine();

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

    }


}
