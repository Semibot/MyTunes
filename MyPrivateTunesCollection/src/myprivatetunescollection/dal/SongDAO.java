package myprivatetunescollection.dal;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import myprivatetunescollection.be.Song;

/**
 * DAO = Data Access Object
 * @author DKE
 */
public class SongDAO{
    SQLServerDataSource ds;
    
    public SongDAO(){
        
    }
    
    //Crud Create
    public Song createSong(int id, Song s) throws SQLException{
        
        try(Connection conn = ds.getConnection()){
            String sql = "INSERT INTO Song(title, artist) "
                    + "VALUES(?,?)";
            PreparedStatement stmt = conn.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, s.getTitle());
            stmt.setString(2, s.getArtist());
            
            int createdRows = stmt.executeUpdate();
            
            if(createdRows == 0){
                throw new SQLException("Creating song failed, no rows created.");
            }
            
            try(ResultSet generatedKeys = stmt.getGeneratedKeys()){
                if(generatedKeys.next()){
                    s.setId((int)generatedKeys.getLong(1));
                }else {
                    throw new SQLException("Creating song failed, no ID obtained");
                }
            }
        }
        return s;
    }
    
    //cRud Read
    public Song getSong(int id){
        
        try(Connection conn = ds.getConnection()){
            PreparedStatement pstmt =
                    conn.prepareStatement("SELECT * FROM Song WHERE id=?");
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                int ids = rs.getInt("id");
                String title = rs.getString("title");
                String artist = rs.getString("artist");
                Song s = new Song(ids, title, artist);
                return s;
            }
        }catch(SQLServerException ex){
            Logger.getLogger(SongDAO.class.getName()).log(Level.SEVERE, null, ex);
        }catch(SQLException ex){
            Logger.getLogger(SongDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    //crUd Update
    public void updateSong(Song s) throws SQLException{
        
        try(Connection conn = ds.getConnection()){
            String sql = "UPDATE Song SET title=? WHERE id=?";
            PreparedStatement stmt = conn.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, s.getTitle());
            stmt.setInt(2, s.getId());
            
            int updatedRows = stmt.executeUpdate();
            
            if(updatedRows == 0){
                throw new SQLException("Updating song failed, no rows updated.");
            }
            
            try(ResultSet generatedKeys = stmt.getGeneratedKeys()){
                if(generatedKeys.next()){
                    s.setId((int) generatedKeys.getLong(1));
                }else {
                    throw new SQLException("Updating a song failed, no song updated.");
                }
            }
        }
    }
    
    //cruD Delete
    public void deleteSong(Song s) throws SQLException{
        
        try(Connection conn = ds.getConnection()){
            String sql = "DELETE FROM Song WHERE id=?";
            PreparedStatement stmt = conn.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, s.getId());
            
            int deletedRows = stmt.executeUpdate();
            
            if(deletedRows == 0){
                throw new SQLException("Deleting a song failed, no rows deleted.");
            }
            
            try(ResultSet generatedKeys = stmt.getGeneratedKeys()){
                if(generatedKeys.next()){
                    s.setId((int) generatedKeys.getLong(1));
                }else {
                    throw new SQLException("Deleting a song failed, no ID obtained.");
                }
            }
        }
    }
    
    public List<Song> getAllSongs(){
        
        List<Song> songs = new ArrayList();
        try(Connection conn = ds.getConnection()){
            String sqlStatement = "SELECT * FROM Song";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sqlStatement);
            while(rs.next()){
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String artist = rs.getString("artist");
                Song s = new Song(id, title, artist);
                songs.add(s);
            }
        }catch(SQLServerException ex){
            Logger.getLogger(SongDAO.class.getName()).log(Level.SEVERE, null, ex);
        }catch(SQLException ex){
            Logger.getLogger(SongDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return songs;
    }
}