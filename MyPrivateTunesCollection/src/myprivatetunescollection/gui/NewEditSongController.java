package myprivatetunescollection.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import myprivatetunescollection.be.Song;

/**
 * FXML Controller class
 *
 * @author DKE
 */
public class NewEditSongController implements Initializable {
    
    private MyPrivateTunesCollectionController parent;
    @FXML
    private TextField newSongTitleFld;
    @FXML
    private Button saveSongBtn;
    @FXML
    private TextField newSongArtistFld;
    @FXML
    private TextField newSongFileFld;
    @FXML
    private TextField newSongTimeFld;
    @FXML
    private Button cancelSongBtn;
    @FXML
    private ComboBox<String> genres;
    private Song selected = null;
    
    public void setParentWindowController(MyPrivateTunesCollectionController parent){
        this.parent = parent;
    }
    
    private void createComboBoxItems(){
        ObservableList<String> genreList =
                FXCollections.observableArrayList(
                        "Rock", "Pop", "Country", "Jazz",
                        "Classical", "Blues"
                );
        genres.getItems().addAll(genreList);
        genres.setPromptText("Select a genre");
    }
    
    @FXML
    private void handleSaveSongButtonAction(ActionEvent event){
        Song s = new Song(0, newSongTitleFld.getText(), newSongArtistFld.getText());
        parent.addSong(s);
        Stage ss = (Stage)saveSongBtn.getScene().getWindow();
        ss.close();
    }
    
    @FXML
    private void handleCancelSongButtonAction(ActionEvent event){
        Stage cs = (Stage)cancelSongBtn.getScene().getWindow();
        cs.close();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        createComboBoxItems();
    }
    
    void setSongToEdit(Song selected){
        this.selected=selected;
        if(selected != null){
            newSongTitleFld.setText(selected.getTitle());
            newSongArtistFld.setText(selected.getArtist());
        }
    }
}