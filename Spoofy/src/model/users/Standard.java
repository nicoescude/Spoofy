package model.users;
import java.util.Random;

import interfaces.IEntityHandler;
import interfaces.IPlayable;
import model.musical.Song;
import model.users.User.PLAN_TYPE;
import model.musical.Playlist;
import util.RandomN;

public class Standard extends User{

    
    public Standard(String username, String nombreApellido) {
        super(username, nombreApellido);
        this.type = PLAN_TYPE.STANDARD;
    }

    public Standard(String username, String nombreApellido, String date ){
        super(username, nombreApellido,date);
        this.type = PLAN_TYPE.STANDARD;
    }

    @Override
    public void playSong(int playlist, int song) {
        this.shuffleSong(playlist);
    }

    @Override
    public void addSongToPlaylist(int playlist,int song, IEntityHandler handler){
        Playlist pl = this.findPlaylist(playlist);
        if (pl.getSize() > 30)
            System.out.println("Error, no puede agregar más canciones a esta lista: "+pl.getNombre());
        else
        {
            System.out.println("Exito, cancion agregada a la lista: "+pl.getNombre());
            super.addSongToPlaylist(playlist, song, handler);
        }  
    }
}
