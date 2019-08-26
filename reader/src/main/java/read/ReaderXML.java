package read;

import database.Person;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class ReaderXML implements FileCheck {

    // == fields
    private String filePath;
    private HandlerXML handlerXML;

    // == constructors
    public ReaderXML(String filePath) {
        this.filePath = filePath;
    }

    // == public methods
<<<<<<< HEAD
=======

>>>>>>> 88d730c01a7e48410192b1590acb030b7838e0d9
    public List<Person> getListPersons() {
        return handlerXML.getPersons();
    }

    public void readXML() {

        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            handlerXML = new HandlerXML();

            File file = new File(filePath);
            InputStream inputStream = new FileInputStream(file);
            Reader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);

            InputSource is = new InputSource(reader);
            is.setEncoding("UTF-8");

            saxParser.parse(is, handlerXML);

        } catch (SAXException | ParserConfigurationException | IOException e) {
            e.printStackTrace();
        }

    }

}
