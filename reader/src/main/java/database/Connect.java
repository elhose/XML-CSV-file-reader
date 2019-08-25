package database;

import read.FileCheck;
import read.ReaderCSV;
import read.ReaderXML;

import java.sql.*;
import java.util.List;
import java.util.Scanner;

public class Connect {
    static int counts =0;
    public static void main(String[] args) {

        String path = "D:\\ProgramowanieJAVA\\CSV,XML contact reader\\database_location\\";
        String dbName = "bazaDanych.db";
        String name = "Agata";
        String surname = "Sawko";
        String age = "23";
        String city = "Łomża";

        int id_customer = 112313;
        String contact = "twasdasdasda.com";
        int type = 2;

//        createNewDatabase(dbName);

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:bazaDanych.db");
             Statement statement = connection.createStatement()) {

            //customers TABLE
            statement.execute("CREATE TABLE IF NOT EXISTS customers(ID INTEGER PRIMARY KEY, name TEXT NOT NULL, surname TEXT NOT NULL, age INTEGER)");
//            statement.execute("INSERT INTO CUSTOMERS (NAME, SURNAME, AGE) VALUES(" +"'"+name+"', '"+ surname + "', " + age +")");

            //CONTACTS table
            statement.execute("CREATE TABLE IF NOT EXISTS contacts(ID INTEGER PRIMARY KEY, ID_CUSTOMER INTEGER, TYPE INTEGER NOT NULL, CONTACT STRING)");
//            statement.execute("INSERT INTO contacts (ID_CUSTOMER, TYPE, CONTACT) VALUES("+ id_customer+", "+type+ ", '" + contact+ "'" +")");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }

    public static void createNewDatabase(String fileName) {

        String url = "jdbc:sqlite:" + fileName;

        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    public static void insertPerson(List<Person> persons){

        try(Connection connection = DriverManager.getConnection("jdbc:sqlite:bazaDanych.db");
            Statement statement = connection.createStatement()){

            String customersStatement = "INSERT INTO customers (NAME, SURNAME, AGE) VALUES(?,?,?)";
            String contactsStatement = "INSERT INTO contacts (ID_CUSTOMER, TYPE, CONTACT) VALUES(?,?,?)";

            for (Person person:persons){
                PreparedStatement preparedStatement = connection.prepareStatement(customersStatement);
                preparedStatement.setString(1,person.getName());
                preparedStatement.setString(2,person.getSurname());
                preparedStatement.setString(3,person.getAge());
                preparedStatement.execute();

                for (int i=0;i<person.getContacts().size();i++){
                    PreparedStatement prp = connection.prepareStatement(contactsStatement);
                    prp.setInt(1,1);
                    prp.setInt(2,person.getContacts().get(i).getValue());
                    prp.setString(3,person.getContacts().get(i).getContactString());
                    prp.execute();
                }
                System.out.println(counts++);
            }



//            for (Person person: persons){
//                statement.execute("INSERT INTO customers (NAME, SURNAME, AGE) VALUES(" +"'"+person.getName()+"', '"+ person.getSurname() + "', " + person.getAge() +")");
//
//                for (int i=0;i<person.getContacts().size();i++){
//                    statement.execute("INSERT INTO contacts (ID_CUSTOMER, TYPE, CONTACT) VALUES("+ 1+", "+person.getContacts().get(i).getValue()+ ", '" + person.getContacts().get(i).getContactString()+ "'" +")");
//                }
//                System.out.println(counts++);
//            }

        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    public static void runReader(){

//        String pathCSV = "C:\\Users\\Jacek\\Desktop\\test.csv";
//        String pathXML = "C:\\Users\\Jacek\\Desktop\\dane.xml";
//
//        String path = pathCSV;


        try (Scanner scanner = new Scanner(System.in)){

            System.out.println("Enter path to CSV/XML file:");
            String path = scanner.nextLine();

            if (FileCheck.checkFileExtension(path).equalsIgnoreCase("csv")){
                ReaderCSV readerCsv = new ReaderCSV(path);
                readerCsv.readCSV();
                Connect.insertPerson(readerCsv.getPersons());

            }else if (FileCheck.checkFileExtension(path).equalsIgnoreCase("xml")){
                ReaderXML readerXML = new ReaderXML(path);
                readerXML.readXML();

            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("File extension is not valid. Try changing path.");
        }
    }


}
