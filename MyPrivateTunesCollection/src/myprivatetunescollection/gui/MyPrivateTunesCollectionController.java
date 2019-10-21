package myprivatetunescollection.gui;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import myprivatetunescollection.be.Playlist;
import myprivatetunescollection.be.Song;
import myprivatetunescollection.bll.MyTunesLogic;

/**
 *
 * @author DKE
 */
public class MyPrivateTunesCollectionController implements Initializable {
    private ObservableList listSong = FXCollections.observableArrayList();
    private ObservableList listPlaylist = FXCollections.observableArrayList();
    private ObservableList listPSRelation = FXCollections.observableArrayList();
    
    @FXML private Button previousBtn;
    @FXML private Button playBtn;
    @FXML private Button nextBtn;
    @FXML private Label speakerLbl;
    @FXML private Button searchBtn;
    @FXML private Button upBtn;
    @FXML private Button downBtn;
    @FXML private Button leftBtn;
    @FXML private ListView<Playlist> playList;
    @FXML private ListView<Song> songs;
    @FXML private ListView<String> songsOnPlaylist;
    private MyTunesLogic bll;
    
    public MyPrivateTunesCollectionController(){
        bll = new MyTunesLogic();
    }
    
    @FXML
    private void setImageAudioPlayerButtons() throws MalformedURLException{
        Path dir = FileSystems.getDefault().getPath("./src/images/Previous-icon.png");
        Image image = new Image(dir.toUri().toURL().toExternalForm());
        previousBtn.setGraphic(new ImageView(image));
        
        Path dir1 = FileSystems.getDefault().getPath("./src/images/Play-icon.png");
        Image image1 = new Image(dir1.toUri().toURL().toExternalForm());
        playBtn.setGraphic(new ImageView(image1));
        
        Path dir2 = FileSystems.getDefault().getPath("./src/images/Next-icon.png");
        Image image2 = new Image(dir2.toUri().toURL().toExternalForm());
        nextBtn.setGraphic(new ImageView(image2));
        
        Path dir3 = FileSystems.getDefault().getPath("./src/images/Speaker-icon.png");
        Image image3 = new Image(dir3.toUri().toURL().toExternalForm());
        speakerLbl.setGraphic(new ImageView(image3));
    }
    
    @FXML
    private void setImagePlaylistSearchButtons() throws MalformedURLException{
        Path dir = FileSystems.getDefault().getPath("./src/images/Arrow-up.jpg");
        Image image = new Image(dir.toUri().toURL().toExternalForm());
        upBtn.setGraphic(new ImageView(image));
        
        Path dir1 = FileSystems.getDefault().getPath("./src/images/Arrow-down.png");
        Image image1 = new Image(dir1.toUri().toURL().toExternalForm());
        downBtn.setGraphic(new ImageView(image1));
        
        Path dir2 = FileSystems.getDefault().getPath("./src/images/Arrow-left.png");
        Image image2 = new Image(dir2.toUri().toURL().toExternalForm());
        leftBtn.setGraphic(new ImageView(image2));
        
        Path dir3 = FileSystems.getDefault().getPath("./src/images/Search-icon.png");
        Image image3 = new Image(dir3.toUri().toURL().toExternalForm());
        searchBtn.setGraphic(new ImageView(image3));
    }
    
    @FXML
    private void handleNewEditSongButtonAction(ActionEvent e) throws IOException{
        openNewEditWindow(e, null);
    }
    
    private void openNewEditWindow(ActionEvent e, Song selected) throws IOException{
        Stage newEditSongWindow = new Stage();
        newEditSongWindow.initModality(Modality.NONE);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("NewEditSong.fxml"));
        Parent root = loader.load();
        NewEditSongController nsc = loader.getController();
        nsc.setParentWindowController(this);
        nsc.setSongToEdit(selected);
        
        Scene scene = new Scene(root);
        newEditSongWindow.setTitle("New / Edit Song");
        newEditSongWindow.setScene(scene);
        newEditSongWindow.showAndWait();
    }
    
    @FXML
    private void handleNewEditPlayListButtonAction(ActionEvent e) throws IOException{
        Stage newEditPlaylistWindow = new Stage();
        newEditPlaylistWindow.initModality(Modality.NONE);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("NewEditPlayList.fxml"));
        Parent root = loader.load();
        NewEditPlayListController npc = loader.getController();
        npc.setParentWindowController(this);
        
        Scene scene = new Scene(root);
        newEditPlaylistWindow.setTitle("New / Edit Playlist");
        newEditPlaylistWindow.setScene(scene);
        newEditPlaylistWindow.showAndWait();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
        try{
            setImagePlaylistSearchButtons();
            setImageAudioPlayerButtons();
            
            //Add song
            String a = "Title\t\t\t\t\tArtist\t\t Category\t\tTime";
            listSong.add(a);
            List<Song> list = bll.getAllSongs();
            listSong.addAll(list);
            
            //Add playlist
            String b = "Name \t\t Songs \t   Time";
            listPlaylist.add(b);
            List<Playlist> listp = bll.getAllPlaylists();
            listPlaylist.addAll(listp);
            
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }
    
    public void addPlaylist(String name){
        try {
            Playlist playlist = bll.createPlaylist(name);
            playList.getItems().clear();
            listPlaylist.addAll(playlist);
            playList.getItems().addAll(listPlaylist);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void addSong(Song s){
        try {
            Song song = bll.createSong(s);
            songs.getItems().clear();
            listSong.addAll(song);
            songs.getItems().addAll(listSong);
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }
    
    @FXML
    private void deleteSong(ActionEvent e){
        try {
            Song selected =
                songs.getSelectionModel().getSelectedItem();
            bll.deleteSong(selected);
            songs.getItems().remove(selected);
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }
    
    @FXML
    private void deletePlaylist(ActionEvent e){
        try {
            Playlist selected =
                playList.getSelectionModel().getSelectedItem();
            bll.deletePlaylist(selected);
            playList.getItems().remove(selected);
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }
    
    @FXML
    private void editSong(ActionEvent e) throws IOException{
        Song selected = songs.getSelectionModel().getSelectedItem();
        openNewEditWindow(e, selected);
    }
}