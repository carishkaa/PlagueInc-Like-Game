/*
 * Authors: Karina Balagazova and Daria Dunina
 * FEL OI PJV 2019
 */
package fel.pjv.client;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import fel.pjv.semestral_proj.help.GameCell;
import fel.pjv.semestral_proj.help.NewGameRequest;
import fel.pjv.semestral_proj.help.NewGameRequest.GameRequestStatus;
import fel.pjv.semestral_proj.help.NewGameRequest.Role;
import fel.pjv.semestral_proj.help.Request;
import fel.pjv.semestral_proj.help.Request.RequestType;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

/**
 * Represents a class which listens for new messages from server
 */
public class NIOListener extends Thread {

    private final int START_DNA_VALUE = 20;
    private ChooseRoleController chooseRole;
    private MainMap map;
    private ArrayList<GameCell> updateCells;
    private int updateCellsSize;
    private int updateCellsOldSize = 0;
    
    /**
     * Empty constructor
     */
    public NIOListener() {
    }

    /**
     * Gets the list of updated cells
     * @return updated cells
     */
    public ArrayList<GameCell> getUpdateCells() {
        return updateCells;
    }

    /**
     * Sets new ChooseRoleController
     * @param chooseRole Choose role controller
     */    
    public void setChooseRole(ChooseRoleController chooseRole) {
        this.chooseRole = chooseRole;
    }

    /**
     * 
     * Sets new Main map
     * @param map new map
     */ 
    public void setMap(MainMap map) {
        this.map = map;
    }

    /**
     * Starts listening for new messages
     */
    @Override
    public void run() {
        ByteBuffer readBuffer = ByteBuffer.allocate(7000000);
        while (!Thread.interrupted()) {
            readBuffer.clear();
            int length = -1;

            while (length == -1) {
                try {
                    length = Client.getClient().read(readBuffer);
                } catch (IOException ex) {
                    continue;
                }

                if (length != -1) {
                    readBuffer.flip();
                    readBuffer.clear();
                    byte[] buff = new byte[7000000];
                    readBuffer.get(buff, 0, length);
                    Logger.getLogger(NIOListener.class.getName()).log(Level.INFO, "lengthBuf " + length);
                    
                    ObjectMapper objectMapper = new ObjectMapper();
                    objectMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
                    Request request;
                    try {
                        request = objectMapper.readValue(buff, Request.class);
                        Logger.getLogger(NIOListener.class.getName()).log(Level.INFO, "Read Request");
                    } catch (IOException ex) {
                        Logger.getLogger(TitleController.class.getName()).log(Level.SEVERE, null, ex);
                        continue;
                    }

                    switch (request.requestType) {
                        case ENDGAMEWINNER:
                            Logger.getLogger(NIOListener.class.getName()).log(Level.INFO, "Request type: ENDGAMEWINNER");
                            try {
                                String winnerMessage = objectMapper.readValue(request.data, String.class);
                                Platform.runLater(() -> {
                                    Alert alert = new Alert(AlertType.INFORMATION);
                                    alert.setTitle("Game over");
                                    alert.setHeaderText(null);
                                    alert.setContentText(winnerMessage);

                                    alert.showAndWait();
                                    map.closeStage();
                                });

                            } catch (IOException ex) {
                                Logger.getLogger(NIOListener.class.getName()).log(Level.SEVERE, null, ex);
                            }

                            break;
                        case ENDGAME:
                            Logger.getLogger(NIOListener.class.getName()).log(Level.INFO, "Request type: ENDGAME");
                            Client.setState(Client.PlayerStatus.ACCESSIBLE);
                            Platform.runLater(() -> {
                                Alert alert = new Alert(AlertType.INFORMATION);
                                alert.setTitle("No connection");
                                alert.setHeaderText(null);
                                alert.setContentText("No connection with Player2. Press OK to exit.");

                                alert.showAndWait();
                                map.closeStage();
                            });
                            break;

                        case MAPUPDATE:
                            Logger.getLogger(NIOListener.class.getName()).log(Level.INFO, "Request type: MAPUPDATE");
                            System.out.println("Got update");
                            JavaType type = objectMapper.getTypeFactory()
                                    .constructCollectionType(ArrayList.class, GameCell.class);
                            try {
                                map.updateDNALabel();
                                updateCells = objectMapper.readValue(request.data, type);
                                updateCellsSize = updateCells.size();
                                map.updateDNA(updateCellsSize, updateCellsOldSize);
                                for (GameCell cell : updateCells) {
                                    map.fillCells(cell);
                                }
                                updateCellsOldSize = updateCellsSize;
                                Request confirm = new Request();
                                confirm.requestType = RequestType.CONFIRM;
                                confirm.SenderIP = Client.getMyIP();
                                confirm.ReceiverIP = Client.getOpponentIP();
                                confirm.SendRequest();
                            } catch (IOException ex) {
                                Logger.getLogger(NIOListener.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            break;

                        case GETPLAYERS:
                            Logger.getLogger(NIOListener.class.getName()).log(Level.INFO, "Request type: GETPLAYERS");
                            Request answer = null;
                            try {
                                answer = objectMapper.readValue(buff, Request.class);
                                List<String> players = (ArrayList<String>) objectMapper.readValue(answer.data, Object.class);
                                ObservableList<String> playersView = FXCollections.observableArrayList(players);

                                Platform.runLater(() -> {
                                    chooseRole.setPlayersListView(playersView);
                                });

                            } catch (IOException ex) {
                                Logger.getLogger(NIOListener.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            break;
                        case NEWGAME:
                            Logger.getLogger(NIOListener.class.getName()).log(Level.INFO, "Request type: NEWGAME");
                            try {
                                NewGameRequest invitation = objectMapper.readValue(request.data, NewGameRequest.class);
                                if (invitation.Status == GameRequestStatus.INVITED
                                        && Client.getState() == Client.PlayerStatus.ACCESSIBLE) {
                                    Platform.runLater(() -> {
                                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                        alert.setTitle("You are invited to play");
                                        alert.setHeaderText("Invitation from " + request.SenderIP);
                                        alert.setContentText("You will play as " + invitation.ReceiverRole);

                                        Optional<ButtonType> result = alert.showAndWait();
                                        if (result.get() == ButtonType.OK) {
                                            invitation.Status = GameRequestStatus.ACCEPTED;
                                            String tmp = request.ReceiverIP;
                                            request.ReceiverIP = request.SenderIP;
                                            request.SenderIP = tmp;
                                            Client.setState(Client.PlayerStatus.NOTACCESSIBLE);
                                            Client.setMyIP(request.SenderIP);
                                            Client.setOpponentIP(request.ReceiverIP);
                                            request.data = invitation.toSendString();
                                            request.SendRequest();

                                            Stage stage = new Stage();
                                            Client.setDNA(START_DNA_VALUE);
                                            if (invitation.ReceiverRole == Role.HUMANITY) {
                                                MainMapHumanity mapH = new MainMapHumanity();
                                                mapH.MapWindow(stage, Role.HUMANITY);
                                            } else {
                                                MainMapPandemia mapP = new MainMapPandemia();
                                                mapP.MapWindow(stage, Role.PANDEMIA);
                                            }
                                        } else {
                                            invitation.Status = GameRequestStatus.DECLINED;
                                            String tmp = request.ReceiverIP;
                                            request.ReceiverIP = request.SenderIP;
                                            request.SenderIP = tmp;
                                            request.data = invitation.toSendString();
                                            request.SendRequest();
                                        }
                                    });
                                } else if (invitation.Status == GameRequestStatus.ACCEPTED) {
                                    Client.setMyIP(request.ReceiverIP);
                                    Client.setOpponentIP(request.SenderIP);
                                    Platform.runLater(() -> {
                                        Alert alert = new Alert(AlertType.INFORMATION);
                                        alert.setTitle("Invitation accepted");
                                        alert.setHeaderText(null);
                                        alert.setContentText("Press OK to start.");
                                        alert.showAndWait();

                                        Stage stage = new Stage();
                                        Client.setDNA(START_DNA_VALUE);
                                        if (invitation.SenderRole == Role.HUMANITY) {
                                            MainMapHumanity mapH = new MainMapHumanity();
                                            mapH.MapWindow(stage, Role.HUMANITY);
                                        } else {
                                            MainMapPandemia mapP = new MainMapPandemia();
                                            mapP.MapWindow(stage, Role.PANDEMIA);
                                        }
                                    });
                                } else {
                                    Client.setState(Client.PlayerStatus.DECLINED);
                                    Platform.runLater(() -> {
                                        Alert alert = new Alert(AlertType.INFORMATION);
                                        alert.setTitle("Invitation declined");
                                        alert.setHeaderText(null);
                                        alert.setContentText("Press OK to contunue.");

                                        alert.showAndWait();
                                    });
                                }

                            } catch (IOException ex) {
                                Logger.getLogger(TitleController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            break;
                    }
                }
            }
        }
    }
}
