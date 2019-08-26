package database;

import lombok.Getter;

import java.util.Objects;

@Getter
public class Contact {

    // == fields
    private String contactString;
    private int value;

    // == constructors
    public Contact(String contactString, int value) {
        this.contactString = contactString;
        this.value = value;
    }

<<<<<<< HEAD
    // == public methods
=======
>>>>>>> 88d730c01a7e48410192b1590acb030b7838e0d9
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return value == contact.value &&
                Objects.equals(contactString, contact.contactString);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contactString, value);
    }

}
