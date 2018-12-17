package myprivatetunescollection.dal;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import myprivatetunescollection.be.Playlist;

/**
 * DAO = Data Access Object
 * @author DKE
 */
public class PlaylistDAO{
    SQLServerDataSource ds;
    
    public PlaylistDAO(){
        
    }
    
    //Crud Create
    public Playlist createPlaylist(int id, String name) throws SQLException{
        
        Playlist p = null;
        try(Connection conn = ds.getConnection()){
            String sql = "INSERT INTO Playlist(name) "
                    + "VALUES(?)";
            PreparedStatement stmt = conn.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, name);
            p = new Playlist(id, name);
            
            int createdRows = stmt.executeUpdate();
            
            if(createdRows == 0){
                throw new SQLException("Creating playlist failed, no rows created.");
            }
            
            try(ResultSet generatedKeys = stmt.getGeneratedKeys()){
                if(generatedKeys.next()){
                    p.setId((int) generatedKeys.getLong(1));
                }else {
                    throw new SQLException("Creating playlist failed, no ID obtained.");
                }
            }
        }
        return p;
    }
    
    //cRud Read
    public Playlist getPlaylist(int id){
        
        try(Connection conn = ds.getConnection()){
            PreparedStatement pstmt =
                    conn.prepareStatement("SELECT * FROM Playlist WHERE id=?");
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                int idp = rs.getInt("id");
                String name = rs.getString("name");
                Playlist p = new Playlist(idp, name);
                return p;
            }
        }catch(SQLServerException ex){
            Logger.getLogger(PlaylistDAO.class.getName()).log(Level.SEVERE, null, ex);
        }catch(SQLException ex){
            Logger.getLogger(PlaylistDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    //crUd Update
    public void updatePlaylist(Playlist p) throws SQLException{
        
        try(Connection conn = ds.getConnection()){
            String sql = "UPDATE Playlist SET name=? WHERE id=?";
            PreparedStatement stmt = conn.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, p.getId());
            
            int updatedRows = stmt.executeUpdate();
            
            if(updatedRows == 0){
                throw new SQLException("Updating playlist failed, no rows updated.");
            }
            try(ResultSet generatedKeys = stmt.getGeneratedKeys()){
                if(generatedKeys.next()){
                    p.setId((int) generatedKeys.getLong(1));
                }else {
                    throw new SQLException("Updating playlist failed, no ID obtained.");
                }
            }
        }
    }
    
    //cruD Delete
    public void deletePlaylist(Playlist p) throws SQLException{
        
        try(Connection conn = ds.getConnection()){
            String sql = "DELETE FROM Playlist WHERE id=?";
            PreparedStatement stmt = conn.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, p.getId());
            
            int deletedRows = stmt.executeUpdate();
            
            if(deletedRows == 0){
                throw new SQLException("Deleting playlist failed, no rows deleted.");
            }
            
            try(ResultSet generatedKeys = stmt.getGeneratedKeys()){
                if(generatedKeys.next()){
                    p.setId((int) generatedKeys.getLong(1));
                }else {
                    throw new SQLException("Deleting playlist failed, no ID obtained.");
                }
            }
        }
    }
    
    public List<Playlist> getAllPlaylists(){
        
        List<Playlist> playlists = new ArrayList();
        try(Connection conn = ds.getConnection()){
            String sqlStatement = "SELECT * FROM Playlist";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sqlStatement);
            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                Playlist p = new Playlist(id, name);
                playlists.add(p);
            }
        }catch(SQLServerException ex){
            Logger.getLogger(PlaylistDAO.class.getName()).log(Level.SEVERE, null, ex);
        }catch(SQLException ex){
            Logger.getLogger(PlaylistDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return playlists;
    }
}