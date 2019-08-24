package testing;

import database.ConnectMain;
import read.FileCheck;
import read.ReaderCSV;
import read.ReaderXML;

public class MainTest {

    public static void main(String[] args) {
        String pathCSV = "C:\\Users\\Jacek\\Desktop\\test.csv";
        String pathXML = "C:\\Users\\Jacek\\Desktop\\dane.xml";

        String path = pathCSV;

        System.out.println("podaj ścieżkę do pliku:");
        System.out.println("pdoaj ścieżkę do bazy danych lub wpisz utwórz");

        System.out.println("WYBRAŁEŚ:   " + path);

        if (FileCheck.checkFileExtension(path).equalsIgnoreCase("csv")){
            ReaderCSV readerCsv = new ReaderCSV(path);
            readerCsv.readCSV();
            ConnectMain.insertPerson(readerCsv.getPersons());

        }else if (FileCheck.checkFileExtension(path).equalsIgnoreCase("xml")){
            ReaderXML readerXML = new ReaderXML(path);
            readerXML.readXML();

        }else {
            System.out.println("File extension is not valid. Try changing path.");
        }

    }

}
