package org.example;

import org.json.JSONObject;

public class addresses {
    private int users;
    private String type;
    private String line1;
    private String line2;
    private String city;
    private String province;
    private String postcode;


    public int parseJson(String json){
        try {
            JSONObject obj = new JSONObject(json);
            users = obj.getInt("users");
            type = obj.getString("type");
            line1 = obj.getString("line1");
            line2 = obj.getString("line2");
            province = obj.getString("province");
            city = obj.getString("city");
            postcode = obj.getString("postcode");
        }catch (Exception e){
            return 1;
        }
        return 0;
    }

    public void insert(){
        ConnectSQL inputDB = new ConnectSQL();
        inputDB.inputAddresses(users, type, line1, line2, province, city, postcode);
    }

    public void update(String idGanti){
        ConnectSQL inputDB = new ConnectSQL();
        inputDB.updateAddresses(users, type, line1,line2, city, province,postcode,idGanti);
    }

}
