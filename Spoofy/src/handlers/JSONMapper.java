package handlers;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import model.musical.*;
import model.users.Premium;
import model.users.Standard;
import model.users.User;

public class JSONMapper {

    public static JSONObject mapToJSON(Playlist p) {
        JSONObject json = new JSONObject();

        json.put("id", p.getId());
        json.put("nombre", p.getNombre());
        json.put("master", p.getMaster());
        json.put("size", p.getSize());
        JSONArray songlist = new JSONArray();

        for (Song c : p.getLista()){
            songlist.put(c.getId());
        }

        json.put("songlist", songlist);
        return json;
    }
    

    public static JSONObject mapToJSON(Song song) {
        JSONObject json = new JSONObject();

        json.put("id", song.getId());
        json.put("nombre", song.getName());
        json.put("artista", song.getArtist());

        return json;
    }

    public static JSONObject mapToJSON(User user){
        JSONObject json = new JSONObject();

        json.put("username", user.getUsername().toLowerCase());
        json.put("nombreapellido", user.getFullName());
        json.put("date", user.getDateOfCreation());
        json.put("size", user.getSize());
        json.put("type", user.getType().value());
        JSONArray playlists = new JSONArray();

        for (Playlist pl : user.getList()){
            playlists.put(pl.getId());
        }
        
        json.put("playlists", playlists);
        return json;
    }

    public static JSONArray mapUsersToJSON(ArrayList<User> users){
        JSONArray array = new JSONArray();
        for (User u: users){
            array.put(mapToJSON(u));
        }
        return array;
    }

    public static JSONArray mapPaylistsToJSON(ArrayList<Playlist> playlists){
        JSONArray array = new JSONArray();
        for (Playlist p: playlists){
            array.put(mapToJSON(p));
        }
        return array;
    }

    public static JSONArray mapSongsToJSON(ArrayList<Song> songs){
        JSONArray array = new JSONArray();
        for (Song s: songs){
            array.put(mapToJSON(s));
        }
        return array;
    }

    public static User mapUsuario(JSONObject json){
        User u;
        /**
         * public Usuario(String username, String nombreApellido){
         */
        String username = json.getString("username");
        String nombreapellido = json.getString("nombreapellido");
        String date = json.getString("date");
        
        if (json.getString("type").equals(User.PLAN_TYPE.PREMIUM.value())){
            u = new Premium(username,nombreapellido,date);
        }
        else{
            u = new Standard(username,nombreapellido,date);
        }
        return u;
    }

    public static Song mapCancion(JSONObject json){
        Song s;
        /** public Cancion(int id, String nombre, String artista){ */
        int id = json.getInt("id");
        String nombre = json.getString("nombre");
        String artista = json.getString("artista");
        s = new Song(id, nombre, artista);
        return s;
    }

}
