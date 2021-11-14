package handlers;

import java.util.ArrayList;

import interfaces.IEntityHandler;
import model.musical.*;
import model.users.Premium;
import model.users.Standard;
import model.users.User;

public class EntityHandler implements IEntityHandler{

    private static ArrayList<User> users;
    private static ArrayList<Song> songs;


    static{
        users = new ArrayList<>();
        songs = new ArrayList<>();
    }

    public EntityHandler(){};

    public void loadUser(User user){
        if (!containsUser(user.getUsername())){
            users.add(user);
        }
        else
            System.out.println("Error, nombre de usuario no disponible");
    }

    public void loadSong(Song song){
        if (song.getId() != 0){
            if (!containsSong(song.getId()))
                songs.add(song);
            else
            System.out.println("Error, ya existe una cancion con ese ID.");
        }
        else
            System.out.println("Error, canción mal formada, método de creación incorrecto.");
    }

    public void updateUser(User updated){
        users.remove(this.findUserByName(updated.getUsername()));
        users.add(updated);
    }

    public boolean containsUser(String username){
        return findUserByName(username) != null;
    }

    @Override
    public boolean containsSong(int id) {
        return findSongById(id) != null;
    }

    public User findUserByName(String username){

        for (User u : users){
            if (u.getUsername().toLowerCase().equals(username.toLowerCase()))
                return u;
        }
        return null;
    }

    public Song findSongById(int songid){

        for (Song s : songs){
            if (s.getId() == songid )
                return s;
        }
        return null;
    }

    public  ArrayList<User> getUserList() {
        return users;
    }

    public  ArrayList<Playlist> getPlaylistList() {
        ArrayList<Playlist> pl = new ArrayList<>();
        for (User u : users){
            pl.addAll(u.getList());
        }
        return pl;
    }

    public  ArrayList<Song> getSongList() {
        return songs;
    }

    public int createPlaylist(User updated, String nombre) {
        int maxId = -1;

        for (Playlist pl : this.getPlaylistList()){
            if (pl.getId() >= maxId)
                maxId = pl.getId();
        }
        if (maxId == -1) maxId = 0;

        Playlist pl = new Playlist(maxId+1, nombre, updated.getUsername());
        updated.addPlaylist(pl);
        return pl.getId();
    }

    @Override
    public int createSong(String nombre, String artista) {
        int maxId = -1;
        for (Song s : this.getSongList()){
            if (s.getId() >= maxId)
                maxId = s.getId();
        }
        if (maxId == -1) maxId = 0;
        Song s = new Song(maxId+1, nombre, artista);
        this.loadSong(s);
        return s.getId();
    }

    @Override
    public User createPremium(String username, String nombreApellido) {
        User u = new Premium(username,nombreApellido);
        this.loadUser(u);
        return u;
    }

    @Override
    public User createStandard(String username, String nombreApellido) {
        User u = new Standard(username,nombreApellido);
        this.loadUser(u);
        return u;
    }

    @Override
    public void deleteUser(String username) {
        User u = this.findUserByName(username);
        users.remove(u);        
    }

    @Override
    public void deleteSong(int id) {
        Song s = findSongById(id);
        songs.remove(s);
        ArrayList<Song> toRemove;
        for (User u : users){
            for (Playlist p : u.getList()){
                toRemove = new ArrayList<>();
                for (Song c : p.getLista()){
                    if (c.getId() == id)
                        toRemove.add(c);
                        
                }
                p.getLista().removeAll(toRemove);
            }
        }
    }

    
}
