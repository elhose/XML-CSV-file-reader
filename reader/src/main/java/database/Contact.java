package database;

import lombok.Getter;

import java.util.Objects;

@Getter
public class Contact {
    private String contactString;
    private int value;

    public Contact(String contactString, int value) {
        this.contactString = contactString;
        this.value = value;
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

}
