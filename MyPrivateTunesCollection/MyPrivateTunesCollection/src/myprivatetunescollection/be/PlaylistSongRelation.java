package myprivatetunescollection.be;

/**
 *
 * @author DKE
 */
public class PlaylistSongRelation{
    private int playlistId;
    private int songId;
    
    public PlaylistSongRelation(int playlistId, int songId){
        this.playlistId = playlistId;
        this.songId = songId;
    }
    
    @Override
    public String toString(){
        return "PlaylistSongRelation(" + "playlistId=" + playlistId + ", songId=" + songId + '}';
    }
    
    public int getPlaylistId(){
        return playlistId;
    }
    
    public void setPlaylistId(int playlistId){
        this.playlistId = playlistId;
    }
    
    public int getSongId(){
        return songId;
    }
    
    public void setSongId(int songId){
        this.songId = songId;
    }
}