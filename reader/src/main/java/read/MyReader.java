package read;

import database.Person;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.*;
import java.util.Scanner;

public class MyReader {

    // == fields
    private String filePath;

    // == constructors
    public MyReader(String filePath) {
        this.filePath = filePath;
    }

    // == public methods
    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String checkFileExtension(){
        String fileExtension = filePath.substring(filePath.lastIndexOf(".")+1);
        return fileExtension;
    }

    public void readCSV() {
//        try(BufferedReader csvReader = new BufferedReader(new FileReader(filePath))){
//            String row;
//            csvReader.readLine();
//            while ((row = csvReader.readLine()) != null){
//                String[] array = row.split(",");
//
//                String name = array[0];
//                String surname = array[1];
//                String age = array[2];
//                String city = array[3];
//
//                for (int i=4; i<array.length; i++){
//
//                    if (array[i].matches("(^\\d{9}$|^\\d{3}.\\d{3}.\\d{3}$)")){
//                        System.out.println(array[i] + " - it's a number INT 2");
//                    } else if (array[i].matches("^\\w+\\@\\w+\\.\\w+$")){
//                        System.out.println(array[i] + " - it's an email INT 1");
//                    }else if (array[i].matches("^jbr:\\w+\\@\\w+\\.\\w+$")){
//                        System.out.println(array[i] + " - it's a jabber INT 3");
//                    }else {
//                        System.out.println(array[i] + " - it's unknown INT 0");
//                    }
//
//                }
//
//                System.out.println("NEXT PERSON XDDD");
//            }
//
//        }catch (IOException e){
//            e.printStackTrace();
//        }


        try (FileInputStream inputStream = new FileInputStream(this.filePath);
             Scanner scanner = new Scanner(inputStream, "UTF-8")) {

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] array = line.split(",");

                String name = array[0];
                String surname = array[1];
                String age = array[2];
                String city = array[3];

                Person person = new Person();

                person.setName(name);
                person.setSurname(surname);
                person.setAge(age);
                person.setCity(city);

                for (int i=4;i<array.length;i++){

                }




                System.out.println();
            }
            // note that Scanner suppresses exceptions
            if (scanner.ioException() != null) {
                throw scanner.ioException();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void readXML(){

        try{
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            HandlerXML handlerXML = new HandlerXML();

            File file = new File(filePath);
            InputStream inputStream= new FileInputStream(file);
            Reader reader = new InputStreamReader(inputStream,"UTF-8");

            InputSource is = new InputSource(reader);
            is.setEncoding("UTF-8");

            saxParser.parse(is, handlerXML);

        }catch (SAXException | ParserConfigurationException |  IOException e){
            e.printStackTrace();
        }

    }




}
