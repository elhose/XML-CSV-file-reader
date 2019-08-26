package read;

import database.Contact;
import database.Person;
import lombok.Getter;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class HandlerXML extends DefaultHandler implements HandleContactType {

    // == fields
   private Person person;
   private boolean nameMarker;
   private boolean surnameMarker;
   private boolean ageMarker;
   private boolean cityMarker;
   private boolean contactMarker;
   private String contactType;

<<<<<<< HEAD
   // == public methods
=======
>>>>>>> 88d730c01a7e48410192b1590acb030b7838e0d9
   @Getter
   private List<Person> persons = new ArrayList<>();


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        if (qName.equalsIgnoreCase("person")){
            this.person = new Person();
        }

        if (qName.equalsIgnoreCase("NamE")) {
            nameMarker = true;
        }

        if (qName.equalsIgnoreCase("surname")) {
            surnameMarker = true;
        }

        if (qName.equalsIgnoreCase("age")) {
            ageMarker = true;
        }

        if (qName.equalsIgnoreCase("city")) {
            cityMarker = true;
        }

        if (qName.equalsIgnoreCase("contact")) {
            contactMarker = true;
            contactType  = attributes.getValue("type");
        }

    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {

        if (qName.equalsIgnoreCase("person")){
            persons.add(this.person);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {

        if (nameMarker) {
            nameMarker = false;
            person.setName(new String(ch, start, length));
        }

        if (surnameMarker) {
            surnameMarker = false;
            person.setSurname(new String(ch, start, length));
        }

        if (ageMarker) {
            ageMarker = false;
            person.setAge(new String(ch, start, length));
        }

        if (cityMarker) {
            cityMarker = false;
            person.setCity(new String(ch, start, length));
        }

        if (contactMarker) {
            contactMarker = false;
            person.getContacts().add(new Contact(new String(ch, start, length), handleContact(contactType)));
        }

    }

    @Override
    public int handleContact(String contactType) {
        switch (contactType){
            case "email":{
                return 1;
            }
            case "phone":{
                return 2;
            }
            case "jabber":{
                return 3;
            }
            default: {
                return 0;
            }
        }
    }

}
