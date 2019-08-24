package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConncetMain {
    public static void main(String[] args) {

        try{
            Connection connection = DriverManager.getConnection("jdbc:sqlite:D:\\ProgramowanieJAVA\\CSV,XML contact reader\\database_location\\contactsDatabase.db");

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
