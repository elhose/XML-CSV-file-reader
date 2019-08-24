package read;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.*;

public class Reader {

    // == fields
    private String filePath;

    // == constructors
    public Reader(String filePath) {
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

    public void readCSV(){
        try(BufferedReader csvReader = new BufferedReader(new FileReader(filePath))){
            String row;
            csvReader.readLine();
            while ((row = csvReader.readLine()) != null){
                String[] array = row.split(",");

                String name = array[0];
                String surname = array[1];
                String age = array[2];
                String city = array[3];

                for (int i=4; i<array.length; i++){

                    if (array[i].matches("(^\\d{9}$|^\\d{3}.\\d{3}.\\d{3}$)")){
                        System.out.println(array[i] + " - it's a number INT 2");
                    } else if (array[i].matches("^\\w+\\@\\w+\\.\\w+$")){
                        System.out.println(array[i] + " - it's an email INT 1");
                    }else if (array[i].matches("^jbr:\\w+\\@\\w+\\.\\w+$")){
                        System.out.println(array[i] + " - it's a jabber INT 3");
                    }else {
                        System.out.println(array[i] + " - it's unknown INT 0");
                    }

                }

                System.out.println("NEXT PERSON XDDD");
            }

        }catch (IOException e){
            e.printStackTrace();
        }
    }




    public void readXML(){
//        try {
//
//            File fXmlFile = new File(filePath);
//            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
//            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
//            Document doc = dBuilder.parse(fXmlFile);
//
//            //optional, but recommended
//            //read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
//            doc.getDocumentElement().normalize();
//
//            System.out.println("Root element : " + doc.getDocumentElement().getNodeName());
//
//            NodeList nList = doc.getElementsByTagName("person");
//
//            System.out.println("----------------------------");
//
//            for (int temp = 0; temp < nList.getLength(); temp++) {
//
//                Node nNode = nList.item(temp);
//
//                System.out.println("\nCurrent Element :" + nNode.getNodeName());
//
//                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
//
//                    Element eElement = (Element) nNode;
//
//                    System.out.println("Name : " + eElement.getElementsByTagName("name").item(0).getTextContent());
//                    System.out.println("Surname : " + eElement.getElementsByTagName("surname").item(0).getTextContent());
//                    System.out.println("Age : " + eElement.getElementsByTagName("age").item(0).getTextContent());
//                    System.out.println("City : " + eElement.getElementsByTagName("city").item(0).getTextContent());
//
//                    for (int i=0;i<eElement.getElementsByTagName("contact").getLength();i++){
//                        System.out.println(eElement.getElementsByTagName("contact").item(i).getTextContent());
//                    }
//
//                }
//
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        try{
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            HandlerXML handlerXML = new HandlerXML();

            File file = new File(filePath);
            

            InputSource is = new InputSource(reader);
            is.setEncoding("UTF-8");

            saxParser.parse(is, handler);

        }catch (SAXException | ParserConfigurationException | FileNotFoundException | UnsupportedEncodingException e){
            e.printStackTrace();
        }






    }




}
