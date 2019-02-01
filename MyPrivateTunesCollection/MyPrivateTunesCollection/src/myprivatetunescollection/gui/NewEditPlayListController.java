package myprivatetunescollection.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author DKE
 */
public class NewEditPlayListController implements Initializable {
    
    private MyPrivateTunesCollectionController parent;
    @FXML
    private TextField newPlaylistFld;
    @FXML
    private Button saveBtn;
    @FXML
    private Button cancelBtn;
    
    public void setParentWindowController(MyPrivateTunesCollectionController parent){
        this.parent = parent;
    }
    
    @FXML
    private void handleSaveButtonAction(ActionEvent e){
        parent.addPlaylist(newPlaylistFld.getText());
        Stage s = (Stage)saveBtn.getScene().getWindow();
        s.close();
    }
    
    @FXML
    private void handleCancelButtonAction(ActionEvent e){
        Stage c = (Stage)cancelBtn.getScene().getWindow();
        c.close();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
        // TODO
    }
}