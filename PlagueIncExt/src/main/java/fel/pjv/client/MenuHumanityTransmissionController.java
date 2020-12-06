/*
 * Authors: Karina Balagazova and Daria Dunina
 * FEL OI PJV 2019
 */
package fel.pjv.client;

import fel.pjv.semestral_proj.help.Request;
import fel.pjv.semestral_proj.help.RuleChangeRequest;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * 
 * Represents a class for Humanity Transmission Window
 */
public class MenuHumanityTransmissionController {

    String[] description = new String[5];
    Map<Integer, Integer> DNA = new HashMap<>();
    private RuleChangeRequest ruleChangeRequest = new RuleChangeRequest(0,0,0,0,
                0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);


    @FXML Button transmissionBtn;
    @FXML Button symptomsBtn;
    @FXML Button abilitiesBtn;

    @FXML Button exitBtn;

    @FXML Button Water1;
    @FXML Button Water2;
    @FXML Button Air1;
    @FXML Button Air2;
    @FXML Button Insect1;
    @FXML Button Insect2;
    @FXML Button People1;
    @FXML Button People2;
    @FXML Button Livestock1;
    @FXML Button Livestock2;
    
    @FXML Button evolveBtn;
    
    @FXML Label descriptionTitleLabel;
    @FXML Label descriptionLabel;
    @FXML Label DNALabel;
    
    Button thisBtn;
    Button nextBtn;
    
    ArrayList<Button> allBtnList = new ArrayList<>();
    ArrayList<Button> boughtBtnList = new ArrayList<>();

    @FXML
    private void water1BtnAction(ActionEvent event) throws IOException {
        descriptionLabel.setText(description[0]);
        thisBtn = Water1;
        nextBtn = Water2;
        descriptionTitleLabel.setText("Water 1");
        DNALabel.setText("-" + DNA.get(allBtnList.indexOf(thisBtn)) + " DNA");
        
        evolveBtnSetDisable();
        ruleChangeData(
            //HIGH      MEDIUM      LOW
            0.000f,      -0.001f,      0.000f,      //temperature 
            0.000f,       0.000f,      0.000f,      //urbanisation
           -0.005f,       0.000f,      0.000f,      //humidity
            0.000f,       0.000f,      0.000f       //development
        );
    }
    
    @FXML
    private void water2BtnAction(ActionEvent event) throws IOException {
        descriptionLabel.setText(description[0]);
        thisBtn = Water2;
        descriptionTitleLabel.setText("Water 2");
        DNALabel.setText("-" + DNA.get(allBtnList.indexOf(thisBtn)) + " DNA");
        evolveBtnSetDisable();
        ruleChangeData(
            //HIGH      MEDIUM      LOW
            0.000f,      -0.001f,      0.000f,      //temperature 
            0.000f,       0.000f,      0.000f,      //urbanisation
           -0.005f,       0.000f,      0.000f,      //humidity
            0.000f,       0.000f,      0.000f       //development
        );
    }

    @FXML
    private void air1BtnAction(ActionEvent event) throws IOException {
        descriptionLabel.setText(description[1]);
        thisBtn = Air1;
        nextBtn = Air2;
        descriptionTitleLabel.setText("Air 1");
        DNALabel.setText("-" + DNA.get(allBtnList.indexOf(thisBtn)) + " DNA");
        
        evolveBtnSetDisable();
        ruleChangeData(
            //HIGH      MEDIUM      LOW
            0.000f,      -0.001f,     0.000f,      //temperature 
            0.000f,      -0.001f,     0.000f,     //urbanisation
            0.000f,      0.000f,      -0.005f,      //humidity
            0.000f,      -0.001f,     0.000f      //development
        );
    }
    
    @FXML
    private void air2BtnAction(ActionEvent event) throws IOException {
        descriptionLabel.setText(description[1]);
        thisBtn = Air2;
        descriptionTitleLabel.setText("Air 2");
        DNALabel.setText("-" + DNA.get(allBtnList.indexOf(thisBtn)) + " DNA");
        
        evolveBtnSetDisable();
        ruleChangeData(
            //HIGH      MEDIUM      LOW
            0.000f,      -0.001f,     0.000f,      //temperature 
            0.000f,      -0.001f,     0.000f,     //urbanisation
            0.000f,      0.000f,      -0.005f,      //humidity
            0.000f,      -0.001f,     0.000f      //development
        );
    }

    @FXML
    private void insect1BtnAction(ActionEvent event) throws IOException {
        descriptionLabel.setText(description[2]);
        thisBtn = Insect1;
        nextBtn = Insect2;
        descriptionTitleLabel.setText("Insect 1");
        DNALabel.setText("-" + DNA.get(allBtnList.indexOf(thisBtn)) + " DNA");
        
        evolveBtnSetDisable();
        ruleChangeData(
            //HIGH      MEDIUM      LOW
            -0.005f,      0.000f,     0.000f,      //temperature 
            0.000f,      -0.001f,     0.000f,      //urbanisation
            0.000f,      -0.001f,     0.000f,      //humidity
            0.000f,      -0.001f,     0.000f       //development
        );
    }
    
    @FXML
    private void insect2BtnAction(ActionEvent event) throws IOException {
        descriptionLabel.setText(description[2]);
        thisBtn = Insect2;
        descriptionTitleLabel.setText("Insect 1");
        DNALabel.setText("-" + DNA.get(allBtnList.indexOf(thisBtn)) + " DNA");
        
        evolveBtnSetDisable();
        ruleChangeData(
            //HIGH      MEDIUM      LOW
            -0.005f,      0.000f,     0.000f,      //temperature 
            0.000f,      -0.001f,     0.000f,      //urbanisation
            0.000f,      -0.001f,     0.000f,      //humidity
            0.000f,      -0.001f,     0.000f       //development
        );
    }
    
    @FXML
    private void people1BtnAction(ActionEvent event) throws IOException {
        descriptionLabel.setText(description[3]);
        thisBtn = People1;
        nextBtn = People2;
        descriptionTitleLabel.setText("People 1");
        DNALabel.setText("-" + DNA.get(allBtnList.indexOf(thisBtn)) + " DNA");
        
        evolveBtnSetDisable();
        ruleChangeData(
            //HIGH      MEDIUM      LOW
            0.000f,      -0.001f,     0.000f,      //temperature 
            0.000f,      -0.001f,     0.000f,      //urbanisation
            0.000f,      -0.001f,     0.000f,      //humidity
            0.000f,      0.000f,      -0.005f      //development
        );
    }
    
    @FXML
    private void people2BtnAction(ActionEvent event) throws IOException {
        descriptionLabel.setText(description[3]);
        thisBtn = People2;
        descriptionTitleLabel.setText("People 2");
        DNALabel.setText("-" + DNA.get(allBtnList.indexOf(thisBtn)) + " DNA");
        
        evolveBtnSetDisable();
        ruleChangeData(
            //HIGH      MEDIUM      LOW
            0.000f,      -0.001f,     0.000f,      //temperature 
            0.000f,      -0.001f,     0.000f,      //urbanisation
            0.000f,      -0.001f,     0.000f,      //humidity
            0.000f,      0.000f,      -0.005f      //development
        );
    }

    @FXML
    private void livestock1BtnAction(ActionEvent event) throws IOException {
        descriptionLabel.setText(description[4]);
        thisBtn = Livestock1;
        nextBtn = Livestock2;
        descriptionTitleLabel.setText("Livestock 1");
        DNALabel.setText("-" + DNA.get(allBtnList.indexOf(thisBtn)) + " DNA");
        
        evolveBtnSetDisable();
        
        ruleChangeData(
            //HIGH      MEDIUM      LOW
            0.000f,      -0.001f,     0.000f,     //temperature 
            0.000f,      0.000f,      -0.005f,    //urbanisation
            0.000f,      -0.001f,     0.000f,    //humidity
            0.000f,      -0.001f,      0.000f      //development
        );
    }
    
    @FXML
    private void livestock2BtnAction(ActionEvent event) throws IOException {
        descriptionLabel.setText(description[4]);
        thisBtn = Livestock2;
        descriptionTitleLabel.setText("Livestock 2");
        DNALabel.setText("-" + DNA.get(allBtnList.indexOf(thisBtn)) + " DNA");
        
        evolveBtnSetDisable();
        
        ruleChangeData(
            //HIGH      MEDIUM      LOW
            0.000f,      -0.001f,     0.000f,     //temperature 
            0.000f,      0.000f,      -0.005f,    //urbanisation
            0.000f,      -0.001f,     0.000f,    //humidity
            0.000f,      -0.001f,      0.000f      //development
        );
    }
    

    @FXML
    private void evolveBtnAction(ActionEvent event) throws IOException {
        nextBtn.setVisible(true);
        boughtBtnList.add(thisBtn);
        
        Request request = new Request(Client.getMyIP(), Client.getOpponentIP(), Request.RequestType.RULECHANGE, ruleChangeRequest.toSendString());
        request.SendRequest();
        
        evolveBtn.setDisable(true);
        DNALabel.setVisible(false);
        thisBtn.setStyle("-fx-background-color: #090a0c, "
                    + "linear-gradient(#38424b 0%, #1f2429 20%, #191d22 100%), "
                    + "linear-gradient(#20262b, #191d22), "
                    + "radial-gradient(center 50% 0%, radius 100%, rgba(114,131,148,0.9), rgba(255,255,255,0));" 
                    + "-fx-background-radius: 5,4,3,5;" 
                    + "-fx-background-insets: 0,1,2,0;" 
                    + "-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );");
        Client.setDNA(Client.getDNA() - DNA.get(allBtnList.indexOf(thisBtn)));
        System.out.println("current DNA: " + Client.getDNA());
    }
    
    private void evolveBtnSetDisable(){
        if (evolveBtn.isDisable() || (Client.getDNA() >= DNA.get(allBtnList.indexOf(thisBtn)))) {
            evolveBtn.setDisable(false);
        }

        if (boughtBtnList.contains(thisBtn) || (Client.getDNA() < DNA.get(allBtnList.indexOf(thisBtn)))) {
            evolveBtn.setDisable(true);
        }

        if (boughtBtnList.contains(thisBtn)) {
            DNALabel.setVisible(false);
        } else {
            DNALabel.setVisible(true);
        }              
    }
    
    private void ruleChangeData(float infectionRateTempHigh, float infectionRateTempMed, 
            float infectionRateTempLow, float infectionRateUrbHigh, 
            float infectionRateUrbMed, float infectionRateUrbLow, 
            float infectionRateHumHigh, float infectionRateHumMed, 
            float infectionRateHumLow, float infectionRateDevHigh, 
            float infectionRateDevMed, float infectionRateDevLow){
        
        ruleChangeRequest.setInfectionRateTempHigh(infectionRateTempHigh);
        ruleChangeRequest.setInfectionRateTempMed(infectionRateTempMed);
        ruleChangeRequest.setInfectionRateTempLow(infectionRateTempLow);
        
        ruleChangeRequest.setInfectionRateUrbHigh(infectionRateUrbHigh);
        ruleChangeRequest.setInfectionRateUrbMed(infectionRateUrbMed);
        ruleChangeRequest.setInfectionRateUrbLow(infectionRateUrbLow);
        
        ruleChangeRequest.setInfectionRateHumHigh(infectionRateHumHigh);
        ruleChangeRequest.setInfectionRateHumMed(infectionRateHumMed);
        ruleChangeRequest.setInfectionRateHumLow(infectionRateHumLow);
        
        ruleChangeRequest.setInfectionRateDevHigh(infectionRateDevHigh);
        ruleChangeRequest.setInfectionRateDevMed(infectionRateDevMed);
        ruleChangeRequest.setInfectionRateDevLow(infectionRateDevLow);
    }

    @FXML
    private void symptomsBtnAction(ActionEvent event) throws IOException {
        MenuStages menu = MainMapHumanity.menu;
        Stage stageS = menu.getStageSymptoms();
        Stage stageT = menu.getStageTransmission();
        
        stageT.hide();
        stageS.show();
    }

    @FXML
    private void abilitiesBtnAction(ActionEvent event) throws IOException {
        MenuStages menu = MainMapHumanity.menu;
        Stage stageA = menu.getStageAbilities();
        Stage stageT = menu.getStageTransmission();
        
        stageT.hide();
        stageA.show();
    }

    @FXML
    private void exitBtnAction(ActionEvent event) throws IOException {
        MenuStages menu = MainMapHumanity.menu;
        Stage stageT = menu.getStageTransmission();
        stageT.hide();
    }

    /**
     * 
     * Initializes Humanity Transmission Window
     * @throws FileNotFoundException
     */    
    @FXML
    public void initialize() throws FileNotFoundException {
        loadDescription();
        loadDNA();
        allBtnList.add(Water1);
        allBtnList.add(Water2);
        allBtnList.add(Air1);
        allBtnList.add(Air2);
        allBtnList.add(Insect1);
        allBtnList.add(Insect2);
        allBtnList.add(People1);
        allBtnList.add(People2);
        allBtnList.add(Livestock1);
        allBtnList.add(Livestock2);
        
        for (Button btn: allBtnList)
            btn.setStyle("-fx-background-color: #090a0c "
                    + "linear-gradient(#0055a5 0%, #003363 20%, #001930 100%), "
                    + "linear-gradient(#021326, #260202), "
                    + "radial-gradient(center 50% 0%, radius 100%, rgba(32,72,165,0.9), rgba(255,255,255,0));" 
                    + "-fx-background-radius: 5,4,3,5;" 
                    + "-fx-background-insets: 0,1,2,0;" 
                    + "-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );"
                    + "-fx-text-fill: linear-gradient(white, #d0d0d0);");
    }

    private void loadDescription() throws FileNotFoundException {
        try (
                BufferedReader br = new BufferedReader(new FileReader("src/main/resources/txt_files/menu_humanity_transmission_description.txt"));) {
            String s = br.readLine();
            int i = 0;
            while (s != null) {
                description[i++] = s;
                s = br.readLine();
            }
        } catch (IOException ex) {
            System.out.println("=== Error loadDescription()");
        }
    }
    
    private void loadDNA() throws FileNotFoundException {
        try (
                BufferedReader br = new BufferedReader(new FileReader("src/main/resources/txt_files/menu_humanity_transmission_DNA.txt"));) {
            String s = br.readLine();
            int i = 0;
            while (s != null) {
                DNA.put(i, Integer.parseInt(s));
                System.out.println("=====" + i);
                System.out.println("strToInt " + Integer.parseInt(s));
                System.out.println("allBtnList size " + allBtnList.size());
                i++;
                s = br.readLine();
            }
        } catch (IOException ex) {
            System.out.println("=== Error loadDescription()");
        }
    }

}
