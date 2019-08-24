package testing;

import read.CSVReader;
import read.FileCheck;
import read.XMLReader;

public class MainTest {

    public static void main(String[] args) {
        String pathCSV = "C:\\Users\\Jacek\\Desktop\\test.csv";
        String pathXML = "C:\\Users\\Jacek\\Desktop\\dane.xml";

//        MyReader reader = new MyReader(pathXML);
//        Scanner scanner = new Scanner(System.in);

        String path = pathXML;
        System.out.println("podaj ścieżkę do pliku:");
        System.out.println("pdoaj ścieżkę do bazy danych lub wpisz utwórz");


        System.out.println(" WYBRAŁEŚ:   " + path);
        if (FileCheck.checkFileExtension(path).equalsIgnoreCase("csv")){
            CSVReader csvReader = new CSVReader(path);
            csvReader.readCSV();
        }else if (FileCheck.checkFileExtension(path).equalsIgnoreCase("xml")){
            XMLReader xmlReader = new XMLReader(path);
            xmlReader.readXML();

        }else {
            System.out.println("File extension is not valid. Try changing path.");
        }
    }

}
