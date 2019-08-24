package database;

import java.util.Objects;

public class Contact {
    private String contactString;
    private int value;

    public Contact(String contactString, int value) {
        this.contactString = contactString;
        this.value = value;
    }

    public String getContactString() {
        return contactString;
    }

    public int getValue() {
        return value;
    }


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

    @Override
    public String toString() {
        return "Contact{" +
                "contactString='" + contactString + '\'' +
                ", value=" + value +
                '}';
    }
}
