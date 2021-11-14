package model.users;
import java.util.ArrayList;
import java.util.Date;

import interfaces.IEntityHandler;
import interfaces.IPlayable;
import model.musical.Song;
import model.musical.Playlist;
import util.RandomN;

public abstract class User implements IPlayable{

    private String username;
    private String fullName;
    private String dateOfCreation;
    protected PLAN_TYPE type;
    private ArrayList<Playlist> list;
    
    public enum PLAN_TYPE{
        STANDARD("ST"), PREMIUM("PM");
        String plan;
        private PLAN_TYPE (String s) { this.plan = s; }
        public String value(){ return this.plan; }
    }

    public User(String username, String fullName){
        this.username = username;
        this.fullName = fullName;
        this.dateOfCreation = (new Date()).toString();
        this.list = new ArrayList<>();
    }

    public User(String username, String fullName, String date ){
        this.username = username;
        this.fullName = fullName;
        this.dateOfCreation = date;
        this.list = new ArrayList<>();
    }

    public void addPlaylist(Playlist p){
        this.list.add(p);
    }
    
    public PLAN_TYPE getType() {
        return type;
    }

    public String getDateOfCreation() {
        return dateOfCreation;
    }

    public String getFullName() {
        return fullName;
    }
    
    public String getUsername() {
        return username;
    }

    public ArrayList<Playlist> getList() {
        return list;
    }
    
    public void setList(ArrayList<Playlist> list) {
        this.list = list;
    }

    

    public int getSize(){
        return this.getList().size();
    }

    @Override
    public void shuffleSong(int playlist) {
        Playlist p = this.findPlaylist(playlist);
        int i = RandomN.rng(p.getLista().size());
        Song s = p.getLista().get(i);
        System.out.println("Random playing: -> "+s.getName()+" - by: "+s.getArtist());
    }
    
    protected Playlist findPlaylist(int playlist){
        for (Playlist p : this.getList()){
            if (p.getId() == playlist)
                return p;
        }
        return null;
    }

    protected Song findSong(Playlist pl, int songid){
        for (Song s : pl.getLista()){
            if (s.getId() == songid)
                return s;
        }
        return null;
    }

    @Override
    public void addSongToPlaylist(int playlist,int songid, IEntityHandler handler){
        Playlist pl = this.findPlaylist(playlist);
        Song s = handler.findSongById(songid);
        pl.addCancion(s);
        handler.updateUser(this);
    }

    public int createPlaylist(String name, IEntityHandler handler){
        return handler.createPlaylist(this, name);
    }
}
