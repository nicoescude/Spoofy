package model.users;

import model.musical.Song;
import model.musical.Playlist;

public class Premium extends User{

    public Premium(String username, String nombreApellido) {
        super(username, nombreApellido);
        this.type = PLAN_TYPE.PREMIUM;
    }

    public Premium(String username, String nombreApellido, String date ){
        super(username, nombreApellido,date);
        this.type = PLAN_TYPE.PREMIUM;
    }

    @Override
    public void playSong(int playlist, int song) {
        Playlist pl = this.findPlaylist(playlist);
        Song s = this.findSong(pl, song);
        
        System.out.println("Playing: -> "+s.getName()+" - by: "+s.getArtist());
    }
    
    
}
