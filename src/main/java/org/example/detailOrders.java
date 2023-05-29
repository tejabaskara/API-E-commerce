package org.example;

import org.json.JSONObject;


public class detailOrders {
    private int order;
    private int product;
    private int quantity;
    private int price;



    public int parseJson(String json){
        try {
            JSONObject obj = new JSONObject(json);
            order = obj.getInt("order");
            product = obj.getInt("product");
            quantity = obj.getInt("quantity");
            price = obj.getInt("price");
        }catch (Exception exception){
            return 1;
        }
        return  0;
    }

    public void insert(){
        ConnectSQL inputDB = new ConnectSQL();
        inputDB.inputOrdersDetails(order, product, quantity, price);
    }

    public void update(String idGanti){
        ConnectSQL inputDB = new ConnectSQL();
        inputDB.updateOrderDetails(order, product, quantity, price, idGanti);
    }
}
