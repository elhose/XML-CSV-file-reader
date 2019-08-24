package testing;

import read.Reader;

public class MainTest {

    public static void main(String[] args) {
        String pathCSV = "C:\\Users\\Jacek\\Desktop\\test.csv";
        String pathXML = "C:\\Users\\Jacek\\Desktop\\dane.xml";
        Reader reader = new Reader(pathXML);

        if (reader.checkFileExtension().equals("csv")){
            //split file
            //read file
            reader.readCSV();
            //send to base
        }else if (reader.checkFileExtension().equals("xml")){
            //split file
            //read file
            reader.readXML();
            //send to base
        }else {
            return;
        }
    }

}
