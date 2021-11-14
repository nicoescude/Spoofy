package handlers;

import java.util.ArrayList;

import org.json.*;

import interfaces.IEntityHandler;
import interfaces.IPersistenceHandler;
import model.musical.Song;
import model.users.User;
import model.musical.Playlist;
import util.JSONReader;
import util.JSONWriter;

public class JSONHandler implements IPersistenceHandler{


    public JSONHandler(){};

    public void serialize(IEntityHandler handler, String file){
        JSONArray arrayusers = JSONMapper.mapUsersToJSON(handler.getUserList());
        JSONArray arrayplaylists = JSONMapper.mapPaylistsToJSON(handler.getPlaylistList());
        JSONArray arraysongs = JSONMapper.mapSongsToJSON(handler.getSongList());
        JSONObject json = new JSONObject();
        json.put("usuarios", arrayusers);
        json.put("playlists", arrayplaylists);
        json.put("canciones", arraysongs);

        JSONWriter.write(json, file);
    }


    @Override
    public void deserialize(IEntityHandler handler, String file) {
        JSONObject json = JSONReader.read(file);
        if (json == null) return;

        JSONArray userArray = json.getJSONArray("usuarios");
        JSONArray songArray = json.getJSONArray("canciones");
        JSONArray playlistArray = json.getJSONArray("playlists");

        for (Object obj : songArray){
            JSONObject jsonObj = (JSONObject) obj;
            Song song = JSONMapper.mapCancion(jsonObj);
            handler.loadSong(song);
        }

        for(Object obj : userArray){
            JSONObject jsonObj = (JSONObject) obj;
            User user = JSONMapper.mapUsuario(jsonObj);
            handler.loadUser(user);
        }

        for (Object obj : playlistArray){
            JSONObject jsonObj = (JSONObject) obj;

            User master = handler.findUserByName(jsonObj.getString("master"));
            String masterName = null;
            if (master != null){
                masterName = master.getUsername();
                String nombre = jsonObj.getString("nombre");
                JSONArray songSubArray = jsonObj.getJSONArray("songlist");
                int id = jsonObj.getInt("id");


                Playlist p = new Playlist(id, nombre, masterName);
                
                for (int i=0; i<jsonObj.getInt("size");i++){
                    int songid = ((Integer)songSubArray.get(i));
                    Song s = handler.findSongById(songid);
                    p.addCancion(s);
                }
                master.addPlaylist(p);
            }
        }
    }    
}
