package org.example;

import org.json.JSONObject;
import org.mortbay.util.ajax.JSON;

public class users {
   private int id;
   private String firstName;
   private String lastName;
   private String email;
   private String phoneNumber;
   private String type;

   public int id(){
       return  id;
   }
   public String firstName(){
       return firstName;
   }
   public String lastName(){
       return lastName;
   }
   public String email(){
       return email;
   }
   public String phoneNumber(){
       return phoneNumber;
   }
   public String type(){
       return type;
   }

   public void parseJson(String json){
       JSONObject obj = new JSONObject(json);
       id = obj.getInt("id");
       firstName = obj.getString("first_name");
       lastName = obj.getString("last_name");
       email = obj.getString("email");
       phoneNumber = obj.getString("phone_number");
       type = obj.getString("type");
   }
}
