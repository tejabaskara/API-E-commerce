package org.example;

import java.util.ArrayList;

public class JsonStructure {
    public static String user(int id, String first_name, String last_name, String email, String phone_number, String type){
        String hasil = "\t\"id\" : \"" + id +  "\"\n" +
                "\t\"first_name\" : \"" + first_name + "\"\n" +
                "\t\"last_name\" : \"" + last_name + "\"\n"+
                "\t\"email\" : \"" + email + "\"\n" +
                "\t\"phone_number\" : \"" + phone_number + "\"\n" +
                "\t\"type\" : \"" + type +"\"";

        return hasil;
    }
}
