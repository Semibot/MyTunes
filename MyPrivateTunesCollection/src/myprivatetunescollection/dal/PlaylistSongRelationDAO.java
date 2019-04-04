package myprivatetunescollection.dal;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import myprivatetunescollection.be.PlaylistSongRelation;

/**
 * DAO = Data Access Object
 * @author DKE
 */
public class PlaylistSongRelationDAO{
    SQLServerDataSource ds;
    
    public PlaylistSongRelationDAO(){
        ds = new SQLServerDataSource();
        ds.setDatabaseName("MyPrivateTunesCollection");
        ds.setUser("CS2018B_3");
        ds.setPassword("CS2018B_3");
        ds.setPortNumber(1433);
        ds.setServerName("10.176.111.31");
    }
    
    //Crud Create
    public PlaylistSongRelation createPlaylistSongRelation(int playlistId, int songId) throws SQLException{
        
        PlaylistSongRelation psr = null;
        try(Connection conn = ds.getConnection()){
            String sql = "INSERT INTO PlaylistSongRelation(playlistId)"
                    + "VALUES(?)";
            PreparedStatement stmt = conn.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, playlistId);
            psr = new PlaylistSongRelation(playlistId, songId);
            
            int createdRows = stmt.executeUpdate();
            
            if(createdRows == 0){
                throw new SQLException("Creating PSrelation failed, no rows created.");
            }
            
            try(ResultSet generatedKeys = stmt.getGeneratedKeys()){
                if(generatedKeys.next()){
                    psr.setSongId((int) generatedKeys.getLong(1));
                }else {
                    throw new SQLException("Creating PSrelation failed, no ID obtained.");
                }
            }
        }
        return psr;
    }
    
    //cRud Read
    public PlaylistSongRelation getPlaylistSongRelation(int playlistId){
        
        try(Connection conn = ds.getConnection()){
            PreparedStatement pstmt =
                    conn.prepareStatement("SELECT * FROM PlaylistSongRelation WHERE playlistId=?");
            pstmt.setInt(1, playlistId);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                int playlistIdpsr = rs.getInt("playlistId");
                int songId = rs.getInt("songId");
                PlaylistSongRelation psr = new PlaylistSongRelation(playlistIdpsr, songId);
                return psr;
            }
        }catch(SQLServerException ex){
            Logger.getLogger(PlaylistSongRelationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }catch(SQLException ex){
            Logger.getLogger(PlaylistSongRelationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    //crUd Update
    public void updatePlaylistSongRelation(PlaylistSongRelation psr) throws SQLException{
        
        try(Connection conn = ds.getConnection()){
            String sql = "UPDATE PlaylistSongRelation WHERE playlistId=?";
            PreparedStatement stmt = conn.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, psr.getPlaylistId());
            
            int updatedRows = stmt.executeUpdate();
            
            if(updatedRows == 0){
                throw new SQLException("Updating PSrelation failed, no rows updated.");
            }
            
            try(ResultSet generatedKeys = stmt.getGeneratedKeys()){
                if(generatedKeys.next()){
                    psr.setSongId((int) generatedKeys.getLong(1));
                }else {
                    throw new SQLException("Updating PSrelation failed, no ID obtained.");
                }
            }
        }
    }
    
    //cruD Delete
    public void deletePlaylistSongRelation(PlaylistSongRelation psr) throws SQLException{
        
        try(Connection conn = ds.getConnection()){
            String sql = "DELETE FROM PlaylistSongRelation WHERE playlistId=?";
            PreparedStatement stmt = conn.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, psr.getPlaylistId());
            
            int deletedRows = stmt.executeUpdate();
            
            if(deletedRows == 0){
                throw new SQLException("Deleting PSrelation failed, no rows deleted.");
            }
            
            try(ResultSet generatedKeys = stmt.getGeneratedKeys()){
                if(generatedKeys.next()){
                    psr.setSongId((int) generatedKeys.getLong(1));
                }else {
                    throw new SQLException("Deleting PSrelation failed, no ID obtained.");
                }
            }
        }
    }
    
    public List<PlaylistSongRelation> getAllPlaylistSongRelations(){
        
        List<PlaylistSongRelation> playlistSongRelations = new ArrayList();
        try(Connection conn = ds.getConnection()){
            String sqlStatement = "SELECT * FROM PlaylistSongRelation";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sqlStatement);
            while(rs.next()){
                int playlistId = rs.getInt("playlistId");
                int songId = rs.getInt("songId");
                PlaylistSongRelation psr = new PlaylistSongRelation(playlistId, songId);
                playlistSongRelations.add(psr);
            }
        }catch(SQLServerException ex){
            Logger.getLogger(PlaylistSongRelationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }catch(SQLException ex){
            Logger.getLogger(PlaylistSongRelationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return playlistSongRelations;
    }
}