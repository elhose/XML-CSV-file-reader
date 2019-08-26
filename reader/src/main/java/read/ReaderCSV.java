package read;

import database.Contact;
import database.Person;
import lombok.Getter;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReaderCSV implements FileCheck, HandleContactType {

    // == fields
    private String filePath;
    private Person person;
    @Getter private List<Person> persons = new ArrayList<>();

    // == constructors
    public ReaderCSV(String filePath) {
        this.filePath = filePath;
    }

    // == public methods

    public void readCSV() {

        try (FileInputStream inputStream = new FileInputStream(this.filePath);
             Scanner scanner = new Scanner(inputStream, "UTF-8")) {

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] array = line.split(",");

                String name = array[0];
                String surname = array[1];
                String age = array[2];
                String city = array[3];

                person = new Person();

                person.setName(name);
                person.setSurname(surname);
                person.setAge(age);
                person.setCity(city);

                for (int i = 4; i < array.length; i++) {
                    person.getContacts().add(new Contact(array[i], handleContact(array[i])));
                }

                persons.add(person);
            }
            // note that Scanner suppresses exceptions
            if (scanner.ioException() != null) {
                throw scanner.ioException();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int handleContact(String contactType) {

        if (contactType.matches("^\\w+\\@\\w+\\.\\w+$")) {
            return 1;
        } else if (contactType.matches("(^\\d{9}$|^\\d{3}.\\d{3}.\\d{3}$)")) {
            return 2;
        } else if (contactType.matches("^jbr:\\w+\\@\\w+\\.\\w+$")) {
            return 3;
        } else {
            return 0;
        }
    }


}
