package org.example;

import org.json.JSONObject;

public class addresses {
    private int users;
    private String type;
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

    public int parseJson(String json){
        try {
            JSONObject obj = new JSONObject(json);
            users = obj.getInt("id");
            type = obj.getString("type");
            city = obj.getString("city");
            province = obj.getString("province");
            postcode = obj.getString("postcode");
        }catch (Exception e){
            return 1;
        }
        return 0;
    }

}
