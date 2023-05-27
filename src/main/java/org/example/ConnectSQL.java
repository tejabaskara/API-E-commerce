package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectSQL {

    public static void connect(){
        Connection connection = null;
        String rootPath = System.getProperty("user.dir");
        try{
            String url = "jdbc:sqlite:" + rootPath + "/DB_Api.db";
            connection = DriverManager.getConnection(url);
            System.out.println("connection ok");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }finally {
            try{
                if (connection != null){
                    connection.close();
                }
            }catch (SQLException exception){
                System.out.println(exception.getMessage());
            }
        }
    }
}
