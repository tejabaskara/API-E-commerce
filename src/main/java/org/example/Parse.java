package org.example;

public class Parse {
    public static String[] path(String string){
        String[] hasil = string.split("/");
        return hasil;
    }
    public static int checkFilter(String string){
        char[] check = string.toCharArray();
        for (int i= 0; i < check.length; i++){
            if (check[i] == '?'){
             return 1;
            }
        }
        return 0;
    }
    public static String[] filter(String string){
        String[] hasil = string.split("&");
        return hasil;
    }


}
