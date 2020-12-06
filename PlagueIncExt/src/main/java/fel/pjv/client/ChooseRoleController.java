/*
 * Authors: Karina Balagazova and Daria Dunina
 * FEL OI PJV 2019
 */
package fel.pjv.client;

import java.io.IOException;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Modality;
import javafx.stage.Stage;
import fel.pjv.semestral_proj.help.NewGameRequest;
import fel.pjv.semestral_proj.help.NewGameRequest.Role;
import fel.pjv.semestral_proj.help.Request;
import fel.pjv.semestral_proj.help.Request.RequestType;
import javafx.scene.input.MouseEvent;

/**
 *
 * Represents a controller for a Choose role window ChoodeRole.fxml
 */
public class ChooseRoleController {

    @FXML
    private Button chooseRoleOKBtn;

    @FXML
    private Button chooseRoleCancelBtn;

    @FXML
    private RadioButton humanityRadioBtn;

    @FXML
    private RadioButton pandemiaRadioBtn;

    @FXML
    private Button findPlayersBtn;

    @FXML
    private Label invitePlayerLabel;

    @FXML
    final ToggleGroup role = new ToggleGroup();

    @FXML
    private ListView<String> PlayersListView = new ListView<String>();

    public void setPlayersListView(ObservableList<String> playersView) {
        PlayersListView.setItems(playersView);
        PlayersListView.refresh();
    }

    @FXML
    private void chooseRoleCancelBtnAction(ActionEvent event) throws IOException {
        Stage stage1 = (Stage) chooseRoleCancelBtn.getScene().getWindow();
        stage1.close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Title.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        stage1 = new Stage();
        stage1.initModality(Modality.APPLICATION_MODAL);
        stage1.setScene(new Scene(root));
        stage1.show();
    }

    @FXML
    private void chooseRoleOKBtnAction(ActionEvent event) throws IOException {
        Role r = humanityRadioBtn.isSelected()? Role.HUMANITY :
                Role.PANDEMIA;
        
        String recIP = PlayersListView.getSelectionModel().getSelectedItem();
            Request newGame = new Request(recIP,new NewGameRequest(r));
            newGame.SendRequest();
            Client.setState(Client.PlayerStatus.AWAITING); 
    }
    
    /**
    * Enables the "OK" button after selecting a player to invite
    */
    public void listClicked(MouseEvent event) {
        chooseRoleOKBtn.setDisable(false);
    }

    @FXML
    private void findPlayersBtnAction(ActionEvent event) throws IOException {
        Request request = new Request(Client.getMyIP(), null, RequestType.GETPLAYERS);
        request.SendRequest();
    }
    
    /**
    *
    * Initializes new Choose Role Window
    */
    public void initialize() {
        TitleController.listener.setChooseRole(this);
        Client.setState(Client.PlayerStatus.NOTACCESSIBLE);
            TitleController.listener.setChooseRole(this);
            Client.setState(Client.PlayerStatus.NOTACCESSIBLE);
    }

}
