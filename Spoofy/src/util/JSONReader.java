package util;

import java.io.FileReader;
import java.io.IOException;

import org.json.*;

public class JSONReader {
    
    public static JSONObject read(String file){
        try {
            String s = new String("file");
            JSONTokener tokener = new JSONTokener((new FileReader(file)));
            JSONObject obj = new JSONObject(tokener);
            return obj;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    } 

    public static JSONArray readArray(String file){
        try {
            String s = new String("file");
            JSONTokener tokener = new JSONTokener((new FileReader(s)));
            JSONArray array = new JSONArray(tokener);
            return array;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    } 

}
