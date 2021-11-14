package util;

import java.io.FileWriter;
import java.io.IOException;
import org.json.*;
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;

public class JSONWriter {

    public static void write (JSONObject json, String file){

        try(FileWriter fw = new FileWriter(file)) {
            fw.write(json.toString(4));
            fw.flush();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void writeArray (JSONArray json, String file){

        try(FileWriter fw = new FileWriter("file")) {
            fw.write(json.toString(4));
            fw.flush();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

}
