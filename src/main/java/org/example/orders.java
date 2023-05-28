package org.example;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStream;

public class orders {
    private int id;
    private int buyer;
    private int note;
    private int total;
    private int discount;
    private String isPaid;

    public int getId() {
        return id;
    }

    public int getBuyer() {
        return buyer;
    }

    public int getDiscount() {
        return discount;
    }

    public int getNote() {
        return note;
    }

    public int getTotal() {
        return total;
    }

    public String getIsPaid() {
        return isPaid;
    }

    public int parseJson(String json){
        try {
            JSONObject obj = new JSONObject(json);
            id = obj.getInt("id");
            buyer = obj.getInt("buyer");
            note = obj.getInt("note");
            total = obj.getInt("total");
            discount = obj.getInt("discount");
            isPaid = obj.getString("is_paid");
            ConnectSQL inputDB = new ConnectSQL();
            inputDB.inputOrders(id, buyer, note, total, discount, isPaid);
        }catch (Exception exception){
            return 1;
        }
        return  0;
    }
}
