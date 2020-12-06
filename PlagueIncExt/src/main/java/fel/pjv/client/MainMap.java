/*
 * Authors: Karina Balagazova and Daria Dunina
 * FEL OI PJV 2019
 */
package fel.pjv.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fel.pjv.semestral_proj.help.GameCell;
import fel.pjv.semestral_proj.help.NewGameRequest;
import fel.pjv.semestral_proj.help.NewGameRequest.Role;
import fel.pjv.semestral_proj.help.Request;
import fel.pjv.semestral_proj.help.Request.RequestType;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * Represents an abstract class for Map
 */
public abstract class MainMap {

    private final int CELL_SIZE = 8;
    private final String MAP_FILE_NAME = "pictures/world-map.png";
    private final String BACKGROUND_FILE_NAME = "pictures/background.png";
    private Role role;
    private Boolean firstClick = true;
    public static Stage mainMapStage;
    public static Label DNALabel;
    private Canvas canvas;
    private ModelMap worldMap;
    private int red, green;
    private int deadRedCoef;

    /**
     * Initializes Map Window
     *
     * @param primaryStage stage with main interface
     * @param r player role
     */
    public void MapWindow(Stage primaryStage, Role r) {
        TitleController.listener.setMap(this);
        mainMapStage = primaryStage;

        role = r;
        StackPane root = new StackPane();
        Scene scene = new Scene(root);
        Image mapImage = new Image(MAP_FILE_NAME);
        Image backgroundImage = new Image(BACKGROUND_FILE_NAME);
        ImageView mapImageView = new ImageView(mapImage);
        ImageView backgroundImageView = new ImageView(backgroundImage);
        canvas = new Canvas(mapImage.getWidth(), mapImage.getHeight());
        GraphicsContext gc = canvas.getGraphicsContext2D();
        HBox bottomMenu = new HBox();
        DNALabel = new Label();

        DNALabel.setText("DNA:" + Client.getDNA());

        primaryStage.initStyle(StageStyle.TRANSPARENT);
        scene.setFill(Color.TRANSPARENT);

        scene.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent t) {
                if (t.getCode() == KeyCode.ESCAPE) {
                    Client.setState(Client.PlayerStatus.ACCESSIBLE);
                    Request request = new Request(Client.getMyIP(), Client.getOpponentIP(), Request.RequestType.ENDGAME);
                    request.SendRequest();

                    primaryStage.close();
                }
            }
        });

        VBox vbox = new VBox();
        vbox.getChildren().addAll(canvas, bottomMenu);
        vbox.setPadding(new Insets((backgroundImage.getHeight() - canvas.getHeight()) / 2, 0.0, 0.0, 0.0));

        gc.setFill(Color.LIGHTGREEN);

        System.out.println("Pixel size : x = " + (int) (mapImage.getWidth() / CELL_SIZE) + " y = " + (int) (mapImage.getHeight() / CELL_SIZE));

        //Button Improoving
        Button improveBtn = new Button();
        improveBtn.setText("Improving");
        if (r == Role.PANDEMIA)
            improveBtn.setDisable(true);

        improveBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                try {
                    if (r == Role.PANDEMIA) {
                        MainMapPandemia.clickButton(e);
                    } else {
                        MainMapHumanity.clickButton(e);
                    }
                } catch (Exception ex) {
                    Logger.getLogger(MainMap.class.getName()).log(Level.SEVERE, null, ex);

                }
            }
        });
        
        worldMap = new ModelMap((int) mapImage.getWidth(), (int) mapImage.getHeight());
        canvas.setOnMouseClicked((event) -> {
            improveBtn.setDisable(false);

            Integer x = (int) event.getX() / CELL_SIZE;
            Integer y = (int) event.getY() / CELL_SIZE;
            ObjectMapper objMapper = new ObjectMapper();
            String json = null;
            try {
                json = objMapper.writeValueAsString(x * 1000 + y);

                if (firstClick && role == Role.PANDEMIA) {
                    //send start points
                    Request request = new Request(Client.getMyIP(), Client.getOpponentIP(), RequestType.STARTPOINT, json);
                    request.SendRequest();
                    firstClick = false;
                    worldMap.fillCell(x, y, canvas, 125, 125);
                } else {
                    //get info
                }
            } catch (JsonProcessingException ex) {
                Logger.getLogger(NewGameRequest.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        

        improveBtn.setPrefSize(500, (backgroundImage.getHeight() - canvas.getHeight()) / 2);
        DNALabel.setPrefSize(400, (backgroundImage.getHeight() - canvas.getHeight()) / 2);

        if (r == Role.PANDEMIA) {
            bottomMenu.setStyle("-fx-background-color: rgb(200, 0, 0);");
            improveBtn.setStyle("-fx-background-color: #090a0c "
                    + "linear-gradient(#a50000 0%, #800000 20%, #260202 100%), "
                    + "linear-gradient(#260202, #260202), "
                    + "radial-gradient(center 50% 0%, radius 100%, rgba(165,33,33,0.9), rgba(255,255,255,0));" 
                    + "-fx-background-radius: 5,4,3,5;" 
                    + "-fx-background-insets: 0,1,2,0;" 
                    + "-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 ); "
                    + "-fx-text-fill: linear-gradient(white, #d0d0d0);");
        } else {
            bottomMenu.setStyle("-fx-background-color: rgb(0, 155, 255);");
            improveBtn.setStyle("-fx-background-color: #090a0c "
                    + "linear-gradient(#0055a5 0%, #003363 20%, #001930 100%), "
                    + "linear-gradient(#021326, #260202), "
                    + "radial-gradient(center 50% 0%, radius 100%, rgba(32,72,165,0.9), rgba(255,255,255,0));" 
                    + "-fx-background-radius: 5,4,3,5;" 
                    + "-fx-background-insets: 0,1,2,0;" 
                    + "-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );"
                    + "-fx-text-fill: linear-gradient(white, #d0d0d0);");
        }
        bottomMenu.getChildren().addAll(improveBtn, DNALabel);
        bottomMenu.setAlignment(Pos.BOTTOM_LEFT);
        bottomMenu.setPrefSize(canvas.getWidth(), backgroundImage.getHeight() - canvas.getHeight());

        root.getChildren().addAll(backgroundImageView, mapImageView, vbox);

        primaryStage.setMinHeight(backgroundImage.getHeight());
        primaryStage.setMaxHeight(backgroundImage.getHeight());
        primaryStage.setMinWidth(backgroundImage.getWidth());
        primaryStage.setTitle("world map");
        primaryStage.setScene(scene);
        primaryStage.showAndWait();
    }

    /**
     *
     * Closes the Window
     */
    public void closeStage() {
        mainMapStage.close();
    }
    
    /**
     *
     * Updates the DNA label on the map
     */
    public void updateDNALabel() {
        Platform.runLater(() -> {
            System.out.println("fill cells cur DNA" + Client.getDNA());
            DNALabel.setText("DNA: " + Client.getDNA());
        });
    }

    /**
     * 
     * Updates the DNA count
     * @param newSize size of update cells list
     * @param oldSize old size of update cells list
     */
    public void updateDNA(int newSize, int oldSize){
        if (role == Role.PANDEMIA)
            return;
        
        int diff = newSize - oldSize;
        if (diff > 0 && diff <= 5){
            Client.setDNA(Client.getDNA() + 1);
            return;
        }
        if (diff > 5 && diff <= 10){
            Client.setDNA(Client.getDNA() + 2);
            return;
        }
        if (diff > 10){
            Client.setDNA(Client.getDNA() + 3);
            return;
        }
    }
    
    
    /**
     *
     * Fills the cell with a certain color depending on its properties
     * @param cell
     */
    public void fillCells(GameCell cell) {
        if (cell.sickCount <= 50) {
            System.out.println("Fill cell: x " + cell.x + ", y " + cell.y);
            red = 125;
            green = 255 - red;
            worldMap.fillCell(cell.x, cell.y, canvas, red, green);
            return;
        }

        if (cell.sickCount <= 100) {
            System.out.println("Fill cell: x " + cell.x + ", y " + cell.y);
            red = 150;
            green = 255 - red;
            worldMap.fillCell(cell.x, cell.y, canvas, red, green);
            return;
        }

        if (cell.sickCount <= 200) {
            System.out.println("Fill cell: x " + cell.x + ", y " + cell.y);
            red = 175;
            green = 255 - red;
            if (role == Role.PANDEMIA)
                Client.setDNA(Client.getDNA() + 1);
            else 
                
            return;
        }

        if (cell.sickCount <= 300) {
            System.out.println("Fill cell: x " + cell.x + ", y " + cell.y);
            red = 200;
            green = 255 - red;
            worldMap.fillCell(cell.x, cell.y, canvas, red, green);
            if (randomValue(30) == 1 && role == Role.PANDEMIA) {
                Client.setDNA(Client.getDNA() + 1);
            }
            return;
        }

        if (cell.sickCount <= 400) {
            System.out.println("Fill cell: x " + cell.x + ", y " + cell.y);
            red = 225;
            green = 255 - red;
            worldMap.fillCell(cell.x, cell.y, canvas, red, green);
            if (randomValue(30) == 1 && role == Role.PANDEMIA) {
                Client.setDNA(Client.getDNA() + 1);
            }
            return;
        }

        if (cell.sickCount <= cell.totalCount) {
            System.out.println("Fill cell: x " + cell.x + ", y " + cell.y);
            red = 255;
            green = 255 - red;
            worldMap.fillCell(cell.x, cell.y, canvas, red, green);
            if (randomValue(20) == 1 && role == Role.PANDEMIA) {
                Client.setDNA(Client.getDNA() + 2);
            }
        }

        if (cell.deadCount <= 1) {
            deadRedCoef = red / 6;
            red = 6 * deadRedCoef - 1;
            return;
        }

        if (cell.deadCount <= 50) {
            System.out.println("Fill cell: x " + cell.x + ", y " + cell.y);
            red = 5 * deadRedCoef;
            worldMap.fillCell(cell.x, cell.y, canvas, red, green);
            return;
        }

        if (cell.deadCount <= 100) {
            System.out.println("Fill cell: x " + cell.x + ", y " + cell.y);
            red = 4 * deadRedCoef;
            worldMap.fillCell(cell.x, cell.y, canvas, red, green);
            return;
        }

        if (cell.deadCount <= 200) {
            System.out.println("Fill cell: x " + cell.x + ", y " + cell.y);
            red = 3 * deadRedCoef;
            worldMap.fillCell(cell.x, cell.y, canvas, red, green);
            return;
        }

        if (cell.deadCount <= 300) {
            System.out.println("Fill cell: x " + cell.x + ", y " + cell.y);
            red = 2 * deadRedCoef;
            worldMap.fillCell(cell.x, cell.y, canvas, red, green);
            return;
        }

        if (cell.deadCount <= 400) {
            System.out.println("Fill cell: x " + cell.x + ", y " + cell.y);
            red = deadRedCoef;
            worldMap.fillCell(cell.x, cell.y, canvas, red, green);
            return;
        }

        if (cell.deadCount <= cell.totalCount) {
            System.out.println("Fill cell: x " + cell.x + ", y " + cell.y);
            red = 0;
            worldMap.fillCell(cell.x, cell.y, canvas, red, green);
        }
    }

    private int randomValue(int size) {
        int randomIntValue = (int) (Math.random() * size);
        return randomIntValue;
    }
}
