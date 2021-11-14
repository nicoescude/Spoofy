package interfaces;

import java.util.ArrayList;

import model.musical.*;
import model.users.User;

public interface IEntityHandler {

    public void loadSong(Song song);
    public void loadUser(User user);

    public void updateUser(User updated);

    public User createPremium(String username, String nombreApellido);
    public User createStandard(String username, String nombreApellido);
    public int createPlaylist(User master, String nombre);
    public int createSong(String nombre, String artista);

    public void deleteUser(String username);
    public void deleteSong(int songid);
    
    public ArrayList<User> getUserList();
    public ArrayList<Playlist> getPlaylistList();
    public ArrayList<Song> getSongList();

    public boolean containsUser(String username);
    public boolean containsSong(int songid);

    public User findUserByName(String username);
    public Song findSongById(int songid);
    
}
