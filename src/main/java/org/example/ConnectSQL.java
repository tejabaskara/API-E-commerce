package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class ConnectSQL {

    private Connection connect(){
        Connection connection = null;
        String rootPath = System.getProperty("user.dir");
        String url = "jdbc:sqlite:" + rootPath + "/DB_Api.db";
        try{
            connection = DriverManager.getConnection(url);
            System.out.println("connection ok");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return connection;
    }

    public void inputUsers(int id, String firstName, String lastName, String email, String phoneNumber, String type) {
        String sql = "INSERT INTO users( id, first_name, last_name, email, phone_number, type) VALUES(?,?,?,?,?,?)";
        try{
            Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.setString(2, firstName );
            pstmt.setString(3, lastName );
            pstmt.setString(4, email );
            pstmt.setString(5, phoneNumber );
            pstmt.setString(6, type );
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void inputProducts(int id, int seller, String title, String description, String price, int stock) {
        String sql = "INSERT INTO products( id, seller, title, description, price, stock) VALUES(?,?,?,?,?,?)";
        try{
            Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.setInt(2, seller );
            pstmt.setString(3, title );
            pstmt.setString(4, description );
            pstmt.setString(5, price );
            pstmt.setInt(6, stock );
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void inputAddresses(int id, String type, String line1, String line2, String city, String province, String postcode) {
        String sql = "INSERT INTO addresses( id, type, line1, line2, city, province, postcode) VALUES(?,?,?,?,?,?,?)";
        try{
            Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.setString(2, type );
            pstmt.setString(3, line1 );
            pstmt.setString(4, line2 );
            pstmt.setString(5, city );
            pstmt.setString(6, province);
            pstmt.setString(7, postcode);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void inputReviews(int order, int star, String description) {
        String sql = "INSERT INTO reviews( order, star, description) VALUES(?,?,?)";
        try{
            Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, order);
            pstmt.setInt(2, star );
            pstmt.setString(3, description );
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void inputOrders(int id, int buyer, int note, int total, int discount, String isPaid) {
        String sql = "INSERT INTO order(id, buyer, note, total, discount, is_paid) VALUES(?,?,?,?,?,?)";
        try{
            Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.setInt(2, buyer );
            pstmt.setInt(3, note );
            pstmt.setInt(4, total);
            pstmt.setInt(5, discount);
            pstmt.setString(6, isPaid);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void inputOrdersDetails(int order, int product, int quantity, int price) {
        String sql = "INSERT INTO order_details(order,product,quantity,price) VALUES(?,?,?,?)";
        try{
            Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, order);
            pstmt.setInt(2, product );
            pstmt.setInt(3, quantity );
            pstmt.setInt(4, price);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public String appendString(ArrayList<String> json ){
        StringBuffer gabung = new StringBuffer();
        for (String s : json) {
            gabung.append(s);
            gabung.append("\n\n");
        }
        String hasil = gabung.toString();
        return hasil;
    }

    public String selectAllUser(){
        String sql = "SELECT * FROM users";
        ArrayList<String> hasil = new ArrayList<String>();

        try {
            Connection connect = this.connect();
            Statement stmt  = connect.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);
            // loop through the result set
            while (rs.next()) {
                hasil.add(JsonStructure.user(rs.getInt("id"), rs.getString("first_name"),
                        rs.getString("last_name"), rs.getString("email") ,
                        rs.getString("phone_number"), rs.getString("type") ));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        String jsonKirim = "{"+ "\n" + appendString(hasil)+"\n"+"}";
        return jsonKirim;
    }

    public String selectId(String id){
        String sql = "SELECT * FROM users WHERE"+ id;
        ArrayList<String> hasil = new ArrayList<String>();

        try {
            Connection connect = this.connect();
            Statement stmt  = connect.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);


            // loop through the result set
            while (rs.next()) {
                hasil.add(JsonStructure.user(rs.getInt("id"), rs.getString("first_name"),
                        rs.getString("last_name"), rs.getString("email") ,
                        rs.getString("phone_number"), rs.getString("type") ));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        String jsonKirim = "{"+ "\n" + appendString(hasil)+"\n"+"}";
        return jsonKirim;
    }

    public String selectTable(String table, String id, String table1){
        String sql = "SELECT * FROM " +table + "WHERE"+ table1 + " = "+ id;
        ArrayList<String> hasil = new ArrayList<String>();

        try {
            Connection connect = this.connect();
            Statement stmt  = connect.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);


            // loop through the result set
            while (rs.next()) {
                hasil.add(JsonStructure.user(rs.getInt("id"), rs.getString("first_name"), rs.getString("last_name"), rs.getString("email") , rs.getString("phone_number"), rs.getString("type") ));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        String jsonKirim = "{"+ "\n" + appendString(hasil)+"\n"+"}";
        return jsonKirim;
    }
}
