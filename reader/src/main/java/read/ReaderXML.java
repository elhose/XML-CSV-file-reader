package read;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.*;

public class ReaderXML implements FileCheck{

    // == fields
    private String filePath;

    // == constructors
    public ReaderXML(String filePath) {
        this.filePath = filePath;
    }

    // == public methods
    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
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
