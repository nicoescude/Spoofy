package interfaces;

public interface IPlayable {
    
    public void addSongToPlaylist(int playlistid, int songid, IEntityHandler handler);
    public int createPlaylist(String nombre, IEntityHandler handler);

    public void playSong(int playlist, int songid);
    public void shuffleSong(int playlist);

}
