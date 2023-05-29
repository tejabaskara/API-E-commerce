package org.example;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class users {
   private int id;
   private String firstName;
   private String lastName;
   private String email;
   private String phoneNumber;
   private String type;


   public Integer id(){
       return  id;
   }



    public void setUser(int id, String firstName, String lastName, String email, String phoneNumber, String type) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.type = type;

    }


    public int parseJson(String json){
       try {
           JSONObject obj = new JSONObject(json);
           id = obj.getInt("id");
           firstName = obj.getString("first_name");
           lastName = obj.getString("last_name");
           email = obj.getString("email");
           phoneNumber = obj.getString("phone_number");
           type = obj.getString("type");
       }catch (Exception e){
           return 1;
       }
       return 0;
   }
   public void insert(){
       ConnectSQL inputDB = new ConnectSQL();
       inputDB.inputUsers(id, firstName, lastName, email, phoneNumber, type);
   }

   public void  update(String idGanti){
       ConnectSQL updateDB = new ConnectSQL();
       updateDB.updateUser(id, firstName, lastName,email, phoneNumber,type, idGanti);
   }

}
