package database;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
public class Person {

    // == fields
    private String name;
    private String surname;

    @Setter(AccessLevel.NONE)
    private String age;

    @Getter(AccessLevel.NONE)
    private String city;

    @Setter(AccessLevel.NONE)
    private List<Contact> contacts = new ArrayList<>();

    // == public methods
    public void setAge(String age) {
        if (age.equals("")){
            this.age = null;
        }else {
            this.age = age;
        }
    }

}
