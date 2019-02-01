package myprivatetunescollection.bll;

import java.sql.SQLException;
import java.util.List;
import myprivatetunescollection.be.Playlist;
import myprivatetunescollection.be.PlaylistSongRelation;
import myprivatetunescollection.be.Song;
import myprivatetunescollection.dal.PlaylistDAO;
import myprivatetunescollection.dal.PlaylistSongRelationDAO;
import myprivatetunescollection.dal.SongDAO;

/**
 *
 * @author DKE
 */
public class MyTunesLogic {
    
    public Song createSong(Song s) throws SQLException{
        SongDAO sdao = new SongDAO();
        return sdao.createSong(0, s);
    }
    
    public Song getSong(int id){
        SongDAO sdao = new SongDAO();
        return sdao.getSong(id);
    }
    
    public void updateSong(Song s) throws SQLException{
        SongDAO sdao = new SongDAO();
        sdao.updateSong(s);
    }
    
    public void deleteSong(Song s) throws SQLException{
        SongDAO sdao = new SongDAO();
        sdao.deleteSong(s);
    }
    
    public List<Song> getAllSongs(){
        SongDAO sdao = new SongDAO();
        return sdao.getAllSongs();
    }
    
    public Playlist createPlaylist(String name) throws SQLException{
        PlaylistDAO pdao = new PlaylistDAO();
        return pdao.createPlaylist(0, name);
    }
    
    public Playlist getPlaylist(int id){
        PlaylistDAO pdao = new PlaylistDAO();
        return pdao.getPlaylist(id);
    }
    
    public void updatePlaylist(Playlist p) throws SQLException{
        PlaylistDAO pdao = new PlaylistDAO();
        pdao.updatePlaylist(p);
    }
    
    public void deletePlaylist(Playlist p) throws SQLException{
        PlaylistDAO pdao = new PlaylistDAO();
        pdao.deletePlaylist(p);
    }
    
    public List<Playlist> getAllPlaylists(){
        PlaylistDAO pdao = new PlaylistDAO();
        return pdao.getAllPlaylists();
    }
    
    public PlaylistSongRelation createPlaylistSongRelation(int playlistId) throws SQLException{
        PlaylistSongRelationDAO psrdao = new PlaylistSongRelationDAO();
        return psrdao.createPlaylistSongRelation(0, 0);
    }
    
    public PlaylistSongRelation getPlaylistSongRelation(int playlistId){
        PlaylistSongRelationDAO psrdao = new PlaylistSongRelationDAO();
        return psrdao.getPlaylistSongRelation(playlistId);
    }
    
    public void updatePlaylistSongRelation(PlaylistSongRelation psr) throws SQLException{
        PlaylistSongRelationDAO psrdao = new PlaylistSongRelationDAO();
        psrdao.updatePlaylistSongRelation(psr);
    }
    
    public void deletePlaylistSongRelation(PlaylistSongRelation psr) throws SQLException{
        PlaylistSongRelationDAO psrdao = new PlaylistSongRelationDAO();
        psrdao.deletePlaylistSongRelation(psr);
    }
    
    public List<PlaylistSongRelation> getAllPlaylistSongRelations(){
        PlaylistSongRelationDAO psrdao = new PlaylistSongRelationDAO();
        return psrdao.getAllPlaylistSongRelations();
    }
}