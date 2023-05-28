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

    public int getUsers() {
        return users;
    }

    public String getCity() {
        return city;
    }

    public String getPostcode() {
        return postcode;
    }

    public String getProvince() {
        return province;
    }

    public String getType() {
        return type;
    }

    public String getLine1() {
        return line1;
    }

    public String getLine2() {
        return line2;
    }

    public int parseJson(String json){
        try {
            JSONObject obj = new JSONObject(json);
            users = obj.getInt("id");
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

}
