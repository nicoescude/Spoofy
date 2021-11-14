package model.musical;

public class Song {
    private int id;
    private String name;
    private String artist;

    public Song(int id, String name, String artist){
        this.id = id;
        this.name = name;
        this.artist = artist;
    }

    public String getArtist() {
        return artist;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
