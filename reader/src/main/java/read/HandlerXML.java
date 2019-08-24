package read;

import database.Contact;
import database.Person;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class HandlerXML extends DefaultHandler implements HandleContactType {

   private Person person;
   private boolean nameMarker;
   private boolean surnameMarker;
   private boolean ageMarker;
   private boolean cityMarker;
   private boolean contactMarker;
   private String contactType;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
//        System.out.println("Start Element :" + qName);

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
//        System.out.println("End Element :" + qName);

        if (qName.equalsIgnoreCase("person")){
            System.out.println(this.person.toString());
//            this.person = null;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
//        System.out.println(new String(ch, start, length));


        if (nameMarker) {
//            System.out.println("name : " + new String(ch, start, length));
            nameMarker = false;
            person.setName(new String(ch, start, length));
        }

        if (surnameMarker) {
//            System.out.println("surname : " + new String(ch, start, length));
            surnameMarker = false;
            person.setSurname(new String(ch, start, length));
        }

        if (ageMarker) {
//            System.out.println("age : " + new String(ch, start, length));
            ageMarker = false;
            person.setAge(new String(ch, start, length));
        }

        if (cityMarker) {
//            System.out.println("city : " + new String(ch, start, length));
            cityMarker = false;
            person.setCity(new String(ch, start, length));
        }

        if (contactMarker) {
//            System.out.println("city : " + new String(ch, start, length));
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
