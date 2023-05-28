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
    public static String product(int id, int seller, String title, String description, String price, int stock){
        String hasil = "\t\"id\" : \"" + id +  "\"\n" +
                "\t\"seller\" : \"" + seller + "\"\n" +
                "\t\"title\" : \"" + title + "\"\n"+
                "\t\"description\" : \"" + description + "\"\n" +
                "\t\"price\" : \"" + price + "\"\n" +
                "\t\"stock\" : \"" + stock +"\"";
        return hasil;
    }

    public static String addresses(int users, String type, String line1, String line2, String city, String province, String postcode){
        String hasil = "\t\"users\" : \"" + users +  "\"\n" +
                "\t\"type\" : \"" + type + "\"\n" +
                "\t\"line1\" : \"" + line1 + "\"\n"+
                "\t\"line2\" : \"" + line2 + "\"\n" +
                "\t\"city\" : \"" + city + "\"\n" +
                "\t\"province\" : \"" + province +"\"\n" +
                "\t\"postcode\" : \"" + postcode +"\"";
        return hasil;
    }

    public static String orders(int id, int buyer, int note, int total, int discount, String isPaid){
        String hasil = "\t\"id\" : \"" + id +  "\"\n" +
                "\t\"buyer\" : \"" + buyer + "\"\n" +
                "\t\"note\" : \"" + note + "\"\n"+
                "\t\"total\" : \"" + total + "\"\n" +
                "\t\"discount\" : \"" + discount + "\"\n" +
                "\t\"is_paid\" : \"" + isPaid +"\"";
        return hasil;
    }

    public static String reviews(int order, int star, String description ){
        String hasil = "\t\"order\" : \"" + order +  "\"\n" +
                "\t\"star\" : \"" + star + "\"\n" +
                "\t\"description\" : \"" + description + "\"";
        return hasil;
    }

    public static String orderDetail(int order, int product, int quantity, int price){
        String hasil = "\t\"order\" : \"" + order +  "\"\n" +
                "\t\"product\" : \"" + product + "\"\n" +
                "\t\"quantity\" : \"" + quantity + "\"\n" +
                "\t\"price\" : \"" + price + "\"" ;
        return hasil;
    }
}
