package org.example;

import org.json.JSONObject;



public class reviews {
    private int order;
    private int star;
    private String description;


    public int parseJson(String json){
        try {
            JSONObject obj = new JSONObject(json);
            order = obj.getInt("order");
            description = obj.getString("description");
            star = obj.getInt("star");
        }catch (Exception exception){
            return 1;
        }
        return  0;
    }

    public void insert(){
        ConnectSQL inputDB = new ConnectSQL();
        inputDB.inputReviews(order, star,description);
    }

    public void update(String idGanti){
        ConnectSQL inputDB = new ConnectSQL();
        inputDB.updateReviews(order, star, description, idGanti);
    }
}
