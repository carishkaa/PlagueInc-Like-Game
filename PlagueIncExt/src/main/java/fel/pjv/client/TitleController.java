/*
 * Authors: Karina Balagazova and Daria Dunina
 * FEL OI PJV 2019
 */
package fel.pjv.client;

import fel.pjv.client.Client.PlayerStatus;
import java.io.IOException;
import static java.lang.Thread.MIN_PRIORITY;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * Represents a controller for Title.fxml
 */
public class TitleController implements Initializable {
    public static NIOListener listener;

    /**
    * Gets the listener
     * @return listener
    */    
    public NIOListener getListener() {
        return listener;
    }
    
    @FXML 
    private Button startBtn;
    
    @FXML 
    private Button readRulesBtn;
    

    //click "START NEW GAME" -> open window in which you choose role and invite a player
    @FXML
    private void startBtnAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) startBtn.getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/ChooseRole.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root1));
        stage.show();
        
        }
    
    //click "READ THE RULES" -> open window with the rules
    @FXML
    private void readRulesBtnAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) readRulesBtn.getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/rules.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root));
        stage.show();
    }

    /**
     *
     * Initializes the Title Window
     * @param url
     * @param rb
     */    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Client.setState(PlayerStatus.ACCESSIBLE);
        listener = new NIOListener();
        listener.setDaemon(true);
        listener.setPriority(MIN_PRIORITY);
        listener.start();
           
    }
    
}
