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
//            System.out.println("connection ok");
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
        String sql = "INSERT INTO addresses( users, type, line1, line2, city, province, postcode) VALUES(?,?,?,?,?,?,?)";
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
        String sql = "INSERT INTO reviews( order_id, star, description) VALUES(?,?,?)";
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
        String sql = "INSERT INTO orders(id, buyer, note, total, discount, is_paid) VALUES(?,?,?,?,?,?)";
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
        String sql = "INSERT INTO order_details( order_id ,product ,quantity ,price) VALUES(?,?,?,?)";
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

    public String selectAll(String tabel){
        String sql = "SELECT * FROM " + tabel;
        ArrayList<String> hasil = new ArrayList<String>();

        try {
            Connection connect = this.connect();
            Statement stmt  = connect.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);

            if (tabel.equals("users")){
                while (rs.next()) {
                    hasil.add(JsonStructure.user(rs.getInt("id"), rs.getString("first_name"), rs.getString("last_name"), rs.getString("email") , rs.getString("phone_number"), rs.getString("type") ));
                }
            } else if (tabel.equals("addresses")) {
                while (rs.next()) {
                    hasil.add(JsonStructure.addresses(rs.getInt("users"), rs.getString("type"), rs.getString("line1"), rs.getString("line2") , rs.getString("city"), rs.getString("province"), rs.getString("postcode") ));
                }
            } else if (tabel.equals("products")) {
                while (rs.next()) {
                    hasil.add(JsonStructure.product(rs.getInt("id"), rs.getInt("seller"), rs.getString("title"), rs.getString("description") , rs.getString("price"), rs.getInt("stock")));
                }
            } else if (tabel.equals("orders")) {
                while (rs.next()) {
                    hasil.add(JsonStructure.orders(rs.getInt("id"), rs.getInt("buyer"), rs.getInt("note"), rs.getInt("total") , rs.getInt("discount"), rs.getString("is_paid")));
                }
            } else if (tabel.equals("orders_details")) {
                while (rs.next()) {
                    hasil.add(JsonStructure.orderDetail(rs.getInt("order_id"), rs.getInt("product"), rs.getInt("quantity"), rs.getInt("price")));
                }
            } else if (tabel.equals("reviews")) {
                while (rs.next()) {
                    hasil.add(JsonStructure.reviews(rs.getInt("order_id"), rs.getInt("star"), rs.getString("description")));
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        String jsonKirim = "{"+ "\n" + appendString(hasil)+"\n"+"}";
        return jsonKirim;
    }


    /*
        selectID merupakan methode yang berguna
        untuk mengambil data sesuai dengan id
    */
    public String selectId(String tabel, String id){
        String sql = "SELECT * FROM "+ tabel + " WHERE id = "+ id;
        ArrayList<String> hasil = new ArrayList<String>();

        try {
            Connection connect = this.connect();
            Statement stmt  = connect.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);


            // loop through the result set
            if (tabel.equals("users")){
                while (rs.next()) {
                    hasil.add(JsonStructure.user(rs.getInt("id"), rs.getString("first_name"), rs.getString("last_name"), rs.getString("email") , rs.getString("phone_number"), rs.getString("type") ));
                }
            } else if (tabel.equals("products")) {
                while (rs.next()) {
                    hasil.add(JsonStructure.product(rs.getInt("id"), rs.getInt("seller"), rs.getString("title"), rs.getString("description") , rs.getString("price"), rs.getInt("stock")));
                }
            } else if (tabel.equals("orders")) {
                while (rs.next()) {
                    hasil.add(JsonStructure.orders(rs.getInt("id"), rs.getInt("buyer"), rs.getInt("note"), rs.getInt("total") , rs.getInt("discount"), rs.getString("is_paid")));
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        String jsonKirim = "{"+ "\n" + appendString(hasil)+"\n"+"}";
        return jsonKirim;
    }

    /*
    selectOrderID merupakan methode yang berguna
    untuk mengambil data sesuai dengan order id
     */
    public String selectOrdersId(String tabel, String id){
        String sql = "SELECT * FROM "+ tabel + " WHERE order_id = "+ id;
        ArrayList<String> hasil = new ArrayList<String>();

        try {
            Connection connect = this.connect();
            Statement stmt  = connect.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);


            // loop through the result set
            if (tabel.equals("order_details")) {
                while (rs.next()) {
                    hasil.add(JsonStructure.orderDetail(rs.getInt("order_id"), rs.getInt("product"), rs.getInt("quantity"), rs.getInt("price")));
                }
            } else if (tabel.equals("reviews")) {
                while (rs.next()) {
                    hasil.add(JsonStructure.reviews(rs.getInt("order_id"), rs.getInt("star"), rs.getString("description")));
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        String jsonKirim = "{"+ "\n" + appendString(hasil)+"\n"+"}";
        return jsonKirim;
    }

    /*
    selectOrderUsers merupakan methode yang berguna
    untuk mengambil data sesuai dengan users id
     */
    public String selectIdUsers(String tabel, String id){
        String sql = "SELECT * FROM "+ tabel + " WHERE users = "+ id;
        ArrayList<String> hasil = new ArrayList<String>();

        try {
            Connection connect = this.connect();
            Statement stmt  = connect.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);


            // loop through the result set
            while (rs.next()) {
                hasil.add(JsonStructure.addresses(rs.getInt("users"), rs.getString("type"), rs.getString("line1"), rs.getString("line2") , rs.getString("city"), rs.getString("province"), rs.getString("postcode") ));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        String jsonKirim = "{"+ "\n" + appendString(hasil)+"\n"+"}";
        return jsonKirim;
    }

    
    
    
    /*
    untuk query GET dengan tabel kedua dan juga ID
     */
    public String selectTableUser(String id, String tabel1){
        String sql = "SELECT * FROM users WHERE id = "+ id;
        String jsonKirim = "";
        System.out.println(id);
        try {
            Connection connect = this.connect();
            Statement stmt  = connect.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);
            users user = new users();
            user.setUser(rs.getInt("id"), rs.getString("first_name"), rs.getString("last_name"), rs.getString("email") , rs.getString("phone_number"), rs.getString("type"));

            if (tabel1.equals("addresses")){
                jsonKirim = selectIdUsers(tabel1, String.valueOf(user.id()));
            } else if (tabel1.equals("products")) {
                jsonKirim = selectId(tabel1 , String.valueOf(user.id()));
            } else if (tabel1.equals("orders")) {
                jsonKirim = selectId(tabel1, String.valueOf(user.id()));
            } else if (tabel1.equals("reviews")) {
                String sqlFind = "SELECT * FROM orders WHERE buyer = "+ id;
                Connection connectFind = this.connect();
                Statement stmtFind  = connectFind.createStatement();
                ResultSet rsFind    = stmtFind.executeQuery(sqlFind);
                orders order = new orders();
                order.setOrder(rsFind.getInt("id"), rsFind.getInt("buyer"), rsFind.getInt("note"), rsFind.getInt("total") , rsFind.getInt("discount"), rsFind.getString("is_paid"));

                jsonKirim = selectOrdersId(tabel1, String.valueOf(order.getId()));
            } else if (tabel1.equals("order_details")) {
                String sqlFind = "SELECT * FROM orders WHERE buyer = "+ id;
                Connection connectFind = this.connect();
                Statement stmtFind  = connectFind.createStatement();
                ResultSet rsFind    = stmtFind.executeQuery(sqlFind);
                orders order = new orders();
                order.setOrder(rsFind.getInt("id"), rsFind.getInt("buyer"), rsFind.getInt("note"), rsFind.getInt("total") , rsFind.getInt("discount"), rsFind.getString("is_paid"));

                jsonKirim = selectOrdersId(tabel1, String.valueOf(order.getId()));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return jsonKirim;
    }


    public String queryAllCond( String tabel, String id, String cond1, String cond2, String cond3){
        String sql = "";
        String[] kondisi1 = Parse.filterCondition(cond1);
        String[] kondisi2 = Parse.filterCondition(cond2);
        String[] kondisi3 = Parse.filterCondition(cond3);
        ArrayList<String> hasil = new ArrayList<String>();

        if (kondisi2[1].equals("largerEqual")){
            sql = "SELECT * FROM "+tabel+ " WHERE "+ kondisi1[0] +" = \""+ kondisi1[1] + "\" AND " + id + " >= " + kondisi3[1];
        } else if (kondisi2[1].equals("larger")) {
            sql = "SELECT * FROM "+tabel+ " WHERE "+ kondisi1[0] +" = \""+ kondisi1[1] + "\" AND " + id + " > " + kondisi3[1];
        } else if (kondisi2[1].equals("smaller")) {
            sql = "SELECT * FROM "+tabel+ " WHERE "+ kondisi1[0] +" = \""+ kondisi1[1] + "\" AND " + id + " < " + kondisi3[1];
        } else if (kondisi2[1].equals("smallerEqual")) {
            sql = "SELECT * FROM "+tabel+ " WHERE "+ kondisi1[0] +" = \""+ kondisi1[1] + "\" AND " + id + " <= " + kondisi3[1];
        } else if (kondisi2[1].equals("equal")) {
            sql = "SELECT * FROM "+tabel+ " WHERE "+ kondisi1[0] +" = \""+ kondisi1[1] + "\" AND " + id + " = " + kondisi3[1];
        } else {
            hasil.add("Filter wrong");
        }


        try {
            Connection connect = this.connect();
            Statement stmt  = connect.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);

            if (tabel.equals("users")){
                while (rs.next()) {
                    hasil.add(JsonStructure.user(rs.getInt("id"), rs.getString("first_name"), rs.getString("last_name"), rs.getString("email") , rs.getString("phone_number"), rs.getString("type") ));
                }
            } else if (tabel.equals("addresses")) {
                while (rs.next()) {
                    hasil.add(JsonStructure.addresses(rs.getInt("users"), rs.getString("type"), rs.getString("line1"), rs.getString("line2") , rs.getString("city"), rs.getString("province"), rs.getString("postcode") ));
                }
            } else if (tabel.equals("products")) {
                while (rs.next()) {
                    hasil.add(JsonStructure.product(rs.getInt("id"), rs.getInt("seller"), rs.getString("title"), rs.getString("description") , rs.getString("price"), rs.getInt("stock")));
                }
            } else if (tabel.equals("orders")) {
                while (rs.next()) {
                    hasil.add(JsonStructure.orders(rs.getInt("id"), rs.getInt("buyer"), rs.getInt("note"), rs.getInt("total") , rs.getInt("discount"), rs.getString("is_paid")));
                }
            } else if (tabel.equals("orders_details")) {
                while (rs.next()) {
                    hasil.add(JsonStructure.orderDetail(rs.getInt("order_id"), rs.getInt("product"), rs.getInt("quantity"), rs.getInt("price")));
                }
            } else if (tabel.equals("reviews")) {
                while (rs.next()) {
                    hasil.add(JsonStructure.reviews(rs.getInt("order_id"), rs.getInt("star"), rs.getString("description")));
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        String jsonKirim = "{"+ "\n" + appendString(hasil)+"\n"+"}";
        return jsonKirim;
    }

    public String queryOneCond( String tabel, String id, String cond1){
        String sql = "";
        String[] kondisi1 = Parse.filterCondition(cond1);
        ArrayList<String> hasil = new ArrayList<String>();

        sql = "SELECT * FROM "+tabel+ " WHERE "+ kondisi1[0] +" = \""+ kondisi1[1] + "\"";

        try {
            Connection connect = this.connect();
            Statement stmt  = connect.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);

            if (tabel.equals("users")){
                while (rs.next()) {
                    hasil.add(JsonStructure.user(rs.getInt("id"), rs.getString("first_name"), rs.getString("last_name"), rs.getString("email") , rs.getString("phone_number"), rs.getString("type") ));
                }
            } else if (tabel.equals("addresses")) {
                while (rs.next()) {
                    hasil.add(JsonStructure.addresses(rs.getInt("users"), rs.getString("type"), rs.getString("line1"), rs.getString("line2") , rs.getString("city"), rs.getString("province"), rs.getString("postcode") ));
                }
            } else if (tabel.equals("products")) {
                while (rs.next()) {
                    hasil.add(JsonStructure.product(rs.getInt("id"), rs.getInt("seller"), rs.getString("title"), rs.getString("description") , rs.getString("price"), rs.getInt("stock")));
                }
            } else if (tabel.equals("orders")) {
                while (rs.next()) {
                    hasil.add(JsonStructure.orders(rs.getInt("id"), rs.getInt("buyer"), rs.getInt("note"), rs.getInt("total") , rs.getInt("discount"), rs.getString("is_paid")));
                }
            } else if (tabel.equals("orders_details")) {
                while (rs.next()) {
                    hasil.add(JsonStructure.orderDetail(rs.getInt("order_id"), rs.getInt("product"), rs.getInt("quantity"), rs.getInt("price")));
                }
            } else if (tabel.equals("reviews")) {
                while (rs.next()) {
                    hasil.add(JsonStructure.reviews(rs.getInt("order_id"), rs.getInt("star"), rs.getString("description")));
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        String jsonKirim = "{"+ "\n" + appendString(hasil)+"\n"+"}";
        return jsonKirim;
    }

    public void deleteKolom(String tabel, String id, String idKolom){
        String sql = "DELETE FROM "+ tabel + " WHERE " +id +" = "+ idKolom;
        try {
            Connection connect = this.connect();
            Statement stmt  = connect.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }




    /*
    UPDATE QUERY UNTUK METHODE PUT
     */
    public void updateUser(int id, String firstName, String lastName, String email, String phoneNumber, String type, String idUbah) {
        String sql = "UPDATE users SET id = " + id + " ,first_name = \"" + firstName + "\" ,last_name = \"" + lastName + "\" ,email = \"" + email +
                "\" ,phone_number = \"" + phoneNumber + "\" ,type = \"" + type + "\" WHERE id = " + idUbah;
        try {
            Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateProducts(int id, int seller, String title, String description, String price, int stock, String idGanti) {
        String sql = "UPDATE products SET id = " + id + " ,seller = \"" + seller + "\" ,title = \"" + title + "\" ,description = \"" + description +
                "\" ,price = \"" + price + "\" ,stock = \"" + stock + "\" WHERE id = " + idGanti;
        try {
            Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateOrders(int id, int buyer, int note, int total, int discount, String isPaid, String idGanti) {
        String sql = "UPDATE orders SET id = " + id + " ,buyer = \"" + buyer + "\" ,note = \"" + note + "\" ,total = \"" + total +
                "\" ,discount = \"" + discount + "\" ,is_paid = \"" + isPaid + "\" WHERE id = " + idGanti;
        try {
            Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void updateAddresses(int users, String type, String line1, String line2, String city, String province, String postcode, String idGanti) {
        String sql = "UPDATE addresses SET users = " + users + " ,type = \"" + type + "\" ,line1 = \"" + line1 + "\" ,line2 = \"" + line2 +
                "\" ,city = \"" + city + "\" ,province = \"" + province + "\" ,postcode = \"" + postcode + "\" WHERE users = " + idGanti;
        try {
            Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void updateReviews(int orderId, int star, String description, String idGanti) {
        String sql = "UPDATE reviews SET order_id = " + orderId + " ,star = \"" + star + "\" ,description = \"" + description + "\" WHERE order_id = " + idGanti;
        try {
            Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void updateOrderDetails(int orderId, int product, int quantity, int price, String idGanti) {
        String sql = "UPDATE order_details SET order_id = " + orderId + " ,product = \"" + product + "\" ,quantity = \"" + quantity + "\" ,price = \"" + price + "\" WHERE order_id = " + idGanti;
        try {
            Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
