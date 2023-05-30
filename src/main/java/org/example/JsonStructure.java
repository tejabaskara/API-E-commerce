package org.example;

import java.util.ArrayList;

public class JsonStructure {
    public static String user(int id, String first_name, String last_name, String email, String phone_number, String type){
        String hasil = "\t\t\"id\" : \"" + id +  "\",\n" +
                "\t\t\"first_name\" : \"" + first_name + "\",\n" +
                "\t\t\"last_name\" : \"" + last_name + "\",\n"+
                "\t\t\"email\" : \"" + email + "\",\n" +
                "\t\t\"phone_number\" : \"" + phone_number + "\",\n" +
                "\t\t\"type\" : \"" + type +"\"";

        return hasil;
    }
    public static String product(int id, int seller, String title, String description, String price, int stock){
        String hasil = "\t\t\"id\" : \"" + id +  "\",\n" +
                "\t\t\"seller\" : \"" + seller + "\",\n" +
                "\t\t\"title\" : \"" + title + "\",\n"+
                "\t\t\"description\" : \"" + description + "\",\n" +
                "\t\t\"price\" : \"" + price + "\",\n" +
                "\t\t\"stock\" : \"" + stock +"\"";
        return hasil;
    }

    public static String addresses(int users, String type, String line1, String line2, String city, String province, String postcode){
        String hasil = "\t\t\"users\" : \"" + users +  "\",\n" +
                "\t\t\"type\" : \"" + type + "\",\n" +
                "\t\t\"line1\" : \"" + line1 + "\",\n"+
                "\t\t\"line2\" : \"" + line2 + "\",\n" +
                "\t\t\"city\" : \"" + city + "\",\n" +
                "\t\t\"province\" : \"" + province +"\",\n" +
                "\t\t\"postcode\" : \"" + postcode +"\"";
        return hasil;
    }

    public static String orders(int id, int buyer, int note, int total, int discount, String isPaid){
        String hasil = "\t\t\"id\" : \"" + id +  "\",\n" +
                "\t\t\"buyer\" : \"" + buyer + "\",\n" +
                "\t\t\"note\" : \"" + note + "\",\n"+
                "\t\t\"total\" : \"" + total + "\",\n" +
                "\t\t\"discount\" : \"" + discount + "\",\n" +
                "\t\t\"is_paid\" : \"" + isPaid +"\"";
        return hasil;
    }

    public static String reviews(int order, int star, String description ){
        String hasil = "\t\t\"order\" : \"" + order +  "\",\n" +
                "\t\t\"star\" : \"" + star + "\",\n" +
                "\t\t\"description\" : \"" + description + "\"";
        return hasil;
    }

    public static String orderDetail(int order, int product, int quantity, int price){
        String hasil = "\t\t\"order\" : \"" + order +  "\",\n" +
                "\t\t\"product\" : \"" + product + "\",\n" +
                "\t\t\"quantity\" : \"" + quantity + "\",\n" +
                "\t\t\"price\" : \"" + price + "\"" ;
        return hasil;
    }
}
