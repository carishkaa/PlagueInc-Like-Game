/*
 * Authors: Karina Balagazova and Daria Dunina
 * FEL OI PJV 2019
 */
package fel.pjv.client;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * 
 * Represents a class for Humanity Map
 */ 
public class MainMapHumanity extends MainMap {
    public static MenuStages menu;
    static boolean isFirstOpening = true;
    
    /**
     * 
     * Opens a window to buy improvements
     * @param event mouse event where to open the window
     * @throws java.lang.Exception
     */ 
    public static void clickButton(MouseEvent event) throws Exception {
        try {
            if (isFirstOpening) {
                FXMLLoader fxmlLoader;

                fxmlLoader = new FXMLLoader(MainMapHumanity.class.getResource("/fxml/MenuHumanityTransmission.fxml"));
                Parent root = (Parent) fxmlLoader.load();
                Stage stageT = new Stage();
                stageT.setScene(new Scene(root));
                stageT.show();

                fxmlLoader = new FXMLLoader(MainMapHumanity.class.getResource("/fxml/MenuHumanitySymptoms.fxml"));
                root = (Parent) fxmlLoader.load();
                Stage stageS = new Stage();
                stageS.setScene(new Scene(root));
                stageS.show();
                stageS.hide();

                fxmlLoader = new FXMLLoader(MainMapHumanity.class.getResource("/fxml/MenuHumanityAbilities.fxml"));
                root = (Parent) fxmlLoader.load();
                Stage stageA = new Stage();
                stageA.setScene(new Scene(root));
                stageA.show();
                stageA.hide();

                menu = new MenuStages(stageT, stageS, stageA);
                isFirstOpening = false;
                
            } else {
                Stage stageT = menu.getStageTransmission();
                stageT.show();
            }

        } catch (IOException e) {
            Logger.getLogger(MainMapPandemia.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
