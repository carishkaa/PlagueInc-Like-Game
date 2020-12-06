/*
 * Authors: Karina Balagazova and Daria Dunina
 * FEL OI PJV 2019
 */
package fel.pjv.client;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * Represents a controller for a connecting window Connecting.fxml
 * 
 */
public class ConnectingController implements Initializable {
    
    @FXML
    private ProgressBar ConnectPgb;
    
    @FXML
    private Label ConnectLbl;
    
    @FXML 
    private Button OKBtn;
    
    @FXML 
    private Button CancelBtn;
    
    @FXML
    private void OKBtnAction(ActionEvent event) throws IOException {
        
    Stage stage = (Stage) OKBtn.getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Title.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root));
        stage.show();
    }
    
    @FXML
    private void CancelBtnAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) CancelBtn.getScene().getWindow();
        stage.close();
        }
    
    /**
     *
     * Initializes Connection Window
     * 
     * @param url
     * @param rb
     */    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            OKBtn.setDisable(true);
           
            Client.startClient();
            ConnectLbl.setText("Connection established successfully");
            ConnectPgb.setVisible(false);
            OKBtn.setDisable(false);
        } catch (Exception ex) {
            Logger.getLogger(ConnectingController.class.getName()).log(Level.SEVERE, null, ex);
            ConnectLbl.setText("Connection couldn't be established");
        }
    }
    
}
