package org.example;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStream;

public class products {
    private int id;
    private int seller;
    private String title;
    private String description;
    private String price;
    private int stock;

    public int getId() {
        return id;
    }

    public int getSeller() {
        return seller;
    }

    public int getStock() {
        return stock;
    }

    public String getDescription() {
        return description;
    }

    public String getPrice() {
        return price;
    }

    public String getTitle() {
        return title;
    }

    public int parseJson(String json){
        try {
            JSONObject obj = new JSONObject(json);
            id = obj.getInt("id");
            seller = obj.getInt("seller");
            stock = obj.getInt("stock");
            description = obj.getString("description");
            price = obj.getString("price");
            title = obj.getString("title");
        }catch (Exception exception){
            return 1;
        }
        return 0;
    }
}

