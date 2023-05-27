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
                hasil.add("\t\"id\" : \"" + rs.getInt("id") +  "\"\n" +
                        "\t\"first_name\" : \"" + rs.getString("first_name") + "\"\n" +
                        "\t\"last_name\" : \"" +rs.getString("last_name") + "\"\n"+
                        "\t\"email\" : \"" + rs.getString("email") + "\"\n" +
                        "\t\"phone_number\" : \"" +rs.getString("phone_number") + "\"\n" +
                        "\t\"type\" : \"" +rs.getString("type") +"\"");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        String jsonKirim = "{"+ "\n" + appendString(hasil)+"\n"+"}";
        return jsonKirim;
    }
}
